package DataDrivenPractise_Without_DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class chatGPT_DDT_code {
	
	
	@Test
	public ArrayList<String> ddTest(String testCaseName) throws IOException {
	    ArrayList<String> al = new ArrayList<String>();

	    FileInputStream fis = new FileInputStream("C://Users//rames//DemoTestData.xlsx");
	    XSSFWorkbook book = new XSSFWorkbook(fis);

	    int sheetsNumber = book.getNumberOfSheets();

	    for (int i = 0; i < sheetsNumber; i++) {
	        if (book.getSheetName(i).equalsIgnoreCase("Sheet1")) {
	            // sheet is a collection of Rows
	            XSSFSheet sheet = book.getSheetAt(i);

	            // Identify Testcase column by scanning the entire row
	            Iterator<Row> rows = sheet.iterator();
	            Row firstRow = rows.next();

	            // row is a collection of Cells
	            Iterator<Cell> cell = firstRow.cellIterator();

	            int k = 0;
	            int column = 0;
	            while (cell.hasNext()) {
	                Cell cellValue = cell.next();
	                if (cellValue.getStringCellValue().equalsIgnoreCase("TestCases")) {
	                    column = k;
	                }
	                k++;
	            }
	            System.out.println("The columns number is : " + column);

	            while (rows.hasNext()) {
	                Row rowValue = rows.next();
	                if (rowValue.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
	                    Iterator<Cell> rowCellValue = rowValue.cellIterator();
	                    while (rowCellValue.hasNext()) {
	                        Cell cellExtractType = rowCellValue.next();

	                        switch (cellExtractType.getCellType()) {
	                            case STRING:
	                                al.add(cellExtractType.getStringCellValue());
	                                break;
	                            case NUMERIC:
	                                if (DateUtil.isCellDateFormatted(cellExtractType)) {
	                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                                    al.add(dateFormat.format(cellExtractType.getDateCellValue()));
	                                } else {
	                                    al.add(NumberToTextConverter.toText(cellExtractType.getNumericCellValue()));
	                                }
	                                break;
	                            case BOOLEAN:
	                                al.add(Boolean.toString(cellExtractType.getBooleanCellValue()));
	                                break;
	                            case FORMULA:
	                                al.add(cellExtractType.getCellFormula());
	                                break;
	                            default:
	                                al.add("Unsupported cell type");
	                        }
	                    }
	                }
	            }
	        }
	    }
	    return al;
	}

	

}


