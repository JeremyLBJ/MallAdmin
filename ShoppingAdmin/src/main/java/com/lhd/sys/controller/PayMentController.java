package com.lhd.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payMent")
public class PayMentController {
	
	@RequestMapping("PayMentTows")
	public String payMent() {
		return "PayMentTow";
	}

}
