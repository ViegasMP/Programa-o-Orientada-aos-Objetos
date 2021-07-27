/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;

import java.io.Serializable;
import java.util.*;

public class Universidade extends PontoInteresse implements Serializable{
    private ArrayList <String> cursos = new ArrayList<>();

    public Universidade(String nome, Horario horario_funcionamento, ArrayList cursos) { 
        super(nome, horario_funcionamento);
        this.cursos = cursos;
    }
    
    public String getTipo() {
        return "Universidade";
    }
    
    @Override
    public float calculaCusto() {
          return 0;
    }

    public ArrayList getCursos() {
        return cursos;
    }
    
    @Override
    public String toString() {
        System.out.println(super.toString());
        return "Local: " +nomePI+ "\nHorario de Funcionamento: "+horario_funcionamento+"\nCusto de entrada: "+calculaCusto()+"\nCursos(relacionados a Engenharia Informatica): "+cursos;
    } 
}
