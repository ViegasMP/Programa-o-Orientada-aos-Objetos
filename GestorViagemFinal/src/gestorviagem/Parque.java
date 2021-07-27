/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.io.Serializable;

abstract class Parque extends PontoInteresse implements Serializable{

    public Parque(String nome, Horario horario_funcionamento) { 
        super(nome, horario_funcionamento);
    }

  
}