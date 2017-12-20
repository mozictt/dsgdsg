/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package own_camping;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import konfig.koneksi;

/**
 *
 * @author Fatma Wati
 */
public class Petugas extends javax.swing.JInternalFrame {

    /**
     * Creates new form Petugas
     */
    public Petugas() {
        initComponents();
        otomatisID();
        tampil_tabel();
        nonaktif();
    }
    //mengosongkan data transaksi
    public void kosongkan(){
        txtAlamat.setText(null);
        txtPasword.setText(null);
        txtnama.setText(null);
        txtnoTlp.setText(null);
        jcLevel.setSelectedItem("User");
    }
    
    //Non aktifkan tombol
    public void nonaktif(){
        btEdit.setEnabled(false);
        bthapus.setEnabled(false);
        btSimpan.setEnabled(true);
    }
    public void aktif(){
        btEdit.setEnabled(true);
        bthapus.setEnabled(true);
        btSimpan.setEnabled(false);
    }
    public void otomatisID(){
        try {
            Statement statment=koneksi.GetconConnection().createStatement();;
            ResultSet rs= statment.executeQuery("select max(right(id_admin,5))as no from admin");
            while(rs.next()){
                if(rs.first()==false){
                    txtid.setText("AD-000001");
                }else{
                    rs.last();
                    int auto_id= rs.getInt(1)+1;
                    String no=String.valueOf(auto_id);
                    int noLong=no.length();
                    for(int a=0;a<6-noLong;a++)
                    {
                        no="0" +no;
                    }
                    txtid.setText("AD-"+no);
                }
            }
            rs.close();
            statment.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //menambahkan admin
    public void tambah(){
        String id = txtid.getText();
        String nama = txtnama.getText();
        String notlp = txtnoTlp.getText();
        String Alamat = txtAlamat.getText();
        String Password = txtPasword.getText();
        
        try {
            Statement statement=koneksi.GetconConnection().createStatement();
            statement.execute("INSERT into admin (id_admin,nm_admin,no_tlp,alamat,level,password) values('"+id+"','"+nama+"','"+notlp+"','"+Alamat+"','"+jcLevel.getSelectedItem()+"',MD5('"+Password+"'))");
            JOptionPane.showMessageDialog(this, "Data Telah Berhasil Disimpan");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Data Gagal Disimpan");
            e.printStackTrace();
        }
        tampil_tabel();
        nonaktif();
    }
    
    //Menampilkan data tabel admin
    public void tampil_tabel(){
        try {
            Statement statment= koneksi.GetconConnection().createStatement();
            ResultSet res= statment.executeQuery("select * from admin");
            DefaultTableModel tbl= new DefaultTableModel();
            tbl.addColumn("Id Admin");
            tbl.addColumn("Nama");
            tbl.addColumn("No Tlp");
            tbl.addColumn("Alamat");
            tbl.addColumn("Level");
            tabelAdmin.setModel(tbl);
            
            while(res.next()){
                tbl.addRow(new Object[]{
                    res.getString("id_admin"),
                    res.getString("nm_admin"),
                    res.getString("no_tlp"),
                    res.getString("alamat"),
                    res.getString("level")
                    
                });
            }
        } catch (Exception e) {
        }
    }
   
    
    //Edit Data Admin
    public void Edittabel(){
        String id = txtid.getText();
        String nama = txtnama.getText();
        String notlp = txtnoTlp.getText();
        String Alamat = txtAlamat.getText();
        
        if(txtPasword.getText().equals("")){
            //System.out.println("tanpa edit pasword");
            try {
                Statement statment= koneksi.GetconConnection().createStatement();
                statment.executeUpdate("UPDATE admin SET nm_admin='"+nama+"',no_tlp='"+notlp+"',alamat='"+Alamat+"',level='"+jcLevel.getSelectedItem()+"' where id_admin='"+id+"'");
                JOptionPane.showMessageDialog(this, "Update Data Berhasil");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal Update Data ");
            }
            
        }else{
             //System.out.println("ganti password edit pasword");
            try {
                Statement statment= koneksi.GetconConnection().createStatement();
                statment.executeUpdate("UPDATE admin SET nm_admin='"+nama+"',no_tlp='"+notlp+"',alamat='"+Alamat+"',level='"+jcLevel.getSelectedItem()+"',password=md5('"+this.txtPasword.getText()+"') where id_admin='"+id+"'");
                JOptionPane.showMessageDialog(this, "Update Data Berhasil");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal Update Data ");
            }
        }
        kosongkan();
        otomatisID();
        tampil_tabel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnoTlp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelAdmin = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btSimpan = new javax.swing.JButton();
        bthapus = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jcLevel = new javax.swing.JComboBox<>();
        txtPasword = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("ID Petugas");

        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Nama Petugas");

        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("No. Telepon");

        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Alamat");

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel5.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Form Petugas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tabelAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelAdminMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelAdmin);

        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Password");

        btSimpan.setBackground(new java.awt.Color(153, 153, 153));
        btSimpan.setForeground(new java.awt.Color(255, 255, 0));
        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });

        bthapus.setBackground(new java.awt.Color(153, 153, 153));
        bthapus.setForeground(new java.awt.Color(255, 255, 0));
        bthapus.setText("Hapus");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        btEdit.setBackground(new java.awt.Color(153, 153, 153));
        btEdit.setForeground(new java.awt.Color(255, 255, 0));
        btEdit.setText("Edit");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setForeground(new java.awt.Color(255, 255, 0));
        jButton4.setText("Keluar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Hak Akses");

        jcLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3))
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnoTlp, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(192, 192, 192)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPasword))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bthapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPasword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnoTlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jcLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpan)
                            .addComponent(bthapus)
                            .addComponent(btEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // TODO add your handling code here:
        tambah();
        kosongkan();
        otomatisID();
    }//GEN-LAST:event_btSimpanActionPerformed

    private void tabelAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAdminMouseClicked
        // TODO add your handling code here:
        int baris = tabelAdmin.rowAtPoint(evt.getPoint());
        String id_admin= tabelAdmin.getValueAt(baris, 0).toString();
        txtid.setText(id_admin);
        String nm_admin=tabelAdmin.getValueAt(baris, 1).toString();
        txtnama.setText(nm_admin);
        String no_tlp= tabelAdmin.getValueAt(baris, 2).toString();
        txtnoTlp.setText(no_tlp);
        String alamat= tabelAdmin.getValueAt(baris, 3).toString();
        txtAlamat.setText(alamat);
        String level= tabelAdmin.getValueAt(baris, 4).toString();
        jcLevel.setSelectedItem(level);
        aktif();
    }//GEN-LAST:event_tabelAdminMouseClicked

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        // TODO add your handling code here:
        String id_admin= txtid.getText();
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            statment.execute("DELETE from admin where id_admin= '"+id_admin+"'");
            JOptionPane.showMessageDialog(this, "Data '"+id_admin+"' Berhasil Dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data '"+id_admin+"' Gagal Dihapus");
            e.printStackTrace();
        }
        kosongkan();
        tampil_tabel();
        otomatisID();
        nonaktif();
    }//GEN-LAST:event_bthapusActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        // TODO add your handling code here:
        Edittabel();
        nonaktif();
    }//GEN-LAST:event_btEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcLevel;
    private javax.swing.JTable tabelAdmin;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JPasswordField txtPasword;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnoTlp;
    // End of variables declaration//GEN-END:variables
}
