package Repo;

import Domain.Comanda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoComenziPlasate {
    String url;
    public RepoComenziPlasate(String url) {
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

    public void save(Comanda p) {
        String sql = "Insert into ComenziPlasate(Medicament,Cantitate) VALUES(?,?)";
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            //ps.setInt(1, p.getId());
            ps.setString(1, p.getMedicament());
            ps.setInt(2, p.getCantitate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "Delete from ComenziPlasate where id=?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Comanda p) {
        String sql = "UPDATE ComenziPlasate SET Cantitate = ? " + " ,Medicament=?"
                + " WHERE id = ?";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setInt(1, p.getCantitate());
                pstmt.setString(2, p.getMedicament());
                pstmt.setInt(3, p.getId());
                pstmt.executeUpdate();
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Comanda> getAll(){
        String sql = "Select * from ComenziPlasate";
        List<Comanda>l=new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    l.add(new Comanda(rs.getInt("Id"), rs.getString("Medicament"), rs.getInt("Cantitate")));
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
    public Comanda findOne(int id) {
        String sql = "Select * from ComenziPlasate where id=?";
        Comanda p = null;
        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            try {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    p = new Comanda(rs.getInt("Id"), rs.getString("Cantitate"), rs.getInt("Medicament"));
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