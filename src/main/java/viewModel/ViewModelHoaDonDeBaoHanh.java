/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author ktkha
 */
public class ViewModelHoaDonDeBaoHanh {

    private String id;
    private String ma;
    private String ngayThanhToan;
    private String tenNV;
    private String idKH;
    private BigDecimal tongTien;
    private int SLBH;

    public ViewModelHoaDonDeBaoHanh() {
    }

    public ViewModelHoaDonDeBaoHanh(String id, String ma, String ngayThanhToan, String tenNV, String idKH, BigDecimal tongTien, int SLBH) {
        this.id = id;
        this.ma = ma;
        this.ngayThanhToan = ngayThanhToan;
        this.tenNV = tenNV;
        this.idKH = idKH;
        this.tongTien = tongTien;
        this.SLBH = SLBH;
    }

    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getMa() {
        
        return ma;
    }

    public void setMa(String ma) {
        
        this.ma = ma;
    }

    public String getNgayThanhToan() {
        
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getTenNV() {
        
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        
        this.tenNV = tenNV;
    }

    public String getIdKH() {
        
        return idKH;
    }

    public void setIdKH(String idKH) {
        
        this.idKH = idKH;
    }

    public BigDecimal getTongTien() {
        
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        
        this.tongTien = tongTien;
    }

    public int getSLBH() {
        
        return SLBH;
    }

    public void setSLBH(int SLBH) {
        
        this.SLBH = SLBH;
    }

    @Override
    public String toString() {
        
        return "ViewModelHoaDonDeBaoHanh{" + "id=" + id + ", ma=" + ma + ", ngayThanhToan=" + ngayThanhToan + ", tenNV=" + tenNV + ", idKH=" + idKH + ", tongTien=" + tongTien + ", SLBH=" + SLBH + '}';
    }

    
    
    

}
