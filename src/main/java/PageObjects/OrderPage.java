package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Abstract.Abstract;

public class OrderPage extends Abstract {

	

	public WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page Factory // Locators

	@FindBy(xpath = "//tbody/tr/td[2]")
	List<WebElement> products;
	

	// Action


public boolean verifyProductDisplay(String productName) {
	boolean Match = products.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	return Match;
	
}
	

}
