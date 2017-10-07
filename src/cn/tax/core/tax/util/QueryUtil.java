package cn.tax.core.tax.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * hql ��ѯ���
 * 
 * @author Blake.Zhou
 * 
 */
public class QueryUtil {
	
	/** ����˳�� ���� */
	public static String ORDER_BY_DESC = "DESC";
	
	/** ����˳�� ���� */
	public static String ORDER_BY_ASC = "ASC";
	
	/** from ��� ����һ�� */
	private String FromClase = "";
	
	/** where ��� ���ֶ�� */
	private String WhereClase = "";
	
	/** order by ��� */
	private String OrderByClase = "";

	private List<Object> parameters = new ArrayList<Object>();

	/** Hql ��ѯ��� */
	public String getQueryListHql() {
		return FromClase + WhereClase + OrderByClase;
	}

	/**
	 * ����order by�Ӿ�
	 * 
	 * @param property
	 *            �������ԣ��磺i.createTime
	 * @param order
	 *            ����˳���磺DESC ���� ASC
	 * @return
	 */
	public void addOrderByProperty(String property, String order) {
		if (OrderByClase.length() > 1) {/* �ǵ�һ������˳�� order by ��� */
			OrderByClase += "," + property + " " + order;
		} else {/* ��һ������˳�� */
			OrderByClase = " ORDER BY " + property + " " + order;
		}
	}

	/**
	 * ����where ���
	 * 
	 * @param conding
	 *            ����:i.title like ?
	 * @param params
	 *            ? ��Ӧ %title%
	 * @return
	 */
	public void addCondition(String conding, Object... params) {
		System.out.println();
		if (WhereClase.length() > 0) {/* �ǵ�һ�β�ѯ���� */
			WhereClase += " AND " + conding;
		} else {/* ��һ�β�ѯ */
			WhereClase += " where " + conding;
		}

		/* ��������ֵ������������ */
		if (parameters != null) {
			parameters = new ArrayList<Object>();
		}
		/* ֵ add List */
		if (params != null) {
			for (Object param : params) {
				parameters.add(param);
			}
		}
	}

	/**
	 * ����From ���
	 * 
	 * @param tClass
	 * @param alias
	 */
	public QueryUtil(@SuppressWarnings("rawtypes") Class tClass, String alias) {
		if (!StringUtils.isEmpty(alias)) {
			FromClase = "FROM " + tClass.getSimpleName() + " as " + alias;
		} else {
			String flag = tClass.getSimpleName();/* �������Ϊnull��ȡ��������Сд��ʽ */
			FromClase = "FROM " + tClass.getSimpleName() + " " + flag.toLowerCase();
		}
	}

	/**
	 * ��ѯhql�����?��Ӧ�Ĳ�ѯ����ֵ����
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
	
	/**��ѯͳ������hql���*/
	public String getQueryCountHql(){
		return "SELECT COUNT(*) " + FromClase + WhereClase;
	}

}
