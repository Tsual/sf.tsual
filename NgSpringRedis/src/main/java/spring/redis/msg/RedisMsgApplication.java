package spring.redis.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import sf.task.DefaultHost;
import sf.task.Task;
import sf.task.TaskHub;

@SpringBootApplication
public class RedisMsgApplication
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisMsgApplication.class);

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
	                                        MessageListenerAdapter listenerAdapter)
	{

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver)
	{
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	Receiver receiver()
	{
		return new Receiver();
	}

	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory)
	{
		return new StringRedisTemplate(connectionFactory);
	}

	@Bean
	TaskHub hub()
	{
		return DefaultHost.newDefaultHub(LOGGER::info);
	}

	public static void main(String[] args) throws Exception
	{
		ApplicationContext ctx = SpringApplication.run(RedisMsgApplication.class, args);
		ctx.getBean(TaskHub.class).execute(() ->
		{
			LOGGER.info("Sending message...");
			ctx.getBean(StringRedisTemplate.class).convertAndSend("chat", "Hello from Redis!");
			return null;
		}, null).awaitResultClose();
		Thread.sleep(1000);
		System.exit(0);
	}
}