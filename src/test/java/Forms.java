import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Forms {

    WebDriver driver;
    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @AfterTest
    public void AfterTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test(priority = 0)
    public void navigateToForms() {
        System.out.println("Navigating to Forms Section");
        driver.get("https://demoqa.com/");
        driver.findElement(By.xpath("//h5[text()='Forms']")).click();
        driver.findElement(By.xpath("//span[text()='Practice Form']")).click();
    }

    @Test(priority = 1)
    public void fillPracticeForm() throws InterruptedException {
        Random random = new Random();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Random data generation
        String firstName = "User" + random.nextInt(1000);
        String lastName = "Test" + random.nextInt(1000);
        String email = "user" + random.nextInt(1000) + "@example.com";
        String mobile = "9" + (100000000 + random.nextInt(900000000));
        String address = "Street " + random.nextInt(100) + ", Test City";

        // Fill basic fields
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);

        // Select gender randomly
        String[] genders = {"Male", "Female", "Other"};
        String randomGender = genders[random.nextInt(genders.length)];
        driver.findElement(By.xpath("//label[text()='" + randomGender + "']")).click();

        // Enter mobile number
        driver.findElement(By.id("userNumber")).sendKeys(mobile);

        // Subjects - type and press Enter
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("Maths");
        subjectsInput.sendKeys(Keys.ENTER);

        // Random hobby selection with safe click
        String[] hobbies = {"Sports", "Reading", "Music"};
        String randomHobby = hobbies[random.nextInt(hobbies.length)];

        WebElement hobbyElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[contains(text(),'" + randomHobby + "')]")
        ));

        // Scroll and click via JavaScript to avoid interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hobbyElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbyElement);

        // Current address
        driver.findElement(By.id("currentAddress")).sendKeys(address);

        // State & City dropdowns (react-select fields)
        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR");
        driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);

        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi");
        driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);

        // Scroll and submit
        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        // Verify submission modal
        WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
        assert modalTitle.isDisplayed() : "Submission modal not displayed!";

        // pause to view modal result
        Thread.sleep(2000);
    }



}
