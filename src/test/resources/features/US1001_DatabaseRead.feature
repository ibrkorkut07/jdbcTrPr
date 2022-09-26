Feature: US1001 kullanici database baglanarak bilgileri okuyabilir

  @db
  Scenario: Kullanici database baglanip istedigi bilgileri okuyabilmeli
    Given kullanici HMC veri tabanina baglanir
    # database'e baglanacagiz
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    # Query calistiracagiz
    And kullanici "Price" sutunundaki verileri okur
    #3.adimda database sorgusu sonunda bizi dondurulen bilgileri nasil kullanabilecegimizi gorecegiz
