package com.lenovo.yangww4.springbootweb;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 代码配置Tomcat
 * 
 * @author yangww
 *
 */
@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		
		container.setPort(8888);// 配置端口号
		
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html")); //错误页面, 根据http的状态信息
		
		container.setSessionTimeout(10, TimeUnit.MINUTES); //session过期时间配置
	}

}
