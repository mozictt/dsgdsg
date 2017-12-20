/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package own_camping;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import login.login;
import login.sessionLogin;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author USER
 */
public class frmUtama extends javax.swing.JFrame {

    /**
     * Creates new form MU
     */
    //jkui
   
    public int kondisiLogin;
    int Login;
    
    public frmUtama() {
        setTitle("APLIKASI OWN CAMPING VR TP-01");
         initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
       // System.out.println(Login);
        belumLogin();
        prosesCekLogin();
        
    }
    public void prosesCekLogin(){
        Login=kondisiLogin;
            if(Login==1){
                loginAdmin();
            }else if(Login==2){
                loginUser();
            }else{
                belumLogin();
            }
    }
    public void loginAdmin(){
        btBarangKeluar.setEnabled(true);
       btPenyewa.setEnabled(true);
       btPetugas.setEnabled(true);
       btTransaksi.setEnabled(true);
       btdataPeralatan.setEnabled(true);
       dataPenyewa.setEnabled(true);
       jMenuItem9.setEnabled(true);
       SewaAlat.setEnabled(true);
       disewa.setEnabled(true);
       LapPendapatanDenda.setEnabled(true);
       lapDataBarang.setEnabled(true);
       lapDataSewa.setEnabled(true);
       LapPendapatan.setEnabled(true);
       manageUser.setEnabled(true);
       ChangePasswors.setEnabled(true); 
       
    }
     public void loginUser(){
        btBarangKeluar.setEnabled(true);
       btPenyewa.setEnabled(true);
       btPetugas.setEnabled(false);
       btTransaksi.setEnabled(true);
       btdataPeralatan.setEnabled(true);
       dataPenyewa.setEnabled(true);
       jMenuItem9.setEnabled(true);
       SewaAlat.setEnabled(true);
       disewa.setEnabled(true);
       LapPendapatanDenda.setEnabled(false);
       lapDataBarang.setEnabled(true);
       lapDataSewa.setEnabled(true);
       LapPendapatan.setEnabled(false);
       manageUser.setEnabled(false);
       ChangePasswors.setEnabled(true); 
    }
    public void belumLogin(){
       btBarangKeluar.setEnabled(false);
       btPenyewa.setEnabled(false);
       btPetugas.setEnabled(false);
       btTransaksi.setEnabled(false);
       btdataPeralatan.setEnabled(false);
       dataPenyewa.setEnabled(false);
       jMenuItem9.setEnabled(false);
       SewaAlat.setEnabled(false);
       disewa.setEnabled(false);
       LapPendapatanDenda.setEnabled(false);
       lapDataBarang.setEnabled(false);
       lapDataSewa.setEnabled(false);
       LapPendapatan.setEnabled(false);
       manageUser.setEnabled(false);
       ChangePasswors.setEnabled(false);       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btTransaksi = new javax.swing.JButton();
        btPetugas = new javax.swing.JButton();
        btPenyewa = new javax.swing.JButton();
        btdataPeralatan = new javax.swing.JButton();
        btLogin = new javax.swing.JButton();
        btBarangKeluar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        dataPeralatan = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        dataPenyewa = new javax.swing.JMenuItem();
        Disewa = new javax.swing.JMenu();
        SewaAlat = new javax.swing.JMenuItem();
        disewa = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        lapDataBarang = new javax.swing.JMenuItem();
        lapDataSewa = new javax.swing.JMenuItem();
        PENDAPATAN = new javax.swing.JMenu();
        LapPendapatan = new javax.swing.JMenuItem();
        LapPendapatanDenda = new javax.swing.JMenuItem();
        menegUser = new javax.swing.JMenu();
        manageUser = new javax.swing.JMenuItem();
        ChangePasswors = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(102, 102, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        btTransaksi.setBackground(new java.awt.Color(153, 153, 153));
        btTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disewa.png"))); // NOI18N
        btTransaksi.setPreferredSize(new java.awt.Dimension(79, 23));
        btTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btTransaksiMouseEntered(evt);
            }
        });
        btTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTransaksiActionPerformed(evt);
            }
        });

        btPetugas.setBackground(new java.awt.Color(153, 153, 153));
        btPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Medium 161.png"))); // NOI18N
        btPetugas.setPreferredSize(new java.awt.Dimension(79, 23));
        btPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btPetugasMouseEntered(evt);
            }
        });
        btPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPetugasActionPerformed(evt);
            }
        });

        btPenyewa.setBackground(new java.awt.Color(153, 153, 153));
        btPenyewa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/penyewa.png"))); // NOI18N
        btPenyewa.setPreferredSize(new java.awt.Dimension(79, 23));
        btPenyewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btPenyewaMouseEntered(evt);
            }
        });
        btPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPenyewaActionPerformed(evt);
            }
        });

        btdataPeralatan.setBackground(new java.awt.Color(153, 153, 153));
        btdataPeralatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/centang.png"))); // NOI18N
        btdataPeralatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btdataPeralatanMouseEntered(evt);
            }
        });
        btdataPeralatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdataPeralatanActionPerformed(evt);
            }
        });

        btLogin.setBackground(new java.awt.Color(153, 153, 153));
        btLogin.setText("Login");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        btBarangKeluar.setBackground(new java.awt.Color(153, 153, 153));
        btBarangKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang.png"))); // NOI18N
        btBarangKeluar.setPreferredSize(new java.awt.Dimension(79, 23));
        btBarangKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btBarangKeluarMouseEntered(evt);
            }
        });
        btBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarangKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btdataPeralatan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btdataPeralatan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OWN CAMPING");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));

        dataPeralatan.setBackground(new java.awt.Color(51, 51, 51));
        dataPeralatan.setForeground(new java.awt.Color(0, 255, 0));
        dataPeralatan.setText("Data");
        dataPeralatan.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jMenuItem9.setBackground(new java.awt.Color(51, 51, 51));
        jMenuItem9.setForeground(new java.awt.Color(0, 255, 51));
        jMenuItem9.setText("Data Peralatan");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        dataPeralatan.add(jMenuItem9);

        dataPenyewa.setBackground(new java.awt.Color(51, 51, 51));
        dataPenyewa.setForeground(new java.awt.Color(0, 255, 0));
        dataPenyewa.setText("Data Penyewa");
        dataPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataPenyewaActionPerformed(evt);
            }
        });
        dataPeralatan.add(dataPenyewa);

        jMenuBar1.add(dataPeralatan);

        Disewa.setBackground(new java.awt.Color(51, 51, 51));
        Disewa.setForeground(new java.awt.Color(0, 255, 0));
        Disewa.setText("Transaksi");
        Disewa.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        SewaAlat.setBackground(new java.awt.Color(51, 51, 51));
        SewaAlat.setForeground(new java.awt.Color(0, 255, 0));
        SewaAlat.setText("Sewa Alat");
        SewaAlat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SewaAlatActionPerformed(evt);
            }
        });
        Disewa.add(SewaAlat);

        disewa.setBackground(new java.awt.Color(51, 51, 51));
        disewa.setForeground(new java.awt.Color(0, 255, 0));
        disewa.setText("Disewa");
        disewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disewaActionPerformed(evt);
            }
        });
        Disewa.add(disewa);

        jMenuBar1.add(Disewa);

        jMenu4.setBackground(new java.awt.Color(51, 51, 51));
        jMenu4.setForeground(new java.awt.Color(0, 255, 0));
        jMenu4.setText("Laporan");
        jMenu4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        lapDataBarang.setBackground(new java.awt.Color(51, 51, 51));
        lapDataBarang.setForeground(new java.awt.Color(51, 255, 0));
        lapDataBarang.setText("Lap Data Barang");
        lapDataBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapDataBarangActionPerformed(evt);
            }
        });
        jMenu4.add(lapDataBarang);

        lapDataSewa.setBackground(new java.awt.Color(51, 51, 51));
        lapDataSewa.setForeground(new java.awt.Color(0, 255, 0));
        lapDataSewa.setText("Lap Data Sewa");
        lapDataSewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapDataSewaActionPerformed(evt);
            }
        });
        jMenu4.add(lapDataSewa);

        PENDAPATAN.setBackground(new java.awt.Color(255, 102, 102));
        PENDAPATAN.setText("Lap Pendapatan");
        PENDAPATAN.setToolTipText("");

        LapPendapatan.setBackground(new java.awt.Color(51, 51, 51));
        LapPendapatan.setForeground(new java.awt.Color(0, 255, 0));
        LapPendapatan.setText("Lap Pendapatan Sewa");
        LapPendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapPendapatanActionPerformed(evt);
            }
        });
        PENDAPATAN.add(LapPendapatan);

        LapPendapatanDenda.setBackground(new java.awt.Color(51, 51, 51));
        LapPendapatanDenda.setForeground(new java.awt.Color(0, 255, 0));
        LapPendapatanDenda.setText("Lap Pendapatan Denda");
        LapPendapatanDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapPendapatanDendaActionPerformed(evt);
            }
        });
        PENDAPATAN.add(LapPendapatanDenda);

        jMenu4.add(PENDAPATAN);

        jMenuBar1.add(jMenu4);

        menegUser.setBackground(new java.awt.Color(51, 51, 51));
        menegUser.setForeground(new java.awt.Color(0, 255, 0));
        menegUser.setText("Admin");
        menegUser.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        manageUser.setBackground(new java.awt.Color(51, 51, 51));
        manageUser.setForeground(new java.awt.Color(0, 255, 0));
        manageUser.setText("Management User");
        manageUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUserActionPerformed(evt);
            }
        });
        menegUser.add(manageUser);

        ChangePasswors.setBackground(new java.awt.Color(51, 51, 51));
        ChangePasswors.setForeground(new java.awt.Color(0, 255, 0));
        ChangePasswors.setText("Ubah");
        ChangePasswors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePassworsActionPerformed(evt);
            }
        });
        menegUser.add(ChangePasswors);

        jMenuBar1.add(menegUser);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //note cek juga buttn login nya
    public void itemTerpilih(){
        login fDB = new login();
        fDB.asal = this;
        System.out.println(kondisiLogin);
        prosesCekLogin();
        btLogin.setText("Logout");
       
    }
    //-------------------------------------------------------------------------------
    private void disewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disewaActionPerformed
        // TODO add your handling code here:
        Barangkeluar keluar=new Barangkeluar();
        jDesktopPane1.add(keluar);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=keluar.getSize();
        keluar.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        keluar.setVisible(true);
    }//GEN-LAST:event_disewaActionPerformed

    private void btTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTransaksiActionPerformed
        // TODO add your handling code here:
        Transaksi transaksi=new Transaksi();
        jDesktopPane1.add(transaksi);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=transaksi.getSize();
        transaksi.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        transaksi.setVisible(true);
    }//GEN-LAST:event_btTransaksiActionPerformed

    private void btPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPetugasActionPerformed
        // TODO add your handling code here:
        Petugas petugas =new Petugas();
        jDesktopPane1.add(petugas);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize= petugas.getSize();
        petugas.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        petugas.setVisible(true);
    }//GEN-LAST:event_btPetugasActionPerformed

    private void btPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPenyewaActionPerformed
        // TODO add your handling code here:
        Datapenyewa penyewa=new Datapenyewa();
        jDesktopPane1.add(penyewa);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=penyewa.getSize();
        penyewa.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        penyewa.setVisible(true);
    }//GEN-LAST:event_btPenyewaActionPerformed

    private void btdataPeralatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdataPeralatanActionPerformed
        // TODO add your handling code here:
        Dataperalatan alat=new Dataperalatan();
        jDesktopPane1.add(alat);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=alat.getSize();
        alat.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        alat.setVisible(true);
    }//GEN-LAST:event_btdataPeralatanActionPerformed

    private void btTransaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btTransaksiMouseEntered
        // TODO add your handling code here:
        btTransaksi.setToolTipText("Transaksi");
    }//GEN-LAST:event_btTransaksiMouseEntered

    private void btPetugasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPetugasMouseEntered
        // TODO add your handling code here:
        btPetugas.setToolTipText("Admin");
    }//GEN-LAST:event_btPetugasMouseEntered

    private void btPenyewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPenyewaMouseEntered
        // TODO add your handling code here:
        btPenyewa.setToolTipText("Penyewa");
    }//GEN-LAST:event_btPenyewaMouseEntered

    private void btdataPeralatanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btdataPeralatanMouseEntered
        // TODO add your handling code here:
        btdataPeralatan.setToolTipText("Peralatan");
    }//GEN-LAST:event_btdataPeralatanMouseEntered

    private void btBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangKeluarActionPerformed
        // TODO add your handling code here:
         Barangkeluar keluar=new Barangkeluar();
        jDesktopPane1.add(keluar);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=keluar.getSize();
        keluar.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        keluar.setVisible(true);
    }//GEN-LAST:event_btBarangKeluarActionPerformed

    private void btBarangKeluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBarangKeluarMouseEntered
        // TODO add your handling code here:
        btBarangKeluar.setToolTipText("Barang Keluar");
    }//GEN-LAST:event_btBarangKeluarMouseEntered

    private void SewaAlatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SewaAlatActionPerformed
        // TODO add your handling code here:
        Transaksi transaksi=new Transaksi();
        jDesktopPane1.add(transaksi);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=transaksi.getSize();
        transaksi.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        transaksi.setVisible(true);
    }//GEN-LAST:event_SewaAlatActionPerformed

    private void dataPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataPenyewaActionPerformed
        // TODO add your handling code here:
        Datapenyewa penyewa =new Datapenyewa();
        jDesktopPane1.add(penyewa);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=penyewa.getSize();
        penyewa.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        penyewa.setVisible(true);

    }//GEN-LAST:event_dataPenyewaActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        Dataperalatan peralatan =new Dataperalatan();
        jDesktopPane1.add(peralatan);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=peralatan.getSize();
        peralatan.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        peralatan.setVisible(true);

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void LapPendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapPendapatanActionPerformed
        // TODO add your handling code here:
        lapPendapatanSewa sewa=new lapPendapatanSewa();
        jDesktopPane1.add(sewa);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=sewa.getSize();
        sewa.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        sewa.setVisible(true);
    }//GEN-LAST:event_LapPendapatanActionPerformed

    private void LapPendapatanDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapPendapatanDendaActionPerformed
        // TODO add your handling code here:
         lapPendapatanDenda denda=new lapPendapatanDenda();
        jDesktopPane1.add(denda);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=denda.getSize();
        denda.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        denda.setVisible(true);
    }//GEN-LAST:event_LapPendapatanDendaActionPerformed

    private void lapDataBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapDataBarangActionPerformed
        // TODO add your handling code here:
        JasperReport jasRep;
        JasperPrint jasPrint;
        Map<String, Object> Transaksi=new HashMap<String, Object>();
        JasperDesign jasDes;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/owncamping","root","");
            String report="./src/laporan/LapDataBarang.jrxml";
            jasDes = JRXmlLoader.load(report);
            jasRep=JasperCompileManager.compileReport(jasDes);
            jasPrint=JasperFillManager.fillReport(jasRep, Transaksi, koneksi);
            JasperViewer.viewReport(jasPrint,false);
            
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_lapDataBarangActionPerformed

    private void lapDataSewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapDataSewaActionPerformed
        // TODO add your handling code here:
        JasperReport jasRep;
        JasperPrint jasPrint;
        Map<String, Object> Transaksi=new HashMap<String, Object>();
        JasperDesign jasDes;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/owncamping","root","");
            String report="./src/laporan/LapDataSewa.jrxml";
            jasDes = JRXmlLoader.load(report);
            jasRep=JasperCompileManager.compileReport(jasDes);
            jasPrint=JasperFillManager.fillReport(jasRep, Transaksi, koneksi);
            JasperViewer.viewReport(jasPrint,false);
            
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_lapDataSewaActionPerformed

    private void ChangePassworsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePassworsActionPerformed
        // TODO add your handling code here:
        change change=new change();
        jDesktopPane1.add(change);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize=change.getSize();
        change.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        change.setVisible(true);
    }//GEN-LAST:event_ChangePassworsActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        // TODO add your handling code here:
        if(btLogin.getText()=="Logout"){
            belumLogin();
            btLogin.setText("Login");
        }else{
            login fDB = new login();
            fDB.asal = this;
            fDB.setVisible(true);
            fDB.setResizable(false);
        }
        
    }//GEN-LAST:event_btLoginActionPerformed

    private void manageUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUserActionPerformed
        // TODO add your handling code here:
        Petugas petugas =new Petugas();
        jDesktopPane1.add(petugas);
        Dimension parentSize=jDesktopPane1.getSize();
        Dimension childSize= petugas.getSize();
        petugas.setLocation((parentSize.width - childSize.width)/2, (parentSize.height - childSize.height)/2);
        petugas.setVisible(true);
    }//GEN-LAST:event_manageUserActionPerformed

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
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUtama().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ChangePasswors;
    private javax.swing.JMenu Disewa;
    private javax.swing.JMenuItem LapPendapatan;
    private javax.swing.JMenuItem LapPendapatanDenda;
    private javax.swing.JMenu PENDAPATAN;
    private javax.swing.JMenuItem SewaAlat;
    private javax.swing.JButton btBarangKeluar;
    private javax.swing.JButton btLogin;
    private javax.swing.JButton btPenyewa;
    private javax.swing.JButton btPetugas;
    private javax.swing.JButton btTransaksi;
    private javax.swing.JButton btdataPeralatan;
    private javax.swing.JMenuItem dataPenyewa;
    private javax.swing.JMenu dataPeralatan;
    private javax.swing.JMenuItem disewa;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JMenuItem lapDataBarang;
    private javax.swing.JMenuItem lapDataSewa;
    private javax.swing.JMenuItem manageUser;
    private javax.swing.JMenu menegUser;
    // End of variables declaration//GEN-END:variables
}
