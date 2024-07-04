package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class CacheWebApiExRateProvider implements ExRateProvider{

    private final WebApiExRateProvider webApiExRateProvider;
    private static BigDecimal cacheExRate;

    public CacheWebApiExRateProvider() {
        this.webApiExRateProvider = new WebApiExRateProvider();
    }


    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if(cacheExRate == null) {
            cacheExRate = webApiExRateProvider.getExRate(currency);
        }

        System.out.printf("cache web api response");
        return cacheExRate;
    }
}
