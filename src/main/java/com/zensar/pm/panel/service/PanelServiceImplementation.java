package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.zensar.pm.panel.dto.PanelAvailabilityListDto;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.entity.Panel;
import com.zensar.pm.panel.entity.PanelAvailabilityList;
import com.zensar.pm.panel.enums.Constants;
import com.zensar.pm.panel.exceptions.CustomNullPointerException;
import com.zensar.pm.panel.exceptions.EmptyListException;
import com.zensar.pm.panel.exceptions.InvalidAssociateNameException;
import com.zensar.pm.panel.exceptions.InvalidPanelException;
import com.zensar.pm.panel.exceptions.UnauthorizedUserException;
import com.zensar.pm.panel.exceptions.WrongDateException;
import com.zensar.pm.panel.repository.PanelAvailabilityListRepository;
import com.zensar.pm.panel.repository.PanelRepository;

@Service
public class PanelServiceImplementation implements PanelService {

	@Autowired
	PanelRepository panelRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	LoginDelegate loginDelegate;
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired 
	private EntityManager entityManager;

	@Autowired
	private PanelAvailabilityListRepository repo;
	
	@Override
	public PanelDTO updatePanel(String associateId, PanelDTO panelDTO, String jwtToken) {
		
		
		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())) {
			Panel existingPanel = panelRepository.findById(associateId).orElse(null);
			if (existingPanel == null)
				throw new InvalidPanelException("Panel not found");
			else if (panelDTO.getDate() == null || panelDTO.getFromTime() == null || panelDTO.getToTime() == null
					|| panelDTO.getStatus() == null)
				throw new CustomNullPointerException("Empty value! enter the value");

			else {
				existingPanel.setDate(panelDTO.getDate());
				existingPanel.setFromTime(panelDTO.getFromTime());
				existingPanel.setToTime(panelDTO.getToTime());
				existingPanel.setStatus(panelDTO.getStatus());

				Panel save = panelRepository.save(existingPanel);

				return modelMapper.map(save, PanelDTO.class);
			}

		}

		else {
			throw new UnauthorizedUserException("Invalid User");
		}
	}
	@Override
    public List<PanelDTO> getByAssociateName(String name,String jwtToken) {
		return null;
		/*
		 * List<Panel> findByAssociateName =
		 * panelRepository.findByTextIgnoreAssociates(name);
		 * 
		 * if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(
		 * jwtToken).getRoleName())) { if(findByAssociateName.isEmpty()) { throw new
		 * InvalidAssociateNameException("Associate Not Found"); } else { List<PanelDTO>
		 * collect = findByAssociateName.stream() .map((e) -> modelMapper.map(e,
		 * PanelDTO.class)).collect(Collectors.toList()); return collect; } } else {
		 * throw new UnauthorizedUserException("Invalid User"); } }
		 * 
		 * public void setAssociateRepository(PanelRepository associateRepository) {
		 * this.panelRepository= associateRepository; } public void
		 * setMapper(ModelMapper mapper) { this.modelMapper = mapper;
		 */
    }
    
    
    
    /// Our Project
    
    @Override//// Here in this function we are not using crud repository
    public List<PanelAvailabilityListDto> ExportPanelBYFilter(String panelId, String role,
            String email,LocalDate fromDate,LocalDate toDate, String interviewType, String panelName,
            String availabilityStatus,String jwtToken)    {

    	if(Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName()))
		  {
		 

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PanelAvailabilityList> criteriaQuery=criteriaBuilder.createQuery(PanelAvailabilityList.class);
        Root<PanelAvailabilityList> rootEntity = criteriaQuery.from(PanelAvailabilityList.class);

        List<Predicate> searchcriteria = new ArrayList<>();

        if(panelName!=null && panelName!="")
        searchcriteria.add(criteriaBuilder.like(rootEntity.get("panelName"),"%"+panelName+"%" ));
        if(panelId!=null && panelId!="")
        searchcriteria.add(criteriaBuilder.like(rootEntity.get("panelId"),panelId));    
        if(role!=null && role!="")
        searchcriteria.add(criteriaBuilder.like(rootEntity.get("role"),role));//panel_role name must be same in databse
        if(interviewType!=null && interviewType!="")
        searchcriteria.add(criteriaBuilder.like(rootEntity.get("interviewType"),interviewType));    
        if(email!=null && email!="")
        searchcriteria.add(criteriaBuilder.like(rootEntity.get("email"),email));
        if(availabilityStatus!=null && availabilityStatus!="")
        searchcriteria.add(criteriaBuilder.like(rootEntity.get("availabilityStatus"),availabilityStatus));
         //                                                      should be entity name

        if(fromDate!=null && toDate!=null)
        {
           if(fromDate.isAfter(toDate)==true)        
            {searchcriteria.add(criteriaBuilder.between(rootEntity.get("date"),fromDate,toDate));}// checking date in between start date and end date
           else if(toDate.isAfter(fromDate)==false)
           throw new WrongDateException("From Date should be before To Date");    
        }


        criteriaQuery.select(rootEntity).where(criteriaBuilder.and(searchcriteria.toArray(new Predicate[searchcriteria.size()])));

        List<PanelAvailabilityList> p = entityManager.createQuery(criteriaQuery).getResultList();

 

        if(p.size()==0)
        {throw new UnauthorizedUserException("Invalid data");}
        else
        return p.stream().map((dto) -> modelMapper.map(dto,PanelAvailabilityListDto.class)).collect(Collectors.toList());
        }


		 else 
		 throw new UnauthorizedUserException("Invaild User");
		 
          
   }
    
    ///For Search
    @Override
	public List<PanelAvailabilityListDto> SearchPanelBYFilter(String panelId, String panelName, String email,
            String availabilityStatus, LocalDate fromDate, LocalDate toDate,
            String role, String interviewType,int pageNo, int pageSize, String jwtToken) {
		 
    	 
    	
    		if(Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleName())) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PanelAvailabilityList> criteriaQuery = criteriaBuilder.createQuery(PanelAvailabilityList.class);

 

        Root<PanelAvailabilityList> rootEntity = criteriaQuery.from(PanelAvailabilityList.class);
     List<Predicate> predicates=new ArrayList<Predicate>();
     
     if(panelId!=null && !"".equals(panelId)) 
        predicates.add(criteriaBuilder.equal(rootEntity.get("panelId"), panelId));
       
     

     if(panelName!=null && !"".equals(panelName)) 
    	 predicates.add(criteriaBuilder.equal(rootEntity.get("panelName"), panelName));
       
         
     
     if(email!=null && !"".equals(email)) 
    	 predicates.add(criteriaBuilder.equal(rootEntity.get("email"), email));
        
     
     if(availabilityStatus!=null && !"".equals(availabilityStatus)) 
    	 predicates.add(criteriaBuilder.equal(rootEntity.get("availabilityStatus"), availabilityStatus));
       
     
     if(role!=null && !"".equals(role)) 
    	 predicates.add(criteriaBuilder.equal(rootEntity.get("role"), role));
         
     
     if(interviewType!=null && !"".equals(interviewType)) 
    	 predicates.add(criteriaBuilder.equal(rootEntity.get("interviewType"), interviewType));
        
     
     if(fromDate!=null && toDate!=null)
		{
		   if(fromDate.isAfter(toDate)==true)		
		    {predicates.add(criteriaBuilder.between(rootEntity.get("date"),fromDate,toDate));} 
		   else if(toDate.isAfter(fromDate)==false)
	       throw new WrongDateException("Invalid Date");	
		}
     
     criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[]{}));

     TypedQuery<PanelAvailabilityList> typedQuery = entityManager.createQuery(criteriaQuery);
     
     typedQuery.setFirstResult(pageNo * pageSize); 
     typedQuery.setMaxResults(pageSize);
     List<PanelAvailabilityList> searchPanel = typedQuery.getResultList(); //Query is executed
       
       if (searchPanel.isEmpty()) 
       throw new EmptyListException("List is Empty");
       else 
       return searchPanel.stream().map((dto) -> modelMapper.map(dto, PanelAvailabilityListDto.class)).collect(Collectors.toList());    
	} 
    		
    	 else
   		 throw new UnauthorizedUserException("Invalid User");
	
    	
    
	}

    
    
    
    
    


}
