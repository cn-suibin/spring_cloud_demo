package xframe.springcloud.web.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserService{
	@Autowired

	  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


	  /**
	   * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
	   * @param id id
	   * @return 通过id查询到的用户
	   */

	  @HystrixCommand(fallbackMethod = "fallback",threadPoolProperties = {  
			            @HystrixProperty(name = "coreSize", value = "30"), @HystrixProperty(name = "maxQueueSize", value = "100"),  
			            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") }, commandProperties = {  
			                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),  
			                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "50")  
			  
			    })
	  public String findById(Long id) {
	    //return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
		  try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		  return "这是findbyid";
	  }

	  /**
	   * hystrix fallback方法
	   * @param id id
	   * @return 默认的用户
	   */
	  public String fallback(Long id) {
		UserService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
	    return "这是findbyid熔断器！";
	  }
}
