package com.generator.dao;

import com.generator.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public int deleteByPrimaryKey(String userid);

    public int insert(User record);

    public  int insertSelective(User record);
//    @Select("select * from user where userid=#{userid}")
    public User selectByPrimaryKey(String userid);

    public  int updateByPrimaryKeySelective(User record);

    public int updateByPrimaryKey(User record);
}