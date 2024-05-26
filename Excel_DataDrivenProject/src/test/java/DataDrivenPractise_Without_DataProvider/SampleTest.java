package DataDrivenPractise_Without_DataProvider;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

public class SampleTest {

	@Test()
	public void test() throws IOException
	{
		DDTest t = new DDTest();

		chatGPT_DDT_code gpt = new chatGPT_DDT_code();

		ArrayList data =	t.ddTest("Delete Profile");

		System.out.println( data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));

		ArrayList data1 =	gpt.ddTest("Add Profile");

		System.out.println( data1.get(0));
		System.out.println(data1.get(1));
		System.out.println(data1.get(2));
		System.out.println(data1.get(3));
	}

}
