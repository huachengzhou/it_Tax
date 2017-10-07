package cn.tax.core.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Constant {
	public static String USER_SESSION_NAME = "user";
	public static String USER_ERROR = "error";
	/*----------------------系统权限集合--------------------------*/
	/**
	 * 行政管理
	 */
	public static String PRIVILEGE_XZGL = "xzgl"; 
	/**
	 * 后勤服务
	 */
	public static String PRIVILEGE_HQFW = "hqfw"; 
	/**
	 * 在线学习
	 */
	public static String PRIVILEGE_ZXXX = "zxxx"; 
	/**
	 * 纳税服务
	 */
	public static String PRIVILEGE_NSFW = "nsfw"; 
	/**
	 * 我的空间
	 */
	public static String PRIVILEGE_SPACE = "spaces"; 

	public static HashMap<String, String> PRIVILEGE_MAP;
	public static List<String> keyPRIVILEGE = new ArrayList<String>(Arrays.asList("PRIVILEGE_XZGL","PRIVILEGE_HQFW"
			,"PRIVILEGE_ZXXX","PRIVILEGE_NSFW","PRIVILEGE_SPACE"));
	public static List<String> valuePRIVILEGE = new ArrayList<String>(Arrays.asList("行政管理","后勤服务"
			,"在线学习","纳税服务","我的空间"));
	static {
		PRIVILEGE_MAP = new HashMap<String, String>();
		PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_HQFW, "后勤服务");
		PRIVILEGE_MAP.put(PRIVILEGE_ZXXX, "在线学习");
		PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "纳税服务");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
	}
}
