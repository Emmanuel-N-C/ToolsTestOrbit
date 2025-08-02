import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Widgets {
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
    public void navigateToWidgets() {
        driver.get("https://demoqa.com/");
        WebElement widgetsMenu = driver.findElement(By.xpath("//h5[text()='Widgets']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widgetsMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", widgetsMenu);
    }

    // Accordian
    @Test(priority = 1)
    public void handleAccordian() {
        WebElement accordianMenu = driver.findElement(By.xpath("//span[text()='Accordian']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accordianMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", accordianMenu);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement firstHeading = driver.findElement(By.id("section1Heading"));
        firstHeading.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("section1Content")));

        WebElement secondHeading = driver.findElement(By.id("section2Heading"));
        secondHeading.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("section2Content")));
    }

    // Auto Complete
    @Test(priority = 2)
    public void handleAutoComplete() throws InterruptedException {
        WebElement autoCompleteMenu = driver.findElement(By.xpath("//span[text()='Auto Complete']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", autoCompleteMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", autoCompleteMenu);

        String[] colors = {"Red", "Blue", "Green", "Yellow", "Black", "White", "Purple"};
        Random random = new Random();

        WebElement autoCompleteInput = driver.findElement(By.id("autoCompleteMultipleInput"));
        for (int i = 0; i < 2; i++) {
            String randomColor = colors[random.nextInt(colors.length)];
            autoCompleteInput.sendKeys(randomColor);
            autoCompleteInput.sendKeys(Keys.ENTER);
            Thread.sleep(500);
        }

        WebElement chipsContainer = driver.findElement(By.className("auto-complete__multi-value"));
        assert chipsContainer.isDisplayed() : "No color chips displayed after input!";
    }

    // Date Picker
    @Test(priority = 3)
    public void handleDatePicker() {
        WebElement datePickerMenu = driver.findElement(By.xpath("//span[text()='Date Picker']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datePickerMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", datePickerMenu);

        WebElement dateInput = driver.findElement(By.id("datePickerMonthYearInput"));
        dateInput.clear();
        dateInput.sendKeys("08/15/2025");
        dateInput.sendKeys(Keys.ENTER);
    }

    // Slider
    @Test(priority = 4)
    public void handleSlider() {
        WebElement sliderMenu = driver.findElement(By.xpath("//span[text()='Slider']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sliderMenu);

        WebElement slider = driver.findElement(By.className("range-slider"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, 50, 0).perform();
    }

    // Progress Bar
    @Test(priority = 5)
    public void handleProgressBar() {
        WebElement progressBarMenu = driver.findElement(By.xpath("//span[text()='Progress Bar']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", progressBarMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", progressBarMenu);

        driver.findElement(By.id("startStopButton")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("progressBar"), "100%"));

        System.out.println("Progress bar reached 100%");
    }

    // Tabs
   /*@Test(priority = 6)
    public void handleTabs() {
        WebElement tabsMenu = driver.findElement(By.xpath("//span[text()='Tabs']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tabsMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabsMenu);

        driver.findElement(By.id("demo-tab-origin")).click();
        WebElement originContent = driver.findElement(By.id("demo-tabpane-origin"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(originContent));
        assert originContent.isDisplayed() : "Origin tab content not displayed!";

        driver.findElement(By.id("demo-tab-use")).click();
        WebElement useContent = driver.findElement(By.id("demo-tabpane-use"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(useContent));
        assert useContent.isDisplayed() : "Use tab content not displayed!";
    }

    // Tool Tips
    @Test(priority = 7)
    public void handleToolTips() throws InterruptedException {
        WebElement toolTipsMenu = driver.findElement(By.xpath("//span[text()='Tool Tips']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toolTipsMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toolTipsMenu);

        WebElement hoverButton = driver.findElement(By.id("toolTipButton"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverButton).perform();

        Thread.sleep(2000); // Wait for tooltip to appear
        WebElement tooltip = driver.findElement(By.className("tooltip-inner"));
        assert tooltip.isDisplayed() : "Tooltip not displayed!";
    }

    // Menu
    @Test(priority = 8)
    public void handleMenu() {
        WebElement menuMenu = driver.findElement(By.xpath("//span[text()='Menu']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuMenu);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement mainItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='Main Item 2']")));

        Actions actions = new Actions(driver);

        actions.moveToElement(mainItem2).pause(Duration.ofSeconds(1)).perform();

        WebElement subSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        actions.moveToElement(subSubList).pause(Duration.ofSeconds(1)).perform();

        WebElement subSubItem1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='Sub Sub Item 1']")));

        assert subSubItem1.isDisplayed() : "Sub Sub Item 1 not visible!";
    }
    @Test(priority = 9)
    public void handleSelectMenu() throws InterruptedException {
        // Scroll and click on Select Menu
        WebElement selectMenu = driver.findElement(By.xpath("//span[text()='Select Menu']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectMenu);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Random random = new Random();
        Actions actions = new Actions(driver); // FIX: Initialize Actions

        // 1. Select Value (React-select)
        WebElement selectValue = driver.findElement(By.id("react-select-2-input"));
        selectValue.click();
        selectValue.sendKeys("Group 1, option 1");
        selectValue.sendKeys(Keys.ENTER);

        // 2. Select One (React-select)
        WebElement selectOne = driver.findElement(By.id("react-select-3-input"));
        selectOne.click();
        selectOne.sendKeys("Prof.");
        selectOne.sendKeys(Keys.ENTER);

        // 3. Old Style Select Menu (HTML Select)
        WebElement oldStyleSelect = driver.findElement(By.id("oldSelectMenu"));
        new org.openqa.selenium.support.ui.Select(oldStyleSelect)
                .selectByVisibleText("Green");

        // 4. Multi Select Drop Down (React-select)
        WebElement multiSelect = driver.findElement(By.id("react-select-4-input"));
        multiSelect.click();
        multiSelect.sendKeys("Blue");
        multiSelect.sendKeys(Keys.ENTER);
        multiSelect.sendKeys("Black");
        multiSelect.sendKeys(Keys.ENTER);

        // 5. Standard Multi Select (HTML Select) – Select multiple individually
        WebElement standardMulti = driver.findElement(By.name("cars"));
        org.openqa.selenium.support.ui.Select multi = new org.openqa.selenium.support.ui.Select(standardMulti);

        multi.selectByVisibleText("Volvo");
        multi.selectByVisibleText("Opel");

        Thread.sleep(1000); // Just to observe result
    }*/


}



