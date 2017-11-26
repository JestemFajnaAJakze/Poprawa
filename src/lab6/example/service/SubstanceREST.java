package lab6.example.service;

import lab6.example.PATCH;
import lab6.example.RequestPOJO;
import lab6.example.ResponsePOJO;
import lab6.rest.pojo.CommentPOJO;
import lab6.rest.pojo.EntryPOJO;
import lab6.rest.pojo.SubstancePOJO;
import lab6.rest.service.Blog;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("")
public class SubstanceREST {

    //List<EntryPOJO> entrys = new ArrayList<>();
    List<SubstancePOJO> substances = new ArrayList<SubstancePOJO>();
   /* @PostConstruct
    private void Init() {
        substances = new ArrayList<SubstancePOJO>();
    }
*/
    @POST
    @Path("/substances/") //dodaje nowe substancje
    @Consumes(MediaType.APPLICATION_JSON)
    public SubstancePOJO addSubstance(SubstancePOJO substancePOJO){

        if(substancePOJO.getSubstanceId().isEmpty() || substancePOJO.getSubstanceName().isEmpty() || substancePOJO.getUnit().isEmpty()){
            return null;
            }else {
                for (int i = 0; i < substances.size(); i++) {
                    if (substances.get(i).getSubstanceId().equals(substancePOJO.getSubstanceId())) {
                        return null;
                    }
                }
                substances.add(substancePOJO);
            return substancePOJO;
            }
        }
    @GET
    @Path("/substances/") //zwraca wszystkie wpisy
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubstancePOJO> allSubstances(){
        return substances;
    }

    @GET
    @Path("/substances/{id}") //zwraca subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public SubstancePOJO getSubstances(@PathParam("id")String id) {
        for (int i = 0; i < substances.size(); i++) {
            if (substances.get(i).getSubstanceId().equals(id)) {
                return substances.get(i);
            }
        }
        return null;
    }

    @PUT
    @Path("/substances/{id}") //update subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public SubstancePOJO modifySubstance(SubstancePOJO substancePOJO,@PathParam("id")String id){
        for(int i = 0 ; i< substances.size() ; i++ ){
            if(substances.get(i).getSubstanceId().equals(id)){
                substances.get(i).setSubstanceId(substancePOJO.getSubstanceId());
                substances.get(i).setSubstanceName(substancePOJO.getSubstanceName());
                substances.get(i).setUnit(substancePOJO.getUnit());
                substances.get(i).setTreshold(substancePOJO.getTreshold());
                return substances.get(i);
            }
        }
     return null;
    }

    @PATCH
    @Path("/substances/{id}") //update subs o podanym id
    @Produces(MediaType.APPLICATION_JSON)
    public SubstancePOJO modifyTreshold(SubstancePOJO substancePOJO,@PathParam("id")String id){
        for(int i = 0 ; i<substances.size() ; i++ ){
            if(substances.get(i).getSubstanceId().equals(id)){
                substances.get(i).setTreshold(substancePOJO.getTreshold());
                return substances.get(i);
            }
        }
        return null;
    }


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
