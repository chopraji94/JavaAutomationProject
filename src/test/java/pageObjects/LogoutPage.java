package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage {

	public LogoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='post-header']//h1")
	WebElement successfullloginHeader;
	
	@FindBy(xpath="//a[text()='Log out']")
	WebElement logoutButtonXpath;
	
	public String getsuccessfullloginHeaderText() {
		return successfullloginHeader.getText();
	}
	
	public boolean succesfullTextExist() {
		try {
			return successfullloginHeader.isDisplayed();
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public void clickLogoutButton() {
		logoutButtonXpath.click();
	}
}
