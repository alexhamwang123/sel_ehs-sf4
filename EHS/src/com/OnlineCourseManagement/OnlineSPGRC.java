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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
@Test
public class OnlineSPGRC {
    public void OnlineSPGRCCompletion() throws IOException, InterruptedException{

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
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();;
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();


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

        JavascriptExecutor js0 = ((JavascriptExecutor) driver);
        js0.executeScript("window.scrollBy(0,850)", "");

// Clicking on USA Normal User
//driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

// Click on 'Save' button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/button")).click();



//Click Role Page
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"__BVID__46___BV_tab_button__\"]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"__BVID__46___BV_tab_button__\"]")).click();
        Thread.sleep(2000);
//Click the DanielAdmin Btn
        WebElement RolePicking= driver.findElement(By.xpath("//*[@id=\"__BVID__86\"]"));
        js.executeScript("arguments[0].click();", RolePicking);

        Thread.sleep(2000);

        //Create SPG Course
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js1.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Admin")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Admin"))));
        Thread.sleep(1000);
        WebElement CreateBtn = driver.findElement(By.xpath("//a[contains(text(),'Create new course')]"));
        js1.executeScript("arguments[0].click()", CreateBtn);


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

        Thread.sleep(1000);
        //Click SPG Btn
        WebElement SPG=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/a[3]/div/input"));
        js1.executeScript("arguments[0].click()", SPG);
        Thread.sleep(1000);
        //Click OK BTN
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]")).click();

        //input training time
        wait.until(ExpectedConditions.elementToBeClickable(By.id("course-trainingTime")));
        Thread.sleep(1000);
        driver.findElement(By.id("course-trainingTime")).sendKeys("1");
        //input credit
        driver.findElement(By.id("course-credit")).sendKeys("1");

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
        js1.executeScript("arguments[0].click()", Save_Btn);
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
        js1.executeScript("arguments[0].click()", CorrectBtn);

        //Click OK btn
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/footer/button[2]")).click();

        //Click Save Btn
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/button[3]")));
        Thread.sleep(1000);
        js1.executeScript("arguments[0].click()", Save_Btn);

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
        js1.executeScript("arguments[0].click()", VisibilityBtn);

        //Click viewable btn
        WebElement ViewableBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/a[2]/div/input"));
        js1.executeScript("arguments[0].click()", ViewableBtn);

        //Wait until the success message shows up
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/header")));

        //Create A new RC to add the SPG Classroom
        //Clicking on EHS Admin
        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        js1.executeScript("arguments[0].click();",ele1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();

        //Click on the 'Create Risk Category' button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/ul/a[1]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RandomStringGenerator generator1 = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator1.generate(10);
        //Enter the Risk Category Name
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-name"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-name")).sendKeys(title);

        //Enter the Risk Category Abbr Name
        driver.findElement(By.id("input-abbrev")).sendKeys(title);

        //Enter the description
        driver.findElement(By.id("input-desc")).sendKeys("this is the description for at least 20 characters!");

        Thread.sleep(1500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div/div[4]/div/div/div/div[1]/div/div/button")).click();
        Thread.sleep(2500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("criteriaBadge"))));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys(username);
        //Click Search
        Thread.sleep(2500);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/footer/div/button")).click();
        Thread.sleep(2500);
        //Click Result
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(1000);
        //Click Save btn
        WebElement Save= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/button"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();", Save);

        //Click add Required Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/ul/li[3]/a"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/ul/li[3]/a")).click();
        //Click add course Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/button"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/button")).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys(courseId);

        //Click result
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(500);
        //WebElement add = driver.findElement(By.cssSelector("input[type='button'][value='Add']"));
        WebElement addconfirmbtn = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]"));
        js.executeScript("arguments[0].click();", addconfirmbtn);
        WebElement EmailUser = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/button[2]"));
        js.executeScript("arguments[0].click();", EmailUser);

        //Assign the RC
        //Click the Assign Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/ul/li[4]/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/ul/li[4]/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div[4]/div/div[3]/div/button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[2]/div[4]/div/div[3]/div/button[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("criteriaBadge"))));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys(userid);
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/footer/div/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();


        //Click OK Btn
        WebElement OKBtn = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/button[2]"));
        js.executeScript("arguments[0].click();", OKBtn);

        Thread.sleep(2000);
        //Click Home
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Home"))));
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

        //Login the Created User

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

        //Click the RC on Home Page
        WebElement box= driver.findElement(By.xpath("//div[@class='bottom-shadow bg-white']//div[@class='card shadow-sm']//div[2]"));
        List<WebElement> Links= box.findElements(By.tagName("td"));
        for (WebElement Link:Links
        ) {
            if (Link.getText().contains(title)){
                JavascriptExecutor js001 = (JavascriptExecutor)driver;
                js001.executeScript("arguments[0].click();",Link);
            }
        }
        Thread.sleep(2000);

        //Click Start Btn   //--In General Find the Start Btn Row through TR(Row), then Break the Row into TD and Click it
        //Find the Row of Start Btn
        List<WebElement> StartBtnsRows= box.findElements(By.tagName("table"));
        for (WebElement StartBtnROw:StartBtnsRows){
            if(StartBtnROw.getText().contains(courseId)){
                System.out.println("The RC Table has been found");
                WebElement StartBtn=StartBtnROw.findElement(By.tagName("button"));

                JavascriptExecutor js002 = (JavascriptExecutor)driver;
                js002.executeScript("arguments[0].click();",StartBtn);



            }
        }


        driver.quit();

    }

}
