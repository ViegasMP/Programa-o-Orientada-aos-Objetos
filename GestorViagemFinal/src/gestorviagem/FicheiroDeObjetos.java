/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.io.*;
/**
 *
 * @author ViegasMP
 */
 public class FicheiroDeObjetos{
        private ObjectInputStream iS;
        private ObjectOutputStream oS;


        public boolean abreLeitura(String nomeDoFicheiro) {
            try {
                iS = new ObjectInputStream(new FileInputStream (nomeDoFicheiro));
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        public void abreEscrita(String nomeDoFicheiro) throws IOException {
            oS = new ObjectOutputStream(new FileOutputStream (nomeDoFicheiro));
        }

        public Object leObjeto() throws IOException, ClassNotFoundException {
            return iS.readObject();
        }

        public void escreveObjeto(Object o) throws IOException {
            oS.writeObject(o);
        }

        public void fechaLeitura() throws IOException {
            iS.close();
        }
        public void fechaEscrita() throws IOException {
            oS.close();
        }
    }
