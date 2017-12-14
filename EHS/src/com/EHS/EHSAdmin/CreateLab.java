//This script is written to create a new Lab.
//Flow: EHS UAT > Admin > EHS Admin > Lab Management > Create Lab

package com.EHS.EHSAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class CreateLab {

	public static void main(String[] args) throws IOException {
		
        System.setProperty("webdriver.chrome.driver", "/Users/bhavesh/Downloads/chromedriver-1.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://192.168.15.131:8330");
		
		driver.findElement(By.id("login_login_id")).sendKeys("admin");
		
		File file=new File(System.getProperty("user.dir")+"/EHS.password.properties");
		FileInputStream inStream=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(inStream);
		String val = prop.getProperty("adminpassword");
		driver.findElement(By.id("login_password")).sendKeys(val);
		
		driver.findElement(By.name("submit")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clicking on EHS Admin
		WebElement ele = driver.findElement(By.xpath("//*[@id='navPrimary']/li[7]/ul/li[4]/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);
				
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Lab Management'
		driver.findElement(By.xpath("//*[@id='left']/table/tbody/tr[3]/td/a")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click on 'Create Lab'
		driver.findElement(By.cssSelector("input[type='button'][value='Create Lab']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter the 'Lab Name' that you wish to create
		driver.findElement(By.name("detailLabName")).sendKeys("Lab 2");

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Enter the Description of the lab that you wish to create
		driver.findElement(By.name("detailLabDescr")).sendKeys("This is the second lab in the series");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Lab Manager' to assign a manager to the lab you wish to create
		driver.findElement(By.id("selectBtnTeam")).click();

		// Enter the Badge Number
		driver.findElement(By.name("badgeNo")).sendKeys("173999");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click the 'Search' button
		driver.findElement(By.cssSelector("input[type='submit'][value='Search']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Choosing the user '173999' from the list for this case
		driver.findElement(By.xpath("//*[@id='teammember_result']/div/table/tbody/tr/td[2]/a")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Site'
		driver.findElement(By.id("selectBtnSite")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Choosing the site 'DLL' for this case
		driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr[3]/td[1]/a")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on 'Building' to select a building where the lab session would be held
		driver.findElement(By.id("selectBtnBuilding")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Click on Search
		driver.findElement(By.xpath("//*[@id='BuildingForm']/div[1]/center/a/input")).click();

		// Choosing a building. We choose Apple Inc R451 - Apple Inc R451 in  this case
		driver.findElement(By.xpath("//*[@id='Deptdirectreport']/tbody/tr[2]/td/a")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter the room number that you wish where the lab session would take place
		driver.findElement(By.id("aReplyEmail")).sendKeys("114");

		// Click the 'Save' button to save the lab session record to display in the list of Labs
		driver.findElement(By.cssSelector("input[type='button'][value='Save']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click on the 'Back' button to show the name of the lab in the list of labs
		driver.findElement(By.cssSelector("input[type='button'][value='Back']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Now searching for the Lab's name to see if it gets displayed in the list of the labs
		driver.findElement(By.id("srch_fld")).sendKeys("Lab 2");

		//Clicking on the 'Go' button to display the result
		driver.findElement(By.cssSelector("input[type='submit'][value='Go']")).click();

	}

}
