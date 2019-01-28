package redis.spring.gs;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CoffeeLoader {
	private final ReactiveRedisConnectionFactory factory;
	private final ReactiveRedisOperations<String, Coffee> coffeeOps;

	public CoffeeLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Coffee> coffeeOps) {
		this.factory = factory;
		this.coffeeOps = coffeeOps;
	}

	@PostConstruct
	public void loadData() {
//		factory.getReactiveConnection().serverCommands().flushAll().thenMany(
//				Flux.just("Jet Black Redis1", "Darth Redis1", "Black Alert Redis1")
//						.map(name -> new Coffee(UUID.randomUUID().toString(), name))
//						.flatMap(coffee -> coffeeOps.opsForValue().set(coffee.getId(), coffee)))
//				.thenMany(coffeeOps.keys("*").flatMap(coffeeOps.opsForValue()::get))
//				.subscribe(System.out::println);
	}
}