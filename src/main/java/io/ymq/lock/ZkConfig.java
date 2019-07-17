package io.ymq.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;

@Configuration
public class ZkConfig {

	@Bean
    public ZookeeperLockRegistry zookeeperLockRegistry() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
		client.start();
		try {
//			client.createContainers("/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			client.close();
		}
        return new ZookeeperLockRegistry(client);
    }
}