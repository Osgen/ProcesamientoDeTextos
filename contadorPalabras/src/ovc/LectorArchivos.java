/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovc;
import javax.swing.JFileChooser;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 5e
 */
public class LectorArchivos extends javax.swing.JFrame {
    JFileChooser jf;
    File file;
    FileReader fr;
    BufferedReader br;
    List<ContadorPorArchivo> list = new ArrayList<>();
    Hashtable<String,String> repetidas=new Hashtable<String,String>();
    Hashtable<Character,Letra> letras=new Hashtable<Character,Letra>();
    
    /**
     * Creates new form LectorArchivos
     */
    public LectorArchivos() {
        initComponents();
       jf = new JFileChooser();
    }
    
    private void openMethod() throws IOException 
    {
        ContadorPorArchivo cont;
        String line;
        String[] text;
        int count=0;
        int countNoRepe=0;
        int countVocales=0;
        if(jf.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
        {
            file = jf.getSelectedFile();
            //jLabel1.setText(file.toString());
            try
            {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                while((line=br.readLine()) != null){
                    line=line.toLowerCase();
                    text=line.split(" ");
                    countVocales+=contarVocalPorLinea(text);
                    letras(text);
                    countNoRepe+=loadWords(text);
                    count += text.length;
                }
                String url= file.toString();
                System.out.println(url);
                cont = new ContadorPorArchivo(url, countNoRepe, count);
                list.add(cont);
                br.close();
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("nel");
            }
            catch(IOException ex)
            {
                System.out.print(ex);
            }
        }
        System.out.println( "Vocales:"+countVocales);
        System.out.println( getLetras());

        repetidas.clear();
    }
    
    public int contarVocalPorLinea(String[] text){
        int count=0;
        for(int i=0; i<text.length; i++){
            count+=contarSilabas(text[i]);
        }
        return count;
    }
    
    public static int contarSilabas(String word){
        int count = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '\"' || word.charAt(i) == '\'' || word.charAt(i) == '-' || word.charAt(i) == ',' || word.charAt(i) == ')' || word.charAt(i) == '(') {
                word = word.substring(0,i)+word.substring(i+1, word.length());
            }
        }
        boolean isPrevVowel = false;
        for (int j = 0; j < word.length(); j++) {
            if (word.contains("a") || word.contains("e") || word.contains("i") || word.contains("o") || word.contains("u")) {
                if (esVocal(word.charAt(j)) && !((word.charAt(j) == 'e') && (j == word.length()-1))) {
                    if (isPrevVowel == false) {
                        count++;
                        isPrevVowel = true;
                    }
                } else {
                    isPrevVowel = false;
                }
            } else {
                count++;
                break;
            }
        }
        return count;
    }
    
    public static boolean esVocal(char c){
        
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        } else {
            return false;
        }
    }
    
    private int loadWords(String[] text){
        int count=0;
        for(int i=0; i<text.length; i++){
            if(!repetidas.containsKey(text[i])){
                repetidas.put(text[i], text[i]);
                count++;
               // System.out.println(text[i]);
            }
        }
        return count;
    }
    
    private void letras(String [] text){
        for(int i=0; i<text.length; i++){
            String letra=text[i];
            for(int j=0; j<letra.length(); j++){
                char caracter = letra.charAt(j);
                if(letras.containsKey(letra.charAt(j))){
                    Letra l=letras.get(letra.charAt(j));
                            l.aumentar();
                    letras.put(letra.charAt(j), l);
                }else{
                    letras.put(letra.charAt(j), new Letra(letra.charAt(j)));
                }
            }
        }
    }
    
    public String getLetras(){
        String contenido="";
        Enumeration elements=letras.elements();
        while(elements.hasMoreElements()){
            contenido+=elements.nextElement().toString()+"\n";
        }
        return contenido;
    }
    
    private String loadMethod(){
        String contenido="";
        for(ContadorPorArchivo elemento:list){
            contenido += elemento.toString()+"\n";
        }
        return contenido;
    }
    
    private void file() throws IOException{
        List<String> lines = Arrays.asList(loadMethod(), getLetras());
        Path file = Paths.get("the-file-name.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrir = new javax.swing.JButton();
        btnCrearArchivo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAbrir.setText("Buscar archivo");
        btnAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAbrirMouseClicked(evt);
            }
        });

        btnCrearArchivo.setText("CrearArchivo");
        btnCrearArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearArchivoMouseClicked(evt);
            }
        });
        btnCrearArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearArchivo)
                    .addComponent(btnAbrir))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnAbrir)
                .addGap(18, 18, 18)
                .addComponent(btnCrearArchivo)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseClicked
        try {
            // TODO add your handling code here:
            openMethod();
        } catch (IOException ex) {
            Logger.getLogger(LectorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAbrirMouseClicked

    private void btnCrearArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearArchivoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearArchivoMouseClicked

    private void btnCrearArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearArchivoActionPerformed
        try {
            file();
        } catch (IOException ex) {
            Logger.getLogger(LectorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCrearArchivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LectorArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LectorArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LectorArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LectorArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LectorArchivos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCrearArchivo;
    // End of variables declaration//GEN-END:variables
}
