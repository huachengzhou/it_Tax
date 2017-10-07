package cn.tax.core.tax.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * hql 查询语句
 * 
 * @author Blake.Zhou
 * 
 */
public class QueryUtil {
	
	/** 排序顺序 降序 */
	public static String ORDER_BY_DESC = "DESC";
	
	/** 排序顺序 升序 */
	public static String ORDER_BY_ASC = "ASC";
	
	/** from 语句 出现一次 */
	private String FromClase = "";
	
	/** where 语句 出现多次 */
	private String WhereClase = "";
	
	/** order by 语句 */
	private String OrderByClase = "";

	private List<Object> parameters = new ArrayList<Object>();

	/** Hql 查询语句 */
	public String getQueryListHql() {
		return FromClase + WhereClase + OrderByClase;
	}

	/**
	 * 构造order by子句
	 * 
	 * @param property
	 *            排序属性，如：i.createTime
	 * @param order
	 *            排序顺序，如：DESC 或者 ASC
	 * @return
	 */
	public void addOrderByProperty(String property, String order) {
		if (OrderByClase.length() > 1) {/* 非第一个排序顺序 order by 语句 */
			OrderByClase += "," + property + " " + order;
		} else {/* 第一个排序顺序 */
			OrderByClase = " ORDER BY " + property + " " + order;
		}
	}

	/**
	 * 构造where 语句
	 * 
	 * @param conding
	 *            例如:i.title like ?
	 * @param params
	 *            ? 对应 %title%
	 * @return
	 */
	public void addCondition(String conding, Object... params) {
		System.out.println();
		if (WhereClase.length() > 0) {/* 非第一次查询条件 */
			WhereClase += " AND " + conding;
		} else {/* 第一次查询 */
			WhereClase += " where " + conding;
		}

		/* 设置条件值到条件集合众 */
		if (parameters != null) {
			parameters = new ArrayList<Object>();
		}
		/* 值 add List */
		if (params != null) {
			for (Object param : params) {
				parameters.add(param);
			}
		}
	}

	/**
	 * 构造From 语句
	 * 
	 * @param tClass
	 * @param alias
	 */
	public QueryUtil(@SuppressWarnings("rawtypes") Class tClass, String alias) {
		if (!StringUtils.isEmpty(alias)) {
			FromClase = "FROM " + tClass.getSimpleName() + " as " + alias;
		} else {
			String flag = tClass.getSimpleName();/* 如果别名为null则取其类名的小写形式 */
			FromClase = "FROM " + tClass.getSimpleName() + " " + flag.toLowerCase();
		}
	}

	/**
	 * 查询hql语句中?对应的查询条件值集合
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
	
	/**查询统计数的hql语句*/
	public String getQueryCountHql(){
		return "SELECT COUNT(*) " + FromClase + WhereClase;
	}

}
