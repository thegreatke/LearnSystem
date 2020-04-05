package com.online.service_impl;

import com.online.mapper.AdminMapper;
import com.online.mapper.StudentsMapper;
import com.online.service.LoginService;
import com.online.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    AdminMapper adminMapper;
    @Autowired
    StudentsMapper studentsMapper;


    @Override
    public int adminLogin(String id, String password) {

        String truePassword = adminMapper.findPasswordById(id);
        if (password.equals(truePassword)) return 1;
        else return 0;
    }

    @Override
    public int studentlogin(int id, String password) {
        String truePassword = studentsMapper.findPasswordById(id);
        if (password.equals(truePassword)) return 1;
        else return 0;
    }

    @Override
    public int adminRegister(String id, String password) {

        String truePassword = adminMapper.findPasswordById(id);
        if (truePassword != null) return 2;
        int respons = adminMapper.register(id, password);

        return respons;
    }


}
