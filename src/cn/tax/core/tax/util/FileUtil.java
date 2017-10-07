package cn.tax.core.tax.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author Blake.Zhou
 *
 */
public class FileUtil {
	/**
	 * @param file
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String saveFile(MultipartFile file,HttpServletRequest request){
		String url = "";
		if (!file.isEmpty()) {// �ļ���Ϊnull
			String path = "";
			try {
//				path = request.getServletContext().getRealPath("/upload/");// �ϴ�·��
				path = request.getRealPath("/upload/");
			} catch (Exception e) {
			}
			String fileName = file.getOriginalFilename();// �ļ���
			File filepath = new File(path, fileName);
			// �ж�·���Ƿ��������������򴴽�һ��
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// ���ϴ��ļ����浽һ��Ŀ���ļ���
			try {
				String uuid = UUID.randomUUID().toString();
				uuid.substring(1, 19);
				url = path + File.separator+uuid + fileName;
				file.transferTo(new File(path + File.separator+uuid + fileName));
			} catch (Exception e) {
				
			}
		}
		return url;
	}
}
