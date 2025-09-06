package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class Checkout_steps {
	WebDriver driver;
    PageClass pg;
	
	@Given("home should be open in default browser and search and add to cart is working")
	public void home_should_be_open_in_default_browser_and_search_and_add_to_cart_is_working() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^log in to the website using valid (.*) and (.*)$")
	public void log_in_to_the_website_using_valid_useremail_and_password(String user1, String pass1) {
		pg.completeLogin(user1, pass1);
	}

	@And("^in search box enter the name of (.*) and click search$")
	public void in_search_box_enter_the_name_of_item_and_click_search(String it) throws InterruptedException {
		pg.searchForItem(it);
	}

	@And("after the item displayed click on the add to cart option")
	public void after_the_item_displayed_click_on_the_add_to_cart_option() throws InterruptedException {
		pg.addtocart();
		driver.findElement(By.cssSelector("span[class='close']")).click();
	}

	@And("navigate to cart, click the terms and condition checkbox and the checkout button for checkout")
	public void navigate_to_cart_click_the_terms_and_condition_checkbox_and_the_checkout_button_for_checkout() throws InterruptedException {
	    pg.scrolltotop();
	    driver.findElement(By.cssSelector("a[href='/cart'][class='ico-cart']")).click();
	    driver.findElement(By.id("termsofservice")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("checkout")).click();
	}

	@And("click on billing address continue")
	public void click_on_billing_address_continue() throws InterruptedException {
//	    driver.findElement(By.cssSelector("button[type=\'button\'][class=\'button-1 cart-button\']")).click();
		Thread.sleep(1000);
	    driver.findElement(By.xpath("//*[@id=\'billing-buttons-container\']/button[2]")).click();
	}

	@And("select ground shipping method and continue")
	public void select_ground_shipping_method_and_continue() throws InterruptedException {
		Thread.sleep(1000);
	    driver.findElement(By.id("shippingoption_1")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector("button[type=\'button\'][class=\'button-1 shipping-method-next-step-button\']")).click();
	}

	@And("select check\\/money payment then continue")
	public void select_check_money_payment_then_continue() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type=\'button\'][class=\'button-1 payment-method-next-step-button\']")).click();
		
	}

	@And("payment information verify and continue")
	public void payment_information_verify_and_continue() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type=\'button\'][class=\'button-1 payment-info-next-step-button\']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[class=\'button-1 confirm-order-next-step-button\']")).click();
		
	}
	@And("check the order details and continue")
	public void check_the_order_details_and_continue() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[class=\'button-1 order-completed-continue-button\']")).click();
	}

	@Then("checkout successful")
	public void checkout_successful() {
		System.out.println("Checkout successful");
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
        driver.close();
	}
}
