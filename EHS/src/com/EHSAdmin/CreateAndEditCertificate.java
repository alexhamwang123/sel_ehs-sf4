package com.EHSAdmin;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateAndEditCertificate {

	public void CreateAndEditCertificate() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 35);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
		String testnormuser = prop.getProperty("testnormuser");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1500);


		//Clicking on EHS Admin
		WebElement EHSAdmin = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",EHSAdmin);

		Thread.sleep(2500);


		//Clicking on 'Certificate Management'
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Certificate Management"))));
		Thread.sleep(2500);
		driver.findElement(By.partialLinkText("Certificate Management")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clicking on 'Create Certificate'
		driver.findElement(By.cssSelector("input[type='button'][value='Create Certificate']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String title = generator.generate(10);

        // Entering the Certificate Name that you want for the certificate
		driver.findElement(By.name("detailCertName")).sendKeys(title);

		// Entering the Description that you want for your certificate
		driver.findElement(By.name("detailCertDescr")).sendKeys("this is the certificate description !");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clicking on the 'Save' button
		driver.findElement(By.cssSelector("input[type='submit'][value='Save']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// clicking the 'Back' button
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		driver.quit();

		// Searching for the lab name that you just created to show in the search result
//        Actions actions = new Actions(driver);
//		actions.moveToElement(driver.findElement(By.id("secondmenu")));
//		actions.click();
//		actions.sendKeys(title);
//		actions.build().perform();
//
//		driver.findElement(By.cssSelector("input[value='Go']")).click();
//		Thread.sleep(2500);
//		driver.findElement(By.className("editAction")).click();
//		Thread.sleep(1500);
//
//		driver.findElement(By.name("detailCertDescr")).clear();
//		driver.findElement(By.name("detailCertDescr")).sendKeys("im editing the certificate description");
//        Thread.sleep(500);
//        driver.findElement(By.cssSelector("input[value='Save']")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.cssSelector("input[value='Back']")).click();
//        Thread.sleep(1500);
//        actions.moveToElement(driver.findElement(By.id("secondmenu")));
//        actions.click();
//        actions.sendKeys(title);
//        actions.build().perform();
//
//        driver.findElement(By.cssSelector("input[value='Go']")).click();
//        Thread.sleep(2500);
//        driver.findElement(By.className("editAction")).click();
//        Thread.sleep(1500);
//        if(!driver.findElement(By.name("detailCertDescr")).getAttribute("innerHTML").equals("im editing the certificate description")) {
//            Assert.fail("something went wrong while editing the certificate");
//        }
        Thread.sleep(2000);
		//driver.findElement(By.className("editAction")).click()
		//certificate_checkbox
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		WebElement courseAdmin2 = driver.findElement(By.xpath("//a[contains(text(),'Course Admin')]"));
		js1.executeScript("arguments[0].click()", courseAdmin2);

		Thread.sleep(3500);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a[4]")).click();//Online Management.
		Thread.sleep(2700);
		driver.findElement(By.xpath("//*[@id=\"search_result\"]/div/button")).click();//Create Web Course
		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[14]/input[1]"))));
		Thread.sleep(1500);
		String courseId2 = generator.generate(10);
		//Send Course No.
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[1]/div[1]/input")).sendKeys(courseId2);
		new Select(driver.findElement(By.name("detailCourseCategory"))).selectByVisibleText("EHS - Ergonomics");//We have to make it via manually, just in case.
		new Select(driver.findElement(By.name("detailCourseFulfillType"))).selectByVisibleText("Normal");
		new Select(driver.findElement(By.name("detailCourseExpiration"))).selectByVisibleText("Never Expires");
		Thread.sleep(1600);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/form/div[16]/div/table/tbody/tr/td[5]/input"))));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='button'][value='Edit']")).click();
		Thread.sleep(3000);
		String courseTitle = generator.generate(10);
		driver.findElement(By.name("detailCourseTitle")).sendKeys(courseTitle);
		driver.findElement(By.name("detailCourseDescription")).sendKeys("this is the course description");
		driver.findElement(By.name("detailInstructionalText")).sendKeys("gratz dude");
		Thread.sleep(1000);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
		String path = System.getProperty("user.dir") + "/Intro_OneDrive.zip";
		//  System.out.println("path="+path);
		StringSelection str = new StringSelection(path);
		clipboard.setContents(str, str);

		driver.findElement(By.name("certificate_checkbox")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.name("certificate_select"))).selectByVisibleText(title);
		Thread.sleep(500);
//PLEASE Choice scorm type  and file_Scrom_Upload.
		driver.findElement(By.cssSelector("input[type='radio'][value='scorm']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(path);
		Thread.sleep(1000);

		Thread.sleep(1500);
		driver.findElement(By.id("btn_Scorm_UploadFile")).click();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Save']"))));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		Thread.sleep(3700);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[type='button'][value='Back']"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"versionRow1\"]/td[5]/input"))));
		Thread.sleep(2000);
		driver.findElement(By.name("langIsViewable")).click();
		Thread.sleep(2500);
		driver.findElement(By.name("detailCourseIsActive")).click();
		Thread.sleep(2700);
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();
		Thread.sleep(1500);


		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Courses"))));
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("Courses")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[1]/input")).sendKeys(courseId2);
		System.out.println("courseId2="+courseId2);
		Thread.sleep(2500);
		try {
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div[1]/table/tbody/tr[1]/td[5]/button")).click();
		} catch (NoSuchElementException e) {
			Assert.fail("something went wrong while creating and uploading the scorm course");
		}
		Thread.sleep(1500);

		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/button[1]"))));
		Thread.sleep(1500);

		WebElement English=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/button[1]"));
		js1.executeScript("arguments[0].click()",English);
		Thread.sleep(8500);
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_W);
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_W);
//        Thread.sleep(2500);
		//we have to finish the course first, then complete it for scorm.
		//first one
		driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
		Thread.sleep(2500);
		//second one
		driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //time in second
		Thread.sleep(2000);

		String innerText = driver.findElement(By.xpath("//*[@id=\"control-next\"]/div")).getText();
		System.out.println("innerText=" + innerText);
		if (innerText.equals("NEXT")) {
			driver.findElement(By.xpath("//div[@id='control-next']")).click();
		}

		Thread.sleep(2500);

		driver.findElement(By.xpath("//div[@id='control-next']")).click();
		Thread.sleep(5500);
		driver.switchTo().defaultContent();
		//gThread.sleep(1000);
		//first one
		driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
		//driver.switchTo().defaultContent();
		Thread.sleep(3500);
		//second one
		driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //time in second
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"item_6RV08inxbAS\"]/div/div[1]/div")).click();//.selectByVisibleText("True");
		//Then submit
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		//first one
		driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
		//driver.switchTo().defaul
		//
		// tContent();
		Thread.sleep(4000);
		//second one
		driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
		Thread.sleep(3500);
		//submit first one
		String submitText = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]/div")).getText();
		System.out.println("SubmitText=" + submitText);
		if (submitText.equals("SUBMIT")) {
			WebElement targetDiv = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", targetDiv);
		} else {
			Assert.fail("Should not be submitted here line 228");
		}
		Thread.sleep(3000);
		String continueDivText = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/div[6]/div[10]/div[5]")).getText();
		System.out.println("continueDivText=" + continueDivText);
		if (continueDivText.equals("")) {
			//Then submit
			Thread.sleep(3600);
			Actions builder = new Actions(driver);
			WebElement continueDivEle = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/div[6]/div[10]/div[5]"));
			Action drawAction = builder.moveToElement(continueDivEle, 136 / 2, 34 / 2)  // start point
					.click()
					.build();
			drawAction.perform();
			System.out.println("continueDivText=" + continueDivText);
		} else {
			Assert.fail("Should not be submitted here line 245");
		}
		//Seocnd Questions
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		Thread.sleep(1600);
		//first one
		driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
		Thread.sleep(4700);
		driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
		Thread.sleep(4800);
		//Let's Choice the Microsoft
		String secondQuestionElementDivText = driver.findElement(By.xpath("//div[@class='item image vectorshape item_5dkZK7ONPxW textlib']")).getText();
		System.out.println("secondQuestionElementDivText=" + secondQuestionElementDivText);
		if (secondQuestionElementDivText.equals("")) {
			//Then submit
			Thread.sleep(1500);
			Actions builder = new Actions(driver);
			WebElement secondQuestionElementDiv = driver.findElement(By.xpath("//div[@class='item image vectorshape item_5dkZK7ONPxW textlib']"));
			Action drawAction = builder.moveToElement(secondQuestionElementDiv, 689 / 2, 58 / 2)  // start point
					.click()
					.build();
			drawAction.perform();
			driver.findElement(By.xpath("//*[@id=\"item_6oOgDem4Ino\"]/div/div[2]")).click();//.Click Microsoft;

			System.out.println("secondQuestionElementDivText=" + secondQuestionElementDivText);
		} else {
			Assert.fail("Should not be submitted here line 271");
		}
		Thread.sleep(1500);
		driver.switchTo().defaultContent();
		Thread.sleep(4600);
		//first one
		driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
		Thread.sleep(4700);
		//second one
		driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
		Thread.sleep(4800);
		//first one
		String submitText2 = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]/div")).getText();
		System.out.println("submitText2=" + submitText2);
		if (submitText2.equals("SUBMIT")) {
			WebElement targetDiv2 = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", targetDiv2);
		} else {
			Assert.fail("Should not be submitted here line 291");
		}
		Thread.sleep(3000);
		String continueDivText2 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/div[6]/div[10]/div[5]")).getText();
		System.out.println("continueDivText2=" + continueDivText2);
		if (continueDivText2.equals("")) {
			Thread.sleep(2600);
			Actions builder2 = new Actions(driver);
			WebElement continueDivEle2 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/div[6]/div[10]/div[5]"));
			Action drawAction2 = builder2.moveToElement(continueDivEle2, 136 / 2, 34 / 2)  // start point
					.click()
					.build();
			drawAction2.perform();
			System.out.println("continueDivText2=" + continueDivText2);
		} else {
			Assert.fail("Should not be submitted here line 307");
		}
		//Third Questions
		Thread.sleep(2500);
		driver.switchTo().defaultContent();
		Thread.sleep(3600);
		driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
		Thread.sleep(3700);
		driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
		Thread.sleep(3800);
		//Let's Choice the THree. Videos and Docuemnts and Photos
		String thirdQuestionElementDivVideosText = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6SbbAbd8vb3 textlib\"]/canvas[@class=\"content\"]")).getText();
		String thirdQuestionElementDivDocuemntsText = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6l8QiU7zo26 textlib\"]/canvas[@class=\"content\"]")).getText();
		String thirdQuestionElementDivPhotosText = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5dfOOSV7gny textlib\"]/canvas[@class=\"content\"]")).getText();

		System.out.println("thirdQuestionElementDivVideosText=" + thirdQuestionElementDivVideosText);
		System.out.println("thirdQuestionElementDivDocuemntsText=" + thirdQuestionElementDivDocuemntsText);
		System.out.println("thirdQuestionElementDivPhotosText=" + thirdQuestionElementDivPhotosText);
		if (thirdQuestionElementDivVideosText.equals("") && thirdQuestionElementDivVideosText.equals("") && thirdQuestionElementDivVideosText.equals("")) {
			//Then submit
			Thread.sleep(2500);
			Actions builderVedio = new Actions(driver);
			Actions builderDocuemnt = new Actions(driver);
			Actions builderPhoto = new Actions(driver);
			WebElement thirdQuestionElementDivVideo = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6SbbAbd8vb3 textlib\"]/canvas[@class=\"content\"]"));
			WebElement thirdQuestionElementDivDocuemnt = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6l8QiU7zo26 textlib\"]/canvas[@class=\"content\"]"));
			WebElement thirdQuestionElementDivPhoto = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_5dfOOSV7gny textlib\"]/canvas[@class=\"content\"]"));
			Action drawActionVideo = builderVedio.moveToElement(thirdQuestionElementDivVideo, 649 / 2, 45 / 2)  // start point
					.click()
					.build();
			drawActionVideo.perform();
			Action drawActionDocuemnt = builderDocuemnt.moveToElement(thirdQuestionElementDivDocuemnt, 649 / 2, 45 / 2)  // start point
					.click()
					.build();
			drawActionDocuemnt.perform();
			Action drawActionPhoto = builderPhoto.moveToElement(thirdQuestionElementDivPhoto, 649 / 2, 45 / 2)  // start point
					.click()
					.build();
			drawActionPhoto.perform();
			System.out.println("thirdQuestionElementDivVideosText=" + thirdQuestionElementDivVideosText);
			System.out.println("thirdQuestionElementDivDocuemntsText=" + thirdQuestionElementDivDocuemntsText);
			System.out.println("thirdQuestionElementDivPhotosText=" + thirdQuestionElementDivPhotosText);

		} else {
			Assert.fail("Should not be submitted here line 352");
		}
		driver.findElement(By.xpath("//*[@id=\"item_6gT2Nvoe0S5\"]/div/div[3]")).click();//.selectTreeQuestion;
		driver.findElement(By.xpath("//*[@id=\"item_6gT2Nvoe0S5\"]/div/div[2]")).click();//.selectTreeQuestion;
		driver.findElement(By.xpath("//*[@id=\"item_6gT2Nvoe0S5\"]/div/div[4]")).click();//.selectTreeQuestion;

		Thread.sleep(2500);
		driver.switchTo().defaultContent();
		Thread.sleep(3600);
		driver.switchTo().frame(driver.findElement(By.id("scorm_iframe")));
		Thread.sleep(3700);
		driver.switchTo().frame(driver.findElement(By.name("scormdriver_content")));
		Thread.sleep(3800);
		String submitText3 = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]/div")).getText();
		System.out.println("SubmitText3=" + submitText3);
		if (submitText3.equals("SUBMIT")) {
			WebElement targetDiv3 = driver.findElement(By.xpath("//div[@class=\"contentpane\"]/*[@id=\"controls\"]/*[@id=\"control-submit\"]"));
			JavascriptExecutor executor3 = (JavascriptExecutor) driver;
			executor3.executeScript("arguments[0].click();", targetDiv3);
		} else {
			Assert.fail("Should not be submitted here line 369");
		}
		Thread.sleep(2000);
		String continueDivText3 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/div[6]/div[10]/div[5]")).getText();
		System.out.println("continueDivText3=" + continueDivText3);
		if (continueDivText3.equals("")) {
			Thread.sleep(1600);
			Actions builder3 = new Actions(driver);
			WebElement continueDivEle3 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/div[6]/div[10]/div[5]"));
			Action drawAction3 = builder3.moveToElement(continueDivEle3, 136 / 2, 34 / 2)  // start point
					.click()
					.build();
			drawAction3.perform();
			System.out.println("continueDivText3=" + continueDivText3);
		} else {
			Assert.fail("Should not be submitted here line 385");
		}
		//Submit Results.item image vectorshape item_6JIGnuBEjkK textlib
		Thread.sleep(2500);
		String submitResultsStr = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6JIGnuBEjkK textlib\"]/canvas[@class=\"content\"]")).getText();
		System.out.println("submitResultsStr=" + submitResultsStr);
		String mainWin = driver.getWindowHandle();
		if (continueDivText2.equals("")) {
			//Then submit
			Thread.sleep(1600);
			Actions builderSR = new Actions(driver);
			WebElement submitResultsEle = driver.findElement(By.xpath("//div[@class=\"item image vectorshape item_6JIGnuBEjkK textlib\"]/canvas[@class=\"content\"]"));
			Action drawActionSR = builderSR.moveToElement(submitResultsEle, 179 / 2, 51 / 2)  // start point
					.click()
					.build();
			drawActionSR.perform();
			System.out.println("submitResultsStr=" + submitResultsStr);
		} else {
			Assert.fail("Should not be submitted here line 367");
		}
		// Click on All Classes tab
		// find a SCORM course and click play
		// complete the course
		// back to the All Classes tab and search for this course
		// see if Status is now showing Completed
		// also, go to the My Training Report to see if the system successfully record the completion in the report.
		// courseId  My Training Report
		Thread.sleep(1000);
//        WebDriver driver2 = new ChromeDriver();
//        driver2.switchTo().defaultContent();
		Thread.sleep(1500);
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			break;
		}
		Thread.sleep(800);
		JavascriptExecutor js2 = (JavascriptExecutor)driver;
		//WebElement myTrainingReport = driver.findElement(By.xpath("//*[@id=\"navPrimary\"]/li[7]/ul/li[1]/a"));
		WebElement myTrainingReport = driver.findElement(By.xpath("//a[contains(text(),'My Training Report')]"));
		js2.executeScript("arguments[0].click()", myTrainingReport);
		Thread.sleep(1000);
		new Select(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[1]/div/select"))).selectByVisibleText("Online");
		Thread.sleep(1600);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		JavascriptExecutor js10 = (JavascriptExecutor)driver;
		java.util.List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div/div/div[1]/div[2]/div/span"));
        java.util.List<WebElement> list1 = driver.findElements(By.xpath("//*[@id=\"main\"]/div[2]/div/div[2]/div/div/div[2]/div[2]/div/span"));
		Calendar calendar = new GregorianCalendar();

		int DateofToday= calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("DateofToday:"+DateofToday);

		String TodayOnly= String.valueOf(DateofToday);
		System.out.println("TodayOnly:"+TodayOnly);
		if (TodayOnly.equals("01")){
			TodayOnly="1";
		}
		if (TodayOnly.equals("02")){
			TodayOnly="2";
		}
		if (TodayOnly.equals("03")){
			TodayOnly="3";
		}
		if (TodayOnly.equals("04")){
			TodayOnly="4";
		}
		if (TodayOnly.equals("05")){
			TodayOnly="5";
		}
		if (TodayOnly.equals("06")){
			TodayOnly="6";
		}
		if (TodayOnly.equals("07")){
			TodayOnly="7";
		}
		if (TodayOnly.equals("08")){
			TodayOnly="8";
		}
		if (TodayOnly.equals("09")){
			TodayOnly="9";
		}
		System.out.println("TodayOnly is"+TodayOnly);


		String Number= TodayOnly;

		for(WebElement e : list) {
			String dateofcanlendar = e.getAttribute("textContent");

			System.out.println(dateofcanlendar);

			if (dateofcanlendar.equals(Number)) {
				System.out.println("Object Found Yeah Yeah Yeah");
				js10.executeScript("arguments[0].click();", e);
				break;
			}
			else{System.out.println("Object Not Found ");
			}
		}

        for(WebElement e : list1) {
            String dateofcanlendar = e.getAttribute("textContent");

            System.out.println(dateofcanlendar);

            if (dateofcanlendar.equals(Number)) {
                System.out.println("Object Found Yeah Yeah Yeah");
                js10.executeScript("arguments[0].click();", e);
                break;
            }
            else{System.out.println("Object Not Found ");
            }
        }


		driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div[3]/div/button[1]")).click();//Go for Reports

		//Click OK
		Thread.sleep(2000);

		try {
			WebElement Last_Page = driver.findElement(By.xpath("//*[@id=\"my-courses\"]/div/div/div[2]/div[2]/nav/ul/li[5]/a"));
			if (Last_Page.isDisplayed()) {
				JavascriptExecutor jsx = (JavascriptExecutor)driver;
				jsx.executeScript("arguments[0].click();", Last_Page);
			}
		}
		catch(Exception e){
			System.out.println("There is no last page element");}



//		driver.findElement(By.cssSelector("Input[type='button'][value='Go']")).click();//Go for My Historyå
		Thread.sleep(1200);

			java.util.List<WebElement>  myResults3a;
			WebElement myResult = driver.findElement(By.xpath("//*[@id=\"my-training\"]/div[2]/div/div[1]/div[2]/table/tbody"));//Under The Reports
//			myResults = driver.findElements(By.xpath("//div[@class=\"msg_head\"]/table/tbody/tr")); //Under My History

					myResults3a = myResult.findElements(By.tagName("td"));

                        for (WebElement myResult3a : myResults3a) {

                            String idstr3a = myResult3a.getText();

                            if (idstr3a.contains(courseId2)) {
                                System.out.println("Course has been found the test is successful");

                            }
                        }
                    driver.quit();



	}

}
