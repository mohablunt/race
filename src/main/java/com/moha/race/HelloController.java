package com.moha.race;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
//**fxml
public class HelloController {
    @FXML
    private TextArea mostraTransportistes;
    @FXML
    private TextField NomTran;
    @FXML
    private TextField CognomTran;
    @FXML
    private TextField AdressTran;
    @FXML
    private TextField TelTran;
    @FXML
    private TextField IDtransportista;
    @FXML
    private Button Bguarda;
    @FXML
    private TextArea mostraTransportistaAleatori;
    @FXML
    private TextField idTransportPerRepartiment;
    @FXML
    private TextField AdressRep;
    @FXML
    private TextField RefTrans;
    @FXML
    private TextField NPacRep;
    @FXML
    private TextField PesRep;
    @FXML
    private TextField DataRep;
    @FXML
    private Button assignarrepartiment;
//fxml

    String numerogenerat="";
    String numerogeneratrepart="";
    String rutaFitxer = "data\\transportistes.csv";
    String rutaFitxerRepartiments = "data\\repartiments.csv";

    String linia=""; // per recollir la línia
    String[] dades; // per particionar la linia
    String text = ""; // per acumular el text
    String[] mta1 = new String[contaLinies(rutaFitxer)];
    String[] mta2 = new String[contaLinies(rutaFitxer)];
    String[] mta3 = new String[contaLinies(rutaFitxer)];
    String[] mta4 = new String[contaLinies(rutaFitxer)];
    int j = 0;
    //String[] referencia;
    //int sumalinies = 0;

    public void initialize(){
        Bid();
    }
    public void Bguarda() throws InterruptedException {
            String nom = NomTran.getText();
            String cognom = CognomTran.getText();
            String adress = AdressTran.getText();
            String tel = TelTran.getText();
            text = numerogenerat + ";" + nom + ";" + cognom + ";" + adress + ";" + tel;
            Fitxers f = new Fitxers();

            try {
                f.escriuAlFitxerText(rutaFitxer, text, true);

            } catch (IOException e) {
                mostraTransportistes.setText("Error");
                e.printStackTrace();
            }

            //Esperar 5 segundos
            Thread.sleep(1000);
            IDtransportista.setText("");
            NomTran.setText("");
            CognomTran.setText("");
            AdressTran.setText("");
            TelTran.setText("");
            Bid();
        }
    public void Bid () {
        //IDtransportista.setText("");
        Random random = new Random();
        int xifres = random.nextInt(4);
        String l1="";
        String[] lletres = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int num;
            for (int x = 0; x < 3;x++) {
                num= random.nextInt(lletres.length);
                l1 = l1+lletres[num];
                numerogenerat = xifres + (l1);
            }
        //System.out.println(numerogenerat);
        IDtransportista.setText(numerogenerat);
    }
    private int contaLinies(String rutaFitxer) {
        int cont = 0;

        try (FileReader in = new FileReader(rutaFitxer);) {
            Scanner input = new Scanner(in);

            while (input.hasNextLine()) {
                input.nextLine();
                cont++;
            }
        } catch (IOException e) {
            return cont;
        }
        return cont;
    }
    public void llegirtransportistes () {
        mostraTransportistes.setText("");
        String[] v = new String[contaLinies(rutaFitxer)];
        int cont = 0;
        try (FileReader in = new FileReader(rutaFitxer);) {
            Scanner input = new Scanner(in);
            while (input.hasNextLine()) {
    //            sumalinies++;
    //            v[cont]=dades[cont];
                linia = linia + input.nextLine()+"\n";
                v[cont] = linia;
                cont++;
            }
        } catch (IOException ignored) {
        }
        mostraTransportistes.setText(linia);
    }
    public void repartiment () throws IOException, InterruptedException, ClassNotFoundException {
        randomRepartiment();
        //escullTransportista();
        setMostraTransportistaAleatori();
    }
    Random random = new Random();
    public void randomRepartiment () {

        int x1 = random.nextInt(9);
        int x2 = random.nextInt(9);
        int x3 = random.nextInt(9);
        String l1="";
        String[] lletres = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int num;
        for (int x = 0; x < 6;x++) {
            num = random.nextInt(lletres.length);
            l1 = l1+lletres[num];
            numerogeneratrepart = x1 + (l1) + x2 + x3;
        }
        //System.out.println(numerogeneratrepart);
        RefTrans.setText(numerogeneratrepart);
    }
    public void escullTransportista () throws InterruptedException, ClassNotFoundException {
        Fitxers f = new Fitxers();
        List fichero=f.retornaFitxerTextLlista(rutaFitxer);
        int i = fichero.size();
        System.out.println();
        //String[] v = new String[contaLinies(rutaFitxer)];
        //int cont = 0;
        //int j = 0;
        //String[] bool = new String[0];
        String[] ww = new String[contaLinies(rutaFitxer)];
        //String [] jj = fichero.get();
        try (FileReader in = new FileReader(rutaFitxer);) {
            Scanner input = new Scanner(in);
            //while (linia !=null && i<=8) {
            //Random rr = new Random();
            //int j=rr.nextInt();
            String idd = "";
            List<Object> dada=f.retornaFitxerObjecteEnLlista(rutaFitxer);
            while (input.hasNextLine()) {
                //            sumalinies++;
                //v[cont]=dades[cont];
                //bool[j] = linia;
                //String[] id = v[rr.nextInt(j)].split(";");
                linia = input.nextLine();           // Agafa una línia
                dades = (String[]) dada.get(1);
                idd = dades[0];
                mta1[i]=dades[1];
                mta2[i]=dades[2];
                mta3[i]=dades[3];
                mta4[i]=dades[4];
                ww[i] = idd;
                //linia = input.nextLine();           // Agafa una línia
                //dades = linia.split(";");     // Parteix per columnes per ";"
                //int idd = Integer.parseInt(dades[0]);                  // Recollim el cognom
                //linia = linia + input.nextLine()+"\n";
                //v[cont] = linia;
                //System.out.println(v[cont]);
                //System.out.println(idd);
                //cont++;
                //i++;
                //j++;
            }
        } catch (IOException ignored) {
        }


        Random rr = new Random();
        j=rr.nextInt(fichero.size());
        //System.out.println(ww[j]);
        //String[] id = v[rr.nextInt(j)].split(";");
        //String[] id = v[7].split(";");
        //System.out.println(v[7]);
        //System.out.println(id[0]);
        //System.out.println(id);
        //int numrandom = random.nextInt(5);
        //System.out.println();
        //idTransportPerRepartiment.setText();
        idTransportPerRepartiment.setText(ww[j]);
    }
    public void setMostraTransportistaAleatori() {
        Fitxers f = new Fitxers();
        Random rr = new Random();

        List <String> fichero=f.retornaFitxerTextLlista(rutaFitxer);
        j=rr.nextInt(fichero.size());
        String element = fichero.get(j);
        String[] ele = element.split(";");
        String[] ww = new String[contaLinies(rutaFitxer)];
        int i = 0;

            mta1[i] = ele[1];
            mta2[i] = ele[2];
            mta3[i] = ele[3];
            mta4[i] = ele[4];

        mostraTransportistaAleatori.setText(ele[1]+"\n\n"+ele[2]+"\n\n"+ele[3]+"\n\n"+ele[4]);


        idTransportPerRepartiment.setText(ele[0]);

    }
    public void Bassignarrepartiment() {
        String packet="Numero del paquet: "+NPacRep.getText();
        String pes="Pes del paquet: "+PesRep.getText();
        String data="Dia d'enviament: "+DataRep.getText();
        String referencia="Numero de referencia: "+RefTrans.getText();
        String adreçarepart= "Adreca: "+AdressRep.getText();
        String repartidor="Repartidor: "+idTransportPerRepartiment.getText();
        String inforepart= repartidor+";"+adreçarepart+";"+referencia+";"+packet+";"+pes+";"+data;
        Fitxers f = new Fitxers();
        try {
            f.escriuAlFitxerText(rutaFitxerRepartiments, inforepart, true);
        } catch (IOException e) {
        mostraTransportistaAleatori.setText("Error");
        e.printStackTrace();
    }
        NPacRep.setText("");
        PesRep.setText("");
        DataRep.setText("");
        RefTrans.setText("");
        AdressRep.setText("");
        idTransportPerRepartiment.setText("");

    }
}

