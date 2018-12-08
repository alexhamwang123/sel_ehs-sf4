package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;








@Test

public class ClassroomPrereq {
    public void ClassroomPrereq() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");

        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

//        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
        //go to onlinecourseManagement


        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);

        Thread.sleep(3500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(5500);

        //pick a class  & Assign prerequisite

        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("EHS-3500");
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//a[@class='editAction']")).click();
        Thread.sleep(3500);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText("04a1awuKpJ - test checklist title");
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(8500);
        // make a normal user that never completed the prerequisite
        //Clicking on 'User Admin'
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        JavascriptExecutor js0 = (JavascriptExecutor)driver;
        js0.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Click on Create User
        driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();

        Thread.sleep(3000);

        // Enter the First Name of the user that you wish to create
        String userid = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");


        driver.findElement(By.id("detailFirstName")).sendKeys(userid);

        // Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("detailLastName")).sendKeys(userid);



        // Enter the email of the user that you wish to create
        driver.findElement(By.name("detailEmailAddress")).sendKeys(userid + "@trismax.com");

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
        Thread.sleep(2000);
        // Click on 'Select' for Site
        driver.findElement(By.id("selectBtnSite")).click();
        Thread.sleep(4000);

        //click on SVC
        driver.findElement(By.xpath("//tbody//tr[6]//td[1]//a[1]")).click();

        try {
            Thread.sleep(4500);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }



        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Thread.sleep(3000);
        //pick a class  & Assign prerequisite  // put new user as attendee
        /*JavascriptExecutor js01 = (JavascriptExecutor) driver;
        WebElement courseAdmin01 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js01.executeScript("arguments[0].click()", courseAdmin01);

        Thread.sleep(3500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(5500);




        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("EHS-3500");
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//a[@class='editAction']")).click();
        Thread.sleep(5500);
        driver.findElement(By.xpath("//tbody//tr[1]//td[7]//a[1]")).click();
        Thread.sleep(5500);
        driver.findElement(By.xpath("//body[@onload='init();']/div[@id='wrapper']/div[@id='content']/form[@id='EHSForm']/div[@class='row']/div[@class='col-sm-12']/div[@class='panel-heading col-xs-12 mt-4 text-right']/input[1]")).click();
        Thread.sleep(5500);
        driver.findElement(By.xpath("//input[@name='badgeNo']")).sendKeys(userid);
        driver.findElement(By.xpath("//input[@value='Search']")).click();
        Thread.sleep(5500);
        driver.findElement(By.xpath("//tr//td[1]//a[1]")).click();
        Thread.sleep(5500);*/


        // sign out current user
        driver.findElement(By.xpath("//a[@id='lightbox']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
        Thread.sleep(2000);
        //Sign in the user that never completed the prerequisite & try the prerequisite
        driver.findElement(By.id("login_login_id")).sendKeys(userid);
        driver.findElement(By.id("login_password")).sendKeys(userid);
        driver.findElement(By.name("submit")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//span[contains(text(),'No thanks')]")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//input[@id='No_e12f18af182b98a6ec02af470818d89f']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@id='No_5394802c250b20fa96a6754de28aed31']")).click();
        driver.findElement(By.xpath("//input[@id='No_dfe0fc112f0d741fe30acdfd38aa669c']")).click();
        driver.findElement(By.xpath("//input[@id='No_cc79fac4584b10803beb7a7a61c39611']")).click();
        driver.findElement(By.xpath("//input[@id='No_ca5cdb2bd18473f189b312c6e939837a']")).click();
        driver.findElement(By.xpath("//input[@id='No_07bd927b3f4f4cec18863f3e00417c37']")).click();
        driver.findElement(By.xpath("//input[@id='No_46f358fe68654f6468a5f557a9ec7070']")).click();
        driver.findElement(By.xpath("//input[@id='No_e6a6c5871a396a96beceff1a212d9f27']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@id='DivResult']//a[contains(@class,'BlueButton BlueButtonFont submit')]")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//a[@id='lightboxOverLayClickFalse']")).click();
        Thread.sleep(6500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(4500);
        driver.findElement(By.id("srch_fld")).sendKeys("EHS-3500");
        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("viewglass")).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//input[@id='5ae1f1f07f81acd38400be131cad2def']")).click();
        Thread.sleep(2500);

        String enrollbtn =driver.findElement(By.xpath("//div[@class='errMsg']")).getAttribute("innerHTML");
        if(enrollbtn=="\n" +
                "                Required prerequisite course: 04a1awuKpJ. ")
        {
            System.out.println("Not able to register for the course without completing the prereq");
        }

        else {
            System.out.println("test fails, able to register for the course without completing the prereq");
        }
        //driver.findElement(By.xpath("//div[@class='roundType1']//div//input[@value='OK']")).click();

        Thread.sleep(6000);

        //got to the prerequisite and complete it
        driver.findElement(By.xpath("//a[contains(text(),'Courses')]")).click();

        driver.findElement(By.id("srch_fld")).sendKeys("04a1awuKpJ"); //checklist name on localhost

        driver.findElement(By.name("searchButton")).click();

        Thread.sleep(3000);



        //Click on the Enroll button
        driver.findElement(By.xpath("//*[@id='msg_headd96dd51c141c3643425434a4898d8e09']/table/tbody/tr/td[5]/img")).click();

        Thread.sleep(5000);


        //Click on 'Default-English' Language option
        driver.findElement(By.id("crselink1")).click();


        Thread.sleep(3000);


        //Clicking the Radio Button
        //driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();

//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();


        Thread.sleep(2000);

        //Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
        driver.findElement(By.cssSelector("input[type='checkbox'][value='13c496ea53471d3bce0c72e9b4a63b66']")).click(); // Cat

        driver.findElement(By.cssSelector("input[type='checkbox'][value='4bd53adf7e6118d7c8625537fdd551c6']")).click(); // Elephant

        driver.findElement(By.cssSelector("input[type='checkbox'][value='9dadf671b25ecf8ba926941141405bf9']")).click(); // Fox


        Thread.sleep(2000);


        //Entering value in text box for Question 4

        driver.findElement(By.xpath("//*[@id='78b39db9d6c9f3801f1792d2073b9c32']")).clear();

        driver.findElement(By.xpath("//*[@id='78b39db9d6c9f3801f1792d2073b9c32']")).sendKeys("40");

        Thread.sleep(5000);


        driver.findElement(By.cssSelector("input[type='button'][value='Submit']")).click();
//		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(5000);

        //After completion of prerequisite, try "EHS-3500" again.
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(4500);
        driver.findElement(By.id("srch_fld")).sendKeys("EHS-3500");
        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(1500);
        driver.findElement(By.className("viewglass")).click();
        Thread.sleep(3500);
        driver.findElement(By.xpath("//input[@id='5ae1f1f07f81acd38400be131cad2def']")).click();
        Thread.sleep(2500);
        String enrollbtn1 =driver.findElement(By.xpath("//div[@class='errMsg']")).getAttribute("innerHTML");
        if(enrollbtn1=="\n" +
                "                You have successfully enrolled the class. ")
        {
            System.out.println("Test is successful");
        }

        else {
            System.out.println("test fails, able to register for the course without completing the prereq");
        }






    }
}
