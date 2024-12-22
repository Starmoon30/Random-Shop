import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login_suc {
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
  public void logintest() throws InterruptedException, IOException, CsvValidationException {
    // 从CSV文件中读取登录信息
    String[] loginData = readCSV("src/main/resources/TestDriver/CSV/log-suc.csv");
    String username = loginData[0];  // 假设用户名在CSV的第一列
    String password = loginData[1];  // 假设密码在CSV的第二列

    driver.get("http://localhost:8090/");
    driver.manage().window().setSize(new Dimension(856, 596));

    // 输入用户名
    driver.findElement(By.cssSelector("input:nth-child(1)")).click();
    driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys(username);
    Thread.sleep(1000);

    // 输入密码
    driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys(password);
    Thread.sleep(2000);

    // 点击登录按钮
    driver.findElement(By.cssSelector("button")).click();

    // Perform any additional actions like hovering over the button
    {
      WebElement element = driver.findElement(By.cssSelector("button"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
      Thread.sleep(3000);
    }
  }

  // 逐行读取CSV文件并返回数据
  private String[] readCSV(String filePath) throws IOException, CsvValidationException {
    CSVReader reader = new CSVReader(new FileReader(filePath));
    String[] loginData = null;

    // 读取CSV文件的第一行（假设只有一行数据）
    String[] nextLine;
    if ((nextLine = reader.readNext()) != null) {
      loginData = nextLine;  // 保存第一行（用户名和密码）
    }
    reader.close();

    return loginData;
  }
}
