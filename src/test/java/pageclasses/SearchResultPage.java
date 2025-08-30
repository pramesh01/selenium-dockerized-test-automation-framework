package pageclasses;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BrowserUtilities;

public class SearchResultPage extends BrowserUtilities {

	private static final By PRODUCT_ITEM_LOCATOR = By.xpath("//span[@class='lighter']");
	public static final By ALL_SEARCHED_ITEMLIST_NAMES = By.xpath("//h5[@itemprop='name']/a");

	public SearchResultPage(WebDriver driver) {
		super(driver);

	}
	public By getAllSearchedItemLocator() {
	    return ALL_SEARCHED_ITEMLIST_NAMES;
	}

	public String getsearchresultTitle() {
		return getVisibleText(PRODUCT_ITEM_LOCATOR);
	}

	public boolean isProductPresentinSearchList(String searchitem) {
	/*	List<String> keywords = Arrays.asList(searchitem.toLowerCase().split(" "));
		List<String> productNameList = getALLVisibleText(ALL_SEARCHED_ITEMLIST_NAMES);
		boolean result = productNameList.stream()
				.anyMatch(name -> (keywords.stream().anyMatch(name.toLowerCase()::contains)));// searching with keywords
																								// i.e product or search																					// or Dress
		return result;*/
		  List<String> keywords = Arrays.asList(searchitem.toLowerCase().split(" "));
		    List<String> productNameList = getALLVisibleText(ALL_SEARCHED_ITEMLIST_NAMES);

		    if (productNameList.isEmpty()) {
		       logger.warn("Search returned 0 products for: " + searchitem);
		        return false;
		    }

		    boolean result = productNameList.stream()
		        .anyMatch(name -> keywords.stream().anyMatch(name.toLowerCase()::contains));

		    if (!result) {
		        logger.warn("Search item '" + searchitem + "' not found in product list: " + productNameList);
		    }
		    return result;
		
	}

	public ProductDetailPage clickOntheProductAt(int index) {
		/*clickon(getAllElements(ALL_SEARCHED_ITEMLIST_NAMES).get(index));
        ProductDetailPage productDetailPage=new ProductDetailPage(getDriver());
        return productDetailPage;*/
		
		  // Wait until at least (index + 1) products are visible
	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	    wait.until(driver -> getAllElements(ALL_SEARCHED_ITEMLIST_NAMES).size() > index);

	    List<WebElement> products = getAllElements(ALL_SEARCHED_ITEMLIST_NAMES);

	    if (products.size() > index) {
	        clickon(products.get(index));
	    } else {
	        throw new RuntimeException("Product at index " + index + " not found! Only " + products.size() + " products present.");
	    }

	    return new ProductDetailPage(getDriver());
	}
	
	

}
