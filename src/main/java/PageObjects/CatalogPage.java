package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Abstract.Abstract;

public class CatalogPage extends Abstract {
	public WebDriver driver;

	public CatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	/*
	 * List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	 * WebElement prod = products.stream() .filter(product ->
	 * product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(
	 * productName)) .findFirst().orElse(null);
	 * prod.findElement(By.cssSelector(".w-10")).click();
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
	 * "#toast-container")));
	 * wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.
	 * cssSelector(".ng-animating"))));
	 * driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click(
	 * );
	 */

	// Page Factory

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3"); // Wait
	By toastMsg = By.cssSelector("#toast-container");
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList() {
		waitElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productname) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productname) throws InterruptedException {
		WebElement prod = getProductByName(productname);
		prod.findElement(By.cssSelector(".w-10")).click(); // page factory will not work here
		waitElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);

	}

}
