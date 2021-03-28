package com.generator.lottery.service;

import com.generator.lottery.entity.LotterySsq;

/**
 * @author DL
 * @diescription com.generator.lottery.service
 * @dis_projectdxl
 * @create 2021-03-26
 */
public interface SsqServiceInterface {
	//根据期号id查询
	public LotterySsq getLot(String versionId);
	//最新一期号
	public LotterySsq queryForNewOne();
	//插入
	public int insert(LotterySsq record);
	//修改
	public int update(LotterySsq record);

	public boolean delete(String versionId);

}
