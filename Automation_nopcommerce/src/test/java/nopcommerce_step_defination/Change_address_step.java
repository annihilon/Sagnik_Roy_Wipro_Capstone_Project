package nopcommerce_step_defination;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import io.cucumber.java.en.*;

public class Change_address_step {
    WebDriver driver;
    PageClass pg;
    WebDriverWait wait;
    
    @Given("homepage should be loaded and the myaccounts hyperlink is clearly visible")
    public void homepage_should_be_loaded_and_the_myaccounts_hyperlink_is_clearly_visible() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
    }

    @When("^the user logs in nopcommerce using valid (.*) and (.*)$")
    public void the_user_logs_in_nopcommerce_using_valid_useremail_and_password(String user1, String pass1) {
        pg.completeLogin(user1, pass1);
    }

    @And("then navigate to my account section")
    public void then_navigate_to_my_account_section() throws InterruptedException {
        Thread.sleep(1000);
        
        Assert.assertTrue("Account link should be visible",
                         driver.findElement(By.cssSelector("a[href='/customer/info'][class='ico-account']")).isDisplayed());
        
        driver.findElement(By.cssSelector("a[href='/customer/info'][class='ico-account']")).click();
        
    }

    @And("click on address section and existing address edit")
    public void click_on_address_section_and_existing_address_edit() throws InterruptedException {
        Thread.sleep(1000);
        pg.scrolldown();
        
     
        driver.findElement(By.xpath("//*[@id='main']/div/div[1]/div/div[2]/ul/li[2]/a")).click();
        
        driver.findElement(By.cssSelector("button[class='button-2 edit-address-button']")).click();
        
    }

    @And("^Enter Country, State, (.*), (.*), ZipCode, Phone$")
    public void enter_country_state_city_address1_zip_code_phone(String city, String add) throws InterruptedException {
        pg.enterintoaddressfields(city, add);
    }

    @And("click on the Save button")
    public void click_on_the_save_button() {
        
        
        driver.findElement(By.cssSelector("button[type='submit'][class='button-1 save-address-button']")).click();
        
        
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")),
            ExpectedConditions.urlContains("addresses")
        ));
    }

    @Then("Address Updation successful")
    public void address_updation_successful() {
        try {
            
            boolean success = false;
            try {
                if (driver.findElement(By.cssSelector(".bar-notification.success")).isDisplayed()) {
                    String successMessage = driver.findElement(By.cssSelector(".bar-notification.success")).getText();
                    if (successMessage.contains("successfully") || successMessage.contains("updated")) {
                        success = true;
                        System.out.println("Success message: " + successMessage);
                    }
                }
            } catch (Exception e) {
                System.out.println("No success notification found, checking URL");
            }
            
            // Check if we're back on addresses page
            if (driver.getCurrentUrl().contains("addresses")) {
                success = true;
            }
            
            Assert.assertTrue("Address update should be successful", success);
            System.out.println("User address changed successfully");
            
        } finally {
            driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img")).click();
            driver.close();
        }
    }
}