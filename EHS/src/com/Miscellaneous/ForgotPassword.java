package com.Miscellaneous;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@Test
@Test
public class ForgotPassword {
    public void ForgotPassword1() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
actions.build().perform(); }
catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        driver.manage().window().maximize();
        Thread.sleep(1000);

        //Click Forget Password
        driver.findElement(By.partialLinkText("Forgot password?")).click();
        Thread.sleep(2250);
        driver.findElement(By.id("email")).sendKeys("X00003037@trismax.com");
        Thread.sleep(750);
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/div/form/button")).click();
        Thread.sleep(2250);
        driver.quit();

    }

}
