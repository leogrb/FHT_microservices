package at.technikumwien.servicegateway.fallback;

import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixTimeoutException;

import lombok.extern.java.Log;

@Component
@Log
public class BonusServiceFallback implements FallbackProvider {
	private static final String RESPONSE_MESSAGE = "Bonus service is currently not available";

	@Override
	public String getRoute() {
		return "bonusservice";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		log.info("Fallback response triggered for bonus service. Cause: " + cause.getMessage());
		if (cause instanceof HystrixTimeoutException) {
			return FallbackHttpResponse.response(HttpStatus.GATEWAY_TIMEOUT, RESPONSE_MESSAGE);
		} else if (cause instanceof ClientException || cause instanceof ConnectTimeoutException) {
			return FallbackHttpResponse.response(HttpStatus.SERVICE_UNAVAILABLE, RESPONSE_MESSAGE);
		} else {
			return FallbackHttpResponse.response(HttpStatus.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE);
		}
	}
}