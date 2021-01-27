package com.ClassroomCourseManagement;

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

//@Test
@Test
public class CancelAndNotify {
    public void CancelAndNotify() throws IOException, InterruptedException {
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

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));


        js.executeScript("arguments[0].click()", courseAdmin);

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();


        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("DanielClassroom02");

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'DanielClassroom02')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td[contains(text(),'DanielClassroom02')]")).click();

        //Click Offer Schedule
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        //Click Add attendee
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        WebElement AddAttendee=driver.findElement(By.xpath("//button[@title='Attendee List']"));
        js.executeScript("arguments[0].click()", AddAttendee);
        //CLick Attendee Tab
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[1]/ul/li[2]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[1]/ul/li[2]/a")).click();

        //Click Add attendee btn
        Thread.sleep(1000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[1]/ul/li[2]/a")));
        WebElement AttendeeBtn=driver.findElement(By.xpath("//a[contains(text(),'Add Attendee')]"));
        js.executeScript("arguments[0].click()", AttendeeBtn);

        //Send in Badge No
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("criteriaBadge")));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys("554752");

        //Click Search
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();

        //Click Result
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[1]/div/div/div/form/div/div[2]/table/tbody/tr/td[1]")));
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div/form/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(1000);
        Thread.sleep(1000);
        //CLick Select User
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[2]/table/thead/tr/th[1]/input")).click();

        //Email User
        WebElement EmailBtn= driver.findElement(By.xpath("//a[@class='dropdown-item'][contains(text(),'Email Attendees')]"));
        js.executeScript("arguments[0].click()", EmailBtn);

        //subject input
        driver.findElement(By.xpath("//input[@id='input-subject']")).sendKeys("Test subjuet");

        //content input
        Thread.sleep(2000);

        driver.switchTo().frame("input-message_ifr");
        driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys("This is Test Content");
        driver.switchTo().defaultContent();

        //CLick Send Email
        driver.findElement(By.xpath("//button[contains(text(),'Send Email')]")).click();

        //Click Remove User Btn
        Thread.sleep(2000);
        WebElement Remove= driver.findElement(By.xpath("//div[@class='btn-tools']//button[@id='remove']"));
        js.executeScript("arguments[0].click()", Remove);
        //Click OK Btn
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();



        Thread.sleep(3500);
        if(driver.getPageSource().contains("System error")){

            Assert.fail("the test failed");
        }

        driver.quit();



    }
}