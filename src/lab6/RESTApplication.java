package lab6;

import lab6.example.service.AirqualityREST;
import lab6.example.service.SubstanceREST;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/*")
public class RESTApplication extends ResourceConfig {

    public RESTApplication() {

        packages("lab6");
        register(AirqualityREST.class);
        register(SubstanceREST.class);
        register(JacksonFeature.class);

    }
}
