package Code;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CatalogPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.OrderPage;
import TestComponents.BaseTest;

public class SATest2 extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		CatalogPage catalogpage = landingpage.loginApplication(input.get("email"), input.get("pass"));

		// CatalogPage catalogpage = new CatalogPage(driver);
		// List<WebElement> products = catalogpage.getProductList();
		catalogpage.addProductToCart(input.get("Pname"));

		CartPage cartpage = catalogpage.goToCartPage();
		// CartPage cartpage = new CartPage(driver);

		boolean Match = cartpage.verifyProductDisplay(input.get("Pname"));
		Assert.assertTrue(Match);
		CheckoutPage checkoutpage = cartpage.checkoutPage();

		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String message = confirmationpage.getConfirmationMsg();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

	}

	// Verify product name displaying in orders page

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {

		CatalogPage catalogpage = landingpage.loginApplication("vinaykumarbu@gmail.com", "Vinay@490");
		OrderPage orderpage = catalogpage.myOrderPage();
		boolean Match = orderpage.verifyProductDisplay(productName);
		Assert.assertTrue(Match);

	}

	// Senario 1
	/*
	 * @DataProvider public Object[][] getData() { 
	 * return new Object[][] { {
	 * "vinaykumarbu@gmail.com", "Vinay@490", "ADIDAS ORIGINAL" }, {
	 * "vinaybubgsit@gmail.com", "Vinay@490", "ZARA COAT 3" } };
	 * 
	 * }
	 */
	
	// Senario 2
	
/*	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "vinaykumarbu@gmail.com");
		map.put("password", "Vinay@490");
		map.put("Pname", "ADIDAS ORIGINAL");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "vinaybubgsit@gmail.com");
		map1.put("password", "Vinay@490");
		map1.put("Pname", "ZARA COAT 3");

		return new Object[][] { { map }, { map1 } };   

	} */
	
	// Senario 3
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data  =  getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\PurchaseOrder.json");
		

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
