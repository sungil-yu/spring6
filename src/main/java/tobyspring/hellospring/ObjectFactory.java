package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tobyspring.hellospring.api.ApiExecutor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.exrate.CacheWebApiExRateProvider;
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
		return new WebApiExRateProvider();
	}

	@Bean
	public ApiExecutor apiExecutor() {
		return new SimpleApiExecutor();
	}

}
