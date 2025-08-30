package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import com.constants.Env;

public class PropertyUtils {
	
	public static String propertyFileReader(Env env,String propName) {
		 Properties prop=new Properties();
		/*String filePath=System.getProperty("user.dir")+"//src//test//resources//ConfigurationFiles//"+env+".properties";
	   
	    File file=new File(filePath);
	    FileReader fileReader;
		try {
			fileReader = new FileReader(file);
		    prop.load(fileReader);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	  String textValue=prop.getProperty(propName.toUpperCase());
	  return textValue;*/
		 try {
	            // Load from classpath
	            String fileName = "ConfigurationFiles/" + env + ".properties";
	            try (InputStream input = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName)) {
	                if (input == null) {
	                    throw new RuntimeException("Property file not found: " + fileName);
	                }
	                prop.load(input);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return prop.getProperty(propName.toUpperCase());
	}

}
