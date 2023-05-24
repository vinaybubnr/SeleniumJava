package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Abstract.Abstract;

public class ConfirmationPage extends Abstract {

	

	public WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page Factory // Locators

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	

	// Action


public String getConfirmationMsg() {
	String message = confirmationMessage.getText();
	return message;
}

}
