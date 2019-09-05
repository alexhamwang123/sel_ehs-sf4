package com.Miscellaneous;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);

        //Check the SPG Manager of the 3 types Courses
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Management")).click();
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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Management")).click();
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("1220testforscript9326");
        //Click SPG Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='search[is_spg]']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='search[is_spg]']")).click();

        //Click Go btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("1220testforscript9326"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("1220testforscript9326")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        if(driver.getPageSource().contains("tom tom")){
            System.out.println("The SPG Manager is the current System User");
        }
        else {
            Assert.fail("The SPG Manager is not the current System User");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("0YC5wikw6d");
        //Click SPG Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='search[is_spg]']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='search[is_spg]']")).click();

        //Click Go btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("0YC5wikw6d"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("0YC5wikw6d")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
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
        // 1220testforscript9326 is Online SPG course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("1220testforscript9326");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("1220testforscript9326")){
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
        if(!driver.getPageSource().contains("1220testforscript9326")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // 0sDvK3sEly is Classroom SPG Course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("0YC5wikw6d");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("0YC5wikw6d")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        //Clicking on 'User Admin'
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        js.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Click on Create User
        driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();

        Thread.sleep(3500);
        //自動會有detailBadgeNumber ，也就是我們要用的bdgeeNumber ID
        String id = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");
        System.out.println("id is " + id);

        // Enter the First Name of the user that you wish to create
        driver.findElement(By.id("detailFirstName")).sendKeys(id);

        // Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("detailLastName")).sendKeys(id);

        // Click on 'Select' for Site
        driver.findElement(By.id("selectBtnSite")).click();

        // Enter the search value as "SCV"
        driver.findElement(By.id("searchName")).sendKeys("SCV");

        // Click on Search
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Click on 'SCV' from the search results
        driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Enter the email of the user that you wish to create
        driver.findElement(By.name("detailEmailAddress")).sendKeys(id + "@trismax.com");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JavascriptExecutor js1 = ((JavascriptExecutor) driver);
        js1.executeScript("window.scrollBy(0,850)", "");

        driver.findElement(By.xpath("//label[contains(text(),'System Admin')]")).click();
        // Click on 'Save' button
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Thread.sleep(2000);

        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("username")).sendKeys(id);
        driver.findElement(By.id("password")).sendKeys(id);

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
        // 1220testforscript9326 is Online SPG course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("1220testforscript9326");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("1220testforscript9326")){
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
        if(!driver.getPageSource().contains("1220testforscript9326")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        // 0sDvK3sEly is Classroom SPG Course
        driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[1]/div/div[1]/input")).sendKeys("0YC5wikw6d");
        Thread.sleep(1000);
        if(!driver.getPageSource().contains("0YC5wikw6d")){
            System.out.println("SPG Course is not searchable");
        }
        else{
            Assert.fail("SPG Course is searchable in Courses");
        }


        driver.quit();

    }

    }