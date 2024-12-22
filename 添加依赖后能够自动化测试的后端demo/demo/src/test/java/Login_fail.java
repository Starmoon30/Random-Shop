import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login_fail {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  // CSV文件路径
  private String csvFilePath = "C:\\Users\\23888\\Desktop\\我的测试\\csv\\log-fai.csv";

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
  public void logFail() throws InterruptedException, IOException, CsvValidationException {
    driver.get("http://localhost:8090/");
    driver.manage().window().setSize(new Dimension(876, 674));

    // 读取CSV文件中的数据
    List<String[]> csvData = readCSV(csvFilePath);

    for (String[] row : csvData) {
      // 假设CSV文件第一列是用户名，第二列是密码
      String username = row[0];
      String password = row[1];

      // 输入用户名和密码
      driver.findElement(By.cssSelector("input:nth-child(1)")).clear();
      driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys(username);
      driver.findElement(By.cssSelector("input:nth-child(2)")).clear();
      driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys(password);

      // 点击登录按钮
      driver.findElement(By.cssSelector("button")).click();

      // 等待页面加载
      Thread.sleep(3000);

      // 你可以在此处增加更多的验证步骤，如检查登录是否失败或成功
    }
  }

  // 读取CSV文件
  private List<String[]> readCSV(String filePath) throws IOException, CsvValidationException {
    CSVReader csvReader = new CSVReader(new FileReader(filePath));
    List<String[]> csvData = new ArrayList<>();
    String[] line;

    // 跳过标题行（如果有的话）
    // csvReader.skip(1);

    while ((line = csvReader.readNext()) != null) {
      csvData.add(line);
    }
    csvReader.close();
    return csvData;
  }
}
