package com.Reports;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class EHS_SPGOrganizationReport {
    public void EHS_SPGOrganizationReport() throws IOException, InterruptedException{

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
try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
actions.build().perform(); }
catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        //Clicking on EHS Reports under Reports
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        driver.findElement(By.xpath("/html/body/div[1]/div/header/div[2]/nav/div/ul/li[6]/a")).click();
        Thread.sleep(1000);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",ele);
        Thread.sleep(1500);
        //Click on Organization Report
        driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[6]")).click();
        Thread.sleep(1500);

        //Click RC Select Btn
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[2]/div/div/button")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys("RLBgSzMbTE");
        Thread.sleep(1500);

        if(driver.getPageSource().contains("RLBgSzMbTE")){
            System.out.println("The SPG Filter as All works");
        }
        else
        {
            Assert.fail("The SPG Filter as All Failed");
        }

        Thread.sleep(1500);

        new Select(driver.findElement(By.xpath("//div[@class='input-group col col-sm-2 input-group-sm']//select[@class='custom-select form-control']"))).selectByVisibleText("N");
        driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys("RLBgSzMbTE");
        if(!driver.getPageSource().contains("RLBgSzMbTE")){
            System.out.println("The SPG Filter as N works");
        }
        else
        {
            Assert.fail("The SPG Filter as N Failed");
        }

        driver.quit();
    }

    }
