package com.moha.race;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fitxers {
    /*
     *  @param rutaFitxer
     *  @param text
     *  @parram afegeix
     *  @param IOException
     */
    public void escriuAlFitxerText(String rutaFitxer,
                                   String text,
                                   boolean afegeix)
            throws IOException {
        try (
                FileWriter out = new FileWriter(rutaFitxer, afegeix);) {
            out.write(text + "\n");
        }
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

    @Deprecated

    public String[] retornaFitxerText(String rutaFitxer) {

        String linia = "";
        String v[] = new String[contaLinies(rutaFitxer)];
        int cont = 0;

        try (FileReader in = new FileReader(rutaFitxer);) {
            Scanner input = new Scanner(in);

            while (input.hasNextLine()) {
                linia = linia + input.nextLine();
                v[cont] = linia;
                cont++;
            }
        } catch (IOException e) {
            return v;
        }
        return v;
    }


    public List<String> retornaFitxerTextLlista(String rutaFitxer){

        List<String> llista = new ArrayList<String>();
        //llista.add("Joan");
        try(FileReader in = new FileReader(rutaFitxer);){
            Scanner input = new Scanner(in);

            while (input.hasNextLine()){
                llista.add(input.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return llista;
    }
    public List<Object> retornaFitxerObjecteEnLlista(String arxiu) throws IOException,
            InterruptedException, ClassNotFoundException {
        List<Object> LObjs = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arxiu));
            do {
                Object obj = in.readObject();
                LObjs.add(obj);
            } while (in != null);
            in.close();                     // tanquem el fitxer d'objectes
            in = null;                      // i l'alliberem de memòria
        } catch (IOException e) {
            //e.printStackTrace();
            //System.err.println("\nFinal Fitxer");
        }
        return LObjs;
    }

}

