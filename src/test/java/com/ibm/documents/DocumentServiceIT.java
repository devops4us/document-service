package com.ibm.documents;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.junit.BeforeClass;
import org.junit.Test;

public class DocumentServiceIT {

	private static WebTarget target;

	@BeforeClass
	public static void initialize() {
		Client client= ClientBuilder.newClient();
		String targetProtocol=System.getenv("DOCSERVICE_PROTOCOL");
		String targetHost=System.getenv("DOCSERVICE_HOST");
		String targetPort=System.getenv("DOCSERVICE_PORT");
		String targetUrl=String.format("%s://%s:%s", targetProtocol, targetHost, targetPort);
		System.out.format("url=%s%n", targetUrl);
		target= client.target(targetUrl);
	}
	
	@Test
	public void testStatus() {
		Response response= target.path("/status").request(MediaType.TEXT_PLAIN).get();
		assertTrue("status request must be successful", response.getStatusInfo().getFamily().equals(Family.SUCCESSFUL));
		String responseData= response.readEntity(String.class);
		assertNotNull("status response must not be null", responseData);				
	}
}
