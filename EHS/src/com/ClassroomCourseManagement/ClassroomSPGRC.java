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
public class ClassroomSPGRC {
    public void ClassroomSPGRCCompletion() throws IOException, InterruptedException {

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

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String normuser = prop.getProperty("testnormuser");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();


        //Clicking on 'User Admin'
        JavascriptExecutor js = (JavascriptExecutor)driver;

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
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

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-firstName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-firstName")).sendKeys(userid);

// Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("input-lastName")).sendKeys(userid);

// Click on 'Select' for Site
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[7]/div/div[1]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[7]/div/div[1]/button")).click();
        Thread.sleep(1000);
// Enter the search value as "SCV"
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("SCV");


// Click on 'SCV' from the search results
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


// Enter the email of the user that you wish to create
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-email"))));
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
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/button")).click();

        Thread.sleep(2000);


//Click Role Page
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Roles')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Roles')]")).click();
        Thread.sleep(2000);
//Click the DanielAdmin Btn
        WebElement RolePicking= driver.findElement(By.xpath("//*[@id=\"__BVID__92\"]"));
        js.executeScript("arguments[0].click();", RolePicking);

        Thread.sleep(2500);
                Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);
         WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));

        js.executeScript("arguments[0].click()", courseAdmin);
        Thread.sleep(1000);
        //Create SPG Course
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        Thread.sleep(1000);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();

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


        //SPG Btn
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
        WebElement SPG=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/a[3]/div/input"));
        js.executeScript("arguments[0].click();", SPG);

        //CLick OK
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        //Click Classroom Details
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")));
Thread.sleep(1000);
driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[1]/ul/li[2]/a")).click();
        Wait.until(ExpectedConditions.elementToBeClickable(By.id("expiration")));
        new Select(driver.findElement(By.id("expiration"))).selectByVisibleText("6 months");
        Thread.sleep(2000);


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
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Classroom Course Management')]")));
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

        //Create A new RC to add the SPG Classroom
        //Clicking on EHS Admin
        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin2=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin2);
        Thread.sleep(1000);

        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        js1.executeScript("arguments[0].click();",ele1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();

        //Click on the 'Create Risk Category' button
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/ul/a[1]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RandomStringGenerator generator1 = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator1.generate(10);
        //Enter the Risk Category Name
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-name"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-name")).sendKeys(title);

        //Enter the Risk Category Abbr Name
        driver.findElement(By.id("input-abbrev")).sendKeys(title);

        //Enter the description
        driver.findElement(By.id("input-desc")).sendKeys("this is the description for at least 20 characters!");

        Thread.sleep(1500);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div[4]/div/div/div[1]/div/div/button")).click();
        Thread.sleep(2500);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("criteriaBadge"))));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys(username);
        //Click Search
        Thread.sleep(2500);
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        Thread.sleep(2500);
        //Click Result
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//td[contains(text(),'"+username+"')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td[contains(text(),'"+username+"')]")).click();
        Thread.sleep(1000);
        //Click Save btn
        WebElement Save= driver.findElement(By.xpath("//*[@id=\"admin-curriculum\"]/div/div[2]/button"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();", Save);

        //Click add Required Course
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/ul/li[3]/a"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/ul/li[3]/a")).click();
        //Click add course Btn
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Add Course')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Add Course')]")).click();
        Thread.sleep(1000);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Add Core Course')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Add Core Course')]")).click();
        Thread.sleep(1000);
        Thread.sleep(1000);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys(courseId);

        //Click result
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td[contains(text(),'"+courseId+"')]")).click();
        Thread.sleep(500);
        //WebElement add = driver.findElement(By.cssSelector("input[type='button'][value='Add']"));
        JavascriptExecutor js0 = (JavascriptExecutor)driver;

        WebElement addconfirmbtn = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
        js0.executeScript("arguments[0].click();", addconfirmbtn);
        Thread.sleep(1500);
        WebElement EmailUser = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
        js0.executeScript("arguments[0].click();", EmailUser);
        Thread.sleep(1000);
        //Assign the RC
        //Click the Assign Btn
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//main//li[4]//a[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//main//li[4]//a[1]")).click();

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Add Member')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Add Member')]")).click();
        Thread.sleep(1500);


        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("criteriaBadge"))));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys(userid);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td[contains(text(),'"+userid+"')]")).click();

        Thread.sleep(1500);
        //Click OK Btn
        WebElement OKBtn = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
        js0.executeScript("arguments[0].click();", OKBtn);

        Thread.sleep(2000);
        //Click Home
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Home"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Home")).click();
        Thread.sleep(1000);
        //Try to Find RC on Home Page
        if(driver.getPageSource().contains(title)){
            Assert.fail("The Unassigned User is able to see the SPG RC");
        }
        else {
            System.out.println("The Unassigned User is not able to see the SPG RC");
        }

        //Go to the Created SPG Course and the RC assigned course should be unable to be deactivated

        Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin3=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin3);
        Thread.sleep(1500);
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin1);

        Thread.sleep(1500);
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        Thread.sleep(1500);
        //Search SPG Course
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(courseId);

        //CLick the result
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='form-control']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[1]")).click();

        //CLick the active btn, it should not be able to be deactivated

        //CLick active btn
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Offering Schedule')]"))));
        Thread.sleep(2000);
        WebElement ActiveBtn= driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/a[1]/div/input"));
        js.executeScript("arguments[0].click()",ActiveBtn);

        Thread.sleep(2000);

        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Offering Schedule')]"))));
        Thread.sleep(1000);
        WebElement active=driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/a[1]/div/input"));
        if(active.isSelected()){
            System.out.println("The Assign RC course cant be deactivated");
        }
        else{
            Assert.fail("RC course can be deactivated");
        }


        //Login the Created User

        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/div/span")).click();
        Thread.sleep(2000);


        driver.findElement(By.id("username")).sendKeys(userid);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        
 Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("welcomeShowRS"))));
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


        driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button")).click();
        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("annContinue"))));
        Thread.sleep(1000);
         driver.findElement(By.id("annContinue")).click();
         try {

            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
         }
         catch (Exception e){
            System.out.println("there is no OK btn, and it is ok");
        }


        Wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Home')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        Thread.sleep(2000);
        js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
        String Title="New RC"+title;
        driver.findElement(By.xpath("//figcaption[contains(text(),'"+Title+"')]")).click();
        //Click Start Btn   //--In General Find the Start Btn Row through TR(Row), then Break the Row into TD and Click it
        //Find the Row of Start Btn
        driver.findElement(By.partialLinkText(courseId)).click();




        //CLick enroll Btn
        Thread.sleep(3000);
        WebElement EnrollBtn01= driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']"));
        js.executeScript("arguments[0].click();",EnrollBtn01);

        //click Close
        Thread.sleep(3000);
        WebElement CloseBtn01= driver.findElement(By.xpath("//button[@class='close']"));
        js.executeScript("arguments[0].click();",CloseBtn01);
        Thread.sleep(3000);


        driver.quit();

    }

    }
