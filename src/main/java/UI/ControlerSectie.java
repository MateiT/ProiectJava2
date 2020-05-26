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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerSectie implements Initializable {
    int nrSectie=0;
    int nr=0;
    ControlerFarmacie cf;
    private Service service;

    @FXML
    TextArea textRaspuns;
    @FXML
    TextField textMedicament;
    @FXML
    TextField textCantitate;
    @FXML
    TextField textId;

    @FXML
    TableView<Comanda> tabelC;
    @FXML
    TableView<Medicament> tabelM;
    @FXML
    TableColumn<Comanda,Integer> IdC;
    @FXML
    TableColumn<Comanda,String> MedicamentC;
    @FXML
    TableColumn<Comanda,Integer> CantitateC;
    @FXML
    TableColumn<Medicament,Integer> IdM;
    @FXML
    TableColumn<Medicament,String> NumeM;



    private void initMedicamente(){
        IdM.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NumeM.setCellValueFactory(new PropertyValueFactory<>("Nume"));

        for (Medicament m:service.getAllMedicamente()
        ) {
            tabelM.getItems().add(m);
        }

        tabelM.getSelectionModel().selectedItemProperty().addListener((v,oldItem,newItem)-> {
            textMedicament.setText(newItem.getNume()+"");
        });
    }
    private void initComenzi(){
        IdC.setCellValueFactory(new PropertyValueFactory<>("Id"));
        MedicamentC.setCellValueFactory(new PropertyValueFactory<>("Medicament"));
        CantitateC.setCellValueFactory(new PropertyValueFactory<>("Cantitate"));

        for (Comanda m:service.getAllComenzi(nrSectie)
        ) {
            tabelC.getItems().add(m);
        }

        tabelC.getSelectionModel().selectedItemProperty().addListener((v,oldItem,newItem)-> {
            textId.setText(newItem.getId()+"");
            textMedicament.setText(newItem.getMedicament());
            textCantitate.setText(newItem.getCantitate()+"");
        });
    }
    private ObservableList<Comanda> getComenzi(){
        ObservableList<Comanda> listuta = FXCollections.observableArrayList();
        for(Comanda p:service.getAllComenzi(nrSectie)){
            listuta.add(p);
        }
        return listuta;
    }
    public void update(){
        ObservableList<Comanda> o=tabelC.getItems();
        if(!o.isEmpty())o.clear();
        o.setAll(getComenzi());
        tabelC.setItems(o);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setServices();

    }


    @FXML
    public void pressAdauga(){
        try {
            int id = Integer.parseInt(textId.getText());
            String med = textMedicament.getText();
            int cant = Integer.parseInt(textCantitate.getText());
            service.addComanda(id,med,cant,nrSectie);
            update();
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Format gresit! Id si Cantitate trebuie sa fie numere!");
        }

    }
    @FXML
    public void pressModifica(){
        try {
            int id = Integer.parseInt(textId.getText());
            String med = textMedicament.getText();
            int cant = Integer.parseInt(textCantitate.getText());
            service.ModificaComanda(id,med,cant);
            update();
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Format gresit! Id si Cantitate trebuie sa fie numere!");
        }
    }
    @FXML
    public void pressSterge(){
        try {
            int id = Integer.parseInt(textId.getText());
            service.stergeComanda(id);
            update();
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Format gresit! Id-ul trebuie sa fie numar!");
        }
    }
    @FXML
    public void pressPlaseazaComenzi(){
        service.sendToFarmacie(nrSectie);
        service.deleteAllComenzi(nrSectie);
        update();
        cf.update();
    }

    public void transferMessage(Service ser,int i) {
        this.service=ser;
        this.nrSectie=i;
        initMedicamente();
        initComenzi();
    }
    public void getControler(ControlerFarmacie cf){
        this.cf=cf;
    }
    public void onorare(){
        nr++;
        textRaspuns.clear();
        textRaspuns.setText(nr+" comenzi onorate!");
    }


}