package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class Orders_steps {
	WebDriver driver;
    PageClass pg;
	
	@Given("home page should be opened in browser and the account section is clickable")
	public void home_page_should_be_opened_in_browser_and_the_account_section_is_clickable() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^log-in to the website using my (.*) and (.*) and navigate to home page$")
	public void log_in_to_the_website_using_my_useremail_and_password_and_navigate_to_home_page(String user1, String pass1) {
		pg.completeLogin(user1, pass1);
	}

	@And("click on the my account section at the top")
	public void click_on_the_my_account_section_at_the_top() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[href='/customer/info'][class='ico-account']")).click();
	}

	@And("click on orders tab in the left")
	public void click_on_orders_tab_in_the_left() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\'main\']/div/div[1]/div/div[2]/ul/li[3]/a")).click();
	}

	@And("click on view details of the first order")
	public void click_on_view_details_of_the_first_order() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\'main\']/div/div[2]/div/div[2]/div/div[1]/div[2]/button")).click();
		Thread.sleep(3000);
		pg.scrolldown();
		Thread.sleep(1000);
		pg.scrolldownmore();
	}

	@Then("order details displayed and testcases successful")
    public void order_details_displayed_and_testcases_successful() {
        try {
            // Extract order information for file output
            String orderNumber = driver.findElement(By.cssSelector(".order-number")).getText();
            String orderDate = driver.findElement(By.cssSelector(".order-date")).getText();
            String orderStatus = driver.findElement(By.cssSelector(".order-status")).getText();
            String orderTotal = driver.findElement(By.cssSelector(".order-total")).getText();
            
            pg.writeOrderDetailsToFile(orderNumber, orderDate, orderStatus, orderTotal);
            
            System.out.println("Order details successfully displayed and saved to file");
            
        } finally {
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img")).click();
            driver.close();
        }
    }
    
}
