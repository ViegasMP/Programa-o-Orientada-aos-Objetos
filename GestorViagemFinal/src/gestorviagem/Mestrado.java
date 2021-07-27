/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dcost
 */
public class Mestrado extends Utilizador implements Serializable{
    private Local evitar;
    public Mestrado(String nome, int id, double despesa_max, Local evitar){
        super(nome, id, despesa_max);
        this.evitar = evitar;
    }
    public Local getEvitar() {
        return evitar;
    }
    public String getCiclo() {
          return "Mestrado";
    }
    public int getID() {
        return id;
    }
    public String getPreferencia(){
        if (evitar != null){
            return evitar.getNome();
        } else {
            return null;
        }
    }
    @Override
    public String toString() {
        System.out.println(super.toString());
        return "\tLocal a evitar: "+evitar;
    }
}
