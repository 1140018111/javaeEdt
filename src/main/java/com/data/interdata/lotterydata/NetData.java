package com.data.interdata.lotterydata;

import com.data.loderdate.LotSticData;
import com.generator.lottery.entity.LotterySsq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬取网络最新数据
 * @author DL
 * @diescription com.date
 * @dis_projectdxl
 * @create 2021-03-13
 */
@Component
@PropertySource(value = "classpath:otherConf/lotConfig.properties")
public class NetData {
	private static Logger logger = LoggerFactory.getLogger(NetData.class);
	private String urlAdd= LotSticData.SSQ;
	private LotterySsq lotterySsq = null;
	private SimpleDateFormat formatMin = new SimpleDateFormat("yyyyMMdd");
	public  LotterySsq getNewLOt() {
		InputStream inputStream =null;
		HttpURLConnection c1 = null;
		lotterySsq = new LotterySsq();
		int insert=0;
		try {
			//下载地址 双色球
			URL url = new URL(urlAdd);
			logger.info("爬取地址:"+urlAdd);
			//2.连接资源
			c1 = (HttpURLConnection)url.openConnection();
			inputStream = c1.getInputStream();
			byte[] bytes = new byte[1024];
			int len;
			while ((len=inputStream.read(bytes))!=-1){
			}
			String date = new String(bytes,"UTF-8");
			logger.info("爬取的数据:"+date);
			int star = date.indexOf("bor_rnone");
			int end = date.indexOf("</div>");
			String substring = date.substring(star, end);
			String red="<span class=\\'tit_red\\'>";
			String bule="<span class=\\'tit_bule\\'>";

			int indexA = substring.indexOf(red);
			int indexB = substring.indexOf(bule);
			int count = substring.indexOf("期",2);
			//截取红球
			String redArea = substring.substring(indexA,indexB);
			String outRed = redArea.replaceAll("[^(0-9)]", "");
			//截取红蓝区
			String blueArea = substring.substring(indexB);
			String outBlue = blueArea.replaceAll("[^(0-9)]", "");
			//截取期次
			String countQ = substring.substring(count-10,count).replaceAll("[^(0-9)]", "");
			//截取开奖时间
			String executeDate = substring.substring(count,count+25).replaceAll("[^(0-9)]", "");
			logger.info("爬取的数据日期:"+executeDate);
			lotterySsq.setBuleareal(outBlue);
			lotterySsq.setRedareal(outRed);
			lotterySsq.setVersionid(countQ);

			try {
				lotterySsq.setExecutedate(formatMin.parse(executeDate));
				logger.info("转换日期:"+formatMin.parse(executeDate));
			} catch (ParseException e) {
				throw new RuntimeException("爬取最新开奖号组装数据时日期转换异常！！！");
			}
			logger.info("整合数据模型:"+lotterySsq.toString());

		} catch (IOException  e) {
			e.printStackTrace();
			lotterySsq=null;
		}finally {
			try {
				if(inputStream!=null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(c1!=null) {
				c1.disconnect();
			}
		}
		return lotterySsq;
	}

}
