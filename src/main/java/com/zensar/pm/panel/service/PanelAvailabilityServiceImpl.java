package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.dto.PanelsGetAllDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;
import com.zensar.pm.panel.dto.UserDTO;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;
import com.zensar.pm.panel.entity.UserRoleEntity;
import com.zensar.pm.panel.exceptions.DuplicateStatusException;
import com.zensar.pm.panel.exceptions.UnauthorizedUserException;
import com.zensar.pm.panel.repository.PanelAvailabilityRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityStatusRepository;
import com.zensar.pm.panel.repository.PanelEntityRepository;
import com.zensar.pm.panel.repository.UserRepository;
import com.zensar.pm.panel.repository.UserRolesRepository;

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

		PanelAvailabilityEntity entity = modelMapper.map(dto, PanelAvailabilityEntity.class);
		boolean panel = repository.existsByPanelId(entity.getPanelId());
		boolean startTime = repository.existsByStartTime(entity.getStartTime());
		LocalDate date = dto.getDate();
		boolean existDate = repository.existsByDate(date);
		if (panel && startTime && existDate) {
			throw new DuplicateStatusException("You are already booked");
		}
		PanelEntity panelEntity = panelRepo.findById(entity.getPanelId().getId()).get();
		PanelAvailabilityStatusEntity statusEntity = panelStatusRepo.findById(1).get();

		int idAll = repository.findAll().size();
		int id = repository.findById(idAll + 1).get().getPanelAvailablityId();
		entity.setPanelAvailablityId(id + 1);
		entity.setPanelId(panelEntity);
		entity.setPanelAvailablityStatusEntity(statusEntity);
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
			UserEntity userEntity = userRepo.findByUserName(dto2.getUserName());
			String name = userEntity.getUserName();
			int id = userEntity.getUserId();
			String grade = panelRepo.findById(id).get().getGradeEntity().getGrade();

			PanelsGetAllDTO returnDTO = new PanelsGetAllDTO();
			returnDTO.setPanelGrade(grade);
			returnDTO.setPanelId(id);
			returnDTO.setPanelName(name);

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
				dto.setPanelId(panel.getId());
				UserEntity userEntity = userRepo.findById(panel.getId()).get();
				dto.setPanelName(userEntity.getUserName());
				PanelEntity pan = panelRepo.findById(panel.getId()).get();
				dto.setPanelGrade(pan.getGradeEntity().getGrade());

				dtoList.add(dto);
			}
			PanelsGetAllResponseDTO responseDTO = new PanelsGetAllResponseDTO();
			responseDTO.setList(dtoList);
			return responseDTO;
		}
		return null;
	}

	

}
