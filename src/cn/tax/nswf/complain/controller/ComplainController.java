package cn.tax.nswf.complain.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tax.core.controller.BaseController;
import cn.tax.core.tax.util.QueryUtil;
import cn.tax.nswf.complain.entity.Complain;
import cn.tax.nswf.complain.service.ComplainService;

@Controller
public class ComplainController extends BaseController {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private ComplainService ComplainService;

	@RequestMapping(value = "/showComplainList")
	public String showComplainList(Model model,
			@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "3") int pageSize) {
		QueryUtil queryUtil = new QueryUtil(Complain.class, "i");
		Map<String, String> COMPLAIN_STATE_MAP = Complain.COMPLAIN_STATE_MAP;
		pageResult = ComplainService.getPageResult(queryUtil, pageNo, pageSize);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("complainMap", COMPLAIN_STATE_MAP);
		return "nsfw/complain/listUI";
	}
}
