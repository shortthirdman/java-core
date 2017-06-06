package com.shortthirdman.core.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Utility methods for resolving resource locations to files in the
 * file system. Mainly for internal use within the framework.
 *
 * <p>Consider using Spring's Resource abstraction in the core package
 * for handling all kinds of file resources in a uniform manner.
 * {@link org.springframework.core.io.ResourceLoader}'s <code>getResource</code>
 * method can resolve any location to a {@link org.springframework.core.io.Resource}
 * object, which in turn allows to obtain a <code>java.io.File</code> in the
 * file system through its <code>getFile()</code> method.
 *
 * <p>The main reason for these utility methods for resource location handling
 * is to support {@link Log4jConfigurer}, which must be able to resolve
 * resource locations <i>before the logging system has been initialized</i>.
 * Spring' Resource abstraction in the core package, on the other hand,
 * already expects the logging system to be available.
 *
 * @author Swetank Mohanty (shortthirdman)
 * @version 1.0.0
 * @see org.springframework.core.io.Resource
 * @see org.springframework.core.io.ClassPathResource
 * @see org.springframework.core.io.FileSystemResource
 * @see org.springframework.core.io.UrlResource
 * @see org.springframework.core.io.ResourceLoader
 */
public abstract class ResourceUtils {

  /** Pseudo URL prefix for loading from the class path: "classpath:" */
  public static final String CLASSPATH_URL_PREFIX = "classpath:";

  /** URL prefix for loading from the file system: "file:" */
  public static final String FILE_URL_PREFIX = "file:";

  /** URL protocol for a file in the file system: "file" */
  public static final String URL_PROTOCOL_FILE = "file";

  /** URL protocol for an entry from a jar file: "jar" */
  public static final String URL_PROTOCOL_JAR = "jar";

  /** URL protocol for an entry from a zip file: "zip" */
  public static final String URL_PROTOCOL_ZIP = "zip";

  /** URL protocol for an entry from a WebSphere jar file: "wsjar" */
  public static final String URL_PROTOCOL_WSJAR = "wsjar";

  /** URL protocol for an entry from an OC4J jar file: "code-source" */
  public static final String URL_PROTOCOL_CODE_SOURCE = "code-source";

  /** Separator between JAR URL and file path within the JAR */
  public static final String JAR_URL_SEPARATOR = "!/";


  /**
   * Return whether the given resource location is a URL:
   * either a special "classpath" pseudo URL or a standard URL.
   * @param resourceLocation the location String to check
   * @return whether the location qualifies as a URL
   * @see #CLASSPATH_URL_PREFIX
   * @see java.net.URL
   */
  public static boolean isUrl(String resourceLocation) {
    if (resourceLocation == null) {
      return false;
    }
    if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
      return true;
    }
    try {
      new URL(resourceLocation);
      return true;
    }
    catch (MalformedURLException ex) {
      return false;
    }
  }



  /**
   * Determine whether the given URL points to a resource in a jar file,
   * that is, has protocol "jar", "zip", "wsjar" or "code-source".
   * <p>"zip" and "wsjar" are used by BEA WebLogic Server and IBM WebSphere, respectively,
   * but can be treated like jar files. The same applies to "code-source" URLs on Oracle
   * OC4J, provided that the path contains a jar separator.
   * @param url the URL to check
   * @return whether the URL has been identified as a JAR URL
   */
  public static boolean isJarURL(URL url) {
    String protocol = url.getProtocol();
    return (URL_PROTOCOL_JAR.equals(protocol) ||
        URL_PROTOCOL_ZIP.equals(protocol) ||
        URL_PROTOCOL_WSJAR.equals(protocol) ||
        (URL_PROTOCOL_CODE_SOURCE.equals(protocol) && url.getPath().indexOf(JAR_URL_SEPARATOR) != -1));
  }

  /**
   * Extract the URL for the actual jar file from the given URL
   * (which may point to a resource in a jar file or to a jar file itself).
   * @param jarUrl the original URL
   * @return the URL for the actual jar file
   * @throws MalformedURLException if no valid jar file URL could be extracted
   */
  public static URL extractJarFileURL(URL jarUrl) throws MalformedURLException {
    String urlFile = jarUrl.getFile();
    int separatorIndex = urlFile.indexOf(JAR_URL_SEPARATOR);
    if (separatorIndex != -1) {
      String jarFile = urlFile.substring(0, separatorIndex);
      try {
        return new URL(jarFile);
      }
      catch (MalformedURLException ex) {
        // Probably no protocol in original jar URL, like "jar:C:/mypath/myjar.jar".
        // This usually indicates that the jar file resides in the file system.
        if (!jarFile.startsWith("/")) {
          jarFile = "/" + jarFile;
        }
        return new URL(FILE_URL_PREFIX + jarFile);
      }
    }
    else {
      return jarUrl;
    }
  }
}