/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String Id;
    private String Ma;
    private String TenKhachHang;
    private String Sdt;
    private String DiaChi;
    private int TrangThai;
    
   @OneToMany(mappedBy = "IdKH")
    private Set<BaoHanh> BaoHanhs;
   
   @OneToMany(mappedBy = "IdKhachHang")
    private Set<HoaDon> HaoDons;

    
    public KhachHang(String Id, String Ma, String TenKhachHang, String Sdt, String DiaChi, int TrangThai) {
        this.Id = Id;
        this.Ma = Ma;
        this.TenKhachHang = TenKhachHang;
        this.Sdt = Sdt;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public KhachHang() {
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

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "Id=" + Id + ", Ma=" + Ma + ", TenKhachHang=" + TenKhachHang + ", Sdt=" + Sdt + ", DiaChi=" + DiaChi + ", TrangThai=" + TrangThai + '}';
    }

}
