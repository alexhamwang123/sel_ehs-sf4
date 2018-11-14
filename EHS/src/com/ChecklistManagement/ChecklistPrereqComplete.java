package com.ChecklistManagement;


import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
@Test(priority=10)
//@Test(dependsOnGroups = "ehs1",priority=10)
public class ChecklistPrereqComplete {
    public void ChecklistPrereqComplete() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        //driver.manage().window().maximize();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(3500);
        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("Survey_Only_New");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        new Select(driver.findElement(By.id("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.id("detailCoursePrerequisitesCourse1"))).selectByVisibleText("EHS-4100 - Building Evacuation Training");

        Thread.sleep(3000);
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
        driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
        driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
        driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("createContent")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        Thread.sleep(7000);
        driver.findElement(By.id("langIsViewable")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("detailIsActive")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
        Thread.sleep(1500);
        driver.findElement(By.name("searchButton")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//img[@class='viewglass']")).click();
        Thread.sleep(1500);
        String working = "";
        try {
            working = driver.findElement(By.xpath("//*[@id=\"errorMsg_data\"]")).getAttribute("innerHTML");
        } catch (NoSuchElementException e) {
            Assert.fail("was able to register for the course without completing the prereq");
        }

        if(!working.equals("Required prerequisite course:EHS-4100")) {
            Assert.fail("user was not able to register for the course without completing the prereq");
        }
        driver.quit();
        //we have to makr it completed as this user's via UI.

        //Let it search first. then click the result.
//        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
//        Thread.sleep(1000);
/*        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
        Thread.sleep(1500);

        driver.findElement(By.className("editAction")).click();
        Thread.sleep(3500);

//        driver.findElement(By.name("searchButton")).click();
//        driver.findElement(By.name("searchButton")).click();

        Thread.sleep(2000);
        driver.quit();*/
//        Thread.sleep(1000);
//        driver.findElement(By.id("addClass")).click();
//        Thread.sleep(2500);
//        driver.findElement(By.id("site_radio")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("selectBtnSite")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("searchName")).sendKeys("SCV");
//        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.xpath("//*[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
//        Thread.sleep(1500);
//        String building = generator.generate(15);
//        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
//        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
//        driver.findElement(By.name("detailClassMaxSize")).sendKeys("5");
//        Thread.sleep(1500);
//        driver.findElement(By.id("TimeAdd")).click();
//        Thread.sleep(1500);
//        js.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 25,2030'");
//        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
//        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
//        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
//        Thread.sleep(1500);
//        driver.findElement(By.id("TimeAdd_Save")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("saveClassCourse")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.partialLinkText("Courses")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
//        driver.findElement(By.name("searchButton")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.className("viewglass")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Enroll']")).click();
//        Thread.sleep(1500);
//        WebElement courseAdmin0 = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
//        js.executeScript("arguments[0].click()", courseAdmin0);
//        Thread.sleep(1500);
//        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
//        Thread.sleep(1500);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(By.id("secondmenu")));
//        actions.click();
//        actions.sendKeys(courseId);
//        actions.build().perform();
//
//        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.className("editAction")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[@id=\"crseRecord\"]/tbody/tr/td[7]/a[1]")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.name("emailme")).click();
//        Thread.sleep(500);
//        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Completed");
//        Thread.sleep(500);
//        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
//        Thread.sleep(1500);
//        WebElement ok = driver.findElement(By.id("ok"));
//        js.executeScript("arguments[0].click();", ok);
//
//        Thread.sleep(1500);
//        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div/div[1]/div/a[2]")).click();
//        Thread.sleep(1500);
//
//
//
//
//        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
//        Thread.sleep(3500);
//        String courseId0 = generator.generate(10);
//        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId0);
//        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("Survey_Only_New");
//        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
//        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
//        new Select(driver.findElement(By.id("detailCoursePrerequisitesCourse1"))).selectByVisibleText(courseId + " - test classroom course");
//        Thread.sleep(1000);
//        driver.findElement(By.id("saveBtn")).click();
//        Thread.sleep(3500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("detailCheckListTitle")).sendKeys("test checklist title");
//        driver.findElement(By.id("detailCheckListHeader")).sendKeys("test checklist header");
//        driver.findElement(By.id("detailCheckListDescription")).sendKeys("test checklist description");
//        driver.findElement(By.id("detailCheckListFooter")).sendKeys("test checklist footer");
//        driver.findElement(By.id("detailInstructionalText")).sendKeys("gratz dude");
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("createContent")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("input[type='submit'][value='Create']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("saveBtn")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("fancyConfirm_ok")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
//        Thread.sleep(3500);
//        driver.findElement(By.id("langIsViewable")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("detailIsActive")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.partialLinkText("Courses")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("srch_fld")).sendKeys(courseId);
//        driver.findElement(By.name("searchButton")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.className("viewglass")).click();
//        Thread.sleep(1500);
//        if (!driver.getPageSource().contains("Enrolled")) {
//            Assert.fail("the user was not enrolled after completing the prereq");
//        }
//        Thread.sleep(2000);
//        driver.quit();


    }

}
