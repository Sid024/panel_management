package com.zensar.pm.panel.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zensar.pm.panel.dto.GradeDTO;
import com.zensar.pm.panel.dto.PanelAvailDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityStatusDTO;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.entity.GradeEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;
import com.zensar.pm.panel.repository.GradeRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityRepository;
import com.zensar.pm.panel.repository.PanelAvailabilityStatusRepository;
import com.zensar.pm.panel.repository.PanelEntityRepository;
import com.zensar.pm.panel.repository.UserRepository;
import com.zensar.pm.panel.service.PanelAvailabilityServiceImpl;

public class PanelAvailConstants {
	@Autowired
    PanelAvailabilityRepository panelAvailabilityRepository; 
    @Autowired
    PanelAvailabilityStatusRepository panelAvailabilityStatusRepository; 
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    UserRepository usersRepository;
    @Autowired
    PanelEntityRepository panelsRepository;

    PanelAvailabilityServiceImpl simpl=new PanelAvailabilityServiceImpl();

 

    public static PanelAvailDTO convertPanelAvailEntityIntoPanelAvailDto(PanelAvailabilityEntity panelAvailEntity) {
        PanelAvailabilityStatusEntity panelAvailabilityStatusEntity = panelAvailEntity.getAvailablityStatusId();
        PanelEntity panelsEntity=panelAvailEntity.getPanelId();
        PanelAvailabilityStatusDTO panelAvailabilityStatusEntityDto = new PanelAvailabilityStatusDTO(panelAvailabilityStatusEntity.getId(), panelAvailabilityStatusEntity.getAvailablityStatus());
        int availabilityStatusId = panelAvailabilityStatusEntityDto.getAvailabilityStatusId();
        GradeEntity gradeEntity=panelsEntity.getGradeEntity();
        GradeDTO gradeDto=new GradeDTO(gradeEntity.getId(),gradeEntity.getGrade());
        String grade=gradeDto.getGrade();
        UserEntity userEntity = panelsEntity.getUserEntity();
        String panelName = userEntity.getUserName();
        int panelId = userEntity.getUserId();
        PanelDTO panelDto=new PanelDTO(panelId,panelName,panelsEntity.getContact(),grade);
        return new PanelAvailDTO(panelAvailEntity.getId(), panelName, panelId, grade, panelAvailEntity.getDate(),availabilityStatusId ,panelAvailEntity.getCreatedBy(),panelAvailEntity.getCreatedOn().toLocalDate(),panelAvailEntity.getUpdatedOn().toLocalDate(),panelAvailEntity.getUpdatedBy(),panelAvailEntity.isDeleted(),panelAvailEntity.getDeletedBy(),panelAvailEntity.getDeletedOn().toLocalDate(),panelAvailEntity.getStartTime(),panelAvailEntity.getEndTime());
    }

 

//    public static PanelAvailabilityEntity convertPanelAvailDtoIntoPanelAvailEntity(PanelAvailabilityDTO pa) {
//        String availabilityStatusId = pa.getAvailabilityStatusId();
//        PanelAvailabilityStatusEntity panelAvailabilityStatusEntity = panelAvailabilityStatusRepository.findById(availabilityStatusId).get();
//        String panelName = pa.getPanelName();
//        int panelId = pa.getPanelId();
//        PanelsEntity panelsEntity = panelsRepository.findById(panelId).get();
//        PanelAvailabilityEntity panelAvailEntity=new PanelAvailabilityEntity(pa.getPanelsAvailabilityId(),panelsEntity,pa.getAvailablilityDate(),panelAvailabilityStatusEntity,pa.getCreatedBy(),pa.getCreatedOn(),pa.getUpdatedOn(),pa.getUpdatedBy(),pa.isDeleted(),pa.getDeletedBy(),pa.getDeletedOn(),pa.getStartTime(),pa.getEndTime(),pa.getFromDate(),pa.getToDate());
//        PanelAvailabilityEntity newPanelAvailEntity = panelAvailabilityRepository.save(panelAvailEntity);
//        return newPanelAvailEntity;
//    }

 

    public static List<PanelAvailDTO> convertPanelAvailEntityListIntoPanelAvailDtoList(List<PanelAvailabilityEntity> panelAvailEntityList) {
        List<PanelAvailDTO> panelAvailDtoList = new ArrayList<PanelAvailDTO>();
        for(PanelAvailabilityEntity panelAvailEntity: panelAvailEntityList) {
            panelAvailDtoList.add(convertPanelAvailEntityIntoPanelAvailDto(panelAvailEntity));
        }
        return panelAvailDtoList;
    }

 

    public static PanelDTO convertPanelEntityToPanelDto(PanelEntity panelsEntity) {
        GradeEntity gradeEntity = panelsEntity.getGradeEntity();
        String grade = gradeEntity.getGrade();
        UserEntity userEntity = panelsEntity.getUserEntity();
        String panelName = userEntity.getUserName();
        int panelId = userEntity.getUserId();
        PanelDTO panelDto=new PanelDTO(panelId,panelName,panelsEntity.getContact(),grade);
        return panelDto;
    }

    public static List<PanelDTO> convertPanelsEntityListToPanelsDtoList(List<PanelEntity> panelsEntityList){
        List<PanelDTO> panelDtoList=new ArrayList<PanelDTO>();
        for(PanelEntity panelEntity: panelsEntityList) {

            panelDtoList.add(convertPanelEntityToPanelDto(panelEntity));
        }
        return panelDtoList;
    }
}
