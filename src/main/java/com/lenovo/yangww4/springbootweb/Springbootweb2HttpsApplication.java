package com.lenovo.yangww4.springbootweb;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

/**
 * 同时支持http和 https访问
 * <p>
 * 访问方式:访问 http://127.0.0.1:7777 会直接跳转到 https://127.0.0.1:8443/
 * <p>
 * 类似百度的访问方式
 * 
 * @author yangww
 *
 */
@SpringBootApplication
public class Springbootweb2HttpsApplication {

	public static void main(String[] args) {

		SpringApplication.run(Springbootweb2HttpsApplication.class, args);
	}

	/**
	 * 修改tomcat的配置
	 * 
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {

			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");

				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);

				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}

	/**
	 * 重新定义http链接
	 * 
	 * @return
	 */
	@Bean
	public Connector httpConnector() {

		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(7777);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}
}
