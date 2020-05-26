package Repo;

import Domain.Comanda;
import Domain.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoMedicamente {
    String url;
    public RepoMedicamente(String url) {
        this.url = url;
    }

    public Connection connect() {

        String url = this.url;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void save(Medicament p) {
        String sql = "Insert into Medicamente(Nume) VALUES(?)";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNume());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "Delete from Medicamente where id=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Medicament p) {
        String sql = "UPDATE Medicamente SET Nume = ? " + " WHERE id = ?";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, p.getNume());
                pstmt.setInt(2, p.getId());
                pstmt.executeUpdate();
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Medicament> getAll(){
        String sql = "Select * from Medicamente";
        List<Medicament>l=new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    l.add(new Medicament(rs.getInt("Id"), rs.getString("Nume")));
                    ;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
    public Medicament findOne(int id) {
        String sql = "Select * from Medicamente where id=?";
        Medicament p = null;
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            try {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    p = new Medicament(rs.getInt("Id"), rs.getString("Nume"));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;

    }
}
