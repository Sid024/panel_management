package com.zensar.pm.panel.service;

import com.zensar.pm.panel.dto.UserDTO;

public interface LoginDelegate {
	UserDTO isTokenValid(String jwtToken);

}
