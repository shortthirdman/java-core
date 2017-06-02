package com.shortthirdman.core.filesystem.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Date;
 
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.shortthirdman.core.filesystem.jackson.model.User;

public class JavaToPrettyJSON {
   public static void main(String[] args) {
      @SuppressWarnings("deprecation")
      User user = new User(1, "Lokesh", "Gupta", new Date(1981,8,18));
      ObjectMapper mapper = new ObjectMapper();
      try {
         mapper.defaultPrettyPrintingWriter().writeValue(new File("user-employee.json"), user);
      } catch (JsonGenerationException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}