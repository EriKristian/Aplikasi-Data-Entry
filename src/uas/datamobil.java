/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public final class datamobil extends javax.swing.JFrame {

    Koneksi conn = new Koneksi();

    public Connection koneksi;
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel model;

    public datamobil() {
        initComponents();
        String[] header = {"Kode Mobil", "Nama Mobil", "Kode Jenis", "Jenis",
            "Harga Beli", "Harga Jual", "Stock", "Total H beli",
            "Total H Jual", "Keterangan"};
        model = new DefaultTableModel(header, 0);
        tabel.setModel(model);
        tampiltbox();
        tampilan();
    }

    public void tampilan() {

        try {
            koneksi = conn.bukainkoneksi();
            st = koneksi.createStatement();
            String sql = "Select tbldatamobil.kodem, tbldatamobil.namam, "
                    + "tbldatamobil.kodej, tbljenis.jenis, tbldatamobil.hargabeli,"
                    + " tbldatamobil.hargajual, tbldatamobil.stok, (tbldatamobil.hargabeli*tbldatamobil.stok) as totalbeli, (tbldatamobil.hargajual*tbldatamobil.stok) as totaljual"
                    + " from tbldatamobil"
                    + " inner join tbljenis "
                    + "ON tbldatamobil.kodej=tbljenis.kodej;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String totalbeli = rs.getString("totalbeli");
                String totaljual = rs.getString("totaljual");
                String ket;
                if (Integer.parseInt(rs.getString("stok")) < 2) {
                    ket = "Stock Kritis";
                } else if (Integer.parseInt(rs.getString("stok")) > 5) {
                    ket = "Over Stock";
                } else {
                    ket = "Stock Aman";
                }
                String[] baris = {rs.getString("kodem"), rs.getString("namam"),
                    rs.getString("kodej"), rs.getString("jenis"), rs.getString("hargabeli"),
                    rs.getString("hargajual"), rs.getString("stok"), totalbeli,
                    totaljual, ket};
                model.addRow(baris);
            }
            tabel.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error Bambank!" + e);
        }
    }

    public void tampiltbox() {
        try {
            koneksi = conn.bukainkoneksi();
            st = koneksi.createStatement();
            rs = st.executeQuery("Select jenis from tbljenis");
            while (rs.next()) {
                tbox.addItem(rs.getString("jenis"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String tkodej(String kodej) {
        try {
            koneksi = conn.bukainkoneksi();
            st = koneksi.createStatement();
            rs = st.executeQuery("Select kodej from tbljenis where jenis='" + tbox.getSelectedItem() + "'");
            while (rs.next()) {
                kodej = rs.getString("kodej");
            }
        } catch (Exception e) {
            System.out.println(kodej);
            JOptionPane.showMessageDialog(null, e);
        }
        return kodej;
    }

    public void simpan() {
        String kode = tkode.getText();
        String nama = tnama.getText();
        int beli = Integer.parseInt(tbeli.getText());
        int jual = Integer.parseInt(tjual.getText());
        int stok = Integer.parseInt(tstok.getText());
        String kodej = "";
        kodej = tkodej(kodej);
        if (kode.equals("") || nama.equals("") || tbeli.equals("") || tjual.equals("")
                || tstok.equals("")) {
            JOptionPane.showMessageDialog(this, "Isi semua form!");
        } else {
            String sql = "insert into tbldatamobil(kodem, namam, kodej, hargabeli, hargajual, stok)"
                    + "values"
                    + "('" + kode + "', "
                    + "'" + nama + "',"
                    + "'" + kodej + "',"
                    + "'" + beli + "',"
                    + "'" + jual + "',"
                    + "'" + stok + "'"
                    + ")";
            conn.simpanData(sql);
        }
        new datamobil().setVisible(true);
        this.dispose();

    }

    public void edit() {
        String kode = tkode.getText();
        String nama = tnama.getText();
        int beli = Integer.parseInt(tbeli.getText());
        int jual = Integer.parseInt(tjual.getText());
        int stok = Integer.parseInt(tstok.getText());
        String kodej = "";
        kodej = tkodej(kodej);
        if (kode.equals("") || nama.equals("") || tbeli.equals("") || tjual.equals("")
                || tstok.equals("")) {
            JOptionPane.showMessageDialog(this, "Isi semua form!");
        } else {
            String sql = "Update tbldatamobil set namam='" + nama + "', "
                    + "kodej='" + kodej + "', "
                    + "hargabeli='" + beli + "', "
                    + "hargajual='" + jual + "', "
                    + "stok='" + stok + "' "
                    + "where kodem='" + kode + "'";
            conn.simpanData(sql);
        }
        new datamobil().setVisible(true);
        this.dispose();

    }

    public void hapus() {
        String kode = tkode.getText();
        String nama = tnama.getText();
        int beli = Integer.parseInt(tbeli.getText());
        int jual = Integer.parseInt(tjual.getText());
        int stok = Integer.parseInt(tstok.getText());
        if (kode.equals("") || nama.equals("") || tbeli.equals("") || tjual.equals("")
                || tstok.equals("")) {
            JOptionPane.showMessageDialog(this, "Isi semua form!");
        } else {
            String sql = "Delete from tbldatamobil where kodem='" + kode + "'";
            conn.simpanData(sql);
        }
        new datamobil().setVisible(true);
        this.dispose();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tkode = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        tbox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tbeli = new javax.swing.JTextField();
        tjual = new javax.swing.JTextField();
        tstok = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Kode Mobil  :");

        jLabel2.setText("Nama Mobil :");

        jLabel3.setText("Jenis           :");

        tbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tboxActionPerformed(evt);
            }
        });

        jLabel4.setText("Harga Beli   :");

        jLabel5.setText("Harga Jual  :");

        jLabel6.setText("Stock          :");

        tjual.setToolTipText("");

        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Close");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tkode, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tbox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tstok, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tjual))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tboxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        simpan();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new MainMenu().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        edit();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        hapus();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        int baris = tabel.getSelectedRow();
        if (baris != -1) {
            tkode.setText(tabel.getValueAt(baris, 0).toString());
            tnama.setText(tabel.getValueAt(baris, 1).toString());
            String item;
            String value = (tabel.getValueAt(baris,3).toString());
            for (int i = 0; i < tbox.getItemCount(); i++)
            {
                item = tbox.getItemAt(i).toString();
                if (item.contains(value))
                {
                    tbox.setSelectedIndex(i);
                }
            }
            tbeli.setText(tabel.getValueAt(baris, 4).toString());
            tjual.setText(tabel.getValueAt(baris, 5).toString());
            tstok.setText(tabel.getValueAt(baris, 6).toString());
        }
    }//GEN-LAST:event_tabelMouseClicked

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
            java.util.logging.Logger.getLogger(datamobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(datamobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(datamobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(datamobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datamobil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tbeli;
    private javax.swing.JComboBox<String> tbox;
    private javax.swing.JTextField tjual;
    private javax.swing.JTextField tkode;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tstok;
    // End of variables declaration//GEN-END:variables
}
