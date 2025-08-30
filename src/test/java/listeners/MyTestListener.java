package listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import testbase.BaseTest;
import utilities.BrowserUtilities;
import utilities.ExtentReportUtility;
import utilities.LoggerUtility;

public class MyTestListener implements ITestListener, ISuiteListener {
	
	  Logger logger = LoggerUtility.getLogger(this.getClass());

	    public void onStart(ISuite suite) {
	        logger.info("==== Test Suite Started ====");
	        // Report is initialized lazily in ExtentReportUtility when first test runs
	        //ExtentReportUtility.setupExtentSparkReporter("Report"); // initialize report
	    }

	    public void onFinish(ISuite suite) {
	        logger.info("==== Test Suite Finished ====");
	        ExtentReportUtility.flushReports(); // single flush after all tests
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	    	
	    	   String methodName = result.getMethod().getMethodName();
	    	   // Skip config methods manually
	    	    if (!result.getMethod().isTest() || methodName.startsWith("setup") 
	    	        || methodName.startsWith("before") || methodName.startsWith("after")) {
	    	        return;
	    	    }
	        logger.info("Starting Test: " + methodName);
	        //ExtentReportUtility.createExtentTest(methodName);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        logger.info(testName + " PASSED");

	        ExtentReportUtility.getTest().log(Status.PASS,
	                "Test " + testName + " passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        logger.error(testName + " FAILED", result.getThrowable());

	        ExtentReportUtility.getTest().log(Status.FAIL,
	                result.getThrowable());

	        // Screenshot logic
	        Object testClass = result.getInstance();
	        BrowserUtilities browserUtility = ((BaseTest) testClass).getInstance();
	        String screenshotPath = browserUtility.TakeScreenShot(testName);

	        try {
	            ExtentReportUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	        } catch (Exception e) {
	            logger.error("Error attaching screenshot: " + e.getMessage());
	        }

	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        logger.warn(testName + " SKIPPED");

	        ExtentReportUtility.getTest().log(Status.SKIP,
	                "Test " + testName + " skipped");
	    }

	/*Logger logger = LoggerUtility.getLogger(this.getClass());
	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info("Test has been started in logger....");
		ExtentReportUtility.CreateExtentTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "Test Is Passed..onlogs");
		ExtentReportUtility.getTest().log(Status.PASS,
				"Test " + result.getMethod().getMethodName() + "is Passes in extent Reports");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "Test Is FAILED..onlogs");
		logger.error(result.getThrowable().getMessage());
		ExtentReportUtility.getTest().log(Status.FAIL,
				"Test " + result.getMethod().getMethodName() + " is failed in extent Reports");
		ExtentReportUtility.getTest().log(Status.FAIL,
				result.getThrowable().getMessage() + " is the failed description message in extent Reports");

		// accessing the driver object from BrowserUtility class..
		Object testClass = result.getInstance();
		BrowserUtilities browserUtility = ((BaseTest) testClass).getInstance();
		logger.info("capturing screenshot on failure");
		String screenshotpath = browserUtility.TakeScreenShot(result.getMethod().getMethodName());
		ExtentReportUtility.getTest().addScreenCaptureFromPath(screenshotpath);
		logger.info("successfully captured the  screenshot on @Test failure");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "Test Is SKIPPED..onlogs");
		ExtentReportUtility.getTest().log(Status.SKIP,
				"Test " + result.getMethod().getMethodName() + "is Skipped in extent Reports");
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started...");
		ExtentReportUtility.setupExtentSparkReporter("Report");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test Suite execution is Finished...");
		ExtentReportUtility.flushReports();
	}
	*/

}
