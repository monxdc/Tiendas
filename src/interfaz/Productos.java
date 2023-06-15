 
package interfaz;
import config.Conexion;
//import java.awt.ComponentOrientation;
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
         jTBuscar.setVisible(false);
        
    }
    void consultar(){
        String sql ="SELECT clave_corta,clave_larga,nombre,descripcion,"
                + "existencias,tipo, familia, nombre_prov from "
                + "productos JOIN proveedores ON productos.idproveedor=proveedores.idproveedor";
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
    void eliminartabla(){
        for (int i = 0; i <tableProductos.getRowCount(); i++) {
            tmProductos.removeRow(i);
            i -= 1;
        }
    
    }
    void buscar(){
        String bus = jTBuscar.getText();
        String dato= jTDato.getText();
        if(bus!=null){
            switch (bus) {
                case "Clave Corta":
                    String sql ="SELECT clave_corta,clave_larga,nombre,descripcion,"
                + "existencias,tipo, familia, nombre_prov from "
                + "productos JOIN proveedores ON productos.idproveedor=proveedores.idproveedor "
                            + "where clave_corta LIKE '%"+dato+"%'";
                    try {
                        eliminartabla();
                        conet = con1.getConnection();
                        st = conet.createStatement();
                        rs = st.executeQuery(sql);
                        Object[] productos = new Object[8];
                        tmProductos = (DefaultTableModel) tableProductos.getModel();
                        while(rs.next()){
                            productos[0] = rs.getInt("clave_corta");
                            productos[1] = rs.getInt("clave_larga");
                            productos[2] = rs.getString("nombre");
                            productos[3] = rs.getString("descripcion");
                            productos[4] = rs.getInt("existencias");
                            productos[5] = rs.getString("tipo");
                            productos[6] = rs.getString("familia");
                            productos[7] = rs.getString("nombre_prov");
                            tmProductos.addRow(productos);                         
                        }if(tmProductos.getRowCount()==0){
                            JOptionPane.showMessageDialog(null, "No existe este registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                         jTBuscar.setText("");
                        consultar();
                                }
                        tableProductos.setModel(tmProductos);
                    } catch (Exception e) {
                        System.out.println("ERROR CC:" +e);
                    }
                    break;     
                case "Nombre":
                    String sqln = "SELECT clave_corta,clave_larga,nombre,descripcion,"
                            + "existencias,tipo, familia, nombre_prov from "
                            + "productos JOIN proveedores ON productos.idproveedor=proveedores.idproveedor "
                            + "where nombre LIKE '%" + dato + "%'";
                    try {
                        eliminartabla();
                        conet = con1.getConnection();
                        st = conet.createStatement();
                        rs = st.executeQuery(sqln);
                        Object[] productos = new Object[8];
                        tmProductos = (DefaultTableModel) tableProductos.getModel();
                        while (rs.next()) {
                            productos[0] = rs.getInt("clave_corta");
                            productos[1] = rs.getInt("clave_larga");
                            productos[2] = rs.getString("nombre");
                            productos[3] = rs.getString("descripcion");
                            productos[4] = rs.getInt("existencias");
                            productos[5] = rs.getString("tipo");
                            productos[6] = rs.getString("familia");
                            productos[7] = rs.getString("nombre_prov");
                            tmProductos.addRow(productos);
                        }
                        if (tmProductos.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No existe este registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                            jTBuscar.setText("");
                            consultar();
                        }
                        tableProductos.setModel(tmProductos);
                    } catch (Exception e) {
                        System.out.println("ERROR N:" + e);
                    }
                    break;
                case "Existencias":
                  
                    String sqle = "SELECT clave_corta,clave_larga,nombre,descripcion,"
                            + "existencias,tipo, familia, nombre_prov from "
                            + "productos JOIN proveedores ON productos.idproveedor=proveedores.idproveedor "
                            + "order by existencias";
                    try {
                        eliminartabla();
                        conet = con1.getConnection();
                        st = conet.createStatement();
                        rs = st.executeQuery(sqle);
                        Object[] productos = new Object[8];
                        tmProductos = (DefaultTableModel) tableProductos.getModel();
                        while (rs.next()) {
                            productos[0] = rs.getInt("clave_corta");
                            productos[1] = rs.getInt("clave_larga");
                            productos[2] = rs.getString("nombre");
                            productos[3] = rs.getString("descripcion");
                            productos[4] = rs.getInt("existencias");
                            productos[5] = rs.getString("tipo");
                            productos[6] = rs.getString("familia");
                            productos[7] = rs.getString("nombre_prov");
                            tmProductos.addRow(productos);
                        }
                        if (tmProductos.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No existe este registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                            jTBuscar.setText("");
                            consultar();
                        }
                        tableProductos.setModel(tmProductos);
                    } catch (Exception e) {
                        System.out.println("ERROR N:" + e);
                    }
                     break;
                case "Tipo":
                    String sqlt = "SELECT clave_corta,clave_larga,nombre,descripcion,"
                            + "existencias,tipo, familia, nombre_prov from "
                            + "productos JOIN proveedores ON productos.idproveedor=proveedores.idproveedor "
                            + "where tipo LIKE '%" + dato + "%'";
                    try {
                        eliminartabla();
                        conet = con1.getConnection();
                        st = conet.createStatement();
                        rs = st.executeQuery(sqlt);
                        Object[] productos = new Object[8];
                        tmProductos = (DefaultTableModel) tableProductos.getModel();
                        while (rs.next()) {
                            productos[0] = rs.getInt("clave_corta");
                            productos[1] = rs.getInt("clave_larga");
                            productos[2] = rs.getString("nombre");
                            productos[3] = rs.getString("descripcion");
                            productos[4] = rs.getInt("existencias");
                            productos[5] = rs.getString("tipo");
                            productos[6] = rs.getString("familia");
                            productos[7] = rs.getString("nombre_prov");
                            tmProductos.addRow(productos);
                        }
                        if (tmProductos.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No existe este registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                            jTBuscar.setText("");
                            consultar();
                        }
                        tableProductos.setModel(tmProductos);
                    } catch (Exception e) {
                        System.out.println("ERROR T:" + e);
                    }
                    break;
                case "Familia":
                    String sqlf = "SELECT clave_corta,clave_larga,nombre,descripcion,"
                            + "existencias,tipo, familia, nombre_prov from "
                            + "productos JOIN proveedores ON productos.idproveedor=proveedores.idproveedor "
                            + "where familia LIKE '%" + dato + "%'";
                    try {
                        eliminartabla();
                        conet = con1.getConnection();
                        st = conet.createStatement();
                        rs = st.executeQuery(sqlf);
                        Object[] productos = new Object[8];
                        tmProductos = (DefaultTableModel) tableProductos.getModel();
                        while (rs.next()) {
                            productos[0] = rs.getInt("clave_corta");
                            productos[1] = rs.getInt("clave_larga");
                            productos[2] = rs.getString("nombre");
                            productos[3] = rs.getString("descripcion");
                            productos[4] = rs.getInt("existencias");
                            productos[5] = rs.getString("tipo");
                            productos[6] = rs.getString("familia");
                            productos[7] = rs.getString("nombre_prov");
                            tmProductos.addRow(productos);
                        }
                        if (tmProductos.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No existe este registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                            jTBuscar.setText("");
                            consultar();
                        }
                        tableProductos.setModel(tmProductos);
                    } catch (Exception e) {
                        System.out.println("ERROR F:" + e);
                    }
                    break;        
            }
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
        jBPrincipal = new javax.swing.JButton();
        jBProveedor = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTExism = new javax.swing.JTextField();
        jBBuscar = new javax.swing.JButton();
        jTBuscar = new javax.swing.JTextField();
        jCOpciones = new javax.swing.JComboBox<>();
        jTDato = new javax.swing.JTextField();
        jBback = new javax.swing.JButton();

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
                .addContainerGap(153, Short.MAX_VALUE))
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
            .addComponent(jLabel1)
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        tableProductos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));

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

        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setText("Control de productos");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(53, 53, 53)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jTExism.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTExismActionPerformed(evt);
            }
        });

        jBBuscar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBBuscar.setText("Buscar");
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jTBuscar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jCOpciones.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jCOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Clave Corta", "Nombre", "Existencias", "Tipo", "Familia" }));
        jCOpciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCOpcionesItemStateChanged(evt);
            }
        });

        jTDato.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jBback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/regresar.jpg"))); // NOI18N
        jBback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbackActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jTExism, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCOpciones, 0, 159, Short.MAX_VALUE)
                            .addComponent(jTDato))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBback)))
                        .addGap(122, 122, 122))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jBBuscar)
                                .addComponent(jTDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jBback, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTExism, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
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
                .addGap(0, 9, Short.MAX_VALUE))
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

            jTExism.setText(""+exs);

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

    private void jBPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrincipalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBPrincipalActionPerformed

    private void jBProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBProveedorActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
      try{ 
          /*aP.setVisible(true);
       this.setVisible(true); */
    
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

    private void jCOpcionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCOpcionesItemStateChanged
        // TODO add your handling code here:
        String b = (String) jCOpciones.getSelectedItem();
        switch (b){
            case "Clave Corta":
            jTBuscar.setText(b);
            break;
            case "Nombre":
                jTBuscar.setText(b);
                break;
            case "Existencias":
                jTBuscar.setText(b);
                break;
            case "Tipo":
                jTBuscar.setText(b);
                break;    
            case "Familia":
                jTBuscar.setText(b);
                break;
        }
    }//GEN-LAST:event_jCOpcionesItemStateChanged

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        // TODO add your handling code here:
        buscar();
       
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbackActionPerformed
        // TODO add your handling code here:
        jTDato.setText("");
        eliminartabla();
        consultar();
    }//GEN-LAST:event_jBbackActionPerformed

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
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBPrincipal;
    private javax.swing.JButton jBProveedor;
    private javax.swing.JButton jBback;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCOpciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTBuscar;
    private javax.swing.JTextField jTDato;
    private javax.swing.JTextField jTExism;
    public javax.swing.JTable tableProductos;
    // End of variables declaration//GEN-END:variables
}
