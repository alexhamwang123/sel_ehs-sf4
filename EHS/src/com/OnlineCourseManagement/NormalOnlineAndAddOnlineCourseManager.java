package com.OnlineCourseManagement;


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


//@Test
@Test(priority=3)
public class NormalOnlineAndAddOnlineCourseManager {
    public void AddManagerOnlineCourse() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        //driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        String testCourseMgr = prop.getProperty("testCourseMgr");
        String testCourseMgr1 = prop.getProperty("testCourseMgr1");
        String testCourseMgr2 = prop.getProperty("testCourseMgr2");

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click(); //CREATE online course
        Thread.sleep(4500);
        driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='A']")).click();
        driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='A']")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("badgeNo")).sendKeys(testCourseMgr);
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(1500);
        //driver.findElement(By.cssSelector("input[href*='selectCourseManager']")).click();
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='B']")).click();
        driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='B']")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("badgeNo")).sendKeys(testCourseMgr1);
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(5000);
        WebElement c = driver.findElement(By.cssSelector("input[value='Add Course Manager'][onclick*='C']"));
        js.executeScript("arguments[0].click();", c);
        Thread.sleep(3000);
        driver.findElement(By.name("badgeNo")).sendKeys(testCourseMgr2);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(3500);
        driver.quit();



    }

}
