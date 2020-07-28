package com.Miscellaneous;

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
@Test
public class SPGAccessinAllCourses {

    public void SPGAccessinAllCourses() throws IOException, InterruptedException{


        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
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

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);

        //Check the SPG Manager of the 3 types Courses
        JavascriptExecutor js = (JavascriptExecutor)driver;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

        js.executeScript("arguments[0].click();", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Admin")).click();
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("0sDvK3sEly");
        //Click SPG Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='search[is_spg]']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='search[is_spg]']")).click();

        //Click Go btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("0sDvK3sEly"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("0sDvK3sEly")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        if(driver.getPageSource().contains("tom tom")){
            System.out.println("The SPG Manager is the current System User");
        }
        else {
            Assert.fail("The SPG Manager is not the current System User");
        }


        driver.findElement(By.partialLinkText("Home")).click();
        Thread.sleep(1000);

        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

        js1.executeScript("arguments[0].click();", courseAdmin1);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("DanielSPGCourse01");
        //Click SPG Btn
        Thread.sleep(1000);
        WebElement SPG=driver.findElement(By.xpath("//input[@class='custom-control-input']"));
        js1.executeScript("arguments[0].click();", SPG);


        //CLick result
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a"))));
        Thread.sleep(1000);
        if(driver.getPageSource().contains("tom tom")){
            System.out.println("The SPG Manager is the current System User");
        }
        else {
            Assert.fail("The SPG Manager is not the current System User");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div/input")).sendKeys("DanielSPGTestCourse03");

        //Click result
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(2000);
        Thread.sleep(1000);
        if(driver.getPageSource().contains("tom tom")){
            System.out.println("The SPG Manager is the current System User");
        }
        else {
            Assert.fail("The SPG Manager is not the current System User");
        }


        //Courses Page check
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // DanielSPGCourse01 is Online SPG course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("DanielSPGCourse01");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("DanielSPGCourse01")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // 0sDvK3sEly is Checklist SPG Course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("0sDvK3sEly");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("DanielSPGCourse01")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // DanielSPGTestCourse03 is Classroom SPG Course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("DanielSPGTestCourse03");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("DanielSPGTestCourse03")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        //Clicking on 'User Admin'
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin2=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin2);
        Thread.sleep(1000);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        js.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


// Click on Create User
        WebElement Create_User=driver.findElement(By.xpath("//a[contains(text(),'Create new user')]"));
        js.executeScript("arguments[0].click();", Create_User);


// Enter the First Name of the user that you wish to create
        String userid = driver.findElement(By.id("input-badgeNo")).getAttribute("value");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-firstName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-firstName")).sendKeys(userid);

// Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("input-lastName")).sendKeys(userid);

// Click on 'Select' for Site
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[7]/div/div[1]/button"))));
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[7]/div/div[1]/button")).click();
            Thread.sleep(1000);
// Enter the search value as "SCV"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("SCV");


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

        JavascriptExecutor js2 = ((JavascriptExecutor) driver);
        js2.executeScript("window.scrollBy(0,850)", "");

// Clicking on USA Normal User
//driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

// Click on 'Save' button
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/button")).click();

        Thread.sleep(2000);


//Click Role Page
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Roles')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Roles')]")).click();
        Thread.sleep(2000);
//Click the DanielAdmin Btn
        WebElement RolePicking= driver.findElement(By.xpath("//*[@id=\"__BVID__92\"]"));
        js.executeScript("arguments[0].click();", RolePicking);

        Thread.sleep(2000);

        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("username")).sendKeys(userid);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("welcomeShowRS"))));
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


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // DanielSPGCourse01 is Online SPG course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("DanielSPGCourse01");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("DanielSPGCourse01")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // 0sDvK3sEly is Checklist SPG Course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("0sDvK3sEly");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("DanielSPGCourse01")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // haaKWcGLxR is Classroom SPG Course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("DanielSPGTestCourse03");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("DanielSPGTestCourse03")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }


        driver.quit();

    }

    }
