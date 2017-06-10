package com.shortthirdman.core.microsoft.excel.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class is used to create the excel file for the given list of TWObject in
 * the given file path.
 */
public class XlsxWriterFactory {
 private SXSSFWorkbook workbook;

  /**
  * A no-constructor argument is needed. This allows BPM to create an
  * instance of this object when a method is invoked from BPM.
  */

  public XlsxWriter() {
  super();
  workbook = new SXSSFWorkbook(100);
 }

  /**
  * This method will handle the input from the calling application and
  * creates the excel file for the given input.
  * 
  * @param path
  *            - Location where excel file should get create
  * @param object
  *            - List of TW object from BPM
  * @param sheetName
  *            - sheet name in the excel file.
  * @throws ExcelWriterException
  */
 public File createExcelFile(String path, String columns, String header,
   String object) throws ExcelWriterException {

   File newFile = null;
  try {

    List<String> colModelList = new ArrayList<String>();

    JSONArray dataValue = new JSONArray(object);

    // If the user didnt select any column, default table column order
   // will be displayed
   if (columns != null && !columns.equalsIgnoreCase("")) {
    StringTokenizer colToken = new StringTokenizer(columns, ",");
    while (colToken.hasMoreTokens()) {
     colModelList.add(colToken.nextToken());
    }
   } else {
    JSONArray columnList = new JSONArray(header);
    for (int i = 0; i < columnList.length(); i++) {
     colModelList.add(columnList.get(i).toString());
    }
   }

    // Create a worksheet with given input values
   addSheet(dataValue, colModelList, "WOAccrual_Report");

    // Create an output stream to write the report to.
   newFile = new File(path);
   OutputStream output = new FileOutputStream(newFile);

    // Write the report to the output stream
   write(output);

    // Finally, save the report
   output.close();

   } catch (ParseException e) {
   System.out.println("");
  } catch (FileNotFoundException e) {
   System.out.println("");
  } catch (Exception e) {
   System.out.println("");
 }
  return newFile;
 }

  /**
  * This method used to create a worksheet and add to workbook.
  * 
  * @param data
  *            - data to be inserted in to worksheet
  * @param colModel
  *            - This is used to provide the column name.
  * @param sheetName
  *            - work sheet name.
  * @throws ExcelWriterException
  */
 private void addSheet(JSONArray data, List<String> colModel,
   String sheetName) throws ExcelWriterException {

   Sheet sheet = workbook.createSheet(sheetName);
  int numCols = colModel.size();
  int currentRow = 0;
  Row row;

   try {

    // Create the report header at row 0
   row = sheet.createRow(currentRow);
   // Loop over all the column beans and populate the report headers
   for (int i = 0; i < numCols; i++) {
    Cell cell = row.createCell(i);
    Font fn = workbook.createFont();
    fn.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
    CellStyle cs = workbook.createCellStyle();
    cs.setFont(fn);
    cell.setCellStyle(cs);
    cell.setCellValue(colModel.get(i).toString());
   }

    currentRow++; // increment the spreadsheet row before we step into
       // the data

    // Write report rows
   for (int i = 0; i < data.length(); i++) {
    // create a row in the spreadsheet
    row = sheet.createRow(currentRow++);
    // get the bean for the current row
    JSONObject bean = data.getJSONObject(i);

     // For each column object, create a column on the current row based on the col model
    for (int y = 0; y < numCols; y++) {
     Object proertyValue = bean.getString(colModel.get(y));
     String value;
     if (proertyValue != null) {
      value = proertyValue.toString();
     } else {
      value = "";
     }
     Cell cell = row.createCell(y);
     cell.setCellValue(value);
    }
   }

    // Autosize columns
   for (int i = 0; i < numCols; i++) {
    sheet.autoSizeColumn((short) i);
   }
   } catch (Exception e) {
	throw new ExcelWriterException(0, e.getMessage());
  }

  }

  /**
  * This method will write the values into output stream.
  * 
  * @param outputStream
  * @throws Exception
  */
 private void write(OutputStream outputStream) throws Exception {
  workbook.write(outputStream);
 }
}