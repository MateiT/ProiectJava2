package UI;

import Domain.Comanda;
import Domain.Medicament;
import Repo.RepoComenzi;
import Repo.RepoComenziPlasate;
import Repo.RepoMedicamente;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.*;

public class ControlerFarmacie implements Initializable {
    List<ControlerSectie> lcs=new ArrayList<>();
    private int Id=0;
    private String Medicament="";
    private int Cantitate=0;
    private Service service;

    @FXML
    TableView<Comanda> tabelCP;

    @FXML
    TableColumn<Comanda,Integer> IdCP;
    @FXML
    TableColumn<Comanda,String> MedicamentCP;
    @FXML
    TableColumn<Comanda,Integer> CantitateCP;

    private void initCP(){
        IdCP.setCellValueFactory(new PropertyValueFactory<>("Id"));
        MedicamentCP.setCellValueFactory(new PropertyValueFactory<>("Medicament"));
        CantitateCP.setCellValueFactory(new PropertyValueFactory<>("Cantitate"));

        for (Comanda m:service.getAllComenziPlasate()
        ) {
            tabelCP.getItems().add(m);
        }

        tabelCP.getSelectionModel().selectedItemProperty().addListener((v,oldItem,newItem)-> {
            Id=newItem.getId();
            Medicament=newItem.getMedicament();
            Cantitate=newItem.getCantitate();
        });
    }


    private ObservableList<Comanda> getComenzi(){
        ObservableList<Comanda> listuta = FXCollections.observableArrayList();
        for(Comanda p:service.getAllComenziPlasate()){
            listuta.add(p);
        }
        return listuta;
    }
    public void update(){
        ObservableList<Comanda> o=tabelCP.getItems();
        if(!o.isEmpty())o.clear();
        o.setAll(getComenzi());
        tabelCP.setItems(o);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    public void pressOnoreazComanda(){
        if(Id!=0) {
            service.stergeSelectedCP(Id);
            update();
            for (ControlerSectie c:lcs
                 ) {
                c.onorare();
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Nu a fost selectata nici o comanda");

    }
    public void transferMessage(Service ser) {
        this.service=ser;
        initCP();
    }
    public void getControler(List<ControlerSectie> l){
        this.lcs=l;
    }

}


