package DataDrivenPractise_Without_DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DDTest {


	@Test
	public ArrayList<String> ddTest(String testCaseName) throws IOException
	{
		ArrayList<String> al = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C://Users//rames//DemoTestData.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(fis);
		
		int sheetsNumber = book.getNumberOfSheets();

		for(int i=0;i<sheetsNumber;i++)
		{
			if(book.getSheetName(i).equalsIgnoreCase("Sheet1"))
			{
				//sheet is a collection of Rows
				XSSFSheet sheet = book.getSheetAt(i);

				//Identify Testcase  column by scanning  the entire row
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();

				//row is a collection of Rows
				Iterator<Cell> cell = firstRow.cellIterator();

				int k=0 ;
				int column=0;
				while(cell.hasNext())
				{
					Cell cellValue =cell.next();
					if(cellValue.getStringCellValue().equalsIgnoreCase("TestCases"))
					{
						column=k;
					}
					k++;
				}
				System.out.println("The columns number is : "+column);

				while(rows.hasNext())
				{
					Row rowValue = rows.next();
					if(rowValue.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						Iterator<Cell>rowCellvalue = rowValue.cellIterator();
						while(rowCellvalue.hasNext())
						{
							Cell cellExtractType = rowCellvalue.next();
							
							if(cellExtractType.getCellType()==CellType.STRING)
							{
								al.add(cellExtractType.getStringCellValue());
							}
							else if(cellExtractType.getCellType()==CellType.NUMERIC)
							{
								al.add(NumberToTextConverter.toText(cellExtractType.getNumericCellValue()));
							}
							
							else
							{
								System.out.println("Data is not Formated");
							}
							
							//System.out.println(rowCellvalue.next().getStringCellValue());
						}
					}
				}

			}
		}
		return al;

	}

}
