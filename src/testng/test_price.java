package testng;



import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List  ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test_price {

	public WebDriver driver;
    public int numberOfTry = 5;
  
   SoftAssert softassertProcess = new  SoftAssert();
	@BeforeTest()

	public void login() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://smartbuy-me.com/smartbuystore/");
        driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
		driver.manage().window().maximize();
		
	}
	@Test(priority = 1)
	public void Test_Adding_Itim_DELL_G15() {
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	    for (int i = 0; i< numberOfTry;i++) {
	    	
		driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button")).click();
		String msg = driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/div[1]")).getText();
		if(msg.contains("Sorry")) {
			numberOfTry =i;
			 driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[1]")).click();
		}
		else {
			 driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();
	    	
		}
	  
	 }
	    
}
	@Test(priority = 2)
	public void we_need_to_check_the_correct_price() throws ParseException  {
		
	driver.navigate().back();
		
  String the_single_item_price = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]")).getText();
	
  String[]the_updated_single_item_price =the_single_item_price.split("JOD");
  String the_final_single_item_price_in_string = the_updated_single_item_price[0].trim();

  String update= the_final_single_item_price_in_string.replace(",",".");
    
  Double final_price = Double.parseDouble(update);
  System.out.println( "the single item price " + final_price);
  System.out.println( "**********************************");
	
  System.out.println( "the total price " + final_price*numberOfTry);
	
  driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button")).click();
  driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[1]")).click();
  String the_total_price = driver.findElement(By.xpath("/html/body/main/div[3]/div[1]/div[2]/div[4]/div/div[2]/div/div[1]/div[4]")).getText();

  String[]the_updated_the_total_price =the_total_price.split("JOD");

  String the_final_total_price =the_updated_the_total_price[0].trim();
		  
 String update_total=  the_final_total_price.replace(",",".");
 String update_total2 =  update_total.replace("000","");
 String finalTotal = update_total2;	 
 NumberFormat finalTotal2 = NumberFormat.getInstance();
 double final_total3 = finalTotal2.parse(finalTotal).doubleValue();
 System.out.println("********************************** ");
 System.out.println("the total price in cart " + final_total3);
 softassertProcess.assertAll();
		
	}
	
	@Test( priority = 3)
	public void we_need_to_check_the__price_Discount() throws ParseException  {
		
	driver.navigate().back();
	 
	  String the_price_befour_discount = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[2]")).getText();
	  String[]the_updated_discount_price =the_price_befour_discount.split("JOD");
	  String the_final_the_price_befour_discount =the_updated_discount_price[0].trim();
	  String update_price_befour_discount= the_final_the_price_befour_discount.replace(",",".");
	  Double final_price_befour_discount = Double.parseDouble( update_price_befour_discount);
	  System.out.println("/////////////////////////////////////// ");
	  System.out.println("The price Before discount "+final_price_befour_discount );
	  System.out.println("********************************** ");
      String the_discount = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[1]")).getText();
	  String[]the_updated_discount =the_discount.split("%");
	  String the_final_discount =the_updated_discount[0].trim();
	  String update2_discount= the_final_discount.replace(",",".");
      Double final_discount = Double.parseDouble( update2_discount);
	  System.out.println("The discount "+ final_discount );
	  System.out.println("********************************** ");
	  System.out.println("The price after discount "+( final_price_befour_discount-(final_price_befour_discount*final_discount/100)));																;

	  String the_single_item_price = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]")).getText();
		
	  String[]the_updated_single_item_price =the_single_item_price.split("JOD");
	  String the_final_single_item_price_in_string = the_updated_single_item_price[0].trim();
	  String update= the_final_single_item_price_in_string.replace(",",".");	  Double final_price = Double.parseDouble(update);
	  System.out.println( "The price after discount on the site " + final_price);
	  System.out.println("********************************** ");
	  softassertProcess.assertEquals(final_price, final_price_befour_discount-(final_price_befour_discount*final_discount/100));	
			  
	
}  

}
