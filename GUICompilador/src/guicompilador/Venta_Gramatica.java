/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicompilador;

/**
 *
 * @author Ivan
 */
public class Venta_Gramatica extends javax.swing.JFrame {

    /**
     * Creates new form Venta_Gramatica
     */
    
    String gram;
    public Venta_Gramatica() {
        initComponents();
        
        gram = 
"begin                                 :       principal funciones | principal;\n" +
"principal                            :       INICIO '(' ')' '{' lineascodigos '}' FIN;\n" +
"lineascodigos                  :       lineascodigo | ;\n" +
"lineascodigo                    :       lineascodigo linea | linea;\n" +
"linea                                   :       invocarmetodo ';' | crearvariable ';' | cambiarvalor ';' | ciclocondicion ;\n" +
"invocarmetodo                 :       NOMBRECAMPO  '(' parametrosenvios ')';\n" +
"parametrosenvios           :       parenvio | ;\n" +
"parenvio                             :       parenvio ',' penvio | penvio ;\n" +
"penvio                                :        valor | NOMBRECAMPO;\n" +
"valor                                    :       ENTERO | DECIMAL | BOLEANO | CADENA;\n" +
"crearvariable                     :       tipodato NOMBRECAMPO | tipodato NOMBRECAMPO asignarvalor;\n" +
"tipodato                              :       T_ENTERO | T_DECIMAL | T_BOLEANO | T_CADENA;\n" +
"asignarvalor                      :       ASIGNADOR operasignacion | ASIGNADOR valor \n" +
"                                                    | ASIGNADOR NOMBRECAMPO;\n" +
"operasignacion	               :       aritmetica | invocarmetodo | incredisminvariable;\n" +
"aritmetica                          :       oprcomun | oprcomun oprcomplemento;\n" +
"oprcomun                          :       valor tipoopr valor | valor tipoopr NOMBRECAMPO \n" +
"                                                     | NOMBRECAMPO tipoopr valor | NOMBRECAMPO tipoopr NOMBRECAMPO;\n" +
"tipoopr                                :       SUMA | RESTA | MULTIPLICACION | DIVISION;\n" +
"oprcomplemento             :       oprcomplemento oprcom | oprcom;\n" +
"oprcom                              :       tipoopr valor | tipoopr NOMBRECAMPO;\n" +
"incredisminvariable        :       NOMBRECAMPO indis;\n" +
"indis                                   :       AUMENTAR | DISMINUIR;\n" +
"cambiarvalor                    :       NOMBRECAMPO ASIGNADOR cambvalor;\n" +
"cambvalor                         :       valor | operasignacion | NOMBRECAMPO;\n" +
"ciclocondicion                  :       ifcondicion | ciclofor | ciclowhile;\n" +
"ifcondicion                        :       condicionsi | condicionsi condicionno \n" +
"                                                    | condicionsi condicionessino condicionno;\n" +
"condicionsi                       :       IF '(' condicion ')' '{' lineascodigos '}';\n" +
"condicion                          :       valor condicional valor | valor condicional NOMBRECAMPO \n" +
"                                                    | NOMBRECAMPO condicional valor \n" +
"                                                    | NOMBRECAMPO condicional NOMBRECAMPO;\n" +
"condicional                       :       MAYOR | MENOR | IGUAL | MAYORIGUAL | MENORIGUAL | DIFERENTE;\n" +
"condicionno                      :       ELSE '{' lineascodigos '}';\n" +
"condicionessino              :       condicionessino condicionsino | condicionsino;\n" +
"condicionsino                   :       IFELSE '(' condicion ')' '{' lineascodigos '}';\n" +
"ciclofor                               :        FOR '(' iniciafor ';' condicion ';' incredisminvariable ')' '{' lineascodigos '}';\n" +
"iniciafor                              :        tipodato NOMBRECAMPO asignarvalor;\n" +
"ciclowhile                          :        WHILE '(' condicion ')' '{' lineascodigos '}';\n" +
"funciones                          :        funciones funcion | funcion;\n" +
"funcion                               :        tiporetorno NOMBRECAMPO '(' parametrosentrada ')' '{' lineascodigos '}' \n" +
"                                                     | CIRCULO '(' entdec ',' entdec ',' entdec ',' CADENA ')' ';' \n" +
"                                                     | RECTANGULO '(' entdec ',' entdec ',' entdec ',' entdec ',' CADENA ')' ';' \n" +
"                                                     | TRIANGULO '(' entdec ',' entdec ',' entdec ',' entdec ',' CADENA')' ';' \n" +
"                                                     | PAUSE '(' BOLEANO ')' ';' | INICIAR '(' BOLEANO ')' ';' \n" +
"                                                     | DETENER '(' BOLEANO ')' ';';\n" +
"tiporetorno                        :        tipodato;\n" +
"parametrosentrada        :        parametros;\n" +
"parametros                      :        parametros ',' parametro | parametro;\n" +
"parametro                        :        tipodato NOMBRECAMPO;\n" +
"entdec                               :        ENTERO | DECIMAL;";
        
    
        String prod = 
                "1    principal\n"+
                "2    INICIO '(' ')' '{' lineascodigos '}' FIN\n"+
                "3    lineascodigo linea\n"+
                "4    linea\n"+
                "11    crearvariable\n"+
                "12    tipo_dato NOMBRECAMPO\n"+
                "42    T_ENTERO NOMBRECAMPO\n"+
                "43    INICIO '(' ')' '{' T_ENTERO NOMBRECAMPO '}' FIN\n";
        jTextArea1.setText(gram);
        jTextArea2.setText(prod);
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Grámatica");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(378, 378, 378))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Venta_Gramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Venta_Gramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Venta_Gramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Venta_Gramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Venta_Gramatica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
