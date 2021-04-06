package com.contrller;


import com.data.interdata.lotterydata.AllBalls;
import com.data.interdata.lotterydata.NetData;
import com.data.loderdate.LotSticData;
import com.generator.lottery.entity.LotterySsq;
import com.generator.lottery.service.impl.SsqServiceImpl;
import com.lotteryms.kinds.WeifareSSQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Date;

/**
 * 彩票定制接口
 * @author DL
 * @diescription com.contrller
 * @dis_projectdxl
 * @create 2021-03-25
 */
@RestController
@RequestMapping("/lot_ssq")
@PropertySource(value = "classpath:otherConf/lotConfig.properties")
public class LotteryContrller {
	private static Logger logger = LoggerFactory.getLogger(LotteryContrller.class);
	@Autowired
	private WeifareSSQ weifareSSQ;
	@Autowired
	private NetData netData;
	@Autowired
	private AllBalls allBalls;
	@Autowired
	private SsqServiceImpl ssqService;
	private static LotterySsq lot =null;
	private static LotterySsq newLOt=null;
	@Value("${SSQ}")
	private String  ne;
	/**
	 * 随机彩票投注
	 * @return
	 */
	@GetMapping("/sell")
	public String randomProduct() {
		System.out.println(ne);
		String lottery = weifareSSQ.randomLottery();
		return lottery;
	}
	/**
	 * 算法选取彩票投注
	 * @return
	 */
	@GetMapping("/sell/ap")
	public String algorithmProduct() {
		LotterySsq lotterySsq = weifareSSQ.drawPrizeForNew();
		//精妙算法
		WeifareSSQ.algorithmForBefourNo(lotterySsq.getRedareal());
//		ssqService.queryForRed()

		return null;
	}
	/**
	 * 手工爬取数据存储
	 * @returnΩ
	 */
	@GetMapping("/pa/one")
	@Transactional(rollbackFor = Exception.class)
	public LotterySsq paData() {
		newLOt = netData.getNewLOt();
		if(newLOt!=null){
			lot = ssqService.getLot(newLOt.getVersionid());
		}
		if(!StringUtils.isEmpty(lot)){
			LotterySsq lotterySsq = weifareSSQ.drawPrizeForNew();
			if(!StringUtils.isEmpty(lotterySsq)){
				lotterySsq.setFlag(LotSticData.WQSTATUS);
				ssqService.update(lotterySsq);
			}
			newLOt.setInputdate(new Date());
			newLOt.setFlag(LotSticData.ZXSTATUS);
			ssqService.insert(newLOt);
		}
		return newLOt;
	}
	/**
	 * 手工爬取数据更新校对
	 * @return
	 */
	@GetMapping("/pa/all_list")
	public void paAllData() {
		try {
			allBalls.netAllLotData(1,LotSticData.UPDATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 最新开奖号码
	 * @return
	 */
	@GetMapping("/ln")
	public String latestLotteryNo() {
		LotterySsq lotterySsq = weifareSSQ.drawPrizeForNew();
		logger.info("获取最新开奖数据："+lotterySsq.toString());
		return weifareSSQ.formatLot(lotterySsq.getRedareal(), lotterySsq.getBuleareal());
	}
	/**
	 * 修改
	 * @return
	 */
	@GetMapping("/up")
	public int update(String no) {
		int update=0;
		LotterySsq lotterySsq = weifareSSQ.drawPrizeForNew();
		if(!StringUtils.isEmpty(lotterySsq)){
			lotterySsq.setFlag(LotSticData.WQSTATUS);
			 update =ssqService.update(lotterySsq);
		}
		return update;
	}

}
