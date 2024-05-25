package dev.swanhtet.godaung.Config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig implements CachingConfigurer {

	@Bean
	@Override
	public CacheManager cacheManager(){
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("products");
	//	cacheManager.setAllowNullValues(false);
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}

	Caffeine<Object, Object> caffeineCacheBuilder(){
		return  Caffeine.newBuilder()
				.initialCapacity(100)
				.maximumSize(500)
				.expireAfterWrite(5, TimeUnit.MINUTES)
				.recordStats();
	}


}
