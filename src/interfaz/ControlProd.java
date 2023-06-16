
package interfaz;
import config.Conexion;
import java.awt.ComponentOrientation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;

public class ControlProd extends javax.swing.JFrame {
    Conexion con1 = new Conexion();
    Connection conet;
    DefaultTableModel modeloProd;
    Statement st;
    ResultSet rs;
    int cc;

    public ControlProd() {
        initComponents();
        setLocationRelativeTo(null); //<-- Centrar
        consultar(); //<-- Lista la tabla de productos (select from)
        idproveedor(jCprov);
        clavecorta(jCCC);
    }

    void consultar() {
        String sql = "select * from inventario JOIN productos "
                + "on inventario.clave_corta=productos.clave_corta "
                + "JOIN proveedores ON productos.idproveedor=proveedores.idproveedor";
        try {
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            Object[] cp = new Object[7];
            modeloProd = (DefaultTableModel) tableControl.getModel();
            while (rs.next()) {
                cp[0] = rs.getInt("clave_corta");
                cp[1] = rs.getString("nombre_prov");
                cp[2] = rs.getString("nombre");
                cp[3] = rs.getDate("fecha_caducidad");
                cp[4] = rs.getDate("fecha_entrada");
                cp[5] = rs.getDate("fecha_salida");
                cp[6] = rs.getInt("existencias");
                modeloProd.addRow(cp);
            }
            tableControl.setModel(modeloProd);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    void limpiartabla() {
        for (int i = 0; i <= tableControl.getRowCount(); i++) {
            modeloProd.removeRow(i);
            i = i - 1;
        }
    }

    void agregar() {
        String cc = (String.valueOf(jTClaveC.getText()));
        String proveedor=jTidpr.getText();
        String existencias = (String.valueOf(jTExistencias.getText()));
        try {
            Date caducidad = jDCaducidad.getDate();
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String fcad = f.format(caducidad);
            /*     */
            Date entrada = jDEntrada.getDate();
          String fent = f.format(entrada);
            /*     */
            Date salida = jDSalida.getDate();
            String fsal = f.format(salida);
            try {
                String sql="INSERT INTO inventario(clave_corta,idproveedor,"
                        + "fecha_caducidad,fecha_entrada,fecha_salida,"
                        + "existencias)VALUES('"+cc+"','"+proveedor+"','"+fcad+"','"+fent+"','"+fsal+"','"+existencias+"')";
                conet = con1.getConnection();
                st = conet.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Dato agregado");
                limpiartabla();
                jCprov.setSelectedIndex(0);
                
                
            } catch (Exception e) {
                System.out.println("ERROR CONSULTA: "+e);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR FECHAS: ");
        }
            

    }
    void idproveedor(JComboBox prov){
        try {
            String sql="select nombre_prov from proveedores";
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                prov.addItem(rs.getString("nombre_prov"));
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    void clavecorta(JComboBox prod){
        try {
            String sql="select nombre from productos";
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                prod.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }

    void eliminartabla() {
        for (int i = 0; i < tableControl.getRowCount(); i++) {
            modeloProd.removeRow(i);
            i -= 1;
        }
    }

    void modificar() {
        int fila = tableControl.getSelectedRow();
        int clavec= Integer.parseInt((String) tableControl.getValueAt(fila, 0).toString());
        String prov=jTidpr.getText();
        String existencias =(String.valueOf(jTExistencias.getText()));
        //String prod= jTnombreprod.getText();
        /* */
        Date cad = jDCaducidad.getDate(); 
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String caducidad = f.format(cad);
        
        Date ent= jDEntrada.getDate();
        String entrada = f.format(ent);
        
        Date sal=jDSalida.getDate();
        String salida=f.format(sal);
        try{
        String sql="UPDATE inventario SET fecha_caducidad='"+caducidad+"',fecha_entrada='"+entrada+"',fecha_salida='"+salida+"',existencias='"+existencias+"'"
                + "where clave_corta="+clavec;
        try {
            conet = con1.getConnection();
                st = conet.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Datos actualizados");
                limpiartabla();
            
        } catch (Exception e) {
            System.out.println(""+e);
        }
        
        }catch (Exception e) {
            System.out.println("ERROR MODIFICAR: "+e);
        }
        
    }
    
void eliminar(){
    try{
        int fila= tableControl.getSelectedRow();
        int clavc=Integer.parseInt((String) tableControl.getValueAt(fila, 0).toString());
           String sql="DELETE FROM inventario WHERE clave_corta="+clavc;
        try {
            conet = con1.getConnection();
            st = conet.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Datos actualizados");
            limpiartabla();
            
        } catch (Exception e) {
            System.out.println("" + e);
        }} catch (  Exception e){
            System.out.println("ERROR: "+e);
        }
}
void limpiarcampos(){}
void buscar(){}

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBAgregar = new javax.swing.JButton();
        jBModi = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTnombreprod = new javax.swing.JTextField();
        jTProv = new javax.swing.JTextField();
        jDCaducidad = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jDEntrada = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jDSalida = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jTExistencias = new javax.swing.JTextField();
        jBuscar = new javax.swing.JButton();
        jTBuscar = new javax.swing.JTextField();
        jBRegresar = new javax.swing.JButton();
        jCprov = new javax.swing.JComboBox<>();
        jTidpr = new javax.swing.JTextField();
        jCCC = new javax.swing.JComboBox<>();
        jTClaveC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableControl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel2.setBackground(new java.awt.Color(102, 255, 51));

        jBAgregar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBAgregar.setText("Agregar");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        jBModi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBModi.setText("Modificar");
        jBModi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModiActionPerformed(evt);
            }
        });

        jBEliminar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBEliminar.setText("Eliminar");
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBModi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jBModi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 255, 102));

        jLabel1.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 36)); // NOI18N
        jLabel1.setText("CONTROL DE PRODUCTOS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 255, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Producto");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Proveedor: ");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Caducidad: ");

        jTnombreprod.setEditable(false);
        jTnombreprod.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jTProv.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Entrada: ");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Salida:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Existencias:");

        jTExistencias.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jBuscar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jBuscar.setText("Buscar");

        jBRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/regresar.jpg"))); // NOI18N
        jBRegresar.setText("R");

        jCprov.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jCprov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jCprov.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCprovItemStateChanged(evt);
            }
        });

        jCCC.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jCCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jCCC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCCCItemStateChanged(evt);
            }
        });

        jTClaveC.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Clave Corta");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel7))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel3)))
                        .addGap(22, 22, 22)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBRegresar)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(11, 11, 11))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCprov, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTidpr, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTProv, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTClaveC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCCC, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTnombreprod, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBRegresar))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTClaveC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTnombreprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCprov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTidpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableControl.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tableControl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave Corta", "Proveedor", "Producto", "F. Caducidad", "F. Entrada", "F. Salida", "Existencias"
            }
        ));
        tableControl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableControlMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableControl);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        consultar();
                
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void tableControlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableControlMouseClicked
        // TODO add your handling code here:
           int fila = tableControl.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Los datos son incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int idcc = Integer.parseInt((String) tableControl.getValueAt(fila, 0).toString());
          // int idcl = Integer.parseInt((String) tableControl.getValueAt(fila, 1).toString());
            String prov = (String) tableControl.getValueAt(fila, 1);
            String prod =(String) tableControl.getValueAt(fila, 2);
            jDCaducidad.setDate(java.sql.Date.valueOf(tableControl.getValueAt(fila, 3).toString()));
            jDEntrada.setDate(java.sql.Date.valueOf(tableControl.getValueAt(fila, 4).toString()));
            jDSalida.setDate(java.sql.Date.valueOf(tableControl.getValueAt(fila, 5).toString()));
            int exis = Integer.parseInt((String) tableControl.getValueAt(fila, 6).toString());  
            jTClaveC.setText(""+idcc);
            jTnombreprod.setText(prod);
            jTProv.setText(prov);
            jTExistencias.setText(" " + exis);
           // jTnombreprod.setText(prod);
            
            
  

        }
    }//GEN-LAST:event_tableControlMouseClicked

    private void jCprovItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCprovItemStateChanged
        // TODO add your handling code here:
        String np = (String) jCprov.getSelectedItem(); //almacenar item en variable
        try {
            String sql = "Select idproveedor from proveedores where nombre_prov='" + np + "'";
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                jTidpr.setText((rs.getString("idproveedor")));
                jTProv.setText(np);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
    }//GEN-LAST:event_jCprovItemStateChanged

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        // TODO add your handling code here:
        agregar();
        consultar();
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jCCCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCCCItemStateChanged
        // TODO add your handling code here:
        String prod = (String) jCCC.getSelectedItem(); //almacenar item en variable
        try {
            String sql = "Select clave_corta from productos where nombre='" + prod + "'";
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                jTClaveC.setText((rs.getString("clave_corta")));
                jTnombreprod.setText(prod);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }
    }//GEN-LAST:event_jCCCItemStateChanged

    private void jBModiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModiActionPerformed
        // TODO add your handling code here:
        modificar();
        consultar();
    }//GEN-LAST:event_jBModiActionPerformed

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
            java.util.logging.Logger.getLogger(ControlProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControlProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControlProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControlProd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBModi;
    private javax.swing.JButton jBRegresar;
    private javax.swing.JButton jBuscar;
    private javax.swing.JComboBox<String> jCCC;
    private javax.swing.JComboBox<String> jCprov;
    private com.toedter.calendar.JDateChooser jDCaducidad;
    private com.toedter.calendar.JDateChooser jDEntrada;
    private com.toedter.calendar.JDateChooser jDSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTBuscar;
    private javax.swing.JTextField jTClaveC;
    private javax.swing.JTextField jTExistencias;
    private javax.swing.JTextField jTProv;
    private javax.swing.JTextField jTidpr;
    private javax.swing.JTextField jTnombreprod;
    private javax.swing.JTable tableControl;
    // End of variables declaration//GEN-END:variables
}
