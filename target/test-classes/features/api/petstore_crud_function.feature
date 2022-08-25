@api
Feature: PetStore CRUD fonksiyonları

  @api_post
  Scenario: Post Request
    Given kullanici Post request icin gerekli pathprams ayarlarini yapar
    When  kullanici expectedData'ları belirler
    Then  kullanici POST request gönderir gelen responsu alir ve ekrana yazdirir
    Then   kullanici gelen veriyi(responsu) dogrular

@api_get
  Scenario: Get Request
    Given kullanici Get request icin gerekli pathprams ayarlarini yapar
    Then  kullanici Get request gönderir gelen responsu alir ve ekrana yazdirir
    Then   kullanici gelen responsu dogrular


@api_put
 Scenario: Put Request
  Given kullanici Put request icin gerekli pathprams ayarlarini yapar
  When  kullanici Put icin expectedData'ları belirler
  Then  kullanici Put request gönderir gelen responsu alir ve ekrana yazdirir
  Then   kullanici Put icin gelen responsu dogrular

@api_delete
  Scenario: Delete Request
  Given kullanici Delete request icin gerekli pathprams ayarlarini yapar
  Then  kullanici Delete request gönderir gelen responsu alir ve ekrana yazdirir
  Then  kullanici Delete icin gelen responsu dogrular