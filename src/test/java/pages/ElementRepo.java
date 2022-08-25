package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.Driver.getDriver;

public class ElementRepo {
    WebDriverWait wait;

    public ElementRepo(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[@title='hayat sana gelir']")
    public WebElement anaSayfaLogo;
    @FindBy(xpath = "//*[@title='Giriş Yap']")
    public WebElement girisButton;
    @FindBy(id = "email")
    public WebElement emailKutu;
    @FindBy(id = "password")
    public WebElement sifreKutu;
    @FindBy(id = "loginButton")
    public WebElement girisYapButon;
    @FindBy(xpath = "//*[@title='Hesabım']")
    public WebElement hesabımLogo;
    @FindBy(id = "searchData")
    public WebElement aramaKutusu;
    @FindBy(xpath = "//*[@title='Favorilerim / Listelerim']")
    public WebElement favoriListelerSekme;
    @FindBy(xpath ="(//h4[@class='listItemTitle'])[1]" )
    public WebElement favoriListeSayfa;
    @FindBy(xpath = "//*[@class='deleteProFromFavorites']")
    public WebElement favoriUrunSilButton;
    @FindBy(xpath = "//*[text()='Ürününüz listeden silindi.']")
    public WebElement urunSilindiText;
    @FindBy(xpath = "//*[text()='Tamam']")
    public WebElement tamamButton;
    @FindBy(xpath = "//*[text()='Çıkış Yap']")
    public WebElement cikisYapButon;

    public void anaSayfaKontrol(){
        assertTrue(anaSayfaLogo.isDisplayed());
        String actualurl = getDriver().getCurrentUrl();
        String expectedurl = "https://www.n11.com/";
        assertEquals(expectedurl, actualurl);

    }

    public void loginSayfasinaGirisYap(){

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(webDriver -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete"));
        girisButton.click();
        Driver.wait(3);
    }

    public void siteyeLoginOl(){
       // emailKutu.sendKeys("n11uyelikadres@gmail.com");
       // sifreKutu.sendKeys("n11yeniuye");
       //   girisYapButon.click();

        Actions a=new Actions(getDriver());
        a.sendKeys("n11uyelikadres@gmail.com" + Keys.TAB).sendKeys("n11yeniuye" + Keys.ENTER).perform();
       Driver.wait(5);

    }

    public void loginKontrol() {
        assertTrue(hesabımLogo.isDisplayed());
    }

    public void urunAra(String urun){
        aramaKutusu.sendKeys(urun, Keys.ENTER);
    }

    public void arananUrunKontrol(String arananurun){
        assertTrue(getDriver().getCurrentUrl().contains(arananurun));
    }

    public void istenilenSayfayaGit(String sayfaNo){
        String path= "(//div[@class='pagination'])/a[contains(text(),'"+sayfaNo+"')]";
        getDriver().findElement(By.xpath(path)).click();
    }

    public void gidilenSayfaKontrol(String gidilenSayfa){
        assertTrue(getDriver().getCurrentUrl().contains(gidilenSayfa));
    }

    public void istenilenUrunFavoriyeEkleme(String urunNo){
        String path="(//div[@id='view']/ul/li["+urunNo+"])//*[@title='Favorilere ekle']";
        getDriver().findElement(By.xpath(path)).click();

    }
    public void favorilerimSayfasinaGit(){
        ReusableMethods.hoverover(hesabımLogo);
        favoriListelerSekme.click();
    }

    public void favorilerimSayfasiDogrula(){
        assertTrue(getDriver().getCurrentUrl().contains("listelerim"));
    }

    public void favoriUrunSil(){
        favoriListeSayfa.click();
        favoriUrunSilButton.click();
    }

    public void silindiDoğrula(){
        assertTrue(urunSilindiText.isDisplayed());
        tamamButton.click();
    }
    public void logOut(){
        Driver.wait(3);
        ReusableMethods.hoverover(hesabımLogo);
        Driver.waitAndClick(cikisYapButon);
    }
}
