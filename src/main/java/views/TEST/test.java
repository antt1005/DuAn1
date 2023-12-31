/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.TEST;

import domainModels.ChiTietDoGo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import utilities.mycompany.DBConext.HibernatUtil;
import viewModel.ViewModelChiTietSanPhamBanHang;
import viewModel.ViewModelHoaDonChiTietBanHang;

/**
 *
 * @author Admin
 */
public class test extends javax.swing.JFrame {

    /**
     * Creates new form test
     */
    List<ViewModelHoaDonChiTietBanHang> l = new ArrayList<>();

    public test() {
        initComponents();
//        load();
    }

    public void load() {

//        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
//        l.add(new ViewModelHoaDonChiTietBanHang("1", "1", "duy1211", 1, new BigDecimal(190000)));
//        l.add(new ViewModelHoaDonChiTietBanHang("2", "2", "c", 2, new BigDecimal(19000000)));
//        l.add(new ViewModelHoaDonChiTietBanHang("3", "3", "v", 11, new BigDecimal(190000)));
//        l.add(new ViewModelHoaDonChiTietBanHang("4", "4", "b", 511, new BigDecimal(10000)));
//        l.add(new ViewModelHoaDonChiTietBanHang("5", "4", "b", 511, new BigDecimal(10000)));
//        for (ViewModelHoaDonChiTietBanHang i : l) {
//            model.addRow(new Object[]{
//                i.getIdsp(), i.getTen(), i.getSoluong(), i.getDonGia()
//            });
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        oke = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tr = new javax.swing.JPanel();
        iii = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        oke.setText("jButton3");
        oke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okeActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        tr.setLayout(new java.awt.BorderLayout());

        iii.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        iii.setLayout(new javax.swing.BoxLayout(iii, javax.swing.BoxLayout.LINE_AXIS));
        tr.add(iii, java.awt.BorderLayout.CENTER);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oke)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tr, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tr, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(oke)
                .addGap(125, 125, 125)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okeActionPerformed
        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();
            session.beginTransaction();
            Query q = session.createQuery("FROM ChiTietDoGo where SoLuong > 0");
            List<ChiTietDoGo> list = q.getResultList();
            session.close();
            for (ChiTietDoGo a : list) {
                dataset.setValue(a.getTenSP()+" SL"+a.getSoLuong(), a.getSoLuong());
                System.out.println(a.toString());
            }
            for (ChiTietDoGo a : list) {
                dataset2.setValue(a.getSoLuong(), a.getTenSP(), "");
            }
        } catch (HibernateException hibernateException) {
        }
        //////////////
        try {
            JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                    dataset, true, true,
                    false);
            ///////////////
            JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                    "TenSP", "soluong", dataset2,
                    PlotOrientation.VERTICAL, true,
                    true, false);

            
            ChartPanel chartPanel = new ChartPanel(chart);// add cái biểu dồ vào jfanel

            iii.removeAll();
            iii.add(chartPanel);
            iii.updateUI();
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_okeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();
            session.beginTransaction();
            Query q = session.createQuery("FROM ChiTietDoGo where SoLuong > 0");
            List<ChiTietDoGo> list = q.getResultList();
            session.close();
            for (ChiTietDoGo a : list) {
                dataset.setValue(a.getTenSP()+"SL"+a.getSoLuong(), a.getSoLuong());
                System.out.println(a.toString());
            }
            for (ChiTietDoGo a : list) {
                dataset2.setValue(a.getSoLuong(), a.getTenSP(), "");
            }
        } catch (HibernateException hibernateException) {
        }
        //////////////
        try {
            JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                    dataset, true, true,
                    false);
            ///////////////
            JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                    "TenSP", "soluong", dataset2,
                    PlotOrientation.VERTICAL, true,
                    true, false);
            //////////////////
            PiePlot3D p1 = (PiePlot3D) chart.getPlot();
//        CategoryPlot p1 = (CategoryPlot) barChart.getCategoryPlot();

//        p1.setRangeGridlinePaint(Color.ORANGE);
            ChartFrame frame = new ChartFrame("Bang", chart, true);
            ChartFrame frame1 = new ChartFrame("Bang", barChart, true);
//            frame.setVisible(true);
//            frame.setSize(1200, 800);
//            frame.setVisible(true);
//            frame1.setVisible(true);
//            frame1.setSize(1200, 800);
//            frame1.setVisible(true);
            
            ChartPanel chartPanel = new ChartPanel(barChart);// add cái biểu dồ vào jfanel

            iii.removeAll();
            iii.add(chartPanel);
            iii.updateUI();
        } catch (Exception e) {
        }
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
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel iii;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton oke;
    private javax.swing.JPanel tr;
    // End of variables declaration//GEN-END:variables
}
