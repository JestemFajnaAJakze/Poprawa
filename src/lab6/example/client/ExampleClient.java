package lab6.example.client;


import lab6.example.ResponsePOJO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

public class ExampleClient {

    private static final Logger LOG = LogManager.getLogger(ExampleClient.class);

    private static final String REST_URI = "http://localhost:8080/lab6_v2Web/";

    private Client restClient;
    private WebTarget resourceTarget;

    public ExampleClient() {
        restClient = ClientBuilder.newClient();
        resourceTarget = restClient.target(REST_URI);
    }

    public String consumeJSON(String name) {
        WebTarget methodTarget = resourceTarget.path("/hello/" + name);

        Invocation.Builder invocationBuilder = methodTarget.request();
        String response = invocationBuilder.get(String.class);

        return response;
    }

    public ResponsePOJO getJSON() {
        WebTarget methodTarget = resourceTarget.path("/json");
        Invocation.Builder invocationBuilder = methodTarget.request(MediaType.APPLICATION_JSON);

        ResponsePOJO responsePOJO = invocationBuilder.get(ResponsePOJO.class);

        return responsePOJO;
    }


    public static void main(String[] args) {

        ExampleClient exampleClient = new ExampleClient();

        String responseString = exampleClient.consumeJSON("John");

        LOG.debug(responseString);

        ResponsePOJO responsePOJO = exampleClient.getJSON();

        if(responsePOJO != null) {
            LOG.debug(responsePOJO);
        }

    }

}
