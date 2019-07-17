package io.ymq.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * 描述:启动服务
 *
 * @author yanpenglei
 * @create 2017-10-16 18:51
 **/
@SpringBootApplication
public class StartupLock {

	
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StartupLock.class, args);
//        RedisController redisController = applicationContext.getBean(RedisController.class);
//        try {
//        	redisController.testLockReids();
//        } catch (InterruptedException e) {
//        	// TODO Auto-generated catch block
//        	e.printStackTrace();
//        }
        ZkController zkController = applicationContext.getBean(ZkController.class);
        try {
        	zkController.testLockZk();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
