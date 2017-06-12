package com.shortthirdman.core.util;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelToJsonConverter {
    public static void uploadXLS(MultipartFile file, Document doc) throws IOException {
        Products products = new Products();
        List<Products> productsList = new ArrayList<Products>();
        logger.info("uploadExcel method");
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(file.getInputStream());
            System.out.println("Workbook: " + wb);
        
            HSSFSheet sheet = wb.getSheetAt(0);
            System.out.println("Worksheet: " + sheet);
        
            HSSFRow row;

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                products = new Products();
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                Cell cell = cellIterator.next(); 
                Iterator cells = nextRow.cellIterator();

                cell=(HSSFCell) cells.next();
                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    System.out.print(cell.getStringCellValue() + " ");
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    System.out.print(cell.getNumericCellValue() + " ");
                } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                } else {
                    //U Can Handel Boolean, Formula, Errors
                }

                products.setId(new DataFormatter().formatCellValue(nextRow.getCell(0)));
                products.setProductId(new DataFormatter().formatCellValue(nextRow.getCell(1)));
                products.setNameId(new DataFormatter().formatCellValue(nextRow.getCell(2)));
                products.setName(new DataFormatter().formatCellValue(nextRow.getCell(3)));
                products.setDesc(new DataFormatter().formatCellValue(nextRow.getCell(4)));
                products.setDimension(new DataFormatter().formatCellValue(nextRow.getCell(5)));
                products.setCategory(new DataFormatter().formatCellValue(nextRow.getCell(6)));
                products.setSubcategory((new DataFormatter().formatCellValue(nextRow.getCell(7))));
                products.setCategoryId(new DataFormatter().formatCellValue(nextRow.getCell(8)));
                products.setSubcategoryId((new DataFormatter().formatCellValue(nextRow.getCell(9))));
                products.setTags((new DataFormatter().formatCellValue(nextRow.getCell(10))));
                products.setDesigner((new DataFormatter().formatCellValue(nextRow.getCell(11))));
                products.setCurr((new DataFormatter().formatCellValue(nextRow.getCell(12))));
                products.setPopularity((new DataFormatter().formatCellValue(nextRow.getCell(13))));
                products.setRelevance((new DataFormatter().formatCellValue(nextRow.getCell(14))));
                products.setShortlisted((new DataFormatter().formatCellValue(nextRow.getCell(15))));
                products.setLikes((new DataFormatter().formatCellValue(nextRow.getCell(16))));
                products.setCreateDt((new DataFormatter().formatCellValue(nextRow.getCell(17))));
                products.setPageId((new DataFormatter().formatCellValue(nextRow.getCell(18))));
                products.setStyleName((new DataFormatter().formatCellValue(nextRow.getCell(19))));
                products.setStyleId((new DataFormatter().formatCellValue(nextRow.getCell(20))));
                products.setPriceRange((new DataFormatter().formatCellValue(nextRow.getCell(21))));
                products.setPriceId((new DataFormatter().formatCellValue(nextRow.getCell(22))));
                products.setDefaultPrice((new DataFormatter().formatCellValue(nextRow.getCell(23))));
                products.setDefaultMaterial((new DataFormatter().formatCellValue(nextRow.getCell(24))));
                products.setDefaultFinish((new DataFormatter().formatCellValue(nextRow.getCell(25))));

                Map<String, String> inputMap = new HashMap<String, String>();
                inputMap.put("name", "Java2Novice");
                inputMap.put("site", "http://java2novice.com");
                productsList.add(products);
                System.out.println(productsList);

                // JSON CONVERTER
                ObjectMapper mapper = new ObjectMapper();

                System.out.println("productsList: " + products);
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                String location = dateFormat.format(date);
                System.out.println("productsList final: " + products);

                // Convert object to JSON string and save into file directly
                mapper.writeValue(new File("D:\\" + location + "mygubbi.json"), productsList);
                // Convert object to JSON string and save into file directly
                mapper.writeValue(new File("D:\\products.json"), productsList);

                // Convert object to JSON string
                String jsonInString = mapper.writeValueAsString(productsList);
                System.out.println("JsonInString " + jsonInString);

                // Convert object to JSON string and pretty print
                jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
                System.out.println("Final Json " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));
                
                mapper.writeValue(new File("D:\\productsJson.json"), jsonInString);
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}