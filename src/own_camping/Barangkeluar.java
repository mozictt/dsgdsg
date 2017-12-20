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
import java.text.SimpleDateFormat;
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
public class Barangkeluar extends javax.swing.JInternalFrame {

    /**
     * Creates new form Barangkeluar
     */
    public static String id_pinjam;
    public static String id_peralatan;
    public static String id_penyewa;
    public static String penyewa;
    public static String peralatan;
    public static String jml_peralatan;
    public static String bts_sewa;
    
    int subtotll=0;
    public Barangkeluar() {
        initComponents();
        OtomatisNotrans();
        TabelPengembalian();
        TabelPinjaman();
    }
    //mengosongkan jtextfile
    public void kosongkan(){
        txtDenda.setText(null);
        txtJml.setText(null);
        txtPenyewa.setText(null);
        txtPeralatan.setText(null);
        txtTelat.setText(null);
        txtTotalDenda.setText(null);
        txtidPenyewa.setText(null);
        txtidPeralatan.setText(null);
        alter.setText(null);
    }
     public void  OtomatisNotrans(){
        try {
            Statement statement=(Statement)koneksi.GetconConnection().createStatement();
            ResultSet res=statement.executeQuery("Select max(right(id_transaksi_pengembalian,5))as no from transaksi_pengembalian");
            while(res.next())
            {
                if(res.first() == false)
                {
                txtidPengembalian.setText("TP.000001");
       
                }
                else
                   {
                   res.last();
                   int auto_id = res.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                   //MENGATUR jumlah 0
                    for(int a=0;a<6-noLong;a++)
                        { 
                            no = "0" + no;
                        }
                   txtidPengembalian.setText("TP." + no);
                
                 }        
            }
       res.close();
       statement.close();
       }catch (Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);

        }
    }
    //----------------------------------------------------------------------
    public void tampilDataSewa(){
        String id_penyewa=txtcek.getText();
        DefaultTableModel tbl=new DefaultTableModel();
        tbl.addColumn("ID Sewa");
        tbl.addColumn("ID Penyewa");
        tbl.addColumn("ID Peralatan");
        tbl.addColumn("Penyewa");
        tbl.addColumn("Peralatan");
        tbl.addColumn("Tgl Sewa");
        tbl.addColumn("Bts Sewa");
        tbl.addColumn("Jml");
        tabel_cek_peralatan.setModel(tbl);
        try {
            Statement statment=(Statement)koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("select* from tmp_transaksi_sewa join penyewa on tmp_transaksi_sewa.id_penyewa=penyewa.id_penyewa join peralatan on tmp_transaksi_sewa.id_peralatan=peralatan.id_peralatan where tmp_transaksi_sewa.id_penyewa='"+id_penyewa+"'");
            while(res.next()){
                tbl.addRow(new Object[]{
                    res.getString("id_transaksi_sewa"),
                    res.getString("id_penyewa"),
                    res.getString("id_peralatan"),
                    res.getString("nm_penyewa"),
                    res.getString("nm_peralatan"),
                    res.getString("tgl_sewa"),
                    res.getString("bts_sewa"),
                    res.getString("jml_peralatan"),
                });
                tabel_cek_peralatan.setModel(tbl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Eror");
            e.printStackTrace();
        }
    }
    //menampilkan tanggal saatini
    public void tangal(){
       // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        Date date = new Date();
        Tgl_Kembali.setDate(date);
    }
    //------------------------------------------------------------------------
    
    //menghitung selisih tanggal
    public void selisihTanggal(){
        try {
            Date TanggalPinjam = bts_kembali.getDate();
            SimpleDateFormat date = new SimpleDateFormat("Y-MM-dd");
            //Date TglPinjam = (Date) date.parse(TanggalPinjam);
            Date TanggalKembali = Tgl_Kembali.getDate();
            //Date TglKembali = (Date) date.parse(TanggalKembali);
            if(TanggalPinjam.getTime()>TanggalKembali.getTime()){
                txtTelat.setText("0");
            }else{
            long Telat =Math.abs(TanggalKembali.getTime()-TanggalPinjam.getTime());
            long Lama = Telat / (24 * 60 * 60 * 1000);
            txtTelat.setText(Long.toString(Lama));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //fungsi tambah data
    public void TambahData(){
        String id_pengembalian=txtidPengembalian.getText();
        String id_pinjam=txtidPinjam.getText();
        String id_penyewa=txtidPenyewa.getText();
        String id_peralatan=txtidPeralatan.getText();
        String penyewa=txtPenyewa.getText();
        String peralatan=txtPeralatan.getText();
        String jml=txtJml.getText();
        String jml_hari=txtTelat.getText();
        String denda_perhari=txtDenda.getText();
        String TotalDenda=txtTotalDenda.getText();
        java.util.Date tgl_kembali=this.Tgl_Kembali.getDate();
        java.util.Date bts_kembali=this.bts_kembali.getDate();
        try {
            Statement statment1=koneksi.GetconConnection().createStatement();
            ResultSet rs=statment1.executeQuery("select * from tmp_transaksi_sewa where id_transaksi_sewa='"+id_pinjam+"' and id_peralatan='"+id_peralatan+"' and jml_peralatan='"+jml+"'");
            if(rs.next()){
                                    try {
                                        Statement statement5=(Statement)koneksi.GetconConnection().createStatement();
                                        statement5.execute("delete from tmp_transaksi_sewa where id_transaksi_sewa='"+id_pinjam+"'and id_peralatan='"+id_peralatan+"'");
                                        JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus");
                                        statement5.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                        }else{
                                try {
                                    Statement statement2=(Statement)koneksi.GetconConnection().createStatement();
                                    statement2.execute("UPDATE tmp_transaksi_sewa Set jml_peralatan=jml_peralatan - '"+jml+"' where id_transaksi_sewa='"+id_pinjam+"' and id_peralatan='"+id_peralatan+"'");
                                    statement2.close();

                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(rootPane,"Gagal Update Barang");
                                        e.printStackTrace();
                                    }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        
        //input data ke dalam tabel pengembalian barang
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            statment.execute("insert into transaksi_pengembalian values('"+id_pengembalian+"','"+id_pinjam+"','"+id_penyewa+"','"+id_peralatan+"','"+new java.sql.Date(bts_kembali.getTime())+"','"+new java.sql.Date(tgl_kembali.getTime())+"','"+jml_hari+"','"+jml+"','"+denda_perhari+"','"+TotalDenda+"','"+cbStatus.getSelectedItem()+"')");
            JOptionPane.showMessageDialog(this, "Data Pengembalian Berhasil disimpan");
            this.UpdatePeralatan(id_peralatan, jml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DendaPeralatan();
        this.hitung(TotalDenda);
        kosongkan();
        
    }
    
     //menampilkan data pengembalian peralatan camping
    public void TabelPengembalian(){
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("SELECT * from transaksi_pengembalian join penyewa on transaksi_pengembalian.id_penyewa=penyewa.id_penyewa join peralatan on transaksi_pengembalian.id_peralatan=peralatan.id_peralatan where id_transaksi_pengembalian='"+txtidPengembalian.getText()+"'");
            DefaultTableModel tbl=new DefaultTableModel();
            tbl.addColumn("ID Pengembalian");
            tbl.addColumn("ID Sewa");
            tbl.addColumn("ID Penyewa");
            tbl.addColumn("ID Peralatan");
            tbl.addColumn("Nama");
            tbl.addColumn("Peralatan");
            tbl.addColumn("BTS Sewa");
            tbl.addColumn("pengembalian");
            tbl.addColumn("JML");
            tbl.addColumn("Telat");
            tbl.addColumn("Denda");
            tbl.addColumn("Kondisi");
            tabel_pengembalian.setModel(tbl);
            
            while(res.next()){
                tbl.addRow(new Object[]{
                    res.getString("id_transaksi_pengembalian"),
                    res.getString("id_transaksi_sewa"),
                    res.getString("id_penyewa"),
                    res.getString("id_peralatan"),
                    res.getString("nm_penyewa"),
                    res.getString("nm_peralatan"),
                    res.getString("bts_sewa"),
                    res.getString("tgl_kembalii"),
                    res.getString("jml_peralatan"),
                    res.getString("jml_hari"),
                    res.getString("denda"),
                    res.getString("status")
                    
                    
                });
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //menampilkan data peminjam peralatan camping
    public void TabelPinjaman(){
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            ResultSet res=statment.executeQuery("SELECT * from tmp_transaksi_sewa join penyewa on tmp_transaksi_sewa.id_penyewa=penyewa.id_penyewa join peralatan on tmp_transaksi_sewa.id_peralatan=peralatan.id_peralatan where penyewa.id_penyewa='"+txtcek.getText()+"'");
            DefaultTableModel tbl=new DefaultTableModel();
            tbl.addColumn("ID Sewa");
            tbl.addColumn("ID Penyewa");
            tbl.addColumn("ID Peralatan");
            tbl.addColumn("Nama");
            tbl.addColumn("Peralatan");
            tbl.addColumn("TGL Sewa");
            tbl.addColumn("BTS Sewa");
            tbl.addColumn("JML");
            tabel_cek_peralatan.setModel(tbl);
            
            while(res.next()){
                tbl.addRow(new Object[]{
                    res.getString("id_transaksi_sewa"),
                    res.getString("id_penyewa"),
                    res.getString("id_peralatan"),
                    res.getString("nm_penyewa"),
                    res.getString("nm_peralatan"),
                    res.getString("tgl_sewa"),
                    res.getString("bts_sewa"),
                    res.getString("jml_peralatan"),                
                    
                });
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //update peralatan
    public void UpdatePeralatan(String id_peralatan, String jml_peralatan){
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            statment.execute("UPDATE peralatan set jml=jml+'"+jml_peralatan+"' where id_peralatan='"+id_peralatan+"'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Peralatan Eror");
            e.printStackTrace();
        }
    }
    //update peralatan minus
    public void UpdatePeralatan2(String id_peralatan, String jml_peralatan){
        try {
            Statement statment=koneksi.GetconConnection().createStatement();
            statment.execute("UPDATE peralatan set jml=jml-'"+jml_peralatan+"' where id_peralatan='"+id_peralatan+"'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Peralatan Eror");
            e.printStackTrace();
        }
    }    
    //cek status di combobox
    public void cekCombobox(){
         if(cbStatus.getSelectedItem()=="Hilang"){
            txtDenda.setEditable(false);
            try {
                Statement statment=koneksi.GetconConnection().createStatement();
                ResultSet rs=statment.executeQuery("select * from peralatan where id_peralatan='"+txtidPeralatan.getText()+"'");
                while(rs.next()){
                    int cek=Integer.parseInt(rs.getString("hrg_beli"))* Integer.parseInt(txtJml.getText());
                    txtTotalDenda.setText(Integer.toString(cek));
                }
                txtDenda.setText("0");
            } catch (Exception e) {
                e.printStackTrace();
            }   
        }else if(cbStatus.getSelectedItem()=="Rusak"){
            txtDenda.setEditable(false);
            try {
                Statement statment=koneksi.GetconConnection().createStatement();
                ResultSet rs=statment.executeQuery("select * from peralatan where id_peralatan='"+txtidPeralatan.getText()+"'");
                while(rs.next()){
                    int cek=(Integer.parseInt(rs.getString("hrg_beli"))* Integer.parseInt(txtJml.getText()))/2;
                    txtTotalDenda.setText(Integer.toString(cek));
                }
                txtDenda.setText("0");
            } catch (Exception e) {
                e.printStackTrace();
            }   
        }else{
            txtDenda.setEditable(true);
            if(Integer.parseInt(txtTelat.getText())>0){
                alter.setText("isi denda");
                txtDenda.setText(null);
                txtTotalDenda.setText("0");
            }else{
                txtDenda.setText("0");
                txtTotalDenda.setText("0");                
            }
            
            
        }
    }
    //--------------------------------------------------------------------------------------------------------------------
    
    //insert denda barang jika terlambat, rusak,hilang
    
    public void DendaPeralatan(){
        String id_pengembalian=txtidPengembalian.getText();
        String id_pinjam=txtidPinjam.getText();
        String id_penyewa=txtidPenyewa.getText();
        String id_peralatan=txtidPeralatan.getText();
        String penyewa=txtPenyewa.getText();
        String peralatan=txtPeralatan.getText();
        String jml=txtJml.getText();
        String jml_hari=txtTelat.getText();
        String TotalDenda=txtTotalDenda.getText();
        java.util.Date tgl_kembali=this.Tgl_Kembali.getDate();
        java.util.Date bts_kembali=this.bts_kembali.getDate();
        int cekDenda=Integer.parseInt(txtTotalDenda.getText());
        if(cekDenda>0){ //menyimpan blia ada denda
            try {
                Statement statment=koneksi.GetconConnection().createStatement();
                statment.executeUpdate("Insert into denda (id_transaksi_pengembalian,id_penyewa,id_peralatan,tgl_kembalii,jml_peralatan,denda,status) values ('"+id_pengembalian+"','"+id_penyewa+"','"+id_peralatan+"','"+new java.sql.Date(tgl_kembali.getTime())+"','"+jml+"','"+TotalDenda+"','"+cbStatus.getSelectedItem()+"')");
                if(cbStatus.getSelectedItem()=="Hilang"){
                    this.UpdatePeralatan2(id_peralatan, jml);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //menghapus data denda
    public void updateCekDebda(String cekdenda, String id_transaksi_pengembalian, String status,String id_peralatan,String jml_peralatan){
        if(Integer.parseInt(cekdenda)>0){ //menghapus blia ada denda
            try {
                Statement statment=koneksi.GetconConnection().createStatement();
                statment.executeUpdate("DELETE from denda where id_transaksi_pengembalian='"+id_transaksi_pengembalian+"' and status='"+status+"'");
                if(status.equals("Hilang")){
                    this.UpdatePeralatan(id_peralatan, jml_peralatan);
                    //System.out.println("run");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //hitung  (penambahan)
    public void hitung(String hit){
       // int total=Integer.parseInt(subtotal.getText());
        subtotll=subtotll+Integer.parseInt(hit);
        subtotal.setText(Integer.toString(subtotll));
    }
    //hitung denda (pengurangan)
    public void hitungMinus(String hit){
       // int total=Integer.parseInt(subtotal.getText());
        subtotll=subtotll-Integer.parseInt(hit);
        subtotal.setText(Integer.toString(subtotll));
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pengembalian = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtidPengembalian = new javax.swing.JTextField();
        txtidPinjam = new javax.swing.JTextField();
        txtidPenyewa = new javax.swing.JTextField();
        txtidPeralatan = new javax.swing.JTextField();
        txtPenyewa = new javax.swing.JTextField();
        txtPeralatan = new javax.swing.JTextField();
        txtJml = new javax.swing.JTextField();
        bts_kembali = new com.toedter.calendar.JDateChooser();
        Tgl_Kembali = new com.toedter.calendar.JDateChooser();
        txtTelat = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox<>();
        txtDenda = new javax.swing.JTextField();
        txtTotalDenda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtbayar = new javax.swing.JTextField();
        subtotal = new javax.swing.JLabel();
        kembalian = new javax.swing.JLabel();
        btTambah = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_cek_peralatan = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtcek = new javax.swing.JTextField();
        btcek = new javax.swing.JButton();
        alter = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 255));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Barang Keluar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabel_pengembalian.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_pengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pengembalianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pengembalian);

        jLabel2.setForeground(new java.awt.Color(204, 204, 0));
        jLabel2.setText("Id Pengembalian");

        jLabel3.setForeground(new java.awt.Color(204, 204, 0));
        jLabel3.setText("Id Pinjam");

        jLabel4.setForeground(new java.awt.Color(204, 204, 0));
        jLabel4.setText("Id Penyewa");

        jLabel5.setForeground(new java.awt.Color(204, 204, 0));
        jLabel5.setText("Id Peralatan");

        jLabel6.setForeground(new java.awt.Color(204, 204, 0));
        jLabel6.setText("Penyewa");

        jLabel7.setForeground(new java.awt.Color(204, 204, 0));
        jLabel7.setText("Peralatan");

        jLabel8.setForeground(new java.awt.Color(204, 204, 0));
        jLabel8.setText("Jumlah");

        jLabel9.setForeground(new java.awt.Color(204, 204, 0));
        jLabel9.setText("Batas Waktu");

        jLabel10.setForeground(new java.awt.Color(204, 204, 0));
        jLabel10.setText("Tanggal Kembali");

        jLabel11.setForeground(new java.awt.Color(204, 204, 0));
        jLabel11.setText("Telat");

        jLabel12.setForeground(new java.awt.Color(204, 204, 0));
        jLabel12.setText("Status");

        jLabel13.setForeground(new java.awt.Color(204, 204, 0));
        jLabel13.setText("Denda Per Hari");

        jLabel14.setForeground(new java.awt.Color(204, 204, 0));
        jLabel14.setText("Total Denda");

        txtidPeralatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidPeralatanActionPerformed(evt);
            }
        });

        txtJml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJmlActionPerformed(evt);
            }
        });

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dikembalikan", "Rusak", "Hilang" }));
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        txtDenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDendaKeyPressed(evt);
            }
        });

        txtTotalDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalDendaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Sub Total");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Bayar");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Kembali");

        jButton1.setText("Cetak Nota");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtbayar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtbayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });
        txtbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbayarKeyPressed(evt);
            }
        });

        subtotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        kembalian.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtbayar, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(kembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        btTambah.setText("Tambahkan");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        btExit.setText("Exit");
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });

        tabel_cek_peralatan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_cek_peralatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_cek_peralatanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_cek_peralatan);

        jLabel15.setForeground(new java.awt.Color(204, 204, 0));
        jLabel15.setText("Id Penyewa");

        btcek.setText("Cek");
        btcek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcekActionPerformed(evt);
            }
        });

        alter.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        alter.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtidPeralatan, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtidPenyewa, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtidPinjam, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtidPengembalian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtPeralatan)
                                                .addComponent(bts_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(Tgl_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel14)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel12))
                                                .addGap(24, 24, 24))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btTambah)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtTotalDenda, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtDenda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(alter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(btExit, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcek, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btcek)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcek)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtidPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtidPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtidPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtidPeralatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel12)
                                        .addComponent(txtPeralatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel13)
                                        .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(alter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(bts_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtTotalDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(Tgl_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(8, 8, 8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(txtTelat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btTambah)
                                        .addComponent(btExit))
                                    .addGap(23, 23, 23)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(158, 158, 158))))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Mengirimkan nilai parameter dari Class pembelian ke Class SourceBarang
   /* public void itemTerpilih(){
        SourcePeralatan fDB = new SourcePeralatan();
        fDB.fAB = this;
        txtidPinjam.setText(id_pinjam);
        txtidPenyewa.setText(id_penyewa);
        txtidPeralatan.setText(id_peralatan);
        txtJml.setText(jml_peralatan);
        txtPenyewa.setText(penyewa);
        txtPeralatan.setText(peralatan);
       
       
       // txtnama.setText(namaBrg);
        //txtharga.setText(hargaBrg);
    }*/
    private void txtidPeralatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidPeralatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidPeralatanActionPerformed

    private void txtTotalDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalDendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDendaActionPerformed

    private void txtJmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJmlActionPerformed

    private void btcekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcekActionPerformed
        // TODO add your handling code here:
        tampilDataSewa();
    }//GEN-LAST:event_btcekActionPerformed

    private void tabel_cek_peralatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_cek_peralatanMouseClicked
        // TODO add your handling code here:
        int baris=tabel_cek_peralatan.rowAtPoint(evt.getPoint());
        String id_pinjam=tabel_cek_peralatan.getValueAt(baris, 0).toString();
        txtidPinjam.setText(id_pinjam);
        String id_penyewa=tabel_cek_peralatan.getValueAt(baris, 1).toString();
        txtidPenyewa.setText(id_penyewa);
        String id_peralatan=tabel_cek_peralatan.getValueAt(baris, 2).toString();
        txtidPeralatan.setText(id_peralatan);
        String penyewa=tabel_cek_peralatan.getValueAt(baris, 3).toString();
        txtPenyewa.setText(penyewa);
        String peralatan=tabel_cek_peralatan.getValueAt(baris, 4).toString();
        txtPeralatan.setText(peralatan);
        String jml=tabel_cek_peralatan.getValueAt(baris, 7).toString();
        txtJml.setText(jml);
        try {
            String tgl=tabel_cek_peralatan.getValueAt(baris, 6).toString();
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
            bts_kembali.setDate(date);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Tanggal Bermasalah");
        }
        tangal();
        selisihTanggal();
        cbStatus.setSelectedItem("Dikembalikan");
        cekCombobox();
    }//GEN-LAST:event_tabel_cek_peralatanMouseClicked

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        TambahData();
        TabelPengembalian();
        TabelPinjaman();
    }//GEN-LAST:event_btTambahActionPerformed

    private void txtDendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDendaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int selisih=Integer.parseInt(txtTelat.getText());
            int denda=Integer.parseInt(txtDenda.getText());
            int hit=selisih*denda;
            txtTotalDenda.setText(Integer.toString(hit));
        }
        
    }//GEN-LAST:event_txtDendaKeyPressed

    private void btExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btExitActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
       cekCombobox();
    }//GEN-LAST:event_cbStatusActionPerformed

    private void tabel_pengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pengembalianMouseClicked
        // TODO add your handling code here:
        int baris=tabel_pengembalian.rowAtPoint(evt.getPoint());
        String id_Pengembalian=tabel_pengembalian.getValueAt(baris, 0).toString();
        String id_sewa=tabel_pengembalian.getValueAt(baris, 1).toString();
        String id_penyewa=tabel_pengembalian.getValueAt(baris, 2).toString();
        String id_peralatan=tabel_pengembalian.getValueAt(baris, 3).toString();
        String nm_penyewa=tabel_pengembalian.getValueAt(baris, 4).toString();
        String nm_peralatan=tabel_pengembalian.getValueAt(baris, 5).toString();
        String  jml=tabel_pengembalian.getValueAt(baris, 8).toString();
        String  denda=tabel_pengembalian.getValueAt(baris, 10).toString();
        String  status=tabel_pengembalian.getValueAt(baris, 11).toString();
        
        try {
            String tgl_kembali=tabel_pengembalian.getValueAt(baris, 6).toString();
            java.util.Date bts_kembali=new SimpleDateFormat("yyyy-MM-dd").parse(tgl_kembali);
            Statement statment=koneksi.GetconConnection().createStatement();
            statment.execute("Update peralatan set jml=jml-'"+jml+"' where id_peralatan='"+id_peralatan+"'");
            //Statement statment2=koneksi.GetconConnection().createStatement();
            
                try {// mengecek apakan ada data yang sama
                    Statement statment2=koneksi.GetconConnection().createStatement();
                    ResultSet rs=statment2.executeQuery("SELECT * from tmp_transaksi_sewa where id_transaksi_sewa='"+id_sewa+"' and id_peralatan='"+id_peralatan+"'");
                    if(rs.next()){
                        statment.execute("UPDATE tmp_transaksi_sewa set jml_peralatan=jml_peralatan + '"+jml+"' where id_transaksi_sewa='"+id_sewa+"' and id_peralatan='"+id_peralatan+"'");
                    }else{
                        statment.execute("insert into tmp_transaksi_sewa values ('"+id_sewa+"','"+id_peralatan+"','"+id_penyewa+"','"+new java.sql.Date(bts_kembali.getTime())+"','"+new java.sql.Date(bts_kembali.getTime())+"','"+jml+"')");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
           // Statement statment3=koneksi.GetconConnection().createStatement();
            statment.execute("DELETE from transaksi_pengembalian where id_transaksi_pengembalian='"+id_Pengembalian+"' AND id_peralatan='"+id_peralatan+"' AND id_penyewa='"+id_penyewa+"' AND status='"+status+"'");
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
       TabelPengembalian();
       TabelPinjaman();
       this.updateCekDebda(denda, id_Pengembalian, status, id_peralatan, jml);
       this.hitungMinus(denda);
    }//GEN-LAST:event_tabel_pengembalianMouseClicked

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbayarActionPerformed

    private void txtbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int total=Integer.parseInt(subtotal.getText());
            int bayar=Integer.parseInt(txtbayar.getText());
            int hit=bayar-total;
            kembalian.setText(Integer.toString(hit));
        }
    }//GEN-LAST:event_txtbayarKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JasperReport jasRep;
        JasperPrint jasPrint;
        Map<String, Object> Transaksi=new HashMap<String, Object>();
        JasperDesign jasDes;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi=DriverManager.getConnection("jdbc:mysql://localhost/owncamping","root","");
            String report="./src/laporan/cetaknotasewapengembalian.jrxml";
            jasDes=JRXmlLoader.load(report);
            Transaksi.put("id_transaksi_pengembalian",txtidPengembalian.getText());
            Transaksi.put("subtotal",subtotal.getText());
            Transaksi.put("bayar",txtbayar.getText());
            Transaksi.put("kembalian",kembalian.getText());
            jasRep=JasperCompileManager.compileReport(jasDes);
            jasPrint=JasperFillManager.fillReport(jasRep, Transaksi, koneksi);
            JasperViewer.viewReport(jasPrint,false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        OtomatisNotrans();
        kosongkan();
        txtidPinjam.setText(null);
        TabelPengembalian();
        TabelPinjaman();
        subtotal.setText(null);
        txtbayar.setText(null);
        kembalian.setText(null);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Tgl_Kembali;
    private javax.swing.JLabel alter;
    private javax.swing.JButton btExit;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btcek;
    private com.toedter.calendar.JDateChooser bts_kembali;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel kembalian;
    private javax.swing.JLabel subtotal;
    private javax.swing.JTable tabel_cek_peralatan;
    private javax.swing.JTable tabel_pengembalian;
    private javax.swing.JTextField txtDenda;
    private javax.swing.JTextField txtJml;
    private javax.swing.JTextField txtPenyewa;
    private javax.swing.JTextField txtPeralatan;
    private javax.swing.JTextField txtTelat;
    private javax.swing.JTextField txtTotalDenda;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtcek;
    private javax.swing.JTextField txtidPengembalian;
    private javax.swing.JTextField txtidPenyewa;
    private javax.swing.JTextField txtidPeralatan;
    private javax.swing.JTextField txtidPinjam;
    // End of variables declaration//GEN-END:variables
}
