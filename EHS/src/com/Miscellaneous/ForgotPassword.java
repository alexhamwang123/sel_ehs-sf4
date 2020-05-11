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
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/form/fieldset/div[2]/div/a")).click();
        Thread.sleep(2250);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div[1]/input")).sendKeys("X00003037");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div[2]/input")).sendKeys("X00003037@trismax.com");
        Thread.sleep(750);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/button")).click();
        Thread.sleep(2250);
        driver.quit();

    }

}
