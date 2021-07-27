/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author dcost
 */
public class Local implements Serializable{
    private String nome;
    protected ArrayList<PontoInteresse> pontos = new ArrayList<>();
    private int visitas;

    public Local(String nome) { 
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    public void setVisitas(int contador){
        this.visitas=contador;
    }
    
    public int getVisitas() {
        return visitas;
    }
    
    @Override
    public String toString() {
        return "Cidade: " +nome;
    }
}
