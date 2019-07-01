package com.ChecklistManagement;



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

        import java.awt.event.KeyEvent;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.util.NoSuchElementException;
        import java.util.Properties;
        import java.util.concurrent.TimeUnit;

        import static org.apache.commons.text.CharacterPredicates.DIGITS;
        import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Test

public class ChecklistPrereqComplete {
    public void CompletetheprerequisitebeforetakingChecklist() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        File file = new File(System.getProperty("user.dir") + "/PasswordFileEHS.properties");

        FileInputStream inStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inStream);
        String urladdr = prop.getProperty("url");

        driver.get(urladdr);

        driver.manage().window().maximize();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(4500);
        //go to onlinecourseManagement


        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement courseAdmin = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
        js.executeScript("arguments[0].click()", courseAdmin);


        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[2]")).click();
        Thread.sleep(5500);

        //pick a class  & Assign prerequisite
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='secondmenu']//input[@id='srch_fld']")).sendKeys("1bLkHwGarU");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Go']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Go']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("editAction"))));
        Thread.sleep(1000);
        driver.findElement(By.className("editAction")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))));
        Thread.sleep(1000);
        new Select(driver.findElement(By.name("detailCoursePrerequisitesCourse1"))).selectByVisibleText("kimi-online-006 - kimi-online-006");
        Thread.sleep(3500);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

        // make a normal user that never completed the prerequisite
        //Clicking on 'User Admin'
        Thread.sleep(3000);
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Create User']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='button'][value='Create User']")).click();


        // Enter the First Name of the user that you wish to create
        String userid = driver.findElement(By.id("detailBadgeNumber")).getAttribute("value");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("detailFirstName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("detailFirstName")).sendKeys(userid);

        // Enter the Last Name of the user that you wish to create
        driver.findElement(By.id("detailLastName")).sendKeys(userid);

        // Click on 'Select' for Site
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("selectBtnSite"))));
        Thread.sleep(1000);
        driver.findElement(By.id("selectBtnSite")).click();

        // Enter the search value as "SCV"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("searchName"))));
        Thread.sleep(1000);
        driver.findElement(By.id("searchName")).sendKeys("SCV");

        // Click on Search
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='submit'][value='Search']"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();


        // Click on 'SCV' from the search results
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr/td[2]/a")).click();


        // Enter the email of the user that you wish to create
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("detailEmailAddress"))));
        Thread.sleep(1000);
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

        // Click on 'Save' button
        driver.findElement(By.xpath("//input[@value='Save']")).click();

        // sign out current user
        Thread.sleep(3000);
        WebElement Logout0=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        js1.executeScript("arguments[0].click()", Logout0);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"top-menu\"]/div/a/h1/img")).click();

       // driver.findElement(By.cssSelector("input[type='submit'][value='OK']")).click();
        //Thread.sleep(2000);
        //Sign in the user that never completed the prerequisite & try the prerequisite
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("username"))));
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys(userid);
        driver.findElement(By.id("password")).sendKeys(userid);

        driver.findElement(By.xpath("//button[@type='submit']")).click();


        Thread.sleep(4500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("welcomeShowRS"))));
        Thread.sleep(1000);
        driver.findElement(By.id("welcomeShowRS")).click();
        Thread.sleep(4500);

        driver.findElement(By.name("question[4586]")).click();
        driver.findElement(By.name("question[4127]")).click();
        driver.findElement(By.name("question[1361]")).click();
        driver.findElement(By.name("question[4562]")).click();
        driver.findElement(By.name("question[4225]")).click();
        driver.findElement(By.name("question[4193]")).click();
        driver.findElement(By.name("question[145]")).click();
        driver.findElement(By.name("question[1164]")).click();
        driver.findElement(By.name("question[4676]")).click();

        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"rs-modal1___BV_modal_footer_\"]/div/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("annContinue"))));
        Thread.sleep(1000);
        driver.findElement(By.id("annContinue")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        //Search the Course
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1bLkHwGarU");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
         driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();

        Thread.sleep(3500);
        String working = "";
        try {
            working = driver.findElement(By.xpath("//*[@id=\"courses\"]/div/div/div[2]/div[1]/table/tbody/tr[2]/td/div/div/div/div[1]/div")).getAttribute("innerHTML");
        } catch (NoSuchElementException e) {
            Assert.fail("was able to register for the course without completing the prereq");
        }
        System.out.println(working);

        if (!working.contains("kimi-online-006")) {
            Assert.fail("user was ale to register for the course without completing the prereq");
        }

        Thread.sleep(2000);

        //got to the prerequisite and complete it

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kimi-online-006"); //checklist name on localhost


        Thread.sleep(3000);



        //Click on the Enroll button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn rounded-circle btn-outline-success border-0']")).click();

        Thread.sleep(3000);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        //Click on 'Default-English' Language option
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"))));
        Thread.sleep(1000);
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        WebElement Language_English=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
        js2.executeScript("arguments[0].click();",Language_English);


        Thread.sleep(3000);


        //Clicking the Radio Button
        //driver.findElement(By.xpath("//*[@name='cf29c8de74fb0d0f2846573a541e8737'][@value='N']")).click();

//		driver.findElement(By.cssSelector("input[type='radio'][value='Y']")).click();


        Thread.sleep(2000);

        //Clicking on 'Which of these are animals' options - Cat, Elephant, Fox
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='10039']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='10039']")).click(); // Cat

        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"c-body\"]/div/div[2]/div[2]/button"))));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"c-body\"]/div/div[2]/div[2]/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'OK')]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='btn btn-primary btn float-right']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn float-right']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]"))));
        Thread.sleep(1000);
        WebElement Exit=driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button[2]"));
        js2.executeScript("arguments[0].click();",Exit);
        Thread.sleep(2000);
        //After completion of prerequisite, try "1bLkHwGarU" again.
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Courses")).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@type='text']"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1bLkHwGarU");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"))));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[1]//td[5]//button[1]")).click();
        Thread.sleep(1500);
        String handle= driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {

            if(!winHandle.equals(handle)){
                driver.switchTo().window(winHandle);
                WebElement Successconfrimbtn=driver.findElement(By.xpath("//button[contains(text(),'Default - English')]"));
                if(Successconfrimbtn.isDisplayed()){
                    System.out.println("Test is successful");
                }

            }
        }


        driver.quit();






    }
}
