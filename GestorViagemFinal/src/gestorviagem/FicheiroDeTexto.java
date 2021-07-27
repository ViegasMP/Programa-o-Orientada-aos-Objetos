/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;

import java.io.*;

/**
 *
 * @author dcost
 */
public class FicheiroDeTexto {
    private BufferedReader fR;
    private BufferedWriter fW;

    public void abreLeitura(String nomeDoFicheiro) throws IOException {
        fR = new BufferedReader(new FileReader(nomeDoFicheiro));

    }
    public void abreEscrita(String nomeDoFicheiro) throws IOException {
        fW = new BufferedWriter(new FileWriter(nomeDoFicheiro));
    }
    public String leLinha() throws IOException {
        return fR.readLine();
    }
    public void escreveLinha(String linha) throws IOException {
        fW.write(linha, 0, linha.length());
        fW.newLine();
    }
    public void escreveNumero(int num) throws IOException {
        String st = "";
        st = st.valueOf(num);
        escreveLinha(st);
    }
    public void fechaLeitura() throws IOException {
        fR.close();
    }
    public void fechaEscrita() throws IOException {
        fW.close();
    }
}