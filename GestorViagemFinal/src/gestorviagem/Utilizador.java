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
abstract class Utilizador implements Serializable {
    protected String nome;
    protected int id;
    protected double despesa_max;
    protected Viagem minha_viagem;
    
    public Utilizador(String nome, int id, double despesa_max) {
        this.nome = nome;
        this.id = id;
        this.despesa_max = despesa_max;
    }
    
    public abstract String getCiclo();
        
    public String getNome() {
        return nome;
    }
    
    public abstract String getPreferencia();
    
    public abstract int getID();
    
    public double getDespesa() {
        return despesa_max;
    }
    
    
    @Override
    public String toString() {
        return "Nome: "+nome+"\nPreferencias:\n\tverba: "+despesa_max;
    }
    
}
