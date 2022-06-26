package Edit.SauceDemo;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Asignacionclase2 {
	String url = "https://www.saucedemo.com/";
	String chromeDriverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	String firefoxDriverPath = "..\\SauceDemo\\Drivers\\geckodriver.exe";

	@Test
	public void HacerBusquedaEnChrome() {
		// Ubicacion del driver del navegador
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		// Paso A: Abra el navegador Chrome
		WebDriver navegador = new ChromeDriver();
		// paso B: Acceda a la página
		navegador.get(url);

		// Paso C: Escriba el usuario
		WebElement usuario = navegador.findElement(By.id("user-name"));
		usuario.sendKeys("standard_user");
		// Paso D: Escriba la contraseña
		WebElement contraseña = navegador.findElement(By.id("password"));
		contraseña.sendKeys("secret_sauce");

		// Paso E: Haga clic en el botón LOGIN
		WebElement login = navegador.findElement(By.id("login-button"));
		login.click(); // Simular que hacemos click

	}
}
