package com.EHS.ClassroomCourseManagement;

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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test
public class NoShowNotPassedCompleted {
    public void NoShowNotPassedCompleted() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://twn:WrongAdeeDow2-@demo.accentrixus.com:8330");

        driver.manage().window().maximize();

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("login_login_id")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();

        Thread.sleep(4500);
        WebElement courseAdmin = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[3]/a"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        Thread.sleep(3500);
        String courseId = generator.generate(10);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("Regular");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("addClass")).click();
        Thread.sleep(2500);
        driver.findElement(By.id("site_radio")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("selectBtnSite")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("searchName")).sendKeys("SCV");
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("1");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(1500);
        Calendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH);
        String smonth = "";
        String cmonth = "";

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        day --;
        String sday = "";
        if(day < 10) {
            sday = "0" + day;
        } else { sday = "" + day; }

        int year = calendar.get(Calendar.YEAR);
        System.out.println(month);
        switch (month) {
            case 0: smonth = "Jan ";break;
            case 1: smonth = "Feb ";break;
            case 2: smonth = "Mar ";break;
            case 3: smonth = "Apr ";break;
            case 4: smonth = "May ";break;
            case 5: smonth = "Jun ";break;
            case 6: smonth = "Jul ";break;
            case 7: smonth = "Aug ";break;
            case 8: smonth = "Sep ";break;
            case 9: smonth = "Oct ";break;
            case 10: smonth = "Nov ";break;
            case 11: smonth = "Dec ";break;
        }

        month ++;
        if(month < 10) {
            cmonth = "0" + month;
        } else { cmonth = "" + month; }


        js.executeScript("document.getElementById('TimeAdd_datepicker').value='" + smonth + sday + "," + year + "'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd_Save")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("saveClassCourse")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"FirstForm\"]/div[4]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"crseRecord\"]/tbody/tr/td[7]/a[1]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("selectTeamMemberButton1")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("badgeNo")).sendKeys(username);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"directreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("emailme")).click();
        Thread.sleep(1500);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark No Show");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(1500);
        WebElement lilok = driver.findElement(By.cssSelector("input[type='button'][value='ok']"));
        js.executeScript("arguments[0].click();", lilok);
        Thread.sleep(2000);
        String noshow = "";
        try {
            noshow = driver.findElement(By.xpath("//*[@id=\"accordion_AttendeeList_Table\"]/tbody/tr/td[6]")).getAttribute("innerHTML").substring(45,52);
        } catch (IndexOutOfBoundsException e) {

        }
        if(!noshow.equals("No Show")) {
            Assert.fail("user is not marked as no show");
        }
        driver.findElement(By.name("emailme")).click();
        Thread.sleep(1500);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Not Passed");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(1500);
        WebElement lilok0 = driver.findElement(By.cssSelector("input[type='button'][value='ok']"));
        js.executeScript("arguments[0].click();", lilok0);
        Thread.sleep(2000);
        String notPassed = "";
        try {
            notPassed = driver.findElement(By.xpath("//*[@id=\"accordion_AttendeeList_Table\"]/tbody/tr/td[6]")).getAttribute("innerHTML").substring(45,55);
        } catch (IndexOutOfBoundsException e) {

        }
        if(!notPassed.equals("Not Passed")) {
            Assert.fail("user is not marked as not passed");
        }
        Thread.sleep(1500);

        driver.findElement(By.name("emailme")).click();
        Thread.sleep(1500);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Completed");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(1500);
        WebElement lilok1 = driver.findElement(By.cssSelector("input[type='button'][value='ok']"));
        js.executeScript("arguments[0].click();", lilok1);
        Thread.sleep(2000);
        String complete = "";
        try {
            complete = driver.findElement(By.xpath("//*[@id=\"accordion_AttendeeList_Table\"]/tbody/tr/td[6]")).getAttribute("innerHTML").substring(45,48);
        } catch (IndexOutOfBoundsException e) {

        }

        if (!complete.equals("yes")) {
            Assert.fail("user is not marked as completed");
        }
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText("My History")).click();
        Thread.sleep(1500);

        js.executeScript("document.getElementById('dateFrom').value='" + cmonth + "/" + sday + "/" + year + "'");
        js.executeScript("document.getElementById('dateTo').value='" + cmonth + "/" + sday + "/" + year + "'");
        Thread.sleep(1000);
        driver.findElement(By.id("searchHistory")).click();
        Thread.sleep(1500);
        driver.findElement(By.partialLinkText(courseId)).click();
        Thread.sleep(1000);
        if (!driver.getPageSource().contains("Completed")) {
            Assert.fail("the course did not show up as completed in my history");
        }

        driver.findElement(By.partialLinkText("Reports")).click();
        Thread.sleep(1500);
        new Select(driver.findElement(By.id("selectedCourseType"))).selectByVisibleText("Classroom");

        js.executeScript("document.getElementById('dateFrom').value='" + cmonth + "/" + sday + "/" + year + "'");
        js.executeScript("document.getElementById('dateTo').value='" + cmonth + "/" + sday + "/" + year + "'");
        Thread.sleep(1000);
        driver.findElement(By.id("Button_Go")).click();
        Thread.sleep(1500);
        if (!driver.getPageSource().contains(courseId)) {
            Assert.fail("the course does not show as completed in my training report");
        }
        Thread.sleep(2000);



        driver.quit();

    }
}