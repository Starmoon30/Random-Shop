// Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class Goods_on {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void goodson() throws InterruptedException {
    driver.get("http://localhost:8090/");
    driver.manage().window().setSize(new Dimension(876, 677));
    {
      WebElement dropdown = driver.findElement(By.cssSelector("select"));
      dropdown.findElement(By.xpath("//option[. = 'Admin']")).click();
    }
    driver.findElement(By.cssSelector("option:nth-child(2)")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys("admin123");    Thread.sleep(500);
    driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys("admin123");    Thread.sleep(500);
    driver.findElement(By.cssSelector("input:nth-child(1)")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector("button")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-sub-menu:nth-child(2) > .el-sub-menu__title")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-sub-menu:nth-child(2) .el-menu-item:nth-child(3)")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-card:nth-child(1) .el-button")).click();    Thread.sleep(500);
    driver.findElement(By.cssSelector(".is-active:nth-child(3)")).click();    Thread.sleep(500);
  }
}
