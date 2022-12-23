package com.zensar.pm.panel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.entity.Panel;
import com.zensar.pm.panel.enums.Constants;
import com.zensar.pm.panel.exceptions.CustomNullPointerException;
import com.zensar.pm.panel.exceptions.InvalidAssociateNameException;
import com.zensar.pm.panel.exceptions.InvalidPanelException;
import com.zensar.pm.panel.exceptions.UnauthorizedUserException;
import com.zensar.pm.panel.repository.PanelRepository;

@Service
public class PanelServiceImplementation implements PanelService {
	@Autowired
	PanelRepository panelRepository;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	LoginDelegate loginDelegate;

	@Override
	public PanelDTO updatePanel(String associateId, PanelDTO panelDTO, String jwtToken) {
		
		
		if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleEntity())) {
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
        List<Panel> findByAssociateName = panelRepository.findByTextIgnoreAssociates(name);
        
        if (Constants.ROLE_PRACTICE_HEAD.equalsIgnoreCase(loginDelegate.isTokenValid(jwtToken).getRoleEntity())) {
        if(findByAssociateName.isEmpty()) {
            throw new InvalidAssociateNameException("Associate Not Found");
        }
        else {
        List<PanelDTO> collect = findByAssociateName.stream()
                .map((e) -> modelMapper.map(e, PanelDTO.class)).collect(Collectors.toList());
        return collect;
        }
    }
        else {
            throw new UnauthorizedUserException("Invalid User");
            }
        }

    public void setAssociateRepository(PanelRepository associateRepository) {
        this.panelRepository= associateRepository;
    }
    public void setMapper(ModelMapper mapper) {
        this.modelMapper = mapper;
    }


}