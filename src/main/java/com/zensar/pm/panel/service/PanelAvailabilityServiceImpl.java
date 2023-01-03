package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelsGetAllDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;
import com.zensar.pm.panel.exceptions.DuplicateStatusException;
import com.zensar.pm.panel.repository.PanelAvailabilityRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityStatusRepository;
import com.zensar.pm.panel.repository.PanelEntityRepository;
import com.zensar.pm.panel.repository.UserRepository;

@Service
public class PanelAvailabilityServiceImpl implements PanelAvailabilityService {

	@Autowired
	PanelAvailabilityRepository repository;

	@Autowired
	PanelEntityRepository panelRepo;

	@Autowired
	PanelAvailabilityStatusRepository panelStatusRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PanelAvailabilityDTO addPanelAvailablitySingle(PanelAvailabilityDTO dto) {

		PanelAvailabilityEntity entity = modelMapper.map(dto, PanelAvailabilityEntity.class);
		boolean panel=repository.existsByPanelId(entity.getPanelId());
		boolean startTime=repository.existsByStartTime(entity.getStartTime());
		LocalDate date=dto.getDate();
		boolean existDate=repository.existsByDate(date);
		if(panel && startTime && existDate) {
			throw new DuplicateStatusException("You are already booked");}
		PanelEntity panelEntity = panelRepo.findById(entity.getPanelId().getId()).get();
		PanelAvailabilityStatusEntity statusEntity=panelStatusRepo.findById(1).get();

		entity.setPanelId(panelEntity);
		entity.setPanelAvailablityStatusEntity(statusEntity);
		repository.save(entity);

		return dto;
		}

//	@Override
//	public PanelsGetAllResponseDTO getAllPanels() {
//		List<PanelEntity> panelEntity=panelRepo.findAll();
//		if(!(panelEntity.isEmpty())) {
//			List<PanelsGetAllDTO> dtoList=new ArrayList<>();
//			for(PanelEntity panel:panelEntity) {
//				PanelsGetAllDTO dto=new PanelsGetAllDTO();
//				dto.setPanelId(panel.getId());
//				UserEntity userEntity=userRepo.findById(panel.getId()).get();
//				dto.setPanelName(userEntity.getUserName());
//				PanelEntity pan=panelRepo.findById(panel.getId()).get();
//				dto.setPanelRole(pan.getRoleType().getRoleName());
//				
//				dtoList.add(dto);
//			}
//			PanelsGetAllResponseDTO responseDTO=new PanelsGetAllResponseDTO();
//			responseDTO.setList(dtoList);
//			return responseDTO;
//		}
//		
//		
//		return null;
//	}
		
	}


