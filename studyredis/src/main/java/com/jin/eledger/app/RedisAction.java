package com.jin.eledger.app;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jin.eledger.pojo.*;
import com.jin.eledger.service.EledgerService;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 
 * 电子账单类
 *
 */
@Controller
@RequestMapping("redis")
public class RedisAction {

	@Autowired
	private EledgerService eledgerService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@RequestMapping(value="standaloneRedis")
	public void standaloneRedis(){
		//最简单的使用redis的方法，单机redis
		RedisClient redisClient = RedisClient.create("redis://@localhost:6379/0");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();
		syncCommands.set("name", "Hello, Redis!");
		connection.close();
		redisClient.shutdown();
	}

	//连接集群的用法
	@RequestMapping(value="clusterRedis")
	public void clusterRedis(){
		//最简单的使用redis的方法，单机redis
		RedisClusterClient redisClient = RedisClusterClient.create("redis://@localhost:9000");
		StatefulRedisClusterConnection<String, String> connection = redisClient.connect();
		RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
		String key = syncCommands.set("name", "Hello, Redis!");
		System.out.println(key);
		System.out.println("Connected to Redis");
		connection.close();
		redisClient.shutdown();
	}

	@RequestMapping("lettuceWithSpring/{type}")
	public void lettuceWithSpring(@PathVariable(value="type") String type,String key){
		EledgerPo eledgerPo = new EledgerPo();
		eledgerPo.setCreateTime(new Date());
		eledgerPo.setGuige(key);
		if("add".equals(type)){
			redisTemplate.opsForValue().set(key,eledgerPo);
		}
		if("update".equals(type)){
			redisTemplate.opsForValue().set(key,eledgerPo);
		}
		if("get".equals(type)){
			Object o = redisTemplate.opsForValue().get(key);
			System.out.println(o);
		}
//
//		if(redisTemplate.hasKey()){
//			System.out.println("redis中查找的信息");
//			return (String) redisTemplate.opsForValue().get(key);
//		}else {
//			String val ="RedisTemplate";
//			log.info("mysql中查找的信息");
//			redisTemplate.opsForValue().set(key,val);
//			log.info("mysql中查询的信息存入Redis中");
//			return val;
//		}
	}

}
