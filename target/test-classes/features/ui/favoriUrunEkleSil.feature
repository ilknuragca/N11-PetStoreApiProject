Feature: N11 Favori Urun

  @favoriuruneklesil
  Scenario: Favori Urun Ekle-Sil Senaryo

    Given kullanıcı "n11Url" sayfasına gider
    Then ana sayfanın açıldığı kontrol edilir
    When kullanıcı giris yap sekmesine tıklar
    When kullanıcı siteye email ve sifre ile Login olur
    Then login işlemi kontrol edilir
    When kullanıcı search kutusuna "Iphone" kelimesi yazarak arama yapar
    Then "Iphone" kelimesi aratıldığı kontrol edilir
    When kullanıcı arama sonuçları sayfasından "2". sayfayı açar
    Then "2" sayfanın açıldığı kontrol edilir
    When kullanıcı sayfada ki "3". ürünü favorilerine ekler
    When kullanıcı Hesabım bölümünden Favorilerim sayfasına gider
    Then Favorilerim sayfasının açıldığı kontrol edilir
    When kullanıcı eklediği ürünü favorilerden siler
    Then "Ürününüz listeden silindi." yazisini kontrol eder ve Tamam butonuna tiklanir
    When Hesabim sekmesinden Uye cikis islemi yapılır
    Then sayfa kapatılır






