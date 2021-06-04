package MavenProject.CloudBankSeleniumMavenProject;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class App 
{
	
	public WebDriver driver;
	public String url="http:/salesdemo.habiletechnologies.com/";
	public static final String  username= "qatest@habile.in";
	public static final String password = "Qatest123$";
	public static final String emailAddress = "vijay@gmail.com";
	public static final String  firstname= "TestUser Vijay";
	public static final String lastname = "Jeyakumar";
	public static final String gender = "Male";
	public static final String  clientType= "Merchant";
 	
	@BeforeTest
    public void launchBrowser() {
		//Using ChromeDriver For This Project
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
    	driver.get(url);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
	
    @Test
    public void TestCase() throws InterruptedException {
    	//Login to CloudBankIn
    	driver.findElement(By.name("username")).sendKeys(username);;
    	driver.findElement(By.name("password")).sendKeys(password);
    	driver.findElement(By.id("login-button")).click();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(2000);
    	//Exception if login fails
    	try {
    		driver.findElement(By.xpath("//*[@id=\"expertsearch\"]/h3/strong"));
    	}
    	catch(NoSuchElementException e) {
    		System.out.println("Login Failed");
    		
    	}
    	
    	//Go to client menu,select client and then create client 
    	driver.findElement(By.xpath("//*[@id=\"main-menu-left\"]/li[1]")).click();
    	driver.findElement(By.id("client-dropdown")).click();
    	Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"swatch-menu\"]/li[1]/a")).click();
        driver.findElement(By.id("client_createbutton")).click();
        
        //Fill Form to create client
        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("mobileNo")).sendKeys("9942222222");
        driver.findElement(By.id("emailAddress")).sendKeys(emailAddress);
        
        driver.findElement(By.xpath("//*[@id=\"clienttypeId_chosen\"]/a/span")).click();
        WebElement clientInput = driver.findElement(By.xpath("//*[@id=\"clienttypeId_chosen\"]/div/div/input"));
        clientInput.sendKeys(clientType);
        clientInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        
        driver.findElement(By.xpath("//*[@id=\"genderId_chosen\"]/a/span")).click();
        WebElement genderInput =driver.findElement(By.xpath("//*[@id=\"genderId_chosen\"]/div/div/input"));
        genderInput.sendKeys(gender);
        genderInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
       
        driver.findElement(By.xpath("//*[@id=\"clientClassificationId_chosen\"]/a/span")).click();
        WebElement classificationInput =driver.findElement(By.xpath("//*[@id=\"clientClassificationId_chosen\"]/div/div/input"));
        classificationInput.sendKeys("Sin");
        classificationInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        
        driver.findElement(By.id("save")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       
        try {
    		driver.findElement(By.xpath("//*[@id=\"mifos-reskin-body-view\"]/div/div/div/div/div[1]/h3/strong"));
    	}
    	catch(NoSuchElementException e) {
    		System.out.println("Create Client Failed");
    		Assert.fail("Create client is not successful");
    	}
        Thread.sleep(1000);
        
        //Verify Account
    	driver.findElement(By.id("client-dropdown")).click();
    	Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"swatch-menu\"]/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"mifos-reskin-body-view\"]/div/div/div/div[2]/form/div/div[1]/div/input")).sendKeys(firstname+lastname);
        driver.findElement(By.xpath("//*[@id=\"mifos-reskin-body-view\"]/div/div/div/div[2]/form/div/div[1]/div/span/button")).click();
 
   }  	   
    
    @AfterTest
    public void terminateBrowser(){
        driver.close();
    }
    public static void main( String[] args )
    {  	
    		    	
  	}
 

}
			