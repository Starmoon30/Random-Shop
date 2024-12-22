import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Change_password {
  private WebDriver driver;
  private Map<String, Object> vars;
  private JavascriptExecutor js;
  private List<String[]> csvData;  // 用于保存从CSV文件中读取的数据

  @Before
  public void setUp() throws IOException, CsvException {
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<>();

    // 读取CSV文件并加载数据
    CSVReader csvReader = new CSVReader(new FileReader("C:/Users/23888/Desktop/我的测试/csv/Change_password.csv"));
    csvData = csvReader.readAll();
    csvReader.close();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void changepassword() throws InterruptedException {
    // 打开网页并设置浏览器窗口大小
    driver.get("http://localhost:8090/");
    driver.manage().window().setSize(new Dimension(876, 676));

    // 选择"Admin"并登录
    driver.findElement(By.cssSelector("select")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector("select"));
      dropdown.findElement(By.xpath("//option[. = 'Admin']")).click();
    }
    driver.findElement(By.cssSelector("option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("input:nth-child(1)")).click();
    driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys("admin123");
    Thread.sleep(500);
    driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys("admin123");
    Thread.sleep(500);
    driver.findElement(By.cssSelector("button")).click();

    // 进行进一步的操作，选择修改密码
    driver.findElement(By.cssSelector(".el-menu-item-group:nth-child(7) .el-menu-item")).click();
    driver.findElement(By.xpath("//input")).click();
    driver.findElement(By.xpath("//input")).sendKeys(csvData.get(0)[0]);  // 从CSV读取旧密码
    Thread.sleep(500);
    driver.findElement(By.xpath("//div[2]/div/div/div/input")).sendKeys(csvData.get(0)[1]);  // 从CSV读取新密码
    Thread.sleep(500);
    driver.findElement(By.xpath("//div[3]/div/div/div/input")).sendKeys(csvData.get(0)[2]);  // 从CSV读取确认密码
    Thread.sleep(500);

    // 点击保存按钮
    driver.findElement(By.cssSelector(".el-button")).click();
    Thread.sleep(2000);

    // 模拟移动鼠标操作
    {
      WebElement element = driver.findElement(By.cssSelector(".el-button"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
  }
}
