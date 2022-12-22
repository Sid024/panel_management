package com.zensar.pm.panel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zensar.pm.panel.dto.UserDTO;

@Service
public class LoginDelegateImpl implements LoginDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserDTO isTokenValid(String jwtToken) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", jwtToken);
		HttpEntity entity = new HttpEntity(headers);
		try {
			ResponseEntity<UserDTO> result = this.restTemplate.exchange(
					"http://localhost:8001/pm/user-management/token/validate", HttpMethod.GET, entity, UserDTO.class);
			return result.getBody();
		} catch (Exception e) {
			return new UserDTO();
		}

	}
}
