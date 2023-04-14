import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class AlertTest {
    private static WebDriver driver;

    @Test
    public void testAlertaNaoAberto() {
        assertFalse(isAlertPresent());
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Abrir a página com os alertas
        driver.get("https://igorsmasc.github.io/alertas_atividade_selenium/");
    }

    @Test
    public void testAlertaSimplesPositivo() {
        WebElement btnAlertaSimples = driver.findElement(By.xpath("//button[text()='Alerta Simples']"));
        btnAlertaSimples.click();
        Alert alert = driver.switchTo().alert();
        String textoAlerta = alert.getText();
        assertEquals("Este é um alerta simples!", textoAlerta);
        alert.accept();
    }

    @Test
    public void testAlertaConfirmacaoPositivo() {
        WebElement btnAlertaConfirmacao = driver.findElement(By.xpath("//button[2]"));
        btnAlertaConfirmacao.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        assertEquals("Item excluído com sucesso!", alert.getText());
    }

    @Test
    public void testAlertaConfirmacaoNegativo() {
        WebElement btnAlertaConfirmacao = driver.findElement(By.xpath("//button[2]"));
        btnAlertaConfirmacao.click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        assertEquals("A exclusão foi cancelada.", alert.getText());
    }

    @Test
    public void testAlertaEntradaPositivo() {
        WebElement btnAlertaEntrada = driver.findElement(By.xpath("//button[3]"));
        btnAlertaEntrada.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("João da Silva");
        alert.accept();
        assertEquals("Bem-vindo, João da Silva!", alert.getText());
    }

    @AfterEach
    public void tearDown() {
        // Fechar o navegador
        driver.quit();
    }

    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }
    }
}
