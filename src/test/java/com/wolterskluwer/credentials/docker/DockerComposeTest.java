package com.wolterskluwer.credentials.docker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

/**
 * @author aqueenni
 *
 * 12 Nov 2024
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class DockerComposeTest {

	private static final String COMPOSE_FILE_PATH = "docker-compose.yaml";

	private static DockerComposeContainer environment;

	@BeforeAll
	public static void setUp() throws IOException {
		environment = new DockerComposeContainer(new File(COMPOSE_FILE_PATH))
				.withExposedService("app", 8080, Wait.forListeningPort()) 
				.withExposedService("envoy", 8081, Wait.forListeningPort()) 
				.withExposedService("web", 8082, Wait.forListeningPort()); 

		environment.start();
	}

	@Test
	public void testAppServiceIsRunning() {
		String appUrl = "http://localhost:" + environment.getServicePort("app", 8080);
		assertTrue(isServiceRunning(appUrl), "App service should be running and accessible");
	}

	@Test
	public void testEnvoyServiceIsRunning() {
		String envoyUrl = "http://localhost:" + environment.getServicePort("envoy", 8081);
		assertTrue(isServiceRunning(envoyUrl), "Envoy service should be running and accessible");
	}

	@Test
	public void testWebServiceIsRunning() {
		String webUrl = "http://localhost:" + environment.getServicePort("web", 8082);
		assertTrue(isServiceRunning(webUrl), "Web service should be running and accessible");
	}

	private boolean isServiceRunning(String url) {

		try {
			java.net.HttpURLConnection connection = (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			int responseCode = connection.getResponseCode();
			connection.disconnect();

			return responseCode == 200;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
