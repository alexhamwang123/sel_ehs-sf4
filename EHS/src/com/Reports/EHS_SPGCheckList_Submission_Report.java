package com.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Test
public class EHS_SPGCheckList_Submission_Report {
    public void EHS_SPGCheckList_Submission_Report() throws IOException, InterruptedException{
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        //Clicking on EHS Reports under Reports
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",courseAdmin);

        Thread.sleep(1500);
        //Click on Checklist Submission Report
        driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[3]")).click();
        Thread.sleep(1500);

        //Click on Select button that appears in front of Checklist
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/div/button")).click();
        Thread.sleep(1500);

        new Select(driver.findElement(By.xpath("//div[4]//select[1]"))).selectByVisibleText("Y");

        driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys("0sDvK3sEly");
        Thread.sleep(1500);

        if(driver.getPageSource().contains("0sDvK3sEly")){
            System.out.println("The SPG Filter as Y works");
        }
        else
        {
            Assert.fail("The SPG Filter as Y Failed");
        }

        Thread.sleep(1500);

        new Select(driver.findElement(By.xpath("//div[4]//select[1]"))).selectByVisibleText("N");
        driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys("0sDvK3sEly");
        Thread.sleep(1500);
        if(!driver.getPageSource().contains("0sDvK3sEly")){
            System.out.println("The SPG Filter as N works");
        }
        else
        {
            Assert.fail("The SPG Filter as N Failed");
        }

        driver.quit();
    }

    }
