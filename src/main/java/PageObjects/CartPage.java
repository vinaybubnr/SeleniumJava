package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Abstract.Abstract;

public class CartPage extends Abstract {

	/*
	 * List<WebElement> CartProducts =
	 * driver.findElements(By.cssSelector(".cartSection h3")); 
	 * boolean Match =
	 * CartProducts.stream() .anyMatch(cartproduct ->
	 * cartproduct.getText().equalsIgnoreCase(productName));
	 * Assert.assertTrue(Match);
	 * driver.findElement(By.cssSelector(".totalRow button")).click();
	 * 
	 * 
	 */

	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page Factory // Locators

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;
	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;
	

	// Action

	public boolean verifyProductDisplay(String productname) {
		boolean Match = productTitles.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return Match;
		
	}
	
	public CheckoutPage checkoutPage() {
		checkOutEle.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
		
	}

}
