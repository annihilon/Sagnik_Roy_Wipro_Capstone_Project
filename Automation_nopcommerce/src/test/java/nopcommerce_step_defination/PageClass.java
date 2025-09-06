package nopcommerce_step_defination;

import java.io.FileWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageClass {
	WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    JavascriptExecutor js;
    
    //register page locators
    By genderMale = By.id("gender-male");
    By genderFemale = By.id("gender-female");
    By fname = By.id("FirstName");
    By lname = By.id("LastName");
    By email = By.id("Email");
    By company = By.id("Company");
    By newsletterCheckbox = By.id("Newsletter");
    By pwd = By.id("Password");
    By cpwd = By.id("ConfirmPassword");
    By registerBtn = By.id("register-button");
    By registrationResult = By.className("result");
    By continueBtn = By.xpath("//a[contains(text(),'Continue')]");
    //error msg after register
    By errorMessages = By.cssSelector(".message-error li, .field-validation-error");
    
    //login page locators
    By loginLink = By.className("ico-login");
    By logemail = By.cssSelector("input[class='email']");
    By logpass = By.id("Password");
    By logremember = By.id("RememberMe");
    By logbtn = By.cssSelector("button[type='submit'][class='button-1 login-button']");
    
    //search locators
    By searchField = By.id("small-searchterms");
    By searchButton = By.cssSelector("button[class='button-1 search-box-button']");
    By searchResults = By.cssSelector(".product-item");
    
    
    
    
    
    public PageClass(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }
    
    public void scrolldown() throws InterruptedException {
    	js.executeScript("window.scrollTo(0, 300)");
    	Thread.sleep(1000);
    }
    public void scrolldownmore() throws InterruptedException {
    	js.executeScript("window.scrollTo(0, 500)");
    	Thread.sleep(1000);
    }
    public void scrolltobottom() throws InterruptedException {
    	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    	Thread.sleep(1000);
    }
    public void scrolltotop() throws InterruptedException {
    	js.executeScript("window.scrollTo(0, 0)");
    	Thread.sleep(1000);
    }
 // Register method
    public void register(String gender, String firstName, String lastName, String email, 
            String companyName, boolean newsletter, String password, String confirmPassword) {
    	
    	//gender
    	if (gender.equalsIgnoreCase("male")) {
    		driver.findElement(genderMale).click();	
    	} else if (gender.equalsIgnoreCase("female")) {
    		driver.findElement(genderFemale).click();
    	}
    	//personal details
    	driver.findElement(fname).sendKeys(firstName);
    	driver.findElement(lname).sendKeys(lastName);
    	//email and company
    	driver.findElement(this.email).sendKeys(email);
    	driver.findElement(company).sendKeys(companyName);
    	driver.findElement(newsletterCheckbox).click();
    	//passwords
    	driver.findElement(pwd).sendKeys(password);
    	driver.findElement(cpwd).sendKeys(confirmPassword);
    	driver.findElement(registerBtn).click();

    }
    // registration result message
    public String getRegistrationResult() {
        return driver.findElement(registrationResult).getText();
    }
    	// Get error messages
    public String getErrorMessages() {
        try {
            return driver.findElement(errorMessages).getText();
        } catch (NoSuchElementException e) {
            return "No error messages found";
        }
    }
    // Login page methods
    public void navigateToLoginPage() {
        driver.findElement(loginLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(logemail));
    }
    public void enterLoginCredentials(String email, String password) {
        driver.findElement(logemail).clear();
        driver.findElement(logemail).sendKeys(email);
        driver.findElement(logpass).clear();
        driver.findElement(logpass).sendKeys(password);
    }
    public void setRememberMe(boolean remember) {
        WebElement rememberMe = driver.findElement(logremember);
        if (remember && !rememberMe.isSelected()) {
            rememberMe.click();
        } else if (!remember && rememberMe.isSelected()) {
            rememberMe.click();
        }
    }
    public void clickLoginButton() {
        driver.findElement(logbtn).click();
    }
    //Complete login method
    public void completeLogin(String email, String password) {
        navigateToLoginPage();
        enterLoginCredentials(email, password);
        clickLoginButton();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img")).click();
    }
    
    
    //search method
    public void searchForItem(String searchTerm) throws InterruptedException {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(searchTerm);
        driver.findElement(searchButton).click();
        scrolldown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(searchField).clear();
    }
    
    
    //add and delete from cart locators
    By item1 = By.xpath("//*[@id=\'main\']/div/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[1]");//searching lenovo
    By cart = By.cssSelector("a[href='/cart'][class='ico-cart']");
    By cartremove = By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[7]/button");
    
    
    //add to cart method
    public void addtocart() throws InterruptedException {
    	Thread.sleep(1000);
    	scrolldown();
        driver.findElement(item1).click();
    }
    public void deletefromcart() throws InterruptedException {
    	Thread.sleep(5000);
    	scrolltotop();
        driver.findElement(cart).click();
        Thread.sleep(1000);
        driver.findElement(cartremove).click();
        Thread.sleep(1000);
        
    }
    
    //order module report
    public void writeOrderDetailsToFile(String orderNumber, String orderDate, String orderStatus, String orderTotal) {
        String fileName = "order_details_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
        
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("========================================\n");
            writer.write("         ORDER DETAILS REPORT\n");
            writer.write("========================================\n");
            writer.write("Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("========================================\n");
            writer.write("Order Number: " + orderNumber + "\n");
            writer.write("Order Date: " + orderDate + "\n");
            writer.write("Order Status: " + orderStatus + "\n");
            writer.write("Order Total: " + orderTotal + "\n");
            writer.write("========================================\n");
            writer.write("Page Title: " + driver.getTitle() + "\n");
            writer.write("Page URL: " + driver.getCurrentUrl() + "\n");
            writer.write("========================================\n");
            writer.write("TEST RESULT: SUCCESS\n");
            writer.write("All order details were successfully displayed\n");
            writer.write("========================================\n");
            
            System.out.println("Order details written to file: " + fileName);
            
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
            
            //print to console for exception
            System.out.println("========================================");
            System.out.println("         ORDER DETAILS (CONSOLE)");
            System.out.println("========================================");
            System.out.println("Order Number: " + orderNumber);
            System.out.println("Order Date: " + orderDate);
            System.out.println("Order Status: " + orderStatus);
            System.out.println("Order Total: " + orderTotal);
            System.out.println("========================================");
        }
    }

//    public void enterintoaddressfields(String city, String add, String zip, String phone) throws InterruptedException {
//        Thread.sleep(500);
//     // Select Country (United States) using Select class
//        WebElement countryDropdown = driver.findElement(By.id("Address_CountryId"));
//        Select countrySelect = new Select(countryDropdown);
//        countrySelect.selectByValue("1"); // United States
//        
//        Thread.sleep(500);
//        
//        // Wait for State dropdown to populate (important!)
//        Thread.sleep(1000); // Additional wait for state dropdown to load after country selection
//        
//        // Select State (California) using Select class
//        WebElement stateDropdown = driver.findElement(By.id("Address_StateProvinceId"));
//        Select stateSelect = new Select(stateDropdown);
//        stateSelect.selectByValue("5"); // California
//        
//        Thread.sleep(500);
//        WebElement form_city = driver.findElement(By.id("Address_City"));
//        form_city.clear();
//        form_city.sendKeys(city);
//        
//        Thread.sleep(500);
//        WebElement address1 = driver.findElement(By.id("Address_Address1"));
//        address1.clear();
//        address1.sendKeys(add);
//        
//        Thread.sleep(500);
//        
//        WebElement zipCode = driver.findElement(By.id("Address_ZipPostalCode"));
//        zipCode.clear();
//        zipCode.sendKeys(zip); 
//        
//        Thread.sleep(500);
//        WebElement phoneNumber = driver.findElement(By.id("Address_PhoneNumber"));
//        phoneNumber.clear();
//        phoneNumber.sendKeys(phone); 
//        
//        Thread.sleep(1000);
//    }
    public void enterintoaddressfields(String city, String add) throws InterruptedException {
        Thread.sleep(500);
        
        // Enter City
        WebElement form_city = driver.findElement(By.id("Address_City"));
        form_city.clear();
        form_city.sendKeys(city);
        
        Thread.sleep(500);
        
        // Enter Address
        WebElement address1 = driver.findElement(By.id("Address_Address1"));
        address1.clear();
        address1.sendKeys(add);
        
        Thread.sleep(500);
        
        WebElement zipCode = driver.findElement(By.id("Address_ZipPostalCode"));
        zipCode.clear();
        zipCode.sendKeys("7009");
        
        Thread.sleep(500);
        
        // Enter Phone Number
        WebElement phoneNumber = driver.findElement(By.id("Address_PhoneNumber"));
        phoneNumber.clear();
        phoneNumber.sendKeys("7412369852");
        
        Thread.sleep(1000);
    }
    
    
    
    
    
    
    
    
    
    
}
