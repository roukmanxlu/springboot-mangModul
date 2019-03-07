package com.minqing.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minqing.service.entity.Merchant;
import com.minqing.service.service.MerchantService;

@Controller
@RequestMapping("merchantController")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
	@RequestMapping(value="getMerchant",method=RequestMethod.GET)
	@ResponseBody
	public Merchant getMerchant(int id) {
		return merchantService.selectMerchantById(id);
	}
}
