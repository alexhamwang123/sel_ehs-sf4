package com.OnlineCourseManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;


//@Test
@Test
public class NormalOnlineAndAddOnlineCourseManager {
    public void AddManagerOnlineCourse() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();;

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


        JavascriptExecutor js = (JavascriptExecutor)driver;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

        js.executeScript("arguments[0].click()", courseAdmin);

        String testCourseMgr ="X00002380";

        String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn);


        wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("EHS - Others");
        new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

        //input Course Number
        driver.findElement(By.id("course-num")).sendKeys(courseId);
        //input Course Title
        driver.findElement(By.id("input-courseTitle")).clear();
        driver.findElement(By.id("input-courseTitle")).sendKeys("This is title");
        //input Course Description

        driver.findElement(By.id("input-description")).sendKeys("This is description");

        //Select Course Manager
        //CLick Select Btn
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("criteriaBadge"))));
        driver.findElement(By.id("criteriaBadge")).sendKeys(testCourseMgr);

        //Click Search Btn
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/footer/div/button")).click();

        //Click Result
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]"))));

        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Management"))));
        Thread.sleep(2000);
        WebElement Logout0=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()", Logout0);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img")).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("username"))));
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("X00002380");
        driver.findElement(By.id("password")).sendKeys("X00002380");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);

        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js1.executeScript("arguments[0].click()", courseAdmin1);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[1]/div[1]/div/input")).sendKeys(courseId);
        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);

        //CLick the result
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();


        driver.quit();
    }

}
