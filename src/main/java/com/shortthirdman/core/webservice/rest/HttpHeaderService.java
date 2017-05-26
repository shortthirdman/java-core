package com.shortthirdman.core.webservice.restful;
 
import java.util.Set;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * @author Swetank Mohanty
 *
 */ 
@Path("/http-header")
public class HttpHeaderService {
 
    @GET
    @Path("query")
    public Response queryHeaderInfo(@Context HttpHeaders httpHeaders){
         
        /** how to get specific header info? **/
        String cacheControl = httpHeaders.getRequestHeader("Cache-Control").get(0);
        System.out.println("Cache-Control: "+cacheControl);
        /** get list of all header parameters from request **/
        Set<String> headerKeys = httpHeaders.getRequestHeaders().keySet();
        for(String header:headerKeys){
            System.out.println(header+":"+httpHeaders.getRequestHeader(header).get(0));
        }
        return Response.status(200).entity("successfully queried header info").build();
    } 
}
