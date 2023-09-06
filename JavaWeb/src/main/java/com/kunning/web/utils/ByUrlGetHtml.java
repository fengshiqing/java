package com.kunning.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ByUrlGetHtml {

	public static void main(String[] args) throws Exception {

		List<String> strList = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			String url = "https://m.liepin.com/zhaopin/pn" + i + "/?keyword=%E5%A4%A7%E6%95%B0%E6%8D%AE";
			String htmlSourceCode = getHtmlSourceCode(url, "utf-8");
			Document document = Jsoup.parse(htmlSourceCode);// 转换为DOM树
			Elements jobList = document.getElementsByClass("job-card");
			for (Element job : jobList) {
				String joburl = job.getElementsByClass("flexbox").get(0).getElementsByTag("a").attr("href");

				if (joburl.endsWith(".shtml")) {
					strList.add(joburl);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (String str : strList) {
			String jobDesc = getHtmlSourceCode(str, "utf-8");
			Document document = Jsoup.parse(jobDesc);// 转换为DOM树
			Element jobcontent = document.getElementsByClass("content-word").get(0);
			String[] split = jobcontent.text().split("[^a-zA-Z]+");
			for (String word : split) {
				sb.append(word.toLowerCase()).append("\t");
			}
		}
		
		FileOutputStream fos = new FileOutputStream(new File("D:\\a.txt")); 
		fos.write(sb.toString().getBytes());
		
		fos.flush();
		fos.close();
	}

	/**
	 * 获取网页HTML的源代码
	 * 
	 * @throws Exception
	 */
	private static String getHtmlSourceCode(String urlStr, String charset) throws Exception {
		URLConnection urlConn = new URL(urlStr).openConnection();// 建立并打开连接
		InputStream in = urlConn.getInputStream();// 获取源代码 IO流，就是管道
		// in.read();// 不用这个流的这个方法，原因有二：1、一个字节一个字节的读取，很慢；2、频繁的读取磁盘，给磁盘造成很大的压力
		BufferedReader bfr = new BufferedReader(new InputStreamReader(in, charset));// 建立缓冲流
		// 4.3、从缓冲流中读取数据
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = bfr.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

}
