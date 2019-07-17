package io.ymq.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class RedisController {

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    private int num = 20;

    /**
     * 测试redis分布式锁(没有锁)
     */
    public void testUnLockReids() throws InterruptedException {
        String s = Thread.currentThread().getName();
        if (num > 0) {
            System.out.println(s + "redis排号成功，号码是：" + num);
            num--;
        } else {
            System.out.println(s + "redis排号失败,号码已经被抢光");
        }
    }

    /**
     * 测试redis分布式锁(有锁)
     */
    public void testLockReids() throws InterruptedException {
        Lock lock = redisLockRegistry.obtain("lock");
        boolean isLock = lock.tryLock(1, TimeUnit.SECONDS);
        String s = Thread.currentThread().getName();
        if (num > 0 && isLock) {
            System.out.println(s + "redis排号成功，号码是：" + num);
            num--;
        } else {
            System.out.println(s + "redis排号失败,号码已经被抢光");
        }
        lock.unlock();
    }

}
