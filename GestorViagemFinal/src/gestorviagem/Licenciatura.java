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
public class Licenciatura extends Utilizador implements Serializable{
    private PontoInteresse pontoHot;
    public Licenciatura(String nome, int id, double despesa_max, PontoInteresse pontoHot){
        super(nome, id, despesa_max);
        this.pontoHot = pontoHot;
    }
    public PontoInteresse getHot() {
        return pontoHot;
    }
    public String getCiclo() {
          return "Licenciatura";
    }
    public int getID() {
        return id;
    }
    public String getPreferencia(){
        if (pontoHot != null){
            return pontoHot.getNome();
        } else {
            return null;
        }
    }
    @Override
    public String toString() {
        System.out.println(super.toString());
        return "\tHotspot: "+pontoHot;
    }
}
