package testbase;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentTest;
import com.constants.Browsers;
import pageclasses.HomePage;
import utilities.BrowserUtilities;
import utilities.ExtentReportUtility;
import utilities.LoggerUtility;

//@Listeners(listeners.MyTestListener.class)
public class BaseTest {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Logger logger = LoggerUtility.getLogger(this.getClass());
	protected HomePage homePage;
	private boolean isGridEnabled;

	@SuppressWarnings("deprecation")
	@Parameters({"browserName","isHeadless","isGridEnabled"})
	
	@BeforeMethod
	public void setup(ITestResult result,@Optional("chrome") String browserName,@Optional ("false") boolean isHeadless, 
			@Optional ("false") boolean isGridEnabled,Method method) {
		
		logger.info("Setting up the initial configurations..");
		//For steps logging..
		ExtentReportUtility.createExtentTest(method.getName());
		
		this.isGridEnabled=isGridEnabled;
		WebDriver lambdaDriver = null;
		
		if(isGridEnabled) {
		
			String hubHost = System.getProperty("selenium.grid.hubHost", "localhost");
		    String gridUrl = "http://" + hubHost + ":4444/wd/hub";
		    
			  if (browserName.equalsIgnoreCase("chrome")) {
	                ChromeOptions options = new ChromeOptions();
	                if (isHeadless) {
	                    options.addArguments("--headless=new"); // modern headless
	                    options.addArguments("--disable-gpu");
	                }
	                try {
						lambdaDriver = new RemoteWebDriver(new URL(gridUrl), options);
					} catch (MalformedURLException e) {
						logger.error("Cannot connect to Selenium Grid at " + gridUrl, e);
					    throw new SkipException("Skipping test because Selenium Grid is unreachable: " + e.getMessage());
					}

	            } else if (browserName.equalsIgnoreCase("firefox")) {
	                FirefoxOptions options = new FirefoxOptions();
	                if (isHeadless) {
	                    options.addArguments("--headless");
	                }
	                try {
						lambdaDriver = new RemoteWebDriver(new URL(gridUrl), options);
					} catch (MalformedURLException e) {
						logger.error("Cannot connect to Selenium Grid at " + gridUrl, e);
					    throw new SkipException("Skipping test because Selenium Grid is unreachable: " + e.getMessage());
					}

	            } else {
	                throw new IllegalArgumentException("Unsupported browser: " + browserName);
	            }
			  
			  driver.set(lambdaDriver);
	          //homePage = new HomePage(lambdaDriver);
			  homePage = new HomePage(getDriver());
			  
		       }
		else {
			logger.info("Loading HomePage of the website..");
		homePage=new HomePage(Browsers.valueOf(browserName.toUpperCase()), isHeadless, isGridEnabled);
	    }
	}

	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) {
		ExtentTest test = ExtentReportUtility.getTest();
		 if (result.getStatus() == ITestResult.FAILURE) {
		        test.fail("Test Failed: " + result.getThrowable());
		    } 
		 else if (result.getStatus() == ITestResult.SKIP) {
		        test.skip("Test Skipped: " + result.getThrowable());
		    } 
		 else {
		        test.pass("Test Passed");
		    }
		
		 
		if (homePage != null && homePage.getDriver() != null) {
			logger.info("Quiting the browser window..");
			homePage.getDriver().quit();
		}
		
		// cleanup test reference
	    ExtentReportUtility.removeTest();
	}
	
	
	public BrowserUtilities getInstance() {
		return homePage;
	}
	
	 public static WebDriver getDriver() {
	        return driver.get();
	    }
	    
	//****************************************************************************//
	
	/* 
	  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	 
    Logger logger = LoggerUtility.getLogger(this.getClass());
    protected HomePage homePage;
    private boolean isGridEnabled;

    @SuppressWarnings("deprecation")
    @Parameters({"browserName","isHeadless","isGridEnabled"})
    @BeforeMethod
    public void setup(ITestResult result,
                      @Optional("chrome") String browserName,
                      @Optional("false") boolean isHeadless,
                      @Optional("false") boolean isGridEnabled) {
        logger.info("Setting up the initial configurations..");

        // Read values from system properties if present (fallback to TestNG @Parameters)
       // String browser = System.getProperty("browserName", browserName);
        String browser = System.getProperty("browserName");
        if (browser == null || browser.isEmpty()) {
            browser = System.getenv("browserName");
        }
        if (browser == null || browser.isEmpty()) {
            browser = browserName;
        }
        boolean headless = Boolean.parseBoolean(System.getProperty("isHeadless", String.valueOf(isHeadless)));
        this.isGridEnabled = Boolean.parseBoolean(System.getProperty("isGridEnabled", String.valueOf(isGridEnabled)));

        WebDriver lambdaDriver = null;

        if (this.isGridEnabled) {
        	String hubHost = System.getProperty("selenium.grid.hubHost");
        	if (hubHost == null || hubHost.isEmpty()) {
        	    hubHost = System.getenv("selenium.grid.hubHost");
        	}
        	if (hubHost == null || hubHost.isEmpty()) {
        	    hubHost = "hub"; // default for docker-compose
        	}

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--disable-gpu");
                }
                try {
                    lambdaDriver = new RemoteWebDriver(new URL("http://" + hubHost + ":4444/"), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("--headless");
                }
                try {
                    lambdaDriver = new RemoteWebDriver(new URL("http://" + hubHost + ":4444/"), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.set(lambdaDriver);
            homePage = new HomePage(getDriver());
            //homePage = new HomePage(lambdaDriver);

        } else {
            logger.info("Loading HomePage of the website..");
            homePage = new HomePage(Browsers.valueOf(browser.toUpperCase()), headless);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        /*if (homePage.getDriver() != null) {
            logger.info("Quitting the browser window..");
            homePage.getDriver().quit();
        }
    	 if (homePage != null && homePage.getDriver() != null) {
    	        logger.info("Quitting the browser window..");
    	        homePage.getDriver().quit();
    	    }
    }*/

  
}
