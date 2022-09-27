Feature: US1002 kullanici DBUtil ile database baglanip istedigi field bilgilerini okur

  Scenario: Kullanici tablo ve field ismi ile sorgu yapabilmeli

    Given kullanici DBUtil ile HMC veri tabanina baglanir
    And kullanici DBUtil ile "tHOTELROOM" tablosundaki "Price" verilerini alir
    And kullanici DBUtil ile "Price" sutunundaki verileri okur
    And kullanici DBUtil ile tum "Price" degerlerini sira numarasi ile yazdirir
    When kullanici DBUtil ile 4. "Price" in 425 oldugunu test eder