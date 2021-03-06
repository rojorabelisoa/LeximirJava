/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leximir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.StaticValue;

/**
 *
 * @author rojo
 */
public class ChooseDela extends javax.swing.JFrame {

    /**
     * Creates new form ChooseDela
     */
    public ChooseDela() {
        initComponents();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("dic");
        tableModel.addColumn("path");
        tableModel.addColumn("order");
        tableModel2 = new DefaultTableModel();
        tableModel2.addColumn("dic");
        tableModel2.addColumn("path");
        tableModel2.addColumn("order");
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButtonAdd1 = new javax.swing.JButton();
        jButtonDelete1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Delas dictionnaries :");

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButtonAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonDelete)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Delas", jPanel2);

        jLabel2.setText("Delas dictionnaries :");

        jButtonAdd1.setText("Add");
        jButtonAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdd1ActionPerformed(evt);
            }
        });

        jButtonDelete1.setText("Delete");
        jButtonDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelete1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jTable2);

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButtonAdd1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelete1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButtonAdd1)
                    .addComponent(jButtonDelete1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Delac", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
       JFileChooser theFileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Dic FILES", "dic");
            theFileChooser.setFileFilter(filter);
            theFileChooser.setCurrentDirectory(new File(StaticValue.allDela));
            theFileChooser.setDialogTitle("Search dela Dictionnary");
            theFileChooser.setMultiSelectionEnabled(true);
            theFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            theFileChooser.setAcceptAllFileFilterUsed(false);
            if(theFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File[] f = theFileChooser.getSelectedFiles();
                int count=jTable1.getRowCount();
                for(File i:f){
                    String tmp = i.getName();
                    if(tmp.endsWith(".dic")){
                        tableModel.addRow(new Object[]{tmp,i.getAbsolutePath(),count});
                        count++;
                    }
                }  
                jTable1.setModel(tableModel);
            }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete this row?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            int t = this.jTable1.getSelectedRow();
            this.tableModel.removeRow(t);
            JOptionPane.showMessageDialog(null, "rows deleted !");
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "This will overwrite your configuration. Are you sure?","Save Delas dictioneries configuration",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            try {
                BufferedWriter bfw;
                Map<String,List<String>> fileData=new HashMap<>();
                bfw = new BufferedWriter(new FileWriter(StaticValue.allDelas+"//confDelas.conf"));
                for(int row = 0; row < tableModel.getRowCount(); row ++){
                    
                    String file = (String) tableModel.getValueAt(row, 0);
                    String path = (String) tableModel.getValueAt(row, 1);
                    int order =(int) tableModel.getValueAt(row, 2);
                    String str=file+","+path+":"+order+"\n";
                    bfw.write(str);
                    
                }
                bfw.close();
                JOptionPane.showMessageDialog(null, "Success");
            } catch (IOException ex) {
                Logger.getLogger(ChooseDela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd1ActionPerformed
        JFileChooser theFileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Dic FILES", "dic");
            theFileChooser.setFileFilter(filter);
            theFileChooser.setCurrentDirectory(new File(StaticValue.allDela));
            theFileChooser.setDialogTitle("Search delac Dictionnary");
            theFileChooser.setMultiSelectionEnabled(true);
            theFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            theFileChooser.setAcceptAllFileFilterUsed(false);
            if(theFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File[] f = theFileChooser.getSelectedFiles();
                int count=jTable2.getRowCount();
                for(File i:f){
                    String tmp = i.getName();
                    if(tmp.endsWith(".dic")){
                        tableModel2.addRow(new Object[]{tmp,i.getAbsolutePath(),count});
                        count++;
                    }
                }  
                jTable2.setModel(tableModel2);
            }
    }//GEN-LAST:event_jButtonAdd1ActionPerformed

    private void jButtonDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelete1ActionPerformed
         int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete this row?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            int t = this.jTable2.getSelectedRow();
            this.tableModel2.removeRow(t);
            JOptionPane.showMessageDialog(null, "rows deleted !");
        }
    }//GEN-LAST:event_jButtonDelete1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog (null, "This will overwrite your configuration. Are you sure?","Save Delas dictioneries configuration",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            try {
                BufferedWriter bfw;
                Map<String,List<String>> fileData=new HashMap<>();
                bfw = new BufferedWriter(new FileWriter(StaticValue.allDelac+"//confDelac.conf"));
                for(int row = 0; row < tableModel2.getRowCount(); row ++){
                    
                    String file = (String) tableModel2.getValueAt(row, 0);
                    String path = (String) tableModel2.getValueAt(row, 1);
                    int order =(int) tableModel2.getValueAt(row, 2);
                    String str=file+","+path+":"+order+"\n";
                    bfw.write(str);
                    
                }
                bfw.close();
                JOptionPane.showMessageDialog(null, "Success");
            } catch (IOException ex) {
                Logger.getLogger(ChooseDela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ChooseDela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChooseDela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChooseDela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChooseDela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseDela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAdd1;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDelete1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel tableModel = new DefaultTableModel();
    private DefaultTableModel tableModel2 = new DefaultTableModel();
}
