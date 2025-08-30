package utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browsers;

public class BrowserUtilities {

	protected final Logger logger = LoggerUtility.getLogger(this.getClass());
	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	/*
	 * public BrowserUtilities(WebDriver driver) { this.driver.set(driver); }
	 */

	// Constructor for Remote/Grid usage
	public BrowserUtilities(WebDriver remoteDriver) {
		this.driver.set(remoteDriver);
	}

	// current changed setup.
	public BrowserUtilities(Browsers browserName, boolean isHeadless, boolean isGridEnabled) {
		if (isGridEnabled) {
			// GRID execution
			// ChromeOptions chromeOptions = new ChromeOptions();
			FirefoxOptions firefoxOptions = new FirefoxOptions();

			if (browserName == Browsers.CHROME) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("--disable-background-networking");
				chromeOptions.addArguments("--window-size=1920,1080");
				chromeOptions.addArguments("--remote-allow-origins=*");
				if (isHeadless)
					chromeOptions.addArguments("--headless=new");

				URL gridUrl = null;
				try {
					gridUrl = new URL(
							"http://" + System.getProperty("selenium.grid.hubHost", "localhost") + ":4444/wd/hub");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.set(new RemoteWebDriver(gridUrl, chromeOptions));

			} else if (browserName == Browsers.FIREFOX) {
				firefoxOptions.addArguments("--width=1920");
				firefoxOptions.addArguments("--height=1080");
				if (isHeadless) {
					firefoxOptions.addArguments("--headless");
				}

				String gridUrl = System.getProperty("selenium.grid.hubHost", "localhost");
				URL remoteUrl = null;
				try {
					remoteUrl = new URL("http://" + gridUrl + ":4444/wd/hub");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.set(new RemoteWebDriver(remoteUrl, firefoxOptions));

			} else {
				throw new IllegalArgumentException("Invalid Browser..choose Chrome or Firefox");
			}

		} else {
			// LOCAL execution
			if (browserName == Browsers.CHROME) {
				ChromeOptions option = new ChromeOptions();
				if (isHeadless) {
					option.addArguments("--headless=new");
					option.addArguments("--window-size=1920,1080");
				}
				driver.set(new ChromeDriver(option));

			} else if (browserName == Browsers.FIREFOX) {
				FirefoxOptions option = new FirefoxOptions();
				if (isHeadless) {
					option.addArguments("--headless");
					option.addArguments("--width=1920");
					option.addArguments("--height=1080");
				}
				driver.set(new FirefoxDriver(option));

			} else {
				throw new IllegalArgumentException("Invalid Browser..choose Chrome or Firefox");
			}

			// maximize only if not headless
			if (!isHeadless) {
				driver.get().manage().window().maximize();
			}
		}
	}

	// this was working without gridenabled
	/*
	 * public BrowserUtilities(Browsers browserName, boolean isHeadless) { if
	 * (browserName == Browsers.CHROME) { ChromeOptions option = new
	 * ChromeOptions(); if (isHeadless) { option.addArguments("--headless=new");
	 * option.addArguments("--window-size=1920,1080"); } driver.set(new
	 * ChromeDriver(option));
	 * 
	 * } else if (browserName == Browsers.FIREFOX) { FirefoxOptions option = new
	 * FirefoxOptions(); if (isHeadless) { option.addArguments("--headless");
	 * option.addArguments("--width=1920"); option.addArguments("--height=1080"); }
	 * driver.set(new FirefoxDriver(option));
	 * 
	 * } else { throw new
	 * IllegalArgumentException("Invalid Browser..please choose either Chrome or Firefox"
	 * ); }
	 * 
	 * // maximize only if not headless if (!isHeadless) {
	 * driver.get().manage().window().maximize(); } }
	 */

	/*
	 * public BrowserUtilities(Browsers browserName, boolean isHeadless) {
	 * if(isHeadless) { if (browserName == Browsers.CHROME) { ChromeOptions
	 * option=new ChromeOptions(); option.addArguments("--headless=new");
	 * option.addArguments("--window-size=1920,1080"); driver.set(new
	 * ChromeDriver(option));
	 * 
	 * } else if (browserName == Browsers.FIREFOX) { FirefoxOptions option=new
	 * FirefoxOptions(); option.addArguments("--headless");
	 * option.addArguments("--width=1920"); option.addArguments("--height=1080");
	 * driver.set(new FirefoxDriver(option)); } else {
	 * System.err.println("Invalid browser for headless mode."); } }else { if
	 * (browserName == Browsers.CHROME) { driver.set(new ChromeDriver());
	 * driver.get().manage().window().maximize();
	 * 
	 * } else if (browserName == Browsers.FIREFOX) { driver.set(new
	 * FirefoxDriver()); driver.get().manage().window().maximize(); } else
	 * System.out.println("Invalid Browser..please choose either Chrome or Firefox"
	 * ); System.err.print("please enter correct browser.."); } }
	 */

	public WebDriver getDriver() {
		return driver.get();
	}

	/*
	 * public void click(By locator) {
	 * logger.info("clicking on locator ..through logger");
	 * driver.get().findElement(locator).click(); }
	 */

	public void clickon(WebElement element) { // this method is for clicking on WebElements..
		logger.info("clicking on webelement > ");
		ExtentReportUtility.getTest().info("Clicking on WebElement: " + element);
		element.click();
	}

	public void type(By locator, String text) {
		//logger.info("typing text on locator ..through logger" + text);
		//driver.get().findElement(locator).sendKeys(text);
		 WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
		    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		    element.clear();
		    element.sendKeys(text);

		    logger.info("Typed text '" + text + "' into element: " + locator);
		    ExtentReportUtility.getTest().info("Typed text: <b>" + text + "</b> into field: " + locator.toString());

	}

	public void gotoWebsite(String url) {
		logger.info("launching website ..through logger" + url);
		 ExtentReportUtility.getTest().info("Launching website: " + url);
		driver.get().get(url);
	}

	public String fetchAuthMessage(By locator) {
		//return driver.get().findElement(locator).getText();
		 WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
		    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		    String message = element.getText().trim();

		    logger.info("Fetched auth message: " + message);
		    ExtentReportUtility.getTest().info("Auth message: <b>" + message + "</b>");

		    return message;
	}

	public void clearText(By locator) {
		driver.get().findElement(locator).clear();
	}

	public String TakeScreenShot(String fileName) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		String timeStamp = sdf.format(date);

		String ScreenshotFilePath = System.getProperty("user.dir") + "//Screenshots//" + fileName + "_" + timeStamp
				+ ".png";
		File destfilePath = new File(ScreenshotFilePath);
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver.get();
		File srcFilepath = takesScreenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFilepath, destfilePath);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return ScreenshotFilePath;
	}

	public String getVisibleText(By locator) {
		
		 WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(15));
		    WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		    ExtentReportUtility.getTest().info("Fetched visible text: <b>" + webElement.getText() + "</b>");
		    return webElement.getText();
	}

	public List<WebElement> getAllElements(By locator) {
		/*
		 * logger.info("Returning all the visible elements "); List<WebElement>
		 * allWebElement = driver.get().findElements(locator);
		 * logger.info("Returning list of the all elements."); return allWebElement;
		 */

		logger.info("Waiting for elements to be visible for locator: " + locator);
		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));

		try {
			// Wait until at least one element is visible
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			logger.warn("No elements found for locator: " + locator + " within timeout.");
			return new ArrayList<>(); // Return empty list if nothing is visible
		}

		List<WebElement> allWebElement = driver.get().findElements(locator);
		logger.info("Found " + allWebElement.size() + " elements for locator: " + locator);
		return allWebElement;
	}

	public String getVisibleText(WebElement element) {
		String text = element.getText().trim();
		logger.info("Visible text from element: [" + element + "] => " + text);
	    // Log to Extent Report
	    ExtentReportUtility.getTest().info("Fetched visible text: <b>" + text + "</b>");
		//return element.getText();
	    return text;
	}

	public List<String> getALLVisibleText(By locator) {
		logger.info("Returning the text of all visible elements ");
		ExtentReportUtility.getTest().info("Fetching visible text of all elements located by: " + locator);
		List<WebElement> allWebElement = driver.get().findElements(locator);
		logger.info("Returning list of the items.");
		List<String> visibleTextList = new ArrayList<>();
		
		for (WebElement element : allWebElement) {
			String text = getVisibleText(element);
			ExtentReportUtility.getTest().info("Text found: " + text);
			visibleTextList.add(getVisibleText(element));
		}
		logger.info("Returning list of " + visibleTextList.size() + " items");
	    ExtentReportUtility.getTest().pass("Fetched " + visibleTextList.size() + " items successfully");
		return visibleTextList;
	}

	public void selectFromDropDown(By dropdownLocator, String TextToBeSelected) {
		logger.info(" selecting from drop down " + dropdownLocator);
		ExtentReportUtility.getTest().info("Selecting from drop down: " + dropdownLocator + " -> " + TextToBeSelected);
		WebElement element = driver.get().findElement(dropdownLocator);
		logger.info("drop down item found and selected.. " + dropdownLocator);
		Select select = new Select(element);
		select.selectByVisibleText(TextToBeSelected);
		logger.info("Drop down value selected: " + TextToBeSelected);
        ExtentReportUtility.getTest().pass("Drop down value selected: " + TextToBeSelected);
        
	}

	public void enterText(By locator, String text) {
		// logger.info("Entering the text into UI > "+text);
		// WebElement webElement = driver.get().findElement(locator);
		// webElement.sendKeys(text);
		logger.info("Entering the text into UI > " + text);
		ExtentReportUtility.getTest().info("Entering text: '" + text + "' into locator: " + locator);
		// Wait until the element is visible
		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20));
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		// Clear the field first (optional, but recommended)
		// webElement.clear();
		webElement.sendKeys(text);
	}

	public void enterSpecialKey(By locator, Keys keystoenter) {
		logger.info("Entering the special Key  > " + keystoenter);
		WebElement webElement = driver.get().findElement(locator);
		webElement.sendKeys(keystoenter);
	}

	public void click(By locator) {
		
		/*WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();*/
		 try {
		        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		        element.click();
		        ExtentReportUtility.getTest().pass("Clicked on element: " + locator);
		    } catch (Exception e) {
		        String screenshotPath = TakeScreenShot("ClickFailure");
		        ExtentReportUtility.getTest().fail("Failed to click on element: " + locator)
		                             .addScreenCaptureFromPath(screenshotPath);
		        throw e; // rethrow so test fails
		    }
	}
}
