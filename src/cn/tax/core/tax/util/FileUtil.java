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
		if (!file.isEmpty()) {// 文件不为null
			String path = "";
			try {
//				path = request.getServletContext().getRealPath("/upload/");// 上传路径
				path = request.getRealPath("/upload/");
			} catch (Exception e) {
			}
			String fileName = file.getOriginalFilename();// 文件名
			File filepath = new File(path, fileName);
			// 判断路径是否存在如果不存在则创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件中
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
