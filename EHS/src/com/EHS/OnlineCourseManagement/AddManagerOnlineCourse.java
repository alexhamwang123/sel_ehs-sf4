package com.EHS.OnlineCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@Test
public class AddManagerOnlineCourse {
    public void AddManagerOnlineCourse() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");

        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(4500);
        driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='A']")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("badgeNo")).sendKeys("arjun");
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(1500);
        //driver.findElement(By.cssSelector("input[href*='selectCourseManager']")).click();
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='B']")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("badgeNo")).sendKeys("X00001617");
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(2000);
        WebElement c = driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='C']"));
        js.executeScript("arguments[0].click();", c);
        Thread.sleep(1500);
        driver.findElement(By.name("badgeNo")).sendKeys("X00001618");
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(3500);
        driver.quit();



    }

}
