package DataDrivenPractise_With_DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataPRoviderExample {

	DataFormatter dataFormatter = new DataFormatter();
	@DataProvider
	public Object[][] getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("C://Users//rames//DemoTestData.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheetAt(1);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();

		Object[][] data = new Object[rowCount-1][colCount];

		for(int i=0;i<rowCount-1;i++)
		{
			row = sheet.getRow(i+1);

			for(int j=0;j<colCount;j++)
			{
				//System.out.println( (row.getCell(j)));
				
				XSSFCell cell =row.getCell(j);
				data[i][j] = dataFormatter.formatCellValue(cell);
			}
		}

		return data;


	}


	@Test(dataProvider = "getData")
	public void TestDD(String txt1, String txt2, String txt3)
	{
		System.out.println(txt1 + " "+txt2+" " +txt3);
	}



}
