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


public class TC_AddCommentandNoteToLead extends Base  
{    
	@Test(groups={"Regression"},description="Add Comment To Lead")
	
	public static void AddCommentTolead() throws InterruptedException
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
	  
		 driver.get("https://"+config.getProperty("Environment")+".convergehub.com/leads");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 /*
	    
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
	    
	   lead.CommentIcon.click();
	   */
	   
	   	driver.findElement(By.id("mydiv"+SavedData.getProperty("Lead_Id"))).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("Add Comment")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    
	   lead.CommentTextBox.sendKeys("Test Comments\n Entered");
	   lead.CommentSaveButton.click();
	    
	    
	    Assert.assertNotNull(driver.findElement(By.xpath("//h1[@class='listcomment_heading']")));
	    
	}
	
	
@Test(groups={"Regression"},description="Add Note To Lead")
	
	public static void AddNoteTolead() throws InterruptedException
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
	  
		 driver.get("https://"+config.getProperty("Environment")+".convergehub.com/leads");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		int rowcnt=0;
	    
	    try
	    {
	        List<WebElement> Leads_num=driver.findElements(By.xpath(OR.getProperty("LeadCheckbox")));
	        for(int cnt=0;cnt<Leads_num.size();cnt++)
	        {
	           String val=Leads_num.get(cnt).getAttribute("value");
	           if(val.contains(SavedData.getProperty("Lead_Id")))
	           {
	        	   rowcnt=cnt+1;
	        	   break;
	           }
	     
	        }
	    	
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
	    //lead.NotesIcon.click();
	    driver.findElement(By.id("list_checkbox_"+SavedData.getProperty("Lead_Id"))).click();
	    String noteicon="(//span[contains(text(),' Notes')])["+rowcnt+"]";
	    System.out.println(noteicon);
	    driver.findElement(By.xpath(noteicon)).click();
	  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
	    lead.NotesSubject.sendKeys("Test Notes");
	    lead.NotesDescription.sendKeys("Sample Description /n Entered");
	    lead.NotesSaveBtn.click();
	    
	    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='header_notification_msg']")).getText(), "Note Relation Added");
	    //Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Note Relation Added']")));
	    
	}
      
}
