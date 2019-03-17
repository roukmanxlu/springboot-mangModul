package com.minqing.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MultipartFilter;

import com.alibaba.fastjson.JSONObject;
import com.minqing.service.entity.Merchant;
import com.minqing.service.service.MerchantService;
import com.minqing.service.system.SystemConstants;
import com.minqing.service.utils.ExeclUtils;

@Controller
@RequestMapping("merchantController")
public class MerchantController extends BaseController{

	private Logger log = LoggerFactory.getLogger(MerchantController.class);
	
	@Autowired
	private MerchantService merchantService;
	
	@Value("${async.executor.thread.core_pool_size}")
	private String poolSize;
	@Value("${execl.data}")
	private String execlDate;
	
	@RequestMapping(value="getMerchant",method=RequestMethod.GET)
	@ResponseBody
	public String getMerchant(int id) {
		JSONObject jsonObject = getSuccess();
		Merchant merchant = merchantService.selectMerchantById(id);
		jsonObject.put(SystemConstants.RESULTS, merchant);
		log.info("线程池的大小:size:{}",poolSize);
		return jsonObject.toString();
	}
	
	//单个sheet导出
	@RequestMapping(value="export",method=RequestMethod.GET)
	public void selectMerchant(HttpServletResponse response) {
		List<Merchant> merchants = merchantService.selectAll();
		List<Map<Integer, Object>> mapList = inverset(merchants);
		if(mapList.size()>0) {
			String[] headers = new String[] {"代理商账号","代理商名称","手机号","身份证","地址"};
			String sheetName ="代理商信息详情表";
			Workbook wb = ExeclUtils.fillExeclDate(mapList, headers, sheetName,new HSSFWorkbook());
			ExeclUtils.exportExecl(wb, response, sheetName);
		}
	}
	
	//多个sheet导出
	@RequestMapping(value="export2",method=RequestMethod.GET)
	public void export2(HttpServletResponse response) {
		List<Merchant> merchants = merchantService.selectAll();
		List<Map<Integer, Object>> mapList = inverset(merchants);
		if(mapList.size()>0) {
			String[] headers = new String[] {"代理商账号","代理商名称","手机号","身份证","地址"};
			String sheetName ="代理商信息详情表";
			Workbook wb =  ExeclUtils.manageSheet(Integer.parseInt(execlDate),mapList, headers, sheetName);
			ExeclUtils.exportExecl(wb, response, sheetName);
		}
	}
	
	
	public List<Map<Integer, Object>> inverset(List<Merchant> merchants){
		List<Map<Integer, Object>> mapList = new ArrayList<Map<Integer, Object>>();
		if(merchants !=null && merchants.size()>0) {
			Map<Integer, Object> map;
			for(Merchant merchant:merchants) {
				map= new HashMap<Integer, Object>();
				map.put(0, merchant.getAccountNo());
				map.put(1, merchant.getAccountName());
				map.put(2, merchant.getMobile());
				map.put(3, merchant.getCardNo());
				map.put(4, merchant.getAddress());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	
}
