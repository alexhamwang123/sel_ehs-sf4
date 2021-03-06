//This script is written to create a new Announcement and check in the list of announcements 
//whether the newly created announcement appears in the list for EHS system

package com.EHSAdmin;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

//@Test
@Test
public class CreateAnnouncement {

	public void CreateAnnouncement1() throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
		WebDriverWait Wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		File file = new File(System.getProperty("user.dir")+"/PasswordFileEHS.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String urladdr = prop.getProperty("url");
		driver.get(urladdr);
try { Actions actions = new Actions(driver); actions.sendKeys("thisisunsafe");
actions.build().perform(); }
catch (NoSuchElementException e) { System.out.println("Bypass mode is no more needed"); }
        driver.manage().window().maximize();
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on 'EHS Admin'
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Courses')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Admin')]")).click();
		Thread.sleep(1000);

		JavascriptExecutor js = (JavascriptExecutor)driver;

		WebElement ele1 = driver.findElement(By.xpath("//a[contains(text(),'EHS Admin')]"));

		js.executeScript("arguments[0].click();",ele1);


		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Clicking on 'Create Announcement'
		driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-light dropdown-toggle-no-caret']")).click();
		Thread.sleep(1500);
		WebElement CreatBtn= driver.findElement(By.xpath("//a[contains(text(),'Create New Announcement')]"));
		js.executeScript("arguments[0].click();",CreatBtn);



		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
		String title = generator.generate(10);

		//Entering the 'Announcement Title'
		driver.findElement(By.id("annTitle")).sendKeys(title);
		Thread.sleep(1500);
		//Entering the Announcement Content
		driver.switchTo().frame("annContent_ifr");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys("this is a test");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		driver.switchTo().defaultContent();
		Thread.sleep(1500);

		//Click the Calendar
		driver.findElement(By.id("annEndDate")).click();
		Thread.sleep(1500);

		JavascriptExecutor js10 = (JavascriptExecutor)driver;
		WebElement Box= driver.findElement(By.xpath("/html/body/div[1]/main/div/div[1]/div/div/div[2]/div/form/div[2]/div[4]/div/div/div[1]/div[2]/div/div/div/div/div[2]/div[2]"));
		List<WebElement> list = Box.findElements(By.tagName("span"));
		//Reverse the list to find the end day in case there are two todays or two yesterdays
		Collections.reverse(list);

		Calendar calendar = new GregorianCalendar();


		int DateofToday= calendar.get(Calendar.DAY_OF_MONTH)+1;
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
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("annRegion"))).selectByVisibleText("Taipei");
		//Click Save Btn
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
		Thread.sleep(1000);
	    WebElement Save=	driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
		js10.executeScript("arguments[0].click();", Save);

		// clicking the 'Back' button
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Back')]")));
		Thread.sleep(1000);
		WebElement Back=	driver.findElement(By.xpath("//button[contains(text(),'Back')]"));
		js10.executeScript("arguments[0].click();", Back);


		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Search Title
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);
		Thread.sleep(1500);


		//Entering the Announcement Content
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")).click();
		Thread.sleep(3000);
		driver.switchTo().frame("annContent_ifr");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys("Im editing this content");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		JavascriptExecutor js1 = (JavascriptExecutor)driver;

		//Click Save Btn
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]")));
		Thread.sleep(1000);
		WebElement Save1=	driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
		js10.executeScript("arguments[0].click();", Save1);

		// clicking the 'Back' button
		Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Back')]")));
		Thread.sleep(1000);
		WebElement Back1=	driver.findElement(By.xpath("//button[contains(text(),'Back')]"));
		js10.executeScript("arguments[0].click();", Back1);

		Thread.sleep(3000);
		//Search Title
		driver.findElement(By.xpath("//input[@class='form-control']")).clear();
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(title);

		//CLick the result
		Thread.sleep(3500);
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")).click();
		Thread.sleep(3000);
		driver.switchTo().frame("annContent_ifr");
		Thread.sleep(1500);
		if(!driver.findElement(By.xpath("//p[contains(text(),'this is a testIm editing this content')]")).getAttribute("innerHTML").contains("editing")) {
			Assert.fail("something went wrong with editing the announcement content");
		}

		driver.quit();



	}

}
