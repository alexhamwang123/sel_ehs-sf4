package com.ClassroomCourseManagement;

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
public class ClassroomVisibilityCheckbox {
    public void ClassroomVisibilityCheckbox() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait Wait = new WebDriverWait(driver, 30);
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
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

 /*       //Create A New User
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

        driver.findElement(By.xpath("//label[contains(text(),'Super Admin')]")).click();
        // Click on 'Save' button
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();




*/

        //Click on course admin
        JavascriptExecutor js = (JavascriptExecutor)driver;

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);


        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();
        Thread.sleep(2000);
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js.executeScript("arguments[0].click()", CreateBtn);


        Wait.until(ExpectedConditions.elementToBeClickable(By.id("course-category")));
        new Select(driver.findElement(By.id("course-category"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.id("course-fulfill"))).selectByVisibleText("Normal & Refresh");

                String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

        String Building = generator.generate(11);
        System.out.println(courseId);
        driver.findElement(By.id("course-num")).sendKeys(courseId);

        //input description
        driver.findElement(By.id("input-desc")).sendKeys("This is description");

        //Completion Message
        driver.findElement(By.id("completion-message")).sendKeys("Congrats Completion");

        //input Course Number
        driver.findElement(By.id("course-title")).sendKeys("This is title");

        //Save btn
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Classroom Details
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("expiration")));
        new Select(driver.findElement(By.id("expiration"))).selectByVisibleText("6 months");
        Thread.sleep(2000);;


        //input Classroom Details
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("training-hour")));
        //CLick Viewable btn
        WebElement ViewBtn = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/a[2]/div/input"));
         js.executeScript("arguments[0].click()", ViewBtn);
        driver.findElement(By.id("training-hour")).sendKeys("3");
        driver.findElement(By.id("credit-hour")).sendKeys("3");

        //Click Save
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();

        //Click Offer Schedule
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Management')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[3]/a")).click();

        //Click Add offer
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[1]/button")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div[1]/button")).click();

        //Click Site Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/button")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/button")).click();

        //Search SCV
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to filter result']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys("SCV");

        //Click SCV
        driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")).click();


        //Building
        driver.findElement(By.id("building")).sendKeys(Building);
        driver.findElement(By.id("room")).sendKeys("001");
        driver.findElement(By.id("class-size")).clear();
        driver.findElement(By.id("class-size")).sendKeys("5");

        //Click Edit Btn of time schedule
        driver.findElement(By.xpath("//*[@id=\"classTimeTable\"]/tbody/tr/td[6]/div/button[2]")).click();
// Calendar&Time//click save
        driver.findElement(By.xpath(" //*[@id=\"classTimeTable\"]/tbody/tr/td[6]/div/button[1]")).click();
        Thread.sleep(1000);
        //Click the Save Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")));
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg shadow rounded-circle']")).click();
        Thread.sleep(2500);

        //Uncheck Viewable Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Management')]")));
        Thread.sleep(1500);
        WebElement Viewable=driver.findElement(By.xpath("//a[2]//div[1]//input[1]"));
        js.executeScript("arguments[0].click()", Viewable);

        Thread.sleep(1500);

/*
        //Click EHS Admin

        WebElement EHSAdmin = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        js.executeScript("arguments[0].click();", EHSAdmin);

        Thread.sleep(1500);

        //Click RC Management
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("1 course"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("1 course")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='addCourse']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='addCourse']")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);
        driver.findElement(By.xpath("//div[@class='searchBar']//input[contains(@class,'btn btn-primary')]")).click();


        if(driver.getPageSource().contains(courseId)){
            Assert.fail("The Course is not Checked as Visible, So RC should not it should not appear in RC");
        }


        //Asign User

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("userAssign"))));
        Thread.sleep(1000);
        driver.findElement(By.id("userAssign")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(id);
        driver.findElement(By.xpath("//div[@class='searchBar']//input[contains(@class,'btn btn-primary')]")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='pickone']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='pickone']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='search_result']//input[contains(@class,'btn-primary')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='search_result']//input[contains(@class,'btn-primary')]")).click();

        //Click on course admin
        WebElement courseAdmin0 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin0);

        Thread.sleep(1500);


        //Click on classroom course management
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText(courseId))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText(courseId)).click();


        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='detailCourseIsInvisible']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='detailCourseIsInvisible']")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='btn btn-success']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();


        //Login the User Assigned RC
        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("username")).sendKeys(id);
        driver.findElement(By.id("password")).sendKeys(id);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);

        //Go to courses tab and search the course just made
        WebElement course2 = driver.findElement(By.xpath("//a[contains(text(),'Courses')]"));
        js.executeScript("arguments[0].click();", course2);
        Thread.sleep(1800);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1800);
        if(driver.getPageSource().contains(courseId)){
            Assert.fail("The user can see the course whose checkbox of visibility is not checked(RC is Assigned)");
        }

        //Login the User Not Assigned RC
        WebElement Logout1=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout1);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("username")).sendKeys("X00001815");
        driver.findElement(By.id("password")).sendKeys("X00001815");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
*/
        //Go to courses tab and search the course just made
         Thread.sleep(1800);

         WebElement course3 = driver.findElement(By.xpath("//a[contains(text(),'Courses')]"));
        js.executeScript("arguments[0].click();", course3);
        Thread.sleep(1800);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(1800);
        if(driver.getPageSource().contains(courseId)){
            Assert.fail("The user can see the course whose checkbox of visibility is not checked(RC is Not Assigned)");
        }

        driver.quit();
    }

    }
