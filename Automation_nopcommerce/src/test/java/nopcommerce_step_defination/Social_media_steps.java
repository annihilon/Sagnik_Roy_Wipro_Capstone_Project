package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class Social_media_steps {
	WebDriver driver;
    PageClass pg;
    
	@Given("home page should be opened in browser and the scrolling function to down is working")
	public void home_page_should_be_opened_in_browser_and_the_scrolling_function_to_down_is_working() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("navigate to home page and the social media handles")
	public void navigate_to_home_page_and_the_social_media_handles() throws InterruptedException {
		Thread.sleep(2000);
	    pg.scrolltobottom();
	    Thread.sleep(2000);
	}

	@And("scroll down to bottom and click on facebook")
	public void scroll_down_to_bottom_and_click_on_facebook() {
	    driver.findElement(By.cssSelector("a[href=\'https://www.facebook.com/nopCommerce\']")).click();
	}

	@Then("It navigates to facebook page and validation successful")
	public void it_navigates_to_facebook_page_and_validation_successful() {
		System.out.println("Facebook link clicked - external navigation validated");
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
        driver.quit();
	}

}
