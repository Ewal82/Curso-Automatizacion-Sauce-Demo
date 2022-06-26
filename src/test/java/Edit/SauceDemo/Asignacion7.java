package Edit.SauceDemo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.DatosExcel;

public class Asignacion7 {
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

	@Test(dataProvider = "Datos Login generar compra Saucedemo")

	public void generarOrden(String Usuario, String Contraseña, String Nombre, String Apellido, String CodigoPostal) {

//paso 1: Ingresar Usuario
		driver.findElement(By.cssSelector("#user-name")).clear();
		driver.findElement(By.cssSelector("#user-name")).sendKeys(Usuario);

//paso 2: Ingresar contraseña
		driver.findElement(By.cssSelector("#password")).clear();
		driver.findElement(By.cssSelector("#password")).sendKeys(Contraseña);

//paso 3: hacer Click en el boton login
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

		String urlEsperadaLogin = "https://www.saucedemo.com/inventory.html";
		String urlActualLogin = driver.getCurrentUrl();
		Assert.assertEquals(urlActualLogin, urlEsperadaLogin);

//Es necesario una espera
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-to-cart-sauce-labs-backpack")));

//paso 4: Seleccionar el producto (Sauce Labs Bolt T-Shirt)
		driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt")).click();

//paso 5: hacer click en el Carrito
		driver.findElement(By.xpath("//span[contains(text(),'1')]")).click();

//paso 6: se verifica el producto seleccionado y se procede a hacer Checkout
		driver.findElement(By.xpath("//button[@id='checkout']")).click();

//Es necesario una espera: Informacion Personal
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#first-name")));

//paso 7: nombre
		driver.findElement(By.cssSelector("#first-name")).clear();
		driver.findElement(By.cssSelector("#first-name")).sendKeys(Nombre);
//paso 8: apellido
		driver.findElement(By.cssSelector("#last-name")).clear();
		driver.findElement(By.cssSelector("#last-name")).sendKeys(Apellido);
//paso 9: codigo postal
		driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys(CodigoPostal);

//paso 10: hacer click en continuar
		driver.findElement(By.xpath("//input[@id='continue']")).click();

		String urlCheckOut = "https://www.saucedemo.com/checkout-step-one.html";
		Assert.assertNotEquals(driver.getCurrentUrl(), urlCheckOut);

//Es necesario una espera
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait3.until(ExpectedConditions.elementToBeClickable(By.name("finish")));

//paso 11: se verifica la orden y hacer click en Finalizar
		driver.findElement(By.cssSelector("#finish")).click();

//Comprobaciones y variables
		String urlEsperada = "https://www.saucedemo.com/checkout-complete.html";
		String TituloEsperado = "Swag Labs";
		String urlActual = driver.getCurrentUrl();
		String TituloActual = driver.getTitle();
//Asserts
		Assert.assertEquals(urlActual, urlEsperada);
		Assert.assertEquals(TituloActual, TituloEsperado);

//Logout
		driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logout_sidebar_link")));

		driver.findElement(By.cssSelector("#logout_sidebar_link")).click();

	}

	@DataProvider(name = "Datos Login generar compra Saucedemo")
	public Object[][] obtenerDatosExcel() throws Exception {
		return DatosExcel.leerExcel("..\\SauceDemo/Datos/Datos Login generar compra Saucedemo.xlsx", "Datos");

	}

	@AfterSuite

	public void CerrarNavegador() {
		driver.close();

	}

}