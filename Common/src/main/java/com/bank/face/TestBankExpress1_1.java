/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.bank.face;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * ��ʾ������
 * 1:��ѯ�࣬
 * 2:ת���࣬
 * 3:�ļ��ϴ�
 * 4���ļ�����
 * ���ִ��밸���������ο�
 * </pre>
 * */
public class TestBankExpress1_1 {
	protected String sendData = "";

	protected String http = "http";

	protected String ip = "127.0.0.1";

	protected String userID = "9892327";
	protected String userPWD = "245771";

	protected String port = "7071";

	protected String split = "&";

	protected String account = "";

	protected String sessionid = "";

	protected String serialNo = "";

	protected java.net.HttpURLConnection urlConnection = null;

	protected String sendDataForBatchQueryBalanceCurrentOp = "";// �˻�����ѯ
	protected String transferInner1_1Op = "transferInner1_1Op";// ���ڹ�ת��ת��

	protected String CebankUserLogonOpForSign = ""; // ��¼

	/** ��½���� */
	public String logon() {
		this.sendData = this.CebankUserLogonOpForSign;
		System.out.println(CebankUserLogonOpForSign);
		String responsorUrl = this.http + "://" + this.ip + ":" + this.port + "/CM/APISessionReqServlet?";// ��½
		System.out.println(responsorUrl);
		try {
			java.net.URL aURL = new java.net.URL(responsorUrl);

			urlConnection = (java.net.HttpURLConnection) aURL.openConnection();

			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("User-Agent", "compatible; MSIE 5.0;");
			// urlConnection.setRequestProperty("User-Agent", "HTTP");
			urlConnection.connect();
			if (sendData != null && sendData.trim().length() != 0) {
				urlConnection.getOutputStream().write(sendData.getBytes());
			}

			int resCode = urlConnection.getResponseCode();
			int contentLen = urlConnection.getContentLength();

			java.io.DataInputStream in = new java.io.DataInputStream(urlConnection.getInputStream());

			byte buffer[] = new byte[contentLen];

			int len = 0;

			while (len < contentLen) {
				int remainedLen = contentLen - len;
				if (remainedLen > 1024)
					remainedLen = 1024;
				int readLen = in.read(buffer, len, remainedLen);
				if (readLen == -1 || readLen == 0) {
					break;
				}

				len = len + readLen;
			}

			urlConnection.disconnect();
			urlConnection = null;
			String a = new String(buffer, 0, contentLen);
			System.out.println(a);
			return a;

		} catch (Throwable e) {
			e.printStackTrace();
			if (urlConnection != null) {
				try {
					urlConnection.disconnect();
					urlConnection = null;
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		} finally {
			if (urlConnection != null) {
				try {
					urlConnection.disconnect();
					urlConnection = null;
				} catch (Exception ee) {
				}
			}
		}
		return "";
	}

	/** һ�㽻�� */
	public String hiscomm(String sendDatatemp) {
		this.sendData = sendDatatemp;
		String responsorUrl = this.http + "://" + this.ip + ":" + this.port + "/CM/APIReqServlet?";

		try {
			System.out.println(sendDatatemp);
			java.net.URL aURL = new java.net.URL(responsorUrl);

			urlConnection = (java.net.HttpURLConnection) aURL.openConnection();

			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			// urlConnection.setRequestProperty("User-Agent", "MSIE");
			urlConnection.setRequestProperty("User-Agent", "compatible; MSIE 5.0;");
			urlConnection.connect();
			if (sendData != null && sendData.trim().length() != 0) {
				OutputStream output = urlConnection.getOutputStream();
				output.write(sendData.getBytes("GBK"));
				output.flush();
				output.close();
			}

			int resCode = urlConnection.getResponseCode();
			int contentLen = urlConnection.getContentLength();

			java.io.DataInputStream in = new java.io.DataInputStream(urlConnection.getInputStream());

			byte buffer[] = new byte[contentLen];

			int len = 0;
			long start = System.currentTimeMillis();
			while (len < contentLen) {
				int remainedLen = contentLen - len;
				if (remainedLen > 1024)
					remainedLen = 1024;

				int readLen = in.read(buffer, len, remainedLen);

				if (readLen == -1 || readLen == 0) {
					break;
				}

				len = len + readLen;
			}
			long end = System.currentTimeMillis();
			System.out.println("������ɣ���ʱ��" + (end - start) + "����");
			System.out.println("total nums=" + contentLen);
			urlConnection.disconnect();
			urlConnection = null;
			String a = new String(buffer, 0, contentLen, "GBK");
			return a;

		} catch (Throwable e) {
			e.printStackTrace();
			if (urlConnection != null) {
				try {
					urlConnection.disconnect();
					urlConnection = null;
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		} finally {
			if (urlConnection != null) {
				try {
					urlConnection.disconnect();
					urlConnection = null;
				} catch (Exception ee) {
				}
			}
		}
		return "";
	}

	public void doTest() {
		String longonresult = null;
		String result = null;

		// ��¼
		serialNo = String.valueOf(Math.round(Math.random() * 10000)) + System.currentTimeMillis();
		CebankUserLogonOpForSign = "opName=CebankUserLogon1_1Op"
				+ split
				+ "reqData="
				+ BOS_URLencode(SignOpStep.getNodeValue((new SignOpStep("<?xml version=\"1.0\" encoding=\"GBK\"?>"
						+ "<BOSEBankData>" + "<opReq>" + "<serialNo>" + serialNo + "</serialNo>" + "<reqTime>"
						+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "</reqTime>" + "<ReqParam>" + "<userID>"
						+ userID + "</userID>" + "<userPWD>" + userPWD + "</userPWD>" + "</ReqParam>" + "</opReq>"
						+ "</BOSEBankData>")).sign(), "signed_data"));
		System.out.println(CebankUserLogonOpForSign);
		longonresult = logon();
		System.out.println("�ʺŵ�½�����=" + longonresult + "=");
		if (longonresult.substring(0, 1).equals("<")) {// û��
			System.out.println("û�е�½��ȷ:" + longonresult);
		} else {
			// ��ȡsessionid
			sessionid = longonresult.substring(0, 40);
			// ���ɽ������к�
			serialNo = String.valueOf(Math.round(Math.random() * 10000)) + System.currentTimeMillis();
			try {

				// ��ѯ��ͨ�ʻ�����ѯһ��/�����˻�����
				serialNo = String.valueOf(Math.round(Math.random() * 10000)) + System.currentTimeMillis();
				sendDataForBatchQueryBalanceCurrentOp = "dse_sessionId=" + sessionid + split
						+ "opName=batchQueryBalanceCurrent1_1Op" + split + "reqData="
						+ "<?xml version=\"1.0\" encoding=\"GBK\"?>" //
						+ "<BOSEBankData>" + "<opReq>" + "<serialNo>" + serialNo + "</serialNo>" //
						+ "<reqTime>" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "</reqTime>" //
						+ "<ReqParam>" //
						+ "<SUMU>1</SUMU>"//
						+ "<opReqSet><opRequest>" //
						+ "<ACNO>31600703002050179</ACNO>" //
						+ "</opRequest></opReqSet></ReqParam></opReq>"//
						+ "</BOSEBankData>";
				System.out.println(sendDataForBatchQueryBalanceCurrentOp);
				result = hiscomm(sendDataForBatchQueryBalanceCurrentOp);
				System.out.println("�˺�����ѯ���أ�" + result);

				// ����ת��
				// serialNo = String.valueOf(Math.round(Math.random() * 10000))
				// + System.currentTimeMillis();
				// transferInner1_1Op = "dse_sessionId="
				// + sessionid
				// + split
				// + "opName=transferInner1_1Op"
				// + split
				// + "reqData="
				// + BOS_URLencode(SignOpStep.getNodeValue((new
				// SignOpStep("<?xml version=\"1.0\" encoding=\"GBK\"?>"
				// + "<BOSEBankData>" + "<opReq>" //
				// + "<serialNo>"
				// + serialNo
				// + "</serialNo>"
				// + "<reqTime>"
				// + "20080902"
				// + "</reqTime>"
				// + "<ReqParam>"
				// + "<ACNO>"
				// + account
				// + "</ACNO>"
				// + "<OPAC>"
				// + "31673500009000428"
				// + "</OPAC>"
				// + "<TRAM>"
				// + "1.08"
				// + "</TRAM>"
				// + "<NAME>"
				// + "����ת�˲����ʺ�����"
				// + "</NAME>"
				// + "<USAG>"
				// + ""
				// + "</USAG>"
				// + "<REMK>"
				// + ""
				// + "</REMK>"
				// + "</ReqParam>"
				// + "</opReq>"
				// + "</BOSEBankData>")).sign(), "signed_data"));
				// result = hiscomm(transferInner1_1Op);
				// System.out.println("[����ת��]���أ�" + result);

				// // �ļ��ϴ�
				// String fileName = "c:/a.txt";
				// System.out.println("�ϴ��ļ�:" + fileName);
				// String ret = FileTest.uploadFile(fileName);
				// System.out.println("�ļ��ϴ����:" + ret);

				// �ļ�����
				// System.out.println("��ʼ�����ļ�");
				// String ret = FileTest.downloadFile("", "", "");
				// System.out.println("�ļ����ؽ��:" + ret);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String BOS_URLencode(String orgStr) {
		String newStr = null;
		try {
			newStr = java.net.URLEncoder.encode(orgStr, "GBK");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return newStr;
	}

	public static void main(String[] arg) {
		new TestBankExpress1_1().doTest();
	}
}
