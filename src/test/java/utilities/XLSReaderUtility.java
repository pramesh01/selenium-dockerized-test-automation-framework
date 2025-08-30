package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pojo.User;

public class XLSReaderUtility {

	public static Iterator<User> fetchXLSData(String fileName) {

		/*String xlsFilepath = System.getProperty("user.dir") + "//TestData//" + fileName;
		File file = new File(xlsFilepath);
		XSSFWorkbook xssfWorkbook = null;
		List<User> userList = null;
		try {
			xssfWorkbook = new XSSFWorkbook(file);
			userList = new ArrayList<User>();
			XSSFSheet xssfSheet = xssfWorkbook.getSheet("Test_Data");
			Iterator<Row> rowIterator = xssfSheet.iterator();
			rowIterator.next();
			Row row;
			Cell emailIDCell;
			Cell passwordCell;
			User userData;
			DataFormatter formatter = new DataFormatter();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				emailIDCell = row.getCell(0);
				passwordCell = row.getCell(1);
				
				String email = formatter.formatCellValue(emailIDCell);
			    String password = formatter.formatCellValue(passwordCell);
			    
				//userData = new User(emailIDCell.toString(), passwordCell.toString());
				userData = new User(email,password);
				
				userList.add(userData);
			}
			xssfWorkbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList.iterator();*/
		
		List<User> userList = new ArrayList<>();

	    try (InputStream inputStream = Thread.currentThread()
	                                        .getContextClassLoader()
	                                        .getResourceAsStream("TestData/" + fileName)) {
	        if (inputStream == null) {
	            throw new FileNotFoundException("XLS file not found in resources/TestData: " + fileName);
	        }

	        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream)) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheet("Test_Data");
	            Iterator<Row> rowIterator = xssfSheet.iterator();
	            if (rowIterator.hasNext()) {
	                rowIterator.next(); // skip header row
	            }

	            DataFormatter formatter = new DataFormatter();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                Cell emailIDCell = row.getCell(0);
	                Cell passwordCell = row.getCell(1);

	                String email = formatter.formatCellValue(emailIDCell);
	                String password = formatter.formatCellValue(passwordCell);

	                User userData = new User(email, password);
	                userList.add(userData);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return userList.iterator();
	}
	}


