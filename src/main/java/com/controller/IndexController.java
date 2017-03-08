/**
 * 
 */
package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月8日
 */
@Controller
public class IndexController {
	@RequestMapping(value = "/index.do")
	public String showIndex(){
		return "index";
	}
}
