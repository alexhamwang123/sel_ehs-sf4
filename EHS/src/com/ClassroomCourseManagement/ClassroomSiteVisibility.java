package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class ClassroomSiteVisibility {

    public void ClassroomSiteVisibility() throws IOException, InterruptedException {

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

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);


        //Click on course admin
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);


        //Click on classroom course management
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();


        //Click on Create course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();

        String courseId = generator.generate(10);
        System.out.println("course ID: " + courseId);

        //Fill in Fields
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@id='addClass']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();

        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("site_radio"))));
        Thread.sleep(1000);
        driver.findElement(By.id("site_radio")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("selectBtnSite"))));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        Thread.sleep(3500);
        driver.findElement(By.id("searchName")).sendKeys("SCV");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Search']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("5");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("TimeAdd"))));
        Thread.sleep(1000);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(2500);
        js.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 25,2030'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd_Save")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveClassCourse"))));
        Thread.sleep(1000);
        driver.findElement(By.id("saveClassCourse")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Courses')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-primary border-0']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-primary border-0']")).click();

        WebElement EnrollBtn=driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[5]/button"));
        if(!EnrollBtn.isDisplayed()) {
            Assert.fail("The user can't Enroll the course which is  in his Site");
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

        JavascriptExecutor js1 = ((JavascriptExecutor) driver);
        js1.executeScript("window.scrollBy(0,850)", "");

// Clicking on USA Normal User
//driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

// Click on 'Save' button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/button")).click();



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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"welcomeShowRS\"]"))));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"welcomeShowRS\"]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("question[4586]"))));
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
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-primary border-0']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-primary border-0']")).click();

        try{
            if(EnrollBtn.isDisplayed()){
            Assert.fail("The user can Enroll the course which is not in his Site");
        }

        }
        catch(Exception e){
           System.out.println("The user can't Enroll the course which is not in his Site, Test is successful");

        }



    }

    }
