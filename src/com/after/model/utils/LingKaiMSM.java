package com.after.model.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LingKaiMSM {
	/**
	 * http get���� ���ͷ��� ��������ͬ�� ����ֵ>0 �ύ�ɹ�
	 * 
	 * @param CorpID
	 *            �˺�
	 * @param Pwd
	 *            ����
	 * @param Mobile
	 *            �ֻ�����
	 * @param Content
	 *            ��������
	 * @param send_time
	 *            ��ʱ���͵�ʱ�䣻����Ϊ�գ�Ϊ��ʱΪ��ʱ����
	 * @return ����ֵ
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static int sendSMSGet(String CorpID, String Pwd, String Mobile, String Content, String send_time) throws MalformedURLException,
			UnsupportedEncodingException {
		URL url = null;
		String send_content = URLEncoder.encode(Content.replaceAll("<br/>", " "), "GBK");// ��������
		url = new URL("https://sdk2.028lk.com/sdk2/BatchSend2.aspx?CorpID=" + CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile + "&Content=" + send_content
				+ "&Cell=&SendTime=" + send_time);
		BufferedReader in = null;
		int inputLine = 0;
		try {
			System.out.println("��ʼ���Ͷ����ֻ�����Ϊ ��" + Mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (Exception e) {
			System.out.println("�����쳣,���Ͷ���ʧ�ܣ�");
			inputLine = -2;
		}
		System.out.println("�������Ͷ��ŷ���ֵ��  " + inputLine);
		return inputLine;
	}

	/**
	 * Hppt POST�����ͷ��� ����ֵ>0 Ϊ �ύ�ɹ�
	 * 
	 * @param CorpID
	 *            �˺�
	 * @param Pwd
	 *            ����
	 * @param Mobile
	 *            �绰����
	 * @param Content
	 *            ��������
	 * @param send_time
	 *            ��ʱ����ʱ�䣬Ϊ��ʱ��Ϊ��ʱ����
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static int sendSMSPost(String CorpID, String Pwd, String Mobile, String Content, String send_time) throws MalformedURLException,
			UnsupportedEncodingException {

		String inputLine = "";
		int value = -2;
		DataOutputStream out = null;
		InputStream in = null;
		String send_content = URLEncoder.encode(Content.replaceAll("<br/>", " "), "GBK");// ��������

		String strUrl = "https://sdk2.028lk.com/sdk2/BatchSend2.aspx";
		String param = "CorpID=" + CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile + "&Content=" + send_content + "&Cell=&SendTime=" + send_time;

		try {

			inputLine = sendPost(strUrl, param);

			System.out.println("��ʼ���Ͷ����ֻ�����Ϊ ��" + Mobile);

			value = new Integer(inputLine).intValue();

		} catch (Exception e) {

			System.out.println("�����쳣,���Ͷ���ʧ�ܣ�");
			value = -2;

		}

		System.out.println(String.format("����ֵ��%d", value));

		return value;
	}

	/**
	 * ��ȡ�ظ���Ϣ
	 * 
	 * @param corpId
	 *            �˺�
	 * @param pwd
	 *            ����
	 * @return �ظ���Ϣ���
	 */
	public static String getReplyMsg(String corpId, String pwd) {

		String result = "";
		String msg = "";
		String strUrl = "https://sdk2.028lk.com/sdk2/Get.aspx";
		String param = "CorpID=" + corpId + "&Pwd=" + pwd;

		try {

			result = sendPost(strUrl, param);
			// result
			// ="||15281008413#123#2017-01-04||15281008412#123#2017-01-04||15281008413#121#2017-01-04||15281008410#123#2017-01-04||";

			if (result == null || result.equals("") || result.length() == 0) {
				msg = "û��������Ϣ";
			}

			if (result.equals("-1")) {
				msg = "�˺�δע��";
			} else if (result.equals("-2")) {
				msg = "��������";
			} else if (result.equals("-3")) {
				msg = "�������";
			} else if (result.equals("-101")) {
				msg = "���ýӿ�Ƶ�ʹ���(����30s����һ��)";
			} else if (result.equals("-100")) {
				msg = "IP������";
			} else if (result.equals("-102")) {
				msg = "�˺ź�����";
			} else if (result.equals("-103")) {
				msg = "IPδ����";

			} else {

				msg = String.format("��ȡ�ɹ���%s", result);

				result = result.replace("||", "|");

				String[] strArray = result.split("\\|");
				String outContent = "";

				int i = 0;

				for (String str : strArray) {

					if (str.equals(""))
						continue;

					String[] strConArr = str.split("#");

					if (strConArr == null || strConArr.length <= 0)
						continue;

					i = i + 1;

					outContent = String.format("��%d���ظ�,�ֻ����룺%s,�ظ����ݣ�%s,�ظ�ʱ�䣺%s", i, strConArr[0], strConArr[1], strConArr[2]);

					System.out.println(outContent);

				}
			}

			System.out.println(msg);

		} catch (Exception e) {

			System.out.println("�����쳣,�ظ���Ϣ��ȡʧ�ܣ�");

		}

		return result;
	}

	/**
	 * �˺�����ѯ
	 * 
	 * @param corpId
	 *            �˺�
	 * @param pwd
	 *            ����
	 * @return
	 */
	public static String getSelSum(String corpId, String pwd) {

		String result = "";
		String msg = "";
		String strUrl = "https://sdk2.028lk.com/sdk2/SelSum.aspx";
		String param = "CorpID=" + corpId + "&Pwd=" + pwd;

		try {

			result = sendPost(strUrl, param);

			if (result.equals("-1")) {
				msg = "�˺�δע��";
			} else if (result.equals("-2")) {
				msg = "��������";
			} else if (result.equals("-3")) {
				msg = "�������";
			} else if (result.equals("-101")) {
				msg = "���ýӿ�Ƶ�ʹ���(����30s����һ��)";
			} else if (result.equals("-100")) {
				msg = "IP������";
			} else if (result.equals("-102")) {
				msg = "�˺ź�����";
			} else if (result.equals("-103")) {
				msg = "IPδ����";
			} else {
				msg = String.format("��ȡ�ɹ���%s", result);
			}

			System.out.println(msg);

		} catch (Exception e) {

			System.out.println("�����쳣,�ظ���Ϣ��ȡʧ�ܣ�");

		}

		return result;
	}

	/**
	 * ��ȡ��ֹ���͵ĺ���
	 * 
	 * @param corpId
	 *            �˺�
	 * @param pwd
	 *            ����
	 * @return
	 */
	public static String getNotSend(String corpId, String pwd) {

		String result = "";
		String msg = "";
		String strUrl = "https://sdk2.028lk.com/sdk2/NotSend.aspx";
		String param = "CorpID=" + corpId + "&Pwd=" + pwd;

		try {

			result = sendPost(strUrl, param);

			if (result.equals("-1")) {
				msg = "�˺�δע��";
			} else if (result.equals("-2")) {
				msg = "��������";
			} else if (result.equals("-3")) {
				msg = "�������";
			} else if (result.equals("-101")) {
				msg = "���ýӿ�Ƶ�ʹ���(����30s����һ��)";
			} else if (result.equals("-100")) {
				msg = "IP������";
			} else if (result.equals("-102")) {
				msg = "�˺ź�����";
			} else if (result.equals("-103")) {
				msg = "IPδ����";
			} else {
				msg = result;
			}

			System.out.println(msg);

		} catch (Exception e) {

			System.out.println("�����쳣,��ֹ�����ȡʧ�ܣ�");

		}

		return result;

	}

	/**
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return ������Զ����Դ����Ӧ���
	 */
	public static String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
