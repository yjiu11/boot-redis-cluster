package com.ptw.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

 
 
@Component
public class RedisLockUtils {
	private Integer count = 100;
    @Autowired
    private RedissonClient redisson;
 
 
    public void lock(String key, String num){
        RLock lock = redisson.getLock(key);
        boolean locked = false;
        try{
            locked = lock.tryLock(10, TimeUnit.SECONDS);
            if (locked){
                //开始写业务
            	System.err.println(num + "锁住了。。。");
            	System.err.println(num + "模拟业务耗时开始。。");
                if(count<=0) {
                	System.err.println("=====抢购结束=====");
                	return;
                }
                count--;
                System.err.println(count.toString());
                System.err.println(num + "模拟业务耗时结束。。。");
            } else {
            	System.err.println(num + "抢购失败。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (locked){
                System.err.println(num + "释放锁");
                lock.unlock();
            }
        }
    }
 
 
    public void testThreadLock(){
        for (int i=1; i<20000; i++){
            new Thread(){
                @Override
                public void run(){
                    lock("test", this.getName());
                    System.out.println("====");
                    try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            }.start();
        }
    }
}