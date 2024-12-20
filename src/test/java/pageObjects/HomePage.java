package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='Registration Form']")
	public WebElement RegisterButton;
	
	public void ClickOnRegisterButton() {
		RegisterButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
}
