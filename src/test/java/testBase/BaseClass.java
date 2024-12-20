package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({ "browser","method" })
	public void setup(String br,String method) throws InterruptedException, IOException {
		logger = LogManager.getLogger(this.getClass());

		File file = new File(System.getProperty("user.dir") + "//logs//automation.log");
		file.delete();

		FileReader propertyFile = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(propertyFile);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.WINDOWS);
			
			switch (br) {
			case "Chrome":
				cap.setBrowserName("chrome");
				break;
			case "Edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			case "Firefox":
				cap.setBrowserName("firefox");
				break;
			default:
				break;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		else {
			switch (br) {
			case "Chrome":
				driver = new ChromeDriver();
				break;
			case "Edge":
				driver = new EdgeDriver();
				break;
			case "Firefox":
				driver = new FirefoxDriver();
				break;
			default:
				break;
			}
		}
		
		String url = method.equals("login") ? p.getProperty("appUrl2") : p.getProperty("appUrl");
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void tearDown() {
		driver.quit();
	}

	public void scrollToBottom() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(Duration.ofSeconds(10));
	}

	public void scrollToElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(Duration.ofSeconds(10));
	}
	
	public String captureScreen(String tName) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHss").format(new Date());
		
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tName+"_"+timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
