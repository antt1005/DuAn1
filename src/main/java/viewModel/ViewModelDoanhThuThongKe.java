/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Admin
 */
public class ViewModelDoanhThuThongKe {
    private String id;
    private String ma;
    private String ngaytt;
    private String tongtien;

    public ViewModelDoanhThuThongKe() {
        
    }

    public ViewModelDoanhThuThongKe(String id, String ma, String ngaytt, String tongtien) {
        this.id = id;
        this.ma = ma;
        this.ngaytt = ngaytt;
        this.tongtien = tongtien;
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

    public String getNgaytt() {
        
        return ngaytt;
    }

    public void setNgaytt(String ngaytt) {
        
        this.ngaytt = ngaytt;
    }

    public String getTongtien() {
        
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        
        return "ViewModelDoanhThuThongKe{" + "id=" + id + ", ma=" + ma + ", ngaytt=" + ngaytt + ", tongtien=" + tongtien + '}';
    }
    
    
}
