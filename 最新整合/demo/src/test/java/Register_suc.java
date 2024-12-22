import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register_suc {
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
  public void Registersuc() throws InterruptedException, IOException, CsvException {
    // 读取 CSV 文件
    List<String[]> csvData = readCSV("C:\\Users\\23888\\Desktop\\我的测试\\csv\\Register_suc.csv");

    // 获取 CSV 数据中的第一行
    String[] userData = csvData.get(0);

    // 启动浏览器并打开页面
    driver.get("http://localhost:8090/");
    driver.manage().window().setSize(new Dimension(876, 674));

    // 点击“注册账号”链接
    driver.findElement(By.linkText("注册账号")).click();
    Thread.sleep(500);

    // 填写表单，使用从 CSV 中读取的数据
    driver.findElement(By.xpath("//div/input")).click();
    driver.findElement(By.xpath("//div/input")).sendKeys(userData[0]); // 用户名
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[2]/div/div/div/input")).click();
    driver.findElement(By.xpath("//div[2]/div/div/div/input")).sendKeys(userData[1]); // 密码
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[3]/div/div/div/input")).click();
    driver.findElement(By.xpath("//div[3]/div/div/div/input")).sendKeys(userData[2]); // 手机号
    Thread.sleep(500);

    driver.findElement(By.xpath("//div[4]/div/div/div/input")).click();
    driver.findElement(By.xpath("//div[4]/div/div/div/input")).sendKeys(userData[3]); // 学校
    Thread.sleep(500);

    // 点击提交按钮
    driver.findElement(By.cssSelector(".el-button:nth-child(5)")).click();
    Thread.sleep(2000);
  }

  // 读取 CSV 文件并返回数据
  private List<String[]> readCSV(String filePath) throws IOException, CsvException {
    CSVReader reader = new CSVReader(new FileReader(filePath));
    List<String[]> data = reader.readAll();
    reader.close();
    return data;
  }
}
