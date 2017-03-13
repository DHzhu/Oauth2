/**
 * 
 */
package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Client;
import com.service.ClientService;
import com.utils.PageResult;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月8日
 */
@Controller
public class IndexController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/index.do")
	public String showIndex(HttpServletRequest request,Map<String, Object> map){
		
		return "index";
	}
	
	@RequestMapping(value = "/client.do")
	public String showClient(HttpServletRequest request,Map<String, Object> map){
		
		return "clientList";
	}
	
	@RequestMapping(value = "/getClients.do")
	public @ResponseBody String getInfoByPage(PageResult<Client> pageResult,Client client){
		
		return clientService.getClients(pageResult, client).toString();
	}
	
	@RequestMapping(value = "/user.do")
	public String showUser(HttpServletRequest request,Map<String, Object> map){
		
		return "userProfile";
	}
	
}
