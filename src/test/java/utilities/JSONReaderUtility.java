package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import pojo.TestData;
import pojo.User;

public class JSONReaderUtility {
	
	public static Iterator<Object[]> fetchJSONData(String fileName) {
	 /*Gson gson=new Gson();
	 String filePath=System.getProperty("user.dir")+"//TestData//"+fileName;
	 File file=new File(filePath);
	 List<Object[]> dataToReturn=null;
	 try {
		FileReader fileReader=new FileReader(file);
		TestData testData=gson.fromJson(fileReader, TestData.class);
		dataToReturn=new ArrayList<Object[]>();
		for(User user:testData.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	return  dataToReturn.iterator();*/
		
		 Gson gson = new Gson();
		    List<Object[]> dataToReturn = new ArrayList<>();

		    try (InputStream inputStream = Thread.currentThread()
		                                         .getContextClassLoader()
		                                         .getResourceAsStream("TestData/" + fileName)) {
		        if (inputStream == null) {
		            throw new FileNotFoundException("JSON file not found in resources/TestData: " + fileName);
		        }

		        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
		            TestData testData = gson.fromJson(reader, TestData.class);
		            for (User user : testData.getData()) {
		                dataToReturn.add(new Object[]{user});
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return dataToReturn.iterator();
	
	}
}
