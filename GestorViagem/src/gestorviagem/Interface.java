package gestorviagem;
//import static gestorviagem.GestorViagem.guardaLocais;
//import static gestorviagem.GestorViagem.lerLocaisObj;
//import static gestorviagem.GestorViagem.lerUsersObj;
//import static gestorviagem.GestorViagem.guardaUsers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class Interface extends JFrame implements Serializable{

    /**
     * Atributos da interface
     */
    
    private JLabel bemVindoLabel, nomeLabel, cursoLabel, montanteLabel, eurosLabel, preferenciaLabel;
    private JTextField nomeTF, montanteTF;
    private JButton continuarBT;
    private JPanel registaUser,mainInterface;
    private JComboBox cursoCB;
    private JComboBox registarLocal;
    private JComboBox registrarPI;
    private String[] cursos = {" ","Licenciatura","Mestrado"};
    /**
     * Atributos do programa
     */
    protected ArrayList <Utilizador> users;
    protected ArrayList <Local> locais;
    protected int[][]matriz_distancias;
    int id=0;
    //protected ArrayList <Local> l;
    protected double montante_maximo;
    //protected int cont;
    //protected Utilizador user;

    Interface(JComboBox users, JCheckBox[] locais, int[][] matriz_distancias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //exibir pontos de interesse de um local
    public class LocalCBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() ==  registarLocal){
                
                String ciclo = (String)cursoCB.getSelectedItem();
               
                if(ciclo.equals("Licenciatura")){ //apenas mostramos os pontos caso seja um aluno de licenciatura
                    registrarPI.removeAllItems();
                    registrarPI.addItem("Ponto Hot");
                    String lugar = (String)registarLocal.getSelectedItem();
                    for (int i=0; i<locais.size();i++){
                        if(locais.get(i).getNome().equals(lugar)){
                            //System.out.println(locais.get(i).getNome());
                            //System.out.println(locais.get(i).pontos.size());
                            for (int j = 0; j < locais.get(i).pontos.size(); j++){
                                //System.out.println(locais.get(i).pontos.get(j).getNome());
                                registrarPI.addItem(locais.get(i).pontos.get(j).getNome());
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    public void show_error(String error_text ) {
        JOptionPane.showMessageDialog(new JFrame(), error_text, "ERRO",
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean checkTF(int typeChecker, JTextField tf ){
        //verifica os conteudos de tf. caso typeChecker = 1, assume-se que o conteudo de tf é um inteiro, se typeChecker for outro valor qualquer, assume-se que tf contem texto
        if(tf.getText().equals(""))
            return false;

        boolean valido = true;
        char[] a = tf.getText().toCharArray();
        if(typeChecker == 1){
            for (char c: a){
                valido =  ((c >= '0') && (c <= '9'));

                if (!valido){
                    break;
                }
            }
            return valido;
        }
        else{
            for (char c: a){
                valido =  ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || (c == ' ') || (c == '-');
                if (!valido){
                    break;
                }
            }
            return valido;
        }
    }
    
    //quando carregamos "SEGUINTE"
    public class ButtonSeguinteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == continuarBT ){

                id++;
                String nome = nomeTF.getText();
                String montante = montanteTF.getText();
                String curso = (String)cursoCB.getSelectedItem();
                String local = (String)registarLocal.getSelectedItem();
                String ponto = (String)registrarPI.getSelectedItem();
                Local evitar = null;
                PontoInteresse hot = null;
                if(checkTF(1,montanteTF) && checkTF(0,nomeTF)) {
                    if (curso.equals("Mestrado")) {

                        if (!local.equals("Local a evitar")) { //caso um local tenha sido selecionado
                            for (int i = 0; i < locais.size(); i++) {
                                if (locais.get(i).getNome().equals(local)) {
                                    evitar = locais.get(i);
                                    break;
                                }
                            }
                        }
                        users.add(new Mestrado(nome, id, Double.parseDouble(montante), evitar));

                        Interface2 mainInterface = new Interface2(users,locais,matriz_distancias);
                        mainInterface.setTitle("MENU");
                        mainInterface.setSize(1000, 700);
                        mainInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainInterface.setVisible(true);
                        dispose();

                    } else if (curso.equals("Licenciatura")) {

                        if (!ponto.equals("Ponto Hot")) { //caso um ponto de interesse tenha sido selecionado
                            for (int i = 0; i < locais.size(); i++) {
                                if (locais.get(i).getNome().equals(local)) {
                                    for (int j = 0; j < locais.get(i).pontos.size(); j++) {
                                        if (locais.get(i).pontos.get(j).getNome().equals(ponto)) {
                                            hot = locais.get(i).pontos.get(j);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        users.add(new Licenciatura(nome, id, Double.parseDouble(montante), hot));

                        Interface2 mainInterface = new Interface2(users,locais,matriz_distancias);
                        mainInterface.setTitle("MENU");
                        mainInterface.setSize(1000, 700);
                        mainInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainInterface.setVisible(true);
                        dispose();
                    }
                }
                else{
                    show_error("Verifique as suas informações!");
                    System.out.println("algo errado!\n");
                }
            }
        }
    }
    
    //Como mostra as preferencias de acordo com o ciclo do user
    public class CicloCBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == cursoCB ){
                String ciclo = (String)cursoCB.getSelectedItem();
                
                
                if(ciclo.equals("Mestrado")){
                    registarLocal.removeAllItems();
                    registarLocal.addItem("Local a evitar");
                    for (int i=0; i<locais.size();i++){
                        registarLocal.addItem(locais.get(i).getNome());
                    }
                    registrarPI.removeAllItems();
                    registrarPI.addItem("-");
                } else if (ciclo.equals("Licenciatura")) {
                    registarLocal.removeAllItems();
                    registarLocal.addItem("Local");
                    for (int i=0; i<locais.size();i++){
                        registarLocal.addItem(locais.get(i).getNome());
                    }
                    registrarPI.removeAllItems();
                    registrarPI.addItem("Ponto Hot");
                } else {
                    registrarPI.removeAllItems();
                    registrarPI.addItem("-");
                    registarLocal.removeAllItems();
                    registarLocal.addItem("-");
                    
                }

            }
        }
    }
        
    public Interface (ArrayList<Utilizador> users, ArrayList<Local> locais, int[][] matriz_distancias) {
        
        this.users = users;
        this.locais = locais;
        this.matriz_distancias = matriz_distancias;
        
        
        
        //panel registo
        
        registaUser = new JPanel();
        registaUser.setLayout(null);
        bemVindoLabel = new JLabel ("Bem-vindo!");
        nomeLabel = new JLabel("Nome");
        cursoLabel = new JLabel("Curso");
        montanteLabel = new JLabel("Montante Máximo");
        eurosLabel = new JLabel("€");
        nomeTF = new JTextField(10);
        montanteTF = new JTextField(10);
        continuarBT = new JButton("SEGUINTE");
        cursoCB = new JComboBox (cursos);
        preferenciaLabel = new JLabel("Preferencia");
        registarLocal = new JComboBox();
        registrarPI = new JComboBox();
        
        bemVindoLabel.setBounds(300,10,100,25);
        nomeLabel.setBounds(100,75,100,25);
        cursoLabel.setBounds(100,125,100,25);
        montanteLabel.setBounds(37,175,150,25);
        eurosLabel.setBounds(200,175,150,25);
        nomeTF.setBounds(150,75,250,25);
        montanteTF.setBounds(150,175,50,25);
        cursoCB.setBounds(150,125,150,25);
        continuarBT.setBounds(450,240,150,25);
        preferenciaLabel.setBounds(37,240,100,25);
        registarLocal.setBounds(150,240,140,25);
        registrarPI.setBounds(300,240,140,25);
        
        registaUser.add(bemVindoLabel);
        registaUser.add(nomeLabel);
        registaUser.add(cursoLabel);
        registaUser.add(montanteLabel);
        registaUser.add(eurosLabel);
        registaUser.add(nomeTF);
        registaUser.add(montanteTF);
        registaUser.add(continuarBT);
        registaUser.add(cursoCB);
        registaUser.add(preferenciaLabel);
        registaUser.add(registarLocal);
        registaUser.add(registrarPI);
        
        continuarBT.addActionListener(new ButtonSeguinteListener());
        cursoCB.addActionListener(new CicloCBListener());
        registarLocal.addActionListener(new LocalCBListener());
        
        this.add(registaUser);
       
    }
    
     @SuppressWarnings("unchecked")
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
    }
    

    public void main() {
        Interface frameRegisto;
        ArrayList <Utilizador> users = new ArrayList<>();
        ArrayList <Local> locais = new ArrayList<>();
        int [][] matriz_distancias = new int[20][20]; 
        
        frameRegisto = new Interface(users, locais, matriz_distancias);
        
        frameRegisto.setTitle("REGISTO");
        frameRegisto.setResizable(false);
        frameRegisto.setSize(640,320);
        frameRegisto.setVisible(true);
        frameRegisto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
