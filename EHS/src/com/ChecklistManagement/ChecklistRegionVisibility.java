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
public class ChecklistRegionVisibility {
    public void ChecklistRegionVisibility() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");

        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
actions.build().perform(); }
catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        driver.manage().window().maximize();
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        ;
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

            JavascriptExecutor js = (JavascriptExecutor) driver;
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
        new Select(driver.findElement(By.id("detailCourseRegion"))).selectByVisibleText("SITE_SCV");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
        driver.findElement(By.id("saveBtn")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Edit']")));
        Thread.sleep(1000);
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
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveBtn"))));
        driver.findElement(By.id("saveBtn")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        //Clicking on 'User Admin'
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
        Thread.sleep(1000);
        WebElement Admin1=driver.findElement(By.xpath("//span[contains(text(),'Admin')]"));
        js.executeScript("arguments[0].click()", Admin1);
        Thread.sleep(1000);
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        js.executeScript("arguments[0].click();",ele);

        Thread.sleep(3500);

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

        Thread.sleep(4000);


//Click Role Page

        driver.findElement(By.xpath("//a[contains(text(),'Roles')]")).click();
        Thread.sleep(4000);

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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("welcomeShowRS"))));
        Thread.sleep(1000);
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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        if(driver.getPageSource().contains(courseId)){
            Assert.fail("User in different Region can see the Course");
        }
        Thread.sleep(3000);
        driver.quit();

    }
}
