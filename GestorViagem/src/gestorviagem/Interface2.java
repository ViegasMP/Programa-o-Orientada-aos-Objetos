/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorviagem;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.border.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Diogo Costa
 * @author Maria Paula Viegas
 */
public class Interface2 extends JFrame {
    
     /**
     * Atributos do programa
     */
    private ArrayList <Utilizador> utilizadores;
    private ArrayList <Local> cidades;
    private int[][]matriz_distancias;
    private double montante_maximo;
    private int nr_locais_selecionados = 0;
    private String cidadeHot = null;
    private int contador=0;
    private File f = new File("infoLocaisObj.txt");
    private File f2 = new File("infoUsersObj.txt");
    private int check_pontos = 0;
    private int refresh=0;
    /**
     * Atributos da Interface
     */

    private JPanel mainInterface, zonaLocais, zonaPontosInteresse, zonaDados;
    private JScrollPane scrollLocais, scrollPontosInteresse, scrollDados, scrollTexto;
    private JLabel bemVindoLabel,montanteMaxLabel, escolhaLocais, escolhaPI, nome, id, montante, preferencia, ciclo;
    private JCheckBox locaisCB[];
    private ArrayList <JCheckBox[]> pontosInteresseCB;
    private JComboBox users;
    private JButton registarButton, sair, verPopulares, verSugestoes, verMinhaViagem;
    private JButton registarViagem, registarLocais;
    private JTextArea listaViagens, pontosPopulares, zonaTexto;
   
    public class ButtonListenerRegisto implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == registarButton){
                Utilizador u = null;
                for(int i=0; i < utilizadores.size(); i++){
                    if(((String)users.getSelectedItem()).equals(utilizadores.get(i).getNome())){
                        u = utilizadores.get(i);
                    }
                }
                
                if(u.minha_viagem == null){
                    JOptionPane.showMessageDialog(new JFrame(), "É preciso primeiro que o utilizador guarde uma viagem", "ERRO", JOptionPane.ERROR_MESSAGE);
                } else {
                    Interface registro = new Interface(utilizadores,cidades, matriz_distancias);
                    registro.setTitle("REGISTO");
                    registro.setSize(640,320);
                    registro.setVisible(true);
                
                    dispose();
                }
            }
        }
    }
        
    public double calculaCustos(Viagem viagemSelecionada){
        double totalViagem=0;
        int dist=0;
        int[] pos = new int [3];
        int c=0;
        //System.out.println("entrei na funcao");
        for (int i=0; i<3; i++){
            //System.out.println("uma cidade");
            //System.out.println(viagemSelecionada.locais.get(i).getNome());
            for(int j=0; j < viagemSelecionada.locais.get(i).pontos.size(); j++){
                //System.out.println("adicionei custo");
                //System.out.println(viagemSelecionada.locais.get(i).pontos.get(j).getNome());
                totalViagem += viagemSelecionada.locais.get(i).pontos.get(j).calculaCusto();
            }
            for (int k=0; k<cidades.size(); k++){
                if (viagemSelecionada.locais.get(i).getNome().equals(cidades.get(k).getNome())){
                    pos[c]=k;
                    c++;
                }
            }
        }
        
        totalViagem += (double)(matriz_distancias[0][pos[0]+1])/50;
        for (int i = 0; i<2;i++){
            if(pos[i] < pos[i+1]){
                totalViagem += (double)(matriz_distancias[pos[i]+1][pos[i+1]+1])/70;
            } else {
                totalViagem += (double)(matriz_distancias[pos[i+1]+1][pos[i]+1])/70; 
            }
        }
        totalViagem += (double)(matriz_distancias[0][pos[2]+1])/50;
        
        
        String aluno = (String)users.getSelectedItem();
                
        for(int i=0; i < utilizadores.size(); i++){
            if(aluno.equals(utilizadores.get(i).getNome())){
                if (totalViagem > utilizadores.get(i).getDespesa()){
                    return -1;
                } else {
                    return utilizadores.get(i).getDespesa() - totalViagem;
                }
            }
        }
        return -1;
    }   
    
    public class CheckBoxLocalListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            registarViagem.setEnabled(false);
            Utilizador u = null;
            //zonaPontosInteresse.removeAll();
            for(int i=0; i < utilizadores.size(); i++){
                if(((String)users.getSelectedItem()).equals(utilizadores.get(i).getNome())){
                    u = utilizadores.get(i);
                }
            }
            for (int i = 0; i < locaisCB.length; i++){
                if(e.getSource() == locaisCB[i]) {
                    if (locaisCB[i].isSelected()) {
                        nr_locais_selecionados += 1;
                    }
                    if (!locaisCB[i].isSelected()){
                        nr_locais_selecionados += -1;
                    }
                }
            }
            if (nr_locais_selecionados == 3) {
                registarLocais.setEnabled(true);
                for (int i = 0; i < locaisCB.length; i++) {
                    if (!locaisCB[i].isSelected()) {
                        locaisCB[i].setEnabled(false);
                    }
                    if(u.getCiclo().equals("Licenciatura")){
                        if(cidades.get(i).getNome() == cidadeHot){
                            locaisCB[i].setEnabled(false);
                        }
                    }
                }
            }
            if (nr_locais_selecionados < 3) {
                registarLocais.setEnabled(false);
                for (int i = 0; i < locaisCB.length; i++) {
                    locaisCB[i].setEnabled(true);
                    if(u.getCiclo().equals("Mestrado")){
                        if(cidades.get(i).getNome() == u.getPreferencia()){
                            locaisCB[i].setEnabled(false);
                        }
                    }
                    if(u.getCiclo().equals("Licenciatura")){
                        if(cidades.get(i).getNome() == cidadeHot){
                            locaisCB[i].setEnabled(false);
                        }
                    }
                }
            }
        }
    }
    
    public JCheckBox[] geraCheckboxes(JCheckBox local){
        int ct = 0;
        JCheckBox[] cb_array =  new JCheckBox[3];
        String pontoInteresse_str[] = new String[3];
        Utilizador u = null;
        for(int i=0; i < utilizadores.size(); i++){
            if(((String)users.getSelectedItem()).equals(utilizadores.get(i).getNome())){
                u = utilizadores.get(i);
            }
        }
        for (int i = 0; i < cidades.size(); i++) {
            if(cidades.get(i).getNome().equals(local.getText())) {
                for(int j = 0; j < 3; j++){
                    pontoInteresse_str[j] = cidades.get(i).pontos.get(j).getNome();
                    //System.out.println(pontoInteresse_str[j]);
                    cb_array[ct] = new JCheckBox();
                    cb_array[ct].setText(pontoInteresse_str[j]);
                    if(u.getCiclo().equals("Licenciatura")&& pontoInteresse_str[j].equals(u.getPreferencia())){
                        cb_array[ct].setSelected(true);
                        cb_array[ct].setEnabled(false);
                    } else
                        cb_array[ct].setSelected(false);
                    cb_array[ct].setVisible(true);
                    ct+=1;
                }

            }
        }
        return cb_array;
    }
    
    public class ComboBoxUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() ==  users){
                
                zonaDados.remove(nome);
                zonaDados.remove(id);
                zonaDados.remove(ciclo);
                zonaDados.remove(montante);
                zonaDados.remove(preferencia);
                
                String aluno = (String)users.getSelectedItem();
                
                for(int i=0; i < utilizadores.size(); i++){
                    if(aluno.equals(utilizadores.get(i).getNome())){
                        nome = new JLabel ("Nome: "+aluno);
                        id = new JLabel("id: "+utilizadores.get(i).getID());
                        ciclo = new JLabel("Ciclo: "+utilizadores.get(i).getCiclo());
                        montante = new JLabel("Montante maximo:"+utilizadores.get(i).getDespesa());
                        if(utilizadores.get(i).getCiclo().equals("Licenciatura")){
                            preferencia = new JLabel("Ponto Hot: "+utilizadores.get(i).getPreferencia());
                        } else {
                            preferencia = new JLabel("Evitar: "+utilizadores.get(i).getPreferencia());
                            
                        }
                        
                        nome.setBounds(60,60,150,25);
                        id.setBounds(60,90,150,25);
                        ciclo.setBounds(60,120,150,25);
                        montante.setBounds(60,150,150,25);
                        preferencia.setBounds(60, 180,205,25);
                        zonaDados.add(nome);
                        zonaDados.add(id);
                        zonaDados.add(ciclo);
                        zonaDados.add(montante);
                        zonaDados.add(preferencia);
                        zonaDados.updateUI();
                        
                        zonaTexto.removeAll();
                        
                        if(utilizadores.get(i).minha_viagem != null){
                            zonaPontosInteresse.removeAll();
                            zonaLocais.removeAll();
                            registarLocais.setEnabled(false);
                            registarViagem.setEnabled(false);
                        }
                    }
                }
            }
        }
    }

    public class GuardaViagem implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int aux_visitas=0;
            if(e.getSource() ==  registarViagem){
                ArrayList <Local> lista_locais_viagem = new ArrayList<>();
                for(int i=0;i<locaisCB.length;i++) {
                    if(locaisCB[i].isSelected()){
                        aux_visitas = cidades.get(i).getVisitas();
                        cidades.get(i).setVisitas(aux_visitas+1);
                        Local local1_user = new Local(locaisCB[i].getText());
                        lista_locais_viagem.add(local1_user);
                        //System.out.println(lista_locais_viagem.get(lista_locais_viagem.size()-1).toString());
                        for(int j=0; j<pontosInteresseCB.size(); j++){
                            for(int p=0; p<pontosInteresseCB.get(j).length;p++){
                                //System.out.println("PB: "+pontosInteresseCB.get(j)[p].getText());
                                for(int d=0; d < cidades.get(i).pontos.size(); d++){
                                    aux_visitas=0;
                                    //System.out.println("PI: "+cidades.get(i).pontos.get(d).getNome());
                                    if (pontosInteresseCB.get(j)[p].isSelected() && pontosInteresseCB.get(j)[p].getText().equals(cidades.get(i).pontos.get(d).getNome())){
                                        aux_visitas = cidades.get(i).pontos.get(d).getVisitas();
                                        cidades.get(i).pontos.get(d).setVisitas(aux_visitas+1);
                                        
                                        lista_locais_viagem.get(lista_locais_viagem.size()-1).pontos.add(cidades.get(i).pontos.get(d));
                                    }
                                }  
                            }
                        }
                    }
                }
                
            String simu_viagem = new String();
            String aluno = (String)users.getSelectedItem();
            Viagem viagem_selecionada = new Viagem(lista_locais_viagem);
            if(calculaCustos(viagem_selecionada)>=0){
                viagem_selecionada.setCusto(calculaCustos(viagem_selecionada));
                for(int m=0; m < utilizadores.size(); m++) {
                    if(aluno.equals(utilizadores.get(m).getNome())){
                        utilizadores.get(m).minha_viagem = viagem_selecionada;
                        exibir("\n\n--------------------------------------------------\n\tViagem Selecionada\n");
                        for(int x=0;x<viagem_selecionada.locais.size();x++){
                            simu_viagem = viagem_selecionada.locais.get(x).toString();
                            exibir(simu_viagem+"\n\tPontos de Interesse:\n");
                            for(int y=0;y<viagem_selecionada.locais.get(x).pontos.size();y++) {
                                simu_viagem =viagem_selecionada.locais.get(x).pontos.get(y).toString() + "\n";
                                exibir(simu_viagem);
                            }
                        }
                    }
                }
                    exibir("\n------------------------------------------------------\n");
                    JOptionPane.showMessageDialog(new JFrame(), "Viagem feita com sucesso", ":D",
                    JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Lamento, mas o montante maximo escolhido nao é o suficiente", ":(",
                    JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    public class ButtonMinhaViagem implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() ==  verMinhaViagem){
                //zonaTexto.removeAll();
                String simu_viagem = new String();
                String aluno = (String)users.getSelectedItem();
                for(int m=0; m < utilizadores.size(); m++) {
                    if(aluno.equals(utilizadores.get(m).getNome())){
                        if(utilizadores.get(m).minha_viagem != null){
                            exibir("\n\n--------------------------------------------------------------\n\tViagem Selecionada\n");
                            for(int x=0;x<utilizadores.get(m).minha_viagem.locais.size();x++){
                                simu_viagem = utilizadores.get(m).minha_viagem.locais.get(x).toString();
                                exibir(simu_viagem+"\n\tPontos de Interesse:\n");
                                for(int y=0;y<utilizadores.get(m).minha_viagem.locais.get(x).pontos.size();y++) {
                                    simu_viagem =utilizadores.get(m).minha_viagem.locais.get(x).pontos.get(y).toString() + "\n";
                                    exibir(simu_viagem);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "O utilizador ainda nao escolheu uma viagem", "Atencao!",
                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }
     }
        
    public class ButtonGuardaLocaisListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            registarViagem.setEnabled(true);
            Utilizador u = null;
            for(int i=0; i < utilizadores.size(); i++){
                if(((String)users.getSelectedItem()).equals(utilizadores.get(i).getNome())){
                    u = utilizadores.get(i);
                }
            }
            String[] pontoInteresse_str;
            if(e.getSource() ==  registarLocais){
                zonaPontosInteresse.removeAll();
                for (int i = 0; i < locaisCB.length; i++){
                    if (locaisCB[i].isSelected()) {
                        for(int j=0; j<cidades.size(); j++){
                            if(locaisCB[i].getText().equals(cidades.get(j).getNome())) {
                                JCheckBox[] array_checkboxes =  new JCheckBox[3];
                                array_checkboxes = geraCheckboxes(locaisCB[i]);
                                pontosInteresseCB.add(array_checkboxes);
                                //print_array(array_checkboxes);
                                for(int k=0; k<array_checkboxes.length;k++)
                                zonaPontosInteresse.add(array_checkboxes[k]);
                            }
                        }
                    }
                }
            }
            zonaPontosInteresse.updateUI();        
        }
    }
    
    public class ButtonSairListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() ==  sair){
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(cidades);
                    oos.close();
                } catch (FileNotFoundException ex) {
                        System.out.println("Erro a criar ficheiro.");
                } catch (IOException ex) {
                        System.out.println("Erro a escrever para o ficheiro.");
                }
                
                try {
                    FileOutputStream fos = new FileOutputStream(f2);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(users);
                    oos.close();
                } catch (FileNotFoundException ex) {
                        System.out.println("Erro a criar ficheiro.");
                } catch (IOException ex) {
                        System.out.println("Erro a escrever para o ficheiro.");
                }
            }
            dispose();
        }
    }
    
    public class ButtonSugestoesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() ==  verSugestoes){
                Utilizador u = null;
                for(int i=0; i < utilizadores.size(); i++){
                    if(((String)users.getSelectedItem()).equals(utilizadores.get(i).getNome())){
                        u = utilizadores.get(i);
                    }
                }
                
                ArrayList<Local> locaisDisponiveis = new ArrayList<>();
                int []pos = {-1,-1,-1};
                int flag = 0;
                int numRandom;
                int check_museu = 0;
                
                if(u.getCiclo().equals("Licenciatura")){
                    for (int i=0; i<cidades.size();i++){
                        if(cidades.get(i).getNome().equals(cidadeHot)){
                           locaisDisponiveis.add(cidades.get(i));
                           pos[0]=i;
                           for(int j = 0; j<cidades.get(i).pontos.size();j++){
                               if(cidades.get(i).pontos.get(j).getTipo().equals("Museu")){
                                   check_museu=1;
                               }
                           }
                        }
                    }
                }
                while(flag == 0){
                    while(locaisDisponiveis.size() < 3) {
                        numRandom = (int)(Math.random()*20);
                        if(numRandom !=pos[0] && numRandom !=pos[1] && numRandom !=pos[2]){
                            if(u.getCiclo().equals("Mestrado") && !u.getPreferencia().equals(cidades.get(numRandom))){
                                if(locaisDisponiveis.size() == 2 && check_museu == 0){
                                    for(int j = 0; j<cidades.get(numRandom).pontos.size();j++){
                                        if(cidades.get(numRandom).pontos.get(j).getTipo().equals("Museu")){
                                            check_museu=1;
                                            locaisDisponiveis.add(cidades.get(numRandom));
                                            pos[locaisDisponiveis.size()-1]=numRandom;
                                        }
                                    }
                                } else if(locaisDisponiveis.size() < 2 && check_museu == 0){
                                    locaisDisponiveis.add(cidades.get(numRandom));
                                    for(int j = 0; j<cidades.get(numRandom).pontos.size();j++){
                                        if(cidades.get(numRandom).pontos.get(j).getTipo().equals("Museu")){
                                            check_museu=1;
                                            pos[locaisDisponiveis.size()-1]=numRandom;
                                        }
                                    }
                                } else if (check_museu == 1){
                                    locaisDisponiveis.add(cidades.get(numRandom));
                                    pos[locaisDisponiveis.size()-1]=numRandom;
                                }
                            } else {//Licenciatura
                                if(check_museu == 1){
                                    locaisDisponiveis.add(cidades.get(numRandom));
                                    pos[locaisDisponiveis.size()-1]=numRandom;
                                } else {
                                    if(locaisDisponiveis.size() < 2){
                                        locaisDisponiveis.add(cidades.get(numRandom));
                                        pos[locaisDisponiveis.size()-1]=numRandom;
                                        for(int j = 0; j<cidades.get(numRandom).pontos.size();j++){
                                            if(cidades.get(numRandom).pontos.get(j).getTipo().equals("Museu")){
                                                check_museu=1;
                                            }
                                        }
                                    } else if(locaisDisponiveis.size() == 2){
                                        for(int j = 0; j<cidades.get(numRandom).pontos.size();j++){
                                            if(cidades.get(numRandom).pontos.get(j).getTipo().equals("Museu")){
                                                check_museu=1;
                                                locaisDisponiveis.add(cidades.get(numRandom));
                                                pos[locaisDisponiveis.size()-1]=numRandom;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    Viagem novaViagem = new Viagem(locaisDisponiveis);
                    if(calculaCustos(novaViagem)>=0){
                        exibir("\n\n\tSugestao\n");
                        exibir(novaViagem.locais.get(0).toString());
                        exibir("\n"+novaViagem.locais.get(1).toString());
                        exibir("\n"+novaViagem.locais.get(2).toString());
                        flag = 1;
                    } else {
                        if(u.getCiclo().equals("Mestrado")){
                            locaisDisponiveis.removeAll(cidades);
                            pos[0]=-1;
                            pos[1]=-1;
                            pos[2]=-1;
                        } else {
                            if(!u.getPreferencia().equals(null)){
                                locaisDisponiveis.remove(1);
                                locaisDisponiveis.remove(2);
                                pos[1]=-1;
                                pos[2]=-1;
                            } else{
                                locaisDisponiveis.removeAll(cidades);
                                pos[0]=-1;
                                pos[1]=-1;
                                pos[2]=-1;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public class ButtonPopularesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() ==  verPopulares){
                exibir("\n\n\tLocais e Pontos de Interesse Populares\n");
                for(int i=0; i<cidades.size(); i++){
                    if(cidades.get(i).getVisitas() >= 1/utilizadores.size()){
                        exibir(cidades.get(i).toString());
                        for(int j=0; j<cidades.get(i).pontos.size();j++){
                            if(cidades.get(i).pontos.get(j).getVisitas() >= 1/utilizadores.size()){
                                exibir("\n"+cidades.get(i).pontos.get(j).toString()+"\n");
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void exibir(String texto){
        zonaTexto.setEditable(false);
        zonaTexto.append(texto);
    }
            
    public Interface2(ArrayList<Utilizador> utilizadores, ArrayList<Local> cidades, int[][] matriz_distancias) {
        
        this.utilizadores=utilizadores;
        this.cidades = cidades;
        this.matriz_distancias = matriz_distancias;
        pontosInteresseCB = new ArrayList<>();
                
        mainInterface = new JPanel();
        zonaLocais = new JPanel();
        zonaPontosInteresse = new JPanel();
        zonaTexto = new JTextArea();
        zonaDados = new JPanel();
        
        zonaLocais.setLayout(new GridLayout(20, 1));
        zonaLocais.setBorder(LineBorder.createBlackLineBorder());
        zonaPontosInteresse.setLayout(new GridLayout(60, 1));
        zonaPontosInteresse.setBorder(LineBorder.createBlackLineBorder());
        zonaDados.setLayout(new GridLayout(120, 1));
        zonaDados.setBorder(LineBorder.createBlackLineBorder());
        mainInterface.setLayout(null);
        zonaTexto.setBorder(LineBorder.createBlackLineBorder());
        
        bemVindoLabel = new JLabel ("Bem-vindo!");
        escolhaLocais = new JLabel ("Escolha 3 locais");
        escolhaPI = new JLabel("Escolha os pontos de interesse");
        
        bemVindoLabel.setFont(new Font("Arial", Font.BOLD, 26));
        escolhaLocais.setFont(new Font("Arial", Font.BOLD, 12));
        escolhaPI.setFont(new Font("Arial", Font.BOLD, 12));
        
        registarButton = new JButton("REGISTAR");        
        registarViagem = new JButton("Guardar Viagem");
        sair = new JButton("Sair");
        registarLocais = new JButton("Ver Pontos");
        verSugestoes = new JButton("Sugestões");
        verMinhaViagem = new JButton("Minha Viagem");
        verPopulares = new JButton("Populares");
        
        scrollLocais = new JScrollPane(zonaLocais, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollLocais.setBounds(50,200,200,400);
        scrollPontosInteresse = new JScrollPane(zonaPontosInteresse, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPontosInteresse.setBounds(300,200,200,400);
        scrollDados = new JScrollPane(zonaDados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollDados.setBounds(50,50,300,100);
        scrollTexto = new JScrollPane(zonaTexto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTexto.setBounds(550,150,400,450);
        
        
        //Combo Box de users ja registrados
        String [] registados = new String [utilizadores.size()];
        for(int i = 0; i < utilizadores.size(); i++) {
            registados[i] = utilizadores.get(i).getNome();
        }
        users = new JComboBox(registados);
        users.setSelectedIndex(registados.length-1);
        
        //String userAtual = (String)users.getSelectedItem();
        String hot = null;
        for(int i=0; i < utilizadores.size(); i++){
            if(((String)users.getSelectedItem()).equals(utilizadores.get(i).getNome())){
                if(utilizadores.get(i).getCiclo().equals("Licenciatura")){
                    hot = utilizadores.get(i).getPreferencia();
                }
            }
        }
        String cidade_hot=null;
        for (int i = 0; i<cidades.size(); i++){
            for (int j=0; j<cidades.get(i).pontos.size(); j++){
                if (cidades.get(i).pontos.get(j).nomePI.equals(hot)){
                    cidade_hot = cidades.get(i).getNome();
                    cidadeHot = cidades.get(i).getNome();
                }
            }
        }
        
        bemVindoLabel.setBounds(435,10,150,25);
        escolhaLocais.setBounds(50,170,150,25);
        escolhaPI.setBounds(300,170,190,25);
        registarButton.setBounds(850,10,100,25);
        registarViagem.setBounds(300,600,150,25);
        registarViagem.setEnabled(false);
        users.setBounds(50,10,100,25);
        sair.setBounds(800, 600, 150, 25);
        registarLocais.setBounds(50, 600, 150, 25);
        registarLocais.setEnabled(false);
        verMinhaViagem.setBounds(550,100,150,25);
        verSugestoes.setBounds(725,100, 100, 25);
        verPopulares.setBounds(850,100,100, 25);
        
        //CheckList de locais disponiveis
        Utilizador u = null;
        for(int i=0; i < utilizadores.size(); i++){
            if(((String)users.getSelectedItem()).equals(utilizadores.get(i).getNome())){
                u = utilizadores.get(i);
            }
        }

        int y_sum = 200;
        locaisCB = new JCheckBox[cidades.size()];
        for(int i=0;i<cidades.size();i++){
            String preferecia_user = new String();
            locaisCB[i] = new JCheckBox();
            locaisCB[i].setBounds(30,y_sum,100,50);
            locaisCB[i].setText(cidades.get(i).getNome());
            locaisCB[i].setSelected(false);
            locaisCB[i].setVisible(true);
            if(u.getCiclo().equals("Mestrado")){
                if(cidades.get(i).getNome() == u.getPreferencia()){
                    locaisCB[i].setEnabled(false);
                }
            }
            if(u.getCiclo().equals("Licenciatura")){
                if(cidades.get(i).getNome() == cidade_hot){
                    locaisCB[i].setSelected(true);
                    locaisCB[i].setEnabled(false);
                    nr_locais_selecionados++;
                    
                }
            }
            zonaLocais.add(locaisCB[i]);
            locaisCB[i].addActionListener(new CheckBoxLocalListener());
            y_sum+=40;
        }
               
       String aluno = (String)users.getSelectedItem();
                
        for(int i=0; i < utilizadores.size(); i++){
            if(aluno.equals(utilizadores.get(i).getNome())){
                nome = new JLabel ("Nome: "+aluno);
                id = new JLabel("id: "+utilizadores.get(i).getID());
                ciclo = new JLabel("Ciclo: "+utilizadores.get(i).getCiclo());
                montante = new JLabel("Montante maximo: "+utilizadores.get(i).getDespesa());
                if(utilizadores.get(i).getCiclo().equals("Licenciatura")){
                    preferencia = new JLabel("Ponto Hot: "+utilizadores.get(i).getPreferencia());
                } else {
                    preferencia = new JLabel("Evitar: "+utilizadores.get(i).getPreferencia());
                            
                }

                nome.setBounds(60,60,25,25);
                id.setBounds(60,70,25,25);
                ciclo.setBounds(60,80,25,25);
                montante.setBounds(60,90,25,25);
                zonaDados.add(nome);
                zonaDados.add(id);
                zonaDados.add(ciclo);
                zonaDados.add(montante);
                zonaDados.add(preferencia);
            }
        }
        
        mainInterface.add(scrollPontosInteresse);
        mainInterface.add(scrollLocais);
        mainInterface.add(scrollDados);
        mainInterface.add(bemVindoLabel);
        mainInterface.add(escolhaLocais);
        mainInterface.add(escolhaPI);
        mainInterface.add(registarButton);
        mainInterface.add(registarViagem);
        mainInterface.add(users);
        mainInterface.add(scrollTexto);
        mainInterface.add(sair);
        mainInterface.add(registarLocais);
        mainInterface.add(verPopulares);
        mainInterface.add(verSugestoes);
        mainInterface.add(verMinhaViagem);
        
        registarButton.addActionListener(new ButtonListenerRegisto());
        users.addActionListener(new ComboBoxUserListener());
        registarViagem.addActionListener(new GuardaViagem());
        registarLocais.addActionListener(new ButtonGuardaLocaisListener());
        sair.addActionListener(new ButtonSairListener());
        verMinhaViagem.addActionListener(new ButtonMinhaViagem());
        verPopulares.addActionListener(new ButtonPopularesListener());
        verSugestoes.addActionListener(new ButtonSugestoesListener());
        
        
        this.add(mainInterface);
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        ArrayList <Utilizador> users = new ArrayList<>();
        ArrayList <Local> locais = new ArrayList<>();
        int [][] matriz_distancias = new int[20][20]; 
        Interface2 frameMain = new Interface2(users,locais, matriz_distancias);
        frameMain.setTitle("MENU");
        frameMain.setSize(1000,700);
        frameMain.setVisible(true);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
