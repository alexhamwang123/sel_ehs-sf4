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
public class ClassroomRegionVariantVisibility {
    public void ClassroomRegionVariantVisibility() throws InterruptedException, IOException{
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
        String MarsUser = prop.getProperty("MarsUser");
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
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
Thread.sleep(1000);
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

        WebElement Save_Btn=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js.executeScript("arguments[0].click()", Save_Btn);
        Thread.sleep(1000);
        //Click OK Btn
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(3000);

        //Create a page variant with Mars Region
        //click add page variant btn
        driver.findElement(By.xpath("//button[@class='btn btn-sm btn-outline-secondary rounded']")).click();
        Thread.sleep(2000);
        WebElement Frame2= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[2]/div[1]/iframe"));
        driver.switchTo().frame(Frame2);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body[contains(@class,'mce-content-body')]")).sendKeys("Mars Page Variant only");
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        driver.findElement(By.id("page-title-input")).sendKeys("MarsVariantTitle");
        new Select(driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]" +
                "/div[1]/div/div[2]/div/select"))).selectByVisibleText("Mars");
        //Click Save btn
        Thread.sleep(1000);

        WebElement Save_Btn1=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js.executeScript("arguments[0].click()", Save_Btn1);
        Thread.sleep(1000);
        //Click OK Btn
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);
        //Click Question Tab
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/div[1]/ul/li[2]/a")).click();
        Thread.sleep(1000);
        //Click Add Question Btn
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/div[1]/ul/div/button")).click();

        driver.switchTo().frame("question-content_ifr");
        Thread.sleep(2000);
        driver.findElement(By.id("tinymce")).sendKeys("This is a question content");
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        //input answers
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[1]/input")).sendKeys("Correct Answer");
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[3]/div[1]/input")).sendKeys("Wrong Answer");
        //CLick the first answer as correct
        WebElement CorrectBtn=driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/div/div[3]/div/div/div[2]/div[2]/div/input"));
        js.executeScript("arguments[0].click()", CorrectBtn);

        //Click OK btn
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        //Click Save Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        js.executeScript("arguments[0].click()", Save_Btn);

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



        //the above is the variant with default region which is all region, now create a varaiant with Mars Region
        //CLick add variant
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn btn-sm btn-outline-primary']")).click();

        //Select Region Mars
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("regionSelect")));
        new Select(driver.findElement(By.id("regionSelect"))).selectByVisibleText("Mars");
        //Select Language
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("rcSelect")));
        new Select(driver.findElement(By.id("rcSelect"))).selectByVisibleText("British English");

        //CLick Select Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("rcSelect")));
        driver.findElement(By.xpath("//html//body//div//div//div//div//footer//button[contains(text(),'Select')]")).click();

        //input course infor
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("input-type")));
        Thread.sleep(1000);
        driver.findElement(By.id("input-badgeNo")).sendKeys("Mars-Variant");
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[3]/div/textarea")).sendKeys("Mars-Variant Description");


        //input course type
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
        WebElement Frame1= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div[1]/div[2]/div[1]/iframe"));
        driver.switchTo().frame(Frame1);
        Thread.sleep(2000);
        driver.findElement(By.id("tinymce")).sendKeys("For Mars only For Mars only");

        driver.switchTo().defaultContent();

        driver.findElement(By.id("page-title-input")).sendKeys("MarsTitleonly");
        Thread.sleep(1000);
        //Click Save btn
        Thread.sleep(1000);
        WebElement Save_Btn2=driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        js.executeScript("arguments[0].click()", Save_Btn2);
        Thread.sleep(1000);
        //Click OK Btn
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        Thread.sleep(1000);


        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
        Thread.sleep(1000);
        driver.close();
        Thread.sleep(1000);

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }



        //Click Online Variants
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        Thread.sleep(2000);
        //Click two visibility
        WebElement VisibilityBtn=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
        js.executeScript("arguments[0].click()", VisibilityBtn);
        Thread.sleep(2000);
        WebElement VisibilityBtn1=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/div[1]/input"));
        js.executeScript("arguments[0].click()", VisibilityBtn1);

        //Click viewable btn
        WebElement ViewableBtn=driver.findElement(By.xpath("//a[2]//div[1]//input[1]"));
        js.executeScript("arguments[0].click()", ViewableBtn);

        //Wait until the success message shows up
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/header")));
        if(!ViewableBtn.isEnabled()){
            Assert.fail("Creation Failed");
        }


        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(3000);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
        Thread.sleep(3000);

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);
        if(driver.getPageSource().contains("British English")){
            Assert.fail("SCV region can see the Mars Region Variant");
        }

        //Click SCV Variant Default - English
        driver.findElement(By.xpath("//button[contains(text(),'Default - English')]")).click();

        Wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Grade Quiz')]"))));
        Thread.sleep(1500);
        if(driver.getPageSource().contains("Mars Page Variant only")){
            Assert.fail("SCV region can  see the Mars Page Variant");
        }
        driver.close();
        Thread.sleep(1000);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);

        //Log in Mars Users

        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/div/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("username")).sendKeys(MarsUser);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(3000);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
        Thread.sleep(3000);

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);

        if(!driver.getPageSource().contains("British English")||!driver.getPageSource().contains("Default - English")){
            Assert.fail("Mars region can not see the Mars Region Variant");
        }
        //Click SCV Variant Default - English
        driver.findElement(By.xpath("//button[contains(text(),'Default - English')]")).click();

        Wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Grade Quiz')]"))));
        Thread.sleep(1500);
        if(!driver.getPageSource().contains("Mars Page Variant only")){
            Assert.fail("Mars region can not see the Mars Page Variant");
        }
        Thread.sleep(1000);
        driver.quit();


    }
}
