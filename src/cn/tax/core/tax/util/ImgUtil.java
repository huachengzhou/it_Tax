package cn.tax.core.tax.util;

import cn.tax.nswf.entity.User;
/**
 * 
 * @author Blake.Zhou
 * img 
 */
public class ImgUtil {
	/**
	 * ��ȡͼƬ���·��
	 * @param user
	 * @return
	 */
	public static String headImgUtil(User user){
		String imgSrc = user.getHeadImg();
		if (imgSrc!=null) {
			imgSrc = imgSrc.substring(imgSrc.lastIndexOf("\\")+1,imgSrc.length());
			imgSrc = "upload/"+imgSrc;
		}
		return imgSrc;
	}
}
