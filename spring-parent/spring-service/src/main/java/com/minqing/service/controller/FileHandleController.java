package com.minqing.service.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSONObject;
import com.minqing.service.service.FileHandleService;
import com.minqing.service.system.SystemConstants;

/**
 * @author minqing
 * @desc:文件操作控制层
 */
@Controller
@RequestMapping("fileHandle")
public class FileHandleController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(FileHandleController.class);
	
	@Autowired
	private FileHandleService handService;
	@Value("${local.images}")
	private String localImages;
	
	/**
	 * 文件上传
	 */
	@RequestMapping(value="fileUpload",method=RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public String fileUpload(MultipartHttpServletRequest request) {
		JSONObject jsonObject = getSuccess();
		try {
			MultipartFile file = request.getFile("fileName");
			if(file ==null) {
				jsonObject.put(SystemConstants.CODE,SystemConstants.FILE_UPLOAD_CODE);
				jsonObject.put(SystemConstants.MESSAGE,SystemConstants.FILE_UPLOAD_MESSAGE);
				jsonObject.put(SystemConstants.RESULTS, SystemConstants.NULL_STRING);
			}
			//文件上传后的路径
			String upload = handService.fileUpload(file);
			//将此路径根据业务需求保存在数据库即可
			// TODO
			if(!StringUtils.isEmpty(upload)) {
				
			}
			jsonObject.put(SystemConstants.RESULTS, SystemConstants.FILE_UPLOAD_RESULT);
		} catch (Exception e) {
			logger.error("上传文件失败：message,{}",e.getMessage());
			jsonObject =getFail();
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value="fileDownLoad",method=RequestMethod.GET)
	@ResponseBody
	public String fileDownLoad(HttpServletResponse response) {
		JSONObject jsonObject = getSuccess();
		try {
			/**
			 * localImages 系统根路径 
			 * concat("\\20190317").concat("\\20190317.jpg")应该是文件存在数据库表中的路径值
			 * 此处为模拟
			 */
			String location =localImages.concat("\\20190317").concat("\\20190317.jpg");
			logger.info(location);
			InputStream is = new FileInputStream(location);
			handService.downLoadFile(response,is, "20190317.jpg");
		} catch (Exception e) {
			logger.error("下载文件失败：message,{}",e.getMessage());
			jsonObject =getFail();
		}
		return jsonObject.toString();
	}
	
	
}
