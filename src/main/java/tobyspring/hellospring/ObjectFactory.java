package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import tobyspring.hellospring.api.ApiExecutor;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.exrate.CacheWebApiExRateProvider;
import tobyspring.hellospring.exrate.RestTemplateExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

@Configuration
public class ObjectFactory {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(cacheWebApiExRateProvider());
	}

	@Bean
	public ExRateProvider cacheWebApiExRateProvider() {
		return new CacheWebApiExRateProvider(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new RestTemplateExRateProvider(restTemplate());
	}

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// @Bean
	// public ExRateProvider exRateProvider() {
	// 	return new WebApiExRateProvider(apiTemplate());
	// }

	@Bean
	public ApiTemplate apiTemplate() {
		return new ApiTemplate();
	}

}
