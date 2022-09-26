package dbStepdefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {

    String url="jdbc:sqlserver://184.168.194.58:1433:databaseName=hotelmycamp ; user=techproed;password=P2s@rt65";
    String username="techproed";
    String password="P2s@rt65";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        // database e baglanti kuruyor ve statement a deger atiyor
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        // query calsitiriyoruz
        // SELECT Price FROM tHOTELROOM - bu sekliyle dinamik olmaz. Dinamik hale getirmek icin once
        // String readQuery = "SELECT Price FROM tHOTELROOM"; seklinde atama yapip sonra
        // String readQuery = "SELECT " + Price + " FROM " + tHOTELROOM";   haline ve en sonunda da tam dinamik hale getirmek icin
        String readQuery = "SELECT " + field + " FROM " + table;  // sekline getirmemiz lazim
        // sorgumuzu dinamik olarak bir String seklinde yazmis olduk
        // simdi bu sorguyu calistirmak icin
        // statement.executeQuery(readQuery); olusturup sonucu okuyabilmek icin resultSet objesinin icine atayacagiz.
        resultSet = statement.executeQuery(readQuery);

    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {
        // resultSet bizim simdiye kadar kullandigimiz Set yapisinda degildir
        // resultSet iterator ile calisir. resultSet deki bilgileri okumak istiyorsaniz
        // once iterator i istediginiz yere gondermelisiniz.
        resultSet.first(); // bu komut iterator i ilk elemente goturur ve giderse true, gidemezse false dondurur.
        // iterator istenen konuma gidince arti o elementi yazdirabiliriz.
        System.out.println(resultSet.getString(field));
        // 2. oda fiyatini gormek istersek iterator i oraya gondermemiz lazim
        resultSet.next();   // bu komut sadece iterator i bir sonraki konuma getirdi, yazmak icin;
        System.out.println(resultSet.getString(field));   // yukaridaki ile ayni komutu yazmamiza ragmen
        // iterator in yeri degistigi icin farkli bir elementi yazdirir.
        System.out.println(resultSet.next());   // bize bir element degeri degil, sirada baska bir element olup olmamasina
                                                // gore true vea false dondurur
        System.out.println(resultSet.getString(field));   // bize yeni bir element yazdirir cunku bir onceki
        // print icindeki resultSet.next() hangi sart altinda yazilirsa yazilsin bizi bir sonraki elemente goturur.
        // Tum prive sutununu yazdirmak istersem

        // resultSet ile calisirken iterator in konumunu kontrol etmeliyiz. Birinci elemana donmeden yazdirmaya devam edersek
        // kaldigi yerden yazdirmaya devam eder. Bunu yapmanin 2 yolu var. 1. si
        // resultSet.first();   // veya
        resultSet.absolute(0);
        while (resultSet.next()) {   // bir sonraki eleman oldugu surece true dondurup {} icindeki islemi yapacak
            System.out.println(resultSet.getString(field));
        }   // bu islem tamamen bitince iterator en sonda olacak

        // price sutununda kac tane data oldugunu bulalim
        resultSet.last();
        System.out.println(resultSet.getRow());

        // su anda tum price bilgileri resultSet icinde. Eger bunlar uzerinden islem yapmak istiyorsak, mesela
        // bu bilgileri bir List<Double> icine atip java ortamina alarak tum price verilerini bu listeye ekleyebiliirz
        // boylece bir java objesi olan List sayesinde price degerleri uzerinde istedigimiz testleri yapabiliriz
        resultSet.absolute(0);
        List<Double> priceList = new ArrayList<>();
        while (resultSet.next()) {
            priceList.add(resultSet.getDouble(field));
        }


    }
}
