package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentReportUtility {

		 private static ExtentReports extentReports;
		    // ThreadLocal ensures each test thread gets its own ExtentTest
		    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

		    // Lazy initialization (parallel safe)
		    private static ExtentReports getInstance() {
		        if (extentReports == null) {
		            synchronized (ExtentReportUtility.class) { // double-check lock
		                if (extentReports == null) {

		                    // Create Reports directory if missing
		                    String reportDir = System.getProperty("user.dir") + File.separator + "Reports";
		                    new File(reportDir).mkdirs();

		                    // Timestamp for uniqueness
		                    Date date = new Date();
		                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		                    String timeStamp = sdf.format(date);

		                    String reportPath = reportDir + File.separator + "AutomationReport_" + timeStamp + ".html";
                               if(extentReports==null) {
		                    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		                    spark.config().setTheme(Theme.STANDARD);
		                    spark.config().setReportName("Automation Test Execution");
		                    spark.config().setDocumentTitle("Extent Report - Selenium Tests");

		                    extentReports = new ExtentReports();
		                    extentReports.attachReporter(spark);
                               }
		                    // Optional: Add system info
		                    extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		                    extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		                }
		            }
		        }
		        return extentReports;
		    }

		    // Create a new test node, bound to the current thread
		    public static synchronized  void createExtentTest(String testName) {
		        ExtentTest test = getInstance().createTest(testName);
		        extentTest.set(test);
		    }

		    // Retrieve the ExtentTest for current thread
		    public static synchronized  ExtentTest getTest() {
		        return extentTest.get();
		    }

		    // Remove test reference after each test method (important for parallel)
		    public static void removeTest() {
		        extentTest.remove();
		    }

		    // Flush reports after suite finish
		    public static synchronized  void flushReports() {
		        if (extentReports != null) {
		            extentReports.flush();
		        }
		    }
	}

	/*private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static void setupExtentSparkReporter(String reportName) {
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		String timeStamp=sdf.format(date);

		String reportPath = System.getProperty("user.dir") + "//Reports//" + reportName + "_"+timeStamp+".html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
	}

	public static void CreateExtentTest(String testName) {
		ExtentTest test = extentReports.createTest(testName);
		extentTest.set(test);
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	public static void flushReports() {
		extentReports.flush();
	}*/


