package test;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import au.com.bytecode.opencsv.CSVReader;

public class SelectOrder {
	private static final String path = "D:\\JavaScr\\1\\src\\file\\log-suc.csv";
	HashMap<String, String> map = new HashMap<String, String>();

	@Before
	public void set() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] l;
		reader.readNext();
		while ((l = reader.readNext()) != null) {
			String[] data = l;
			map.put(data[0], data[1]);
		}
	}

	@Test
    public void testMyMethod() throws IOException {
		System.setProperty("webdriver.gecko.driver", "D:/Drivers/web/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        //卖家登录
        driver.get("http://localhost:8080/rand-shop/home.jsp");
        String expectedUrl = "http://localhost:8080/rand-shop/seller_login.jsp";
        WebElement sellerButton = driver.findElement(By.xpath("//a[contains(text(), '卖家平台')]"));

        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sellerButton.click();

        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        assertTrue("未能成功跳转", currentUrl.equals(expectedUrl));
        for(String key : map.keySet()) {    	
        	 WebElement accountInput = driver.findElement(By.name("account"));
             accountInput.sendKeys(key);
        	
             WebElement passwordInput = driver.findElement(By.name("pwd"));
             passwordInput.sendKeys(map.get(key));
        	try {
        		Thread.sleep(2000); 
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        	
        	WebElement submitButton = driver.findElement(By.className("submit"));
            submitButton.click();       	
        }
        try {
        		Thread.sleep(3000); 
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	} 
		//选择订单并冻结
		WebElement sellerButton2 = driver.findElement(By.xpath("//a[contains(text(), '订单管理')]"));
		sellerButton2.click();
		
		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        boolean found = false;
        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            String oDesc = row.findElement(By.xpath("./td[2]")).getText();
            if (oDesc.equals("小翁 2233499210 西湖区")) {
                found = true;
                WebElement acceptButton = row.findElement(By.xpath("./td[7]/a"));
                try {
        			Thread.sleep(1000);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
                acceptButton.click();
                break;
            }
        }
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement back1 = driver.findElement(By.linkText("返回"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        back1.click();
		
		WebElement sellerButton4 = driver.findElement(By.xpath("//a[contains(text(), '货架管理')]"));
		sellerButton4.click();
		WebElement sellerButton5 = driver.findElement(By.xpath("//a[contains(text(), '立即冻结')]"));
		sellerButton5.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//进入买家平台，确认冻结候补效果
		driver.get("http://localhost:8080/rand-shop/home.jsp");
		WebElement Button = driver.findElement(By.xpath("//a[contains(text(), '买家平台')]"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Button.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.close();

	}
}
