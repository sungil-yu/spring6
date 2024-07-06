package tobyspring.hellospring.exrate;

import java.math.BigDecimal;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.ErApiExRateExtractor;
import tobyspring.hellospring.api.HttpClientApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {
    public ApiTemplate apiTemplate = new ApiTemplate();

    public BigDecimal getExRate(String currency) {
        String url = "https://v6.exchangerate-api.com/v6/f05a147b2d7951e5be05b02a/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}

