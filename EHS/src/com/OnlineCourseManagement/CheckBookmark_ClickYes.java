package com.OnlineCourseManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test(priority=33)
public class CheckBookmark_ClickYes {

    public void CheckBookmark_ClickYes() throws IOException, InterruptedException {

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


        driver.findElement(By.xpath("//*[@id='navPrimary']/li[2]/a")).click();

        driver.findElement(By.id("srch_fld")).sendKeys("EHS-1000");

        driver.findElement(By.name("searchButton")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String mainWindow = driver.getWindowHandle();
        //msg_head857e61d0-2598-102a-b70c-b707fad2 -> it is a EHS-1000 - EHS Essentials
        driver.findElement(By.xpath("//*[@id='msg_head857e61d0-2598-102a-b70c-b707fad2']/table/tbody/tr/td[5]/img")).click();

        for(String winHandle : driver.getWindowHandles()){
            if(winHandle!=mainWindow)
                driver.switchTo().window(winHandle);
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        driver.findElement(By.partialLinkText("English")).click();
        driver.findElement(By.partialLinkText("English")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.className("clickYes")).click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.quit();


    }

}
