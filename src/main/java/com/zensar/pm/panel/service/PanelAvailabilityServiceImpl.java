package com.zensar.pm.panel.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.exceptions.DuplicateStatusException;
import com.zensar.pm.panel.repository.PanelAvailablityRepository;
import com.zensar.pm.panel.repository.PanelAvailablityStatusRepository;
import com.zensar.pm.panel.repository.PanelEntityRepository;

@Service
public class PanelAvailabilityServiceImpl implements PanelAvailabilityService {

	@Autowired
	PanelAvailablityRepository repository;

	@Autowired
	PanelEntityRepository panelRepo;

	@Autowired
	PanelAvailablityStatusRepository panelStatusRepo;

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
		
	}


