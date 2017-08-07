项目说明：
xframe-springcloud-eureka-server eureka服务注册中心
xframe-springcloud-eureka-ha-server eureka高可用服务注册中心，可以通过cmd启动两个进程，参数分别为--spring.profiles.active=peer1和--spring.profiles.active=peer2。
xframe-springcloud-eureka-provider-user 用户服务提供者，只是个demo，可以通过cmd启动两个进程，参数分别为--server.port=2222和--server.port=2223
xframe-springcloud-ribbon-consumer 用户服务消费者，通过eureka服务注册中心以轮询方式调用xframe-springcloud-eureka-provider-user服务


-----------------
springcloud后续进一步预研
spring cloud 【Ribbon+eureka+用户服务提供者+配置中心】 (客户端负载均衡) （或 【nginx +用户服务提供者+eureka服务注册中心+consul配置管理】。  根据springcloud研究结果选一种）基础-优先级高
spring cloud Hystrix (服务容错保护)      基础-优先级高（重点：业务接口线程池隔离技术，业务接口降级机制，业务接口熔断机制）
spring cloud Feign (声明式调用服务)       基础-优先级高  http客户端curl,
spring cloud Zuul (API网关服务)          基础-优先级高（重点：动态路由的应用，如：单点登录）
spring cloud Config (分布式配置中心)       基础-优先级高
-----------------
springcloud压测与性能优化



-----------------
springcloud 二期
通过动态路由实现一些基本统计：如流量统计、接口频次统计、接口预警、其他业务基本的数据分析。

-----------------
数据层中间件选型，实战
----------------
DBA数据库主从分库实战