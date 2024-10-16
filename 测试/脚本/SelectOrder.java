package test;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectOrder {
	@Test
	public void testMyMethod() {
		System.setProperty("webdriver.gecko.driver", "D:/Drivers/web/geckodriver.exe");

		WebDriver driver = new FirefoxDriver();
		
		 //进入卖家管理
		driver.get("http://localhost:8080/rand-shop/home.jsp");
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
		WebElement accountInput = driver.findElement(By.name("account"));
		accountInput.sendKeys("123");

		WebElement passwordInput = driver.findElement(By.name("pwd"));
		passwordInput.sendKeys("123");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement submitButton = driver.findElement(By.className("submit"));
		submitButton.click();

		try {
			Thread.sleep(1000);
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
            if (oDesc.equals("xxx need 114514543 23423@qq.com")) {
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement sellerButton1 = driver.findElement(By.xpath("//a[contains(text(), '申请候补')]"));
        sellerButton1.click();
        
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement descInput = driver.findElement(By.id("Desc"));
        descInput.sendKeys("432dqw@163.com");
        WebElement submitButton2 = driver.findElement(By.xpath("//button[text()='提交']"));
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        submitButton2.click();   
        
        //看看新候补
        driver.get("http://localhost:8080/rand-shop/seller_login.jsp");
        WebElement aInput = driver.findElement(By.name("account"));
        aInput.sendKeys("123");

        WebElement pInput = driver.findElement(By.name("pwd"));
        pInput.sendKeys("123");
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement sButton = driver.findElement(By.className("submit"));
        sButton.click();
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement sellerButton6 = driver.findElement(By.xpath("//a[contains(text(), '订单管理')]"));
        sellerButton6.click();
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
		driver.close();

	}
}
