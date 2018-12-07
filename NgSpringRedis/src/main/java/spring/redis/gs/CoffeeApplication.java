package spring.redis.gs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 过于复杂了 java+redis的语法混合带来了很高的应用成本
 */
@SpringBootApplication
public class CoffeeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CoffeeApplication.class, args);
	}
}