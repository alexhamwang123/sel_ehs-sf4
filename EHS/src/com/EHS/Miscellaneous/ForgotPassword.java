package com.EHS.Miscellaneous;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Test
public class ForgotPassword {
    public void ForgotPassword() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");

        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String username = prop.getProperty("testnormuser");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"login-box\"]/div[3]/center/a[3]")).click();
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
