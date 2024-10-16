package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class inputBuyer {
	@Test
    public void testMyMethod() {
		System.setProperty("webdriver.gecko.driver", "D:/Drivers/web/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        //买家下单
        driver.get("http://localhost:8080/rand-shop/home.jsp");
        WebElement sellerButton = driver.findElement(By.xpath("//a[contains(text(), '买家平台')]"));

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
        
        WebElement sellerButton1 = driver.findElement(By.xpath("//a[contains(text(), '立即购买')]"));
        sellerButton1.click();
        
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement descInput = driver.findElement(By.id("Desc"));
        descInput.sendKeys("xxx need 114514543 23423@qq.com");
        WebElement submitButton2 = driver.findElement(By.xpath("//button[text()='提交']"));
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        submitButton2.click();   
        
        //卖家后台查看订单
        driver.get("http://localhost:8080/rand-shop/seller_login.jsp");
        WebElement accountInput = driver.findElement(By.name("account"));
        accountInput.sendKeys("123");

        WebElement passwordInput = driver.findElement(By.name("pwd"));
        passwordInput.sendKeys("123");

        WebElement submitButton = driver.findElement(By.className("submit"));
        submitButton.click();
        
        WebElement sellerButton3 = driver.findElement(By.xpath("//a[contains(text(), '订单管理')]"));
        
        sellerButton3.click();
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        driver.close();

    }
}
