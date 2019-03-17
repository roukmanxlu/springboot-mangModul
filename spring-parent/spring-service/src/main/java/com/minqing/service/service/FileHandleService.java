package com.minqing.service.service;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface FileHandleService {

	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	String fileUpload(MultipartFile file);
	
	/**
	 * 文件下载
	 * @param response
	 * @param inputStream
	 * @param fileName
	 */
	void downLoadFile(HttpServletResponse response,InputStream inputStream,String fileName);
}
