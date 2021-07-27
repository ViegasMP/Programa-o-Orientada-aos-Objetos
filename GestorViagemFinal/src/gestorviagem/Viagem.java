/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;

import java.util.ArrayList;

/**
 *
 * @author Diogo Costa
 * @author Maria Paula Viegas
 * 
 */
public class Viagem {
    //lista de 3 locais
    ArrayList <Local> locais = new ArrayList<>();
    double custo;
    //Local locais[] = new Local[3];
    
    public Viagem(ArrayList<Local> locais){
        this.locais = locais;
        this.custo = custo;
    }
    
    public void setCusto(double custo){
        this.custo = custo;
    }
    
}
