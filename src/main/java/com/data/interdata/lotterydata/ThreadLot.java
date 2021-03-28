package com.data.interdata.lotterydata;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;

/**
 * @author DL
 * @diescription com.data.interdata.lotterydata
 * @dis_projectdxl
 * @create 2021-03-27
 */
public  class  ThreadLot extends Thread {
	private Matcher matcher;
	private AllBalls.LotterEnum lotter;
	@Autowired
	private AllBalls allBalls;

	public ThreadLot(Matcher matcher, AllBalls.LotterEnum lotter) {
		this.matcher = matcher;
		this.lotter = lotter;
	}

	@Override
	public  void run() {
		synchronized (this) {
			int i = 1;
			while (matcher.find()) {
				String oneTermContent = matcher.group();
				switch (lotter) {
					case REDBULE:
						allBalls.getOneTermNumbers(oneTermContent);
						break;
					case IDDATE:
						if (i % 2 == 0) {
							allBalls.getOneTermNumbersId(oneTermContent);
						} else {
							allBalls.getOneTermNumbersDate(oneTermContent);
						}
						i++;
						break;
					case AMOUT:
						allBalls.getOneTermNumbersAmount(oneTermContent);
						break;
					case TOP:
						allBalls.getOneTermNumbersMin(oneTermContent, AllBalls.LotterEnum.TOP);
						break;
					case TWO:
						allBalls.getOneTermNumbersMin(oneTermContent, AllBalls.LotterEnum.TWO);
						break;
					default:
						break;
				}
			}
		}
	}
}
