import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Interactions {
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
    public void navigateToInteractions() {
        driver.get("https://demoqa.com/");
        WebElement interactionsCard = driver.findElement(By.xpath("//h5[text()='Interactions']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", interactionsCard);
        interactionsCard.click();
    }
    @Test(priority = 1)
    public void handleSortable() {
        driver.findElement(By.xpath("//span[text()='Sortable']")).click();
        driver.findElement(By.id("demo-tab-grid")).click();
        driver.findElement(By.id("demo-tab-list")).click();

    }
    @Test(priority = 2)
    public void handleSelectable() {
        driver.findElement(By.xpath("//span[text()='Selectable']")).click();

        // Select multiple items
        driver.findElement(By.xpath("//li[text()='Cras justo odio']")).click();
        driver.findElement(By.xpath("//li[text()='Dapibus ac facilisis in']")).click();
        /*driver.findElement(By.id("demo-tab-grid")).click();
        driver.findElement(By.xpath("//div[@id='row1']/li[text()='One']")).click();
        driver.findElement(By.xpath("//div[@id='row1']/li[text()='Two']")).click();
        driver.findElement(By.xpath("//div[@id='row1']/li[text()='Three']")).click();*/

    }
    @Test(priority = 3)
    public void handleResizable() {
        driver.findElement(By.xpath("//span[text()='Resizable']")).click();

        WebElement resizeHandle = driver.findElement(By.cssSelector("span.react-resizable-handle"));

        Actions actions = new Actions(driver);
        actions.clickAndHold(resizeHandle)
                .moveByOffset(100, 50)  // drag right and down
                .release()
                .perform();

        System.out.println("Box resized successfully!");
    }
    @Test(priority = 4)
    public void handleDroppable() {
        driver.findElement(By.xpath("//span[text()='Droppable']")).click();

        WebElement dragMe = driver.findElement(By.id("draggable"));
        WebElement dropHere = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragMe, dropHere).perform();

        System.out.println("Element dropped successfully!");
    }
    @Test(priority = 5)
    public void handleDraggable() {
        // Locate the Dragabble menu and use JS to scroll and click
        WebElement draggableMenu = driver.findElement(By.xpath("//span[text()='Dragabble']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", draggableMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", draggableMenu);

        // Locate the draggable box
        WebElement dragBox = driver.findElement(By.id("dragBox"));

        // Perform drag and drop by offset
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(dragBox, 150, 100).perform(); // move box to the right and down

        System.out.println("Box dragged successfully!");
    }






}
