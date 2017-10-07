package cn.tax.nswf.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/nsfw")
@Controller
public class HomeController_nswf {
	
	@RequestMapping(value="/nsfwHome")
	public String nswfHome(){
		return "nsfw/frame";
	}
	
	@RequestMapping(value="/home_left")
	public String home_left(){
		return "nsfw/left";
	}
	
	@RequestMapping(value="/home_top")
	public String home_top(){
		return "nsfw/top";
	}
	
}
