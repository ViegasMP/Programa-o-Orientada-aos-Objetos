package gestorviagem;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
import javax.swing.JFrame;

/**
 *
 * @author Maria Paula viegas
 * @author Diogo Costa
 */

public class GestorViagem {

    public static void main(String[] args)throws IOException, ClassNotFoundException{ 
        //System.out.println("Ola, teste");
        ArrayList <Utilizador> users = new ArrayList<>();
        ArrayList <Local> locais = new ArrayList<>();
        int dim=0;
       
        /*Viagem teste = new Viagem();
        teste.locais.add(new Local("Londres"));
        System.out.println(teste.locais);
        teste.locais[0] = new Local("Coimbra");
        System.out.println(teste.locais[0].toString());
        teste.locais[0].pontos.add(new Bar("O Moelas", new Horario(18, 4, 2), 5, 5));
        System.out.println(teste.locais[0].pontos);
        */
        
       //Ler ficheiros
        //try{
           //users = lerUsersObj();
        //}
        //catch(NullPointerException | IOException e){
           System.out.println("Não há utilizadores inscritos");
        //}
        dim = lerLocais(locais);
        locais.removeAll(locais);
        try{
           locais = lerLocaisObj();
        }
        catch(NullPointerException | IOException e) {
           dim = lerLocais(locais);
        }
        //System.out.println(dim);
        int[][] matriz_distancias = lerDistancias(dim+1);
        
      
       Interface frameRegisto = new Interface(users,locais, matriz_distancias);
       frameRegisto.setTitle("REGISTO");
       //frameRegisto.setResizable(false);      
       frameRegisto.setSize(640,320);
       frameRegisto.setVisible(true);
       frameRegisto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

              
    }
    
    public static int lerLocais(ArrayList<Local> locais) throws IOException {
        FicheiroDeTexto l = new FicheiroDeTexto();
        l.abreLeitura("infoLocaisMac.txt");
        int dim=0;

        String linhafich;
        linhafich = l.leLinha();
        while(linhafich != null) {
            //System.out.println("Nome Cidade= "+linhafich);
            Local cidade = new Local(linhafich);
            locais.add(cidade);
            //System.out.println(locais.get(locais.size()-1).toString());
            linhafich = l.leLinha();
            while(linhafich.charAt(0) != '-'){
                String nome[] = linhafich.split(": ");
                //System.out.println("Ponto de Interesse = "+nome[0]);
                //System.out.println("Nome do PI = "+nome[1]);
                linhafich = l.leLinha();
                //System.out.println("linha = "+linhafich);
                String info[] = linhafich.split(",");
                int i = 0;
                while(i < info.length){
                    //System.out.println("informacoes = "+info[i]);
                    i++;
                }
                Horario h = new Horario(Integer.valueOf(info[0]), Integer.valueOf(info[1]), Integer.valueOf(info[2]));

                if(nome[0].equals("Museu")){
                    /*
                    Museu: nome
                    abertura,encerramento,dia de folga,custo de entrada,tematica
                    */
                    Museu m = new Museu(nome[1], h, Float.parseFloat(info[3]), info[4]);
                    locais.get(locais.size()-1).pontos.add(m);
                    //System.out.println(locais.get(locais.size()-1).pontos.get(locais.get(locais.size()-1).pontos.size()-1));

                }else if(nome[0].equals("Universidade")){
                    /*
                    Universidade: nome
                    abertura,encerramento,dia de folga,cursos
                    */
                    ArrayList <String> cursos = new ArrayList<>();
                    for(int j=3; j<info.length; j++){
                        cursos.add(info[j]);
                    }
                    Universidade u = new Universidade(nome[1], h, cursos);
                    locais.get(locais.size()-1).pontos.add(u);
                    //System.out.println(locais.get(locais.size()-1).pontos.get(locais.get(locais.size()-1).pontos.size()-1));


                }else if(nome[0].equals("Bar")){
                    /*
                    Bar: nome
                    abertura,encerramento,dia de folga,consumo,classificacao
                    */
                    Bar b = new Bar(nome[1], h, Float.parseFloat(info[3]), Float.parseFloat(info[4]));
                    locais.get(locais.size()-1).pontos.add(b);
                    //System.out.println(locais.get(locais.size()-1).pontos.get(locais.get(locais.size()-1).pontos.size()-1));

                } else if (nome[0].equals("Parque Diversoes")){
                    /*
                    Parque Diversoes: nome
                    abertura,encerramento,dia de folga,custo entrada,atracoes
                    */
                    ArrayList <String> atracoes = new ArrayList<>();
                    for(int j=4; j<info.length; j++){
                        atracoes.add(info[j]);
                    }
                    ParqueDiversoes pd = new ParqueDiversoes(nome[1], h, Float.parseFloat(info[3]),atracoes);
                    locais.get(locais.size()-1).pontos.add(pd);
                    //System.out.println(locais.get(locais.size()-1).pontos.get(locais.get(locais.size()-1).pontos.size()-1));

                } else if (nome[0].equals("Parque Aquatico")){
                    /*
                    Parque Aquatico: nome
                    abertura,encerramento,dia de folga,custo entrada,atracoes,qtd piscinas,equipamentos,boolean espetaculos
                    */
                    ArrayList <String> atracoes = new ArrayList<>();
                    ArrayList <String> equip = new ArrayList<>();
                    String a[] = info[4].split("/");
                    String e[] = info[6].split("/");
                    for(int j=0; j<a.length; j++){
                        atracoes.add(a[j]);
                    }
                    for(int j=0; j<e.length; j++){
                        equip.add(e[j]);
                    }
                    var boolValue = (info[info.length - 1] =="true");

                    ParqueAquatico pa = new ParqueAquatico(nome[1], h, Float.parseFloat(info[3]),atracoes, Integer.valueOf(info[5]), equip, boolValue);
                    locais.get(locais.size()-1).pontos.add(pa);
                    //System.out.println(locais.get(locais.size()-1).pontos.get(locais.get(locais.size()-1).pontos.size()-1));

                } else if (nome[0].equals("Parque Cultural")){
                    /*
                    Parque Cultural: nome
                    abertura,encerramento,dia de folga,custo entrada,apresetacoes
                    */
                    ArrayList <String> apresentacoes = new ArrayList<>();
                    for(int j=4; j<info.length; j++){
                        apresentacoes.add(info[j]);
                    }

                    ParqueCultural pc = new ParqueCultural(nome[1], h, Float.parseFloat(info[3]),apresentacoes);
                    locais.get(locais.size()-1).pontos.add(pc);
                    //System.out.println(locais.get(locais.size()-1).pontos.get(locais.get(locais.size()-1).pontos.size()-1));
                }
                linhafich = l.leLinha();
            }
            //System.out.println("\n\n");
            dim++;
            linhafich = l.leLinha();
        }
        l.fechaLeitura();
        return dim;
    }

    public static int[][] lerDistancias(int dim) throws IOException{
    int[][]matriz_distancias = new int[dim][dim];
    FicheiroDeTexto l = new FicheiroDeTexto();
    l.abreLeitura("distancias.txt");
    String linhafich;
    for (int i=0; i < dim; i++) {
        linhafich = l.leLinha();
        String linha[] = linhafich.split("\t");
        
        for (int j=0; j < dim; j++){
            matriz_distancias[i][j] = Integer.valueOf(linha[j]);
            //System.out.print(" "+matriz_distancias[i][j]+"\t");
        }
        //System.out.print("\n");
    }
    l.fechaLeitura();
    return matriz_distancias;
}

    public static ArrayList<Local> lerLocaisObj() throws IOException, ClassNotFoundException{
        ArrayList<Local> locais;
        FicheiroDeObjetos backupLocais = new FicheiroDeObjetos();
        backupLocais.abreLeitura("infoLocaisObj.txt");
        locais = (ArrayList<Local>) backupLocais.leObjeto();
        backupLocais.fechaLeitura();
        return locais;
    }
    
    public static ArrayList<Utilizador> lerUsersObj() throws IOException, ClassNotFoundException{
        ArrayList<Utilizador> users;
        FicheiroDeObjetos backupUsers = new FicheiroDeObjetos();
        backupUsers.abreLeitura("infoUsersObj.txt");
        users = (ArrayList<Utilizador>) backupUsers.leObjeto();
        backupUsers.fechaLeitura();
        return users;
    }
    
}