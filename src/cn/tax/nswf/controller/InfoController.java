package cn.tax.nswf.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tax.core.controller.BaseController;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.entity.Info;
import cn.tax.nswf.service.InfoService;
/**
 * InfoController 控制器
 * @author Blake.Zhou
 *
 */
@Controller
public class InfoController extends BaseController{
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping(value="/showInfoList")
	public String showInfoList(Model model,@RequestParam(defaultValue="1") int pageNo,@RequestParam(defaultValue="3") int pageSize){
		QueryUtil queryUtil = new QueryUtil(Info.class,"i");
		List<Info> infos = null;
		infos = infoService.findObjects(Info.class);
		pageResult = infoService.getPageResult(queryUtil, pageNo,pageSize);
		model.addAttribute("infos", infos);
		model.addAttribute("pageResult", pageResult);
		return "nsfw/info/listUI";
	}
	
	/**
	 * 转到增加数据页面
	 * @return
	 */
	@RequestMapping(value="/addInfo_")
	public String addInfo_(Model model){
		Info info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		model.addAttribute("info", info);
		List<String> listInfo = Info.MapKey;
		model.addAttribute("listInfo", listInfo);
		return "nsfw/info/addUI";
	}
	
	/**
	 * 增加数据
	 * @return
	 */
	@RequestMapping(value="/addInfo")
	public String addInfo(@ModelAttribute Info info){
		if (info!=null) {
			Map<String, String> INFO_TYPE_MAP = Info.INFO_TYPE_MAP;
			Set<Entry<String,String>> set = INFO_TYPE_MAP.entrySet();
			String key = "";
			for (Entry<String, String> entry : set) {
				String value = entry.getValue();
				if (value.equals(info.getType())) {
					key = entry.getKey();
					break;
				}
			}
			if (!StringUtils.isEmpty(key)) {
				info.setType(key);
				System.out.println("key:"+key);
			}
			info.setInfoId(UUID.randomUUID().toString());
			System.err.println(info);
			System.out.println(info.getContent());
			infoService.save(info);
		}
		return "redirect:/showInfoList";
	}
	
	/**
	 * 转到修改页面
	 * @return
	 */
	@RequestMapping(value="/updateInfo_")
	public String updateInfo_(@RequestParam String infoId,Model model){
		Info info = infoService.findObjectById(Info.class, infoId);
		List<String> listInfo = Info.MapKey;
		model.addAttribute("listInfo", listInfo);
		model.addAttribute("info", info);
		return "nsfw/info/editUI";
	}
	/**
	 * 修改
	 * @return
	 */
	@RequestMapping(value="/updateInfo")
	public String updateInfo(@ModelAttribute Info info){
		if (info!=null) {
			Map<String, String> INFO_TYPE_MAP = Info.INFO_TYPE_MAP;
			Set<Entry<String,String>> set = INFO_TYPE_MAP.entrySet();
			String key = "";
			for (Entry<String, String> entry : set) {
				String value = entry.getValue();
				if (value.equals(info.getType())) {
					key = entry.getKey();
					break;
				}
			}
			if (!StringUtils.isEmpty(key)) {
				info.setType(key);
				System.out.println("key:"+key);
			}
		}
		System.out.println(info);
		if (info!=null) {
			infoService.update(info);
		}else {
			System.out.println("更新失败");
		}
		return "redirect:/showInfoList";
	}
	
	/**
	 * 删除数据
	 * @return
	 */
	@RequestMapping(value="/removeInfo")
	public String removeInfo(@RequestParam String infoId){
		System.out.println(infoId);
		if (infoId.length()>50) {
			String[] strings = infoId.split(",");
			infoService.delete(Info.class,strings);
		}else {
			infoService.delete(Info.class, infoId);
		}
		return "redirect:/showInfoList";
	}
	
	@RequestMapping(value="/info_publicInfo")
	public @ResponseBody String info_publicInfo(@RequestParam String infoId,@RequestParam String state,HttpServletResponse response){
		String flag = "";
		response.setContentType("text/html");
		System.out.println("数据:"+infoId+" "+state);
		if (infoId!=null&&state!=null) {
			Info info = infoService.findObjectById(Info.class,infoId);
			info.setState(state);
			infoService.update(info);
			flag = "更新状态成功";
		}
		flag = "更新失败";
		try {
			try {
				return flag;
			} catch (Exception e) {
				OutputStream out = response.getOutputStream();
				out.write(flag.getBytes("utf-8"));
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/doSearch_INFO")
	public String doSearch(@RequestParam(defaultValue="信息") String title,Model model,@RequestParam(defaultValue="1") int pageNo,@RequestParam(defaultValue="3") int pageSize){
		System.out.println("...."+title);
		try {
			title = new String(title.getBytes("ISO8859-1"),"utf-8").toString();
			QueryUtil queryUtil = new QueryUtil(Info.class,"i");
			if (!StringUtils.isEmpty(title)) {
				queryUtil.addCondition("i.title like ?", "%" + title + "%");
				//根据创建时间降序排序
				queryUtil.addOrderByProperty("i.createTime", queryUtil.ORDER_BY_ASC);
				pageResult = infoService.getPageResult(queryUtil, pageNo,pageSize);
				List<Info> infos = infoService.findObject__(queryUtil);
				System.out.println(infos.size());
				model.addAttribute("infos", infos);
				model.addAttribute("pageResult", pageResult);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "nsfw/info/listUI";
	}
}
/*
 * 
 * 1.在tomcat6.0下jsp出现getOutputStream() has already been called for this response异常的原因和解决方法
　　在tomcat6.0下jsp中出现此错误一般都是在jsp中使用了输出流（如输出图片验证码，文件下载等），没有妥善处理好的原因。
　　具体的原因就是：
　　在tomcat中jsp编译成servlet之后在函数_jspService(HttpServletRequest request, HttpServletResponse response)的最后有一段这样的代码
 * finally {
    if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
}
out.clear();
out = pageContext.pushBody();
 * */
