/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Admin
 */
public class ViewModelKhachHang {
    private  String ID;
    private String  ma;
    private String tenkh;
    private String sdt;
    private String diachi;
    private String Trangthai;

    public ViewModelKhachHang() {
        
    }

    public ViewModelKhachHang(String ID, String ma, String tenkh, String sdt, String diachi, String Trangthai) {
        this.ID = ID;
        this.ma = ma;
        this.tenkh = tenkh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.Trangthai = Trangthai;
    }

    public String getID() {
        
        return ID;
    }

    public void setID(String ID) {
        
        this.ID = ID;
    }

    public String getMa() {
        
        return ma;
    }

    public void setMa(String ma) {
        
        this.ma = ma;
    }

    public String getTenkh() {
        
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        
        this.tenkh = tenkh;
    }

    public String getSdt() {
        
        return sdt;
    }

    public void setSdt(String sdt) {
        
        this.sdt = sdt;
    }

    public String getDiachi() {
        
        return diachi;
    }

    public void setDiachi(String diachi) {
        
        this.diachi = diachi;
    }

    public String getTrangthai() {
        
        return Trangthai;
    }

    public void setTrangthai(String Trangthai) {
        
        this.Trangthai = Trangthai;
    }

    @Override
    public String toString() {
        
        return "ViewModelKhachHang{" + "ID=" + ID + ", ma=" + ma + ", tenkh=" + tenkh + ", sdt=" + sdt + ", diachi=" + diachi + ", Trangthai=" + Trangthai + '}';
    }
    
    
    
}
