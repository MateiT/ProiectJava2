package Service;

import Domain.Comanda;
import Domain.Medicament;
import Repo.RepoComenzi;
import Repo.RepoComenziPlasate;
import Repo.RepoMedicamente;
import UI.ControlerFarmacie;
import UI.ControlerSectie;

import javax.swing.text.html.ListView;
import java.util.List;

public class Service {

    private RepoComenzi repoC;
    private RepoMedicamente repoM;
    RepoComenziPlasate repoCP;
    public Service(RepoComenzi repoC, RepoMedicamente repoM, RepoComenziPlasate repoCP) {
        this.repoC = repoC;
        this.repoM = repoM;
        this.repoCP=repoCP;
    }

    public List<Medicament> getAllMedicamente(){
        return repoM.getAll();
    }
    public List<Comanda> getAllComenzi(int nrSectie){
        return repoC.getAll(nrSectie);
    }
    public List<Comanda> getAllComenziPlasate(){
        return repoCP.getAll();
    }
    public void addComanda(int id,String med,int cant,int nrSectie){
        Comanda c=new Comanda(id,med,cant);
        repoC.save(c,nrSectie);
    }
    public void ModificaComanda(int id,String med,int cant){
        Comanda c=new Comanda(id,med,cant);
        repoC.update(c);
    }
    public void stergeComanda(int id){
        repoC.delete(id);
    }
    public void stergeSelectedCP(int id){
        repoCP.delete(id);
    }
    public void sendToFarmacie(int nrSectie){
        List<Comanda>l=repoC.getAll(nrSectie);
        for (Comanda c:l
        ) {
            repoCP.save(c);
        }
    }
    public void deleteAllComenzi(int nrSectie){
        List<Comanda>l=repoC.getAll(nrSectie);
        for (Comanda c:l
             ) {
            repoC.delete(c.getId());
        }
    }

}
