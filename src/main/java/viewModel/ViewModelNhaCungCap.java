/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Phuong Bi
 */
public class ViewModelNhaCungCap {
    private String id;
    private String ma;
    private String tenNCC;
    private String diaChi;
    private String sdt;
    private String trangThai;

    public ViewModelNhaCungCap() {
        
    }

    public ViewModelNhaCungCap(String id, String ma, String tenNCC, String diaChi, String sdt, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trangThai = trangThai;
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

    public String getTenNCC() {
        
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        
        this.diaChi = diaChi;
    }

    public String getSdt() {
        
        return sdt;
    }

    public void setSdt(String sdt) {
        
        this.sdt = sdt;
    }

    public String getTrangThai() {
        
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        
        return "ViewModelNhaCungCap{" + "id=" + id + ", ma=" + ma + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", sdt=" + sdt + ", trangThai=" + trangThai + '}';
    }
    
    
}
