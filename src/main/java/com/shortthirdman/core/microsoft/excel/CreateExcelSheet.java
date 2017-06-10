package com.shortthirdman.core.microsoft.excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
* @author Swetank Mohanty (shortthirdman)
*
*/
public class CreateExcelSheet {

	static SXSSFWorkbook  wb ;
	static Sheet sh ;

	private static void autoResizeColumns() {
		for(int colIndex = 0; colIndex < 10 ; colIndex++) {
			sh.autoSizeColumn(colIndex);
		}
	}

	private static CellStyle getHeaderStyle() {
		CellStyle style = wb.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);

	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setAlignment(CellStyle.ALIGN_CENTER);

	    return style;
	}

	private static CellStyle getNormalStyle() {
		CellStyle style = wb.createCellStyle();

	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setAlignment(CellStyle.ALIGN_CENTER);

	    return style;
	}

	public static void main(String[] args) throws Exception {
		wb =  new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
		sh = wb.createSheet("Sample sheet");

	    CellStyle headerStle= getHeaderStyle();
	    CellStyle normalStyle = getNormalStyle();

		 for(int rownum = 0; rownum < 1000; rownum++) {
	            Row row = sh.createRow(rownum);
	            for(int cellnum = 0; cellnum < 10; cellnum++) {
	                Cell cell = row.createCell(cellnum);
	                String address = new CellReference(cell).formatAsString();
	                cell.setCellValue(address);
	                if(rownum == 0) {
	                	cell.setCellStyle(headerStle);
	                } else {
	                	cell.setCellStyle(normalStyle);
	                }
	            }
	        }

		 sh.addMergedRegion(new CellRangeAddress(
		            0, //first row (0-based)
		            0, //last row  (0-based)
		            0, //first column (0-based)
		            5  //last column  (0-based)
		    ));

		 autoResizeColumns();

		 /**
		  * To Auto-resize Row, We have to follow two steps
		  * 1. Set WordWrap property in CellStyle to true
		  * 2. Set setHeightInPoints of row likw this :
		  *  	row.setHeightInPoints((totalHtmlLineBreak * sh.getDefaultRowHeightInPoints()));
		  *  	Where totalHtmlLineBreak is total lines for auto height
		  */

	        File f = new File("c:/DeleteThis/2/Example2.xlsx");

	        if(!f.exists()) {
	        	//If directories are not available then create it
	        	File parent_directory = f.getParentFile();
	        	if (null != parent_directory) {
	        	    parent_directory.mkdirs();
	        	}
	        	f.createNewFile();
	        }

	        FileOutputStream out = new FileOutputStream(f,false);
	        wb.write(out);
	        out.close();

	        // dispose of temporary files backing this workbook on disk
	        wb.dispose();
	        System.out.println("File is created");
	        //Launch Excel File Created
	        Desktop.getDesktop().open(f);
	}
}