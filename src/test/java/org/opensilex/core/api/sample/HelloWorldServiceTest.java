package org.opensilex.core.api.sample;


import org.opensilex.core.api.sample.HelloWorldService;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.opensilex.core.OpensilexTest;

/**
 * Test class for HelloWorldService
 */
public class HelloWorldServiceTest extends OpensilexTest {

    @Override
    protected Application configure() {
        return configure(HelloWorldService.class);
    }
    
    @Test
    public void testHelloWorldResponse() {
        Response response = target("/hello").request().get();

        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        String content = response.readEntity(String.class);
        assertEquals("Content of response is: ", "Hello World !", content);
    }
}
