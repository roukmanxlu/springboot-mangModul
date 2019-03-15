package com.minqing.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minqing.service.entity.Merchant;
import com.minqing.service.mapper.MerchantMapper;
import com.minqing.service.service.MerchantService;
@Service
public class MerchantServiceImpl implements MerchantService{

	Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);
	
	@Autowired
	private MerchantMapper merchantMapper;

	@Override
	public Merchant selectMerchantById(int id) {
		logger.info("id:{}",id);
		return merchantMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Merchant> selectAll() {
		return merchantMapper.selectAll();
	}
	
	
}
