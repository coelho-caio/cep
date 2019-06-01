package com.fatec.teste;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormCorreios {
	private WebDriver driver;
	private String baseUrl;
	Logger logger = Logger.getLogger("FormManterLivro");

	public FormCorreios(WebDriver driver) {
		this.driver = driver;
		baseUrl = "http://www.buscacep.correios.com.br/sistemas/buscacep/buscaCep.cfm";
	}

	public void abre() {
		driver.get(baseUrl);
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public void consultaCEP(String uf, String localidade, String tipo, String logradouro) {
		{
			WebElement dropdown = driver.findElement(By.name("UF"));
			dropdown.findElement(By.xpath("//option[. = '" + uf + "']")).click();
		}
		espera();
		driver.findElement(By.name("Localidade")).sendKeys(localidade);
		{
			WebElement dropdown = driver.findElement(By.name("Tipo"));
			dropdown.findElement(By.xpath("//option[. = '" + tipo + "']")).click();
		}
		driver.findElement(By.name("Tipo")).click();
		driver.findElement(By.name("Logradouro")).sendKeys(logradouro);
		driver.findElement(By.cssSelector(".btn2")).click();
		espera();
	}

	public void espera() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}