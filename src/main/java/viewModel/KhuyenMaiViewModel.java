/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author ktkha
 */
public class KhuyenMaiViewModel {
    private String id;
    private String ma;
    private String tenKhuyenMai;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int PhanTramKM;

    public KhuyenMaiViewModel() {
        
    }

    public KhuyenMaiViewModel(String id, String ma, String tenKhuyenMai, String ngayBatDau, String ngayKetThuc, int PhanTramKM) {
        this.id = id;
        this.ma = ma;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.PhanTramKM = PhanTramKM;
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

    public String getTenKhuyenMai() {
        
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getNgayBatDau() {
        
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getPhanTramKM() {
        
        return PhanTramKM;
    }

    public void setPhanTramKM(int PhanTramKM) {
        
        this.PhanTramKM = PhanTramKM;
    }

    @Override
    public String toString() {
        
        return "KhuyenMaiViewModel{" + "id=" + id + ", ma=" + ma + ", tenKhuyenMai=" + tenKhuyenMai + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", PhanTramKM=" + PhanTramKM + '}';
    }

   
}
