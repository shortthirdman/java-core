package com.shortthirdman.core.microsoft.excel.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FilenameUtils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * 
 * This class is used to convert the xlsx and xlsm file format contents into a
 * XML string.
 * xmlbeans-2.5.0
 * dom4j-1.6.1
 * asm-3.1
 * poi-3.9
 * poi-ooxml-3.9
 * poi-ooxml-schemas-3.9
 * java-json
 */
public class ExcelToXMLOrJson implements Constants {

 static List<String> header = null;

 /**
  * This method called by external system.It will return XMLElement string or
  * json string to calling system.
  * 
  * @param ios
  *            - Input stream of file which need to convert
  * @param fileName
  *            - Absolute file pathk
  * @param sheetNames
  *            - Sheet names to parse.If its null system will parse all
  *            sheets.
  * @param emptyRowCount
  *            - Number of consecutive empty rows to stop reading the file.
  * @param headerRow
  *            - Header row number in the work sheet to find the total number
  *            of column.
  * @param maxLimit
  *            - Maximum number of rows to read
  * @param outputType
  *            - return output type - possible values(json, xml) Default is
  *            XML.
  * @return response -String based on output type.
  */
	public static String getXMLElementsFromExcel(InputStream ios, String fileName, String sheetNames, int emptyRowCount, int headerRow, int maxLimit, String outputType) {
  ExcelReaderResponseBuilder resBuilder = new ExcelReaderResponseBuilder();
  StringBuilder response = new StringBuilder();

  resBuilder.setStatus(STATUS_SUCCESS);
  resBuilder.setErrorCode(null);
  resBuilder.setErrorMessage(null);

  try {

   File fileObject = new File(fileName);
   String ext = FilenameUtils.getExtension(fileName);
   // Call the method to parse the excel file
   if (fileObject != null) {
    if (ext.equalsIgnoreCase("xls")) {
     if (outputType.equalsIgnoreCase("json")) {
      return convertToJson(XlsReader.readExcel(ios,
        sheetNames, resBuilder));
     } else {
      XlsReader.readExcel(ios, sheetNames, resBuilder);
     }
    } else {
     parseExcel(ios, sheetNames, (headerRow - 1), emptyRowCount,
       maxLimit, resBuilder);
    }
   }

  } catch (ExcelReaderException e) {
   resBuilder.setStatus(e.getStatus());
   resBuilder.setErrorCode(e.getErrorCode());
   resBuilder.setErrorMessage(e.getErrormessage());
   resBuilder.setLastPage(e.isLastPage());

  } catch (FileNotFoundException e) {
   resBuilder.setStatus(STATUS_FAILURE);
   resBuilder.setErrorCode(FILE_NOT_FOUND_EC);
   resBuilder.setErrorMessage(FILE_NOT_FOUND);
   resBuilder.setLastPage(true);
  } catch (Exception e) {
   resBuilder.setStatus(STATUS_FAILURE);
   resBuilder.setErrorCode(EXCEPTION_EC);
   resBuilder.setErrorMessage(e.getMessage());
   resBuilder.setLastPage(true);
  }

  if (resBuilder.getResponse() == null || resBuilder.getStatus() == 0) {
   response.append(
     "<?xml version=\"1.0\" encoding=\"us-ascii\"?> <response><data>")
     .append("</data><status>").append(resBuilder.getStatus())
     .append("</status><errorcode>")
     .append(resBuilder.getErrorCode())
     .append("</errorcode><errormessage>")
     .append(resBuilder.getErrorMessage())
     .append("</errormessage></response>");
  } else {
   response.append(
     "<?xml version=\"1.0\" encoding=\"us-ascii\"?><response><data>")
     .append(resBuilder.getResponse().toString())
     .append("</data><status>").append(resBuilder.getStatus())
     .append("</status><errorcode>")
     .append(resBuilder.getErrorCode())
     .append("</errorcode><errormessage>")
     .append(resBuilder.getErrorMessage())
     .append("</errormessage><headers>");

   for (int i = 0; i < header.size(); i++) {
    response.append("<header>" + header.get(i) + "</header>");
   }
   response.append("</headers></response>");
  }

  resBuilder = null;
  if (outputType.equalsIgnoreCase("json")) {
   return convertToJson(response.toString());
  }

  return response.toString();
 }

 /**
  * Start to process and creates XMLElement string.
  * 
  * @param file
  *            - This is file object for the given excel file
  * @param workSheetNames
  *            - List of work sheet names to read
  * @param headerRow
  *            - Header row number in the work sheet to find the total number
  *            of column.
  * @param emptyRowCount
  *            - Number of consecutive empty rows to stop reading the file
  * @param maxLimit
  *            - Maximum number of rows to read
  * @param resBuilder
  *            - Builder class object which holds output xml element values
  * 
  * @throws IOException
  * @throws ExcelReaderException
  */
 private static void parseExcel(InputStream ios, String workSheetNames,
   int headerRow, int emptyRowCount, int maxLimit,
   ExcelReaderResponseBuilder resBuilder) throws IOException,
   ExcelReaderException {
  OPCPackage container = null;
  InputStream stream = null;
  ArrayList<String> sheetNameList = new ArrayList<String>();
  try {
   // split the input list names
   if (workSheetNames != null && !workSheetNames.equalsIgnoreCase("")) {
    StringTokenizer strTokenizer = new StringTokenizer(
      workSheetNames, ",");
    while (strTokenizer.hasMoreElements()) {
     sheetNameList.add(strTokenizer.nextElement().toString()
       .trim().toLowerCase());
    }
   }
   container = OPCPackage.open(ios);
   ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(
     container);
   XSSFReader xssfReader = new XSSFReader(container);
   StylesTable styles = xssfReader.getStylesTable();
   XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader
     .getSheetsData();
   // If sheet name has given this part will get execute.
   if (!sheetNameList.isEmpty()) {
    while (iter.hasNext()) {
     stream = iter.next();
     String sheetName = iter.getSheetName();

     if (resBuilder.getStatus() == 0) {
      throw new ExcelReaderException(resBuilder.getStatus(),
        resBuilder.getErrorCode(),
        resBuilder.getErrorMessage(), true);
     }
     if (sheetNameList.contains(sheetName.toLowerCase())) {
      processSheet(styles, strings, stream, sheetName,
        headerRow, emptyRowCount, maxLimit, resBuilder);
      resBuilder.getResponse().append("</worksheet>");
     }

     sheetNameList.remove(sheetName.toLowerCase());
     stream.close();
    }
    // If sheet name has not given this part will process all
    // worksheet
   } else {
    while (iter.hasNext()) {
     stream = iter.next();
     String sheetName = iter.getSheetName();
     if (resBuilder.getStatus() == 0) {
      throw new ExcelReaderException(resBuilder.getStatus(),
        resBuilder.getErrorCode(),
        resBuilder.getErrorMessage(), true);
     }
     processSheet(styles, strings, stream, sheetName, headerRow,
       emptyRowCount, maxLimit, resBuilder);
     resBuilder.getResponse().append("</worksheet>");
     stream.close();
    }
   }

   // Identify the unavailable sheets
   if (!sheetNameList.isEmpty()) {

    throw new ExcelReaderException(STATUS_FAILURE,
      SHEET_NOT_AVAILABLE_EC, SHEET_NOT_AVAILABLE
        + sheetNameList.toString(), false);
   }

  } catch (InvalidFormatException e) {
   resBuilder.setResponse(null);
   throw new ExcelReaderException(STATUS_FAILURE, EXCEPTION_EC,
     e.getMessage(), false);

  } catch (SAXException e) {
   resBuilder.setResponse(null);
   throw new ExcelReaderException(STATUS_FAILURE, EXCEPTION_EC,
     e.getMessage(), false);
  } catch (OpenXML4JException e) {
   resBuilder.setResponse(null);
   throw new ExcelReaderException(STATUS_FAILURE, EXCEPTION_EC,
     e.getMessage(), false);
  } finally {
   if (stream != null) {
    stream.close();
   }
   if (container != null) {
    container.close();
   }
  }
 }

 /**
  * Start to process and creates XMLElement string.
  * 
  * @param file
  *            - This is file object for the given excel file
  * @param workSheetNames
  *            - List of work sheet names to read
  * @param headerRow
  *            - Header row number in the work sheet to find the total number
  *            of column.
  * @param emptyRowCount
  *            - Number of consecutive empty rows to stop reading the file
  * @param maxLimit
  *            - Maximum number of rows to read
  * @param resBuilder
  *            - Builder class object which holds output xml element values
  * 
  * @throws IOException
  * @throws ExcelReaderException
  */
 public static String getSheetNames(InputStream ios, String fileName) {
  OPCPackage container = null;
  InputStream stream = null;
  StringBuilder sheetNameXML = null;
  String result = null;
  try {
   File fileObject = new File(fileName);
   String ext = FilenameUtils.getExtension(fileName);
   if (fileObject != null) {
    if (ext.equalsIgnoreCase("xls")) {
     return convertToJson(XlsReader.getSheetNames(ios));
    } else {
     container = OPCPackage.open(ios);
     XSSFReader xssfReader = new XSSFReader(container);
     XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader
       .getSheetsData();
     sheetNameXML = new StringBuilder();
     sheetNameXML.append("<response>");

     while (iter.hasNext()) {
      stream = iter.next();
      String sheetName = iter.getSheetName();
      sheetNameXML.append("<worksheet>" + sheetName
        + "</worksheet>");
      stream.close();
     }
     sheetNameXML.append("</response>");
    }
   }

  } catch (InvalidFormatException e) {
   result = "<response><errorMessage>" + e.getMessage()
     + "</errorMessage></response>";

  } catch (OpenXML4JException e) {
   result = "<response><errorMessage>" + e.getMessage()
     + "</errorMessage></response>";
  } catch (IOException e) {
   result = "<response><errorMessage>" + e.getMessage()
     + "</errorMessage></response>";
  } catch (Exception e) {
   result = "<response><errorMessage>" + e.getMessage()
     + "</errorMessage></response>";
  } finally {
   if (stream != null) {
    try {
     stream.close();
    } catch (IOException e) {
     result = "<response><errorMessage>" + e.getMessage()
       + "</errorMessage></response>";
    }
   }
   if (container != null) {
    try {
     container.close();
    } catch (IOException e) {
     result = "<response><errorMessage>" + e.getMessage()
       + "</errorMessage></response>";
    }
   }
  }
  if (sheetNameXML != null) {
   result = sheetNameXML.toString();
  }
  return convertToJson(result);
 }

 /**
  * This method parse the work sheet.
  * 
  * @param styles
  *            - Styles on work sheet
  * @param strings
  *            - String table object
  * @param sheetInputStream
  *            - Input stream for work sheet
  * @param sheetName
  *            - Sheet name to read
  * @param headerRow
  *            - Header row number in the worksheet to find the total number
  *            of column.
  * @param finalEmptyRowCount
  *            - Number of consecutive empty rows to stop reading the file
  * @param maxLimit
  *            - Maximum number of rows to read
  * @param resBuilder
  *            - Builder class object which holds output xml element values
  * @return resBuilder - Worksheet data wrapped into object
  * @throws IOException
  * @throws SAXException
  */
 private static void processSheet(StylesTable styles,
   ReadOnlySharedStringsTable strings, InputStream sheetInputStream,
   String sheetName, int headerRow, int finalEmptyRowCount,
   int maxLimit, ExcelReaderResponseBuilder resBuilder)
   throws IOException, SAXException {

  ContentHandler handler = null;
  InputSource sheetSource = new InputSource(sheetInputStream);
  SAXParserFactory saxFactory = SAXParserFactory.newInstance();
  ExcelReaderSheetHandlerV1 sheetHandler = null;
  try {
   SAXParser saxParser = saxFactory.newSAXParser();
   XMLReader sheetParser = saxParser.getXMLReader();
   resBuilder.getResponse().append(
     "<worksheet sheetName=\"" + sheetName + "\">");
   // Receive notification of the logical content of a document.
   sheetHandler = new ExcelReaderSheetHandlerV1(finalEmptyRowCount,
     headerRow, maxLimit, sheetName);
   handler = new XSSFSheetXMLHandler(styles, strings, sheetHandler,
     false);
   sheetParser.setContentHandler(handler);
   sheetParser.parse(sheetSource);
   resBuilder.setStatus(STATUS_SUCCESS);
   resBuilder.setErrorCode(null);
   resBuilder.setErrorMessage(null);
  } catch (ExcelReaderException e) {
   resBuilder.setStatus(e.getStatus());
   resBuilder.setErrorCode(e.getErrorCode());
   resBuilder.setErrorMessage(e.getErrormessage());

  } catch (ParserConfigurationException e) {
   throw new ExcelReaderException(STATUS_FAILURE, EXCEPTION_EC,
     e.getMessage(), true);
  } finally {
   sheetInputStream.close();
  }

  if (sheetHandler.getResponse() != null)
   resBuilder.getResponse().append(
     sheetHandler.getResponse().toString());
  header = sheetHandler.getHeader();
 }

 private static String convertToJson(String xmlString) {
  JSONObject xmlJSONObj;
  try {
   xmlJSONObj = XML.toJSONObject(xmlString);
   return xmlJSONObj.toString(4);

  } catch (JSONException e) {
   e.printStackTrace();
  }

  return null;
 }


 public static void main(String args[]) {
  FileInputStream io = new FileInputStream(new File("your file"));
  String objList = getXMLElementsFromExcel(io,
    "filename.xlsx", "sheet name", 5,
    1, 10000,"fileType(json or xml)");
  System.out.println(objList);

 }

}