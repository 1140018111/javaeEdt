package com.generator.dao;

import com.generator.lottery.entity.LotterySsq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "lotterySsqMapper")
public interface LotterySsqMapper {
    int deleteByPrimaryKey(String versionid);

    int insert(LotterySsq record);

    int insertSelective(LotterySsq record);

    LotterySsq selectByPrimaryKey(String versionid);

    int updateByPrimaryKeySelective(LotterySsq record);

    int updateByPrimaryKey(LotterySsq record);

	LotterySsq queryNew(String coundition);

}