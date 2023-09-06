/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.bank.face;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class sha {

	public static void main(String[] args) {

		try {
			// �ļ�����
			byte[] fileContent = readFile("c:/a.txt");
			// �����ļ�ժҪ
			byte[] shaByte = MessageDigest.getInstance("SHA-256").digest(fileContent);
			// �ļ�ժҪ16���Ʊ�ʾ(��д)
			String hexStr = byte2Hex(shaByte);
			// ǩ���ַ���
			String singStr = SignOpStep.getNodeValue((new SignOpStep(hexStr)).sign(), "signed_data");
			// ���������sha����
			String sha = BOS_URLencode(singStr);
			System.out.println("��������ϵͳ��sha����:" + sha);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	private static byte[] readFile(String file) {
		ByteOutputStream bout = new ByteOutputStream();
		try {
			File f = new File(file);
			int b = 0;
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
			while ((b = br.read()) != -1) {
				bout.write(b);
			}
			br.close();

		} catch (Exception e) {
		}
		return bout.toByteArray();
	}

	public static String byte2Hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		// ע�⣬�˴�Ҫ��16����ת���ɴ�д
		return hs.toString().toUpperCase();
	}

	private static String BOS_URLencode(String orgStr) {
		String newStr = null;
		try {
			newStr = java.net.URLEncoder.encode(orgStr, "GBK");
		} catch (Exception e) {

			e.printStackTrace();
		}

		return newStr;
	}
}
