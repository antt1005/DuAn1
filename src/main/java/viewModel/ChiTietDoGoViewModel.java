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
public class ChiTietDoGoViewModel {
    private String id;
    private String tensp;
    private String sp;
    private String load;
    private String donggo;
    private String ncc;
    private String nguongoc;
    private String donvi;
    private String mota;
    private int soluong;
    private BigDecimal giaNhap;
    private BigDecimal GiaBan;

    public ChiTietDoGoViewModel(String id, String tensp, String sp, String load, String donggo, String ncc, String nguongoc, String donvi, String mota, int soluong, BigDecimal giaNhap, BigDecimal GiaBan) {
        this.id = id;
        this.tensp = tensp;
        this.sp = sp;
        this.load = load;
        this.donggo = donggo;
        this.ncc = ncc;
        this.nguongoc = nguongoc;
        this.donvi = donvi;
        this.mota = mota;
        this.soluong = soluong;
        this.giaNhap = giaNhap;
        this.GiaBan = GiaBan; 
        
        
        
    }

    public ChiTietDoGoViewModel() {
        
    }

    public String getId() {
        
        return id;
    }

    public void setId(String id) {
        
        this.id = id;
    }

    public String getTensp() {
        
        return tensp;
    }

    public void setTensp(String tensp) {
        
        this.tensp = tensp;
    }

    public String getSp() {
        
        return sp;
    }

    public void setSp(String sp) {
        
        this.sp = sp;
    }

    public String getLoad() {
        
        return load;
    }

    public void setLoad(String load) {
        
        this.load = load;
    }

    public String getDonggo() {
        
        return donggo;
    }

    public void setDonggo(String donggo) {
        
        this.donggo = donggo;
    }

    public String getNcc() {
        
        return ncc;
    }

    public void setNcc(String ncc) {
        
        this.ncc = ncc;
    }

    public String getNguongoc() {
        
        return nguongoc;
    }

    public void setNguongoc(String nguongoc) {
        
        this.nguongoc = nguongoc;
    }

    public String getDonvi() {
        
        return donvi;
    }

    public void setDonvi(String donvi) {
        
        this.donvi = donvi;
    }

    public String getMota() {
        
        return mota;
    }

    public void setMota(String mota) {
        
        this.mota = mota;
    }

    public int getSoluong() {
        
        return soluong;
    }

    public void setSoluong(int soluong) {
        
        this.soluong = soluong;
    }

    public BigDecimal getGiaNhap() {
        
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        
        return GiaBan;
    }

    public void setGiaBan(BigDecimal GiaBan) {
        
        this.GiaBan = GiaBan;
    }

    @Override
    public String toString() {
        
        return "ChiTietDoGoViewModel{" + "id=" + id + ", tensp=" + tensp + ", sp=" + sp + ", load=" + load + ", donggo=" + donggo + ", ncc=" + ncc + ", nguongoc=" + nguongoc + ", donvi=" + donvi + ", mota=" + mota + ", soluong=" + soluong + ", giaNhap=" + giaNhap + ", GiaBan=" + GiaBan + '}';
    }

   

    
}
