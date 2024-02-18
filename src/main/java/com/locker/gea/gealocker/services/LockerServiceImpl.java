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
import com.locker.gea.gealocker.entity.Locker;

@Service
public class LockerServiceImpl implements LockerService {
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @Value("${api.url.lihatdata}")
    private String dataUrl;

    @Value("${api.url.validatepasswordlocker}")
    private String passwordLockerUrl;

    @Value("${api.url.lihatAvailable}")
    private String lihatAvailableUrl;

    @Value("${api.url.pilihlocker}")
    private String pilihLockerApi;

    @Value("${api.url.pengembalian}")
    private String pengembalianApi;

    public ApiResponse lihatData(int id_user) {
        Locker user = new Locker();
        ApiResponse response = null;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_user", id_user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObj.toString(), headers);
        try {
            response = restTemplate.postForObject(dataUrl, request, ApiResponse.class);
        } catch (Exception e) {
            response.setStatuscode(99);
            response.setMessage("Error");
            e.printStackTrace();
        }

        return response;

    }

    public ApiResponse validatePassword(int id_user, String password_locker, String nomor_locker) {
        Locker user = new Locker();
        ApiResponse response = null;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_user", id_user);
        jsonObj.put("password_locker", password_locker);
        jsonObj.put("nomor_locker", nomor_locker);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObj.toString(), headers);
        try {
            response = restTemplate.postForObject(passwordLockerUrl, request, ApiResponse.class);
        } catch (Exception e) {
            response.setStatuscode(99);
            response.setMessage("Error");
            e.printStackTrace();
        }

        return response;

    }

    public ApiResponse pengembalian(int id_user, String password_locker, String nomor_locker) {
        Locker user = new Locker();
        ApiResponse response = null;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_user", id_user);
        jsonObj.put("password_locker", password_locker);
        jsonObj.put("nomor_locker", nomor_locker);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObj.toString(), headers);
        try {
            response = restTemplate.postForObject(pengembalianApi, request, ApiResponse.class);
        } catch (Exception e) {
            response.setStatuscode(99);
            response.setMessage("Error");
            e.printStackTrace();
        }

        return response;

    }

    public ApiResponse lihatAvailable() {
        Locker user = new Locker();
        ApiResponse response = null;
        try {
            response = restTemplate.getForObject(lihatAvailableUrl, ApiResponse.class);
        } catch (Exception e) {
            response.setStatuscode(99);
            response.setMessage("Error");
            e.printStackTrace();
        }

        return response;
    }

    public ApiResponse pilihLocker(int id_user, String nomor_locker) {
        Locker user = new Locker();
        ApiResponse response = null;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_user", id_user);
        jsonObj.put("nomor_locker", nomor_locker);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObj.toString(), headers);
        try {
            response = restTemplate.postForObject(pilihLockerApi, request, ApiResponse.class);
        } catch (Exception e) {
            response.setStatuscode(99);
            response.setMessage("Error");
            e.printStackTrace();
        }

        return response;
    }

}
