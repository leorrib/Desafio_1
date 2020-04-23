package stepDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import cucumber.api.junit.Cucumber;

import org.junit.Assert;
import resources.base;

import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.PaginaGuiaM;
import PageObjects.PaginaInicial;

@RunWith(Cucumber.class)
public class StepDefinition extends base {

	@Dado("^Usuário pesquisa por \"([^\"]*)\" no site da unimed$")
	public void usuario_pesquisa_por_medicos_no_site_da_unimed(String busca) throws Throwable {
		driver = initializeDriver();
		driver.get("https://www.unimed.coop.br/");

		PaginaInicial ini = new PaginaInicial(driver);
		ini.getGuiaMedico().click();

		PaginaGuiaM guia = new PaginaGuiaM(driver);
		guia.getCampoBusca().sendKeys(busca);
		guia.getBotaoBusca().click();
	}

	@Quando("^Restringe a busca ao estado \"([^\"]*)\" e cidade \"([^\"]*)\"$")
	public void usuario_escolhe_estado_e_cidade(String estado, String cidade) throws Throwable {
		PaginaGuiaM guia = new PaginaGuiaM(driver);
		WebDriverWait w = new WebDriverWait(driver, 10);

		guia.getEstado().click().sendKeys(estado, Keys.ENTER).build().perform();
		guia.getCidade().click().sendKeys(cidade, Keys.ENTER).build().perform();
		w.until(ExpectedConditions.visibilityOf(guia.getText()));
		guia.getUnimed().click();
		guia.getContinuar().click();
	}

	@Entao("^Analisa se a busca por \"([^\"]*)\" se restringiu a \"([^\"]*)\"$")
	public void verificar_se_os_parâmetros_da_busca_foram(String busca, String cidade) throws Throwable {
		PaginaGuiaM guia = new PaginaGuiaM(driver);

		Assert.assertTrue(guia.getObjetoPesquisa().getText().contains(busca));
		
		for (int i = 0; i < guia.getEndereco().size(); i++) {
			if (!guia.getEndereco().get(i).getText().contains(cidade)) {
				Assert.assertTrue(false);
			}
		}
		driver.close();
	}

	@Entao("^Analisa se a busca por \"([^\"]*)\" gerou resultados em \"([^\"]*)\" nas \"([^\"]*)\" primeiras páginas$")
	public void analisa_se_a_busca_gerou_resultados_corretors(String busca, String cidade2, Integer numero)
			throws Throwable {
		PaginaGuiaM guia = new PaginaGuiaM(driver);

		for (int j = 1; j <= 3; j++) {
			for (int i = 0; i < guia.getEndereco().size(); i++) {
				if (guia.getEndereco().get(i).getText().contains(cidade2)) {
					Assert.assertTrue(false);
				}
			}
			if (j < numero) {
				Thread.sleep(1000);
				guia.getProximaPagina().click();
			}
		}
		driver.close();
	}
}