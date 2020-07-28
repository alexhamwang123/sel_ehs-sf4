package com.CategoryManagement;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
@Test(priority =3)
public class NewCategoryCourseCompletionReport {
    public void NewCategoryCourseCompletionReport() throws IOException,InterruptedException {


        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //driver.manage().window().maximize();
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
        driver.findElement(By.xpath("//li[6]//a[1]")).click();
        Thread.sleep(1500);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",ele);
        Thread.sleep(1500);
        Thread.sleep(1500);

        //Click Course Type Btn
        WebElement CourseType= driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[11]/label[1]/input"));
        js.executeScript("arguments[0].click();",CourseType);

        Thread.sleep(1500);

        //Select Course Category
        new Select(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[11]/div[2]/select"))).selectByVisibleText(CreateAndEditCategory.CLassroom_Sub_Category_Name);

        Thread.sleep(2000);


        //Select Course Category
        new Select(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[3]/div/div[11]/div[2]/select"))).selectByVisibleText(CreateAndEditCategory.ONline_Sub_Category_Name);

        Thread.sleep(2000);
        driver.quit();




    }
    }
