package com.zhuyaninghystrix8004.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: ZhuYaning
 * Date: 2022/06/29
 * Time: 23:59
 */

@Configuration
public class HystrixDashboardConfig {
   /**
    *  Hystrix dashboard 监控界面必须配置
    */
   @Bean
   public ServletRegistrationBean getServlet() {
      HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
      ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
      registrationBean.setLoadOnStartup(1);
      registrationBean.addUrlMappings("/actuator/hystrix.stream");//访问路径
      registrationBean.setName("hystrix.stream");
      return registrationBean;
   }
}
