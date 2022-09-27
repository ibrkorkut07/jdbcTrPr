package dbStepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtils;

import java.sql.SQLException;

public class DBUtilStepDefinitions {
    @Given("kullanici DBUtil ile HMC veri tabanina baglanir")
    public void kullanici_db_util_ile_hmc_veri_tabanina_baglanir() {
        DBUtils.getConnection();
    }
    @Given("kullanici DBUtil ile {string} tablosundaki {string} verilerini alir")
    public void kullanici_db_util_ile_tablosundaki_verilerini_alir(String table, String field) {
        // SELECT field FROM table;
        String readQuery = "SELECT " + field + " FROM " + table;
        DBUtils.executeQuery(readQuery);
    }
    @Given("kullanici DBUtil ile {string} sutunundaki verileri okur")
    public void kullanici_db_util_ile_sutunundaki_verileri_okur(String field) throws SQLException {
        DBUtils.getResultset().first();
        // System.out.println(DBUtils.getResultset().getString(field));
        // System.out.println(DBUtils.getResultset().toString());

    }
    @Given("kullanici DBUtil ile tum {string} degerlerini sira numarasi ile yazdirir")
    public void kullanici_db_util_ile_tum_degerlerini_sira_numarasi_ile_yazdirir(String field) throws Exception {
        int satirSayisi = DBUtils.getRowCount();
        DBUtils.getResultset().first();   // DBUtils deki get RowCount metodu iterator i son satira getirdigi icin bunu yapiyoruz
        int siraNo = 1;

        for (int i = 1; i < satirSayisi; i++) {
            System.out.println(i + ".ci satirdaki " + field + ": " + DBUtils.getResultset().getString(field));
            DBUtils.getResultset().next();
            siraNo++;
        }
    }
    @When("kullanici DBUtil ile {int}. {string} in {int} oldugunu test eder")
    public void kullanici_db_util_ile_in_oldugunu_test_eder(Integer siraNo, String field, Integer expectedPrice) throws SQLException {
        DBUtils.getResultset().absolute(siraNo);
        double actualPrice = DBUtils.getResultset().getDouble(field);
        Assert.assertTrue(actualPrice==expectedPrice);
    }


    @And("tHOTEL tablosunda IDHotel degeri {int} olan kaydin Email bilgisini {string} yapar")
    public void thotelTablosundaIDHotelDegeriOlanKaydinEmailBilgisiniYapar(int IDHotel, String yeniEmail) {

        // UPDATE tHOTEL SET Email='hoscakal@gmail.com' WHERE IDHotel = '1016';
        String updateQuery = "UPDATE tHOTEL SET Email='" + yeniEmail + "' WHERE IDHotel='" + IDHotel + "';";
        DBUtils.executeQuery(updateQuery);

    }


}
