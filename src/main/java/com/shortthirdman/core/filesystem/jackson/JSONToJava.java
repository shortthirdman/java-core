package com.shortthirdman.core.filesystem.jackson;
 
import java.io.File;
import java.io.IOException;
 
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.shortthirdman.core.filesystem.jackson.model.User;

public class JSONToJava {
   public static void main(String[] args) {
      User employee = null;
      ObjectMapper mapper = new ObjectMapper();
      try {
         employee =  mapper.readValue(new File("user-employee.json"), User.class);
      } catch (JsonGenerationException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.out.println(employee);
   }
}