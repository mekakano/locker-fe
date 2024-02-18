package com.locker.gea.gealocker.services;

import org.springframework.stereotype.Service;

import com.locker.gea.gealocker.entity.ApiResponse;
import com.locker.gea.gealocker.entity.User;

@Service
public interface LockerService {
    public ApiResponse lihatData(int id_user);

    public ApiResponse validatePassword(int id_user, String password_locker, String nomor_locker);

    public ApiResponse pengembalian(int id_user, String password_locker, String nomor_locker);

    public ApiResponse lihatAvailable();

    public ApiResponse pilihLocker(int id_user, String nomor_locker);

    // public ApiResponse register(String nama, String email, String password,
    // String no_ktp, String phone_number);

}