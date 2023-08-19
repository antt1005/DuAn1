/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Admin
 */
public class ViewModelDoanhThu {

    private String idsp;
    private String masp;
    private String tensanpham;
    private String soluongton;
    private String soluongban;
    private String tongtien;
    

    public ViewModelDoanhThu() {
        
    }

    public ViewModelDoanhThu(String idsp, String masp, String tensanpham, String soluongton, String soluongban, String tongtien) {
        this.idsp = idsp;
        this.masp = masp;
        this.tensanpham = tensanpham;
        this.soluongton = soluongton;
        this.soluongban = soluongban;
        this.tongtien = tongtien;
    }

    public String getIdsp() {
        
        return idsp;
    }

    public void setIdsp(String idsp) {
        
        this.idsp = idsp;
    }

    public String getMasp() {
        
        return masp;
    }

    public void setMasp(String masp) {
        
        this.masp = masp;
    }

    public String getTensanpham() {
        
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        
        this.tensanpham = tensanpham;
    }

    public String getSoluongton() {
        
        return soluongton;
    }

    public void setSoluongton(String soluongton) {
        
        this.soluongton = soluongton;
    }

    public String getSoluongban() {
        
        return soluongban;
    }

    public void setSoluongban(String soluongban) {
        
        this.soluongban = soluongban;
    }

    public String getTongtien() {
        
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        
        return "ViewModelDoanhThua{" + "idsp=" + idsp + ", masp=" + masp + ", tensanpham=" + tensanpham + ", soluongton=" + soluongton + ", soluongban=" + soluongban + ", tongtien=" + tongtien + '}';
    }

}
