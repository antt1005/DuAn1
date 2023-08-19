/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;



/**
 *
 * @author Admin
 */
public class ViewModelBAOHANHCHITIET {
    private String idbh;
    private String idsp;
    private String tensp;
    private int soluong;

    public ViewModelBAOHANHCHITIET() {
        
    }

    public ViewModelBAOHANHCHITIET(String idbh, String idsp, String tensp, int soluong) {
        this.idbh = idbh;
        this.idsp = idsp;
        this.tensp = tensp;
        this.soluong = soluong;
    }

    public String getIdbh() {
        
        return idbh;
    }

    public void setIdbh(String idbh) {
        
        this.idbh = idbh;
    }

    public String getIdsp() {
        
        return idsp;
    }

    public void setIdsp(String idsp) {
        
        this.idsp = idsp;
    }

    public String getTensp() {
        
        return tensp;
    }

    public void setTensp(String tensp) {
        
        this.tensp = tensp;
    }

    public int getSoluong() {
        
        return soluong;
    }

    public void setSoluong(int soluong) {
        
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        
        return "ViewModelBAOHANHCHITIET{" + "idbh=" + idbh + ", idsp=" + idsp + ", tensp=" + tensp + ", soluong=" + soluong + '}';
    }

    
    
}
