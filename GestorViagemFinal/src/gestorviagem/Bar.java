/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;

import java.io.Serializable;

public class Bar extends PontoInteresse implements Serializable{
    private float classificacao;
    private float consumo_minimo;

    public Bar(String nome, Horario horario_funcionamento, float consumo_minimo, float classificacao) { 
        super(nome, horario_funcionamento);
        this.classificacao = classificacao;
        this.consumo_minimo = consumo_minimo;
    }
    public String getTipo() {
        return "Bar";
    }

    public float getNota() {
        return classificacao;
    }
    
    public float getConsumoMinimo() {
        return consumo_minimo;
    }
    
    @Override
    public String toString() {
        System.out.println(super.toString());
        return "Local: " +nomePI+ "\nHorario de Funcionamento: "+horario_funcionamento+"\nClassificacao: "+classificacao+"\nConsumo minimo: "+consumo_minimo;
    } 

    @Override
    public float calculaCusto() {
        return consumo_minimo;
    }
}
