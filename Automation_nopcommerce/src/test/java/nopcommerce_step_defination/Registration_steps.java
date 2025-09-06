package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registration_steps {
	WebDriver driver;
    PageClass pg;
	
	
	@Given("registration page should be open in default browser")
	public void registration_page_should_be_open_in_default_browser() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^in the fields enter (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*)$")
	public void in_the_fields_enter_gender_fname_lname_email_cname_newsletter_pwd_cpwd(String gen, String fn, String ln, String em, String cn, boolean nws, String pw, String cpw) {
	    driver.findElement(By.cssSelector("li>a[href=\'/register?returnUrl=%2F\']")).click();
	    pg.register(gen, fn, ln, em, cn, nws, pw, cpw);
	}

	@And("get registration result and get error messages if any")
	public void get_registration_result_and_get_error_messages_if_any() {
	    pg.getRegistrationResult();
	    pg.getErrorMessages();
	}

	@And("click the continue button")
	public void click_the_continue_button() {
		driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
	}

	@Then("registration successful and open home page")
	public void registration_successful_and_open_home_page() {
		System.out.println("Registration successful");
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
        driver.close();
	}
}
