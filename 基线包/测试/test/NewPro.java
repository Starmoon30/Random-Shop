package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
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

public class NewPro {
	HashMap<String, String> map = new HashMap<String, String>();

	@Before
	public void set() throws IOException {
		String path = "D:\\JavaScr\\1\\src\\file\\log-suc.csv";
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] l;
		reader.readNext();
		while ((l = reader.readNext()) != null) {
			String[] data = l;
			map.put(data[0], data[1]);
		}
	}

	@Test
	public void test() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.gecko.driver", "D:/Drivers/web/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        //卖家登录
        driver.get("http://localhost:8080/rand-shop/home.jsp");
        String expectedUrl = "http://localhost:8080/rand-shop/seller_login.jsp";
        WebElement sellerButton = driver.findElement(By.xpath("//a[contains(text(), '卖家平台')]"));

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sellerButton.click();

        try {
            Thread.sleep(1000); 
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
        		Thread.sleep(1000); 
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        	
        	WebElement submitButton = driver.findElement(By.className("submit"));
            submitButton.click();       	
        }
        try {
        	Thread.sleep(1000); 
        } catch (InterruptedException e) {
        	e.printStackTrace();
        } 
        
        WebElement sellerButton1 = driver.findElement(By.xpath("//a[contains(text(), '货架管理')]"));
		sellerButton1.click();
        try {
        	Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement back = driver.findElement(By.linkText("返回"));
        back.click();
        WebElement sellerButton2 = driver.findElement(By.xpath("//a[contains(text(), '商品管理')]"));
	    sellerButton2.click();
		try (CSVReader reader = new CSVReader(new FileReader("D:\\JavaScr\\1\\src\\file\\product.csv"))) {
			reader.readNext();
			List<String[]> rows = reader.readAll();
	        for (String[] row : rows) {
	                // 输入商品名
	        WebElement nameInput = driver.findElement(By.name("name"));
	        nameInput.clear();
	        nameInput.sendKeys(row[0]);
	        System.out.println(row[0]);

                // 输入商品描述
            WebElement descInput = driver.findElement(By.name("desc"));
            descInput.clear();
            descInput.sendKeys(row[1]);
            System.out.println(row[1]);

                // 输入商品价格
            WebElement valueInput = driver.findElement(By.name("value"));
            valueInput.clear();
            valueInput.sendKeys(row[2]);
            System.out.println(row[2]);

            System.out.println(row[3]);
            WebElement imgInput = driver.findElement(By.name("file"));
            imgInput.clear();
            imgInput.sendKeys(row[3]);
	        //driver.findElement(By.name("file")).sendKeys("D:\\JavaScr\\rand-shop\\src\\img\\wine.jpg");
	        
	            
	            try {
	            	Thread.sleep(1000); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            } 
	                // 提交表单
	            WebElement submitButton = driver.findElement(By.className("submit"));
	            submitButton.click();       
	            try {
	            	Thread.sleep(1000); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }   
	                // 返回商品管理页面以进行下一次测试
	            WebElement back1 = driver.findElement(By.linkText("返回"));
	    		try {
	    			Thread.sleep(1000);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	            back1.click();
	            WebElement sellerButton3 = driver.findElement(By.xpath("//a[contains(text(), '货架管理')]"));
	    		sellerButton3.click();
	            try {
	            	Thread.sleep(1000); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
