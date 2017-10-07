package cn.tax.core.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Constant {
	public static String USER_SESSION_NAME = "user";
	public static String USER_ERROR = "error";
	/*----------------------ϵͳȨ�޼���--------------------------*/
	/**
	 * ��������
	 */
	public static String PRIVILEGE_XZGL = "xzgl"; 
	/**
	 * ���ڷ���
	 */
	public static String PRIVILEGE_HQFW = "hqfw"; 
	/**
	 * ����ѧϰ
	 */
	public static String PRIVILEGE_ZXXX = "zxxx"; 
	/**
	 * ��˰����
	 */
	public static String PRIVILEGE_NSFW = "nsfw"; 
	/**
	 * �ҵĿռ�
	 */
	public static String PRIVILEGE_SPACE = "spaces"; 

	public static HashMap<String, String> PRIVILEGE_MAP;
	public static List<String> keyPRIVILEGE = new ArrayList<String>(Arrays.asList("PRIVILEGE_XZGL","PRIVILEGE_HQFW"
			,"PRIVILEGE_ZXXX","PRIVILEGE_NSFW","PRIVILEGE_SPACE"));
	public static List<String> valuePRIVILEGE = new ArrayList<String>(Arrays.asList("��������","���ڷ���"
			,"����ѧϰ","��˰����","�ҵĿռ�"));
	static {
		PRIVILEGE_MAP = new HashMap<String, String>();
		PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "��������");
		PRIVILEGE_MAP.put(PRIVILEGE_HQFW, "���ڷ���");
		PRIVILEGE_MAP.put(PRIVILEGE_ZXXX, "����ѧϰ");
		PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "��˰����");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "�ҵĿռ�");
	}
}
