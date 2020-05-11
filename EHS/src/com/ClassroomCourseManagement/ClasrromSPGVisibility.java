package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
public class ClasrromSPGVisibility {
    public void ClasrromSPGVisibility() throws IOException, InterruptedException {

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

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String normuser = prop.getProperty("testnormuser");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(2000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control']")));
        Thread.sleep(1000);
        Thread.sleep(1000);

        //SPG Btn
        WebElement SPG= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/input[1]"));
        js.executeScript("arguments[0].click();", SPG);
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("DanielSPGTest02");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'DanielSPGTest02')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//td[contains(text(),'DanielSPGTest02')]")).click();


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("course-title"))));
        Thread.sleep(1000);
        if(driver.getPageSource().contains("DanielSPGTest02")){
            System.out.println("The SPG Manager is the current System User");
        }
        else {
            Assert.fail("The SPG Manager is not the current System User");
        }

        //Create A new RC to add the SPG Classroom
        //Clicking on EHS Admin
        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        js.executeScript("arguments[0].click();",ele1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'RC Admin')]")).click();

        //Click on the 'Create Risk Category' button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/button"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/ul/a[1]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RandomStringGenerator generator2 = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title1 = generator2.generate(10);
        //Enter the Risk Category Name
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("input-name"))));
        Thread.sleep(1000);
        driver.findElement(By.id("input-name")).sendKeys(title1);

        //Enter the Risk Category Abbr Name
        driver.findElement(By.id("input-abbrev")).sendKeys(title1);

        //Enter the description
        driver.findElement(By.id("input-desc")).sendKeys("this is the description for at least 20 characters!");

        Thread.sleep(1500);
        //Click Manager
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
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("DanielSPGTest02");

        Thread.sleep(1000);

        if(driver.getPageSource().contains("DanielSPGTest02")){
            System.out.println("RC SPG is Searchable for SPG Manager");
        }

        else {
            Assert.fail("RC SPG is not Searchable for SPG Manager");
        }

        //Clicking on 'User Admin'
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        JavascriptExecutor js0 = (JavascriptExecutor)driver;
        js0.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(3000);
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[7]/div/div/div[1]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[7]/div/div/div[1]/button")).click();
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


//Click Role Page
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"__BVID__46___BV_tab_button__\"]"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"__BVID__46___BV_tab_button__\"]")).click();
        Thread.sleep(2000);
//Click the DanielAdmin Btn
        WebElement RolePicking= driver.findElement(By.xpath("//*[@id=\"__BVID__86\"]"));
        js.executeScript("arguments[0].click();", RolePicking);

        Thread.sleep(2000);
        JavascriptExecutor js3 = (JavascriptExecutor)driver;
        WebElement Logout=driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/nav[1]/div/ul/li[2]/div/a[5]"));
        js3.executeScript("arguments[0].click()",Logout);
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

        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click();", courseAdmin1);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("DanielSPGTest02");

         //SPG Btn
         wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
         JavascriptExecutor js4 = (JavascriptExecutor)driver;
         WebElement SPG1= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/input[1]"));
         js4.executeScript("arguments[0].click();", SPG1);


        if(!driver.getPageSource().contains("DanielSPGTest02")){
            System.out.println("RC SPG is not Searchable for Non SPG Manager");
        }

        else {
            Assert.fail("RC SPG is Searchable for Non SPG Manager");
        }

        WebElement ele2 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        js.executeScript("arguments[0].click();",ele2);

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
        WebElement Save2= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/button"));
        JavascriptExecutor js5 = (JavascriptExecutor)driver;
        js5.executeScript("arguments[0].click();", Save2);

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
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/div[1]/div/input")).sendKeys("DanielSPGTest02");

        Thread.sleep(1000);

        if(!driver.getPageSource().contains("DanielSPGTest02")){
            System.out.println("RC SPG is not Searchable for Non SPG Manager");
        }

        else {
            Assert.fail("RC SPG is Searchable for Non SPG Manager");
        }


        driver.quit();
    }

}
