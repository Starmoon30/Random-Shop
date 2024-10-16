package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Updatepwd_fail {
	@Test
    public void testMyMethod() {
		System.setProperty("webdriver.gecko.driver", "D:/Drivers/web/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        //登陆卖家
        driver.get("http://localhost:8080/rand-shop/home.jsp");
        String expectedUrl = "http://localhost:8080/rand-shop/seller_login.jsp";
        WebElement sellerButton = driver.findElement(By.xpath("//a[contains(text(), '卖家平台')]"));

        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sellerButton.click();

        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        assertTrue("未能成功跳转", currentUrl.equals(expectedUrl));
        
        WebElement accountInput = driver.findElement(By.name("account"));
        accountInput.sendKeys("123");

        WebElement passwordInput = driver.findElement(By.name("pwd"));
        passwordInput.sendKeys("123");

        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement submitButton = driver.findElement(By.className("submit"));
        submitButton.click();
        
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //修改密码
        WebElement Button = driver.findElement(By.xpath("//a[contains(text(), '账号管理')]"));
		Button.click();
		WebElement oldPasswordInput = driver.findElement(By.name("old_pwd"));
        WebElement newPasswordInput = driver.findElement(By.name("new_pwd"));
        oldPasswordInput.sendKeys("1234");
        newPasswordInput.sendKeys("12345");
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement Button1 = driver.findElement(By.className("submit"));
		Button1.click();
		try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
        Alert alert = driver.switchTo().alert();
        alert.accept();
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        driver.close();

    }
}
