/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel.test;

/**
 *
 * @author PC
 */
public class ViewModelCuaHangtesst {

    private String Id;
    private String Ma;
    private String TenCuaHang;
    private String DiaChi;
    private String TrangThai;

    public ViewModelCuaHangtesst() {
    }

    public ViewModelCuaHangtesst(String Id, String Ma, String TenCuaHang, String DiaChi, String TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.TenCuaHang = TenCuaHang;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String TenCuaHang) {
        this.TenCuaHang = TenCuaHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "ViewModelCuaHangtesst{" + "Id=" + Id + ", Ma=" + Ma + ", TenCuaHang=" + TenCuaHang + ", DiaChi=" + DiaChi + ", TrangThai=" + TrangThai + '}';
    }

}
