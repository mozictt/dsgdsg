/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package own_camping;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import konfig.koneksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fatma Wati
 */
public class Transaksi extends javax.swing.JInternalFrame {

    /**
     * Creates new form Transaksi
     */
    int nilai,cek,n;
    int subtotalNew=0;
    
    String jml_peralatan;
    public String id_peralatan,nm_peralatan;
    String id_penyewa,nm_penyewa;
    public Transaksi() {
        initComponents();
        TampilTabel();
        OtomatisTransaksi();
        txtnm_penyewa.setEnabled(false);
        txtid_pinjam.setEnabled(false);
        
    }
    //non aktifkan 
    public void nonAktif(){
        txtid_penyewa.setEnabled(false);
        txtnm_penyewa.setEnabled(false);
        
    }
    //mengaktifkan
    public void aktif(){
        txtid_penyewa.setEnabled(true);
    }
    
    //kosongkan data
    public void kosongkan(){
        txtid_peralatan.setText(null);
        txtjml.setText(null);
        txtnm_peralatan.setText(null);
    }
    
    ///Otomatis kode transaksi
    public void OtomatisTransaksi(){
        try {
            Statement statment=(Statement)koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("SELECT max(right(id_transaksi_sewa,5))as no from transaksi_sewa");
            while(res.next())
            {
                if(res.first()== false){
                    txtid_pinjam.setText("PJ-000001");
                }
                else
                {
                    res.last();
                    int auto_id=res.getInt(1)+1;
                    String no=String.valueOf(auto_id);
                    int nolong=no.length();
                    //mengatur jumlah 0
                    for(int a=0;a<6-nolong;a++){
                        no="0"+no;
                    }
                    txtid_pinjam.setText("PJ-" + no);
                }
            }
            res.close();
            statment.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Transaksi Eror");
        }
    }
    
    
    //kode penyewa
    public void penyewa(){
        String nm_penyewa=null;
        String id_penyewa=txtid_penyewa.getText();
        nilai=0;
        try {
            Statement statment=(Statement)koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("SELECT * from penyewa where id_penyewa='"+id_penyewa+"'");
            while(res.next()){
                nilai= res.getRow();
               nm_penyewa=res.getString("nm_penyewa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Id Penyewa Tidak ditemikan");
        }
        if(nilai==0){
            JOptionPane.showMessageDialog(this, "ID Penyewa Tidak Ditemukan");
            txtnm_penyewa.setText(null);
        }else{
            txtnm_penyewa.setText(nm_penyewa);
        }
    }
    public void Peralatan(){
        String nm_peralatan=null;
        String harga=null;
        String id_peralatan=txtid_peralatan.getText();
        nilai=0;
        try {
            Statement statment=(Statement)koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("SELECT * from peralatan where id_peralatan='"+id_peralatan+"'");
            while(res.next()){
                nilai= res.getRow();
               nm_peralatan=res.getString("nm_peralatan");
               harga=res.getString("hrg_sewa");
               jml_peralatan=res.getString("jml");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Id Peralatan kosong");
            e.printStackTrace();
        }
        if(nilai==0){
            JOptionPane.showMessageDialog(this, "ID Peralatan Tidak Ditemukan");
            txtnm_penyewa.setText(null);
        }else{
            txtnm_peralatan.setText(nm_peralatan);
            txtharga.setText(harga);
        }
    }
     public void tangal(){
        Date date = new Date();
        jctgl_pinjam.setDate(date);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        int tambah=7;
        Date tgl=jctgl_pinjam.getDate();
        Calendar calTambah= Calendar.getInstance();
        calTambah.setTime(tgl);
        calTambah.add(Calendar.DAY_OF_MONTH, tambah);
        jctgl_kembali.setDate(calTambah.getTime());
        
    }
    //------------------------------------------------------------------------
    //tampil tabel
    public void TampilTabel(){
        String id_sewa=txtid_pinjam.getText();
       
        DefaultTableModel tbl=new DefaultTableModel();
        tbl.addColumn("ID Pinjam");
        tbl.addColumn("ID Penyewa");
        tbl.addColumn("ID Peralatan");
        tbl.addColumn("Penyewa");
        tbl.addColumn("Peralatan");
        tbl.addColumn("Tgl Sewa");
        tbl.addColumn("Bts Sewa");
        tbl.addColumn("Jml");
        tbl.addColumn("Hrg");
        tbl.addColumn("Total");
        tabel_pinjam.setModel(tbl);
        
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("select * from detail_transaksi_sewa join penyewa on detail_transaksi_sewa.id_penyewa=penyewa.id_penyewa join peralatan on detail_transaksi_sewa.id_peralatan=peralatan.id_peralatan where id_transaksi_sewa='"+id_sewa+"'");
            while(res.next()){
                subtotalNew=subtotalNew + n;
                tbl.addRow(new Object[]{
                    res.getString("id_transaksi_sewa"),
                    res.getString("id_penyewa"),
                    res.getString("id_peralatan"),
                    res.getString("nm_penyewa"),
                    res.getString("nm_peralatan"),
                    res.getString("tgl_sewa"),
                    res.getString("bts_sewa"),
                    res.getString("jml_peralatan"),
                    res.getString("hrg_sewa"),
                    res.getString("total_hrg_sewa"),
                });
                subtotalNew=subtotalNew + Integer.parseInt(res.getString("total_hrg_sewa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tabel Bermasalah");
        }
    }
    public void Tambah_Data(){
        String id_pinjam=txtid_pinjam.getText();
        String id_penyewa=txtid_penyewa.getText();
        String nm_penyewa=txtnm_penyewa.getText();
        String id_peralatan=txtid_peralatan.getText();
        String nm_peralatan=txtnm_peralatan.getText();
        String jml=txtjml.getText();
        java.util.Date tgl_pinjam=this.jctgl_pinjam.getDate();
        java.util.Date bts_kembali=this.jctgl_kembali.getDate();
        String harga=txtharga.getText();
        String total=jTotal.getText();
        if(jTotal.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Total harga tidak boleh kosong");
        }else if(txtnm_peralatan.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Nama  peralatan tidak boleh kosong");
        }else{
            try {
            Statement statement=koneksi.GetconConnection().createStatement();
            ResultSet res=statement.executeQuery("select * from detail_transaksi_sewa where id_transaksi_sewa='"+id_pinjam+"' and id_peralatan='"+id_peralatan+"' and id_penyewa='"+id_penyewa+"';");
            if(res.next()){
                 try {
                    Statement statement2=koneksi.GetconConnection().createStatement();
                    statement2.execute("UPDATE detail_transaksi_sewa set jml_peralatan=jml_peralatan + '"+jml+"',total_hrg_sewa=total_hrg_sewa + '"+total+"' where id_transaksi_sewa='"+id_pinjam+"' and id_penyewa='"+id_penyewa+"'and id_peralatan='"+id_peralatan+"'");
                    statement2.execute("UPDATE tmp_transaksi_sewa set jml_peralatan=jml_peralatan + '"+jml+"' where id_transaksi_sewa='"+id_pinjam+"' and id_penyewa='"+id_penyewa+"' and id_peralatan='"+id_peralatan+"'");
                    statement2.close();
                    this.UpdatePeralatan(id_peralatan, jml);
                    JOptionPane.showMessageDialog(rootPane,"Berhasil Update Peralatan");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane,"Gagal Update Peralatan");
                        e.printStackTrace();
                    }
            }else{
                try {
                    Statement statment=(Statement)koneksi.GetconConnection().createStatement();
                    Statement statment2=(Statement)koneksi.GetconConnection().createStatement();
                    statment.executeUpdate("insert into detail_transaksi_sewa values('"+id_pinjam+"','"+id_penyewa+"','"+id_peralatan+"','"+new java.sql.Date(tgl_pinjam.getTime())+"','"+new java.sql.Date(bts_kembali.getTime())+"','"+jml+"','"+harga+"','"+total+"');");
                    statment2.executeUpdate("insert into tmp_transaksi_sewa values('"+id_pinjam+"','"+id_peralatan+"','"+id_penyewa+"','"+new java.sql.Date(tgl_pinjam.getTime())+"','"+new java.sql.Date(bts_kembali.getTime())+"','"+jml+"');");
                    JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
                    this.UpdatePeralatan(id_peralatan, jml);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Data Gagal Disimpan");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cek=cek+Integer.parseInt(total);
        subtotal.setText(Integer.toString(cek));
        }
        
    }
    
    public void transaksi(){
         String id_pinjam=txtid_pinjam.getText();
        String id_penyewa=txtid_penyewa.getText();
        String nm_penyewa=txtnm_penyewa.getText();
        String id_peralatan1=txtid_peralatan.getText();
        String nm_peralatan=txtnm_peralatan.getText();
        String jml=txtjml.getText();
        java.util.Date tgl_pinjam=this.jctgl_pinjam.getDate();
        java.util.Date bts_kembali=this.jctgl_kembali.getDate();
        String harga=txtharga.getText();
        String total=jTotal.getText();
        try {
            Statement stat=koneksi.GetconConnection().createStatement();
            ResultSet rz=stat.executeQuery("Select * from transaksi_sewa where id_transaksi_sewa='"+txtid_pinjam.getText()+"'");
            if(rz.next()){
                Statement stat2=koneksi.GetconConnection().createStatement();
                stat2.execute("UPDATE transaksi_sewa set id_penyewa='"+txtid_penyewa.getText()+"' where id_transaksi_sewa='"+txtid_pinjam.getText()+"'");
            }else{
                try {
                    Statement statment=koneksi.GetconConnection().createStatement();
                    //id_peralatan di kosongkan 
                    statment.executeUpdate("insert into transaksi_sewa values('"+id_pinjam+"','"+id_peralatan1+"','"+id_penyewa+"','"+new java.sql.Date(tgl_pinjam.getTime())+"','"+new java.sql.Date(bts_kembali.getTime())+"');");
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, e);
                    }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        
    }
    
    //update peralatan
    public void UpdatePeralatan(String id_peralatan, String jml_peralatan){
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            statment.execute("UPDATE peralatan set jml=jml-'"+jml_peralatan+"' where id_peralatan='"+id_peralatan+"'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Peralatan Eror");
            e.printStackTrace();
        }
    }
    //cencel transaksi
    public void UpdatePeralatan2(String id_peralatan, String jml_peralatan){
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            statment.execute("UPDATE peralatan set jml=jml +'"+jml_peralatan+"' where id_peralatan='"+id_peralatan+"'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Peralatan Eror");
            e.printStackTrace();
        }
    }
    
    //Hapus Data tabel untuk menambah data barang
    public void cekData(){
        String id_pinjam=txtid_pinjam.getText();
        String id_peralatan,jml_peralatan;
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("Select * from detail_transaksi_sewa where id_transaksi_sewa='"+id_pinjam+"'");
            while(res.next()){
                id_peralatan=res.getString("id_peralatan");
                jml_peralatan=res.getString("jml_peralatan");
                 this.UpdatePeralatan2(id_peralatan, jml_peralatan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //memunculkan penyewa pada saat cek
    public void cekPenyewa(){
        try {
            Statement statment =koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("Select * from detail_transaksi_sewa join penyewa on detail_transaksi_sewa.id_penyewa = penyewa.id_penyewa where id_transaksi_sewa='"+txtid_pinjam.getText()+"'");
            while(res.next()){
                id_penyewa=res.getString("id_penyewa");
                nm_penyewa=res.getString("nm_penyewa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Transaksi Kosong");
            e.printStackTrace();
        }
        nonAktif();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pinjam = new javax.swing.JTable();
        txtid_pinjam = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtid_penyewa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnm_penyewa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtid_peralatan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnm_peralatan = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtjml = new javax.swing.JTextField();
        jctgl_pinjam = new com.toedter.calendar.JDateChooser();
        jctgl_kembali = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtbayar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        subtotal = new javax.swing.JLabel();
        kembali = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTotal = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        btcek = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("ID pinjam");

        tabel_pinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabel_pinjam);

        txtid_pinjam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtid_pinjamKeyPressed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("ID penyewa");

        txtid_penyewa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtid_penyewaKeyPressed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Nama penyewa");

        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Kode Peralatan");

        txtid_peralatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtid_peralatanKeyPressed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Peralatan");

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel6.setFont(new java.awt.Font("Cooper Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Transaksi Sewa Alat Camping");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Tanggal sewa");

        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Batas waktu");

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setForeground(new java.awt.Color(255, 255, 0));
        jButton2.setText("Keluar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("Jumlah");

        txtjml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtjmlKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel10.setText("Sub Total");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel11.setText("Bayar");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel12.setText("Kembalian");

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 0));
        jButton1.setText("Nota");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtbayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });
        txtbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbayarKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel14.setText("Rp");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel15.setText("Rp");

        subtotal.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        kembali.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        kembali.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbayar)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kembali, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kembali, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jLabel13.setForeground(new java.awt.Color(255, 255, 0));
        jLabel13.setText("Harga");

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setForeground(new java.awt.Color(255, 255, 0));
        jButton3.setText("Tambah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Total  :");

        jTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Rp.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)))
                    .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton4.setText("...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btcek.setText("cek");
        btcek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcekActionPerformed(evt);
            }
        });

        jButton5.setText("Skip");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnm_penyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtid_pinjam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                    .addComponent(txtid_penyewa, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btcek, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtjml, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnm_peralatan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtid_peralatan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jctgl_pinjam, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jctgl_kembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jctgl_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtid_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(txtid_peralatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(jButton4)
                                .addComponent(btcek)))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtid_penyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtnm_peralatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtnm_penyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtjml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jctgl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton5)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void itemTerpilih(){
        SourcePeralatan fDB = new SourcePeralatan();
        fDB.fAB = this;
        txtid_peralatan.setText(id_peralatan);
        txtnm_peralatan.setText(nm_peralatan);
        nonAktif();
        
        //menampilkan data harga sewa peralatan
        Peralatan();
        
       // txtnama.setText(namaBrg);
        //txtharga.setText(hargaBrg);
    }
    //-------------------------------------------------------------------------------
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String id_pinjam=txtid_pinjam.getText();
        int total=tabel_pinjam.getRowCount();
        /*int total=tabel_pinjam.getRowCount();
        System.out.println(total);
        if(total>0){
            JOptionPane.showMessageDialog(this, "Masih ada data tang belum disimpan");
        }else{
        dispose();
        }*/
        String[] opsi={"Ya","Tidak"};
        int PILIH =JOptionPane.showOptionDialog(null,"Semua Data Transaksi Akan Dihapus?","Pertanyaan",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opsi,opsi[0]);
        if(PILIH==JOptionPane.YES_OPTION)
            if(total>0){
                cekData();
                try {
                    Statement statment=koneksi.GetconConnection().createStatement();
                    Statement statment2=koneksi.GetconConnection().createStatement();
                    Statement statment3=koneksi.GetconConnection().createStatement();
                    statment.execute("delete from transaksi_sewa where id_transaksi_sewa='"+id_pinjam+"'");
                    statment.execute("delete from detail_transaksi_sewa where id_transaksi_sewa='"+id_pinjam+"'");
                    statment.execute("delete from tmp_transaksi_sewa where id_transaksi_sewa='"+id_pinjam+"'");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dispose();
            }else{
                dispose();
            }
       else if(PILIH == JOptionPane.NO_OPTION)
            dispose();
        aktif();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtid_penyewaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid_penyewaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            penyewa();
        }
    }//GEN-LAST:event_txtid_penyewaKeyPressed

    private void txtid_peralatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid_peralatanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Peralatan();
        }
    }//GEN-LAST:event_txtid_peralatanKeyPressed

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbayarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Tambah_Data();
        TampilTabel();
        kosongkan();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtjmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjmlKeyPressed
        // TODO add your handling code here:
        
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int total=Integer.parseInt(txtjml.getText())*Integer.parseInt(txtharga.getText());
            jTotal.setText(Integer.toString(total));
        }
         tangal();
    }//GEN-LAST:event_txtjmlKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        transaksi(); 
        JasperReport jasRep;
        JasperPrint jasPrint;
        Map<String, Object> Transaksi=new HashMap<String, Object>();
        JasperDesign jasDes;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi=DriverManager.getConnection("jdbc:mysql://localhost/owncamping","root","");
            String report="./src/laporan/cetaknotasewa.jrxml";
            jasDes=JRXmlLoader.load(report);
            Transaksi.put("id_transaksi_sewa",txtid_pinjam.getText());
            Transaksi.put("subtotal",subtotal.getText());
            Transaksi.put("bayar",txtbayar.getText());
            Transaksi.put("kembalian",kembali.getText());
            jasRep=JasperCompileManager.compileReport(jasDes);
            jasPrint=JasperFillManager.fillReport(jasRep, Transaksi, koneksi);
            JasperViewer.viewReport(jasPrint,false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        OtomatisTransaksi();
        TampilTabel();
        kosongkan();
        txtid_penyewa.setText(null);
        txtnm_penyewa.setText(null);
        aktif();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyReleased
            String jumlah=subtotal.getText();       
            String bayar=txtbayar.getText();
            double jumlahd=Double.parseDouble(jumlah);
            double bayard=Double.parseDouble(bayar);
            DecimalFormat df = new DecimalFormat("#.##");
            kembali.setText(df.format(bayard - jumlahd));
    }//GEN-LAST:event_txtbayarKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        SourcePeralatan fDB = new SourcePeralatan();
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btcekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcekActionPerformed
        // TODO add your handling code here:
        txtid_pinjam.setEnabled(true);
    }//GEN-LAST:event_btcekActionPerformed

    private void txtid_pinjamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid_pinjamKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TampilTabel();
            txtid_pinjam.setEnabled(false);
            cekPenyewa();
            txtnm_penyewa.setText(nm_penyewa);
            txtid_penyewa.setText(id_penyewa);
            subtotal.setText(Integer.toString(subtotalNew));
            System.out.println(subtotalNew);
        }
    }//GEN-LAST:event_txtid_pinjamKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        OtomatisTransaksi();
        kosongkan();
        aktif();
        TampilTabel();
        txtid_penyewa.setText(null);
        txtnm_penyewa.setText(null);
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcek;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTotal;
    private com.toedter.calendar.JDateChooser jctgl_kembali;
    private com.toedter.calendar.JDateChooser jctgl_pinjam;
    private javax.swing.JLabel kembali;
    private javax.swing.JLabel subtotal;
    private javax.swing.JTable tabel_pinjam;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid_penyewa;
    private javax.swing.JTextField txtid_peralatan;
    private javax.swing.JTextField txtid_pinjam;
    private javax.swing.JTextField txtjml;
    private javax.swing.JTextField txtnm_penyewa;
    private javax.swing.JTextField txtnm_peralatan;
    // End of variables declaration//GEN-END:variables
}
