package com.lenovo.yangww4.springbootweb;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 配置实现的第二种方式
 * <p>
 * 写到主类的内部
 * 
 * @author yangww
 *
 */
@SpringBootApplication
public class Springbootweb2Ch74Application {

	public static void main(String[] args) {

		SpringApplication.run(Springbootweb2Ch74Application.class, args);
	}

	/**
	 * 代码配置Tomcat
	 * 
	 * @author yangww
	 *
	 */
	@Component
	public static class CustomServletContainer implements EmbeddedServletContainerCustomizer {

		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {

			container.setPort(9999);// 配置端口号

			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html")); // 错误页面, 根据http的状态信息

			container.setSessionTimeout(10, TimeUnit.MINUTES); // session过期时间配置
		}

	}
}
