package Pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Paginas.HomePage;

public class SauceDemoTest {
	String url = "https://www.saucedemo.com/";
	String DriverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	WebDriver driver;


	@BeforeSuite

	public void AbrirPaginaSauceDemo() {
		System.setProperty("webdriver.chrome.driver", DriverPath);
		
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

}
	@Test
	public void Login() {
		HomePage inicio = new HomePage(driver);
		
		inicio.escribirUsuario("standard_user");
		inicio.escribirContraseña("secret_sauce");
		inicio.HacerclicEnLogin();
	}
	
}
