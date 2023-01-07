package com.zensar.pm.panel.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityPHDTO;
import com.zensar.pm.panel.dto.PanelAvailabilitySelfDTO;
import com.zensar.pm.panel.dto.PanelsGetAllDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;
import com.zensar.pm.panel.dto.UserDTO;
import com.zensar.pm.panel.entity.GradeEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;
import com.zensar.pm.panel.enums.Constants;
import com.zensar.pm.panel.exceptions.DateAlreadyExistsException;
import com.zensar.pm.panel.exceptions.DateRangeException;
import com.zensar.pm.panel.exceptions.DuplicateStatusException;
import com.zensar.pm.panel.exceptions.TimeFormatException;
import com.zensar.pm.panel.exceptions.UnauthorizedUserException;
import com.zensar.pm.panel.repository.PanelAvailabilityRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityStatusRepository;
import com.zensar.pm.panel.repository.PanelEntityRepository;
import com.zensar.pm.panel.repository.UserRepository;
import com.zensar.pm.panel.repository.UserRolesRepository;
import com.zensar.pm.panel.utils.PanelAvailConstants;

@Service
public class PanelAvailabilityServiceImpl implements PanelAvailabilityService {

	@Autowired
	PanelAvailabilityRepository repository;

	@Autowired
	PanelEntityRepository panelRepo;

	@Autowired
	UserRolesRepository userRolesRepository;

	@Autowired
	PanelAvailabilityStatusRepository panelStatusRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	LoginDelegate loginDelegate;

	@Autowired
	EntityManager entityManager;

	@Override
	public PanelAvailabilityDTO addPanelAvailablitySingle(PanelAvailabilityDTO dto) {

		UserEntity userEntity = userRepo.findById(dto.getPanelId().getId()).get();
		PanelEntity panelEntity = panelRepo.findByUserEntity(userEntity);
		boolean panel = repository.existsByPanelEntity(panelEntity);
		boolean startTime = repository.existsByStartTime(dto.getStartTime());
		LocalDate date = dto.getDate();
		boolean existDate = repository.existsByDate(date);
		if (panel && startTime && existDate) {
			throw new DuplicateStatusException("You are already booked");
		}

		PanelAvailabilityStatusEntity statusEntity = panelStatusRepo.findById(1);
		PanelAvailabilityEntity entity = new PanelAvailabilityEntity();


		entity.setStartTime(dto.getStartTime());
		entity.setEndTime(dto.getEndTime());
		entity.setDate(date);
		entity.setPanelId(panelEntity);
		entity.setAvailablityStatusId(statusEntity);
		repository.save(entity);

		return dto;
	}

	@Override
	public PanelsGetAllResponseDTO getLoginRole(String token) {
		UserDTO dto2 = loginDelegate.isTokenValid(token);
		String roleName = dto2.getRoleName();

		if (roleName.equals("Practice Head")) {
			List<PanelsGetAllDTO> emptyPanel = new ArrayList<PanelsGetAllDTO>();
			PanelsGetAllResponseDTO dto = new PanelsGetAllResponseDTO();
			dto.setList(emptyPanel);
			dto.setRoleName(roleName);
			return dto;

		} else if (roleName.equals("PANEL")) {
			UserEntity userEntity = userRepo.findByUserName(dto2.getUserName()).get(0);
			String name = userEntity.getUserName();
			int id = userEntity.getUserId();
			PanelEntity entity = panelRepo.findByUserEntity(userEntity);
			String grade = entity.getGradeEntity().getGrade();

			PanelsGetAllDTO returnDTO = new PanelsGetAllDTO();
			returnDTO.setPanelId(id);
			returnDTO.setPanelName(name);
			returnDTO.setPanelGrade(grade);

			List<PanelsGetAllDTO> list = Arrays.asList(returnDTO);

			PanelsGetAllResponseDTO dto = new PanelsGetAllResponseDTO();
			dto.setList(list);
			dto.setRoleName(roleName);
			return dto;

		}
		throw new UnauthorizedUserException("Invalid User");
	}

	@Override
	public PanelsGetAllResponseDTO filterByPanelName(String panelName) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PanelEntity> criteriaQuery = criteriaBuilder.createQuery(PanelEntity.class);
		Root<PanelEntity> rootEntity = criteriaQuery.from(PanelEntity.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (panelName != null && !"".equals(panelName)) {

			Predicate predicateroleName = criteriaBuilder.like(rootEntity.get("userEntity").get("userName"),
					("%" + panelName + "%"));
			criteriaQuery.where(predicateroleName);
			predicates.add(predicateroleName);
		}
		criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<PanelEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		List<PanelEntity> panelEntity = typedQuery.getResultList();
		if (!(panelEntity.isEmpty())) {
			List<PanelsGetAllDTO> dtoList = new ArrayList<>();
			for (PanelEntity panel : panelEntity) {
				PanelsGetAllDTO dto = new PanelsGetAllDTO();
				dto.setPanelId(panel.getUserEntity().getUserId());
				UserEntity userEntity = userRepo.findById(panel.getUserEntity().getUserId()).get();
				dto.setPanelName(userEntity.getUserName());
				PanelEntity pan = panelRepo.findById(panel.getId()).get();
				dto.setPanelGrade(pan.getGradeEntity().getGrade());

				dtoList.add(dto);
			}
			PanelsGetAllResponseDTO responseDTO = new PanelsGetAllResponseDTO();
			responseDTO.setList(dtoList);
			return responseDTO;
		}
		throw new DuplicateStatusException();
	}

//	@Override
//	public String post(PanelAvailDTO pa) {
//		UserDTO dto2 = loginDelegate.isTokenValid(token);
//		String roleName = dto2.getRoleName();
//
//		if (roleName.equals("Practice Head")) {
//			List<PanelsGetAllDTO> emptyPanel = new ArrayList<PanelsGetAllDTO>();
//			PanelsGetAllResponseDTO dto = new PanelsGetAllResponseDTO();
//			dto.setList(emptyPanel);
//			dto.setRoleName(roleName);
//			return dto;
//
//		} else if (roleName.equals("PANEL")) {
//			UserEntity userEntity = userRepo.findByUserName(dto2.getUserName()).get(0);
//			String name = userEntity.getUserName();
//			int id = userEntity.getUserId();
//			PanelEntity entity = panelRepo.findByUserEntity(userEntity);
//			String grade = entity.getGradeEntity().getGrade();
//
//			PanelsGetAllDTO returnDTO = new PanelsGetAllDTO();
//			returnDTO.setPanelId(id);
//			returnDTO.setPanelName(name);
//			returnDTO.setPanelGrade(grade);
//
//			List<PanelsGetAllDTO> list = Arrays.asList(returnDTO);
//
//			PanelsGetAllResponseDTO dto = new PanelsGetAllResponseDTO();
//			dto.setList(list);
//			dto.setRoleName(roleName);
//			return dto;
//
//		}
//		throw new UnauthorizedUserException("Invalid User");
//	}

	@Override
	public String post(PanelAvailDTO pa) {
		int panelId = pa.getPanelId(); // userId
		PanelEntity panelsEntity = panelRepo.getPanelsDetails(panelId);
//        System.out.println("---------------------------"+panelsEntity);
		PanelAvailabilityStatusEntity panelAvailabilityStatusEntity = panelStatusRepo.findById(1);
		System.out.println("----------------------" + panelAvailabilityStatusEntity);
		System.out.println(panelsEntity);
		int id = panelsEntity.getId();
		System.out.println(id);
//        PanelEntity panelsEntity = panelRepo.findById(panelId).get();
//        int id = panelsEntity.getId();
		LocalDate fromDate = pa.getFromDate();
		LocalDate endDate = pa.getToDate();
		endDate = endDate.plusDays(1);
		long numOfDays = ChronoUnit.DAYS.between(fromDate, endDate);
		List<LocalDate> collect = Stream.iterate(fromDate, date -> date.plusDays(1)).limit(numOfDays)
				.collect(Collectors.toList());
		LocalTime startTime = pa.getStartTime();
		LocalTime endTime = pa.getEndTime();
		LocalDate date1 = pa.getFromDate();
		List<Date> datesWithId = repository.getDatesWithId(id);
		Set<Date> set = new LinkedHashSet<>();
		set.addAll(datesWithId);
		datesWithId.clear();
		datesWithId.addAll(set);
		List<LocalDate> finalDays = new ArrayList<>();
		List<LocalDate> result = new ArrayList<>();
		List<LocalDate> submittedDates = new ArrayList<>();
		for (int i = 0; i < datesWithId.size(); i++) {
			String strDate = datesWithId.get(i).toString();
			LocalDate parse = LocalDate.parse(strDate);
			finalDays.add(parse);
		}
//        //List<Time> timeWithId = repository.getTimeWithId(id);
//        Set<Time> timeSet = new LinkedHashSet<>();
//        timeSet.addAll(timeWithId);
//        timeWithId.clear();
//        timeWithId.addAll(timeSet);
		while (fromDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
			fromDate = fromDate.plusDays(1);
		}
		LocalDate date2 = fromDate;
		List<String> listOfDays = pa.getListOfDays();
		if ((pa.getToDate().isAfter(date1) || pa.getToDate().isEqual(date1))
				&& (pa.getToDate().isBefore(date2) || pa.getToDate().isEqual(date2))) {
			String patt = "[0-9]{2}[:]{1}(00||30){1}";
			String[] split1 = pa.getStartTime().toString().split(":");
			String[] split2 = pa.getEndTime().toString().split(":");
			if (pa.getStartTime().toString().matches(patt) && pa.getEndTime().toString().matches(patt)
					&& Integer.parseInt(split1[0]) < Integer.parseInt(split2[0])
					&& Integer.parseInt(split1[1]) == Integer.parseInt(split2[1])) {
				for (int i = 0; i < collect.size(); i++) {
					while (startTime.getHour() < endTime.getHour()
							&& listOfDays.contains(collect.get(i).getDayOfWeek().toString())) {
						if (finalDays.contains(collect.get(i))) {
							result.add(collect.get(i));
							// throw new DateAlreadyExistsException("Enter another date");
							break;

						} else {
							repository.save(new PanelAvailabilityEntity(panelsEntity, collect.get(i),
									panelAvailabilityStatusEntity, pa.getCreatedBy(), pa.getCreatedOn().atStartOfDay(),
									pa.getUpdatedOn().atStartOfDay(), pa.getUpdatedBy(), pa.isDeleted(), pa.getDeletedBy(),
									pa.getDeletedOn().atStartOfDay(), startTime, startTime.plusHours(1)));
							submittedDates.add(collect.get(i));
							startTime = startTime.plusHours(1);
						}
					}
					startTime = pa.getStartTime();
				}
			} else
				throw new TimeFormatException("Time should be in correct format");
		}
//        else if(result.size()>0)
//        {
//            throw new DateAlreadyExistsException("for dates"+result.toString().replace("[", "").replace("[", "")+" slots has been alloted enter another date/dates");
//        }
		else {
			throw new DateRangeException("Date should be in that week");
		}
		if (result.size() > 0) {
			Set<LocalDate> set1 = new LinkedHashSet<>();
			set1.addAll(submittedDates);
			submittedDates.clear();
			submittedDates.addAll(set1);
			if (submittedDates.size() == 0) {
				throw new DateAlreadyExistsException("for dates " + result.toString().replace("[", "").replace("]", "")
						+ " slots has been alloted enter another date/dates ");
			} else {
				throw new DateAlreadyExistsException("for dates " + result.toString().replace("[", "").replace("]", "")
						+ " slots has been alloted enter another date/dates and "
						+ submittedDates.toString().replace("[", "").replace("]", "")
						+ " has been submitted successfully");
			}
		}
		return "Submitted Successfully";

	}

	@Override
	public List<PanelAvailDTO> getPanelByFilterCriteria(String panelName, String token) {
		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(token).getRoleName())) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PanelAvailabilityEntity> criteriaQuery = criteriaBuilder
					.createQuery(PanelAvailabilityEntity.class);
			Root<PanelAvailabilityEntity> root = criteriaQuery.from(PanelAvailabilityEntity.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (panelName != null && panelName.length() >= 3 && !"".equals(panelName)) {
				Predicate predicateLevelId = criteriaBuilder
						.like(root.get("panelsEntity").get("userEntity").get("userName"), '%' + panelName + '%');
				predicates.add(predicateLevelId);
			}
			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
			TypedQuery<PanelAvailabilityEntity> typedQuery = entityManager.createQuery(criteriaQuery);
			List<PanelAvailabilityEntity> panelAvailList = typedQuery.getResultList();

			List<PanelAvailDTO> panelsDtoList = PanelAvailConstants
					.convertPanelAvailEntityListIntoPanelAvailDtoList(panelAvailList);
			return panelsDtoList;
		} else {
			throw new UnauthorizedUserException("User must be Practice Head");
		}
	}

	@Override
	public PanelAvailDTO create(PanelAvailDTO pa, String token) {
//		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(token).getRoleName())) {
//			int availabilityStatusId = pa.getAvailabilityStatusId();
//			PanelAvailabilityStatusEntity panelAvailabilityStatusEntity = panelStatusRepo
//					.findById(availabilityStatusId);
//			int panelId = pa.getPanelId();
//			PanelEntity panelsEntity = panelRepo.findById(panelId).get();
//			PanelAvailabilityEntity panelAvailEntity = new PanelAvailabilityEntity(pa.getPanelsAvailabilityId(),
//					panelsEntity, pa.getDate(), pa.getStartTime(), pa.getEndTime(), panelAvailabilityStatusEntity,
//					pa.getCreatedBy(), pa.getCreatedOn(), pa.getUpdatedBy(), pa.getUpdatedOn(), pa.getDeletedBy(),
//					pa.getDeletedOn(), pa.isDeleted());
//			PanelAvailabilityEntity newPanelAvailEntity = repository.save(panelAvailEntity);
//			PanelAvailDTO newPanelAvailDto = PanelAvailConstants
//					.convertPanelAvailEntityIntoPanelAvailDto(newPanelAvailEntity);
//			return newPanelAvailDto;
//		} else {
//			throw new UnauthorizedUserException("User must be Practice Head");
//		}
		return null;
	}

	@Override
	public PanelAvailabilityPHDTO getPanelDetails(String panelName, String token) {
		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(token).getRoleName())) {
			UserEntity userEntity = userRepo.getUserDetails(panelName);
			int userId = userEntity.getUserId();
			PanelEntity panelsEntity = panelRepo.getPanelsDetails(userId);
//			int panelId = panelsEntity.getId();
			GradeEntity gradeEntity = panelsEntity.getGradeEntity();
			String grade = gradeEntity.getGrade();
			PanelAvailabilityPHDTO panelAvailabilityPHDTO = new PanelAvailabilityPHDTO(userId, grade);
			return panelAvailabilityPHDTO;
		} else {
			throw new UnauthorizedUserException("User must be Practice Head");
		}
	}

	@Override
	public PanelAvailabilitySelfDTO getLoginRoleByPanelAvail(String token) {
		UserDTO dto2 = loginDelegate.isTokenValid(token);
		System.out.println(dto2);
		String roleName = dto2.getRoleName();

		if (roleName.equals("PANEL")) {
			UserEntity userEntity = userRepo.getUserDetails(dto2.getUserName());
			int userId = userEntity.getUserId();
			System.out.println(userId);
			PanelEntity panelsEntity = panelRepo.getPanelsDetails(userId);
			String userName = userEntity.getUserName();
			System.out.println(panelsEntity);
			GradeEntity gradeEntity = panelsEntity.getGradeEntity();
			String grade = gradeEntity.getGrade();
			System.out.println(grade);
			return new PanelAvailabilitySelfDTO(userId, userName, grade);

		} else
			throw new UnauthorizedUserException("User must belongs to panel");
	}

}
