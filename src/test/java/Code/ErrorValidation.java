package Code;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CatalogPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import TestComponents.BaseTest;

public class ErrorValidation extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer=TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingpage.loginApplication("vinaykumarbu@gmail.com", "Vinay@4900"); // Wrong password
		landingpage.getErrormsg();
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrormsg());

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";

		CatalogPage catalogpage = landingpage.loginApplication("vinaybubgsit@gmail.com", "Vinay@490");

		// CatalogPage catalogpage = new CatalogPage(driver);
		// List<WebElement> products = catalogpage.getProductList();
		catalogpage.addProductToCart(productName);

		CartPage cartpage = catalogpage.goToCartPage();
		// CartPage cartpage = new CartPage(driver);

		boolean Match = cartpage.verifyProductDisplay("ADIDASS ORIGINAL");   // Wrong Pname
		Assert.assertFalse(Match);
		
		

	}

}
