import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Register_fail {
  private WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/TestDriver/chromedriver.exe");
    driver = new ChromeDriver();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void faillllllllll() throws InterruptedException, IOException, CsvException {
    // 读取CSV文件
    List<String[]> csvData = readCsv("src/main/resources/TestDriver/CSV/Register_fail.csv");

    // 打开页面
    driver.get("http://localhost:8090/");
    driver.manage().window().setSize(new Dimension(876, 674));

    // 注册过程
    driver.findElement(By.linkText("注册账号")).click();

    // 填写表单数据
    driver.findElement(By.xpath("//div/input")).click();
    driver.findElement(By.xpath("//div/input")).sendKeys(csvData.get(0)[0]); // 姓名

    Thread.sleep(500);
    driver.findElement(By.xpath("//div[2]/div/div/div/input")).click();
    driver.findElement(By.xpath("//div[2]/div/div/div/input")).sendKeys(csvData.get(0)[1]); // 密码

    Thread.sleep(500);
    driver.findElement(By.xpath("//div[3]/div/div/div/input")).click();
    driver.findElement(By.xpath("//div[3]/div/div/div/input")).sendKeys(csvData.get(0)[2]); // 电话

    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-button:nth-child(5)")).click();

    Thread.sleep(3000);
  }

  // 读取CSV文件
  private List<String[]> readCsv(String filePath) throws IOException, CsvException {
    try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
      return reader.readAll();
    }
  }
}
