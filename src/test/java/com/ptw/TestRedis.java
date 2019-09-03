package com.ptw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptw.redis.RedisCacheHelper;
import com.ptw.redis.RedisLockUtils;

@RunWith(SpringJUnit4ClassRunner.class) // 让junit与spring环境进行整合
@SpringBootTest(classes = { App.class }) // 自动加载spring相关的配置文件
public class TestRedis {
	@Autowired
	private RedisCacheHelper redisUtils;
	@Autowired
	private RedisLockUtils locks;
	@Test
	public void t1() {
		redisUtils.set("g9", "bbbbb");
	}
	@Test
	public void t2() {
		locks.testThreadLock();
	}
}
