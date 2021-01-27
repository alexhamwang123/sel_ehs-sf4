package com.Miscellaneous;

import org.apache.commons.text.RandomStringGenerator;
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

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
@Test
public class ModulesCheckUp {
    public void moduleCheck() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait= new WebDriverWait(driver,30);
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

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Initialize JS
        JavascriptExecutor js = (JavascriptExecutor)driver;

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        //Manager Portal Checking
        try{
            driver.findElement(By.xpath("//a[contains(text(),'Manager')]"));
        }catch (Exception e){
            System.out.println("Manager's Portal Checking Failed");
            Assert.fail("Manager's Portal Checking Failed ");
        }

        //Lab Portal Checking
        try{
            driver.findElement(By.xpath("//a[contains(text(),'Lab')]"));
        }catch (Exception e){
            System.out.println("Lab Portal Checking Failed");
            Assert.fail("Lab Portal Checking Failed ");

        }



        //Support/Help Checking
        driver.findElement(By.xpath("/html/body/div[1]/div/header/div[1]/div/ul/li[2]/a")).click();
        Thread.sleep(1000);
        try{
            driver.findElement(By.xpath("//a[contains(text(),'Help')]"));
        }catch (Exception e){
            System.out.println("Support/Help Checking Failed");
            Assert.fail("Support/Help Checking Failed");
        }

        //Click Course Admin
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));


        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));


        //Classroom Checking
        try{
            driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]"));
        }catch (Exception e){
            System.out.println("Classroom Checking Failed");
            Assert.fail("Classroom Checking Failed");

        }

        //Online Checking
        try{
            driver.findElement(By.xpath("//a[contains(text(),'Online Course Admin')]"));
        }catch (Exception e){
            System.out.println("Online Course Failed");
            Assert.fail("Online Course Failed");
        }

        //Checklist Checking
        try{
            driver.findElement(By.xpath("//a[contains(text(),'Checklist Admin')]"));
        }catch (Exception e){
            System.out.println("Checklist Checking Failed");
            Assert.fail("Checklist Checking Failed");

        }

        //External Course Checking
        try{
            driver.findElement(By.xpath("//a[contains(text(),'External Course Management')]"));
        }catch (Exception e){
            System.out.println("External Course Management Checking Failed");
            Assert.fail("External Course Management Checking Failed");

        }

        driver.quit();

    }
}
