package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Abstract.Abstract;

public class CheckoutPage extends Abstract {

	public WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page Factory // Locators

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath = "//section/button/following-sibling::button[1]")
	WebElement SelectCountry;
	@FindBy(css = ".btnn")
	WebElement e;
	By results = By.cssSelector(".ta-results");

	// Action

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitElementToAppear(results);
		SelectCountry.click();

	}
	public ConfirmationPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click(0);", e);
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
		
	}

}
