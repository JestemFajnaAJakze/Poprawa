package lab6.example.service;

import lab6.example.PATCH;
import lab6.rest.pojo.EntryPOJO;
import lab6.rest.pojo.SubstancePOJO;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("")
public class SubstanceREST {

    private static final String REST_URI = "http://localhost:8181/lab6_v2Web";
    private Client restClient;
    private WebTarget resourceTarget;

    List<SubstancePOJO> substances = new ArrayList<SubstancePOJO>();

    @POST
    @Path("/substances/") //dodaje nowe substancje
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSubstance(SubstancePOJO substancePOJO) {
        if (substancePOJO.getSubstanceId().isEmpty() || substancePOJO.getSubstanceName().isEmpty() || substancePOJO.getUnit().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            for (int i = 0; i < substances.size(); i++) {
                if (substances.get(i).getSubstanceId().equals(substancePOJO.getSubstanceId())) {
                    return Response.status(Response.Status.CONFLICT).build();
                }
            }
            substances.add(substancePOJO);
        }
        updateEmulator();
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/substances/") //zwraca wszystkie wpisy
    @Produces(MediaType.APPLICATION_JSON)
    public Response allSubstances() {
        if (!substances.isEmpty()) {
            return Response.ok(substances, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Path("/substances/{id}") //zwraca subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubstances(@PathParam("id") String id) {
        for (int i = 0; i < substances.size(); i++) {
            if (substances.get(i).getSubstanceId().equals(id)) {
                return Response.ok(substances.get(i), MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/substances/{id}") //update subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifySubstance(SubstancePOJO substancePOJO, @PathParam("id") String id) {
        for (int i = 0; i < substances.size(); i++) {
            if (substances.get(i).getSubstanceId().equals(id)) {
                substances.get(i).setSubstanceId(substancePOJO.getSubstanceId());
                substances.get(i).setSubstanceName(substancePOJO.getSubstanceName());
                substances.get(i).setUnit(substancePOJO.getUnit());
                substances.get(i).setTreshold(substancePOJO.getTreshold());
                updateEmulator();
                return Response.ok(substances.get(i), MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/substances/{id}") //update subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyTreshold(SubstancePOJO substancePOJO, @PathParam("id") String id) {
        for (int i = 0; i < substances.size(); i++) {
            if (substances.get(i).getSubstanceId().equals(id)) {
                substances.get(i).setTreshold(substancePOJO.getTreshold());
                updateEmulator();
                return Response.ok(substances.get(i), MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }



    private void updateEmulator() {
        restClient = ClientBuilder.newClient();
        resourceTarget = restClient.target(REST_URI);
        WebTarget methodTarget = resourceTarget.path("/substances/");
        Invocation.Builder invocationBuilder = methodTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.post(Entity.entity(substances, MediaType.APPLICATION_JSON));
    }

}
