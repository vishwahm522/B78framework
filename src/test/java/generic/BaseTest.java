package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	public final String DEFAULT_URL="https://demo.actitime.com";
	public final String DEFAULT_BROWSER="edge";
	public final String DEFAULT_ITO="6";
	public final String DEFAULT_ETO="7";
	public final String XL_PATH="./data/input.xlsx";
	public WebDriver driver;
	public WebDriverWait wait;
	
	@Parameters({"appURL","browser","ITO","ETO"})
	@BeforeMethod
	public void preCondition(@Optional(DEFAULT_URL) String appURL, @Optional(DEFAULT_BROWSER) String browser, @Optional(DEFAULT_ITO) String ITO,@Optional(DEFAULT_ETO) String ETO) throws MalformedURLException
	{
		long longITO=Long.parseLong(ITO);
		long longETO=Long.parseLong(ETO);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),new ChromeOptions());
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),new FirefoxOptions());
		}
		else
		{
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),new EdgeOptions());
		}
		
		driver.get(appURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longITO));
		wait=new WebDriverWait(driver, Duration.ofSeconds(longETO));
	}
	
	
	@AfterMethod
	public void postCondition()
	{
		driver.quit();
	}
}
