package com.OnlineCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
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

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
@Test
public class OnlineMinTime {
    public void OnlineMinTime()throws IOException, InterruptedException{

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

        driver.manage().window().maximize();
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);



        JavascriptExecutor js = (JavascriptExecutor)driver;
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

        js.executeScript("arguments[0].click()", courseAdmin);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn);


        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("EHS - Others");
        new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

        String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

        System.out.println(courseId);
        //input Course Number
        driver.findElement(By.id("course-num")).sendKeys(courseId);
        //input Course Title
        driver.findElement(By.id("input-courseTitle")).clear();
        driver.findElement(By.id("input-courseTitle")).sendKeys("This is title");
        //input Course Description

        driver.findElement(By.id("input-description")).sendKeys("This is description");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Online Details
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-num")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();

        //input training time
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
        Thread.sleep(1000);
        driver.findElement(By.id("course-trainingTime")).sendKeys("1");
        //input credit
        driver.findElement(By.id("course-credit")).sendKeys("1");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Online Variants
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        //Click Edit Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn-sm btn btn-outline-primary border-0']")).click();

        //input course type
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("input-type")));
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("input-type"))).selectByVisibleText("Course Builder");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Edit Course Content Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Edit Course Content')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Edit Course Content')]")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);
        //input content
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(2000);
        WebElement Frame= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[2]/div[1]/iframe"));
        driver.switchTo().frame(Frame);
        Thread.sleep(2000);
        driver.findElement(By.id("tinymce")).sendKeys("This is a test only");

        driver.switchTo().defaultContent();

        driver.findElement(By.id("page-title-input")).sendKeys("TitileforTest");
        Thread.sleep(1000);
        //Click Save btn
        Thread.sleep(1000);


        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        WebElement Save_Btn=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js1.executeScript("arguments[0].click()", Save_Btn);
        Thread.sleep(1000);
        //Click OK Btn
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);

        //upload voiceover file
        driver.findElement(By.id("course-voice-over")).sendKeys("/Users/trismax/IdeaProjects/sel_ehs-sf4/EHS/20 seconds of River Flows in You - Yiruma (Piano Cover).mp3");
        Thread.sleep(1000);
        //Select MIN Time 15 sec
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(10000);
        new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/select[1]"))).selectByVisibleText("15 seconds");
        //CLick Save&OK
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(2000);
        WebElement Save_Btn1=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js1.executeScript("arguments[0].click()", Save_Btn1);
        //Click OK Btn
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);
        Thread.sleep(1000);
        //CLick Add Page
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        Thread.sleep(1000);
        WebElement AddPage=   driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        js1.executeScript("arguments[0].click()", AddPage);

        //input content
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(2000);
        WebElement Frame1= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[2]/div[1]/iframe"));
        driver.switchTo().frame(Frame1);
        Thread.sleep(2000);
        driver.findElement(By.id("tinymce")).sendKeys("Page 2 This is a test only");

        driver.switchTo().defaultContent();

        driver.findElement(By.id("page-title-input")).sendKeys("Page2Titile");
        Thread.sleep(1000);


        //Click Save Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        WebElement Save_Btn2=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js1.executeScript("arguments[0].click()", Save_Btn2);

        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);

        //upload voiceover file
        driver.findElement(By.id("course-voice-over")).sendKeys("/Users/trismax/IdeaProjects/sel_ehs-sf4/EHS/20 seconds of River Flows in You - Yiruma (Piano Cover).mp3");
        Thread.sleep(1000);
        //Select MIN Time 30 sec
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(10000);
        new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/select[1]"))).selectByVisibleText("30 seconds");

        //Click Save Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(2000);
        WebElement Save_Btn3=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js1.executeScript("arguments[0].click()", Save_Btn3);

        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);
        Thread.sleep(1000);
        //CLick Add Page
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        Thread.sleep(1000);
        WebElement AddPage1=   driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/button[1]"));
        js1.executeScript("arguments[0].click()", AddPage1);
        Thread.sleep(2000);
        //input content
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(2000);
        WebElement Frame2= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[2]/div[1]/iframe"));
        driver.switchTo().frame(Frame2);
        Thread.sleep(2000);
        driver.findElement(By.id("tinymce")).sendKeys("Page 3 This is a test only");

        driver.switchTo().defaultContent();

        driver.findElement(By.id("page-title-input")).sendKeys("Page3Titile");
        Thread.sleep(1000);


        //Click Save Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        WebElement Save_Btn4=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js1.executeScript("arguments[0].click()", Save_Btn4);

        //Click OK Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);
        //Close the window
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        driver.close();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //Click Online Variants
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Visibility btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);

        WebElement VisibilityBtn=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
        js.executeScript("arguments[0].click()", VisibilityBtn);

        //Click viewable btn
        WebElement ViewableBtn=driver.findElement(By.xpath("//a[2]//div[1]//input[1]"));
        js.executeScript("arguments[0].click()", ViewableBtn);

        //Wait until the success message shows up
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/header")));
        if(!ViewableBtn.isEnabled()){
            Assert.fail("Creation Failed");
        }

        //Find and Complete the course
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        Thread.sleep(1000);

        //click the result
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //Click English btn
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]")));
        WebElement English= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div[2]/div/button[1]"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click()", English);

        Thread.sleep(2000);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //Click Play
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div[2]/div/div")).click();

        Thread.sleep(5000);

        // Min< VoiceOverDuration
        try { driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/div/div[3]/button[3]")).click();
              Assert.fail("Next Btn should not be clickable,when the actual time is less than Min Time");
            }
        catch (ElementClickInterceptedException e) { System.out.println("successful,Min Time is 15 sec, Less than 15 sec is the time not clickable");
            }

        Thread.sleep(23000);
        try {
            WebElement NextBtn=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/div/div[3]/button[3]"));
            JavascriptExecutor js3 = (JavascriptExecutor)driver;
            js3.executeScript("arguments[0].click()", NextBtn);
            System.out.println("Min Time is 15 sec, More than 15 sec is the time clickable");
        }
        catch (ElementClickInterceptedException e) {
            Assert.fail("Next Btn should  be clickable,when the actual time is more than Min Time");
        }
        Thread.sleep(2000);
        //Click Play
       //  driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div[2]/div/div")).click();


        //Min> VoiceOverDuration
        Thread.sleep(2000);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(25000);
        try { driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/div/div[3]/button[3]")).click();
            Assert.fail("Next Btn should not be clickable,when the Min time is not done");
        }
        catch (ElementClickInterceptedException e) { System.out.println("users can not move forward once min time is not done, even though the voice over has finished");
        }

        Thread.sleep(8000);
        try {
            WebElement NextBtn=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/div/div[3]/button[3]"));
            JavascriptExecutor js4 = (JavascriptExecutor)driver;
            js4.executeScript("arguments[0].click()", NextBtn);
            System.out.println("users can only move forward once min time  done, even though the voice over has finished");
        }
        catch (ElementClickInterceptedException e) {
            Assert.fail("users can not move forward once min time is done");
        }


        Thread.sleep(1500);
        driver.quit();

    }
}
