package com.minqing.service.service;

import java.util.List;

import com.minqing.service.entity.Merchant;

public interface MerchantService {

	 Merchant selectMerchantById(int id);
	 
	 List<Merchant> selectAll();
}
