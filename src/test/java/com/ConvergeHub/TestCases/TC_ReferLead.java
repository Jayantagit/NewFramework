package com.ConvergeHub.TestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ConvergeHub.Base.Base;
import com.ConvergeHub.Pages.LeadPage;
import com.ConvergeHub.Pages.LoginPage;


public class TC_ReferLead extends Base  
{    
	@Test(groups={"Regression"},description="Refer a Lead")
	
	public static void Referlead() throws InterruptedException
	{
		LeadPage lead=new LeadPage();
		
		/*
		LoginPage login=new LoginPage();		
		login.username.clear();
	    login.username.sendKeys(config.getProperty("UserName"));
		login.password.sendKeys(config.getProperty("Password"));
	    login.login.click();
	    System.out.println("Successfully Logged");
	    wait=new WebDriverWait(driver,20); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));*/
	  
	    driver.get("https://staging.convergehub.com/leads");
	    
	    try
	    {
	        List<WebElement> Leads_num=driver.findElements(By.xpath(OR.getProperty("LeadCheckbox")));
	          
	        if(Leads_num.size()>0)
	        {
	        	Leads_num.get(0).click();//Click the First Lead in the List
	        }
	        else
	        {
	        	System.out.println("No Lead is present in the Lead List");      
	        }
	    	
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
	    lead.LeadListSelection.click();
	    driver.findElement(By.linkText("Refer")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    lead.ReferBtn.click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    //Enter the value in the Refer popup
	    
	    lead.ReferTo.sendKeys("ABC@mailinator.com");
	    lead.ReferBtnpopup.click();
	    
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@id='header_notification_msg']"))));
	   // Assert.assertEquals(driver.findElement(By.xpath("//span[@id='header_notification_msg']")).getText(), "Leads successfully referred");

	    
	}
      
}
