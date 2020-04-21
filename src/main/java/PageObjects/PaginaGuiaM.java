package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PaginaGuiaM {

	public WebDriver driver;
	
	public PaginaGuiaM(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	private By CBusca = By.id("campo_pesquisa");
	private By BBusca = By.id("btn_pesquisar");
	private By Estado = By.xpath("//div[text()='Estado']");
	private By Cidade = By.xpath("//div[text()='Cidade']");
	private By Unimed = By.cssSelector("form[class*='escolher-unimed'] input");
	private By Cont   =	By.cssSelector("[class='btn btn-success']");
	private By Endereco = By.id("txt_endereco");
	private By Pesquisa = By.cssSelector("div[class*='center-responsivo padding'] p");
	private By ProxPag  = By.cssSelector("a[class='proximo'] i");
	
	public WebElement getCampoBusca()
	{
		return driver.findElement(CBusca);
	}

	public WebElement getBotaoBusca()
	{
		return driver.findElement(BBusca);
	}
	
	public Actions getEstado()
	{
		Actions a = new Actions(driver);	
		return a.moveToElement(driver.findElement(Estado));
	}
	
	public Actions getCidade()
	{
		Actions a = new Actions(driver);	
		return a.moveToElement(driver.findElement(Cidade));
	}

	public WebElement getUnimed()
	{
		return driver.findElement(Unimed);
	}
	
	public WebElement getContinuar()
	{
		return driver.findElement(Cont);
	}
	
	public List<WebElement> getEndereco()
	{
		return driver.findElements(Endereco);
	}
	
	public WebElement getObjetoPesquisa()
	{
		return driver.findElement(Pesquisa);
	}
	
	public WebElement getProximaPagina()
	{
		return driver.findElement(ProxPag);
	}
}
