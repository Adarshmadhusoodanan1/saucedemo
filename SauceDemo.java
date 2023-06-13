package testng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SauceDemo {
	WebDriver driver;
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
	}
	@BeforeMethod
	public void urlload()
	{
		driver.get("https://www.saucedemo.com/");
	}

	@Test
	public void test() throws IOException, Exception
	{
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Read credential from Excel
	
		File f=new File("C:\\Users\\A D M I N\\Desktop\\Sauce.xlsx");
		FileInputStream fi=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("sheet1");
		System.out.println(sh.getLastRowNum());
		
		
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
		String username=sh.getRow(i).getCell(0).getStringCellValue();
		System.out.println("username="+username);
		String pswd=sh.getRow(i).getCell(1).getStringCellValue();
		System.out.println("password="+pswd);
		
		driver.manage().window().maximize();
		//login
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pswd); 
		driver.findElement(By.name("login-button")).click();	
		Thread.sleep(5000);
		//Add cart to the items in ascending order
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-onesie\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-test.allthethings()-t-shirt-(red)\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		
		Thread.sleep(5000);
		//Remove items that have price <$15
		driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-fleece-jacket\"]")).click();
		
		//Click on check out button
		driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
		
		Thread.sleep(5000);
		//Enter customer details
		driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Adarsh");
		driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("M");
		driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("691536");
		
		//click continue and finish
		driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click();
		
		Thread.sleep(5000);
		//logout
	WebElement mouse=driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
	mouse.click();
	Actions act=new Actions(driver);
	act.moveToElement(mouse).perform();
	driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();
		
        }
      }
	}