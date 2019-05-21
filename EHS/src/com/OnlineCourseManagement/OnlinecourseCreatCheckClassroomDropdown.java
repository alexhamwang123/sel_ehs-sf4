package com.OnlineCourseManagement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
@Test
public class OnlinecourseCreatCheckClassroomDropdown {
    public void OnlinecourseCreatandCheckClassroomDropdown() throws IOException, InterruptedException{
        int number;
        int integer;
        Random rand = new Random();
        number= rand.nextInt(10000);
        integer=rand.nextInt(10000);

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

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
        driver.findElement(By.linkText("Online Course Management")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("detailCourseNo")).sendKeys(integer+"testforscript"+number);

        WebElement element1 = driver.findElement(By.xpath("//select[@name='detailCourseCategory']"));
        Select oSelect = new Select(element1);
        oSelect.selectByValue("37");
        WebElement element2 = driver.findElement(By.xpath("//select[@id='detailCourseFulfillType']"));
        Select iSelect = new Select(element2);
        iSelect.selectByValue("1");
        WebElement element3 = driver.findElement(By.xpath("//select[@id='detailCourseExpiration']"));
        Select aSelect = new Select(element3);
        aSelect.selectByValue("6");
        WebElement element4 = driver.findElement(By.xpath("//select[@id='detailCourseGracePeriod']"));
        Select bSelect = new Select(element4);
        bSelect.selectByValue("30");

        driver.findElement(By.xpath("//input[@value='Save']")).click();

        Thread.sleep(14000);
        driver.findElement(By.xpath("//input[@value='Edit']")).click();
        driver.findElement(By.name("detailCourseTitle")).sendKeys("testonly");
        driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
        driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("btn_edit_content")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create Page']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"question_sortable\"]/tr/td[3]/button")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"3\"]/img")).click();
        Thread.sleep(5500);
        driver.findElement(By.id("courseContentTitle")).sendKeys("this is the title");
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(4500);
        driver.findElement(By.id("addQBtn")).click();
        Thread.sleep(4000);
        js.executeScript("tinyMCE.activeEditor.setContent('this is the test question!')");
        Thread.sleep(3500);
        driver.findElement(By.id("courseQuizAnswer1")).sendKeys("this is the correct answer");
        driver.findElement(By.id("courseQuizAnswer2")).sendKeys("no, this is the correct answer");
        driver.findElement(By.name("courseQuizCorrectAnswer")).sendKeys("2");
        driver.findElement(By.name("courseQuizSourcePage")).sendKeys("1");
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save & Back']")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("fancyConfirm_ok")).click();
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(7500);
//        driver.findElement(By.id("fancyConfirm_ok")).click();
//        Thread.sleep(5500);
//        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
//        Thread.sleep(5500);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();
        Thread.sleep(3500);
        driver.findElement(By.name("langIsViewable")).click();
        Thread.sleep(5500);
        driver.findElement(By.id("detailCourseIsActive")).click();
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
        Thread.sleep(3000);

        JavascriptExecutor js1 = (JavascriptExecutor)driver;
        WebElement courseAdmin1 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js1.executeScript("arguments[0].click()", courseAdmin1);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'Classroom Course Management')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@class='btn btn-primary btn-sm float-right editAction']")).click();
        Thread.sleep(3000);
        WebElement element6=driver.findElement(By.xpath("//select[@id='detailCourseExpiration']"));
        Select gselect= new Select(element6);
        gselect.selectByValue("12");
        WebElement element7=driver.findElement(By.xpath("//select[@id='detailCourseRefreshCourse3']"));
        Select hselect= new Select(element7);
        hselect.selectByVisibleText(integer+"testforscript"+number+" - "+integer+"testforscript");

        Thread.sleep(5000);
        driver.quit();


    }
}
