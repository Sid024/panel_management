package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.zensar.pm.panel.entity.InterviewType;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelCandidateRolesEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;
import com.zensar.pm.panel.entity.UserRolesEntity;
import com.zensar.pm.panel.enums.Constants;
import com.zensar.pm.panel.exceptions.CustomNullPointerException;
import com.zensar.pm.panel.exceptions.EmptyListException;
import com.zensar.pm.panel.exceptions.InvalidPanelException;
import com.zensar.pm.panel.exceptions.UnauthorizedUserException;
import com.zensar.pm.panel.repository.CandidateRoleRepository;
import com.zensar.pm.panel.repository.InterviewTypeRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityStatusRepository;
import com.zensar.pm.panel.repository.RolesRepository;
import com.zensar.pm.panel.repository.UserRepository;
import com.zensar.pm.panel.repository.UserRolesRepository;

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

	/// Update --> table need to change--> not done

	@Override
	public PanelAvailabilityDTO updatePanelAvailability(Integer panelAvailablityId,
			PanelAvailabilityDTO panelAvailablityDTO, String jwtToken) {

		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())
				|| Constants.TALENT_ACQUISITION.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())
				) {
			PanelAvailabilityEntity existingPanel = repo.findById(panelAvailablityId).orElse(null);
			PanelAvailabilityStatusEntity availabilityEntity = panelAvailabilityStatusRepo
					.findById(panelAvailablityDTO.getAvailablityStatusId()).get();
			if (existingPanel == null)
				throw new InvalidPanelException("Panel not found");
			else if (panelAvailablityDTO.getStartTime() == null || panelAvailablityDTO.getEndTime() == null)
				throw new CustomNullPointerException("Empty value! enter the value");

			else {
				existingPanel.setUpdatedBy(loginDelegate.isTokenValid(jwtToken).getUserName());
				existingPanel.setUpdatedOn(LocalDateTime.now());
				existingPanel.setStartTime(panelAvailablityDTO.getStartTime());
				existingPanel.setEndTime(panelAvailablityDTO.getEndTime());
				existingPanel.setPanelAvailablityStatusEntity(availabilityEntity);

				// existingPanel.setPanesAvail(panelsAvailabilityDTO.getPanelsAvailabilityStatus());

				PanelAvailabilityEntity save = repo.save(existingPanel);

				PanelAvailabilityDTO panelAvaialablityDTO = modelMapper.map(save, PanelAvailabilityDTO.class);
				panelAvaialablityDTO.setPanelAvailablityId(availabilityEntity.getAvailablityStatusId());
				;
				return panelAvaialablityDTO;
			}

		}

		else {
			throw new UnauthorizedUserException("Invalid User");
		}
	}

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
			String type, boolean isActive, String token) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PanelEntity> criteriaQuery = criteriaBuilder.createQuery(PanelEntity.class);
		if (loginDelegate.isTokenValid(token).getRoleName().equals("PANEL")
				|| loginDelegate.isTokenValid(token).getRoleName().equals("TA")) {
			throw new InvalidPanelException("Panel does not exist");
		}

		PanelDTO allPanelDto = getAllPanel();
		List<Integer> listPanelId = allPanelDto.getListPanelId();
		Root<PanelEntity> rootEntity = criteriaQuery.from(PanelEntity.class);

		Predicate predicateId = criteriaBuilder.and();
		Predicate predicateName = criteriaBuilder.and();
		Predicate predicateEmail = criteriaBuilder.and();
		Predicate predicateGrade = criteriaBuilder.and();
		Predicate predicateRole = criteriaBuilder.and();
		Predicate predicateType = criteriaBuilder.and();
		Predicate predicateIsActive = criteriaBuilder.and();

		if (panelId != 0) {
			if (listPanelId.contains(panelId)) {
				predicateId = criteriaBuilder.equal(rootEntity.get("userEntity").get("userId"), panelId);
			}
			else {
				throw new InvalidPanelException("The User is not a Panel");
			}
		}
		if (panelName != null && !"".equals(panelName)) {
			predicateName = criteriaBuilder.equal(rootEntity.get("userEntity").get("userName"), panelName);
		}
		if (email != null && !"".equals(email)) {
			predicateEmail = criteriaBuilder.equal(rootEntity.get("userEntity").get("email"), email);
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
			Predicate predicateActive = criteriaBuilder.equal(rootEntity.get("userEntity").get("isActive"), isActive);
		}
		Predicate finalPredicate = criteriaBuilder.and(predicateId, predicateName, predicateEmail, predicateGrade,
				predicateRole, predicateType, predicateIsActive);
		criteriaQuery.where(finalPredicate);

		TypedQuery<PanelEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(10);
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

	/// For Export --> role not done

	@Override 
	public List<PanelAvailabilityListDTO> ExportPanelBYFilter(int panelId, String role, String email,
			LocalDate fromDate, LocalDate toDate, String interviewType, String panelName,
			String availabilityStatus,String jwtToken) {
		
		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())||
				Constants.TALENT_ACQUISITION.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())) {// only for practice head 
			                                                  /// if any other access than we have to change

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PanelAvailabilityEntity> criteriaQuery = criteriaBuilder
				.createQuery(PanelAvailabilityEntity.class);
		Root<PanelAvailabilityEntity> rootEntity = criteriaQuery.from(PanelAvailabilityEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		
		if (panelId != 0)
			predicates.add(criteriaBuilder.equal(rootEntity.get("panelId"), panelId));
	     
			if(fromDate!=null || toDate!=null)
		 {      if (fromDate != null && toDate != null) 
				{ if (fromDate.isBefore(toDate) == true) 
				  predicates.add(criteriaBuilder.between(rootEntity.<LocalDate>get("date"), fromDate, toDate));
				  else 
				  throw new EmptyListException("Invalid Date");
				}
			  
			  else if (fromDate!=null) 
			  {   predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootEntity.<LocalDate>get("date"), fromDate));}
			    
			  else
				  predicates.add(criteriaBuilder.lessThanOrEqualTo(rootEntity.<LocalDate>get("date"), toDate));
			  
		  }

			
			
			if (email!=null && !email.isEmpty())
				predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("userEntity").get("email"),"%"+email+"%"));

			if (panelName!=null && !panelName.isEmpty())
				predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("userEntity").get("userName"),"%" + panelName + "%"));


			if (role!=null && !role.isEmpty() &&  !role.equals("Select Roles"))
				predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("panelCandidateRolesEntity").get("role"),role));	

			if (interviewType!=null && !interviewType.isEmpty() && !interviewType.equals("Select Interview Type"))
				predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("interviewType").get("type"),"%"+interviewType+"%"));
	       
			if(availabilityStatus!=null && !availabilityStatus.isEmpty() && !availabilityStatus.equals("Select Availability Status"))
				predicates.add(criteriaBuilder.like(rootEntity.get("availablityStatusId").get("availablityStatus"),"%"+availabilityStatus+"%"));
				
		criteriaQuery.select(rootEntity)
				.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

		List<PanelAvailabilityEntity> exportpanel = entityManager.createQuery(criteriaQuery).getResultList();
        
	
		
		//criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[] {}));

		//int sizeofdto = entityManager.createQuery(criteriaQuery).getResultList().size();
		
		
		if(exportpanel.size()!=0)
		return Convert(exportpanel, exportpanel.size());
		else
		 throw new EmptyListException("List is Empty No records found ");
		}

	 else 
	  throw new UnauthorizedUserException("Invalid User");

	}

	
	

	/// For Search--> role not done
	@Override
	public ShowPanelAvailabilityListDTO SearchPanelBYFilter(int panelId, String panelName, String email,
			String availabilityStatus, LocalDate fromDate, LocalDate toDate, String role, String interviewType,
			int pageNo, int pageSize,String jwtToken) {
		
	if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName()) ||

				Constants.TALENT_ACQUISITION.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())
				
				) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PanelAvailabilityEntity> criteriaQuery = criteriaBuilder
				.createQuery(PanelAvailabilityEntity.class);
		Root<PanelAvailabilityEntity> rootEntity = criteriaQuery.from(PanelAvailabilityEntity.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (panelId != 0)
			predicates.add(criteriaBuilder.equal(rootEntity.get("panelId"), panelId));
     
		if(fromDate!=null || toDate!=null)
	 {      if (fromDate != null && toDate != null) 
			{ if (fromDate.isBefore(toDate) == true) 
			  predicates.add(criteriaBuilder.between(rootEntity.<LocalDate>get("date"), fromDate, toDate));
			  else 
			  throw new EmptyListException("Invalid Date");
			}
		  
		  else if (fromDate!=null) 
		  {   predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootEntity.<LocalDate>get("date"), fromDate));}
		    
		  else
			  predicates.add(criteriaBuilder.lessThanOrEqualTo(rootEntity.<LocalDate>get("date"), toDate));
		  
	  }
		
				
		if (email!=null && !email.isEmpty())
			{predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("userEntity").get("email"),"%"+email+"%"));}

		if (panelName!=null && !panelName.isEmpty())
			predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("userEntity").get("userName"),"%"+panelName+"%"));

		if (role!=null && !role.isEmpty() &&  !role.equals("Select Roles"))
			predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("panelCandidateRolesEntity").get("role"),role));	

		if (interviewType!=null && !interviewType.isEmpty() && !interviewType.equals("Select Interview Type"))
			predicates.add(criteriaBuilder.like(rootEntity.get("panelId").get("interviewType").get("type"),"%"+interviewType+"%"));
       
		if(availabilityStatus!=null && !availabilityStatus.isEmpty() && !availabilityStatus.equals("Select Availability Status"))
			predicates.add(criteriaBuilder.like(rootEntity.get("availablityStatusId").get("availablityStatus"),"%"+availabilityStatus+"%"));
					
		
		criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[] {}));

		int sizeofdto = entityManager.createQuery(criteriaQuery).getResultList().size();

		TypedQuery<PanelAvailabilityEntity> typedQuery = entityManager.createQuery(criteriaQuery);

		typedQuery.setFirstResult(pageNo * pageSize);
		typedQuery.setMaxResults(pageSize);
		List<PanelAvailabilityEntity> searchPanel = typedQuery.getResultList(); // Query is executed
		
		
		
		if(searchPanel.size()!=0)
		return new ShowPanelAvailabilityListDTO (Convert(searchPanel,searchPanel.size()),sizeofdto);
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
			panelDto.setPanelId(entityList.get(x).getPanelId().getId());
			panelDto.setContact(entityList.get(x).getPanelId().getContact());
			panelDto.setEmail(entityList.get(x).getPanelId().getUserEntity().getEmail());
			panelDto.setPanelName(entityList.get(x).getPanelId().getUserEntity().getUserName());
			panelDto.setInterviewType(entityList.get(x).getPanelId().getInterviewType().getType());
			panelDto.setSlotTime(entityList.get(x).getStartTime()+"-"+entityList.get(x).getEndTime());
			panelDto.setAvailabilityStatus(entityList.get(x).getPanelAvailablityStatusEntity().getAvailablityStatus());
	        panelDto.setPanelAvailabilityId(entityList.get(x).getPanelAvailablityId());
	        panelDto.setGradeId(entityList.get(x).getPanelId().getGradeEntity().getGrade());
	       // panelDto.setRole(entityList.get(x).getPanelId().getRoleType().getRoleName());
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
			statusDTO.setAvailabilityStatusId(p.getAvailablityStatusId());
			dtoList.add(statusDTO);
		}
        dtoList.add(new PanelAvailabilityStatusDTO(0,"Select Availability Status"));
		return dtoList;
	}

	/// dynamic dropdown
    @Autowired
    CandidateRoleRepository candidateRoleRepo;
    @Override
    public List<RoleDto> DropDownConvertorRole()
    {
 

        List<PanelCandidateRolesEntity> stringList = candidateRoleRepo.findAll();
        List<RoleDto> roleDToList = new ArrayList<>(); 
        for(PanelCandidateRolesEntity x : stringList)
        {
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
public List<InterviewTypeDTO> DropDownConvertorInterviewType()
{


    List<InterviewType> stringList = interviewTypeRepo.findAll();
    List<InterviewTypeDTO> interviewDToList = new ArrayList<>(); 
    for(InterviewType x : stringList)
    {
    	InterviewTypeDTO roleDto = new InterviewTypeDTO(); 
                   
        roleDto.setInterviewType(x.getType());
        roleDto.setInterviewID(x.getTypeId());
        interviewDToList.add(roleDto);
    }
interviewDToList.add(new InterviewTypeDTO("Select Interview Type",0));

return interviewDToList;
}


}
