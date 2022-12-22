package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
//import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelListDTO;
import com.zensar.pm.panel.entity.PanelList;
import com.zensar.pm.panel.exception.InvalidDataException;
import com.zensar.pm.panel.exception.UnAuthorizedUserException;
import com.zensar.pm.panel.exception.WrongDateException;

import com.zensar.pm.panel.repository.PanelRepository;
import com.zensar.pm.panel.enums.Constants;

@Service
public class ServiceImpl implements ServiceInterface {

	@Autowired
	PanelRepository panelRepository;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	LoginDelegateImpl loginDelegate;

	public ServiceImpl(PanelRepository panelrepository, ModelMapper modelMapper) {
		super();
		panelRepository = panelrepository;
		this.modelMapper = modelMapper;
	}

	public PanelRepository getEmployeeRepo() {
		return panelRepository;
	}

	public void setEmployeeRepo(PanelRepository employeeRepo) {
		panelRepository = employeeRepo;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

///// FOR EXPORT  OR DOWNLOAD
	@Override //// Here in this function we are not using crud repository
	public List<PanelListDTO> filter(String panel_id, String panel_role, String panel_email, LocalDate start_date,
			LocalDate end_date, String interview_type, String panel_name, String Availability_status, String jwtToken) {

		if (Constants.ROLE_PRACTICE_HEAD.equals(loginDelegate.isTokenValid(jwtToken).getRoleEntity())) {

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PanelList> criteriaQuery = criteriaBuilder.createQuery(PanelList.class);
			Root<PanelList> rootEntity = criteriaQuery.from(PanelList.class);

			List<Predicate> searchcriteria = new ArrayList<>();

			if (panel_name != null && panel_name != "")
				searchcriteria.add(criteriaBuilder.like(rootEntity.get("panel_name"), "%" + panel_name + "%"));
			if (panel_id != null && panel_name != "")
				searchcriteria.add(criteriaBuilder.like(rootEntity.get("panel_id"), panel_id));
			if (panel_role != null && panel_role != "")
				searchcriteria.add(criteriaBuilder.like(rootEntity.get("panel_role"), panel_role));// panel_role name
																									// must be same in
																									// databse
			if (interview_type != null && interview_type != "")
				searchcriteria.add(criteriaBuilder.like(rootEntity.get("interview_type"), interview_type));
			if (panel_email != null && panel_email != "")
				searchcriteria.add(criteriaBuilder.like(rootEntity.get("panel_email"), panel_email));
			if (Availability_status != null && Availability_status != "")
				searchcriteria.add(criteriaBuilder.like(rootEntity.get("availability_status"), Availability_status));
			// should be entity name

			if (start_date != null && end_date != null) {
				if (end_date.isAfter(start_date) == true) {
					searchcriteria.add(criteriaBuilder.between(rootEntity.get("date"), start_date, end_date));
				} // checking date in between start date and end date
				else if (end_date.isAfter(start_date) == false)
					throw new WrongDateException("From Date should be before To Date");
			}

			if (panel_email == null && panel_id == null && panel_name == null && panel_role == null
					&& interview_type == null && Availability_status == null && start_date == null
					&& end_date == null) {
				List<PanelList> p = panelRepository.findAll();
				return p.stream().map((dto) -> modelMapper.map(dto, PanelListDTO.class)).collect(Collectors.toList());
			}

			criteriaQuery.select(rootEntity)
					.where(criteriaBuilder.and(searchcriteria.toArray(new Predicate[searchcriteria.size()])));

			List<PanelList> p = entityManager.createQuery(criteriaQuery).getResultList();

			if (p.size() == 0) {
				throw new InvalidDataException("Invalid data");
			}

			else {
				return p.stream().map((dto) -> modelMapper.map(dto, PanelListDTO.class)).collect(Collectors.toList());
			}
		}

		else {
			throw new UnAuthorizedUserException("Invaild User");
		}
	}

////// FOR SEARCH	

	@Override
	public List<PanelListDTO> searchFilter(String panel_id, String panel_role, String panel_email, LocalDate start_date,
			LocalDate end_date, String interview_type, String panel_name, String Availability_status, int pageNo,
			int pageSize, String jwtToken) {

		if (Constants.ROLE_PRACTICE_HEAD.equals(loginDelegate.isTokenValid(jwtToken).getRoleEntity())) {

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PanelList> criteriaQuery = criteriaBuilder.createQuery(PanelList.class);

			Root<PanelList> rootEntity = criteriaQuery.from(PanelList.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (panel_id != null && !"".equals(panel_id)) {
				Predicate predicateUserId = criteriaBuilder.equal(rootEntity.get("panel_id"), panel_id);
				predicates.add(predicateUserId);
			}

			if (panel_name != null && !"".equals(panel_name)) {
				Predicate predicateName = criteriaBuilder.equal(rootEntity.get("panel_name"), panel_name);
				predicates.add(predicateName);

			}
			if (panel_email != null && !"".equals(panel_email)) {
				Predicate predicateEmail = criteriaBuilder.equal(rootEntity.get("panel_email"), panel_email);
				predicates.add(predicateEmail);
			}
			if (Availability_status != null && !"".equals(Availability_status)) {
				Predicate predicateUserRole = criteriaBuilder.equal(rootEntity.get("availability_status"),
						Availability_status);
				predicates.add(predicateUserRole);
			}
			if (panel_role != null && !"".equals(panel_role)) {
				Predicate predicateActive = criteriaBuilder.equal(rootEntity.get("panel_role"), panel_role);
				predicates.add(predicateActive);
			}
			if (interview_type != null && !"".equals(interview_type)) {
				Predicate predicateActive = criteriaBuilder.equal(rootEntity.get("interview_type"), interview_type);
				predicates.add(predicateActive);
			}
			if (start_date != null && end_date != null) {
				if (end_date.isAfter(start_date) == true) {
					predicates.add(criteriaBuilder.between(rootEntity.get("date"), start_date, end_date));
				} // checking date in between start date and end date
				else if (end_date.isAfter(start_date) == false)
					throw new WrongDateException("From Date should be before To Date");
			}

			criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[] {}));

			TypedQuery<PanelList> typedQuery = entityManager.createQuery(criteriaQuery);

			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
			List<PanelList> advertiseEntityList = typedQuery.getResultList(); // Query is executed
			if (advertiseEntityList.isEmpty()) {
				throw new InvalidDataException("List is Empty");
			} else {
				return advertiseEntityList.stream().map((dto) -> modelMapper.map(dto, PanelListDTO.class))
						.collect(Collectors.toList());
			}

		} else {
			throw new UnAuthorizedUserException("Invaild User");

		}

	}
}
