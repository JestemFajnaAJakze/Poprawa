package lab6.example.service;

import lab6.rest.pojo.*;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("")
public class AirqualityREST {
    List<Station> stations = new ArrayList<>();
    private static final String REST_URI = "http://localhost:8080/lab6_v2Web/example";

    private Client restClient;
    private WebTarget resourceTarget;

    @PostConstruct
    private void Init() {
        for (int i = 1 ; i < 5 ; i++){
            Address address = new Address();
            address.setCity("Warszawa"+i);
            address.setStreet("Kochanowskiego"+i);
            Station station = new Station(""+i,address);
            stations.add(station);
        }
    }

    private void checkRepository(AirqualityPOJO airqualityPOJO){
        restClient = ClientBuilder.newClient();
        resourceTarget = restClient.target(REST_URI);
        WebTarget methodTarget = resourceTarget.path("/substance/");
        Invocation.Builder invocationBuilder = methodTarget.request(MediaType.APPLICATION_JSON);
        List<SubstancePOJO> substancesRepository = invocationBuilder.get(new GenericType<List<SubstancePOJO>>(){});
        for(int i = 0 ; i < airqualityPOJO.getSubstances().size(); i++){
            for(int j = 0 ; j < substancesRepository.size() ; j++){
                if(airqualityPOJO.getSubstances().get(i).getType().equals(substancesRepository.get(j))){
//                    if(substancesRepository.get(i).getTreshold() < airqualityPOJO.getSubstances(){}
                }

            }

        }
    }

    @POST
    @Path("/airquality/{stationId}") //dodaje nowe substancje
    @Produces(MediaType.APPLICATION_JSON)
    public AirqualityPOJO addAirquality(AirqualityPOJO airqualityPOJO, @PathParam("stationId") String stationId){
        System.out.println("--------------addAirquality------------");
        System.out.println(stationId);
        System.out.println(airqualityPOJO.getSubstances().size());


        for(int i = 0 ; i < stations.size(); i ++ ){
            //czy stacja istnieje
             if(stationId.equals(stations.get(i).getStationId())){
                 System.out.println("Dobra stacja");

                 System.out.println(airqualityPOJO);

                 stations.get(i).getAirqualities().add(airqualityPOJO);
                 System.out.println(stations.get(i).getAirqualities().size());

                 checkRepository(airqualityPOJO);
                 return airqualityPOJO;
           }
        }        System.out.println("return NULLL");

        return null;
       }

    /*@GET
    @Path("/airquality/") //zwraca wszystkie warunki pogodowe
    @Produces(MediaType.APPLICATION_JSON)
    public List<AirqualityPOJO> allAirqualities(){
        return airqualities;
    }

    @GET
    @Path("/airquality/{id}") //zwraca subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public AirqualityPOJO getSubstance(@PathParam("id")String id) {
        for(int i = 0 ; i < stations.size(); i ++ ){
            if(id.equals(stations.get(i).getStationId())){
                return airqualities.get(i);
            }
        }
        return null;
    }*/
    /*@GET
    @Path("/airquality/{stationId}/substance/{id}") //zwraca subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public List<Substance> getSubstances(@PathParam("id")String id) {
        for(int i = 0 ; i < stations.size(); i ++ ){
            if(id.equals(stations.get(i).getStationId())){
                Substance substance = new Substance();
                for (int j = 0 ; j < )
            }
        }
        return null;
    }


    public List<Substance>getSubstancesForStation(String stationId){
        stations.get(stationId);

return ;
}*/

/*    @PUT
    @Path("/substances/{id}") //update subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public String modifySubstance(SubstancePOJO substancePOJO,@PathParam("id")String id){
        for(int i = 0 ; i< substances.size() ; i++ ){
            if(substances.get(i).getSubstanceId().equals(id)){
                substances.get(i).setSubstanceId(substancePOJO.getSubstanceId());
                substances.get(i).setSubstanceName(substancePOJO.getSubstanceName());
                substances.get(i).setUnit(substancePOJO.getUnit());
                substances.get(i).setTreshold(substancePOJO.getTreshold());
                return "OK";
            }
        }
     return "There is no substance with this id";
    }*/

    /*@PATCH
    @Path("/substances/{id}") //update subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public String modifyTreshold(SubstancePOJO substancePOJO,@PathParam("id")String id){
        for(int i = 0 ; i<0 ; i++ ){
            if(substances.get(i).getSubstanceId().equals(id)){
                substances.get(i).setTreshold(substancePOJO.getTreshold());
                return "OK";
            }
        }
        return "There is no substance with this id";
    }
*/

   /* @PATCH
    @Path("/substances/{id}") //zwraca wpis o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public SubstancePOJO modifySubstance(SubstancePOJO substancePOJO,@PathParam("id")String id){
        SubstancePOJO substance = getSubstance(id);
        substance.setSubstanceId(substancePOJO.getSubstanceId());
        substance.setSubstanceName(substancePOJO.getSubstanceName());
        substance.setUnit(substancePOJO.getUnit());
        substance.setTreshold(substancePOJO.getTreshold());
        return substance;
    }
*/
/*

    @DELETE
    @Path("/blog/entry/{id}") //usuwa wpis o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntryPOJO> deleteEntry(@PathParam("id")String id){
        EntryPOJO entry = getEntryById(id);
        entrys.remove(entry);
        return entrys;
    }


    @PUT
    @Path("/blog/entry/{id}/comment") //dodaje komentarz do wpisu
    @Produces(MediaType.APPLICATION_JSON)
    public void addComment(@PathParam("id")String id, CommentPOJO commentPOJO){
        EntryPOJO entry = getEntryById(id);
        entry.getComments().add(commentPOJO);
    }

    @GET
    @Path("/blog/entry/{id}/comment") //wyświetla komentarze do wpisu
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommentPOJO> getComments(@PathParam("id")String id, CommentPOJO commentPOJO){
        EntryPOJO entry = getEntryById(id);
        return entry.getComments();
    }



    @DELETE
    @Path("/blog/entry/{id}/comment/{id2}") //usuwa komentarz o podanym id dla wskazanego wpisu
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteComment(@PathParam("id")String id, @PathParam("id2")String id2){
        EntryPOJO entry = getEntryById(id);
        entry.getComments().remove(getCommentById(entry, id2));
    }


    @DELETE
    @Path("/blog/entry") //usuwa wszystkie wpisy
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAllComments(){
        entrys = new ArrayList<>();
    }

    private CommentPOJO getCommentById(EntryPOJO entry, String id2) {
        int myId2 = Integer.parseInt(id2);
        for (CommentPOJO commentPOJO : entry.getComments()) {
            if (commentPOJO.getId() == myId2) {
                return commentPOJO;
            }
        }
        return null;
    }

//    @DELETE: /blog/entry/{id}/comment/{id} – usuwa komentarz o podanym id dla wskazanego wpisu
//


    private EntryPOJO getEntryById(String id) {
        int myId = Integer.parseInt(id);
        for (EntryPOJO pojo : entrys) {
            if (pojo.getId() == myId) {
                return pojo;
            }
        }
        return null;
    }
*/

//
//    @GET
//    @Path("/hello")
//    public String hello() {
//        return "Hello";
//    }
//
//    @GET
//    @Path("/hello/{name}")
//    public String hello(@PathParam("name")String name) {
//        return "Hello " + name;
//    }
//
//    @POST
//    @Path("/json/{name}/data/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String consumeJSON(@PathParam("name")String name,RequestPOJO requestPOJO) {
//        return name + " " + requestPOJO.getRequestData();
//    }
//
//
//    @GET
//    @Path("/json")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ResponsePOJO produceJSON() throws Exception {
//
//        ResponsePOJO responsePOJO = new ResponsePOJO();
//        responsePOJO.setName("response name");
//
//        responsePOJO.getAttributes().add("attribute_1");
//        responsePOJO.getAttributes().add("attribute_2");
//        responsePOJO.getAttributes().add("attribute_3");
//
//        return responsePOJO;
//    }
//
//


}
