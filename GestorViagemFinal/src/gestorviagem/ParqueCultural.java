/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;

import java.io.Serializable;
import java.util.*;

public class ParqueCultural extends Parque implements Serializable{
    private ArrayList <String> apresentacoes = new ArrayList<>();
    private float custo_entrada;

    public ParqueCultural(String nome, Horario horario_funcionamento, float custo_entrada, ArrayList apresentacoes) { 
        super(nome, horario_funcionamento);
        this.apresentacoes = apresentacoes;
        this.custo_entrada = custo_entrada;
    }
    public String getTipo() {
        return "Cultural";
    }

    public ArrayList getApresentacoes() {
        return apresentacoes;
    }
    
    public float getCusto() {
        return custo_entrada;
    }
    
    @Override
    public String toString() {
        System.out.println(super.toString());
        return "Local: " +nomePI+ "\nHorario de Funcionamento: "+horario_funcionamento+"\nCusto de entrada: "+calculaCusto()+"\nApresentacoes: "+apresentacoes;
    } 

    @Override
    public float calculaCusto() {
        return custo_entrada;
    }
}
