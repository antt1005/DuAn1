/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "HoaDon")
public class HoaDon implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String Id;
    private String Ma;
    private Date NgayTao;
    private Date NgayThanhToan;
    private int TrangThaiHoaDon;

    @ManyToOne
    @JoinColumn(name = "IdKhuyenMai")
    private KhuyenMai IdKhuyenMai;

    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang IdKhachHang;

    @ManyToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien IdNhanVien;

    private Date NgayNhan;
    private int TrangThai;
    private BigDecimal ThanhTien;
    @OneToMany(mappedBy = "IdHoaDon")
    private Set<HoaDonChiTiet> hoaDonChiTiets;

    public HoaDon(String Id, String Ma, Date NgayTao, Date NgayThanhToan, int TrangThaiHoaDon, KhuyenMai IdKhuyenMai, KhachHang IdKhachHang, NhanVien IdNhanVien, Date NgayNhan, int TrangThai,BigDecimal ThanhTien) {
        this.Id = Id;
        this.Ma = Ma;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TrangThaiHoaDon = TrangThaiHoaDon;
        this.IdKhuyenMai = IdKhuyenMai;
        this.IdKhachHang = IdKhachHang;
        this.IdNhanVien = IdNhanVien;
        this.NgayNhan = NgayNhan;
        this.TrangThai = TrangThai;
        this.ThanhTien = ThanhTien;
    }

    public HoaDon() {
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

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public int getTrangThaiHoaDon() {
        return TrangThaiHoaDon;
    }

    public void setTrangThaiHoaDon(int TrangThaiHoaDon) {
        this.TrangThaiHoaDon = TrangThaiHoaDon;
    }

    public KhuyenMai getIdKhuyenMai() {
        return IdKhuyenMai;
    }

    public void setIdKhuyenMai(KhuyenMai IdKhuyenMai) {
        this.IdKhuyenMai = IdKhuyenMai;
    }

    public KhachHang getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(KhachHang IdKhachHang) {
        this.IdKhachHang = IdKhachHang;
    }

    public NhanVien getIdNhanVien() {
        return IdNhanVien;
    }

    public void setIdNhanVien(NhanVien IdNhanVien) {
        this.IdNhanVien = IdNhanVien;
    }

    public Date getNgayNhan() {
        return NgayNhan;
    }

    public void setNgayNhan(Date NgayNhan) {
        this.NgayNhan = NgayNhan;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public BigDecimal getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(BigDecimal ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "Id=" + Id + ", Ma=" + Ma + ", NgayTao=" + NgayTao + ", NgayThanhToan=" + NgayThanhToan + ", TrangThaiHoaDon=" + TrangThaiHoaDon + ", IdKhuyenMai=" + IdKhuyenMai + ", IdKhachHang=" + IdKhachHang + ", IdNhanVien=" + IdNhanVien + ", NgayNhan=" + NgayNhan + ", TrangThai=" + TrangThai + ", ThanhTien=" + ThanhTien + '}';
    }
    
    

    

}
