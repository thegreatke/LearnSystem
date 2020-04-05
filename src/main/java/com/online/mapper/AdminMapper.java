package com.online.mapper;

import com.online.model.Admin;
import com.online.model.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Mapper
@Repository
public interface AdminMapper {


    @Select("select * from admins order by id desc")
    List<Admin> findAllAdmin();

    @Delete("delete  from admins where id = #{id}")
    void deleteOneAdmin(@Param("id") String id) throws SQLException;

    @Select("select password from admins where id = #{id}")
    String findPasswordById(@Param("id") String id);

    @Insert("insert into admins(id,password) " +
            "values(#{id},#{password})")
    int register(String id, String password);
}
