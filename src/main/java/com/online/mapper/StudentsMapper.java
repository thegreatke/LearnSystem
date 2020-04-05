package com.online.mapper;

import com.online.model.Liuyan;
import com.online.model.Students;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@Mapper
@Repository
public interface StudentsMapper {
    @Select("select * from students ")
    ArrayList<Students> selectAllStudent() throws SQLException;

    @Select("select * from liuyan ")
    ArrayList<Liuyan> selectAllLiuyan() throws SQLException;


    @Select("select * from students where id = #{id} ")
    Students selectAStudent(int id) throws SQLException;

    @Select("select name from students where id = #{id} ")
    String selectNStudent(int id) throws SQLException;

    @Insert("insert into students(name, id, teacher_name, email, password) " +
            "values(#{name},#{id},#{teacherName},#{email},#{password})")
    int addStudent(Students students) throws SQLException;

    @Delete("delete  from students where id = #{id}")
    void deleteStudent(int id) throws SQLException;

    @Delete("delete  from liuyan where id = #{id}")
    void deleteReply(int id) throws SQLException;



    @Update("update  students set score = #{score} where id = #{id}")
    void updateScoreOfStudent(int id, int score) throws SQLException;

    @Update("update  liuyan set reply = #{reply} where id = #{id}")
    void addReply(int id, String reply) throws SQLException;


    @Insert("Insert into liuyan ( name, liuyan) values ( #{name},#{liuyan}) ")
    void addLiuyan(String name, String liuyan) throws SQLException;


    @Update("update  students set name = #{name},email = #{email},password = #{password} where id = #{id}")
    void updateInfoOfStudent(int id,  String email,String name, String password) throws SQLException;

    @Select("select IFNULL ((select IFNULL(score, -2) from students where id = #{id} and name = #{name}), -1)")
    int selectScoreOfStudent(int id, String name) throws SQLException;

    @Select("select password from students where id = #{id}")
    String findPasswordById(int id);

}
