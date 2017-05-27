package com.shortthirdman.core.webservice.rest;

import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
 
@Path("/download")
public class DownloadService {
 
    @GET
    @Path("/service-record")
    @Produces("application/pdf")
    public Response getFile() {
  
        File file = new File("C:\\java2novice\\employee_1415.pdf");
  
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=\"employee_1415.pdf\"");
        return response.build();
    }
}
