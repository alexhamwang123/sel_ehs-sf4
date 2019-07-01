package com.ClassroomCourseManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class ClassroomSPGRCCompletion {
    public void ClassroomSPGRCCompletion() throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String normuser = prop.getProperty("testnormuser");
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);

        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click()", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search_result\"]/div/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/a")).click();
        String courseId = generator.generate(10);
        System.out.println(courseId);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='detailIsSPG']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='detailIsSPG']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='isSPG']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='isSPG']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("detailCourseTitle")));
        Thread.sleep(1000);
        driver.findElement(By.name("detailCourseTitle")).sendKeys("test classroom course");
        new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal & Refresh");
        new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Save']")));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='addClass']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='addClass']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("site_radio")));
        Thread.sleep(1000);
        driver.findElement(By.id("site_radio")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("selectBtnSite")));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("searchName")));
        Thread.sleep(1000);
        driver.findElement(By.id("searchName")).sendKeys("SCV");
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id=\"Deptdirectreport\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(1500);
        String building = generator.generate(15);
        driver.findElement(By.name("detailClassBuilding")).sendKeys(building);
        driver.findElement(By.name("detailClassRoom")).sendKeys("room01");
        driver.findElement(By.name("detailClassMaxSize")).sendKeys("1");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd")).click();
        Thread.sleep(1500);
        js.executeScript("document.getElementById('TimeAdd_datepicker').value='Dec 25,2030'");
        new Select(driver.findElement(By.name("detailClassStartHourSelect"))).selectByVisibleText("06");
        new Select(driver.findElement(By.name("detailClassStartMinuteSelect"))).selectByVisibleText("45");
        new Select(driver.findElement(By.id("detailClassDuration"))).selectByVisibleText("13");
        Thread.sleep(1500);
        driver.findElement(By.id("TimeAdd_Save")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveClassCourse")));
        Thread.sleep(2000);
        driver.findElement(By.id("saveClassCourse")).click();

        //Create A new RC to add the SPG Classroom
        //Clicking on EHS Admin
        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click();",ele);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'RC Management')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'RC Management')]")).click();

        //Click on the 'Create Risk Category' button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Create Risk Category']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create Risk Category']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RandomStringGenerator generator1 = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator1.generate(10);
        //Enter the Risk Category Name
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailJobClassName"))));
        Thread.sleep(1000);
        driver.findElement(By.name("detailJobClassName")).sendKeys(title);

        //Enter the Risk Category Abbr Name
        driver.findElement(By.name("detailJobClassAbbrName")).sendKeys("RCR");

        //Enter the description
        driver.findElement(By.name("detailJobClassDescr")).sendKeys("this is the description!");

        Thread.sleep(1500);
        driver.findElement(By.id("selectBtnCreMaA")).click();
        Thread.sleep(2500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("badgeNo"))));
        Thread.sleep(1000);
        driver.findElement(By.name("badgeNo")).sendKeys(username);
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(2500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a[href*='selectCourseManager']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[href*='selectCourseManager']")).click();
        Thread.sleep(7500);


        driver.findElement(By.xpath("//*[@id=\"saveJC\"]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("addCourse"))));
        Thread.sleep(2000);
        driver.findElement(By.id("addCourse")).click();
        Thread.sleep(4500);
        driver.findElement(By.xpath("//*[@id=\"FirstForm\"]/div/div[1]/div/label/input")).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);

        driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();
        Thread.sleep(1000);
        String value = driver.findElement(By.cssSelector("input[type='checkbox'][name='addCourse[]']")).getAttribute("value");
        Thread.sleep(500);
        System.out.println(value);
        driver.findElement(By.id(value)).click();
        Thread.sleep(500);
        //WebElement add = driver.findElement(By.cssSelector("input[type='button'][value='Add']"));
        WebElement add = driver.findElement(By.xpath("//*[@id=\"paging_result\"]/input[6]"));
        js.executeScript("arguments[0].click();", add);

        Thread.sleep(3500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();


        //Assign the RC
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"userAssign\"]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"userAssign\"]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"FirstForm\"]/div/div[1]/div/input")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"userRecord\"]/tbody/tr/td[1]/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"userRecord\"]/tbody/tr/td[1]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='search_result']//input[contains(@class,'btn-primary')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[value='Save']")).click();

        //Click Home
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Home"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Home")).click();

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
        //CLick enroll Btn
        Thread.sleep(3000);
        WebElement EnrollBtn01= driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']"));
        js.executeScript("arguments[0].click();",EnrollBtn01);

        //click Close
        Thread.sleep(5000);
        WebElement CloseBtn01= driver.findElement(By.xpath("//button[@class='close']"));
        js.executeScript("arguments[0].click();",CloseBtn01);
        Thread.sleep(5000);

        //Click Course Admin
        WebElement CourseAdmin01=driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", CourseAdmin01);

        //Click Classroom Course Management
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Classroom Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Classroom Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='search[is_spg]']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='search[is_spg]']")).click();

        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);
        Thread.sleep(1000);
        //Clilck Go Btn
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(courseId)));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText(courseId)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"crseRecord\"]/tbody/tr/td[7]/a[1]")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"crseRecord\"]/tbody/tr/td[7]/a[1]")).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.id("chkall")));
        Thread.sleep(2000);
        driver.findElement(By.id("chkall")).click();

        Thread.sleep(3000);
        new Select(driver.findElement(By.name("selectStatus"))).selectByVisibleText("Mark Completed");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='OK']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='OK']")).click();
        Thread.sleep(3500);


        WebElement ManagerReports = driver.findElement(By.xpath("//a[contains(text(),'Manager Reports')]"));
        js.executeScript("arguments[0].click();", ManagerReports);

        //Click Checklist Completion Report
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[3]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[3]")).click();

        //Click Checklist Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[3]/div/div/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[3]/div/div/button")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input")).sendKeys(courseId);

        if(!driver.getPageSource().contains(courseId)){
            System.out.println("SPG Course is not searchable in Checklist Completion Reports");
        }
        else{
            Assert.fail("SPG Course is  searchable in Checklist Completion Reports");
        }
        driver.findElement(By.xpath("//*[@id=\"__BVID__13___BV_modal_header_\"]/button")).click();
        Thread.sleep(1000);

        //Search the Course in EHS Reports
        WebElement EHSReports = driver.findElement(By.xpath("//a[contains(text(),'EHS Reports')]"));
        js.executeScript("arguments[0].click();", EHSReports);

        //Click Course-Select Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='courseRadio']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='courseRadio']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='selectCourseDisabled']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='selectCourseDisabled']")).click();

        //Search the Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/div[9]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/div[9]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/div[9]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(courseId);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='is_spg']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='is_spg']")).click();


        //Click Btn Go
        driver.findElement(By.xpath("//*[@id=\"applesearch\"]/div/div[5]/input")).click();
        Thread.sleep(3000);
        if(driver.getPageSource().contains(courseId)){
            System.out.println("SPG Course is searchable in EHS Reports");
        }
        else{
            Assert.fail("SPG Course is not searchable in EHS Reports");
        }

        Thread.sleep(1000);
        //Click Close Btn
        driver.findElement(By.xpath("//*[@id=\"fancybox-close\"]")).click();
        Thread.sleep(1000);
        driver.quit();

    }

    }
