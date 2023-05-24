package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Abstract.Abstract;

public class LandingPage extends Abstract{

	/*
	 * driver.get("https://rahulshettyacademy.com/client/");
	 * driver.findElement(By.cssSelector("#userEmail")).sendKeys(
	 * "vinaykumarbu@gmail.com");
	 * driver.findElement(By.cssSelector("#userPassword")).sendKeys("Vinay@490");
	 * driver.findElement(By.cssSelector("#login")).click();
	 */

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page Factory // Locators

	@FindBy(css = "#userEmail")
	WebElement userEmail;
	@FindBy(css = "#userPassword")
	WebElement userPass;
	@FindBy(css = "#login")
	WebElement submit;
	//div[@class='ng-tns-c4-7 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	

	

	// Action

	public CatalogPage loginApplication(String EmailID, String Password) {
		userEmail.sendKeys(EmailID);
		userPass.sendKeys(Password);
		submit.click();
		CatalogPage catalogpage = new CatalogPage(driver);
		return catalogpage;

	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrormsg() {
		waitwebElementToAppear(errormsg);
		return errormsg.getText();
		
	}

}
