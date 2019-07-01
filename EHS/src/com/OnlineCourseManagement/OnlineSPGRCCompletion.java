package com.OnlineCourseManagement;

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
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
@Test
public class OnlineSPGRCCompletion {
    public void OnlineSPGRCCompletion() throws IOException, InterruptedException{


        int number;
        int integer;
        Random rand = new Random();
        number= rand.nextInt(10000);
        integer=rand.nextInt(10000);
        String courseId= integer+"testforscript"+number;

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

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);
        Thread.sleep(1500);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Online Course Management"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Online Course Management")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailCourseNo"))));
        Thread.sleep(1000);
        driver.findElement(By.name("detailCourseNo")).sendKeys(courseId);

        WebElement element1 = driver.findElement(By.xpath("//select[@name='detailCourseCategory']"));
        Select oSelect = new Select(element1);
        oSelect.selectByValue("37");
        WebElement element2 = driver.findElement(By.xpath("//select[@id='detailCourseFulfillType']"));
        Select iSelect = new Select(element2);
        iSelect.selectByValue("1");
        WebElement element3 = driver.findElement(By.xpath("//select[@id='detailCourseExpiration']"));
        Select aSelect = new Select(element3);
        aSelect.selectByValue("0");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='detailIsSPG']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='detailIsSPG']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='isSPG']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='isSPG']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Edit']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Edit']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='detailCourseTitle']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='detailCourseTitle']")).sendKeys(integer+"testforscript");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='btn_edit_content']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='btn_edit_content']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Create Page']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Create Page']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"2\"]/img"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"2\"]/img")).click();
        driver.findElement(By.xpath("//input[@id='courseContentTitle']")).sendKeys("testforscript"+number);
        driver.findElement(By.xpath("//div[@class='padded form-horizontal mt-3']//div[5]//input[3]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fancyConfirm_ok"))));
        Thread.sleep(1000);
        driver.findElement(By.id("fancyConfirm_ok")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='addQBtn']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='addQBtn']")).click();


        Thread.sleep(3000);
        js.executeScript("tinyMCE.activeEditor.setContent('this is a test only ')");

        driver.findElement(By.xpath("//input[@id='courseQuizAnswer1']")).sendKeys("Correct Answer");
        driver.findElement(By.xpath("//input[@id='courseQuizAnswer2']")).sendKeys("Incorrect Answer");
        driver.findElement(By.xpath("//input[@name='courseQuizCorrectAnswer']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@name='courseQuizSourcePage']")).sendKeys("1");
        driver.findElement(By.xpath("//div[@class='buttonSet mt-4 a-right']//input[2]")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='fancyConfirm_ok']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='fancyConfirm_ok']")).click();

        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Back']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Back']")).click();
        Thread.sleep(4000);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='langIsViewable']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='langIsViewable']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='detailCourseIsActive']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='detailCourseIsActive']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        //Create A new RC to add the SPG Online Course
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
        Thread.sleep(1000);
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='list-group-item list-group-item-action']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='list-group-item list-group-item-action']")).click();

        //Click the Answer
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ol[1]/li[1]/div[2]/label[1]/input[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ol[1]/li[1]/div[2]/label[1]/input[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn btn-primary']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'OK')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        //Clilck Exit
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn btn-primary btn float-right']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn float-right']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn btn-primary']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        for(String winhandle: driver.getWindowHandles()){
            driver.switchTo().window(winhandle);
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

        //Click Course Completion Report
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"sub-menu\"]/div/a[2]")).click();

        //Click Course Btn
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//label[contains(text(),'Course')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(),'Course')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[4]/div[1]/div[1]/button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[4]/div[1]/div[1]/button[1]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='Type to filter result']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Type to filter result']")).sendKeys(courseId);

        if(!driver.getPageSource().contains(courseId)){
            System.out.println("SPG Course is  searchable in Course Completion Reports");
        }
        else{
            Assert.fail("SPG Course is not searchable in Course Completion Reports");
        }

        driver.findElement(By.xpath("//button[@class='close shadow-sm']")).click();

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

        driver.quit();
    }


    }
