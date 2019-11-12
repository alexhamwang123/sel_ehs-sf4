package com.OnlineCourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Test
public class OnlineRegionVisibility {
    public void OnlineRegionVisibility() throws InterruptedException, IOException, AWTException {
        int number;
        int integer;
        Random rand = new Random();
        number= rand.nextInt(10000);
        integer=rand.nextInt(10000);

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
        Thread.sleep(1500);
        driver.findElement(By.linkText("Online Course Management")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("detailCourseNo")).sendKeys(integer+"testforscript"+number);

        WebElement element1 = driver.findElement(By.xpath("//select[@name='detailCourseCategory']"));
        Select oSelect = new Select(element1);
        oSelect.selectByValue("37");
        WebElement element2 = driver.findElement(By.xpath("//select[@id='detailCourseFulfillType']"));
        Select iSelect = new Select(element2);
        iSelect.selectByValue("1");
        WebElement element3 = driver.findElement(By.xpath("//select[@id='detailCourseExpiration']"));
        Select aSelect = new Select(element3);
        aSelect.selectByValue("0");

        WebElement element4 = driver.findElement(By.xpath("//select[@id='detailCourseSite']"));
        Select RegionSelect = new Select(element4);
        RegionSelect.selectByVisibleText("Taipei");


        driver.findElement(By.xpath("//input[@value='Save']")).click();

        Thread.sleep(14000);
        driver.findElement(By.xpath("//input[@value='Edit']")).click();
        driver.findElement(By.xpath("//input[@id='detailCourseTitle']")).sendKeys(integer+"testforscript");

        //driver.findElement(By.id("")).sendKeys("/Users/trismax/IdeaProjects/sel_ehs/EHS/Mantis Status (English).docx.pdf");
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@value='Save']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='btn_edit_content']")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[6]/div[1]/div[1]/div[1]/input[1]")).click();//第二次click Edit Content 按鈕
        driver.findElement(By.xpath("//input[@value='Create Page']")).click();
        driver.findElement(By.cssSelector("div.clearfix:nth-child(1) div.row div.col-md-9.col-xs-9 div.listTable:nth-child(18) table.tablesorter.table.table-hover.table-striped tbody:nth-child(2) tr:nth-child(1) td:nth-child(3) > button.btn.btn-primary.btn-xs.field-tip")).click();

        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/table[1]/tbody[1]/tr[1]/td[2]/ul[1]/li[2]/a[1]/img[1]")).click();
        driver.findElement(By.xpath("//input[@id='courseContentTitle']")).sendKeys("testforscript"+number);
        driver.findElement(By.xpath("//div[@class='padded form-horizontal mt-3']//div[5]//input[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();

        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@id='langIsViewable']")).click();
        Thread.sleep(3000);
        //Actions action= new Actions(driver);

        //action.click(driver.findElement(By.id("checklistDefaultRegion")))

        driver.findElement(By.xpath("//input[@id='detailCourseIsActive']")).click();
        Thread.sleep(3000);
        /*WebElement element5=driver.findElement(By.id("checklistDefaultRegion"));
        Select fseclect= new Select(element5);
        fseclect.selectByValue("48a75e09cf08f298175f78d271a1eee6");

        WebElement element6= driver.findElement(By.id("checklistDefaultLanguage"));
        Select gselect= new Select(element6);
        gselect.selectByValue("English");*/
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(integer+"testforscript"+number);

        if(driver.getPageSource().contains(integer+"testforscript"+number)){
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"__BVID__31\"]/div/div[1]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"__BVID__31\"]/div/div[1]/button")).click();
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
        WebElement RolePage= driver.findElement(By.xpath("//*[@id=\"__BVID__49___BV_tab_button__\"]"));
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
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(integer+"testforscript"+number);

        if(!driver.getPageSource().contains(integer+"testforscript"+number)){
            Assert.fail("The user within the Region can't see the course");
        }
    }

    }
