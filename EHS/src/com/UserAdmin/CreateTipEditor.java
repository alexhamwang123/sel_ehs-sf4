//This program logs in as an admin, creates a new user and provides him with the User Support rights.

package com.UserAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//@Test
@Test(priority=84)
public class CreateTipEditor {

    public void CreateTipEditor() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");
        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Clicking on 'User Admin'
        WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[1]/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Click on Create User
        driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();
        Thread.sleep(3000);
        String id = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");
        String oid = id;
        id=id.substring(id.lastIndexOf("000")+2);
        System.out.println("id 000 is " + id);
        id="TipEditor" + id;
        System.out.println("id append TipEditor is　" + id);
        String password2 = id;
        System.out.println("password2 is " + password2);

        // Enter the First Name of the user that you wish to create
        driver.findElement(By.id("detailFirstName")).sendKeys(id + "_First");

        // Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("detailLastName")).sendKeys(id + "_Last");

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

        // Clicking on USA Normal User

        // IT's gonna have 40285a840b8ea1e4010b8ea1e5100010 of USA Normal User via default
        // If you try to do it again, it's gonna have no this role anymore.
        // driver.findElement(By.cssSelector("input[type='checkbox'][value='40285a840b8ea1e4010b8ea1e5100010']")).click();

        // Clicking on Tip Editor to assign the role
        driver.findElement(By.cssSelector("input[type='checkbox'][value='964e22e8a9b811e8824240f2e9cb54a2']")).click();

        // Click on 'Save' button
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Thread.sleep(2000);
        driver.findElement(By.id("lightbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("login_login_id")).sendKeys(oid);
        driver.findElement(By.id("login_password")).sendKeys(oid);

        driver.findElement(By.name("submit")).click();
        Thread.sleep(4500);


        driver.quit();


    }

}
