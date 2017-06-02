package com.shortthirdman.core.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 *
 * @author Swetank Mohanty (shortthirdman)
 *
*/
public class FileHelper {

  public String getFilePathToSave() {
	Properties prop = new Properties();
	String filePath = "";

	try {
	  InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

	  prop.load(inputStream);
	  filePath = prop.getProperty("json.filepath");

    } catch (IOException e) {
		e.printStackTrace();
	}
	return filePath;
  }
  
  public static String getFilePathToSave() {
	Properties prop = new Properties();
	String filePath = "";

	try {
	  InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream("config.properties");

	  prop.load(inputStream);
	  filePath = prop.getProperty("json.filepath");

	} catch (IOException e) {
		e.printStackTrace();
	}
	return filePath;
  }
}

/*
FileHelper helper = new FileHelper();
String filePath = helper.getFilePathToSave();


// static
String filePath = FileHelper.getFilePathToSave();
*/