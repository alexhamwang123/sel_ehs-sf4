package com.ClassroomCourseManagement;


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
@Test(priority=15)
public class AddClassroomManger {
    public void AddClassroomManger() throws IOException, InterruptedException {
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

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        Thread.sleep(3500);
        driver.findElement(By.id("selectBtnCreMaA")).click();
        Thread.sleep(1500);

        driver.findElement(By.name("badgeNo")).sendKeys(username);

        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"teammanager_result\"]/div/table/tbody/tr/td[1]/a")).click();
        Thread.sleep(3500);

        driver.quit();



    }

}
