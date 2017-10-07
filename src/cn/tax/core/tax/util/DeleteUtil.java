package cn.tax.core.tax.util;

import cn.tax.core.common.RolePrivilegeN;

public class DeleteUtil {
	/**
	 * @see ɾ��������ָ��Ԫ��,����һ����ɾ���ɾ�
	 * @param arr
	 * @param ele
	 * @return
	 */
    public static RolePrivilegeN[] isDeleteNumber(RolePrivilegeN[] arr, RolePrivilegeN ele) {
    	RolePrivilegeN[] ar = isit(arr, ele);
    	for (int i = 0; i < ar.length; i++) {
			if(ar[i]==ele) {
				return isDeleteNumber(ar, ele);
			}
		}
    	return ar;
    }
    /**
     * @see �������һ��ֻ��ɾ��һ��,����ж���ظ���ôҪ��ε��ü���
     * @param arr
     * @param ele
     * @return
     */
	private static RolePrivilegeN[] isit(RolePrivilegeN[] arr, RolePrivilegeN ele) {
		RolePrivilegeN[] ar = new RolePrivilegeN[arr.length - 1];
		int index = 0;// �������
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ele) {
				index = i;
			}
		}
		/*
		 * �˷�����Ҫ���ҳ�Ҫɾ��Ԫ�ص�λ�ã�Ȼ�������Ҹ��ƽ�Ŀ������ ��jdk�� Long������ public static void
		 * arraycopy(Object src, RolePrivilegeN srcPos, Object dest, RolePrivilegeN destPos, RolePrivilegeN length) src -
		 * Դ���顣 srcPos - Դ�����е���ʼλ�á� dest - Ŀ�����顣 destPos - Ŀ�������е���ʼλ�á� ength -
		 * Ҫ���Ƶ�����Ԫ�ص�������
		 */
		RolePrivilegeN[] a1 = new RolePrivilegeN[index];// ǰ�����鳤��
		RolePrivilegeN[] a2 = new RolePrivilegeN[ar.length - a1.length];
		System.arraycopy(arr, 0, a1, 0, a1.length);
		System.arraycopy(arr, index + 1, a2, 0, a2.length);


		for (int i = 0; i < ar.length; i++) {
			if (i < a1.length) {// ��ǰ��������ӵ�������������
				ar[i] = a1[i];
			}
			if (i >= a1.length) {// �Ѻ��������ӵ�������������
				ar[i] = a2[i - a1.length];// a2�����������0��ʼ����Ҫ��ȥǰ��a1����ĳ���
			}
		}
		
		return ar;
	}
}
