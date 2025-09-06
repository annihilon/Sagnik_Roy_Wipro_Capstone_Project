package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class Search_steps {
	WebDriver driver;
    PageClass pg;
	@Given("home page should be open in default browser and search option visible")
	public void home_page_should_be_open_in_default_browser_and_search_option_visible() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^in the searchbox enter the (.*) and click on the search option$")
	public void in_the_searchbox_enter_the_item_and_click_on_the_search_option(String item) throws InterruptedException {
	    pg.searchForItem(item);
	}

	@Then("search successful and redirected to home page")
	public void search_successful_and_redirected_to_home_page() {
		System.out.println("Search initiated and successful");
        driver.close();
	}

}
