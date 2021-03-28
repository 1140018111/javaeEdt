package com.timer;

import com.data.interdata.lotterydata.NetData;
import com.data.interdata.lotterydata.SpringUtil;
import com.data.loderdate.LotSticData;
import com.generator.lottery.entity.LotterySsq;
import com.generator.lottery.service.impl.SsqServiceImpl;
import com.lotteryms.kinds.WeifareSSQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author DL
 * @diescription com.timer
 * @dis_projectdxl
 * @create 2021-03-27
 */
@Component
@Configurable
@EnableScheduling
public class LotteryTimer {
	@Autowired
	private NetData netData;
	/**
	 * SUN, MON, TUE, WED, THU, FRI and SAT
	 * 双色球定时获取最新开奖号
	 * 每周2，4，7  21点，22点 20分进行爬取数据
	 */
	@Scheduled(cron = "0 20 21,22 * * 2,4,7")
	@Transactional(rollbackFor = Exception.class)
	public  void report(){
		SsqServiceImpl ssqService = SpringUtil.getBean(SsqServiceImpl.class);
		WeifareSSQ weifareSSQ = SpringUtil.getBean(WeifareSSQ.class);
		LotterySsq newLOt = netData.getNewLOt();
		LotterySsq lot =null;
		if(newLOt!=null){
			 lot = ssqService.getLot(newLOt.getVersionid());
		}
		if(StringUtils.isEmpty(lot)){
			LotterySsq lotterySsq = weifareSSQ.drawPrizeForNew();
			if(StringUtils.isEmpty(lotterySsq)){
				lotterySsq.setFlag(LotSticData.WQSTATUS);
				ssqService.update(lotterySsq);
			}
			newLOt.setInputdate(new Date());
			newLOt.setFlag(LotSticData.ZXSTATUS);
			ssqService.insert(newLOt);
		}
	}
}
