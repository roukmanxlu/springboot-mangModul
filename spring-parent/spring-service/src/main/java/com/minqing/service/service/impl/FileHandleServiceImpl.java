package com.minqing.service.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.minqing.service.service.FileHandleService;

@Service
public class FileHandleServiceImpl implements FileHandleService {

	private Logger logger = LoggerFactory.getLogger(FileHandleServiceImpl.class);
	
	@Value("${local.images}")
	private String localImages;
	
	/**
	 * 文件上传
	 */
	@Override
	public String fileUpload(MultipartFile file) {
		
		String location ="";
		if(file ==null) {
			throw new RuntimeException("上传文件为空");
		}

		String fileName = file.getOriginalFilename();
		logger.info("上传文件的名称：fileName:{}",fileName);
		String suffix = StringUtils.substring(fileName, fileName.lastIndexOf("."));
		logger.info("上传文件的后缀名称：fileName:{}",suffix);
		
		//定义最终文件存储的目录
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		//localImages 此处为本地路径  在生产上改成生产服务器的路径
		String rootUrl = localImages.concat(File.separator).concat(format.format(new Date())).concat(File.separator);
		File file2 = new File(rootUrl);
		if(!file2.exists()) {
			file2.mkdirs();
		}
		
		// 构造最终的文件名(需要注意，如果文件名存在,再次上传会被覆盖)
		format = new SimpleDateFormat("yyyyMMddHHmmss");
		String destFileName = format.format(new Date()).concat(suffix);
		File destFile = new File(rootUrl.concat(destFileName));
		try {
			file.transferTo(destFile);
			location =File.separator.concat(format.format(new Date())).concat(File.separator).concat(destFileName);
		} catch (IOException e) {
			logger.error("文件上传失败,e,{}",e.getMessage());
		}
		return location;
	}

	@Override
	public void downLoadFile(HttpServletResponse response, InputStream inputStream, String fileName) {
		if(inputStream ==null || StringUtils.isEmpty(fileName)) {
			return;
		}
		
		BufferedInputStream bis =null;
		BufferedOutputStream bos =null;
		OutputStream os  = null;
		try {
			bis= new BufferedInputStream(inputStream);
			os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-disposition",
					"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
			
			byte[] buffer = new byte[10240];
			int len = bis.read(buffer);
			while(len !=-1) {
				bos.write(buffer,0,len);
				len =bis.read(buffer);
			}
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(bis !=null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
			if(inputStream !=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}
