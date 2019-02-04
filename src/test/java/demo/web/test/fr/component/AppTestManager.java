package demo.web.test.fr.component;


import demo.web.test.fr.component.*;
import java.awt.Toolkit;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.opera.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.*;

import com.google.common.base.Strings;

public class AppTestManager {
	
	String testvar="test";
	private String BROWSER=System.getProperty("browser");
	WebDriver driver = null;
	String strtrace = "";
	String output = "";
	Boolean isError=false;


	public String getBrowser() {
		if (Strings.isNullOrEmpty(BROWSER) ) {
			return "chrome";
		}
		else {
			return BROWSER.toLowerCase();
		}
	}
	public String getTrce() {
		   return strtrace;
	} 
	
	public Boolean getIsError() {
		   return isError;
	}

	public String getOutput() {
		   return output;
	}
	
	public WebDriver getDriver() {
		   return driver;
	}
	
	public AppTestManager()
	{
		
		
	}
	public void setBrowser(String browser) {
		BROWSER =browser;
	}
	public void initBrowser() throws Exception {
		System.out.println("selected browser=" + this.getBrowser());
		DesiredCapabilities cap = null;
		//String Node = "http://10.20.11.51:4445/wd/hub";
		//String Node = "http://10.20.11.20:31215/wd/hub";
		String Node = "http://10.20.11.20:32083/wd/hub";
		if (this.getBrowser().equals("chrome")) {			
	 		cap = DesiredCapabilities.chrome();	 		
			//driver = new ChromeDriver(RemoteWebDriverManager.getChromeOptions());
		}
		else if (this.getBrowser().equals("firefox")) {
			cap = DesiredCapabilities.firefox();
			//driver = new FirefoxDriver(RemoteWebDriverManager.getFirefoxOptions());
		}
		else if (this.getBrowser().equals("ie")) {
			cap = DesiredCapabilities.internetExplorer();
			cap.setVersion("11");
			cap.setPlatform(Platform.WINDOWS);
			
			//driver = new InternetExplorerDriver(RemoteWebDriverManager.getIEOptions());
		}
		else {
			//if not specified then use chrome
			cap = DesiredCapabilities.chrome();	
			//driver = new ChromeDriver(RemoteWebDriverManager.getChromeOptions());
		}
		driver = new Augmenter().augment(new RemoteWebDriver(new URL(Node), cap));
		//driver =  new RemoteWebDriver(new URL(Node), cap);
		//driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		
	}
	/*public void initBrowser() {
		System.out.println("selected browser=" + this.getBrowser());
		
		if (this.getBrowser().equals("chrome")) {
			driver = new ChromeDriver(RemoteWebDriverManager.getChromeOptions());
		}
		else if (this.getBrowser().equals("firefox")) {
			driver = new FirefoxDriver(RemoteWebDriverManager.getFirefoxOptions());
		}
		else {
			//if not specified then use chrome
			driver = new ChromeDriver(RemoteWebDriverManager.getChromeOptions());
		}
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int Width = (int) toolkit.getScreenSize().getWidth();
		int Height = (int)toolkit.getScreenSize().getHeight();
		driver.manage().window().setSize(new Dimension(Width,Height));
		
	}*/
	
	public void navigateToTargetPage(String url)
	{
		 System.out.println("navigateToTargetPage-in");
	    
		 Actions x = new Actions(driver);
		 try{
			 x.build().wait(10);
		 }
		 catch(Exception ex){
			 //swallow this exception
			 String tmp=""; 
		 }
	    driver.get(url);
	    
	    System.out.println("navigateToTargetPage-out");
	    //this.sleep(1000);
	}
	
	

	public void enterFirstTextBox(String ip1)
	{
	    WebElement ele1 = driver.findElement(By.id("a"));
	    ele1.sendKeys(ip1);
	}
	public void enterSecondTextBox(String ip2)
	{
	    WebElement ele1 = driver.findElement(By.id("b"));
	    ele1.sendKeys(ip2);
	}
	public void clickConcatenateButton()
	{
			WebElement ele3 = driver.findElement(By.id("c"));
			
			Actions actions = new Actions(driver);
			actions.click(ele3).perform();
	}
	
	public void validateThirdTextBox(String ip3)
	{
	    try
	    {
	        WebElement ele4 = driver.findElement(By.id("d"));
	        output = ele4.getAttribute("value");
	        if (Strings.isNullOrEmpty(output))
	        {
	            output = "No value found";
	        }
	    }
	    catch (Exception ex)
	    {
	        output = "Unexpected Error: " + ex.getMessage();
	    }

	    strtrace = "Expected output=" + ip3 + "; Generated output=" + output;
	  
	}
	


	public void closeTest()
	{
		System.out.println(this.strtrace);
		System.out.println("Driver destroying");
	   
	    closeDriver();
	    System.out.println("Driver destroyed");
	   
	    strtrace = "";
	    output = "";
	}

	private void closeDriver()
	{
	    try
	    {
	        if (driver != null)
	        {
	            driver.close();
	        }
	        
	        if (driver != null)
	        {
	            driver.quit();
	        }
	    }
	    catch (Exception ee)
	    {
	    	if (driver != null)
	        {
	            driver.quit();
	        }
	    }
	   
	}


	
	private void sleep(int time){
		try {
			Thread.sleep(1000);
		}
		catch(Exception ex){
			//swallow exception
		}
	}

}
