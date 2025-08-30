package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

import pojo.User;

public class CSVReaderUtility {

	public static Iterator<User> fetchCSVData(String fileName) {
		/*String filePath=System.getProperty("user.dir")+"//TestData//"+fileName;
		File file=new File(filePath);
		List<User> userList = null;
		try {
			FileReader fileReader=new FileReader(file);
			CSVReader csvReader=new CSVReader(fileReader);
			csvReader.readNext(); //skipping first row of headers.
			User userData;
			userList=new ArrayList<User>();
			String[] line = null;
		
			while((line=csvReader.readNext() )!=null) {
				userData=new User(line[0],line[1]);
				userList.add(userData);
			}
			
			/*for(User user:userList) {
				System.out.println(user);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
       return userList.iterator();*/
		List<User> userList = new ArrayList<>();

		try (
		        InputStream inputStream = Thread.currentThread().getContextClassLoader()
		                .getResourceAsStream("TestData/" + fileName)
		    ) {
		        if (inputStream == null) {
		            throw new RuntimeException("CSV file not found in resources: TestData/" + fileName);
		        }

		        try (
		            InputStreamReader streamReader = new InputStreamReader(inputStream);
		            CSVReader csvReader = new CSVReader(streamReader)
		        ) {
		            csvReader.readNext(); // skip header
		            String[] line;
		            while ((line = csvReader.readNext()) != null) {
		                User userData = new User(line[0], line[1]);
		                userList.add(userData);
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return userList.iterator();
		}
	}


