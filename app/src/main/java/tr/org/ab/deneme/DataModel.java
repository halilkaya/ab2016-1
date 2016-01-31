package tr.org.ab.deneme;

public class DataModel {

    public int id;
    public String ad;
    public String soyad;
    public String sehir;

    public DataModel() {
    }

    public DataModel(int id, String ad, String soyad, String sehir) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.sehir = sehir;
    }

}
