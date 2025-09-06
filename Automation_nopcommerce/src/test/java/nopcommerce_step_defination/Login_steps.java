package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class Login_steps {
	WebDriver driver;
    PageClass pg;

	@Given("login page should be open in default browser and internet connected")
	public void login_page_should_be_open_in_default_browser_and_internet_connected() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^in the textbox enter (.*) and (.*)$")
	public void in_the_textbox_enter_username_and_password(String user, String pass) {
	    pg.navigateToLoginPage();
	    pg.enterLoginCredentials(user, pass);
	}

	@When("^Click on the (.*) option$")
	public void click_on_the_remember_option(boolean rem) {
		pg.setRememberMe(rem);
	}

	@When("Click on the submit\\/login button")
	public void click_on_the_submit_login_button() {
	    pg.clickLoginButton();
	}

	@Then("login successful and you are redirected to the home page")
	public void login_successful_and_you_are_redirected_to_the_home_page() {
		System.out.println("Login successful");
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
        driver.close();
	}
}
