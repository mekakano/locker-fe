package com.locker.gea.gealocker.services;

import org.springframework.stereotype.Service;

import com.locker.gea.gealocker.entity.ApiResponse;
import com.locker.gea.gealocker.entity.User;

@Service
public interface UserService {
    public ApiResponse getUserByEmail(String email, String password);

    public ApiResponse register(String nama, String email, String password, String no_ktp, String phone_number);

}