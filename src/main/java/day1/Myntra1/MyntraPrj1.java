package day1.Myntra1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MyntraPrj1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.myntra.com/");
		
		//Mouseover on WOMEN
		
		WebElement women = driver.findElementByXPath("//*[@id=\"desktop-header-cnt\"]/div[2]/nav/div/div[2]/div/a");
		Actions builder = new Actions(driver);
		builder.moveToElement(women).pause(2000)
		.click(driver.findElementByXPath("//a[text()='Jackets & Coats']"))
		.perform();
		
		String items = driver.findElementByClassName("title-count").getText();
		System.out.println("Jackets & Coats are " +items);
		int itemCount = Integer.parseInt(items.replaceAll("\\D", ""));
		
		String jackTotal = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		System.out.println(jackTotal);
		String coatTotal = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		System.out.println(coatTotal);
		
		jackTotal = jackTotal.replaceAll("\\D", "");
		coatTotal = coatTotal.replaceAll("\\D", "");
		
		int wholeCount = (Integer.parseInt(jackTotal) + Integer.parseInt(coatTotal));
		System.out.println(wholeCount);
		
		if(itemCount==wholeCount)
		{
			System.out.println("Count matches");
		}
		else
		{
			System.out.println("Count doesn't match");
		}
	
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		driver.findElementByXPath("//div[@class='brand-more']").click();
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
		driver.findElementByXPath("//label[@class=' common-customCheckbox']//div[1]").click();
		//Close the pop-up X
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();		
		Thread.sleep(3000);
		//Confirm all the coats are of Mango brand
		
		List<WebElement> brand = driver.findElementsByClassName("product-brand");
		int brandCount = 0;
		for(WebElement ele1 : brand)
		{
			if(ele1.getText().equalsIgnoreCase("MANGO"))
			{
				brandCount++;
			}
		}
		
		if(brandCount == brand.size()) {
			System.out.println("All items are MANGO brand");
		}
		else {
			System.out.println("All the items are not MANGO brand");
		}
		//Sort by better discount
		builder.moveToElement(driver.findElementByClassName("sort-sortBy"))
		.click(driver.findElementByXPath("//label[text()='Better Discount']")).perform();
		
		builder.moveToElement(driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]"))
		.click(driver.findElementByXPath("(//span[text()='wishlist now'])")).pause(2000)
		.perform();
		
	//	Thread.sleep(3000);
		
		driver.close();
		
		
		
		
		
		
		
	}

}
