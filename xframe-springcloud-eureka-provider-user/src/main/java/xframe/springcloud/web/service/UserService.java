package xframe.springcloud.web.service;
import java.util.concurrent.TimeUnit;

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
	public UserService() {
		super();
		// TODO Auto-generated constructor stub
		//HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1);
		//HystrixCommandGroupKey.Factory.asKey("ExampleGroup");
	}

	@Autowired

	  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


	  /**
	   * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
	   * @param id id
	   * @return 通过id查询到的用户
	   */

	  @HystrixCommand(fallbackMethod = "fallback",
			  threadPoolProperties = {  
			            @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "true"),//允许设置并发数
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
			            @HystrixProperty(name = "maxQueueSize", value = "-1"),//
			            //@HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") //拒绝请求的临界值
			            //@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
			            @HystrixProperty(name = "maximumSize", value = "1000"),//线程最大并发数，（必须设置）不然直接fallback！
			            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
			  }, 
			  commandProperties = {  
	                    //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "400"),//指定多久超时，单位毫秒。超时进fallback
	                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
	                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "1000"),//熔断最大并发数（必须设置）不然直接报错！

	                    //@HystrixProperty(name = "circuitBreaker.enabled", value = "false"),//是否开启熔断机制
	                    //@HystrixProperty(name = "circuitBreaker.forceClosed", value = "true"),//强制关闭开关
	     
	                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),//熔断后的重试时间窗口，熔断器默认工作时间,默认:5秒.熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
	                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),	  // 熔断器在整个统计时间内是否开启的阀值，默认20秒。也就是10秒钟内至少请求20次，熔断器才发挥起作用
	                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "80"),//判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
	  })
	  public String findById(Long id) {
	    //return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
		
		  UserService.LOGGER.info("正常访问", id);
		  return "这是findbyid";
	  }

	  /**
	   * hystrix fallback方法
	   * @param id id
	   * @return 默认的用户
	   */
	  public String fallback(Long id) {
		//报错会强制触发熔断器！
		UserService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
	    return "这是findbyid熔断器！";
	  }
}
