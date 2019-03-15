package com.minqing.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.minqing.service.entity.Merchant;
import com.minqing.service.mapper.MerchantMapper;

public  class MapperTest extends TmallApplicationTests{
	
	@Autowired
    private JavaMailSender mailSender; //自动注入的Bean
 
   /* @Autowired
    private TemplateEngine templateEngine;*/
 
	@Value("${execl.data}")
	private String execlDate;
    @Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数
	@Value("${async.executor.thread.core_pool_size}")
	private String poolSize;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private  Environment ev;

	
	@Test
	public void test() {
		System.out.println("9999999999999999999999999"+poolSize+","+sender);
		System.out.println(execlDate);
		System.out.println(ev.getProperty("async.executor.thread.core_pool_size"));
	}
	
	@Test
	public void send() {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo("564853464@qq.com"); //发送邮
        message.setSubject("主题：来自mq@freepay.com的问候");
        message.setText("ceshi mail");
        mailSender.send(message);
        System.out.println("ok");
	}
	
	@Test
	public void send2() {
		List<Merchant> list = merchantMapper.selectAll();
		System.out.println(list);
	}
	
	
}
