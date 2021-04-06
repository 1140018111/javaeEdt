package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DL
 * @diescription com.redis
 * @dis_projectdxl
 * @create 2021-03-31
 */
public class jedisStury {
	public static void main(String[] args) {
//		JedisCluster jedisCluster = new JedisCluster();
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.set("小白白","你好坏");
		String tis = jedis.get("name");
		jedis.sadd("001","aaa","bbbb","cccc");
		Map<String, String> tis1 = jedis.hgetAll("tis");
		HashMap<String, String> hash = new HashMap<>();
		hash.put("","yingle002");

		jedis.hset("map", hash);
		Map<String, String> map = jedis.hgetAll("map");
		String ceshixo = map.get("ceshixo");
		System.out.println(ceshixo);

	}
}
