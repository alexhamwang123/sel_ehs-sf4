package com.Miscellaneous;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@Test
@Test(priority=73)
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
        driver.manage().window().maximize();
        String username = prop.getProperty("testnormuser");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/div/div[2]/div/div/a[2]")).click();
        Thread.sleep(250);
        driver.findElement(By.xpath("//*[@id=\"login-box\"]/div[3]/center/a[3]")).click();
        Thread.sleep(750);
        driver.findElement(By.id("badgeNo")).sendKeys(username);
        driver.findElement(By.id("email")).sendKeys(username + "@trismax.com");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='submit'][value='Email password']")).click();
        Thread.sleep(3500);
        driver.quit();

    }

}
