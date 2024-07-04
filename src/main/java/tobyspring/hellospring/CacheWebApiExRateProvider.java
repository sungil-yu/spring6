package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CacheWebApiExRateProvider implements ExRateProvider{

    private final ExRateProvider webApiExRateProvider;
    private static BigDecimal cacheExRate;
    private LocalDateTime cacheExpiryTime;

    public CacheWebApiExRateProvider(ExRateProvider webApiExRateProvider) {
        this.webApiExRateProvider = webApiExRateProvider;
    }
    public BigDecimal getExRate(String currency) throws IOException {
        if(cacheExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cacheExRate = webApiExRateProvider.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            return cacheExRate;
        }

        System.out.println("cache used");
        return cacheExRate;
    }
}
