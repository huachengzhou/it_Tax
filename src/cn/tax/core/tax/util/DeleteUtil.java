package cn.tax.core.tax.util;

import cn.tax.core.common.RolePrivilegeN;

public class DeleteUtil {
	/**
	 * @see 删除数组中指定元素,并且一次性删除干净
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
     * @see 这个方法一次只能删除一次,如果有多个重复那么要多次调用即可
     * @param arr
     * @param ele
     * @return
     */
	private static RolePrivilegeN[] isit(RolePrivilegeN[] arr, RolePrivilegeN ele) {
		RolePrivilegeN[] ar = new RolePrivilegeN[arr.length - 1];
		int index = 0;// 用作标号
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ele) {
				index = i;
			}
		}
		/*
		 * 此方法主要即找出要删除元素的位置，然后再左右复制进目标数组 在jdk保 Long包下面 public static void
		 * arraycopy(Object src, RolePrivilegeN srcPos, Object dest, RolePrivilegeN destPos, RolePrivilegeN length) src -
		 * 源数组。 srcPos - 源数组中的起始位置。 dest - 目标数组。 destPos - 目标数据中的起始位置。 ength -
		 * 要复制的数组元素的数量。
		 */
		RolePrivilegeN[] a1 = new RolePrivilegeN[index];// 前段数组长度
		RolePrivilegeN[] a2 = new RolePrivilegeN[ar.length - a1.length];
		System.arraycopy(arr, 0, a1, 0, a1.length);
		System.arraycopy(arr, index + 1, a2, 0, a2.length);


		for (int i = 0; i < ar.length; i++) {
			if (i < a1.length) {// 把前段数据添加到返回数组中来
				ar[i] = a1[i];
			}
			if (i >= a1.length) {// 把后段数据添加到返回数组中来
				ar[i] = a2[i - a1.length];// a2的索引必须从0开始所以要减去前面a1数组的长度
			}
		}
		
		return ar;
	}
}
