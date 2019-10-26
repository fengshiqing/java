package com.kunning.commons.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 注意以下两点：
 * File file = new File("路径");
 * file.createNewFile()；
 *
 */
public class Test1 {

	public static void main(String[] args) throws IOException {
		File file = new File("d:" + File.separator + "test1.txt");//为增加可移植性，建议使用File.separator
		 				//通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例，仅仅是在内存中创建了一个实例对象。
		
		//创建文件
//		try {
//			if(file.createNewFile()) {//当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。
//				System.out.println("d:" + File.separator + "test1.txt" + "文件成功创建");
//			} else {
//				System.out.println("d:" + File.separator + "test1.txt" + "文件已存在");
//			}
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
		
		//删除文件
//		if(file.exists()) {
//			if(file.delete()) {
//				System.out.println("d:" + File.separator + "test1.txt" + "文件已经成功删除");
//			} else {
//				System.out.println("d:" + File.separator + "test1.txt" + "文件删除操作失败");
//			}
//		} else {
//			System.out.println("d:" + File.separator + "test1.txt" + "不存在此文件");
//		}
		
		//创建文件夹
//		File file_mk = new File("d:" + File.separator + "test文件夹");
//		file_mk.mkdir();//创建文件夹
		
		//列出指定目录下的全部文件（包括文件和文件夹）
		File file_all = new File("d:" + File.separator);
		String[] strArr1 = file_all.list();//包括文件和文件夹
		File[] strArr2 = file_all.listFiles();//列出带路径的文件和文件夹
		for(String i:strArr1) {
			System.out.println(i);
		}
		for(File i:strArr2) {
			System.out.println(i);
		}
		
	}
	
	@Test
	public void test_1() throws IOException {
		// 1、读取本地硬盘上的文件，并输出到控制台
		File file = new File("d:" + File.separator + "fengshiqing.txt");
		FileInputStream fis = new FileInputStream(file);
		// 3.实现文件的复制
		byte[] b = new byte[1024];
		while ((fis.read(b)) != -1) {
			System.out.write(b);
		}
		fis.close();
	}

}
