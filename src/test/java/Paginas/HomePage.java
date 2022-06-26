package Paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	// Elementos de la pagina web
	@FindBy(id= "user-name" )
	WebElement txtBuscadorUserName;
	@FindBy(name= "password" )
	WebElement txtBuscadorPasswrod;
	@FindBy(id= "login-button" )
	WebElement btnLogin; 
	
	// Constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Acciones sobre los elementos de la pagina
	public void escribirUsuario(String usuario) {
		txtBuscadorUserName.sendKeys (usuario);
	}
	public void	escribirContraseña(String contraseña) {
		txtBuscadorPasswrod.sendKeys (contraseña);	
	}
	public void HacerclicEnLogin () {
		btnLogin.click();
	}

}
