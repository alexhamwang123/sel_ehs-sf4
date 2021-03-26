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
@Test(priority = 2)
public class CategoryVisibility {

    public void CategoryVisibility() throws IOException,InterruptedException {


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

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Category Management"))));
        Thread.sleep(2000);
        Thread.sleep(2000);

        //Click the Active button to disable it
        WebElement ActiveBtn=driver.findElement(By.xpath("//input[@class='custom-control-input']"));
        js.executeScript("arguments[0].click();", ActiveBtn);
        Thread.sleep(2000);
        if(driver.getPageSource().contains(CreateAndEditCategory.CLassroom_Category_Name)){
            System.out.println("01 Disable Active button failed");
        }
        if(driver.getPageSource().contains(CreateAndEditCategory.Online_Category_Name)){
            System.out.println("02 Disable Active button failed");
        }
        //Click the Active button to enable it
        js.executeScript("arguments[0].click();", ActiveBtn);
        Thread.sleep(2000);
        if(driver.getPageSource().contains(CreateAndEditCategory.CLassroom_Category_Name)){
            System.out.println("01 Disable Active button works");
        }
        if(driver.getPageSource().contains(CreateAndEditCategory.Online_Category_Name)){
            System.out.println("02 Disable Active button works");
        }

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn);


        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText(CreateAndEditCategory.CLassroom_Sub_Category_Name);

        driver.findElement(By.partialLinkText("Classroom Course Management")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        WebElement CreateBtn1 = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn1);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("course-category"))));
        Thread.sleep(2000);

        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText(CreateAndEditCategory.ONline_Sub_Category_Name);
        driver.quit();



    }

}
