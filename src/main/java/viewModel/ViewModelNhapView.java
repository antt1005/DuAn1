/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ViewModelNhapView {

    private String id;
    private String idsp;
    private String Tensp;
    private int slNhap;
    private String NgayNhap;
    private BigDecimal TongTien;

    public ViewModelNhapView() {
        
    }

    public ViewModelNhapView(String id, String idsp, String Tensp, int slNhap, String NgayNhap, BigDecimal TongTien) {
        this.id = id;
        this.idsp = idsp;
        this.Tensp = Tensp;
        this.slNhap = slNhap;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
    }

    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getIdsp() {
        
        return idsp;
    }

    public void setIdsp(String idsp) {
        
        this.idsp = idsp;
    }

    public String getTensp() {
        
        return Tensp;
    }

    public void setTensp(String Tensp) {
        
        this.Tensp = Tensp;
    }

    public int getSlNhap() {
        
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        
        this.slNhap = slNhap;
    }

    public String getNgayNhap() {
        
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
        
        this.NgayNhap = NgayNhap;
    }

    public BigDecimal getTongTien() {
        
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        
        this.TongTien = TongTien;
    }

    @Override
    public String toString() {
        
        return "ViewModelNhapView{" + "id=" + id + ", idsp=" + idsp + ", Tensp=" + Tensp + ", slNhap=" + slNhap + ", NgayNhap=" + NgayNhap + ", TongTien=" + TongTien + '}';
    }

}
