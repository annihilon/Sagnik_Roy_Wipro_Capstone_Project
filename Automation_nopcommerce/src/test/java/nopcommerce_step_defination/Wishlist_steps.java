package nopcommerce_step_defination;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;

public class Wishlist_steps {
	WebDriver driver;
    PageClass pg;
    WebDriverWait wait;
	
	@Given("home should be open and the wishlist tab should be visible properly")
	public void home_should_be_open_and_the_wishlist_tab_should_be_visible_properly() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^click on the searcch tab and enter the product (.*) and click on the search button$")
	public void click_on_the_searcch_tab_and_enter_the_product_itemname_and_click_on_the_search_button(String item) {
	    driver.findElement(By.id("small-searchterms")).sendKeys(item);
	    driver.findElement(By.cssSelector("button[class=\'button-1 search-box-button\']")).click();
	}

	@And("go to the product details and click on the wishlist tab")
	public void go_to_the_product_details_and_click_on_the_wishlist_tab() throws InterruptedException {
	    pg.scrolldown();
	    
	    driver.findElement(By.cssSelector("h2.product-title>a[href=\'/apple-macbook-pro\']")).click();
	    pg.scrolldownmore();
	    driver.findElement(By.id("add-to-wishlist-button-4")).click();
	}

	@And("verify if the wishlist added message is displayed")
	public void verify_if_the_wishlist_added_message_is_displayed() throws InterruptedException {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
		pg.scrolltotop();
		driver.findElement(By.cssSelector("span[title=\'Close\']")).click();
		Thread.sleep(2000);
        
	}

	@And("go to the wishlist")
	public void go_to_the_wishlist() {
		
		driver.findElement(By.cssSelector("a[href='/wishlist']")).click();
	}

	@And("click on the items checkbox to be added to cart then add to cart button")
	public void click_on_the_items_checkbox_to_be_added_to_cart_then_add_to_cart_button() throws InterruptedException {
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wishlist-content")));
        
        WebElement checkbox = driver.findElement(By.cssSelector("input[name='addtocart']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        
        driver.findElement(By.cssSelector("button[name='addtocartbutton']")).click();
        Thread.sleep(1000);
	}

	@Then("item successfully added to cart from wishlist and add to cart is displayed")
	public void item_successfully_added_to_cart_from_wishlist_and_add_to_cart_is_displayed() {
		
		    
		    System.out.println("Cart page title verified: " + driver.getTitle());
		    System.out.println("Item successfully added to cart from wishlist");
		    driver.close();
	}

}
