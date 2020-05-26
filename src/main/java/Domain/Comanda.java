package Domain;

public class Comanda {
    private int Id;
    private String Medicament;
    private int Cantitate;

    public Comanda(int id, String medicament, int cantitate) {
        Id = id;
        Medicament = medicament;
        Cantitate = cantitate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMedicament() {
        return Medicament;
    }

    public void setMedicament(String medicament) {
        Medicament = medicament;
    }

    public int getCantitate() {
        return Cantitate;
    }

    public void setCantitate(int cantitate) {
        Cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "Id=" + Id +
                ", Medicament='" + Medicament + '\'' +
                ", Cantitate=" + Cantitate +
                '}';
    }
}
