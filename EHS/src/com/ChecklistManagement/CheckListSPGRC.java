package com.ChecklistManagement;

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
public class CheckListSPGRC {
    public void CheckListSPGRCCompletion() throws IOException, InterruptedException {

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
        JavascriptExecutor js = (JavascriptExecutor)driver;

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin);
        Thread.sleep(1000);

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        js.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(3000);
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

        JavascriptExecutor js1 = ((JavascriptExecutor) driver);
        js1.executeScript("window.scrollBy(0,850)", "");

// Clicking on USA Normal User
//driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

// Click on 'Save' button
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/button")).click();

        //Click Role Page
        Thread.sleep(3000);


        driver.findElement(By.xpath("//a[contains(text(),'Roles')]")).click();
        Thread.sleep(2000);
        //Click the DanielAdmin Btn
        WebElement RolePicking= driver.findElement(By.xpath("//*[@id=\"__BVID__92\"]"));
        js.executeScript("arguments[0].click();", RolePicking);


        //Create SPG Course
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Admin")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(3500);
                String CapitalLetter = generator.generate(1).toUpperCase();
        String courseId = generator.generate(10);
        courseId=CapitalLetter.concat(courseId);
        System.out.println(courseId);

        System.out.println(courseId);
        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Edit']")));
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
        driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
        driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
        driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("createContent"))));
        Thread.sleep(1000);
        driver.findElement(By.id("createContent")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Create']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"detailChecklistContentSaveAs\"]")).click();
        driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Edit']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveBtn"))));
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        //driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        //Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("langIsViewable")));
        Thread.sleep(1000);
        driver.findElement(By.id("langIsViewable")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("detailIsActive")));
        Thread.sleep(1000);
        driver.findElement(By.id("detailIsActive")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='detailIsSPG']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='detailIsSPG']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='isSPG']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='isSPG']")).click();


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        //Create A new RC to add the SPG Classroom
        //Clicking on EHS Admin
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();

        //Click on the 'Create Risk Category' button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/button"))));
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-name"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-name")).sendKeys(title);

        //Enter the Risk Category Abbr Name
        driver.findElement(By.id("input-abbrev")).sendKeys(title);

        //Enter the description
        driver.findElement(By.id("input-desc")).sendKeys("this is the description for at least 20 characters!");

        Thread.sleep(1500);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div/div[4]/div/div/div/div[1]/div/div/button")).click();
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
        WebElement Save= driver.findElement(By.xpath("//*[@id=\"admin-curriculum\"]/div/div[2]/button"));
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();", Save);

        //Click add Required Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/ul/li[3]/a"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[1]/ul/li[3]/a")).click();
        //Click add course Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/button"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/button")).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys(courseId);

        //Click result
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(500);
        //WebElement add = driver.findElement(By.cssSelector("input[type='button'][value='Add']"));
        JavascriptExecutor js0 = (JavascriptExecutor)driver;

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'OK')]"))));
        Thread.sleep(1000);
        WebElement addconfirmbtn = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
        js0.executeScript("arguments[0].click();", addconfirmbtn);
        Thread.sleep(2000);


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"))));
        WebElement EmailUser = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
        js0.executeScript("arguments[0].click();", EmailUser);

        //Assign the RC
        //Click the Assign Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//main//li[4]//a[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//main//li[4]//a[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Add Member')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Add Member')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("criteriaBadge"))));
        Thread.sleep(1000);
        driver.findElement(By.id("criteriaBadge")).sendKeys(userid);
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/footer/div/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div/div/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(1500);

        //Click OK Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'OK')]"))));
        WebElement OKBtn = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
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

        //Go to the Created SPG Course and the RC assigned course should be unable to be deactivated

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));

        Thread.sleep(1000);
        WebElement Admin3=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin3);
        Thread.sleep(1500);
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin1);

        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Admin"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Admin")).click();
        Thread.sleep(1500);
        //Search SPG Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);
        driver.findElement(By.xpath("//label[contains(text(),'SPG')]//input")).click();
        //Click Go btn
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText(courseId))));
        driver.findElement(By.partialLinkText(courseId)).click();

        //CLick the active btn, it should not be able to be deactivated
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        driver.findElement(By.xpath("//input[@id='detailIsActive']")).click();

        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();


        //Search SPG Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);
        driver.findElement(By.xpath("//label[contains(text(),'SPG')]//input")).click();
        //Click Go btn
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText(courseId))));
        driver.findElement(By.partialLinkText(courseId)).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        WebElement active=driver.findElement(By.xpath("//input[@id='detailIsActive']"));
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
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/img")).click();
        Thread.sleep(2000);


        driver.findElement(By.id("username")).sendKeys(userid);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("welcomeShowRS"))));
        Thread.sleep(1500);
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

        System.out.println("title is "+title);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Home')]"))));
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
        Thread.sleep(2000);

            //Scroll to the end of page
        js2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //Find the RC Title and click it
        String Title="New RC"+title;
        Thread.sleep(2000);

        driver.findElement(By.xpath("//figcaption[contains(text(),'"+Title+"')]")).click();

        Thread.sleep(2000);


        //Click Start Btn   //--In General Find the Start Btn Row through TR(Row), then Break the Row into TD and Click it
        //Find the Row of Start Btn


        driver.findElement(By.partialLinkText(courseId)).click();


        driver.quit();

    }

}
