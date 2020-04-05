package com.online.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    int adminLogin(String id, String password);

    int studentlogin(int id, String password);

    int adminRegister(String id, String password);
}
