// Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

public class History_orders {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/TestDriver/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void history() throws InterruptedException {
    driver.get("http://localhost:8090/");    Thread.sleep(500);
    driver.manage().window().setSize(new Dimension(876, 681));    Thread.sleep(500);
    driver.findElement(By.cssSelector("input:nth-child(1)")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys("A1");    Thread.sleep(500);
    driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys("A1");    Thread.sleep(500);
    driver.findElement(By.cssSelector("button")).click();    Thread.sleep(500);
    {
      WebElement element = driver.findElement(By.cssSelector("button"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.linkText("用户信息")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-sub-menu__title")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-menu-item:nth-child(3)")).click();    Thread.sleep(1000);
  }
}
