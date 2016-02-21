package example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import static org.apache.commons.lang.Validate.notEmpty;

/**
 * Created by irvincardenas on 2/21/16.
 */
@Path("pureService")
public class ProxyService {

    // http://jsonplaceholder.typicode.com/posts
    @GET
    @Path("{serviceUrl}")
    @Produces("application/json")
    public String pureJson(@PathParam("serviceUrl") String serviceUrl){

        notEmpty(serviceUrl, "Service URL required");

        Client client = Client.create();
        WebResource webResource = client
                .resource(serviceUrl);

        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if(response.getStatus() != 200) {
            throw new RuntimeException("Failed HTTP Request to Pure: Error code: "
            + response.getStatus());
        }

        String output = response.getEntity(String.class);

        return output;
    }

    private static boolean isValidURL(String proxyServiceUrl) {
        return proxyServiceUrl.contains("");
    }
}
