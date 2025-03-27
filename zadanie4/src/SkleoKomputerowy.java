import java.util.ArrayList;
public class SklepKomputerowy {
    private ArrayList<Produkt> produkty = new ArrayList<>();
    private ArrayList<Klient> klienci = new ArrayList<>();
    private ArrayList<Zamowienie> zamowienia = new ArrayList<>();
    private int licznikZamowien = 1;
    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }
    public void setProdukty(ArrayList<Produkt> produkty) {
        this.produkty = produkty;
    }
    public ArrayList<Klient> getKlienci() {
        return klienci;
    }
    public void setKlienci(ArrayList<Klient> klienci) {
        this.klienci = klienci;
    }
    public ArrayList<Zamowienie> getZamowienia() {
        return zamowienia;
    }
    public void setZamowienia(ArrayList<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }
    public int getLicznikZamowien() {
        return licznikZamowien;
    }
    public void setLicznikZamowien(int licznikZamowien) {
        this.licznikZamowien = licznikZamowien;
    }
    public void dodajProdukt(Produkt produkt) {
        produkty.add(produkt);
    }
    public void dodajKlienta(Klient klient) {
        klienci.add(klient);
    }
    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produktyZam, int[] ilosci) {
        Zamowienie zamowienie = new Zamowienie(
                licznikZamowien++, klient, produktyZam, ilosci,
                java.time.LocalDate.now().toString(), "Nowe"
        );
        zamowienia.add(zamowienie);
        return zamowienie;
    }
    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        Produkt[] zamowione = zamowienie.getProdukty();
        int[] ilosci = zamowienie.getIlosci();
        for (int i = 0; i < zamowione.length; i++) {
            Produkt produktZam = zamowione[i];
            int indeks = produkty.indexOf(produktZam);
            if (indeks != -1) {
                Produkt produktMag = produkty.get(indeks);
                produktMag.setIloscWMagazynie(
                        produktMag.getIloscWMagazynie() - ilosci[i]
                );
            }
        }
    }
    public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
        for (Zamowienie z : zamowienia) {
            if (z.getId() == idZamowienia) {
                z.setStatus(nowyStatus);
                break;
            }
        }
    }
    public void wyswietlProduktyWKategorii(String kategoria) {
        for (Produkt p : produkty) {
            if (p.getKategoria().equalsIgnoreCase(kategoria)) {
                p.wyswietlInformacje();
                System.out.println();
            }
        }
    }
    public void wyswietlZamowieniaKlienta(int idKlienta) {
        for (Zamowienie z : zamowienia) {
            if (z.getKlient().getId() == idKlienta) {
                z.wyswietlSzczegoly();
                System.out.println();
            }
        }
    }
}