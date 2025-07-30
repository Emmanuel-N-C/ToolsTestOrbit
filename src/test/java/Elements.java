import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Elements {
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
    public void homepage(){
        System.out.println("Loading up homepage");
        driver.get("https://demoqa.com/");
    }
    @Test(priority = 1)
    public void elementNavigation(){
        driver.findElement(By.xpath("//h5[text()='Elements']")).click();

        // Verify navigation
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("elements") : "Navigation to Elements page failed!";
    }
    @Test(priority = 2)
    public void fillTextBoxForm() {
        // Click Text Box menu
        driver.findElement(By.xpath("//span[text()='Text Box']")).click();

        // Fill in details (use random or static for now)
        driver.findElement(By.id("userName")).sendKeys("John Doe");
        driver.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("currentAddress")).sendKeys("123 Random Street, Test City");
        driver.findElement(By.id("permanentAddress")).sendKeys("456 Permanent Avenue, Example Town");

        // Scroll and click submit
        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

    }
    @Test(priority = 3)
    public void randomCheckBoxSelection() {
        // Navigate to Check Box section
        driver.findElement(By.xpath("//span[text()='Check Box']")).click();

        // Get list of all checkboxes
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//span[@class='rct-checkbox']"));

        // Pick a random checkbox
        Random random = new Random();
        int randomIndex = random.nextInt(checkBoxes.size());
        WebElement randomCheckBox = checkBoxes.get(randomIndex);

        // Scroll into view (optional, avoids click interception)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomCheckBox);

        // Click the randomly chosen checkbox
        randomCheckBox.click();

    }
    @Test(priority = 4)
    public void randomRadioButtonSelection() {
        // Navigate to Radio Button section
        driver.findElement(By.xpath("//span[text()='Radio Button']")).click();

        // Get all radio button options (Yes, Impressive, No)
        List<WebElement> radioOptions = driver.findElements(By.xpath("//label[@class='custom-control-label']"));

        // Pick a random option
        Random random = new Random();
        int randomIndex = random.nextInt(radioOptions.size());
        WebElement selectedRadio = radioOptions.get(randomIndex);

        // Scroll and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedRadio);
        selectedRadio.click();

        // Verify selection (check the output text below the options)
        WebElement outputText = driver.findElement(By.className("text-success"));
        System.out.println("Selected Radio: " + outputText.getText());
        assert !outputText.getText().isEmpty() : "Radio selection did not register!";
    }
    @Test(priority = 5)
    public void addRecordToWebTable() {
        // Navigate to Web Tables section
        driver.findElement(By.xpath("//span[text()='Web Tables']")).click();

        // Click "Add" button
        driver.findElement(By.id("addNewRecordButton")).click();

        // Fill out form
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Smith");
        driver.findElement(By.id("userEmail")).sendKeys("johnsmith@example.com");
        driver.findElement(By.id("age")).sendKeys("30");
        driver.findElement(By.id("salary")).sendKeys("5000");
        driver.findElement(By.id("department")).sendKeys("Engineering");

        // Submit form
        driver.findElement(By.id("submit")).click();

    }
    @Test(priority = 6)
    public void deleteRandomRowFromWebTable() {
        // Navigate to Web Tables section
        driver.findElement(By.xpath("//span[text()='Web Tables']")).click();

        // Get all delete buttons
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[@title='Delete']"));

        if (deleteButtons.size() == 0) {
            System.out.println("No rows available to delete!");
            return;
        }

        // Pick a random delete button
        Random random = new Random();
        int randomIndex = random.nextInt(deleteButtons.size());
        WebElement deleteButton = deleteButtons.get(randomIndex);

        // Scroll and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        deleteButton.click();

    }
    /*@Test(priority = 7)
    public void buttonInteractions() {
        // Navigate to Buttons section
        driver.findElement(By.xpath("//span[text()='Buttons']")).click();

        // Initialize Actions class
        Actions actions = new Actions(driver);

        // Double Click
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickBtn).perform();

        // Right Click
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickBtn).perform();

        // Single Click
        WebElement singleClickBtn = driver.findElement(By.xpath("//button[text()='Click Me']"));
        singleClickBtn.click();

    }
    @Test(priority = 8)
    public void apiLinksClick() {
        // Navigate to Links section
        driver.findElement(By.xpath("//span[text()='Links']")).click();

        // Get all links after the first two
        List<WebElement> apiLinks = driver.findElements(By.xpath("(//a)[position()>2]"));

        // Click each API link and verify response
        for (WebElement link : apiLinks) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
            link.click();

            // Wait briefly for response text to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement response = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("linkResponse")));

            System.out.println("Clicked: " + link.getText() + " | Response: " + response.getText());

        }
    }*/






}
