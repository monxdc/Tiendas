
package interfaz;
import config.Conexion;
import java.awt.ComponentOrientation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; //<-- Manipular JTable

public class Productos extends javax.swing.JFrame {
    Conexion con1 = new Conexion();
    Connection conet;
    DefaultTableModel tmProductos;
    Statement st;
    ResultSet rs;
    int clave_corta;
    AgregarProducto aP = new AgregarProducto();
    

   
    public Productos() {
        initComponents();
        setLocationRelativeTo(null); //<-- Centrar
        consultar(); //<-- Lista la tabla de productos (select from)
        jTExism.setVisible(false);
        
    }
    void consultar(){
        String sql ="SELECT clave_corta,clave_larga,nombre,descripcion,"
                + "existencias,tipo, familia, nombre_prov from "
                + "productos JOIN proveedores ON productos.idproveedor=proveedores.idproveedor;";
        try {
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            Object[] productos = new Object[8];
            tmProductos = (DefaultTableModel)tableProductos.getModel();
            while(rs.next()){
                productos[0]=rs.getInt("clave_corta");
                productos[1]=rs.getInt("clave_larga");
                productos[2]=rs.getString("nombre");
                productos[3]=rs.getString("descripcion");
                productos[4]=rs.getInt("existencias");
                productos[5]=rs.getString("tipo");
                productos[6]=rs.getString("familia");
                productos[7]=rs.getString("nombre_prov");
                tmProductos.addRow(productos);   
            }
            tableProductos.setModel(tmProductos);
        } catch (Exception e) {
            System.out.println(""+e);
        }
}
     void limpiarTabla(){
           /*Para que no se dupliquen los datos se ocupa este método que lo evite*/
           for (int i = 0; i <= tableProductos.getRowCount(); i++) {
            tmProductos.removeRow(i);
            i = i - 1;
        }
     }
    void modificar(){
       // AgregarProducto mod = new AgregarProducto();
        int fila = tableProductos.getSelectedRow();
        int cc = Integer.parseInt((String) tableProductos.getValueAt(fila, 0).toString());
        String existencias = String.valueOf(jTExism.getText());
        String sql ="UPDATE productos set existencias='"+existencias+"' where clave_corta="+cc;
        try {
            conet = con1.getConnection();
                st = conet.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Datos actualizados");
                limpiarTabla();
        } catch (Exception e) {
            System.out.println("ERROR1: "+e);
        }
        
    }
    void eliminar(){
        int fila = tableProductos.getSelectedRow();
        int cc=Integer.parseInt((String) tableProductos.getValueAt(fila, 0).toString());
        String sql ="DELETE FROM productos where clave_corta="+cc;
        try {
            conet = con1.getConnection();
            st = conet.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Datos actualizados");
            limpiarTabla();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    
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
        jPanel2 = new javax.swing.JPanel();
        jBAgregar = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jBAgregar1 = new javax.swing.JButton();
        jBPrincipal = new javax.swing.JButton();
        jBProveedor = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jTExism = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));

        jBAgregar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBAgregar.setText("Agregar");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        jBModificar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBModificar.setText("Modificar");
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });

        jBEliminar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBEliminar.setText("Eliminar");
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo.PNG"))); // NOI18N
        jLabel2.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jBModificar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jBModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 36)); // NOI18N
        jLabel1.setText("Lista de productos ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addComponent(jLabel1)
                .addContainerGap(308, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C.C", "C.L", "Nombre", "Descripción", "Existencias", "Tipo", "Familia", "Proveedor"
            }
        ));
        tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProductos);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));

        jBAgregar1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBAgregar1.setText("proove produ");
        jBAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregar1ActionPerformed(evt);
            }
        });

        jBPrincipal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBPrincipal.setText("Principal");
        jBPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPrincipalActionPerformed(evt);
            }
        });

        jBProveedor.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBProveedor.setText("Proovedor");
        jBProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProveedorActionPerformed(evt);
            }
        });

        jBCancelar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(jBAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jBPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(jBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(658, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(jBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)))
        );

        jTExism.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTExismActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jTExism, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jTExism, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        //AgregarProducto aP = new AgregarProducto();
        aP.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        // TODO add your handling code here:
        int si = JOptionPane.showConfirmDialog(rootPane, "SOLO PUEDES MODIFICAR EXISTENCIAS, ¿DESEA SEGUIR?");
       String exs;
        if (JOptionPane.OK_OPTION == si) {
           
            exs = JOptionPane.showInputDialog("INTRODUZCA LAS NUEVAS EXISTENCIAS: ");
            //Integer.parseInt(exs);
            //jTExism.setVisible();
            jTExism.setText(""+exs);
            
           // aP.setVisible(true);
          modificar();
            consultar();
        }else{
            
        }
        
    
        
    }//GEN-LAST:event_jBModificarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        consultar();
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jBAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAgregar1ActionPerformed

    private void jBPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrincipalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBPrincipalActionPerformed

    private void jBProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBProveedorActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
      try{ aP.setVisible(true);
       this.setVisible(true);
    
        int fila = tableProductos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Selecciona un registro", "ERROR", JOptionPane.ERROR_MESSAGE);     
    }else{
            try {
                int cc= Integer.parseInt((String) tableProductos.getValueAt(fila, 0).toString());
            int cl=Integer.parseInt((String) tableProductos.getValueAt(fila, 1).toString());
            String nombre= (String) tableProductos.getValueAt(fila, 2);
            String des= (String) tableProductos.getValueAt(fila, 3);
            int exi= Integer.parseInt((String) tableProductos.getValueAt(fila, 4).toString());
            String tipo= (String) tableProductos.getValueAt(fila, 5);          
            String familia= (String) tableProductos.getValueAt(fila, 6);
            String prov =(String) tableProductos.getValueAt(fila, 7);
            aP.jTClave_corta.setText(""+cc);
            aP.jTClave_larga.setText(""+cl);
            aP.jTNombre.setText(nombre);
            aP.jTDescripcion.setText(des);
            aP.jTExistencias.setText(""+exi);
            aP.jTTipo.setText(tipo);
            aP.jTFam.setText(familia);
            aP.jTNP.setText(prov);
            } catch (Exception e) {
                System.out.println("ERRO: "+e);
            }
            
  
        }
      } 
      catch (Exception m){
          System.out.println("ERRROSS: "+m);}
          
       
    }//GEN-LAST:event_tableProductosMouseClicked

    private void jTExismActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTExismActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTExismActionPerformed

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jBAgregar1;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBPrincipal;
    private javax.swing.JButton jBProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTExism;
    public javax.swing.JTable tableProductos;
    // End of variables declaration//GEN-END:variables
}
