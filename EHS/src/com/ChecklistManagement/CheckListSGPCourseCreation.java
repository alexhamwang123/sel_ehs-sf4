package com.ChecklistManagement;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class CheckListSGPCourseCreation {
    public void CheckListSGPCourseCreation() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait= new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");

        FileInputStream inStream=new FileInputStream(file);
        Properties prop=new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");
        driver.get(urladdr);
        //driver.manage().window().maximize();
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();;
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();


        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].click();", courseAdmin);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(3500);
        String courseId = generator.generate(10);
        System.out.println(courseId);
        driver.findElement(By.name("detailCheckListCode")).sendKeys(courseId);
        new Select(driver.findElement(By.id("detailCategoryType"))).selectByVisibleText("EHS - Ergonomics");
        new Select(driver.findElement(By.id("detailCourseType"))).selectByVisibleText("Checklist");
        new Select(driver.findElement(By.id("detailCourseExpiration"))).selectByVisibleText("Never Expires");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
        Thread.sleep(1000);
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='button'][value='Edit']")));
        Thread.sleep(2000);
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
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
        Thread.sleep(1000);
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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Management")).click();
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);

        //Click SPG Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='search[is_spg]']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='search[is_spg]']")).click();

        //Click Go btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input")).click();

        Thread.sleep(3000);

        if(driver.getPageSource().contains(courseId)){
            System.out.println("SPG Course is searchable");
        }
        else{
            Assert.fail("SPG Course is not searchable");
        }


        //Create A new RC to add the SPG Checklist
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
        Thread.sleep(2500);




        //Click on the 'Save' button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("saveJC"))));
        Thread.sleep(2000);
        driver.findElement(By.id("saveJC")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("addCourse"))));
        Thread.sleep(1000);
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


        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //Click English
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"course-start\"]/div/div/div/div[2]/div/button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"course-start\"]/div/div/div/div[2]/div/button[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div/div/div[1]/div/button[3]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"ch-body\"]/div/div/div/div/div[1]/div/button[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        //Search the Course  Again
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);

        if(!driver.getPageSource().contains(courseId)){
            System.out.println("SPG Course is not searchable in Courses");
        }
        else{
            Assert.fail("SPG Course is  searchable in Courses");
        }

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

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"modal-result\"]/div[1]/div[1]/div/input"))));
        Thread.sleep(1000);
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


        //Create new user and  Log in the newly Created User
        WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'User Admin')]"));
        js.executeScript("arguments[0].click();",ele1);


        // Click on Create User
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Create User']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();

        //自動會有detailBadgeNumber ，也就是我們要用的bdgeeNumber ID
        String id = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");
        System.out.println("id is " + id);

        // Enter the First Name of the user that you wish to create
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("detailFirstName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("detailFirstName")).sendKeys(id);

        // Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("detailLastName")).sendKeys(id);

        // Click on 'Select' for Site
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("selectBtnSite"))));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        // Enter the search value as "SCV"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("searchName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("searchName")).sendKeys("SCV");

        // Click on Search
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();


        // Click on 'SCV' from the search results
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a"))));
        Thread.sleep(1000);
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

        js1.executeScript("window.scrollBy(0,850)", "");

        driver.findElement(By.xpath("//label[contains(text(),'System Admin')]")).click();
        // Click on 'Save' button
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        Thread.sleep(2000);

        WebElement Logout=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js.executeScript("arguments[0].click()",Logout);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("username")).sendKeys(id);
        driver.findElement(By.id("password")).sendKeys(id);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4500);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("welcomeShowRS")));
        Thread.sleep(1500);
        driver.findElement(By.id("welcomeShowRS")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("question[4586]")).click();
        driver.findElement(By.name("question[1361]")).click();
        driver.findElement(By.name("question[4562]")).click();
        driver.findElement(By.name("question[4225]")).click();
        driver.findElement(By.name("question[4127]")).click();
        driver.findElement(By.name("question[4193]")).click();
        driver.findElement(By.name("question[145]")).click();
        driver.findElement(By.name("question[1164]")).click();
        driver.findElement(By.name("question[4676]")).click();
        driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("annContinue"))));
        Thread.sleep(1000);
        driver.findElement(By.id("annContinue")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Home')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        Thread.sleep(3000);
        if(!driver.getPageSource().contains(title)){
            System.out.println("SPG Course RC is searchable in Home");
        }
        else{
            Assert.fail("SPG Course RC is not searchable in Home");
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='input-group input-group-sm']//input[@type='text']")).sendKeys(courseId);
        Thread.sleep(3000);
        if(!driver.getPageSource().contains(courseId)){
            System.out.println("SPG Course for new User is searchable in Courses");
        }
        else{
            Assert.fail("SPG Course for new User is not searchable in Courses");
        }

        //Go to Course Admin
        WebElement courseAdmin01 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        JavascriptExecutor js003 = (JavascriptExecutor)driver;
        js003.executeScript("arguments[0].click();", courseAdmin01);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Checklist Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Checklist Management")).click();
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys(courseId);

        //Click SPG Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='search[is_spg]']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='search[is_spg]']")).click();

        //Click Go btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"EHSForm\"]/div[1]/div/input")).click();

        if(!driver.getPageSource().contains(courseId)){
            System.out.println("SPG Course for new User is searchable in Checklist Management");
        }
        else{
            Assert.fail("SPG Course for new User is not searchable in  Checklist Management");
        }

        driver.quit();

    }


    }
