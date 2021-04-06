package com.lotteryms.kinds;

import com.contrller.LotteryContrller;
import com.generator.lottery.entity.LotterySsq;
import com.generator.lottery.service.impl.SsqServiceImpl;
import com.lotteryms.WelfareLottery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author DL
 * @diescription com.lotterymechanism.kinds
 * @dis_projectdxl
 * @create 2021-03-26
 */
@Component
public class WeifareSSQ extends WelfareLottery {
	private static Logger logger = LoggerFactory.getLogger(LotteryContrller.class);
	@Autowired
	private SsqServiceImpl serviceInterfaceImpl;

	final static String[] RED={"01","02","03","04","05","06","07","08","09","10","11","12",
			"13","14","15","16","17","18","19","20","21","22","23","24","25",
			"26","27","28","29","30","31","32","33"};

	final static String[] BULE={"01","02","03","04","05","06","07","08","09","10","11","12",
			"13","14","15","16"};

	static Random random=new Random();

	static LotterySsq lot =new LotterySsq();
	static int count=1;
	/**
	 * 根据期号获取开奖号
	 * @param versionId
	 * @return String
	 */
	@Override
	public String drawPrize(String versionId) {
		String lotNo="";
		if(!StringUtils.isEmpty(versionId)){
			lot = serviceInterfaceImpl.getLot(versionId);
		}
		if(lot!=null){
			lotNo=lot.getRedareal()+" "+lot.getBuleareal();
		}
		return lotNo;
	}

	/**
	 * 生成随机号
	 * @return
	 */
	@Override
	public String randomLottery() {
		List<String> redArea = WeifareSSQ.getRandomArea(RED);
		List<String> blueArea = WeifareSSQ.getRandomArea(BULE);
		//去重操作
		boolean life=quChong(redArea);
		if(life){
			//递归调用
			randomLottery();
		}
		logger.info("生成的彩票号："+redArea.toString()+blueArea.toString());
		return redArea.toString()+" "+blueArea.toString();
	}

	/**
	 * 去重
	 * @param redArea
	 * @return
	 */
	private boolean quChong(List<String> redArea) {
		StringBuffer redNo=new StringBuffer();
		for (String s : redArea) {
			redNo.append(s);
		}
		LotterySsq lotterySsq = serviceInterfaceImpl.queryForRed(redNo.toString());
		return !StringUtils.isEmpty(lotterySsq);
	}

	/**
	 * 获取最新一期的开奖号码
	 * @return LotterySsq
	 */
	public LotterySsq drawPrizeForNew() {
		lot =serviceInterfaceImpl.queryForNewOne();
		return lot;
	}

	/**
	 * 自动随机选取机制
	 * @param rOrB
	 * @return
	 */
	public static List<String> getRandomArea(String[] rOrB) {
		String newNo;
		int number=1;
		List<String> area=new ArrayList<String>();
		switch (rOrB.length) {
			case 33:
				number=6;
				break;
			default:
				number=1;
		}
		for(int i=0;i<number;i++){
			int nextInt = random.nextInt(rOrB.length);
			if(nextInt==0) {
				i-- ;
				continue;
			}
			newNo=rOrB[nextInt-1];
			if(area.contains(newNo)){
				i--;
			}else {
				area.add(newNo);
			}
		}
		area=area.stream().sorted().collect(Collectors.toList());
		return area;
	}

	/**
	 * 格式化
	 * @param red
	 * @param bule
	 * @return
	 */
	public String formatLot(String red,String bule){
		StringBuffer no=new StringBuffer();
		StringBuffer redNo=new StringBuffer();
		if (!StringUtils.isEmpty(red)){
			no.append("[");
			char[] chars = red.toCharArray();
			for (int i = 1; i <=chars.length ; i++) {
				no.append(i ==chars.length?chars[i-1]:i % 2 == 0 ? chars[i-1] + "," : chars[i-1]);
			}
			no.append("]");
		}
		if (!StringUtils.isEmpty(bule)){
			no.append(" ["+bule+"]");
		}

		return no+"";
	}

	/**
	 * 根据上期号算法
	 * @param number
	 * @return
	 */
	public static String algorithmForBefourNo(String number){
		number="101112132628";
		char[] chars = number.toCharArray();
		ArrayList<Integer> list = new ArrayList<Integer>();
		StringBuffer buffer = new StringBuffer();
		for (int j = 0; j < chars.length; j++) {
			if(buffer.length()==2){
				list.add(Integer.parseInt(buffer.toString()));
				buffer.delete(0,buffer.length());
			}
			buffer.append(chars[j]);
		}
		int sumb=0;
		for (Integer integer : list) {
			sumb=integer+sumb;
		}
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (Integer item : list) {
			int c=(sumb-item)/ item;
			if(c>33){
				newList.add(c%10);
			}else{
				newList.add(c);
			}
		}
		System.out.println(sumb);
		return null;
	}

}
