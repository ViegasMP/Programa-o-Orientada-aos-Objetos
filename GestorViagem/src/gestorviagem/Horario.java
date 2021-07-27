/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.io.Serializable;

public class Horario implements Serializable{
    private int abertura;
    private int encerramento;
    private int dia_folga;
    
    public Horario(int abertura, int encerramento, int dia_folga) {
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dia_folga =  dia_folga;
    }

    public void setAbertura(int abertura) {
        this.abertura = abertura;
    }

    public void setEncerramento(int encerramento) {
        this.encerramento = encerramento;
    }
    
    public void setFolga(int dia_folga) {
        this.dia_folga = dia_folga;
    }
    
    public String traduzDia(int dia_folga) {
        if(dia_folga == 1){
            return "domingo";
        }else if(dia_folga == 2){
            return "segunda-feira";
        }else if(dia_folga == 3){
            return "terca-feira";
        }else if(dia_folga == 4){
            return "quarta-feira";
        }else if(dia_folga == 5){
            return "quinta-feira";
        }else if(dia_folga == 6){
            return "sexta-feira";
        }else if(dia_folga == 7){
            return "sabado";
        } else {
            return "nunca";
        }
    }

    @Override
    public String toString() {
        return abertura + "hs - " +encerramento+"hs (encerra-se: "+traduzDia(dia_folga)+")";
    }   
}
