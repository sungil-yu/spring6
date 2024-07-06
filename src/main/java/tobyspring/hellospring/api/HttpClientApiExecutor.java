package tobyspring.hellospring.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientApiExecutor implements ApiExecutor{
	@Override
	public String execute(URI uri) {
		HttpRequest request = HttpRequest.newBuilder()
			.uri(uri)
			.GET()
			.build();
		try (HttpClient client = HttpClient.newBuilder().build()) {
			return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
