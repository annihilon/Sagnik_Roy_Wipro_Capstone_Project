package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class Cart_steps {
	WebDriver driver;
    PageClass pg;
	
	@Given("home should be open in default browser and search bar clearly visible to search")
	public void home_should_be_open_in_default_browser_and_search_bar_clearly_visible_to_search() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^log in using valid (.*) and (.*) and navigate to the home page$")
	public void log_in_using_valid_useremail_and_password_and_navigate_to_the_home_page(String user, String pass) {
		pg.completeLogin(user, pass);
	}

	@When("^in search box enter (.*) and hit search$")
	public void in_search_box_enter_item1_and_hit_search(String item) throws InterruptedException {
		pg.searchForItem(item);
	    
	}

	@When("click on the add to cart option for the item displayed and item added in cart")
	public void click_on_the_add_to_cart_option_for_the_item_displayed_and_item_added_in_cart() throws InterruptedException {
	    pg.addtocart();
	}

	@When("navigate to cart and hit the delete button after the item")
	public void navigate_to_cart_and_hit_the_delete_button_after_the_item() throws InterruptedException {
	    pg.deletefromcart();
	}

	@Then("item successfully deleted from cart and navigate to homepage")
	public void item_successfully_deleted_from_cart_and_navigate_to_homepage() {
		System.out.println("Deleted from cart successful");
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
        driver.close();
	}
}
