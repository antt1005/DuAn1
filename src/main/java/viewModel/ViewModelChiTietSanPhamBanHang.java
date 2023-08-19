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
public class ViewModelChiTietSanPhamBanHang {
    private String id;
    private String tenSp;
    private String sanPham;
    private String loaiSP;
    private String dongGo;
    private String nhaCungCap;
    private String nguonGoc;
    private String donViTinh;
    private int soLuong;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private String moTa;
    private int trangThai;

    public ViewModelChiTietSanPhamBanHang() {
        
    }

    public ViewModelChiTietSanPhamBanHang(String id, String tenSp, String sanPham, String loaiSP, String dongGo, String nhaCungCap, String nguonGoc, String donViTinh, int soLuong, BigDecimal giaNhap, BigDecimal giaBan, String moTa, int trangThai) {
        this.id = id;
        this.tenSp = tenSp;
        this.sanPham = sanPham;
        this.loaiSP = loaiSP;
        this.dongGo = dongGo;
        this.nhaCungCap = nhaCungCap;
        this.nguonGoc = nguonGoc;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getTenSp() {
        
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        
        this.tenSp = tenSp;
    }

    public String getSanPham() {
        
        return sanPham;
    }

    public void setSanPham(String sanPham) {
        
        this.sanPham = sanPham;
    }

    public String getLoaiSP() {
        
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        
        this.loaiSP = loaiSP;
    }

    public String getDongGo() {
        
        return dongGo;
    }

    public void setDongGo(String dongGo) {
        
        this.dongGo = dongGo;
    }

    public String getNhaCungCap() {
        
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        
        this.nhaCungCap = nhaCungCap;
    }

    public String getNguonGoc() {
        
        return nguonGoc;
    }

    public void setNguonGoc(String nguonGoc) {
        
        this.nguonGoc = nguonGoc;
    }

    public String getDonViTinh() {
        
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        
        this.donViTinh = donViTinh;
    }

    public int getSoLuong() {
        
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaNhap() {
        
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        
        return moTa;
    }

    public void setMoTa(String moTa) {
        
        this.moTa = moTa;
    }

    public int getTrangThai() {
        
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelChiTietSanPhamBanHang{" + "id=" + id + ", tenSp=" + tenSp + ", sanPham=" + sanPham + ", loaiSP=" + loaiSP + ", dongGo=" + dongGo + ", nhaCungCap=" + nhaCungCap + ", nguonGoc=" + nguonGoc + ", donViTinh=" + donViTinh + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", moTa=" + moTa + ", trangThai=" + trangThai + '}';
    }
    
    
    
}
