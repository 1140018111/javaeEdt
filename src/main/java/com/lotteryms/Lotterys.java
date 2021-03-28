package com.lotteryms;

/**
 * 彩票接口
 * @author DL
 * @diescription com.lotterymechanism
 * @dis_projectdxl
 * @create 2021-03-26
 */
public interface Lotterys {
	/**
	 * 开奖
	 */
	String drawPrize(String versionId);

	String randomLottery();

}
