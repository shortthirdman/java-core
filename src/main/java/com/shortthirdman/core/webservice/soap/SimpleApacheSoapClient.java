package com.shortthirdman.core.webservice.soap;

import java.net.URL;
import java.util.Vector;

import org.apache.soap.Constants;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;

public class SimpleApacheSoapClient {
  public static void main( String[] args ) throws Exception {
    // soap service endpoint
    //URL url = new URL("http://services.xmethods.com:80/soap/servlet/rpcrouter");
    URL url = new URL( "http://localhost:6666/soap/servlet/rpcrouter" );
    // create a call
    Call call = new Call();

    // Service uses standard SOAP encoding
    call.setEncodingStyleURI( Constants.NS_URI_SOAP_ENC );

    // Set service locator parameters
    call.setTargetObjectURI( "urn:xmethods-Temperature" );
    call.setMethodName( "getTemp" );

    // Create input parameter vector
    Vector params = new Vector();
    params.addElement( new Parameter( "zipcode", String.class, "94041", null ) );
    call.setParams( params );

    // Invoke the service, note that an empty SOAPActionURI of
    // "" indicates that intent of the SOAP request is taken to
    // be the request URI
    Response resp = call.invoke( url, "" );

    // ... and evaluate the response
    if( resp.generatedFault() ) {
      throw new Exception();
    }
    else {
      // Call was successful. Extract response parameter and return result
      Parameter result = resp.getReturnValue();
      System.out.println( "temperature is -> " + result.getValue() );
    }
  }
}
