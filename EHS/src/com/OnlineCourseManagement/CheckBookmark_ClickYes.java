package com.OnlineCourseManagement;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@Test
@Test
public class CheckBookmark_ClickYes {

    public void CheckBookmark_ClickYes() throws IOException, InterruptedException {




        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
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

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("DanielOnline01");

        //	 driver.findElement(By.name("searchButton")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String mainWindow = driver.getWindowHandle();
        //msg_head857e61d0-2598-102a-b70c-b707fad2 -> it is a EHS-1000 - EHS Essentials
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();

        for(String winHandle : driver.getWindowHandles()){
            if(winHandle!=mainWindow)
                driver.switchTo().window(winHandle);
        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")));
        Thread.sleep(2000);

        JavascriptExecutor js= (JavascriptExecutor)driver;
        WebElement English= driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
        js.executeScript("arguments[0].click();",English);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(3000);

        if(driver.getPageSource().contains("Page 2 of 2")){
            System.out.println("The test is successful");
        }
        else{
            Assert.fail("The test failed");
        }

        driver.quit();



    }

}
