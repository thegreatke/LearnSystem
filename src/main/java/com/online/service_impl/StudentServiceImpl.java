package com.online.service_impl;

import com.online.mapper.StudentsMapper;
import com.online.model.Liuyan;
import com.online.model.Students;
import com.online.service.StudentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentsMapper studentsMapper;

    @Override
    public int studentRegister(int id, String email, String password) throws SQLException {

        String truePassword = studentsMapper.findPasswordById(id);
        if (truePassword != null) return 2;
        Students student = new Students();
        student.setPassword(password);
        student.setId(id);
        student.setEmail(email);
        student.setName("默认昵称");
        int respons = studentsMapper.addStudent(student);

        return respons;
    }


    @Override
    public int infoChange(int id, String email, String name, String password) throws SQLException {
        Students students = studentsMapper.selectAStudent(id);

        if (email.equals("")) email = students.getEmail();
        if (name.equals("")) name = students.getName();
        if (password.equals("")) password = students.getPassword();
        studentsMapper.updateInfoOfStudent(id, email, name, password);
        return 1;
    }

    @Override
    public String deleteStudent(int id) {
        try {
            studentsMapper.deleteStudent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "delete successfully";
    }

    @Override
    public String deleteReply(int id) {
        try {
            studentsMapper.deleteReply(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "delete successfully";
    }



    @Override
    public String addScoreToStudent(int id, int score) {
        try {
            studentsMapper.updateScoreOfStudent(id, score);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "update successfully";
    }

    @Override
    public String addReply(int id, String reply) {
        try {
            studentsMapper.addReply(id, reply);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "update successfully";
    }

    @Override
    public String addLiuyan(int id, String liuyan) {
        try {

            String name = studentsMapper.selectNStudent(id);

            studentsMapper.addLiuyan(name, liuyan);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "发布留言成功！";
    }

    @Override
    public String changeScoreToStudent(int id, int score) {
        try {
            studentsMapper.updateScoreOfStudent(id, score);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "change score successfully";
    }

    @Override
    public int getScoreOfStudent(int id, String name) {
        int score = 0;
        // TODO: 2019-10-10 查询未找到应该返回特殊字段
        try {
            score = studentsMapper.selectScoreOfStudent(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    @Override
    public JSONArray findAllStudents() {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Students students : studentsMapper.selectAllStudent()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", students.getId());
                jsonObject.put("name", students.getName());
                jsonObject.put("score", students.getScore());
                jsonObject.put("teacherName", students.getTeacherName());
                jsonObject.put("email", students.getEmail());

                jsonArray.add(jsonObject);   //添加一个学生信息json object进入json array数组中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }



    @Override
    public JSONArray findAllLiuyan() {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Liuyan liuyan : studentsMapper.selectAllLiuyan()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", liuyan.getId());
                jsonObject.put("name", liuyan.getName());
                jsonObject.put("liuyan", liuyan.getLiuyan());
                jsonObject.put("reply", liuyan.getReply());

                jsonArray.add(jsonObject);   //添加一个学生信息json object进入json array数组中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


}
