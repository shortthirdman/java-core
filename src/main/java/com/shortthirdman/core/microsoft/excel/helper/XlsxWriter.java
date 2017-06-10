package com.shortthirdman.core.microsoft.excel.helper;

import com.lombardisoftware.client.persistence.TWClass;
import com.lombardisoftware.data.twclass.TWClassDefinitionData;
import com.lombardisoftware.data.twclass.TWClassProperty;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import teamworks.TWList;
import teamworks.TWObject;

public class XlsxWriter {
    private SXSSFWorkbook workbook = new SXSSFWorkbook(100);

    public void createMultipleExcelFile(String path, TWObject object, TWList sheetNameList) throws ExcelWriterException {
        try {
            List<String> varList = this.getPropertyList(object);
            for (int i = 0; i < varList.size(); ++i) {
                TWList proertyValue = (TWList)object.getPropertyValue(varList.get(i));
                List<String> colModelList = this.getPropertyList((TWObject)proertyValue.getArrayData(0));
                this.addSheet(proertyValue, colModelList, sheetNameList.getArrayData(i).toString());
            }
            FileOutputStream output = new FileOutputStream(path);
            this.write(output);
            output.close();
        } catch (Exception e) {
            throw new ExcelWriterException(0, e.getMessage());
        }
    }

    public void createExcelFile(String path, TWList object, String sheetName) throws ExcelWriterException {
        try {
            ArrayList<String> colModelList = new ArrayList();
            TWObject report = (TWObject)object.getArrayData(0);
            colModelList = this.getPropertyList(report);
            this.addSheet(object, colModelList, sheetName);
            FileOutputStream output = new FileOutputStream(path);
            this.write(output);
            output.close();
        } catch (ParseException e) {
            throw new ExcelWriterException(0, e.getMessage());
        } catch (FileNotFoundException e) {
            throw new ExcelWriterException(0, e.getMessage());
        } catch (Exception e) {
            throw new ExcelWriterException(0, e.getMessage());
        }
    }

    private void addSheet(TWList data, List<String> colModel, String sheetName) throws ExcelWriterException {
        Sheet sheet = this.workbook.createSheet(sheetName);
        int numCols = colModel.size();
        int currentRow = 0;
        try {
            int i;
            Row row = sheet.createRow(currentRow);
            for (i = 0; i < numCols; ++i) {
                Cell cell = row.createCell(i);
                Font fn = this.workbook.createFont();
                fn.setBoldweight(700);
                CellStyle cs = this.workbook.createCellStyle();
                cs.setFont(fn);
                cell.setCellStyle(cs);
                cell.setCellValue(colModel.get(i).toString());
            }
            ++currentRow;
            for (i = 0; i < data.getArraySize(); ++i) {
                row = sheet.createRow(currentRow++);
                TWObject bean = (TWObject)data.getArrayData(i);
                for (int y = 0; y < numCols; ++y) {
                    Object proertyValue = bean.getPropertyValue(colModel.get(y));
                    String value = proertyValue != null ? proertyValue.toString() : "";
                    Cell cell = row.createCell(y);
                    cell.setCellValue(value);
                }
            }
            for (i = 0; i < numCols; ++i) {
                sheet.autoSizeColumn((int)((short)i));
            }
        } catch (Exception e) {
            throw new ExcelWriterException(0, e.getMessage());
        }
    }

    private void write(OutputStream outputStream) throws Exception {
        this.workbook.write(outputStream);
    }

    private List<String> getPropertyList(TWObject twObj) throws ExcelWriterException {
        ArrayList<String> variables = null;
        try {
            variables = new ArrayList<String>();
            List defnPropList = ((com.lombardisoftware.core.TWObject)twObj).getTWClass().getDefinition().getProperties();
            for (TWClassProperty prop : defnPropList) {
                variables.add(prop.getName());
            }
        } catch (Exception e) {
            throw new Exception(0, e.getMessage());
        }
        return variables;
    }
}
