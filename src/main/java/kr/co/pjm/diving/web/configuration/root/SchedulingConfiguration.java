package kr.co.pjm.diving.web.configuration.root;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.configuration.root
 * @Class Name : SchedulingConfiguration.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 4.
 * @Version : 1.0
 * @Description : 스케쥴링 설정
 *
 */
@Configuration
@EnableAsync
@EnableScheduling
@PropertySource("classpath:config/schedule-properties.xml")
public class SchedulingConfiguration implements SchedulingConfigurer {

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    // TODO Auto-generated method stub
    
  }
  
  

}
