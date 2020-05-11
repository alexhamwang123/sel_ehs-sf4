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

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class OnlineRegionVisibility {
    public void OnlineRegionVisibility() throws InterruptedException, IOException, AWTException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
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
        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

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

        String courseId = generator.generate(10);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("course-num")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[2]/a")).click();

        //input training time
        wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
        Thread.sleep(1000);
        driver.findElement(By.id("course-trainingTime")).sendKeys("1");
        //input credit
        driver.findElement(By.id("course-credit")).sendKeys("1");

        //Select Region
        new Select(driver.findElement(By.id("course-region"))).selectByVisibleText("Taipei");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Online Variants
        wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")).click();
        //Click Edit Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div/div/div[3]/button[2]")).click();

        //input course type
        wait.until(ExpectedConditions.elementToBeClickable(By.id("input-type")));
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("input-type"))).selectByVisibleText("Course Creator Content");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Edit Course Content Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[3]/div/button")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div[3]/div/button")).click();

        for(String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);
        //input content
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/button[3]")));
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

        WebElement Save_Btn=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/button[3]"));
        js.executeScript("arguments[0].click()", Save_Btn);
        Thread.sleep(1000);
        //Click OK Btn
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/button[2]")).click();
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
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/footer/button[2]")).click();

        //Click Save Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/button[3]")));
        Thread.sleep(1000);
        js.executeScript("arguments[0].click()", Save_Btn);

        //Click OK Btn
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/button[2]")).click();
        Thread.sleep(1000);

        //Close the window
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/button[3]")));
        Thread.sleep(1000);
        driver.close();

        for(String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
        }

        //Click Online Variants
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Visibility btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/ul/li[3]/a")));
        Thread.sleep(1000);

        WebElement VisibilityBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/div/div/div[1]/input"));
        js.executeScript("arguments[0].click()", VisibilityBtn);

        //Click viewable btn
        WebElement ViewableBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/a[2]/div/input"));
        js.executeScript("arguments[0].click()", ViewableBtn);

        //Wait until the success message shows up
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/header")));

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        if(driver.getPageSource().contains(courseId)){
            Assert.fail("The User not in the Region can see the Course");
        }


        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        js.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




// Click on Create User
        WebElement Create_User=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/ul/li[3]/div/a[1]"));
        js.executeScript("arguments[0].click();", Create_User);


// Enter the First Name of the user that you wish to create
        String userid = driver.findElement(By.id("input-badgeNo")).getAttribute("value");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-firstName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-firstName")).sendKeys(userid);

// Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("input-lastName")).sendKeys(userid);

// Click on 'Select' for Site
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"__BVID__28\"]/div/div[1]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"__BVID__28\"]/div/div[1]/button")).click();
        Thread.sleep(1000);
// Enter the search value as "SCV"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("SAC");


// Click on 'SCV' from the search results
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


// Enter the email of the user that you wish to create
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-email"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-email")).sendKeys(userid + "@trismax.com");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JavascriptExecutor js1 = ((JavascriptExecutor) driver);
        js1.executeScript("window.scrollBy(0,850)", "");

// Clicking on USA Normal User
//driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

// Click on 'Save' button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/button")).click();


//Click Role Page
        Thread.sleep(3000);
        WebElement RolePage= driver.findElement(By.xpath("//*[@id=\"__BVID__46___BV_tab_button__\"]"));
        js.executeScript("arguments[0].click();", RolePage);
        Thread.sleep(3000);
//Click the DanielAdmin Btn
        WebElement RolePicking= driver.findElement(By.xpath("//*[@id=\"__BVID__86\"]"));
        js.executeScript("arguments[0].click();", RolePicking);
        Thread.sleep(2000);

        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("username")).sendKeys(userid);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
        driver.findElement(By.id("welcomeShowRS")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("question[4586]")).click();
        driver.findElement(By.name("question[1361]")).click();
        driver.findElement(By.name("question[4562]")).click();
        driver.findElement(By.name("question[4225]")).click();
        driver.findElement(By.name("question[4193]")).click();
        driver.findElement(By.name("question[145]")).click();
        driver.findElement(By.name("question[1164]")).click();
        driver.findElement(By.name("question[4676]")).click();
        driver.findElement(By.name("question[7466]")).click();
        driver.findElement(By.name("question[7467]")).click();


            driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("annContinue"))));
        Thread.sleep(1000);
        driver.findElement(By.id("annContinue")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Home')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        if(!driver.getPageSource().contains(courseId)){
            Assert.fail("The user within the Region can't see the course");
        }
    }

    }
