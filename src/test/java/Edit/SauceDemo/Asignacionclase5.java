package Edit.SauceDemo;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;

public class Asignacionclase5 {
	String url = "https://www.saucedemo.com/";
	String DriverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	WebDriver driver;
	File pantalla;
	String dirEvidencias = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "SauceDemo.docx";

	@BeforeSuite
	public void abrirPagina() { // setUp
		// Ubicacion del driver del navegador
		System.setProperty("webdriver.chrome.driver", DriverPath);

		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test(description = "login", priority = 3)
	public void login() throws InvalidFormatException, IOException, InterruptedException {
		// configurando el documento de evidencias
		CapturaEvidencia.escribirTituloEnDocumento(dirEvidencias + nombreDocumento, "Evidencias de Prueba Sauce Demo",
				18);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento,
				"pantalla inicio");

		// Paso 1: ingresar el usuario
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");

		// Paso 2: ingresar la contraseña
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");

		// Paso 3: Hacer click en el botón LOGIN
		driver.findElement(By.cssSelector("#login-button")).click();

		// Comprobaciones para verificar que se haya logueado

		String urlEsperada = "https://www.saucedemo.com/inventory.html";
		String tituloEsperado = "Swag Labs";

		String urlActual = driver.getCurrentUrl(); // Devuelve la URL actual de la pagina
		String tituloActual = driver.getTitle(); // Devuelve el titulo actual de la pagina

		Assert.assertEquals(urlActual, urlEsperada);
		Assert.assertEquals(tituloActual, tituloEsperado);

	}

	@Test(description = "seleccionar producto y completar la orden", priority = 7)
	public void CompletarOrden() throws InvalidFormatException, IOException, InterruptedException {
		// configurando el documento de evidencias

		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento,
				"Pantalla Productos");

		// paso 1: seleccionar producto
		driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt")).click();

		// paso 2: hacer click en el carrito
		driver.findElement(By.xpath(
				"//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='header_container']/div[1]/div[3]/a[1]"))
				.click();

		// paso 3: hacer click en checkout
		driver.findElement(By.xpath("//button[@id='checkout']")).click();

		// paso 4: nombre
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Homero");

		// paso 5: apellido
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Simpsons");

		// paso 6: codigo postal
		driver.findElement(By.cssSelector("#postal-code")).sendKeys("12943");

		// paso 7: hacer click en continuar
		driver.findElement(By.xpath("//input[@id='continue']")).click();

		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento,
				"Luego de hacer click en continuar");

		// paso 8: hacer click en finalizar
		driver.findElement(By.cssSelector("#finish")).click();

		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento,
				"Luego de hacer click en finalizar");

		// Comprobaciones para verificar que se haya seleccionado el producto y
		// completar la orden

		String urlEsperada = "https://www.saucedemo.com/checkout-complete.html";
		String tituloEsperado = "Swag Labs";

		String urlActual = driver.getCurrentUrl(); // Devuelve la URL actual de la pagina
		String tituloActual = driver.getTitle(); // Devuelve el titulo actual de la pagina

		Assert.assertEquals(urlActual, urlEsperada);
		Assert.assertEquals(tituloActual, tituloEsperado);
	}

	@AfterSuite
	public void cerrarPagina() { // tearDown
		driver.close();
	}
}