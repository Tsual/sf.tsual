package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PubCater
{
	public static void main(String[] args) throws InterruptedException
	{
		try (final Jedis jedis = new Jedis("localhost")) {
			Thread clientThread = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try (final Jedis jedis = new Jedis("localhost")){
						jedis.subscribe(new JedisPubSub()
						{
							@Override
							public void onMessage(String channel, String message)
							{
								super.onMessage(channel, message);
								System.out.println("get<<" + message);
							}
						}, "test_channel");
					}
				}
			});
			clientThread.start();
			jedis.publish("test_channel", "aaaaaaaa");
			jedis.publish("test_channel", "aaaaaaaa");
			jedis.publish("test_channel", "aaaaaaaa");
			jedis.publish("test_channel", "aaaaaaaa");
			clientThread.join();
			int a = 0;
		}
	}
}
