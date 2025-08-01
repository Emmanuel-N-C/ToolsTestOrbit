import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AFW {
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
    public void navigateToAlertsFrameWindows() {
        System.out.println("Navigating to Alerts, Frame & Windows section");
        driver.get("https://demoqa.com/");
        driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']")).click();
        driver.findElement(By.xpath("//span[text()='Alerts']")).click();
    }

    @Test(priority = 1)
    public void handleAlerts() throws InterruptedException {
        // 1. Simple Alert
        driver.findElement(By.id("alertButton")).click();
        Alert simpleAlert = driver.switchTo().alert();
        System.out.println("Simple Alert Text: " + simpleAlert.getText());
        simpleAlert.accept();

        // 2. Timed Alert
        driver.findElement(By.id("timerAlertButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert timedAlert = driver.switchTo().alert();
        System.out.println("Timed Alert Text: " + timedAlert.getText());
        timedAlert.accept();

        // 3. Confirm Alert
        driver.findElement(By.id("confirmButton")).click();
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Confirm Alert Text: " + confirmAlert.getText());
        confirmAlert.dismiss(); // or accept()

        // 4. Prompt Alert
        driver.findElement(By.id("promtButton")).click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("Test Input");
        promptAlert.accept();
    }
    @Test(priority = 2)
    public void handleSingleFrame() {
        // Navigate to Frames page
        driver.findElement(By.xpath("//span[text()='Frames']")).click();

        // Switch to frame by ID
        driver.switchTo().frame("frame1");

        // Get text inside frame
        WebElement frameText = driver.findElement(By.id("sampleHeading"));
        System.out.println("Frame Text: " + frameText.getText());

        // Switch back to default content
        driver.switchTo().defaultContent();
    }
    @Test(priority = 3)
    public void handleNestedFrames() {
        // Navigate to Nested Frames page
        driver.findElement(By.xpath("//span[text()='Nested Frames']")).click();

        // Switch to parent frame
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='/sampleiframe']")));

        // Inside parent → switch to child frame
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

        // Get child frame text
        WebElement childText = driver.findElement(By.tagName("p"));
        System.out.println("Nested Frame Child Text: " + childText.getText());

        // Switch back to main page
        driver.switchTo().defaultContent();
    }
    @Test(priority = 4)
    public void handleModalDialogs() throws InterruptedException {
        // Navigate to Modal Dialogs page
        driver.findElement(By.xpath("//span[text()='Modal Dialogs']")).click();

        // Open small modal
        driver.findElement(By.id("showSmallModal")).click();
        WebElement smallModal = driver.findElement(By.id("example-modal-sizes-title-sm"));
        System.out.println("Small Modal Title: " + smallModal.getText());
        driver.findElement(By.id("closeSmallModal")).click();

        Thread.sleep(1000);

        // Open large modal
        driver.findElement(By.id("showLargeModal")).click();
        WebElement largeModal = driver.findElement(By.id("example-modal-sizes-title-lg"));
        System.out.println("Large Modal Title: " + largeModal.getText());
        driver.findElement(By.id("closeLargeModal")).click();
    }


}
