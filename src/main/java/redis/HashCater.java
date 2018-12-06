package redis;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class HashCater
{
	public static void main(String[] args){
		try(Jedis jedis=new Jedis("localhost")){
			jedis.hset("HASHSET","test1","VALUE1");
			jedis.hset("HASHSET","test2","VALUE2");
			jedis.hset("HASHSET","test3","VALUE3");


			final Map<String, String> hashset = jedis.hgetAll("HASHSET");

			int a=0;
		}
	}
}
