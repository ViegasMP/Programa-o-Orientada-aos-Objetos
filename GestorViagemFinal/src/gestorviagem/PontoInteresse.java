/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.io.*;

abstract class PontoInteresse implements Serializable{
    protected Horario horario_funcionamento;
    protected String nomePI;
    protected int visitas;
    
    public abstract float calculaCusto();
    
    public abstract String getTipo();
        
    public PontoInteresse(String nomePI, Horario horario) {
        this.horario_funcionamento = horario;
        this.nomePI = nomePI;
    }

    public String getNome() {
        return nomePI;
    }
    
    public Horario getHorario() {
        return horario_funcionamento;
    }
    
    public void setVisitas(int contador){
        this.visitas=contador;
    }
    
    public int getVisitas() {
        return visitas;
    }
    
    @Override
    public String toString() {
        return "Local: " +nomePI+ "\nHorario de Funcionamento: "+horario_funcionamento+"\nCusto de entrada: "+calculaCusto();
    }
}
