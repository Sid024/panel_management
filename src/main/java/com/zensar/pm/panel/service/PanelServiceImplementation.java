package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zensar.pm.panel.dto.InterviewTypeDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityListDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityStatusDTO;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.dto.RoleDto;
import com.zensar.pm.panel.dto.SearchByFilterDTO;
import com.zensar.pm.panel.dto.ShowPanelAvailabilityListDTO;
import com.zensar.pm.panel.entity.GradeEntity;
import com.zensar.pm.panel.entity.InterviewTypesEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelCandidateRolesEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.RolesEntity;
import com.zensar.pm.panel.entity.UserEntity;
import com.zensar.pm.panel.entity.UserRolesEntity;
import com.zensar.pm.panel.enums.Constants;
import com.zensar.pm.panel.exceptions.ContactAlreadyExistsException;
import com.zensar.pm.panel.exceptions.CustomNullPointerException;
import com.zensar.pm.panel.exceptions.EmailAlreadyExistException;
import com.zensar.pm.panel.exceptions.EmptyListException;
import com.zensar.pm.panel.exceptions.GradeNotFoundException;
import com.zensar.pm.panel.exceptions.InterviewTypeNotFoundException;
import com.zensar.pm.panel.exceptions.InvalidPanelException;
import com.zensar.pm.panel.exceptions.MatchNotFoundException;
import com.zensar.pm.panel.exceptions.NoSuchRoleFoundException;
import com.zensar.pm.panel.exceptions.PanelAlreadyExists;
import com.zensar.pm.panel.exceptions.PanelCandidateRoleNotFoundException;
import com.zensar.pm.panel.exceptions.PanelNotFound;
import com.zensar.pm.panel.exceptions.UnauthorizedUserException;
import com.zensar.pm.panel.exceptions.UserNotFoundException;
import com.zensar.pm.panel.repository.GradeRepository;
import com.zensar.pm.panel.repository.InterviewTypeRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityStatusRepository;
import com.zensar.pm.panel.repository.PanelCandidateRolesRepository;
import com.zensar.pm.panel.repository.PanelEntityRepository;
import com.zensar.pm.panel.repository.RolesRepository;
import com.zensar.pm.panel.repository.UserRepository;
import com.zensar.pm.panel.repository.UserRolesRepository;
import com.zensar.pm.panel.utils.UserCreatedSuccessfullyMail;

@Service
public class PanelServiceImplementation implements PanelService {

	@Autowired
	ModelMapper modelMapper; /// I think there is no use of ModelMapper in search and export there is no use

	@Autowired
	LoginDelegate loginDelegate;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PanelAvailabilityRepository repo;/// No use of repo also b/c we are using entity manager//panel Availability

	@Autowired
	PanelAvailabilityStatusRepository panelAvailabilityStatusRepo;// status entity
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRolesRepository userRolesRepository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	PanelEntityRepository panelEntityRepository;
	@Autowired
	GradeRepository gradeRepository;

	@Autowired
	InterviewTypeRepository interviewTypeRepository;

	@Autowired
	PanelCandidateRolesRepository panelCandidateRolesRepository;

	@Autowired
	UserCreatedSuccessfullyMail userCreatedSuccessfullyMail;

	@Override
	public PanelDTO getAllPanel() {
		List<UserRolesEntity> listUserRolesEntity = userRolesRepository.findByRoleId(3);
		List<Integer> listPanelId = new ArrayList<>();
		List<String> listPanelNames = new ArrayList<>();
		for (UserRolesEntity userRolesEntity : listUserRolesEntity) {
			int userId = userRolesEntity.getUserEntity().getUserId();
			listPanelId.add(userId);
			UserEntity userEntity = userRepository.findById(userId).get();
			listPanelNames.add(userEntity.getUserName());
		}
		PanelDTO panelDto = new PanelDTO();
		panelDto.setListPanelId(listPanelId);
		panelDto.setListPanelNames(listPanelNames);
		return panelDto;
	}

	@Override
	public SearchByFilterDTO searchPanelByFilter(int panelId, String panelName, String email, String grade, String role,
			String type, boolean isActive, String token, int pageNumber, int pageSize) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PanelEntity> criteriaQuery = criteriaBuilder.createQuery(PanelEntity.class);
		if (loginDelegate.isTokenValid(token).getRoleName().equals("PANEL")
				|| loginDelegate.isTokenValid(token).getRoleName().equals("TA")) {
			throw new InvalidPanelException("Panel does not exist");
		}

		Root<PanelEntity> rootEntity = criteriaQuery.from(PanelEntity.class);

		Predicate predicateId = criteriaBuilder.and();
		Predicate predicateName = criteriaBuilder.and();
		Predicate predicateEmail = criteriaBuilder.and();
		Predicate predicateGrade = criteriaBuilder.and();
		Predicate predicateRole = criteriaBuilder.and();
		Predicate predicateType = criteriaBuilder.and();
		Predicate predicateIsActive = criteriaBuilder.and();
		Predicate predicateActive = criteriaBuilder.and();

		if (panelId != 0) {
			predicateId = criteriaBuilder.equal(rootEntity.get("userEntity").get("id"), panelId);
		}
		if (panelName != null && !"".equals(panelName)) {
			predicateName = criteriaBuilder.like(rootEntity.get("userEntity").get("userName"), ("%" + panelName + "%"));
		}
		if (email != null && !"".equals(email)) {
			predicateEmail = criteriaBuilder.like(rootEntity.get("userEntity").get("email"), ("%" + email + "%"));
		}
		if (grade != null && !"".equals(grade)) {
			predicateGrade = criteriaBuilder.equal(rootEntity.get("gradeEntity").get("grade"), grade);
		}
		if (role != null && !"".equals(role)) {
			predicateRole = criteriaBuilder.equal(rootEntity.get("panelCandidateRolesEntity").get("role"), role);
			// criteriaQuery.where(predicateName);
		}
		if (type != null && !"".equals(type)) {
			predicateType = criteriaBuilder
					.equal(rootEntity.get("panelCandidateRolesEntity").get("interviewType").get("type"), type);
			// criteriaQuery.where(predicateName);
		}
		if (isActive != false && !"".equals(isActive)) {
			predicateIsActive = criteriaBuilder.equal(rootEntity.get("userEntity").get("isActive"), isActive);
			// criteriaQuery.where(predicateName);
		}
		if (isActive != true && !"".equals(isActive)) {
			predicateActive = criteriaBuilder.equal(rootEntity.get("userEntity").get("isActive"), isActive);
		}
		Predicate finalPredicate = criteriaBuilder.and(predicateId, predicateName, predicateEmail, predicateGrade,
				predicateRole, predicateType, predicateIsActive, predicateActive);
		criteriaQuery.where(finalPredicate);

		TypedQuery<PanelEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult((pageNumber - 1) * pageSize);
		typedQuery.setMaxResults(pageSize);
		List<PanelEntity> panelEntityList = typedQuery.getResultList();
		int totalNoOfRecords = panelEntityList.size();
		List<PanelDTO> convertedDtoList = convertEntityListIntoDTOList(panelEntityList);
		SearchByFilterDTO SearchByFilterDTO = new SearchByFilterDTO();
		SearchByFilterDTO.setPanelDtoList(convertedDtoList);
		SearchByFilterDTO.setTotalNumberOfRecords(totalNoOfRecords);

		return SearchByFilterDTO;
	}

	public List<PanelDTO> convertEntityListIntoDTOList(List<PanelEntity> panelEntityList) {
		List<PanelDTO> panelDtoList = new ArrayList<PanelDTO>();
		for (PanelEntity panelEntity : panelEntityList) {
			PanelDTO panelDto = new PanelDTO(panelEntity.getUserEntity().getUserId(),
					panelEntity.getUserEntity().getUserName(), panelEntity.getUserEntity().getEmail(),
					panelEntity.getContact(), panelEntity.getGradeEntity().getGrade(),
					panelEntity.getPanelCandidateRolesEntity().getRole(), panelEntity.getInterviewType().getType(),
					panelEntity.getUserEntity().getIsActive());
			panelDtoList.add(panelDto);
		}
		return panelDtoList;
	}

	@Override
	public String updateIsActive(int panelId, String token) {
		if (loginDelegate.isTokenValid(token).getRoleName().equals("PANEL")
				|| loginDelegate.isTokenValid(token).getRoleName().equals("TA")) {
			throw new InvalidPanelException("Panel does not exist");
		}
		List<PanelEntity> listPanelEntity = panelEntityRepository.findByUserId(panelId);
		PanelEntity panelEntity = listPanelEntity.get(0);
		UserEntity userEntity = new UserEntity();
		UserRolesEntity userRolesEntity = new UserRolesEntity();
		if (panelEntity.getUserEntity().getIsActive() == false) {
			panelEntity.getUserEntity().setActive(true);
		} else {
			panelEntity.getUserEntity().setActive(false);
		}
		panelEntityRepository.save(panelEntity);
		Optional<UserEntity> opUser = userRepository.findById(panelId);
		if (opUser.isPresent()) {
			userEntity = opUser.get();
		}
		userRepository.save(userEntity);
		List<UserRolesEntity> listUserRolesEntity = userRolesRepository.findByUserId(userEntity.getUserId());
		userRolesEntity = listUserRolesEntity.get(0);
		userRolesEntity.setActive(userEntity.getIsActive());
		userRolesRepository.save(userRolesEntity);

		return "Update Successful!";
	}
///////////////////////////////////////// Team 10 /////////////////////////
	/// Update --> table need to change--> not done
    @Override
    public PanelAvailabilityDTO updatePanelAvailability(Integer panelAvailablityId,
            PanelAvailabilityDTO panelAvailablityDTO, String jwtToken) {

 

        if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())
                || Constants.TALENT_ACQUISITION.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())) {
            PanelAvailabilityEntity existingPanel = repo.findById(panelAvailablityId).orElse(null);

            //PanelAvailabilityStatusEntity availabilityEntity = panelAvailabilityStatusRepo
            //        .findById(panelAvailablityDTO.getAvailablityStatusId());
            if (existingPanel == null)
                throw new InvalidPanelException("Panel not found");
            else if (panelAvailablityDTO.getStartTime() == null || panelAvailablityDTO.getEndTime() == null)
                throw new CustomNullPointerException("Empty value! enter the value");

 

            else {
                existingPanel.setUpdatedBy(loginDelegate.isTokenValid(jwtToken).getUserName());
                existingPanel.setUpdatedOn(LocalDateTime.now());
                existingPanel.setStartTime(panelAvailablityDTO.getStartTime());
                existingPanel.setEndTime(panelAvailablityDTO.getEndTime());
                if(panelAvailablityDTO.getAvailablityStatusId()!=0)
                existingPanel.setAvailablityStatusId(panelAvailabilityStatusRepo.findById(panelAvailablityDTO.getAvailablityStatusId()));
                else
                {existingPanel.setAvailablityStatusId(existingPanel.getAvailablityStatusId());    

                }

 


                PanelAvailabilityEntity save = repo.save(existingPanel);

 

                return null;

 

            }

        }

 

        else {
            throw new UnauthorizedUserException("Invalid User");
        }
    }
	
	/// For Export --> role not done

	@Override
	public List<PanelAvailabilityListDTO> ExportPanelBYFilter(int panelId, String role, String email,
			LocalDate fromDate, LocalDate toDate, String interviewType, String panelName, String availabilityStatus,
			String jwtToken) {

		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())
				|| Constants.TALENT_ACQUISITION.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())) {// only
																														// for
																														// practice
																														// head
			/// if any other access than we have to change

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PanelAvailabilityEntity> criteriaQuery = criteriaBuilder
					.createQuery(PanelAvailabilityEntity.class);
			Root<PanelAvailabilityEntity> rootEntity = criteriaQuery.from(PanelAvailabilityEntity.class);

			List<Predicate> predicates = new ArrayList<>();

			if (panelId != 0)
				predicates.add(criteriaBuilder.equal(rootEntity.get("userEntity").get("id"), panelId));

			if (fromDate != null || toDate != null) {
				if (fromDate != null && toDate != null) {
					if (fromDate.isBefore(toDate) == true)
						predicates.add(criteriaBuilder.between(rootEntity.<LocalDate>get("date"), fromDate, toDate));
					else
						throw new EmptyListException("Invalid Date");
				}

				else if (fromDate != null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootEntity.<LocalDate>get("date"), fromDate));
				}

				else
					predicates.add(criteriaBuilder.lessThanOrEqualTo(rootEntity.<LocalDate>get("date"), toDate));

			}

			if (email != null && !email.isEmpty())
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("userEntity").get("email"),
						"%" + email + "%"));

			if (panelName != null && !panelName.isEmpty())
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("userEntity").get("userName"),
						"%" + panelName + "%"));

			if (role != null && !role.isEmpty() && !role.equals("Select Roles"))
				predicates.add(criteriaBuilder
						.like(rootEntity.get("panelEntity").get("panelCandidateRolesEntity").get("role"), role));

			if (interviewType != null && !interviewType.isEmpty() && !interviewType.equals("Select Interview Type"))
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("interviewType").get("type"),
						"%" + interviewType + "%"));

			if (availabilityStatus != null && !availabilityStatus.isEmpty()
					&& !availabilityStatus.equals("Select Availability Status"))
				predicates.add(
						criteriaBuilder.like(rootEntity.get("panelAvailabilityStatusEntity").get("availablityStatus"),
								"%" + availabilityStatus + "%"));

			criteriaQuery.select(rootEntity)
					.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

			List<PanelAvailabilityEntity> exportpanel = entityManager.createQuery(criteriaQuery).getResultList();

			// criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[]
			// {}));

			// int sizeofdto =
			// entityManager.createQuery(criteriaQuery).getResultList().size();

			if (exportpanel.size() != 0)
				return Convert(exportpanel, exportpanel.size());
			else
				throw new MatchNotFoundException("List is Empty No records found ");
		}

		else
			throw new UnauthorizedUserException("Invalid User");

	}

/// for panel search---->> If panel Logins
	@Override
	public ShowPanelAvailabilityListDTO SearchByPanel(String availabilityStatus, LocalDate fromDate, LocalDate toDate,
			String interviewType, int pageNo, int pageSize, String token) {
		if (Constants.ROLE_PANEL.equalsIgnoreCase(loginDelegate.isTokenValid(token).getRoleName())) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PanelAvailabilityEntity> criteriaQuery = criteriaBuilder
					.createQuery(PanelAvailabilityEntity.class);
			Root<PanelAvailabilityEntity> rootEntity = criteriaQuery.from(PanelAvailabilityEntity.class);

			List<Predicate> predicates = new ArrayList<>();
			String panelName = loginDelegate.isTokenValid(token).getUserName();

			if (panelName != null && !panelName.isEmpty())
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("userEntity").get("userName"),
						"%" + panelName + "%"));

			if (fromDate != null || toDate != null) {
				if (fromDate != null && toDate != null) {
					if (fromDate.isBefore(toDate) == true)
						predicates.add(criteriaBuilder.between(rootEntity.<LocalDate>get("date"), fromDate, toDate));
					else
						throw new EmptyListException("Invalid Date");
				}

				else if (fromDate != null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootEntity.<LocalDate>get("date"), fromDate));
				}

				else
					predicates.add(criteriaBuilder.lessThanOrEqualTo(rootEntity.<LocalDate>get("date"), toDate));

			}
			if (interviewType != null && !interviewType.isEmpty() && !interviewType.equals("Select Interview Type"))
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("interviewType").get("type"),
						"%" + interviewType + "%"));

			if (availabilityStatus != null && !availabilityStatus.isEmpty()
					&& !availabilityStatus.equals("Select Availability Status"))
				predicates.add(
						criteriaBuilder.like(rootEntity.get("panelAvailabilityStatusEntity").get("availablityStatus"),
								"%" + availabilityStatus + "%"));

			criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[] {}));

			int sizeofdto = entityManager.createQuery(criteriaQuery).getResultList().size();

			TypedQuery<PanelAvailabilityEntity> typedQuery = entityManager.createQuery(criteriaQuery);

			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
			List<PanelAvailabilityEntity> searchPanel = typedQuery.getResultList(); // Query is executed

			if (searchPanel.size() != 0)
				return new ShowPanelAvailabilityListDTO(Convert(searchPanel, searchPanel.size()), sizeofdto);
			else
				throw new EmptyListException("List is Empty No records found");
		}

		else
			throw new UnauthorizedUserException("Invalid User");

	}

	/// For Search-->
	@Override
	public ShowPanelAvailabilityListDTO SearchPanelBYFilter(int panelId, String panelName, String email,
			String availabilityStatus, LocalDate fromDate, LocalDate toDate, String role, String interviewType,
			int pageNo, int pageSize, String jwtToken) {

		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName()) ||

				Constants.TALENT_ACQUISITION.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())

		) {

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PanelAvailabilityEntity> criteriaQuery = criteriaBuilder
					.createQuery(PanelAvailabilityEntity.class);
			Root<PanelAvailabilityEntity> rootEntity = criteriaQuery.from(PanelAvailabilityEntity.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (panelId != 0)
				predicates.add(criteriaBuilder.equal(rootEntity.get("userEntity").get("id"), panelId));

			if (fromDate != null || toDate != null) {
				if (fromDate != null && toDate != null) {
					if (fromDate.isBefore(toDate) == true)
						predicates.add(criteriaBuilder.between(rootEntity.<LocalDate>get("date"), fromDate, toDate));
					else
						throw new EmptyListException("Invalid Date");
				}

				else if (fromDate != null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootEntity.<LocalDate>get("date"), fromDate));
				}

				else
					predicates.add(criteriaBuilder.lessThanOrEqualTo(rootEntity.<LocalDate>get("date"), toDate));

			}

			if (email != null && !email.isEmpty()) {
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("userEntity").get("email"),
						"%" + email + "%"));
			}

			if (panelName != null && !panelName.isEmpty())
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("userEntity").get("userName"),
						"%" + panelName + "%"));

			if (role != null && !role.isEmpty() && !role.equals("Select Roles"))
				predicates.add(criteriaBuilder
						.like(rootEntity.get("panelEntity").get("panelCandidateRolesEntity").get("role"), role));

			if (interviewType != null && !interviewType.isEmpty() && !interviewType.equals("Select Interview Type"))
				predicates.add(criteriaBuilder.like(rootEntity.get("panelEntity").get("interviewType").get("type"),
						"%" + interviewType + "%"));

			if (availabilityStatus != null && !availabilityStatus.isEmpty()
					&& !availabilityStatus.equals("Select Availability Status"))
				predicates.add(
						criteriaBuilder.like(rootEntity.get("panelAvailabilityStatusEntity").get("availablityStatus"),
								"%" + availabilityStatus + "%"));

			criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[] {}));

			int sizeofdto = entityManager.createQuery(criteriaQuery).getResultList().size();

			TypedQuery<PanelAvailabilityEntity> typedQuery = entityManager.createQuery(criteriaQuery);

			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
			List<PanelAvailabilityEntity> searchPanel = typedQuery.getResultList(); // Query is executed

			if (searchPanel.size() != 0)
				return new ShowPanelAvailabilityListDTO(Convert(searchPanel, searchPanel.size()), sizeofdto);
			else
				throw new EmptyListException("List is Empty No records found");
		}

		else
			throw new UnauthorizedUserException("Invalid User");

	}

	//// removing unwanted info---> only pass the required info

	public List<PanelAvailabilityListDTO> Convert(List<PanelAvailabilityEntity> entityList, int size) {
		List<PanelAvailabilityListDTO> panelDtoList = new ArrayList<>();

        for (int x = 0; x < size; x++) {
            PanelAvailabilityListDTO panelDto = new PanelAvailabilityListDTO();
            panelDto.setDate(entityList.get(x).getDate());
            panelDto.setPanelId(entityList.get(x).getUserEntity().getUserId());
            panelDto.setContact(entityList.get(x).getPanelId().getContact());
            panelDto.setEmail(entityList.get(x).getPanelId().getUserEntity().getEmail());
            panelDto.setPanelName(entityList.get(x).getPanelId().getUserEntity().getUserName());
            panelDto.setInterviewType(entityList.get(x).getPanelId().getInterviewType().getType());
            panelDto.setSlotTime(entityList.get(x).getStartTime()+"-"+entityList.get(x).getEndTime());
        //// changed
            panelDto.setAvailabilityStatus(entityList.get(x).getAvailablityStatusId().getAvailablityStatus());
            panelDto.setPanelAvailabilityId(entityList.get(x).getId());
            panelDto.setGradeId(entityList.get(x).getPanelId().getGradeEntity().getGrade());
            //panelDto.setRole(entityList.get(x).getPanelId().getRoleType().getRoleName());
            panelDto.setRole(entityList.get(x).getPanelId().getPanelCandidateRolesEntity().getRole());
            panelDto.setFromTime(entityList.get(x).getStartTime());
            panelDto.setToTime(entityList.get(x).getEndTime());
            panelDtoList.add(panelDto);
        }
		return panelDtoList;
	}

	//// panel availability status

	@Override
	public List<PanelAvailabilityStatusDTO> getByAvailabilityStatus() {

		List<PanelAvailabilityStatusEntity> entityList = panelAvailabilityStatusRepo.findAll();
		List<PanelAvailabilityStatusDTO> dtoList = new ArrayList<>();
		for (PanelAvailabilityStatusEntity p : entityList) {
			PanelAvailabilityStatusDTO statusDTO = new PanelAvailabilityStatusDTO();
			statusDTO.setAvailabilityStatus(p.getAvailablityStatus());
			statusDTO.setAvailabilityStatusId(p.getId());
			dtoList.add(statusDTO);
		}
		dtoList.add(new PanelAvailabilityStatusDTO(0, "Select Availability Status"));
		return dtoList;
	}

	/// dynamic dropdown
	@Autowired
	PanelCandidateRolesRepository candidateRoleRepo;

	@Override
	public List<RoleDto> DropDownConvertorRole() {

		List<PanelCandidateRolesEntity> stringList = candidateRoleRepo.findAll();
		List<RoleDto> roleDToList = new ArrayList<>();
		for (PanelCandidateRolesEntity x : stringList) {
			RoleDto roleDto = new RoleDto();

			roleDto.setRoleId(x.getId());
			roleDto.setRoleString(x.getRole());
			roleDToList.add(roleDto);
		}
		roleDToList.add(new RoleDto("Select Roles", 0));

		return roleDToList;
	}

	@Autowired
	InterviewTypeRepository interviewTypeRepo;

	@Override
	public List<InterviewTypeDTO> DropDownConvertorInterviewType() {

		List<InterviewTypesEntity> stringList = interviewTypeRepo.findAll();
		List<InterviewTypeDTO> interviewDToList = new ArrayList<>();
		for (InterviewTypesEntity x : stringList) {
			InterviewTypeDTO roleDto = new InterviewTypeDTO();

			roleDto.setInterviewType(x.getType());
			roleDto.setInterviewID(x.getId());
			interviewDToList.add(roleDto);
		}
		interviewDToList.add(new InterviewTypeDTO("Select Interview Type", 0));

		return interviewDToList;
	}
//////////////////////////////////////Team 10/////////////////////////////////////////

	@Override
	public boolean createPanel(PanelDTO panelDTO, String token) {

		String roleName = loginDelegate.isTokenValid(token).getRoleName();
		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(roleName)) {

			List<PanelEntity> panelList = panelEntityRepository.findByUserId(panelDTO.getPanelId());
			if (panelList.size()==0) {
				PanelEntity panelEntity = new PanelEntity();
				if(!panelEntityRepository.existsByContact(panelDTO.getContact())) {
					panelEntity.setContact(panelDTO.getContact());
				}else {
					throw new ContactAlreadyExistsException("Contact Already Exists");
				}				
				String userName = loginDelegate.isTokenValid(token).getUserName();
				panelEntity.setCreatedBy(userName);
				panelEntity.setCreatedOn(LocalDateTime.now());

				List<UserRolesEntity> findByUserId = userRolesRepository.findByUserId(panelDTO.getPanelId());
				if (findByUserId.size() > 0) {
					UserRolesEntity userRoleEntity = findByUserId.get(0);
					if (userRepository.existsById(panelDTO.getPanelId())) {
						UserEntity userEntity = userRepository.findById(panelDTO.getPanelId()).get();
						setCreateUserEntity(panelDTO, userEntity, userRoleEntity, token);
					} else {
						throw new UserNotFoundException("User Not Found");
					}

				} else {
					UserRolesEntity userRoleEntity = new UserRolesEntity();
					UserEntity userEntity = new UserEntity();
					setCreateUserEntity(panelDTO, userEntity, userRoleEntity, token);
				}

				if (userRepository.existsById(panelDTO.getPanelId())) {
					UserEntity findById = userRepository.findById(panelDTO.getPanelId()).get();
					panelEntity.setUserEntity(findById);
				} else {
					throw new UserNotFoundException("User Not Found");
				}
				if (gradeRepository.existsById(panelDTO.getGradeId())) {
					GradeEntity findByGradeId = gradeRepository.findById(panelDTO.getGradeId()).get();
					panelEntity.setGradeEntity(findByGradeId);
				} else {
					throw new GradeNotFoundException("Grade not found");
				}
				if (interviewTypeRepository.existsById(panelDTO.getInterviewTypeId())) {
					InterviewTypesEntity findByTypeId = interviewTypeRepository.findById(panelDTO.getInterviewTypeId())
							.get();
					panelEntity.setInterviewType(findByTypeId);
				} else {
					throw new InterviewTypeNotFoundException("Interview Type not found");
				}
				if (panelCandidateRolesRepository.existsById(panelDTO.getPanelRoleId())) {
					PanelCandidateRolesEntity findByPanelRoleId = panelCandidateRolesRepository
							.findById(panelDTO.getPanelRoleId()).get();
					panelEntity.setPanelCandidateRolesEntity(findByPanelRoleId);
				} else {
					throw new PanelCandidateRoleNotFoundException("Panel Role not found");
				}
				PanelEntity save = panelEntityRepository.save(panelEntity);

				return true;
			} else {
				throw new PanelAlreadyExists("Panel Already Exists");
			}
		} else {
			return false;
		}

	}

	@Override
	public boolean updatePanel(PanelDTO panelDTO, String token) {
		String roleName = loginDelegate.isTokenValid(token).getRoleName();
		PanelEntity panelEntity = new PanelEntity();
		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(roleName)) {
			List<PanelEntity> list = panelEntityRepository.findByUserId(panelDTO.getPanelId());
			if(list.size()>0) {
				panelEntity = list.get(0);
				if(!panelEntityRepository.existsByIdNotAndContact(panelEntity.getId(),panelDTO.getContact())) {
					panelEntity.setContact(panelDTO.getContact());
				}else {
					throw new ContactAlreadyExistsException("Contact Already Exists");
				}
				String userName = loginDelegate.isTokenValid(token).getUserName();
				panelEntity.setUpdatedBy(userName);
				panelEntity.setUpdatedOn(LocalDateTime.now());
				List<UserRolesEntity> findByUserId = userRolesRepository.findByUserId(panelDTO.getPanelId());
				if (findByUserId.size() == 1) {
					UserRolesEntity userRolesEntity = findByUserId.get(0);
					if (userRepository.existsById(panelDTO.getPanelId())) {
						UserEntity userEntity = userRepository.findById(panelDTO.getPanelId()).get();
						setUpdateUserEntity(panelDTO, userEntity, userRolesEntity, token);
					} else {
						throw new UserNotFoundException("User Not Found");
					}
				}

				if (userRepository.existsById(panelDTO.getPanelId())) {
					UserEntity findById = userRepository.findById(panelDTO.getPanelId()).get();
					panelEntity.setUserEntity(findById);
				} else {
					throw new UserNotFoundException("User Not Found");
				}
				if (gradeRepository.existsById(panelDTO.getGradeId())) {
					GradeEntity findByGradeId = gradeRepository.findById(panelDTO.getGradeId()).get();
					panelEntity.setGradeEntity(findByGradeId);
				} else {
					throw new GradeNotFoundException("Grade not found");
				}
				if (interviewTypeRepository.existsById(panelDTO.getInterviewTypeId())) {
					InterviewTypesEntity findByTypeId = interviewTypeRepository.findById(panelDTO.getInterviewTypeId())
							.get();
					panelEntity.setInterviewType(findByTypeId);
				} else {
					throw new InterviewTypeNotFoundException("Interview Type not found");
				}
				if (panelCandidateRolesRepository.existsById(panelDTO.getPanelRoleId())) {
					PanelCandidateRolesEntity findByPanelRoleId = panelCandidateRolesRepository
							.findById(panelDTO.getPanelRoleId()).get();
					panelEntity.setPanelCandidateRolesEntity(findByPanelRoleId);
				} else {
					throw new PanelCandidateRoleNotFoundException("Panel Role not found");
				}
				PanelEntity save = panelEntityRepository.save(panelEntity);

				return true;
			} else {
				throw new PanelNotFound("Panel not found");
			}
		} else {
			return false;
		}
	}

	public void setCreateUserEntity(PanelDTO panelDTO, UserEntity userEntity, UserRolesEntity userRoleEntity,
			String token) {

		userEntity.setUserId(panelDTO.getPanelId());
		userEntity.setUserName(panelDTO.getPanelName());
		userEntity.setActive(panelDTO.getIsActive());
		userEntity.setCreatedOn(LocalDateTime.now()); // created userEntity
		String userName = loginDelegate.isTokenValid(token).getUserName();
		userEntity.setCreatedBy(userName);
		String common = "@ZEN^";
		String passwordUserName = panelDTO.getPanelName().substring(0, 3);
		int passwordNumber = 100 + (int) (Math.random() * 999);
		common = common + passwordUserName + passwordNumber;
		userEntity.setUserPassword(common);
		if (!userRepository.existsByIdNotAndEmail(panelDTO.getPanelId(), panelDTO.getEmail())) {
			userEntity.setEmail(panelDTO.getEmail());
		} else {
			throw new EmailAlreadyExistException("EMAIL ALREADY EXISTS");
		}
		List<RolesEntity> findByRoleName = rolesRepository.findByRoleName(Constants.ROLE_PANEL);
		RolesEntity rolesEntity = findByRoleName.get(0);
		if (rolesEntity != null) {
			userRoleEntity.setUserEntity(userEntity);
			userRoleEntity.setRolesEntity(rolesEntity);
			userRoleEntity.setActive(userEntity.getIsActive());
			userRoleEntity.setActive(userEntity.getIsActive());
			userRoleEntity.setCreatedBy(userEntity.getCreatedBy());
			userRoleEntity.setCreatedOn(userEntity.getCreatedOn());
			if (userEntity.getIsActive()) {
				userCreatedSuccessfullyMail.userCreatedSuccessfully(userEntity.getUserId(), userEntity.getUserName(),
						userEntity.getEmail(), userEntity.getUserPassword());
			}
			userRolesRepository.save(userRoleEntity);
		} else {
			throw new NoSuchRoleFoundException("No Such Role Found");
		}

	}

	public void setUpdateUserEntity(PanelDTO panelDTO, UserEntity userEntity, UserRolesEntity userRoleEntity,
			String token) {

		userEntity.setUserId(panelDTO.getPanelId());
		userEntity.setUserName(panelDTO.getPanelName());
		userEntity.setActive(panelDTO.getIsActive());
		userEntity.setUpdatedOn(LocalDateTime.now()); // created userEntity
		String userName = loginDelegate.isTokenValid(token).getUserName();
		userEntity.setUpdatedBy(userName);
		if (!userRepository.existsByIdNotAndEmail(panelDTO.getPanelId(), panelDTO.getEmail())) {
			userEntity.setEmail(panelDTO.getEmail());
		} else {
			throw new EmailAlreadyExistException("EMAIL ALREADY EXISTS");
		}
		List<RolesEntity> findByRoleName = rolesRepository.findByRoleName(Constants.ROLE_PANEL);
		RolesEntity rolesEntity = findByRoleName.get(0);
		if (rolesEntity != null) {
			userRoleEntity.setUserEntity(userEntity);
			userRoleEntity.setRolesEntity(rolesEntity);
			userRoleEntity.setActive(userEntity.getIsActive());
			userRoleEntity.setActive(userEntity.getIsActive());
			userRoleEntity.setUpdatedBy(userEntity.getCreatedBy());
			userRoleEntity.setUpdatedOn(userEntity.getCreatedOn());
			userRolesRepository.save(userRoleEntity);

		} else {
			throw new NoSuchRoleFoundException("No Such Role Found");
		}

	}

	@Override
	public List<String> getAllPanelNames() {
		List<PanelEntity> panelEntity = panelEntityRepository.findAll();
		if (!(panelEntity.isEmpty())) {
			List<PanelDTO> dtoList = new ArrayList<>();
			List<String> listPanelNames = new ArrayList<>();
			for (PanelEntity panel : panelEntity) {
				System.out.println(panel);
				UserEntity userEntity = panel.getUserEntity();
				System.out.println(userEntity);
				int panelId = userEntity.getUserId();
				String panelName = userEntity.getUserName();
				GradeEntity gradeEntity = panel.getGradeEntity();
				String grade = gradeEntity.getGrade();
				PanelDTO dto = new PanelDTO(panelId, panelName, panel.getContact(), grade);
				dtoList.add(dto);
				listPanelNames.add(panelName);
			}
			return listPanelNames;
		}

		return null;
	}

}
