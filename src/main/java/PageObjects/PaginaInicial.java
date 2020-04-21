package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaInicial {
	
	public WebDriver driver;
	
		private By guia  = By.xpath("//*[text()=' Guia Médico ']");
		
		public PaginaInicial(WebDriver driver) {
			// TODO Auto-generated constructor stub
			this.driver = driver;
		}
		
		public WebElement getGuiaMedico()
		{
			return driver.findElement(guia);
		}
}
