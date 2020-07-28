package com.CategoryManagement;

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
@Test(priority = 1)
public class CreateAndEditCategory {

    public static String CLassroom_Category_Name= "YClassroomTest001";
    public static String CLassroom_Sub_Category_Name= "YClassroomSubTest001";
    public static String Online_Category_Name= "ZONline001";
    public static String ONline_Sub_Category_Name= "ZONlineSub001";

    public void CreateAndEditCategory() throws IOException,InterruptedException{
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();



        WebDriverWait Wait= new WebDriverWait(driver,30);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
            actions.build().perform(); }
        catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));


        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);

        //Create Classroom Course Category
        //Click the Add Category Button
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(2000);
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();
        Thread.sleep(1500);

        //fill the Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys("TestName");

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);

        //CLick Edit Btn of New Category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div/div/div/div/button[2]")).click();

        //Change the Category Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).clear();
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys(CLassroom_Category_Name);

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(1000);
        if(driver.getPageSource().contains(CLassroom_Category_Name)){
            System.out.println("Content Change works");
        }

        //Click add sub category
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div/div/div/div/button[1]")).click();
        //Add the Sub Category Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys("SubTestName");
        //Click Save
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        Thread.sleep(2000);

        //Click to Expand and show sub category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div")).click();

        //Click to Edit Sub Category
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[5]/div[2]/ul/li/div/div/div/div/button[2]")).click();

        //Change the sub Category Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).clear();
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys(CLassroom_Sub_Category_Name);

        //Click Save
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        Thread.sleep(1000);


        if(driver.getPageSource().contains(CLassroom_Sub_Category_Name)){
            System.out.println(" Sub Category Content Change works");
        }


        System.out.println("CheckPoint 1");

        //Create ONline Course Category
        //Click the Add Category Button
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(2000);
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();
        Thread.sleep(1500);

        //fill the Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys("zTestName");

        new Select(driver.findElement(By.id("courseType"))).selectByVisibleText("Online Course");

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);

        //CLick Edit Btn of New Category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[6]/div/div/div/div/button[2]")).click();

        //Change the Category Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).clear();
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys(Online_Category_Name);

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(1000);
        if(driver.getPageSource().contains(Online_Category_Name)){
            System.out.println("Online Content Change works");
        }

        //Click add sub category
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[6]/div/div/div/div/button[1]")).click();
        //Add the Sub Category Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys("SubTestName");

        new Select(driver.findElement(By.id("courseType"))).selectByVisibleText("Online Course");

        //Click Save
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        Thread.sleep(2000);

        //Click to Expand and show sub category
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[6]/div[1]/div"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[6]/div[1]/div")).click();

        //Click to Edit Sub Category
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/div/ul/li/div[2]/ul/li[6]/div[2]/ul/li/div/div/div/div/button[2]")).click();

        //Change the sub Category Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='categName']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='categName']")).clear();
        driver.findElement(By.xpath("//input[@id='categName']")).sendKeys(ONline_Sub_Category_Name);

        //Click Save
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        Thread.sleep(1000);


        if(driver.getPageSource().contains(ONline_Sub_Category_Name)){
            System.out.println(" Online Sub Category Content Change works");
        }

        driver.quit();
    }



}
