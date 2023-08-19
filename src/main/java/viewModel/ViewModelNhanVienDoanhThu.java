/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Admin
 */
public class ViewModelNhanVienDoanhThu {
    private String id;
    private String ma;
    private String tennv;
    private String chucvu;
    private String hddalam;

    public ViewModelNhanVienDoanhThu() {
    }

    public ViewModelNhanVienDoanhThu(String id, String ma, String tennv, String chucvu, String hddalam) {
        this.id = id;
        this.ma = ma;
        this.tennv = tennv;
        this.chucvu = chucvu;
        this.hddalam = hddalam;
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

    public String getTennv() {
        
        return tennv;
    }

    public void setTennv(String tennv) {
        
        this.tennv = tennv;
    }

    public String getChucvu() {
        
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        
        this.chucvu = chucvu;
    }

    public String getHddalam() {
        
        return hddalam;
    }

    public void setHddalam(String hddalam) {
        
        this.hddalam = hddalam;
    }

    @Override
    public String toString() {
        return "ViewModelNhanVienDoanhThu{" + "id=" + id + ", ma=" + ma + ", tennv=" + tennv + ", chucvu=" + chucvu + ", hddalam=" + hddalam + '}';
    }
    
    
    
}
