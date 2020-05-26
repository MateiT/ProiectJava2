package UI;

import Repo.RepoComenzi;
import Repo.RepoComenziPlasate;
import Repo.RepoMedicamente;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ControlerLogin  implements Initializable {
    private List<ControlerSectie> lcs=new ArrayList<>();
    private ControlerFarmacie cf;
    private Service service;
    public static Stage primaryStage;
    @FXML
    Button buttonLogin;
    @FXML
    TextField textFieldUsername;
    @FXML
    TextField textFieldParola;

    private void openSectii(){
        try {
            for(int i=1;i<=1;i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/designTerminalSectie.fxml"));

                Parent root = fxmlLoader.load();
                ControlerSectie controlarSectie = fxmlLoader.getController();
                lcs.add(controlarSectie);
                controlarSectie.transferMessage(service,i);


                Scene scene = new Scene(root, 600, 427);
                Stage stage = new Stage();
                stage.setTitle("Sectie" + i);
                stage.setScene(scene);

                stage.show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openFarmacie(){
        try {
            for(int i=1;i<=1;i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/designTerminalFarmacie.fxml"));

                Parent root = fxmlLoader.load();
                ControlerFarmacie controlarFarm = fxmlLoader.getController();
                cf=controlarFarm;
                controlarFarm.transferMessage(service);

                Scene scene = new Scene(root, 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Farmacie");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setServices(){
        RepoMedicamente repoM=new RepoMedicamente("jdbc:sqlite:D:\\Pt facultate\\An2\\Sem 2\\ISS\\Java\\db.sqlite");
        RepoComenzi repoC=new RepoComenzi("jdbc:sqlite:D:\\Pt facultate\\An2\\Sem 2\\ISS\\Java\\db.sqlite");
        RepoComenziPlasate repoCP=new RepoComenziPlasate("jdbc:sqlite:D:\\Pt facultate\\An2\\Sem 2\\ISS\\Java\\db.sqlite");
        service=new Service(repoC,repoM,repoCP);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setServices();
    }

    private void verificaUtilizator(){

        String name = textFieldUsername.getText();
        String parola = textFieldParola.getText();
        if (name.equals("f") && parola.equals("f"))
            System.out.println("Conectare reusita!");
        else{
            throw new RuntimeException();
        }
    }
    @FXML
    public void pressButtonLogin(){
        try {
            verificaUtilizator();
            primaryStage = (Stage) buttonLogin.getScene().getWindow();
            primaryStage.hide();
            openSectii();
            openFarmacie();
            for (ControlerSectie c : lcs
            ) {
                c.getControler(cf);
            }
            cf.getControler(lcs);
        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(null, "Utilizator sau parola gresita!");
        }

    }


}