package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initilizeDriver() throws IOException {

		// Properties Class
		Properties prop = new Properties();
		FileInputStream fis1 = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalData.properties");

		prop.load(fis1);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}
	
		// Utilities 
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
			// Read JSON to String

					String JsonContent = FileUtils.readFileToString(
							new File(filePath),
							StandardCharsets.UTF_8);
					
					// Convert String To Hash MAP - Jackson ( Maven Repo )

					ObjectMapper mapper = new ObjectMapper();

					List<HashMap<String, String>> data = mapper.readValue(JsonContent,
							new TypeReference<List<HashMap<String, String>>>() {
							}); // Default IMP
					
					return data;

		}
		
		public String getScreenShot(String TestCaseName, WebDriver driver) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "\\reports\\" + TestCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "\\reports\\" + TestCaseName + ".png"; // return file;

		}

	

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initilizeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
