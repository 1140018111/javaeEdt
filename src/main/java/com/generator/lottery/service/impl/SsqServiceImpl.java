package com.generator.lottery.service.impl;

import com.data.loderdate.LotSticData;
import com.generator.dao.LotterySsqMapper;
import com.generator.lottery.entity.LotterySsq;
import com.generator.lottery.service.SsqServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @author DL
 * @diescription com.generator.lottery.service.impl
 * @dis_projectdxl
 * @create 2021-03-26
 */
@Service
public class SsqServiceImpl implements SsqServiceInterface {
	@Autowired
	private LotterySsqMapper lotterySsqMapper;
	static StringBuffer coundition=null;
	@Override
	public LotterySsq getLot(String versionId) {
		LotterySsq lotterySsq = lotterySsqMapper.selectByPrimaryKey(versionId);
		return lotterySsq;
	}

	@Override
	public LotterySsq queryForNewOne() {
		coundition=new StringBuffer();
		coundition.append(" flag='"+ LotSticData.ZXSTATUS +"' ");
		LotterySsq lotterySsq = lotterySsqMapper.queryNew(coundition.toString());
		return lotterySsq;
	}

	@Override
	public int insert(LotterySsq record) {
		int insert = lotterySsqMapper.insert(record);
		return insert;
	}

	@Override
	public int update(LotterySsq record) {
		int i = lotterySsqMapper.updateByPrimaryKey(record);
		return i;
	}

	@Override
	public boolean delete(String versionId) {
		boolean sure=false;
		int yesOrNo = lotterySsqMapper.deleteByPrimaryKey(versionId);
		sure= BigInteger.ONE.toString().equals(yesOrNo+"")?true:false;
		return sure;
	}
	public LotterySsq queryForRed(String red) {
		coundition=new StringBuffer();
		coundition.append(" redareal='"+red+"' ");
		LotterySsq lotterySsq = lotterySsqMapper.queryNew(coundition.toString());
		return lotterySsq;
	}
}
