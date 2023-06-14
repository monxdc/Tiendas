package interfaz;

import config.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class AgregarProducto extends javax.swing.JFrame {
    Conexion con1 = new Conexion();
    Connection conet;
    DefaultTableModel tableProductos;
    Statement st;
    ResultSet rs;
    /**
     * Creates new form AgregarProducto
     */
    public AgregarProducto() {
        initComponents();
        agregarProveedor(jCIdProveedor);
        familia(jCFamilia);
        tipo(jCTipo);
       
        
        
    }
 
    void agregar(){
        String clavec=String.valueOf(jTClave_corta.getText());
        String clavel=String.valueOf(jTClave_larga.getText());
        String nombre=jTNombre.getText();
        String descripcion=jTDescripcion.getText();
        String existencias= String.valueOf(jTExistencias.getText());
        String tipo =jCTipo.getSelectedItem().toString();
       // jTTipo.setText(tipo);
       String familia =jCFamilia.getSelectedItem().toString();
      // jTFam.setText(familia);
        String prov = String.valueOf(jTIdProveedor.getText());
        //jTNP.setText(prov);
   
            try{
           String sql="INSERT INTO productos(clave_corta,clave_larga,"
                   + "nombre,descripcion,existencias,tipo,familia,"
                   + "idproveedor) values('"+clavec+"','"+clavel+"',"
                   + "'"+nombre+"','"+descripcion+"','"+existencias+"',"
                   + "'"+tipo+"','"+familia+"','"+prov+"')";
                conet = con1.getConnection();
                st = conet.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Dato agregado");
               //Mandar a llamar el método de la clase productos
            Productos tabla = new Productos();
            tabla.limpiarTabla();
            } catch (Exception e) {
                System.out.println("" + e);
            }
       

    }
      
       
    void agregarProveedor( JComboBox nomprov){
     /*En este método se va a listar los nombres de los proveedores en el combo box,
     como parámetro recibe el combo box (idprov)*/
        try {
            String sql="Select nombre_prov from proveedores";
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
            nomprov.addItem(rs.getString("nombre_prov"));
           
            }
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        
    }
    void familia(JComboBox familia){
        try {
            String sql="select familia from productos";
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                familia.addItem(rs.getString("familia"));
            }
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
    }
    void tipo(JComboBox tipo){
        try {
              String sql="select tipo from productos";
              conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                tipo.addItem(rs.getString("tipo"));
            }
            
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
  
    }


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLIdCliente = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jLNombre = new javax.swing.JLabel();
        jTDescripcion = new javax.swing.JTextField();
        jLDireccion = new javax.swing.JLabel();
        jTExistencias = new javax.swing.JTextField();
        jLExistencias = new javax.swing.JLabel();
        jLColonia1 = new javax.swing.JLabel();
        jLColonia2 = new javax.swing.JLabel();
        jLColonia3 = new javax.swing.JLabel();
        jLColonia4 = new javax.swing.JLabel();
        jTClave_corta = new javax.swing.JTextField();
        jTClave_larga = new javax.swing.JTextField();
        jCIdProveedor = new javax.swing.JComboBox<>();
        jTIdProveedor = new javax.swing.JTextField();
        jCTipo = new javax.swing.JComboBox<>();
        jCFamilia = new javax.swing.JComboBox<>();
        jTTipo = new javax.swing.JTextField();
        jTFam = new javax.swing.JTextField();
        jTNP = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 36)); // NOI18N
        jLabel1.setText("Registro de productos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(73, 73, 73))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLIdCliente.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLIdCliente.setText("Clave Corta");

        jTNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTNombreKeyTyped(evt);
            }
        });

        jLNombre.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLNombre.setText("Nombre");

        jLDireccion.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLDireccion.setText("Descripción");

        jTExistencias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTExistenciasKeyTyped(evt);
            }
        });

        jLExistencias.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLExistencias.setText("Existencias");

        jLColonia1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLColonia1.setText("Tipo");

        jLColonia2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLColonia2.setText("Familia");

        jLColonia3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLColonia3.setText("ID Proveedor");

        jLColonia4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLColonia4.setText("Clave Larga");

        jTClave_corta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTClave_cortaKeyTyped(evt);
            }
        });

        jTClave_larga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTClave_largaKeyTyped(evt);
            }
        });

        jCIdProveedor.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCIdProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jCIdProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCIdProveedorItemStateChanged(evt);
            }
        });
        jCIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCIdProveedorActionPerformed(evt);
            }
        });

        jTIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTIdProveedorActionPerformed(evt);
            }
        });
        jTIdProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTIdProveedorKeyTyped(evt);
            }
        });

        jCTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Camara Fria", "Pasillo" }));
        jCTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCTipoItemStateChanged(evt);
            }
        });

        jCFamilia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Lacteos", "Panificados", "Embutidos" }));
        jCFamilia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCFamiliaItemStateChanged(evt);
            }
        });

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLColonia4)
                            .addComponent(jLIdCliente))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTClave_corta, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTClave_larga, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDireccion)
                            .addComponent(jLExistencias)
                            .addComponent(jLColonia1)
                            .addComponent(jLNombre)
                            .addComponent(jLColonia2))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(jTDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jCTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jTIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(142, 142, 142)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFam, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLColonia3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTNP)
                                    .addComponent(jCIdProveedor, 0, 165, Short.MAX_VALUE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButton1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLIdCliente)
                    .addComponent(jTClave_corta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTClave_larga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLColonia4))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombre)
                    .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDireccion)
                    .addComponent(jTDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLExistencias)
                    .addComponent(jTExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLColonia1)
                    .addComponent(jCTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLColonia2))
                .addGap(18, 18, 18)
                .addComponent(jTFam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLColonia3)
                    .addComponent(jCIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTNP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTExistenciasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTExistenciasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTExistenciasKeyTyped

    private void jTNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNombreKeyTyped

    private void jTClave_cortaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTClave_cortaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClave_cortaKeyTyped

    private void jTClave_largaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTClave_largaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClave_largaKeyTyped

    private void jCIdProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCIdProveedorItemStateChanged
        String proveedor = (String) jCIdProveedor.getSelectedItem(); //almacenar item en variable
        try {
            String sql = "Select idproveedor, nombre_prov from proveedores where nombre_prov='" + proveedor + "'";
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                jTIdProveedor.setText((rs.getString("idproveedor")));
              
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }//GEN-LAST:event_jCIdProveedorItemStateChanged

    private void jTIdProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTIdProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTIdProveedorKeyTyped

    private void jCIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCIdProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCIdProveedorActionPerformed

    private void jTIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTIdProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTIdProveedorActionPerformed

    private void jCTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCTipoItemStateChanged
        // TODO add your handling code here:
        String tipo  = (String) jCTipo.getSelectedItem();
                jTTipo.setText(tipo);
        
    }//GEN-LAST:event_jCTipoItemStateChanged

    private void jCFamiliaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCFamiliaItemStateChanged
        // TODO add your handling code here:
        String familia  = (String) jCFamilia.getSelectedItem();
                jTFam.setText(familia);
    }//GEN-LAST:event_jCFamiliaItemStateChanged

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
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public javax.swing.JComboBox<String> jCFamilia;
    public javax.swing.JComboBox<String> jCIdProveedor;
    public javax.swing.JComboBox<String> jCTipo;
    private javax.swing.JLabel jLColonia1;
    private javax.swing.JLabel jLColonia2;
    private javax.swing.JLabel jLColonia3;
    private javax.swing.JLabel jLColonia4;
    private javax.swing.JLabel jLDireccion;
    private javax.swing.JLabel jLExistencias;
    private javax.swing.JLabel jLIdCliente;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JTextField jTClave_corta;
    public javax.swing.JTextField jTClave_larga;
    public javax.swing.JTextField jTDescripcion;
    public javax.swing.JTextField jTExistencias;
    public javax.swing.JTextField jTFam;
    private javax.swing.JTextField jTIdProveedor;
    public javax.swing.JTextField jTNP;
    public javax.swing.JTextField jTNombre;
    public javax.swing.JTextField jTTipo;
    // End of variables declaration//GEN-END:variables
}
