package com.data.interdata.otherresouse;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author DL
 * @diescription com.date
 * @dis_projectdxl
 * @create 2021-03-13
 */
public class AppUrlMovie {
	public static void main(String[] args) {
		System.out.println("开始");
		long start = System.currentTimeMillis();
		/**
		 *  从json中获取到的 url
		 *  请获取后手动填写
		 */
//		String lastUrl = "http://upos-hz-mirrorkodou.acgvideo.com/upgcxcode/22/48/111804822/111804822-1-80.flv?e=ig8euxZM2rNcNbu37zUVhoManwuBhwdEto8g5X10ugNcXBlqNxHxNEVE5XREto8KqJZHUa6m5J0SqE85tZvEuENvNo8g2ENvNo8i8o859r1qXg8xNEVE5XREto8GuFGv2U7SuxI72X6fTr859r1qXg8gNEVE5XREto8z5JZC2X2gkX5L5F1eTX1jkXlsTXHeux_f2o859IB_&uipk=5&nbs=1&deadline=1570200690&gen=playurl&os=kodou&oi=1033761651&trid=c05e1a0ff69a47a08dbb4b39210cbd49u&platform=pc&upsig=03d0df9f30c814d21e70d4e2e1be2e17&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=28569524";
		String lastUrl ="https://upos-sz-mirrorcos.bilivideo.com/upgcxcode/22/48/111804822/111804822_nb2-1-80.flv?e=ig8euxZM2rNcNbuM7WdVhoMa7wUVhwdEto8g5X10ugNcXBlqNxHxNEVE5XREto8KqJZHUa6m5J0SqE85tZvEuENvNo8g2ENvNo8i8o859r1qXg8xNEVE5XREto8GuFGv2U7SuxI72X6fTr859r1qXg8gNEVE5XREto8z5JZC2X2gkX5L5F1eTX1jkXlsTXHeux_f2o859IB_&uipk=5&nbs=1&deadline=1615625906&gen=playurlv2&os=cosbv&oi=997037251&trid=4610cb2a543345c7922b757dd068e69eu&platform=pc&upsig=d6209764dae8bf88363eee91535734f8&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=480636994&orderid=1,3&agrr=1&logo=40000000";
//								 "String lastUrl ="https://www.bilibili.com/video/BV1jJ411S7xr?from=search&seid=2366231657539493916#__lottie_element_48";	//自定义文件名称
		String fileName = "a.mp4";
		downLoadMovie(lastUrl, fileName);

		long end = System.currentTimeMillis();
		System.out.println("完成 ");
		System.err.println("总共耗时：" + (end - start) / 1000 + "s");
	}

	public static void downLoadMovie(String blUrl, String fileName) {
		InputStream inputStream = null;
		try {
			URL url = new URL(blUrl);
			URLConnection urlConnection = url.openConnection();
			// 填需要爬取的av号
			urlConnection.setRequestProperty("Referer", "https://www.bilibili.com/video/BV1jJ411S7xr?from=search&seid=2366231657539493916");
			urlConnection.setRequestProperty("Sec-Fetch-Mode", "no-cors");
			urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
			urlConnection.setConnectTimeout(10 * 1000);
			inputStream = urlConnection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//定义路径
		String path = fileName;
		File file = new File(path);
		int i = 1;
		try {
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			byte[] bys = new byte[1024];
			int len = 0;
			while ((len = bis.read(bys)) != -1) {
				bos.write(bys, 0, len);
				System.out.println("写入第 " + i++ + "次");
			}
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
