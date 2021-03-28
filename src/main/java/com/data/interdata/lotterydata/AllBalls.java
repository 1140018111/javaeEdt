package com.data.interdata.lotterydata;

import com.generator.lottery.entity.LotterySsq;
import com.generator.lottery.service.impl.SsqServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
@Component
public class AllBalls  {
	private final static Logger logger=LoggerFactory.getLogger(AllBalls.class);
	private final static String baseUrlPrefix = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_";;
	private final static String baseUrlSuffix = ".html";;
	private final static String homeUrl = "http://kaijiang.zhcw.com/zhcw/html/ssq/list_1.html";;
	private final static SimpleDateFormat formatMin = new SimpleDateFormat("yyyy-MM-dd");
	private static List<String> mStringBuffer =null;
	private static List<String> amountBuffer = null;
	private static List<String> dateSBuffer = null;
	private static List<String> idSBuffer = null;
	private static List<String> topSBuffer = null;
	private static List<String> twoSBuffer = null;
//	private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public  void netAllLotData() throws Exception {
		logger.info("正在获取...");
		String pageCountContent = getHtmlString(homeUrl);
		int pageCount = getPageCount(pageCountContent);
		try {
			if (pageCount > 0) {
				for (int i = 1; i <= pageCount; i++) {
					mStringBuffer = new ArrayList<String>();
				    amountBuffer = new ArrayList<String>();
				    dateSBuffer = new ArrayList<String>();
				    idSBuffer = new ArrayList<String>();
					topSBuffer = new ArrayList<String>();
					twoSBuffer = new ArrayList<String>();

					String url = baseUrlPrefix + i + baseUrlSuffix;
					//获取总页数
					String pageContent = getHtmlString(url);
					if (pageContent != null && !pageContent.equals("")) {
						//节点处理
						getOneTermContent(pageContent);
						//入库
						logger.info("===当前存储页："+i+" 开始入库===");
						inputData(pageCount,i);
						logger.info("===当前存储页："+i+" 入库完成===");
					} else {
						logger.info("===第" + i + "页丢失===");
					}
					try {
						Thread.sleep(4000);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			} else {
				logger.info("==结果页数为0==");
			}
		}finally {
//			threadPool.shutdown();
		}

		logger.info("==爬取完成！==");
	}

	/**
	 * 数据入库
	 * @param pageCount 总页数
	 * @param newPage   当前页
	 */
	private  void inputData(int pageCount,int newPage) {
		//赋值操作
		LotterySsq lotterySsq =null;
		ArrayList<LotterySsq>  lotterySsqs = new ArrayList<>();
		try {
			for (int  t= 0; t <idSBuffer.size() ; t++) {
				lotterySsq = new LotterySsq();
				lotterySsq.setVersionid(idSBuffer.get(t));
				String newNo = mStringBuffer.get(t).replaceAll(" ", "");
				String red = newNo.substring(0, 12);
				String bule = newNo.substring(12);
				lotterySsq.setRedareal(red);
				lotterySsq.setBuleareal(bule);
				lotterySsq.setExecutedate(formatMin.parse(dateSBuffer.get(t)));
				lotterySsq.setSalesamount(amountBuffer.get(t).replaceAll(",",""));
				lotterySsq.setInputdate(new Date());
				lotterySsq.setFlag("0");
				lotterySsq.setReserve(topSBuffer.get(t));
				lotterySsq.setSpare(twoSBuffer.get(t));
				lotterySsqs.add(lotterySsq);
			}
			for (int i = 0; i < lotterySsqs.size(); i++) {
				LotterySsq record = lotterySsqs.get(i);
				SsqServiceImpl ssqServiceImpl = (SsqServiceImpl)SpringUtil.getBean("ssqServiceImpl");
				ssqServiceImpl.insert(record);
			}
			logger.info(lotterySsqs.toString());
		} catch (Exception e) {
			String exce="数据存储异常，执行位置：总页数："+pageCount+" 当前存储页："+newPage+"!!!";
			e.printStackTrace();
			throw new RuntimeException(exce);
		}
	}

	/**
	 * 获取总页数
	 *
	 * @param result
	 */
	private static int getPageCount(String result) {
		String regex = "\\d+\">末页";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(result);
		String[] splits = null;
		while (matcher.find()) {
			String content = matcher.group();
			splits = content.split("\"");
			break;
		}
		if (splits != null && splits.length == 2) {
			String countString = splits[0];
			if (countString != null && !countString.equals("")) {
				return Integer.parseInt(countString);
			}

		}
		return 0;
	}

	/**
	 * 获取网页源码
	 *
	 * @return
	 */
	private  String getHtmlString(String targetUrl) {
		String content = null;

		HttpURLConnection connection = null;
		try {
			URL url = new URL(targetUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)");
			connection
					.setRequestProperty(
							"Accept",
							"image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
			connection.setRequestProperty("Accept-Language", "zh-cn");
			connection.setRequestProperty("UA-CPU", "x86");
			// 为什么没有deflate呢
			connection.setRequestProperty("Accept-Encoding", "gzip");
			connection.setRequestProperty("Content-type", "text/html");
			// keep-Alive，有什么用呢，你不是在访问网站，你是在采集。嘿嘿。减轻别人的压力，也是减轻自己。
			connection.setRequestProperty("Connection", "close");
			// 不要用cache，用了也没有什么用，因为我们不会经常对一个链接频繁访问。（针对程序）
			connection.setUseCaches(false);
			connection.setConnectTimeout(6 * 1000);
			connection.setReadTimeout(6 * 1000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Charset", "utf-8");

			connection.connect();

			if (200 == connection.getResponseCode()) {
				InputStream inputStream = null;
				if (connection.getContentEncoding() != null
							&& !connection.getContentEncoding().equals("")) {
					String encode = connection.getContentEncoding()
											.toLowerCase();
					if (encode != null && !encode.equals("")
								&& encode.contains("gzip")) {
						inputStream = new GZIPInputStream(
								connection.getInputStream());
					}
				}

				if (null == inputStream) {
					inputStream = connection.getInputStream();
				}

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream, "utf-8"));
				StringBuilder builder = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					builder.append(line).append("\n");
				}
				content = builder.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

		return content;
	}

	private  void getOneTermContent(String pageContent) throws InterruptedException {
		String regex = "<tr>[\\s\\S]+?</strong></td>";
		String regexTwo= "<strong class=\"rc\">[\\s\\S]+?</strong>";
		String regexTop = "<td align=\"left\" style=\"color:#999;\"><strong>[\\s\\S]+?</strong>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pageContent);
		while (matcher.find()) {
			String oneTermContent = matcher.group();
			getOneTermContentext(oneTermContent);
		}

		Pattern patternTop = Pattern.compile(regexTop);
		Matcher matcherTop = patternTop.matcher(pageContent);
		while (matcherTop.find()) {
			String content = matcherTop.group();
			String ballNumber = content.substring(1, content.length() - 1);
			getOneTermNumbersMin(ballNumber, AllBalls.LotterEnum.TOP);
		}

		Pattern patternTwo = Pattern.compile(regexTwo);
		Matcher matcherTwo = patternTwo.matcher(pageContent);
		while (matcherTwo.find()) {
			String content = matcherTwo.group();
			String ballNumber = content.substring(1, content.length() - 1);
			getOneTermNumbersMin(ballNumber, LotterEnum.TWO);
		}


//		System.out.println(patternTop);
//
//		ThreadLot top = new ThreadLot(matcherTop, LotterEnum.TOP);
//		ThreadLot two = new ThreadLot(matcherTwo, LotterEnum.TWO);
//		threadPool.execute(top);
//		top.join();
//		threadPool.execute(two);
//		two.join();


	}
	private  void getOneTermContentext(String pageContent) throws InterruptedException {
		String no = "<td align=\"center\" style=\"padding-left:10px;\">[\\s\\S]+?</em></td>";
		String date= "<td align=\"center\">[\\s\\S]+?</td>";
		String amount= "<strong>[\\s\\S]+?</strong>";

		Pattern patternNo = Pattern.compile(no);
		Pattern patternDate = Pattern.compile(date);
		Pattern patternAmount = Pattern.compile(amount);
		Matcher m1 = patternNo.matcher(pageContent);
		Matcher m2 = patternDate.matcher(pageContent);
		Matcher m3 = patternAmount.matcher(pageContent);
		int i = 1;
		while (m1.find()) {
			String c1 = m1.group();
			String ballNu = c1.substring(1, c1.length() - 1);
			getOneTermNumbers(ballNu);
		}
		while (m2.find()) {
			String c2 = m2.group();
			String ballD = c2.substring(1, c2.length() - 1);
			if (i % 2 == 0) {
				getOneTermNumbersId(ballD);
			} else {
				getOneTermNumbersDate(ballD);
			}
			i++;
		}
		while (m3.find()) {
			String c3 = m3.group();
			String balla = c3.substring(1, c3.length() - 1);
			getOneTermNumbersAmount(balla);
		}


//		ThreadLot n = new ThreadLot(m1, LotterEnum.REDBULE);
//		ThreadLot d = new ThreadLot(m2, LotterEnum.IDDATE);
//		ThreadLot a = new ThreadLot(m3, LotterEnum.AMOUT);
//		threadPool.execute(n);
//		n.join();
//		threadPool.execute(d);
//		d.join();
//		threadPool.execute(a);
//		a.join();
	}

	/**
	 * 处理开奖号
	 * @param oneTermContent
	 */
	public  void getOneTermNumbers(String oneTermContent) {
		//数字正则
		String regex = ">\\d+<";
		StringBuffer buffer;
		buffer = new StringBuffer();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(oneTermContent);
		while (matcher.find()) {
			String content = matcher.group();
			String ballNumber = content.substring(1, content.length() - 1);
			buffer.append(ballNumber);
		}
		mStringBuffer.add(buffer+"");
	}

	/**
	 * 处理开奖日期
	 * @param oneTermContent
	 */
	public  void getOneTermNumbersDate(String oneTermContent) {
		//数字正则
		String regex = ">[\\s\\S]+?<";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(oneTermContent);
		while (matcher.find()) {
			String content = matcher.group();
			String ballNumber = content.substring(1, content.length() - 1);
			dateSBuffer.add(ballNumber);
		}
	}

	/**
	 * 处理开奖期号
	 * @param oneTermContent
	 */
	public  void getOneTermNumbersId(String oneTermContent) {
		//数字正则
		String regex = ">[\\s\\S]+?<";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(oneTermContent);
		while (matcher.find()) {
			String content = matcher.group();
			String ballNumber = content.substring(1, content.length() - 1);
			idSBuffer.add(ballNumber);
		}
	}

	/**
	 * 处理销售金额
	 * @param oneTermContent
	 */
	public  void getOneTermNumbersAmount(String oneTermContent) {
		//数字正则
		String regex = ">[\\s\\S]+?<";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(oneTermContent);
		while (matcher.find()) {
			String content = matcher.group();
			String ballNumber = content.substring(1, content.length() - 1);
			amountBuffer.add(ballNumber);
		}
	}
	/**
	 * 处理获奖人数
	 * @param oneTermContent
	 * @param top
	 */
	public  void getOneTermNumbersMin(String oneTermContent, LotterEnum top) {
		switch (top){
			case TOP:
				String regexw = "<strong>\\d+<";
				Pattern patternTop = Pattern.compile(regexw);
				Matcher mt = patternTop.matcher(oneTermContent);
				while (mt.find()) {
					String t = mt.group();
					getOneTermNumbersCount(t,LotterEnum.TOP);
				}
				break;
			case TWO:
				String regext = "class=\"rc\">\\d+<";
				Pattern patternTwo = Pattern.compile(regext);
				Matcher mw = patternTwo.matcher(oneTermContent);
				while (mw.find()) {
					String two = mw.group();
					getOneTermNumbersCount(two,LotterEnum.TWO);
				}
				break;
			default:
				break;

		}
	}

	/**
	 * 处理获奖人数
	 * @param oneTermContent
	 * @param top
	 */
	public  void getOneTermNumbersCount(String oneTermContent, LotterEnum top) {
		String regex = ">[\\s\\S]+?<";
		switch (top){
			case TOP:

				Pattern patternTop = Pattern.compile(regex);
				Matcher mt = patternTop.matcher(oneTermContent);
				while (mt.find()) {
					String t = mt.group();
					String to = t.substring(1, t.length() - 1);
					topSBuffer.add(to);
				}
				break;
			case TWO:
				Pattern patternTwo = Pattern.compile(regex);
				Matcher mw = patternTwo.matcher(oneTermContent);
				while (mw.find()) {
					String two = mw.group();
					String tw = two.substring(1, two.length() - 1);
					twoSBuffer.add(tw);
				}
				break;
			default:
				break;

		}


	}

	public enum LotterEnum {
		AMOUT("销售金额"), IDDATE("开奖期次/日期"), REDBULE("开奖号"), TOP("一等奖人数"), TWO("二等奖人数");
		private final String name;


		LotterEnum(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}