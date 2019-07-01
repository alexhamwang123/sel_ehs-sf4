package com.ChecklistManagement;

import org.apache.commons.text.RandomStringGenerator;
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

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
@Test
public class CheckListSPGVisibility {
    public void CheckListSPGVisibility() throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String normuser = prop.getProperty("testnormuser");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        //Create new user and  Log in the newly Created User
        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",ele1);


        // Click on Create User
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Create User']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();

        //自動會有detailBadgeNumber ，也就是我們要用的bdgeeNumber ID
        String id = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");
        System.out.println("id is " + id);

        // Enter the First Name of the user that you wish to create
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("detailFirstName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("detailFirstName")).sendKeys(id);

        // Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("detailLastName")).sendKeys(id);

        // Click on 'Select' for Site
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("selectBtnSite"))));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        // Enter the search value as "SCV"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("searchName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("searchName")).sendKeys("SCV");

        // Click on Search
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();


        // Click on 'SCV' from the search results
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a"))));
        Thread.sleep(1000);
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
        Thread.sleep(6500);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("welcomeShowRS")));
        Thread.sleep(1500);
        driver.findElement(By.id("welcomeShowRS")).click();
        Thread.sleep(4500);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("question[4586]")));
        Thread.sleep(1500);
        driver.findElement(By.name("question[4586]")).click();
        driver.findElement(By.name("question[1361]")).click();
        driver.findElement(By.name("question[4562]")).click();
        driver.findElement(By.name("question[4225]")).click();
        driver.findElement(By.name("question[4127]")).click();
        driver.findElement(By.name("question[4193]")).click();
        driver.findElement(By.name("question[145]")).click();
        driver.findElement(By.name("question[1164]")).click();
        driver.findElement(By.name("question[4676]")).click();
        driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("annContinue"))));
        Thread.sleep(1000);
        driver.findElement(By.id("annContinue")).click();


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Home')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        Thread.sleep(3000);
        if(!driver.getPageSource().contains("AuW7PqCTX5")){
            System.out.println("SPG Course RC is searchable in Home");
        }
        else{
            Assert.fail("SPG Course RC is not searchable in Home");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys("AuW7PqCTX5");
        Thread.sleep(3000);
        if(!driver.getPageSource().contains("AuW7PqCTX5")){
            System.out.println("SPG Course for new User is searchable in Courses");
        }
        else{
            Assert.fail("SPG Course for new User is not searchable in Courses");
        }

        //Go to Course Admin
        WebElement courseAdmin01 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js003 = (JavascriptExecutor)driver;
        js003.executeScript("arguments[0].click();", courseAdmin01);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("AuW7PqCTX5");

        //Click SPG Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='search[is_spg]']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='search[is_spg]']")).click();

        //Clilck Go Btn
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        if(!driver.getPageSource().contains("AuW7PqCTX5")){
            System.out.println("SPG Course for new User is searchable in Classroom Management");
        }
        else{
            Assert.fail("SPG Course for new User is not searchable in  Classroom  Management");
        }

        driver.quit();

    }

    }
