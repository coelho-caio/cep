package com.fatec.teste;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UC02ConsultaCEPv2 {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	private FormCorreios form;

	@BeforeClass
	public static void inicializa() {
		try {
			ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Planilha1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "driverBrowser/chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		inicializa();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Ignore
	@Test
	public void CT01ConsultaComSucesso_v2() {
		form = new FormCorreios(driver);
		form.abre();
		form.consultaCEP("SP", "São Paulo", "Rua", "Taquari");
		// resultado obtido X resultado esperado
		assertThat(driver.findElement(By.cssSelector("p")).getText(), is("DADOS ENCONTRADOS COM SUCESSO."));
	}

	@Test
	public void CT01Consulta() {
		form = new FormCorreios(driver);
		for (int i = 1; i < 5; i++) { // ajustar a quantidade de linhas de acordo com a planilha
			try {
				form.abre();
				form.consultaCEP(ExcelUtils.getCellData(i, 0), ExcelUtils.getCellData(i, 1),
						ExcelUtils.getCellData(i, 2), ExcelUtils.getCellData(i, 3));
				// resultado obtido X resultado esperado armazenado na coluna 4 e 5 da planilha
				espera();
				if (ExcelUtils.getCellData(i, 5).equals("alert")) {
					assertThat(driver.switchTo().alert().getText(), is(ExcelUtils.getCellData(i, 4)));
					driver.switchTo().alert().accept();
				} else {
					assertThat(driver.findElement(By.cssSelector("p")).getText(), is(ExcelUtils.getCellData(i, 4)));
				}
			} catch (Exception e) {
				fail("Falha no teste linha ========> " + i + "- mensagem =" + e.getMessage());
			}
		}
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