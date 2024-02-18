package com.locker.gea.gealocker.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.locker.gea.gealocker.entity.ApiResponse;
import com.locker.gea.gealocker.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @Value("${api.url.login}")
    private String loginUrl;

    @Value("${api.url.register}")
    private String registerUrl;

    public ApiResponse getUserByEmail(String email, String password) {
        User user = new User();
        ApiResponse response = null;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("email", email);
        jsonObj.put("password", password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObj.toString(), headers);
        try {
            response = restTemplate.postForObject(loginUrl, request, ApiResponse.class);
        } catch (Exception e) {
            response.setStatuscode(99);
            response.setMessage("Error");
            e.printStackTrace();
        }

        return response;

    }

    public ApiResponse register(String nama, String email, String password, String no_ktp, String phone_number) {
        ApiResponse response = null;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("email", email);
        jsonObj.put("nama", nama);
        jsonObj.put("no_ktp", no_ktp);
        jsonObj.put("phone_number", phone_number);
        jsonObj.put("password", password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObj.toString(), headers);
        try {
            response = restTemplate.postForObject(registerUrl, request, ApiResponse.class);
        } catch (Exception e) {
            response.setStatuscode(99);
            response.setMessage("Error");
            e.printStackTrace();
        }

        return response;

    }

}
