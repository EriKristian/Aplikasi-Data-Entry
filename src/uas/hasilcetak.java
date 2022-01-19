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

public final class hasilcetak extends javax.swing.JFrame {

    Koneksi conn = new Koneksi();

    public Connection koneksi;
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel model;

    public hasilcetak(String tb1,String tb2){
        initComponents();
        String a = tb1;
        String b = tb2;
        String[] header = {"Kode Mobil", "Nama Mobil", "Kode Jenis", "Jenis",
            "Harga Beli", "Harga Jual", "Stock", "Total H beli",
            "Total H Jual", "Keterangan"};
        model = new DefaultTableModel(header, 0);
        tabel.setModel(model);
        tampilan(a,b);
    }

    public void tampilan(String a,String b) {
        try {
            koneksi = conn.bukainkoneksi();
            st = koneksi.createStatement();
            String sql = "Select tbldatamobil.kodem, tbldatamobil.namam, "
                    + "tbldatamobil.kodej, tbljenis.jenis, tbldatamobil.hargabeli,"
                    + " tbldatamobil.hargajual, tbldatamobil.stok, (tbldatamobil.hargabeli*tbldatamobil.stok) as totalbeli, (tbldatamobil.hargajual*tbldatamobil.stok) as totaljual"
                    + " from tbldatamobil"
                    + " inner join tbljenis"
                    + " ON tbldatamobil.kodej=tbljenis.kodej \n"
                    + " WHERE kodem BETWEEN '"+ a +"' AND '"+ b +"'";
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jScrollPane1.setViewportView(tabel);

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new MainMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(hasilcetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hasilcetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hasilcetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hasilcetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new hasilcetak(this.toString(),this.toString()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}
