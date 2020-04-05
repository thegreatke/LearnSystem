package com.online.service;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public interface StudentService {


    int studentRegister(int id, String email, String password) throws SQLException;


    int infoChange(int id, String email, String name, String password) throws SQLException;

    /**
     * 指定删除某一学生的报名信息
     *
     * @param id
     * @return
     */
    @Transactional
    String deleteStudent(int id);


    /**
     * 指定删除某一留言
     *
     * @param id
     * @return
     */
    @Transactional
    String deleteReply(int id);



    /**
     * 首次录入某一学生的得分分数
     *
     * @param id
     * @return
     */
    @Transactional
    String addScoreToStudent(int id, int score);

    /**
     * 录入huifu
     *
     * @param id
     * @return
     */
    @Transactional
    String addReply(int id, String reply);


    /**
     * 录入留言
     *
     * @param id
     * @return
     */
    @Transactional
    String addLiuyan(int id, String liuyan);


    /**
     * 修改某一学生的得分分数
     *
     * @param id
     * @return
     */
    @Transactional
    String changeScoreToStudent(int id, int score);

    /**
     * 学号和姓名同时匹配, 查询某一学生所得的分数
     *
     * @param id
     * @return
     */
    int getScoreOfStudent(int id, String name);

    /**
     * 返回所有学生的报名信息。
     *
     * @return
     */
    JSONArray findAllStudents();


    /**
     * 返回所有学生的留言。
     *
     * @return
     */
    JSONArray findAllLiuyan();
}
