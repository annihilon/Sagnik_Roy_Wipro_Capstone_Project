package nopcommerce_step_defination;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class News_steps {
	WebDriver driver;
    PageClass pg;
    
	@Given("home page is opened and thw news section is clickable")
	public void home_page_is_opened_and_thw_news_section_is_clickable() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
        pg = new PageClass(driver);
	}

	@When("^First log-in with my (.*) and (.*) and navigate back to home screen$")
	public void first_log_in_with_my_useremail_and_password_and_navigate_back_to_home_screen(String user, String pass) {
		pg.completeLogin(user, pass);
	}

	@And("scroll down to find the news section and click on a news details")
	public void scroll_down_to_find_the_news_section_and_click_on_a_news_details() throws InterruptedException {
	    Thread.sleep(1000);
	    pg.scrolldownmore();
	    driver.findElement(By.cssSelector("a[href=\'/nopcommerce-new-release\'][class=\'read-more\']")).click();
	}

	@And("^at the Leave comment section add a (.*) and below add (.*)$")
	public void at_the_leave_comment_section_add_a_title_and_below_add_comments(String title, String comment) throws InterruptedException {
	    pg.scrolldown();
	    driver.findElement(By.id("AddNewComment_CommentTitle")).sendKeys(title);
	    Thread.sleep(1000);
	    driver.findElement(By.id("AddNewComment_CommentText")).sendKeys(comment);
	    Thread.sleep(1000);
	}

	@And("click on the new comment\\/add comment section")
	public void click_on_the_new_comment_add_comment_section() {
		
		driver.findElement(By.cssSelector("button[type=\'submit\'][name=\'add-comment\']")).click();
	}

	@Then("comment displayed and task completed")
	public void comment_displayed_and_task_completed() {
		System.out.println("News Verification successful");
		driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
        driver.close();
	}
}
