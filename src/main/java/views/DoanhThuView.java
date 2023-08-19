/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainModels.ChiTietDoGo;
import java.io.File;
import java.io.FileOutputStream;
import static java.lang.String.format;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import pagination.EventPagination;
import pagination.style.PaginationItemRenderStyle1;
import services.DoanhThuService;
import services.impl.IManageDoanhThu;
import utilities.mycompany.DBConext.HibernatUtil;
import viewModel.ViewModelDoanhThu;
import viewModel.ViewModelDoanhThuThongKe;
import viewModel.ViewModelNhanVienDoanhThu;
import static views.ChiTietSanPhamView.phanTrang;

/**
 *
 * @author Admin
 */
public class DoanhThuView extends javax.swing.JFrame {

    private DefaultTableModel model;

    private IManageDoanhThu im = new DoanhThuService();

    private static int page1 = 1;

    private static int page2 = 1;

    String IdNV;

    String TenNV;

    String CV;

    /**
     * Creates new form DoanhThuView
     */
    public DoanhThuView(String Id, String Ten, String cv) {
        initComponents();
        IdNV = Id;

        TenNV = Ten;

        CV = cv;
        setLocationRelativeTo(null);

        loadTbsanPhamtulondenbe(page1);

        loadTbTOP3();

        laysospdangban();

        laysospsaphet();

        laysosphethang();

        laysospngungkinhdoanh();

        //doanhthu nhanvien
        loadTbNhanVien();

        loadAlldoanhthu();

        //doanhthuthongke
        laydoanhthutheonam();

        laydoanhthutheothang();

        laydoanhthutheongay();

        // laydoanhthuttheochon();
        //so tien bo ra mua sp
        sotienboratheonam();

        sotienboratheothang();

        sotienboratheongay();

//        sotienboratheodachon();
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());

        int index = cbbdoanhthu.getSelectedIndex();

        pagination1.addEventPagination(new EventPagination() {

            @Override
            public void pageChanged(int page) {
                if (index == 0) {

                    page1 = page;

                    loadTbsanPhamtulondenbe(page);

                } else {

                    page2 = page;

                    loadTbsanPhamtubedenlon(page);

                }
            }
        });
    }

    public void loadTbsanPhamtulondenbe(int page) {

        int limit = 3;
        int count = 0;

        List<ViewModelDoanhThu> ct = im.getList1((page - 1) * limit, limit);
        if (ct == null) {
            return;
        }

        count = im.getListSL();

        model = (DefaultTableModel) tbbangsanpham.getModel();

        model.setRowCount(0);

        List<ViewModelDoanhThu> hd = im.getList1((page - 1) * limit, limit);

        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });
        }

        int totalPage = (int) Math.ceil(count / limit);

        pagination1.setPagegination(page, totalPage + 1);

    }

    public void loadTbsanPhamtubedenlon(int page1) {

        int limit = 3;
        int count = 0;

        List<ViewModelDoanhThu> ct = im.getListBdenL((page1 - 1) * limit, limit);
        if (ct == null) {
            return;
        }

        count = im.getListSLBdenL();

        model = (DefaultTableModel) tbbangsanpham.getModel();

        model.setRowCount(0);

        List<ViewModelDoanhThu> hd = im.getListBdenL((page1 - 1) * limit, limit);

        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });
        }

        int totalPage = (int) Math.ceil(count / limit);

        pagination1.setPagegination(page1, totalPage + 1);

    }

    public void laysospdangban() {

        int sp = im.getListSanPham();

        txtsospdangkd.setText(sp + "");

    }

    public void laysospsaphet() {

        int sp = im.getListGanHet();

        txtsospsaphethang.setText(sp + "");

    }

    public void laysosphethang() {

        int sp = im.getListhethang();

        txtsophanhethang.setText(sp + "");

    }

    public void laysospngungkinhdoanh() {
        int sp = im.getListNgungKinhdoanh();

        txtsospngungkinhdoanh.setText(sp + "");

    }

    public void loadTbsanPhamALL() {

        model = (DefaultTableModel) tbbangsanpham.getModel();

        model.setRowCount(0);

        List<ViewModelDoanhThu> hd = im.getList();

        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });

        }

    }

    public void loadTbsanPhamALL1() {

        model = (DefaultTableModel) tbbangsanpham.getModel();

        model.setRowCount(0);

        List<ViewModelDoanhThu> hd = im.getList1();

        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongton(), v.getSoluongban(), v.getTongtien()
            });

        }

    }

    public void loadTbTOP3() {
        model = (DefaultTableModel) tbbangsanphamtop3.getModel();

        model.setRowCount(0);

        List<ViewModelDoanhThu> hd = im.getListtop3();

        for (ViewModelDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getIdsp(), v.getTensanpham(), v.getSoluongban()
            });

        }

    }

    //doanhthunhanvien   
    public void loadTbNhanVien() {

        model = (DefaultTableModel) tbbangnhanhvien.getModel();

        model.setRowCount(0);

        List<ViewModelNhanVienDoanhThu> hd = im.getListNhanVien();

        for (ViewModelNhanVienDoanhThu v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getTennv(), v.getChucvu(), v.getHddalam()
            });

        }

    }

    //doanhthuthongke
    public void laydoanhthutheonam() {

        int sp = im.getDoanhthuTheoNam();

        txtdoanhthutheonam.setText(sp + "");

    }

    public void laydoanhthutheothang() {

        int sp = im.getDoanhthutheoThang();

        txtdoanthuthang.setText(sp + "");

    }

    public void laydoanhthutheongay() {

        int sp = im.getDoanhtHUTHEOnGAY();

        txtdoanhthungay.setText(sp + "");

    }

    public void laydoanhthuttheochon() {

        java.util.Date dateBD = dateNgayBatdau.getDate();

        java.util.Date dateKT = DateNgayKetThuc.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String nbd = format.format(dateBD);

        String nkt = format.format(dateKT);

        int sp = im.getListTongtiendachon(nbd, nkt);

        txtdoanthudachon.setText(sp + "");

    }

    public void loadTbdoanhthuthongke() {
        java.util.Date dateBD = dateNgayBatdau.getDate();

        java.util.Date dateKT = DateNgayKetThuc.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String nbd = format.format(dateBD);

        String nkt = format.format(dateKT);

        model = (DefaultTableModel) tbdongthuthongke.getModel();

        model.setRowCount(0);

        List<ViewModelDoanhThuThongKe> hd = im.getListHoaDonDaChon(nbd, nkt);

        for (ViewModelDoanhThuThongKe v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), nbd + "  đến  " + nkt, v.getTongtien()
            });

        }

    }

    public void loadAlldoanhthu() {

        model = (DefaultTableModel) tbdongthuthongke.getModel();

        model.setRowCount(0);

        List<ViewModelDoanhThuThongKe> hd = im.getListDoanhthu();

        for (ViewModelDoanhThuThongKe v : hd) {
            model.addRow(new Object[]{
                v.getId(), v.getMa(), v.getNgaytt(), v.getTongtien()
            });

        }
    }

    //so tien bo ra mua sp
    public void sotienboratheonam() {

        int sp = im.getsotiennhaptheonam();

        txtsotiennhapnam.setText(sp + "");

    }

    public void sotienboratheothang() {

        int sp = im.getsotiennhaptheothang();

        txtsotiennhaptheothang.setText(sp + "");

    }

    public void sotienboratheongay() {

        int sp = im.getsotiennhaptheoNgay();

        txtsotiennhapngay.setText(sp + "");

    }

    public void sotienboratheodachon() {

        java.util.Date dateBD = dateNgayBatdau.getDate();

        java.util.Date dateKT = DateNgayKetThuc.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String nbd = format.format(dateBD);

        String nkt = format.format(dateKT);

        int sp = im.getListTongBoRaDaChon(nbd, nkt);

        txtsotiennhapdachon.setText(sp + "");

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
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtsospngungkinhdoanh = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtsospdangkd = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtsophanhethang = new javax.swing.JLabel();
        cbbdoanhthu = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtsospsaphethang = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbbangsanphamtop3 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbangsanpham = new javax.swing.JTable();
        pagination1 = new pagination.Pagination();
        EX1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        a = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtdoanhthutheonam = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtdoanhthungay = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtdoanthudachon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdongthuthongke = new javax.swing.JTable();
        dateNgayBatdau = new com.toedter.calendar.JDateChooser();
        DateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtdoanthuthang = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtsotiennhapngay = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtsotiennhapnam = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtsotiennhapdachon = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtsotiennhaptheothang = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbbangnhanhvien = new javax.swing.JTable();
        EX = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jTabbedPane1.setBackground(new java.awt.Color(255, 153, 204));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("SP Ngừng Kinh Doanh");

        txtsospngungkinhdoanh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospngungkinhdoanh.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtsospngungkinhdoanh, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtsospngungkinhdoanh, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(21, 21, 21))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Số Sản Phẩm Đang Kinh Doanh");

        txtsospdangkd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospdangkd.setText("-");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(txtsospdangkd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(txtsospdangkd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số Sản Phẩm hết Hàng");

        txtsophanhethang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsophanhethang.setText("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(txtsophanhethang, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txtsophanhethang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(25, 25, 25))
        );

        cbbdoanhthu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doanh Thu Từ Lớn Đến Bé", "Doanh Thu Từ Bé Đến Lớn" }));
        cbbdoanhthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbdoanhthuActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số Sản Phẩm Sắp hết Hàng");

        txtsospsaphethang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsospsaphethang.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(txtsospsaphethang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(14, 14, 14))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txtsospsaphethang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(20, 20, 20))
        );

        jPanel15.setBackground(new java.awt.Color(255, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0))), "Top 3 "));

        tbbangsanphamtop3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Ten", "SoLuongBan"
            }
        ));
        jScrollPane4.setViewportView(tbbangsanphamtop3);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 204, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0))), "Sản Phẩm"));

        tbbangsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Idsp", "TenSanPham", "SoLuongTon", "SoLuongBan", "TongTien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbbangsanpham);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        pagination1.setOpaque(false);

        EX1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EX1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        EX1.setText("Excel");
        EX1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        EX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EX1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbbdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EX1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(EX1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addComponent(cbbdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("San Pham", jPanel3);

        jPanel6.setBackground(new java.awt.Color(255, 204, 255));
        jPanel6.setBorder(new javax.swing.border.MatteBorder(null));

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("BD  cho Tháng");
        jButton7.setBorder(new javax.swing.border.MatteBorder(null));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setText("bd Thang");
        jButton8.setBorder(new javax.swing.border.MatteBorder(null));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("bd Nam");
        jButton9.setBorder(new javax.swing.border.MatteBorder(null));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        a.setBackground(new java.awt.Color(255, 204, 204));
        a.setBorder(new javax.swing.border.MatteBorder(null));
        a.setLayout(new javax.swing.BoxLayout(a, javax.swing.BoxLayout.LINE_AXIS));

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setText("BD   cho Năm");
        jButton10.setBorder(new javax.swing.border.MatteBorder(null));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
            .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("BD", jPanel6);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jPanel9.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Doanh Thu Năm nay");

        txtdoanhthutheonam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanhthutheonam.setText("-");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtdoanhthutheonam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(txtdoanhthutheonam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(19, 19, 19))
        );

        jPanel10.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Doanh Thu Hôm Nay");

        txtdoanhthungay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanhthungay.setText("-");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(txtdoanhthungay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(txtdoanhthungay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(19, 19, 19))
        );

        jPanel11.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Doanh Thu Đã Chọn");

        txtdoanthudachon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanthudachon.setText("-");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtdoanthudachon, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(txtdoanthudachon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(15, 15, 15))
        );

        tbdongthuthongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Hoa Don", "Ma Hoa Don", "Ngay Thanh Toan", "Tong Tien"
            }
        ));
        jScrollPane2.setViewportView(tbdongthuthongke);

        dateNgayBatdau.setBackground(new java.awt.Color(255, 204, 204));
        dateNgayBatdau.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0))), "StartDay"));
        dateNgayBatdau.setDateFormatString("yyyy-MM-dd");
        dateNgayBatdau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateNgayBatdauKeyReleased(evt);
            }
        });

        DateNgayKetThuc.setBackground(new java.awt.Color(255, 204, 204));
        DateNgayKetThuc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0))), "EndDay"));
        DateNgayKetThuc.setDateFormatString("yyyy-MM-dd");

        jPanel12.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Doanh Thu Tháng ");

        txtdoanthuthang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdoanthuthang.setText("-");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel12))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtdoanthuthang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(txtdoanthuthang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(17, 17, 17))
        );

        jPanel13.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Tiền nhập trong Ngày");

        txtsotiennhapngay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhapngay.setText("-");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel14)
                .addGap(27, 27, 27))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(txtsotiennhapngay, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(txtsotiennhapngay)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Tiền nhập trong năm nay");

        txtsotiennhapnam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhapnam.setText("-");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel15))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(txtsotiennhapnam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(txtsotiennhapnam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(13, 13, 13))
        );

        jPanel17.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Tiền nhập Ðã Chọn ");

        txtsotiennhapdachon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhapdachon.setText("-");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtsotiennhapdachon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(txtsotiennhapdachon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(19, 19, 19))
        );

        jPanel16.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Tiền nhập trong Tháng ");

        txtsotiennhaptheothang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsotiennhaptheothang.setText("-");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtsotiennhaptheothang, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(txtsotiennhaptheothang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(19, 19, 19))
        );

        txttimkiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/timkiemicon.png"))); // NOI18N
        txttimkiem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(255, 204, 204));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0))), "Top 3 Nhân Viên"));

        tbbangnhanhvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Ma", "Ten", "ChucVu", "sohdht"
            }
        ));
        jScrollPane3.setViewportView(tbbangnhanhvien);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        EX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        EX.setText("Excel");
        EX.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        EX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(89, 89, 89)
                            .addComponent(dateNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(70, 70, 70)
                            .addComponent(DateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(79, 79, 79)
                            .addComponent(txttimkiem)
                            .addGap(138, 138, 138)
                            .addComponent(EX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(71, 71, 71)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(2, 2, 2)))
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)))
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(DateNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(dateNgayBatdau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(EX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel2);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Back");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void cbbdoanhthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbdoanhthuActionPerformed
        String index = String.valueOf(cbbdoanhthu.getSelectedItem());

        if (index.equals("Doanh Thu Từ Lớn Đến Bé")) {

            loadTbsanPhamtulondenbe(page1);

        } else {

            loadTbsanPhamtubedenlon(page2);

        }

    }//GEN-LAST:event_cbbdoanhthuActionPerformed

    private void dateNgayBatdauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateNgayBatdauKeyReleased

    }//GEN-LAST:event_dateNgayBatdauKeyReleased

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        for (int i = 0; i < 1000; i++) {

            loadTbdoanhthuthongke();

            laydoanhthuttheochon();

            sotienboratheodachon();

        }
    }//GEN-LAST:event_txttimkiemActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            session.beginTransaction();

            Query q = session.createNativeQuery("select B.TenSP,Sum(A.SoLuong) from HoaDonChiTiet A , ChiTietDoGo B , HoaDon C\n"
                    + "where A.IdChiTietDoGo = B.Id and A.IdHoaDon = C.Id AND Month(NgayThanhToan) = Month(GETDATE()) AND C.TRANGTHAI >1\n"
                    + "group by B.Id,b.TenSP");

            List<Object[]> list = q.getResultList();

            session.close();

            for (Object[] a : list) {

                dataset.setValue(a[0].toString(), Integer.parseInt(a[1].toString()));

                System.out.println(a.toString());

            }
            for (Object[] a : list) {

                dataset2.setValue(Integer.parseInt(a[1].toString()), a[0].toString(), "");

            }
//               dataset.setValue("soluong",100);
//               dataset.setValue("taikhoan",90);
//               dataset.setValue("doanhthu",80);
//               dataset.setValue("thanhtien",70);
//               
//               dataset2.setValue(120, "baihoc", "cannang");
//               dataset2.setValue(120, "taikhoan", "cannang");
//               dataset2.setValue(120, "soluong", "cannang");
//               dataset2.setValue(120, "thanhtien", "cannang");

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

            a.removeAll();

            a.add(chartPanel);

            a.updateUI();

        } catch (Exception e) {

        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        DefaultPieDataset dataset = new DefaultPieDataset();

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            session.beginTransaction();

            Query q = session.createNativeQuery("select SUM(C.ThanhTien) AS'TONG TIEN',COUNT(C.ID)'SO HOA DON',MONTH(C.NgayThanhToan) AS'THANG',COUNT(A.SoLuong) AS 'SL' from HoaDonChiTiet A  ,HoaDon C \n"
                    + "                    WHERE  A.IdHoaDon = C.Id AND C.TRANGTHAI =2\n"
                    + "                    GROUP BY  MONTH(C.NgayThanhToan)");

            List<Object[]> THANG = q.getResultList();

            session.close();

            for (Object[] a : THANG) {

                dataset.setValue(a[2].toString(), Integer.parseInt(a[0].toString()));

            }
            for (Object[] a : THANG) {

                dataset2.setValue(Integer.parseInt(a[0].toString()), a[2].toString(), "");

            }
        } catch (HibernateException hibernateException) {

        }
        //////////////
        JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                dataset, true, true,
                false);
        ///////////////
        JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                "Tháng", "soluong", dataset2,
                PlotOrientation.VERTICAL, true,
                true, false);
        //////////////////
//        PiePlot3D p1 = (PiePlot3D) chart.getPlot();
//        CategoryPlot p1 = (CategoryPlot) barChart.getCategoryPlot();

//        p1.setRangeGridlinePaint(Color.ORANGE);
        ChartPanel chartPanel = new ChartPanel(chart);

        a.removeAll();

        a.add(chartPanel);

        a.updateUI();

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        DefaultPieDataset dataset = new DefaultPieDataset();

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {

            Session session = HibernatUtil.getFACTORY().openSession();

            session.beginTransaction();

            Query q = session.createNativeQuery("select SUM(C.ThanhTien) AS'TONG TIEN',COUNT(C.ID)'SO HOA DON',YEAR(C.NgayThanhToan) AS'NAM',COUNT(A.SoLuong) AS 'SL' from HoaDonChiTiet A  ,HoaDon C \n"
                    + "WHERE  A.IdHoaDon = C.Id AND C.TRANGTHAI =2\n"
                    + "GROUP BY  YEAR(C.NgayThanhToan)");

            List<Object[]> THANG = q.getResultList();

            session.close();

            for (Object[] a : THANG) {

                dataset.setValue(a[2].toString(), Integer.parseInt(a[0].toString()));

            }
            for (Object[] a : THANG) {

                dataset2.setValue(Integer.parseInt(a[0].toString()), a[0].toString(), "");

            }
        } catch (HibernateException hibernateException) {

        }
        //////////////
        JFreeChart chart = ChartFactory.createPieChart3D("Bang",
                dataset, true, true,
                false);
        ///////////////
        JFreeChart barChart = ChartFactory.createBarChart3D("Bang2",
                "Năm", "tổng tiền", dataset2,
                PlotOrientation.VERTICAL, true,
                true, false);
        //////////////////

//        CategoryPlot p1 = (CategoryPlot) barChart.getCategoryPlot();
//        p1.setRangeGridlinePaint(Color.ORANGE);
        ChartPanel chartPanel = new ChartPanel(chart);

        a.removeAll();

        a.add(chartPanel);

        a.updateUI();

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        DefaultPieDataset dataset = new DefaultPieDataset();

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            session.beginTransaction();

            Query q = session.createNativeQuery("select B.TenSP,Sum(A.SoLuong) from HoaDonChiTiet A , ChiTietDoGo B , HoaDon C\n"
                    + "where A.IdChiTietDoGo = B.Id and A.IdHoaDon = C.Id AND year(NgayThanhToan) = year(GETDATE()) AND C.TRANGTHAI >1\n"
                    + "group by B.Id,b.TenSP");

            List<Object[]> list = q.getResultList();

            session.close();

            for (Object[] a : list) {

                dataset.setValue(a[0].toString(), Integer.parseInt(a[1].toString()));

                System.out.println(a.toString());

            }
            for (Object[] a : list) {

                dataset2.setValue(Integer.parseInt(a[1].toString()), a[0].toString(), "");

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

            a.removeAll();

            a.add(chartPanel);

            a.updateUI();

        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MenuView v = new MenuView(IdNV, TenNV, CV);

        v.setLocationRelativeTo(null);

        v.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void EXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXActionPerformed
        // TODO add your handling code here:
        XSSFWorkbook w = new XSSFWorkbook();

        XSSFSheet sheet = w.createSheet("danhsachdt");

        XSSFRow r = null;

        Cell cell = null;

        r = sheet.createRow(0);//số dòng cách đầu ở excel

        cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Id HoaDon");

        cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Ma HoaDon");

        cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Ngay Thanh Toan");

        cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Tong Tien");
        
        cell = r.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Dt Nam");

        cell = r.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Dt Thang");

        cell = r.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Dt Ngay");

        cell = r.createCell(11, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Dt da chon");
        
         cell = r.createCell(12, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Tien Nhap Nam");

        cell = r.createCell(13, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Tien Nhap Thang");

        cell = r.createCell(14, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Tien Nhap Ngay");

        cell = r.createCell(15, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Tien Nhap da chon");

        List<ViewModelDoanhThuThongKe> v = im.getListDoanhthu();

        int s = v.size();
      
        
        for (int i = 0; i < s; i++) {

            ViewModelDoanhThuThongKe chh = v.get(i);

            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getId());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getMa());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getNgaytt());

            cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getTongtien());

        }
        
           cell = r.createCell(8, org.apache.poi.ss.usermodel.CellType.STRING);
        
        String sp = im.getDoanhthuTheoNam() + "";
        
        cell.setCellValue(sp);

        cell = r.createCell(9, org.apache.poi.ss.usermodel.CellType.STRING);
        
        String sp1 = im.getDoanhthutheoThang() + "";
        
        cell.setCellValue(sp1);

        cell = r.createCell(10, org.apache.poi.ss.usermodel.CellType.STRING);
        
        String sp2 = im.getDoanhtHUTHEOnGAY() + "";
        
        cell.setCellValue(sp2);

        java.util.Date dateBD = dateNgayBatdau.getDate();

        java.util.Date dateKT = DateNgayKetThuc.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String nbd = format.format(dateBD);

        String nkt = format.format(dateKT);

        int sp3 = im.getListTongtiendachon(nbd, nkt);
        
        cell = r.createCell(11, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue(sp3);
        
        cell = r.createCell(12, org.apache.poi.ss.usermodel.CellType.STRING);
        
        String sp10 = im.getsotiennhaptheonam() + "";
        
        cell.setCellValue(sp10);

        cell = r.createCell(13, org.apache.poi.ss.usermodel.CellType.STRING);
        
        String sp11 = im.getsotiennhaptheothang() + "";
        
        cell.setCellValue(sp11);

        cell = r.createCell(14, org.apache.poi.ss.usermodel.CellType.STRING);
        
        String sp22 = im.getsotiennhaptheoNgay() + "";
        
        cell.setCellValue(sp22);

      
        int sp33 = im.getListTongBoRaDaChon(nbd, nkt);
        
        cell = r.createCell(15, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue(sp33);

        JFileChooser chooser = new JFileChooser();// mở file lên 

        chooser.showOpenDialog(null);//để chọn lưu vào đâu

        File f = chooser.getSelectedFile();

        try {

            FileOutputStream f1 = new FileOutputStream(new File(f + ".xlsx"));

            w.write(f1);

            f1.close();

        } catch (Exception e) {

        }
        JOptionPane
                .showMessageDialog(this, "In thành công");


    }//GEN-LAST:event_EXActionPerformed

    private void EX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EX1ActionPerformed

        XSSFWorkbook w = new XSSFWorkbook();

        XSSFSheet sheet = w.createSheet("danhsachsp");

        XSSFRow r = null;

        Cell cell = null;

        r = sheet.createRow(0);//số dòng cách đầu ở excel

        cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Id sp");

        cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Ten sp");

        cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("So Luong Ton");

        cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("So Luong Ban");

        cell = r.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);

        cell.setCellValue("Tong Tien");

        

        List<ViewModelDoanhThu> v = im.getList1();

        int s = v.size();
        for (int i = 0; i < s; i++) {

            ViewModelDoanhThu chh = v.get(i);

            r = sheet.createRow(1 + i);

            cell = r.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(i + 1);

            cell = r.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getIdsp());

            cell = r.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getTensanpham());

            cell = r.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getSoluongton());

            cell = r.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getSoluongban());

            cell = r.createCell(5, org.apache.poi.ss.usermodel.CellType.STRING);

            cell.setCellValue(v.get(i).getTongtien());

        }
       

        JFileChooser chooser = new JFileChooser();// mở file lên 

        chooser.showOpenDialog(null);//để chọn lưu vào đâu

        File f = chooser.getSelectedFile();

        try {

            FileOutputStream f1 = new FileOutputStream(new File(f + ".xlsx"));

            w.write(f1);

            f1.close();

        } catch (Exception e) {

        }
        JOptionPane
                .showMessageDialog(this, "In thành công");


    }//GEN-LAST:event_EX1ActionPerformed

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
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoanhThuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

//                new DoanhThuView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgayKetThuc;
    private javax.swing.JButton EX;
    private javax.swing.JButton EX1;
    private javax.swing.JPanel a;
    private javax.swing.JComboBox<String> cbbdoanhthu;
    private com.toedter.calendar.JDateChooser dateNgayBatdau;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private pagination.Pagination pagination1;
    private javax.swing.JTable tbbangnhanhvien;
    private javax.swing.JTable tbbangsanpham;
    private javax.swing.JTable tbbangsanphamtop3;
    private javax.swing.JTable tbdongthuthongke;
    private javax.swing.JLabel txtdoanhthungay;
    private javax.swing.JLabel txtdoanhthutheonam;
    private javax.swing.JLabel txtdoanthudachon;
    private javax.swing.JLabel txtdoanthuthang;
    private javax.swing.JLabel txtsophanhethang;
    private javax.swing.JLabel txtsospdangkd;
    private javax.swing.JLabel txtsospngungkinhdoanh;
    private javax.swing.JLabel txtsospsaphethang;
    private javax.swing.JLabel txtsotiennhapdachon;
    private javax.swing.JLabel txtsotiennhapnam;
    private javax.swing.JLabel txtsotiennhapngay;
    private javax.swing.JLabel txtsotiennhaptheothang;
    private javax.swing.JButton txttimkiem;
    // End of variables declaration//GEN-END:variables
}
