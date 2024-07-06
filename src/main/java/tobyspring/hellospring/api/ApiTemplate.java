package tobyspring.hellospring.api;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

	private final ApiExecutor apiExecutor;

	private final ExRateExtractor exRateExtractor;

	public ApiTemplate() {
		this.apiExecutor = new HttpClientApiExecutor();
		this.exRateExtractor = new ErApiExRateExtractor();
	}

	public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		this.apiExecutor = apiExecutor;
		this.exRateExtractor = exRateExtractor;
	}

	public BigDecimal getExRate(String url) {
		return getExRate(url, this.apiExecutor, this.exRateExtractor);
	}

	public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
		return getExRate(url, apiExecutor, this.exRateExtractor);
	}

	public BigDecimal getExRate(String url, ExRateExtractor exRateExtractor) {
		return getExRate(url, this.apiExecutor, exRateExtractor);
	}

	public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String res = apiExecutor.execute(uri);

		return exRateExtractor.extract(res);
	}

}
