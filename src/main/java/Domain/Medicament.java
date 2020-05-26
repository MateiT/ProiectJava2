package Domain;

public class Medicament {
    private int Id;
    private String Nume;

    public Medicament(int id, String nume) {
        Id = id;
        Nume = nume;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "Id=" + Id +
                ", Nume='" + Nume + '\'' +
                '}';
    }
}
