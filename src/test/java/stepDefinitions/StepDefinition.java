package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import resources.base;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.PaginaGuiaM;
import PageObjects.PaginaInicial;

@RunWith(Cucumber.class)
public class StepDefinition extends base {

    @Given("^Inicializar o browser$")
    public void inicializar_o_browser() throws Throwable {
    	driver = initializeDriver();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    }
    
	@And("^Entrar no site \"([^\"]*)\"$")
	public void entrar_no_site(String url) throws Throwable {
		driver.get(url);
	}


    @And("^Clicar em -Guia Médico-$")
    public void clicar_em_guia_medico() throws Throwable {
    	
    	PaginaInicial ini = new PaginaInicial(driver);
		ini.getGuiaMedico().click();
    }

    @When("^Usuário digita (.+) no campo de busca e clica em Pesquisar$")
    public void usuario_digita_no_campo_de_busca_e_clica_em_Pesquisar(String busca) throws Throwable {
    	PaginaGuiaM guia = new PaginaGuiaM(driver);
    	WebDriverWait w = new WebDriverWait(driver,10);

		guia.getCampoBusca().sendKeys(busca);
		w.until(ExpectedConditions.elementToBeClickable(guia.getBotaoBusca()));
		guia.getBotaoBusca().click();
    }
    
    @And("^Usuário escolhe (.+) no campo Estado e (.+) no campo Cidade, clica em UnimedRio e em Continuar$")
    public void usuario_escolhe_estado_e_cidade(String estado, String cidade) throws Throwable {
    	PaginaGuiaM guia = new PaginaGuiaM(driver);
    	WebDriverWait w = new WebDriverWait(driver,10);

		guia.getEstado().click().sendKeys(estado, Keys.ENTER).build().perform();
    	guia.getCidade().click().sendKeys(cidade, Keys.ENTER).build().perform();
    	w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Essa região')]")));
    	guia.getUnimed().click();
    	guia.getContinuar().click();
    }

	@Then("^Verificar se os parâmetros da busca foram (.+) e (.+)$")
    public void verificar_se_os_parâmetros_da_busca_foram(String busca, String cidade) throws Throwable {
    	PaginaGuiaM guia = new PaginaGuiaM(driver);
    	
    	Assert.assertTrue(guia.getObjetoPesquisa().getText().contains(busca));
    	
    	for(int i = 0; i < guia.getEndereco().size(); i++)
    	{
    		if(!guia.getEndereco().get(i).getText().contains(cidade))
    		{
    			System.out.println("Houve um erro e a pesquisa não se restringiu à/ao " + cidade);
    			break;
    		}
    	}
    	System.out.println("A busca foi bem-sucedida e apenas " + cidade + " apareceu na pesquisa");
    }
	
	@Then("^Verificar se a cidade (.+) aparece em algum resultado das (.+) primeiras páginas$")
	public void verificar_se_a_cidade_aparece(String cidade2, Integer paginas) throws Throwable {
    	PaginaGuiaM guia = new PaginaGuiaM(driver);
    	
    	for(int j = 1; j <= 3; j++)
    	{
    		
    		for(int i = 0; i < guia.getEndereco().size(); i++)
    		{
    			if(guia.getEndereco().get(i).getText().contains(cidade2))
    			{
    			System.out.println("Houve um erro e " + cidade2 + " apareceu na pesquisa");
    			break;
    			}
    		}
    		
    	System.out.println("A busca foi bem-sucedida e " + cidade2 + " não apareceu na pesquisa na"
    			+ " página " + j);
    	
    		if(j < paginas)
    		{
    			Thread.sleep(1000);
    			guia.getProximaPagina().click();
    		}
    	}
    }
	@Then("^Fechar o browser$")
    public void fechar_o_browser() throws Throwable {
    	driver.close();
    }
	
	
}
