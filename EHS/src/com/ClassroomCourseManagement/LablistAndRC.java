package com.ClassroomCourseManagement;

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
public class LablistAndRC {
    public void LablistAndRC()throws IOException,InterruptedException{

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Clicking on 'User Admin'
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
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


        // Click on 'Save' button
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();


        //Go to Lab and Assign the User to the Lab with Manager of X00001572
        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();",ele1);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Lab Management')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Lab Management')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("2J4foMj3Sa");

        //Click Go Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='btn btn-primary no-margin']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='btn btn-primary no-margin']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("2J4foMj3Sa")));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("2J4foMj3Sa")).click();

        //Click Add  btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='addLabMember']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='addLabMember']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(id);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='gobutton btn btn-primary']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='gobutton btn btn-primary']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[7]/div[1]/div[9]/form[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/input[1]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/div[9]/form[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/input[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='squaredcorner modal-body']//input[5]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='squaredcorner modal-body']//input[5]")).click();

        //Click Save
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='center mt-3']//input[@class='btn btn-success']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='center mt-3']//input[@class='btn btn-success']")).click();
        Thread.sleep(5000);
        WebElement ele2 = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        JavascriptExecutor js3 = (JavascriptExecutor)driver;
        js3.executeScript("arguments[0].click();",ele2);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='searchBadgeNo']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='searchBadgeNo']")).sendKeys(id);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='row center mt-4']//input[1]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='row center mt-4']//input[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(id)));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText(id)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='btn btn-success']")));
        if(driver.getPageSource().contains("2J4foMj3Sa")){
            System.out.println("Test is successful");
        }
        else{
            Assert.fail("Test Failed");
        }
    }

    }
