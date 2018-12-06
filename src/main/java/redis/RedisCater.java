package redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedisCater
{
	static void init(Jedis jedis)
	{
		Random ran = new Random();

		jedis.set("test_key", ran.nextLong() + "");

		List<String> strlist = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			strlist.add(ran.nextLong() + "");
		jedis.lpush("test_list", strlist.toArray(new String[0]));
	}

	public static void main(String[] args) throws InterruptedException
	{
		try (Jedis jedis = new Jedis("localhost")) {

//			init(jedis);

			final String test_key = jedis.get("test_key");


			final Long test_list_len = jedis.llen("test_list");
			final String test_list_index_55 = jedis.lindex("test_list", 55);
			final List<String> test_list_range_0_99 = jedis.lrange("test_list", 0, 99);


		}
	}
}
