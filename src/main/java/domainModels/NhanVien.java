/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String Id;
    private String Ma;
    private String HoTen;
    private String Sdt;
    private String DiaChi;
    private Date NgaySinh;
    @ManyToOne
    @JoinColumn(name = "IdCuaHang")
    private CuaHang IdCuaHang;
    @ManyToOne
    @JoinColumn(name = "IdChucVu")
    private ChucVu IdChucVu;
    private String MatKhau;
    private int TrangThai;
    private String Email;
    @OneToMany(mappedBy = "IdNhanVien")
    private Set<HoaDon> HaoDons;

    public NhanVien() {
    }

    public NhanVien(String Id, String Ma, String HoTen, String Sdt, String DiaChi, Date NgaySinh, CuaHang IdCuaHang, ChucVu IdChucVu, String MatKhau, int TrangThai, String Email) {
        this.Id = Id;
        this.Ma = Ma;
        this.HoTen = HoTen;
        this.Sdt = Sdt;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.IdCuaHang = IdCuaHang;
        this.IdChucVu = IdChucVu;
        this.MatKhau = MatKhau;
        this.TrangThai = TrangThai;
        this.Email = Email;
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

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
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

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public CuaHang getIdCuaHang() {
        return IdCuaHang;
    }

    public void setIdCuaHang(CuaHang IdCuaHang) {
        this.IdCuaHang = IdCuaHang;
    }

    public ChucVu getIdChucVu() {
        return IdChucVu;
    }

    public void setIdChucVu(ChucVu IdChucVu) {
        this.IdChucVu = IdChucVu;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "Id=" + Id + ", Ma=" + Ma + ", HoTen=" + HoTen + ", Sdt=" + Sdt + ", DiaChi=" + DiaChi + ", NgaySinh=" + NgaySinh + ", IdCuaHang=" + IdCuaHang + ", IdChucVu=" + IdChucVu + ", MatKhau=" + MatKhau + ", TrangThai=" + TrangThai + ", Email=" + Email + '}';
    }

}
