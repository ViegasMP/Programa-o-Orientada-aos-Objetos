/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.io.Serializable;
import java.util.*;

public class ParqueAquatico extends ParqueDiversoes implements Serializable{
    private ArrayList <String> equipamentos = new ArrayList<>();
    private float custo_entrada;
    private int piscinas;
    private boolean espetaculos;
    //private float custpADD

    public ParqueAquatico(String nome, Horario horario_funcionamento, float custo_entrada, ArrayList atracoes, int piscinas, ArrayList equipamentos, boolean espetaculos) { 
        super(nome, horario_funcionamento, custo_entrada, atracoes);
        this.piscinas = piscinas;
        this.equipamentos = equipamentos;
        this.espetaculos = espetaculos;
        this.custo_entrada = custo_entrada;
    }
    
    public String getTipo() {
        return "Aquatico";
    }

    public ArrayList getEquipamentos() {
        return equipamentos;
    }
    
    public float getCusto() {
       
        return custo_entrada;
    }
    public int getNumPiscinas() {
        return piscinas;
    }
    
    public boolean getEspetaculos() {
        return espetaculos;
    }
    public float calculaCusto(){
        return custo_entrada;
    }
    @Override
    public String toString() {
        System.out.println(super.toString());
        return "Local: " +nomePI+ "\nHorario de Funcionamento: "+horario_funcionamento+"\nCusto de entrada: "+calculaCusto()+"Equipamentos: "+equipamentos+"\nNumero de Piscinas: "+piscinas+"\nEspetaculos? "+espetaculos;
    } 
}
