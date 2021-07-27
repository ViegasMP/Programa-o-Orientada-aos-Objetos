/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;

import java.io.Serializable;

public class Museu extends PontoInteresse implements Serializable{
    private String tematica;
    private float custo_entrada;

    public Museu(String nome, Horario horario_funcionamento, float custo_entrada, String tematica) { 
        super(nome, horario_funcionamento);
        this.tematica = tematica;
        this.custo_entrada = custo_entrada;
    }

    public String getTematica() {
        return tematica;
    }
    public String getTipo() {
        return "Museu";
    }
    
    
    public float getCusto() {
        return custo_entrada;
    }
    
    @Override
    public String toString() {
        System.out.println(super.toString());
        return "Local: " +nomePI+ "\nHorario de Funcionamento: "+horario_funcionamento+"\nCusto de entrada: "+calculaCusto()+"\nTematica: "+tematica;
    } 

    @Override
    public float calculaCusto() {
        return custo_entrada;
    }
}
