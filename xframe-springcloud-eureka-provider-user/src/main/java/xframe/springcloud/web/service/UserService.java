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

	  @HystrixCommand(fallbackMethod = "fallback",
			  threadPoolProperties = {  
/** 
配置线程池大小,默认值10个. 
建议值:请求高峰时99.5%的平均响应时间 + 向上预留一些即可 
*/  
			            @HystrixProperty(name = "coreSize", value = "10"), 
/** 
配置线程值等待队列长度,默认值:-1 
建议值:-1表示不等待直接拒绝,测试表明线程池使用直接决绝策略+ 合适大小的非回缩线程池效率最高.所以不建议修改此值。 
当使用非回缩线程池时，queueSizeRejectionThreshold,keepAliveTimeMinutes 参数无效 
*/ 			            
			            @HystrixProperty(name = "maxQueueSize", value = "-1"),  
			            //@HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") //拒绝请求的临界值
			            //@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
			            //@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                        //@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
			  }, 
			  commandProperties = {  
	                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "400"),//指定多久超时，单位毫秒。超时进fallback
	                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),	  // 熔断器在整个统计时间内是否开启的阀值，默认20秒。也就是10秒钟内至少请求20次，熔断器才发挥起作用
	                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),//判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
	  })
	  public String findById(Long id) {
	    //return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
		
		  try {
			Thread.sleep(3000);
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
