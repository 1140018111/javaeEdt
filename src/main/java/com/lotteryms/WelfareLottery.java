package com.lotteryms;

/**
 * 福利彩票
 * @author DL
 * @diescription com.lotterymechanism
 * @dis_projectdxl
 * @create 2021-03-26
 */
public abstract class WelfareLottery implements Lotterys {

	@Override
	public abstract String drawPrize(String versionId);

	@Override
	public abstract String randomLottery();

}
