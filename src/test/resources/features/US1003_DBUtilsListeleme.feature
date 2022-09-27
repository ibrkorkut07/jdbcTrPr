Feature: US1003 tablo ismi ve field ile listeleme

  Scenario: TC03 tablo ismi ve field ile listeleme yapabilmeli

    Given kullanici DBUtil ile HMC veri tabanina baglanir
    And kullanici DBUtil ile "tHOTEL" tablosundaki "Email" verilerini alir
    And kullanici DBUtil ile tum "Email" degerlerini sira numarasi ile yazdirir


