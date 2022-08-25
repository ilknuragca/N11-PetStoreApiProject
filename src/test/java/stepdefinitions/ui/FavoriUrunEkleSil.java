package stepdefinitions.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ElementRepo;
import utilities.ConfigReader;
import utilities.Driver;

public class FavoriUrunEkleSil {

    ElementRepo er =new ElementRepo();

    @Given("kullanıcı {string} sayfasına gider")
    public void kullanıcı_sayfasına_gider(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        Driver.wait(5);
    }
    @Then("ana sayfanın açıldığı kontrol edilir")
    public void ana_sayfanın_açıldığı_kontrol_edilir() {
         er.anaSayfaKontrol();
         Driver.wait(3);
    }
    @When("kullanıcı giris yap sekmesine tıklar")
    public void kullanıcı_giris_yap_sekmesine_tıklar() {
         Driver.wait(5);
         er.loginSayfasinaGirisYap();
    }
    @When("kullanıcı siteye email ve sifre ile Login olur")
    public void kullanıcı_siteye_email_ve_sifre_ile_login_olur() {
        er.siteyeLoginOl();
    }

    @Then("login işlemi kontrol edilir")
    public void login_işlemi_kontrol_edilir() {
         er.loginKontrol();
    }
    @When("kullanıcı search kutusuna {string} kelimesi yazarak arama yapar")
    public void kullanıcı_search_kutusuna_kelimesi_yazarak_arama_yapar(String arananUrun) {
         er.urunAra(arananUrun);
    }
    @Then("{string} kelimesi aratıldığı kontrol edilir")
    public void kelimesi_aratıldığı_kontrol_edilir(String arananUrun) {
        er.arananUrunKontrol(arananUrun);
    }
    @When("kullanıcı arama sonuçları sayfasından {string}. sayfayı açar")
    public void kullanıcı_arama_sonuçları_sayfasından_sayfayı_açar(String sayfaNo) {
        er.istenilenSayfayaGit(sayfaNo);
    }
    @Then("{string} sayfanın açıldığı kontrol edilir")
    public void sayfanın_açıldığı_kontrol_edilir(String sayfaNo) {
        er.gidilenSayfaKontrol(sayfaNo);
    }
    @When("kullanıcı sayfada ki {string}. ürünü favorilerine ekler")
    public void kullanıcı_sayfada_ki_ürünü_favorilerine_ekler(String urunNo) {
       er.istenilenUrunFavoriyeEkleme(urunNo);
    }
    @When("kullanıcı Hesabım bölümünden Favorilerim sayfasına gider")
    public void kullanıcı_hesabım_bölümünden_favorilerim_sayfasına_gider() {
        er.favorilerimSayfasinaGit();
    }
    @Then("Favorilerim sayfasının açıldığı kontrol edilir")
    public void favorilerim_sayfasının_açıldığı_kontrol_edilir() {
        er.favorilerimSayfasiDogrula();
    }
    @When("kullanıcı eklediği ürünü favorilerden siler")
    public void kullanıcı_eklediği_ürünü_favorilerden_siler() {
        er.favoriUrunSil();
    }
    @Then("{string} yazisini kontrol eder ve Tamam butonuna tiklanir")
    public void yazisini_kontrol_eder_ve_tamam_butonuna_tiklanir(String string) {
      er.silindiDoğrula();
    }
    @Then("Hesabim sekmesinden Uye cikis islemi yapılır")
    public void hesabim_sekmesinden_uye_cikis_islemi_yapılır() {
      er.logOut();
    }
    @Then("sayfa kapatılır")
    public void sayfaKapatılır() {Driver.getDriver().close();}
}
