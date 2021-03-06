package com.ConvergeHub.TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ConvergeHub.Base.Base;
import com.ConvergeHub.Pages.DealPage;
import com.ConvergeHub.Pages.LeadPage;
import com.ConvergeHub.Pages.AccountPage;
import com.ConvergeHub.Pages.ActivitiesPage;
import com.ConvergeHub.Pages.BillingPage;
import com.ConvergeHub.Pages.CasePage;
import com.ConvergeHub.Pages.CollaborationPage;
import com.ConvergeHub.Utilities.TestUtil;


public class TC_Billing extends Base  
{  
	
	@Test(priority=0,groups={"Regression"},description="Create a New Product from Billing Module")
	
	public static void AddProduct() throws InterruptedException
	{
		//Initialize the Page Class
		DealPage deal=new DealPage();
		CasePage casepg=new CasePage();
		ActivitiesPage activities=new ActivitiesPage();
		BillingPage billing=new BillingPage();
	
	/*-------------------------Login Code
		LoginPage login=new LoginPage();
		login.username.clear();
	    login.username.sendKeys(config.getProperty("UserName"));
		login.password.sendKeys(config.getProperty("Password"));
	    login.login.click();
	    System.out.println("Successfully Logged");
	    wait=new WebDriverWait(driver,30); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));	
	    driver.get("https://staging.convergehub.com/targets/add");
	    -------------------------------------------*/
	    
		//Redirecting to the Add Product Page
		driver.get("https://"+config.getProperty("Environment")+".convergehub.com/products/add/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	    
	    //===========Filled up the Add Product Screen=====================
		
		//------Enter Product Name		
	    String prodName=excel.getCellDataUpd("Billing","productName", 1);
	    billing.productName.sendKeys(prodName);    
	   
	    //------Select the Product Type	   
	    String prodType=excel.getCellDataUpd("Billing","productType", 1);
	    new Select(billing.productType).selectByVisibleText(prodType);
	    
   
		//------Enter the Product Price
	    String prodPrice=excel.getCellDataUpd("Billing","productPrice", 1);
	    billing.productPrice.sendKeys(prodPrice);
	    
	    //------Select the Product Category   
	    String prodCategory=excel.getCellDataUpd("Billing","productCategory", 1);
	    new Select(billing.productCategory).selectByVisibleText(prodCategory);
	    
	    //------Select the Product Status   
	    String prodStatus=excel.getCellDataUpd("Billing","productStatus", 1);
	    new Select(billing.productStatus).selectByVisibleText(prodStatus);
	    
	    //Enter the Save Button
	  	billing.btnproductSave.click();
	    
	    //Assertion statement added for the verification
	    WebDriverWait waittasksave = new WebDriverWait (driver, 20);
	    waittasksave.until(ExpectedConditions.visibilityOf(deal.SuccessNotificationMsg));
	    
	    Assert.assertTrue(deal.SuccessNotificationMsg.getText().toString().contains("Created"));
	    
	  	String  baseurl=driver.getCurrentUrl();
		String arr[]=baseurl.split("/");		
		String Product_ID=arr[arr.length-1];
		TestUtil.writeProperty("Product_ID", Product_ID);  
	    	
	}
	
	@Test(priority = 1,groups={"Regression"},description="Edit a Product")

	public static void editProduct() throws InterruptedException
	{
		LeadPage lead=new LeadPage();
		AccountPage account=new AccountPage();
		CollaborationPage clb=new CollaborationPage();
		ActivitiesPage activities=new ActivitiesPage();
		BillingPage billing=new BillingPage();
		
		/*-------------------------Login Code
		LoginPage login=new LoginPage();		
		login.username.clear();
	    login.username.sendKeys(config.getProperty("UserName"));
		login.password.sendKeys(config.getProperty("Password"));
	    login.login.click();
	    System.out.println("Successfully Logged");
	    wait=new WebDriverWait(driver,20); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));
	     -------------------------------------------*/

	    driver.get("https://"+config.getProperty("Environment")+".convergehub.com/products/add/"+SavedData.getProperty("Product_ID"));
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	     
	    //------Select the new Type	   
	    String Typenew=excel.getCellDataUpd("Billing","edtType", 1);
	    new Select(billing.productType).selectByVisibleText(Typenew);
	    
	    //------Select the new Category
	    String Categorynew=excel.getCellDataUpd("Billing","edtCategory", 1);
	    new Select(billing.productCategory).selectByVisibleText(Categorynew);
	    
		//------Enter the Product Description
	    String productDescriptionnew=excel.getCellDataUpd("Billing","edtDescription", 1);
	    billing.productDescription.clear();
	    billing.productDescription.sendKeys(productDescriptionnew);
	    
	    
	    //Click the Save button after Editing
	    billing.btnproductSave.click();
	    
	    WebDriverWait wait = new WebDriverWait (driver, 20);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='header_notification_msg']")));
	    
	    ////Validation that Product edited successfully
	    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='header_notification_msg']")).getText().contains("Updated"));
	 	    
	}
	
	@Test(priority=2,groups={"Regression"},description="Create a New Quote from Billing Module")
	
	public static void CreateQuote() throws InterruptedException
	{
		//Initialize the Page Class
		DealPage deal=new DealPage();
		CasePage casepg=new CasePage();
		ActivitiesPage activities=new ActivitiesPage();
		BillingPage billing=new BillingPage();
	
	/*-------------------------Login Code
		LoginPage login=new LoginPage();
		login.username.clear();
	    login.username.sendKeys(config.getProperty("UserName"));
		login.password.sendKeys(config.getProperty("Password"));
	    login.login.click();
	    System.out.println("Successfully Logged");
	    wait=new WebDriverWait(driver,30); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));	
	    driver.get("https://staging.convergehub.com/targets/add");
	    -------------------------------------------*/
	    
		//Redirecting to the Add Quotation Page
		driver.get("https://"+config.getProperty("Environment")+".convergehub.com/quotations/add/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	    
	    //===========Filled up the Add Quotation Screen=====================
		
		//Select the Account Name
	    billing.accountName.click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    String acctNameQuote=excel.getCellDataUpd("Billing","AccountName", 1);
	    List<WebElement> name_List= driver.findElements(By.xpath("//div[@id='alls_tab_account_name']/ul/li"));
	    //List<WebElement> name_List=acctname.findElements(By.tagName("li"));
	    for (WebElement li : name_List) 
	    {
	    if (li.getText().equals(acctNameQuote)) 
	    {
	    	 System.out.println(li.getText());
	         li.click();
	         driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	         break;
	       }
	    }
	    
		
		//------Select the Date from the Valid Till Date Picker	
	    String Day=excel.getCellDataUpd("Billing","Day", 1);
	    String Month=excel.getCellDataUpd("Billing","Month", 1);
	    String Year=excel.getCellDataUpd("Billing","Year", 1);
	    
	    billing.validTill.click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    new Select(billing.calMonth).selectByVisibleText(Month);
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    new Select(billing.calYear).selectByVisibleText(Year);
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    
	  //-------------For Selecting the Day
		List<WebElement> allDates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
		
		for(WebElement ele:allDates)
		{
			
			String date=ele.getText();
			
			if(date.equalsIgnoreCase(Day))
			{
				ele.click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
				break;
			}
			
		}
		
		//------Enter the Terms & Conditions
	    String TermsCondition=excel.getCellDataUpd("Billing","TermsCondition", 1);
	    billing.TermsandCondition.sendKeys(TermsCondition);
	    
		//------Enter the Quotes Description
	    String QuotesDescription=excel.getCellDataUpd("Billing","QuotesDescription", 1);
	    billing.QuotesDescription.sendKeys(QuotesDescription);
	    
        //Click the Details tab
	    driver.findElement(By.linkText("Details")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    
	    //Enter the Product/Service
	    String productService=excel.getCellDataUpd("Billing","productService", 1);
	    billing.QuotesProduct.sendKeys(productService);
	    	    
	    //Enter the Quantity
	    billing.Quanitity.click();
	    String Qunatity=excel.getCellDataUpd("Billing","Qty", 1);
	    billing.Quanitity.sendKeys(Qunatity);
	    
	    //Enter the Rate
	    billing.Rate.click();
	    String Rate=excel.getCellDataUpd("Billing","Rate", 1);
	    billing.Rate.sendKeys(Rate);
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    //Select the Currency
	    String Currency=excel.getCellDataUpd("Billing","Currency", 1);
	    new Select(billing.Currency).selectByValue(Currency);
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    

	    //Click-Save Quotation Button
	    billing.btnSave.click();
	    
	    
	    //Assertion statement added for the verification
	    WebDriverWait waittasksave = new WebDriverWait (driver, 20);
	    waittasksave.until(ExpectedConditions.visibilityOf(deal.SuccessNotificationMsg));
	    
	    Assert.assertTrue(deal.SuccessNotificationMsg.getText().toString().contains("Created"));
	    
	  	String  baseurl=driver.getCurrentUrl();
		String arr[]=baseurl.split("/");		
		String Quote_ID=arr[arr.length-1];
		TestUtil.writeProperty("Quote_ID", Quote_ID);  
	    	
	}
	
@Test(priority = 3,groups={"Regression"},description="Edit a Quote")

	public static void editQuote() throws InterruptedException
	{
		LeadPage lead=new LeadPage();
		CollaborationPage clb=new CollaborationPage();
		BillingPage billing=new BillingPage();
		
		/*-------------------------Login Code
		LoginPage login=new LoginPage();		
		login.username.clear();
	    login.username.sendKeys(config.getProperty("UserName"));
		login.password.sendKeys(config.getProperty("Password"));
	    login.login.click();
	    System.out.println("Successfully Logged");
	    wait=new WebDriverWait(driver,20); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));
	     -------------------------------------------*/

	    driver.get("https://"+config.getProperty("Environment")+".convergehub.com/quotations/add/"+SavedData.getProperty("Quote_ID"));
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    //Edit  the Product Quantity & rate
	    
        //Click the Details tab
	    driver.findElement(By.linkText("Details")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    
   	    
	    //Enter the Quantity
	    billing.Quanitity.click();
	    billing.Quanitity.clear();
	    String Qunatitynew=excel.getCellDataUpd("Billing","QtyNew", 1);
	    billing.Quanitity.sendKeys(Qunatitynew);
	    
	    //Enter the Rate
	    billing.Rate.click();
	    billing.Rate.clear();
	    String RateNew=excel.getCellDataUpd("Billing","RateNew", 1);
	    billing.Rate.sendKeys(RateNew);
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	    //Click-Save Quotation Button
	    billing.btnSave.click();
	    
	    WebDriverWait wait = new WebDriverWait (driver, 20);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='header_notification_msg']")));
	    
	    ////Validation that the Quotation edited successfully
	    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='header_notification_msg']")).getText().contains("Updated"));
	 	    
}	
	
@Test(priority=4,groups={"Regression"},description="Create a New Invoice from Billing Module")

public static void CreateInvoice() throws InterruptedException
{
	//Initialize the Page Class
	DealPage deal=new DealPage();
	BillingPage billing=new BillingPage();

/*-------------------------Login Code
	LoginPage login=new LoginPage();
	login.username.clear();
    login.username.sendKeys(config.getProperty("UserName"));
	login.password.sendKeys(config.getProperty("Password"));
    login.login.click();
    System.out.println("Successfully Logged");
    wait=new WebDriverWait(driver,30); 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));	
    driver.get("https://staging.convergehub.com/targets/add");
    -------------------------------------------*/
    
	//Redirecting to the Add Invoice Page
	driver.get("https://"+config.getProperty("Environment")+".convergehub.com/invoices/add/");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
    
    //===========Filled up the Add Invoice Screen=====================
	//Save the Generated Invoice No in the Property File
	String Invoice_No=billing.invNo.getText();
	TestUtil.writeProperty("Invoice_No", Invoice_No);  
	
	//Select the Account Name
    billing.accountName.click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    String acctNameQuote=excel.getCellDataUpd("Billing","AccountName", 1);
    List<WebElement> name_List= driver.findElements(By.xpath("//div[@id='alls_tab_account_name']/ul/li"));
   
    for (WebElement liinv : name_List) 
    {
    if (liinv.getText().equals(acctNameQuote)) 
    {
    	 System.out.println(liinv.getText());
    	 liinv.click();
    	 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
         break;
       }
    }
   
	
	//------Select the Date from the-Invoice Date Date Picker	
    String invDay=excel.getCellDataUpd("Billing","InvDay", 1);
    String invMonth=excel.getCellDataUpd("Billing","InvMonth", 1);
    String invYear=excel.getCellDataUpd("Billing","InvYear", 1);
    
    billing.invoiceDate.click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    new Select(billing.calMonth).selectByVisibleText(invMonth);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    new Select(billing.calYear).selectByVisibleText(invYear);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    
    	//-------------For Selecting the Day
	List<WebElement> allDatesinv=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
	
	for(WebElement eleinv:allDatesinv)
	{
		
		String dateinv=eleinv.getText();
		System.out.println(dateinv);
		if(dateinv.equalsIgnoreCase(invDay))
		{
			eleinv.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
			break;
		}
		
	}
	
	//Select the Invoice Status
	String invStatus=excel.getCellDataUpd("Billing","invStatus", 1);
	new Select(billing.invStatus).selectByVisibleText(invStatus);
	
	
	//------Select the Date from the-Due Date Date Picker	
    String dueDay=excel.getCellDataUpd("Billing","dueDay", 1);
    String dueMonth=excel.getCellDataUpd("Billing","dueMonth", 1);
    String dueYear=excel.getCellDataUpd("Billing","dueYear", 1);
    
    billing.dueDate.click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    new Select(billing.calMonth).selectByVisibleText(dueMonth);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    new Select(billing.calYear).selectByVisibleText(dueYear);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    
    	//-------------For Selecting the Day
	List<WebElement> allDatesdue=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
	
	for(WebElement eledue:allDatesdue)
	{
		
		String datedue=eledue.getText();
		
		if(datedue.equalsIgnoreCase(dueDay))
		{
			eledue.click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
			break;
		}
		
	}
		
	//------Enter the Terms & Conditions
    String InvTermsCondition=excel.getCellDataUpd("Billing","InvTermsCondition", 1);
    billing.TermsandCondition.sendKeys(InvTermsCondition);
    
	//------Enter the Description
    String InvoiceDescription=excel.getCellDataUpd("Billing","InvDescription", 1);
    billing.QuotesDescription.sendKeys(InvoiceDescription);
    
    //Click the Details tab
    driver.findElement(By.linkText("Details")).click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    
    //Enter the Product/Service
    String productService=excel.getCellDataUpd("Billing","productService", 1);
    billing.QuotesProduct.sendKeys(productService);
    	    
    //Enter the Quantity
    billing.Quanitity.click();
    String Qunatity=excel.getCellDataUpd("Billing","Qty", 1);
    billing.Quanitity.sendKeys(Qunatity);
    
    //Enter the Rate
    billing.Rate.click();
    String Rate=excel.getCellDataUpd("Billing","Rate", 1);
    billing.Rate.sendKeys(Rate);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    
    //Select the Currency
    String Currency=excel.getCellDataUpd("Billing","Currency", 1);
    new Select(billing.Currency).selectByValue(Currency);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    

    //Click-Save Button
    billing.btnSave.click();
    
    
    //Assertion statement added for the verification
    WebDriverWait waittasksave = new WebDriverWait (driver, 20);
    waittasksave.until(ExpectedConditions.visibilityOf(deal.SuccessNotificationMsg));
    
    Assert.assertTrue(deal.SuccessNotificationMsg.getText().toString().contains("Created"));
    
  	String  baseurl=driver.getCurrentUrl();
	String arr[]=baseurl.split("/");		
	String Invoice_ID=arr[arr.length-1];
	TestUtil.writeProperty("Invoice_ID", Invoice_ID);  
    	
}	

@Test(priority = 5,groups={"Regression"},description="Edit a Invoice")

public static void editInvoice() throws InterruptedException
{
	LeadPage lead=new LeadPage();
	BillingPage billing=new BillingPage();
	
	/*-------------------------Login Code
	LoginPage login=new LoginPage();		
	login.username.clear();
    login.username.sendKeys(config.getProperty("UserName"));
	login.password.sendKeys(config.getProperty("Password"));
    login.login.click();
    System.out.println("Successfully Logged");
    wait=new WebDriverWait(driver,20); 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));
     -------------------------------------------*/

    driver.get("https://"+config.getProperty("Environment")+".convergehub.com/invoices/add/"+SavedData.getProperty("Invoice_ID"));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    //--------------------Edit  the Invoice Quantity & rate----------------------------------------------------
    
    //Click the Details tab
    driver.findElement(By.linkText("Details")).click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    
	    
    //Enter the Quantity
    billing.Quanitity.click();
    billing.Quanitity.clear();
    String Qunatitynew=excel.getCellDataUpd("Billing","QtyNew", 1);
    billing.Quanitity.sendKeys(Qunatitynew);
    
    //Enter the Rate
    billing.Rate.click();
    billing.Rate.clear();
    String RateNew=excel.getCellDataUpd("Billing","RateNew", 1);
    billing.Rate.sendKeys(RateNew);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    //Click-Save Button
    billing.btnSave.click();
    
    WebDriverWait wait = new WebDriverWait (driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='header_notification_msg']")));
    
    ////Validation that Invoice edited successfully
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='header_notification_msg']")).getText().contains("Updated"));
 	    
}	

@Test(priority=6,groups={"Regression"},description="Create a New Payment from Billing Module")

public static void CreatePayment() throws InterruptedException
{
	//Initialize the Page Class
	DealPage deal=new DealPage();
	BillingPage billing=new BillingPage();

/*-------------------------Login Code
	LoginPage login=new LoginPage();
	login.username.clear();
    login.username.sendKeys(config.getProperty("UserName"));
	login.password.sendKeys(config.getProperty("Password"));
    login.login.click();
    System.out.println("Successfully Logged");
    wait=new WebDriverWait(driver,30); 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));	
    driver.get("https://staging.convergehub.com/targets/add");
    -------------------------------------------*/
    
	//Redirecting to the Add Payment Page
	driver.get("https://"+config.getProperty("Environment")+".convergehub.com/payments/add/");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
    
    //===========Filled up the Add Payment Screen=====================

	//Select the Payment Mode
	String mode=excel.getCellDataUpd("Billing","paymentMode", 1);
	new Select(billing.paymentMode).selectByVisibleText(mode);
	
	//Enter the Originator Name
	String Originator=excel.getCellDataUpd("Billing","Originator", 1);
	billing.originatorNm.sendKeys(Originator);
	
	//Select the Currency ID
	String CurrencyID=excel.getCellDataUpd("Billing","Currency_ID", 1);
	new Select(billing.Currency_Id).selectByVisibleText(CurrencyID);
	
	//Enter the payment amount
	String paymentAmt=excel.getCellDataUpd("Billing","Payment_amount", 1);
	billing.paymentAmt.sendKeys(paymentAmt);
	
		
	//------Select the Date from the-Payment Date Date Picker	
    String payDay=excel.getCellDataUpd("Billing","payDay", 1);
    String payMonth=excel.getCellDataUpd("Billing","payMonth", 1);
    String payYear=excel.getCellDataUpd("Billing","payYear", 1);
    
    billing.paymentDate.click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    new Select(billing.calMonth).selectByVisibleText(payMonth);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    new Select(billing.calYear).selectByVisibleText(payYear);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    
    	//-------------For Selecting the Day
	List<WebElement> allDatespayment=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
	
	for(WebElement elepmt:allDatespayment)
	{
		
		String datepayment=elepmt.getText();
		System.out.println(datepayment);
		if(datepayment.equalsIgnoreCase(payDay))
		{
			elepmt.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
			break;
		}
		
	}
	
	//Set the Transaction ID
	String TransId=excel.getCellDataUpd("Billing","Transaction_Id", 1);
	billing.Transaction_id.sendKeys(TransId);
	
	//Select the RelatedTo as-Invoice
	String RelatedTo=excel.getCellDataUpd("Billing","RelatedTo", 1);
    new Select(billing.RelatedTo).selectByVisibleText(RelatedTo);
    
    //Wait for the appeariance of the Invoice popup
    WebDriverWait waitpopup = new WebDriverWait (driver, 20);
    waitpopup.until(ExpectedConditions.visibilityOf(billing.SelectInvDialog));
    
    //Click the Checkbox of the Invoice Selected earlier
    driver.findElement(By.id("list_checkbox_"+SavedData.getProperty("Invoice_ID"))).click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    
    //Enter the value in amount to be paid
    String amtPaid=excel.getCellDataUpd("Billing","AmountPaid", 1);
    driver.findElement(By.id("paid_"+SavedData.getProperty("Invoice_ID"))).sendKeys(amtPaid);
    
    //Click the Button-Add Selected
    billing.AddSelectedbtn.click();
    
    //Wait for the save button-enabled
    WebDriverWait waitSave = new WebDriverWait (driver, 20);
    waitSave.until(ExpectedConditions.visibilityOf(billing.btnSavePayment));
    billing.btnSavePayment.click();
    
    
    //Assertion statement added for the verification
    WebDriverWait waittasksave = new WebDriverWait (driver, 20);
    waittasksave.until(ExpectedConditions.visibilityOf(deal.SuccessNotificationMsg));
    
    Assert.assertTrue(deal.SuccessNotificationMsg.getText().toString().contains("Payment Created"));
    
  	String  baseurl=driver.getCurrentUrl();
	String arr[]=baseurl.split("/");		
	String Payment_ID=arr[arr.length-1];
	TestUtil.writeProperty("Payment_ID", Payment_ID);  
    	
}	

@Test(priority = 7,groups={"Regression"},description="Edit a Payment")

public static void editPayment() throws InterruptedException
{
	LeadPage lead=new LeadPage();
	BillingPage billing=new BillingPage();
	
	/*-------------------------Login Code
	LoginPage login=new LoginPage();		
	login.username.clear();
    login.username.sendKeys(config.getProperty("UserName"));
	login.password.sendKeys(config.getProperty("Password"));
    login.login.click();
    System.out.println("Successfully Logged");
    wait=new WebDriverWait(driver,20); 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));
     -------------------------------------------*/

    driver.get("https://"+config.getProperty("Environment")+".convergehub.com/payments/add/"+SavedData.getProperty("Payment_ID"));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    //--------------------Edit  the Payment Mode/Originator Name & Description----------------------------------------------------
    
    //Update-Payment Mode
  	String modeupd=excel.getCellDataUpd("Billing","EdtMode", 1);
  	new Select(billing.paymentMode).selectByVisibleText(modeupd);
  	
  	//Update the Originator Name
  	String OriginatorUpd=excel.getCellDataUpd("Billing","EdtOriginator", 1);
  	billing.originatorNm.sendKeys(OriginatorUpd);  	
  	
  	//Update the payment amount
  	String paymentAmtUpd=excel.getCellDataUpd("Billing","EdtPayment_amount", 1);
  	billing.paymentAmt.clear();
  	billing.paymentAmt.sendKeys(paymentAmtUpd);
  	
 	//Update  payment Description
  	String paymentdesc=excel.getCellDataUpd("Billing","EdtDescription", 1);
  	billing.Description.clear();
  	billing.Description.sendKeys(paymentdesc);
  	
  	//Click-Save Button
  	billing.btnSavePayment.click();
    
    WebDriverWait wait = new WebDriverWait (driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='header_notification_msg']")));
    
    ////Validation that Payment  edited successfully
    Assert.assertTrue(driver.findElement(By.xpath("//span[@id='header_notification_msg']")).getText().contains("Updated"));
 	    
}	
		
}
