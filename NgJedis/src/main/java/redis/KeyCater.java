package redis;


import redis.clients.jedis.Jedis;

public class KeyCater
{
	static final String KEY_NAME = "KEY_KEY_TEST";

	static void init(Jedis jedis)
	{
		/*设置key*/
		jedis.set(KEY_NAME, "TEST_VALUE");
	}

	public static void main(String[] args)
	{
		try (Jedis jedis = new Jedis("localhost")) {
			init(jedis);

			/*删除key*/
//			jedis.del(KEY_NAME);

			final byte[] dump = jedis.dump(KEY_NAME);//序列化


		}
	}
}
