/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ChiTietDoGo")
public class ChiTietDoGo implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private String Id;

    @ManyToOne
    @JoinColumn(name = "IdSanPham")
    private SanPham IdSanPham;

    @ManyToOne
    @JoinColumn(name = "IdLoaiSP")
    private LoaiSP IdLoaiSP;

    @ManyToOne
    @JoinColumn(name = "IdDongGo")
    private DongGo IdDongGo;

    @ManyToOne
    @JoinColumn(name = "IdNhaCungCap")
    private NhaCungCap IdNhaCungCap;

    @ManyToOne
    @JoinColumn(name = "IdNguonGoc")
    private NguonGoc IdNguonGoc;
// khoa phu cua don vi tinh
    // muon join voi donvitinh
    @ManyToOne
    @JoinColumn(name = "IdDonViTinh")
    private DonViTinh IdDonViTinh;

    private String TenSP;
    private int SoLuong;
    private BigDecimal GiaNhap;
    private BigDecimal GiaBan;
    private String MoTa;
    private int TrangThai;
    
    @OneToMany(mappedBy = "IdSpNhap")
    private Set<LichSuNhap> lichSuNhaps;
    
    @OneToMany(mappedBy = "IdSPBaoHanh")
    private Set<BaoHanh> BaoHanhs;

    @OneToMany(mappedBy = "IdChiTietDoGo")
    private Set<HoaDonChiTiet> hoaDonChiTiets;

    

    public ChiTietDoGo(String Id, SanPham IdSanPham, LoaiSP IdLoaiSP, DongGo IdDongGo, NhaCungCap IdNhaCungCap, NguonGoc IdNguocGoc, DonViTinh IdDonViTinh, String TenSP, int SoLuong, BigDecimal GiaNhap, BigDecimal GiaBan, String MoTa, int TrangThai) {
        this.Id = Id;
        this.IdSanPham = IdSanPham;
        this.IdLoaiSP = IdLoaiSP;
        this.IdDongGo = IdDongGo;
        this.IdNhaCungCap = IdNhaCungCap;
        this.IdNguonGoc = IdNguocGoc;
        this.IdDonViTinh = IdDonViTinh;
        this.TenSP = TenSP;
        this.SoLuong = SoLuong;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.MoTa = MoTa;
        this.TrangThai = TrangThai;
    }

    public ChiTietDoGo() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public SanPham getIdSanPham() {
        return IdSanPham;
    }

    public void setIdSanPham(SanPham IdSanPham) {
        this.IdSanPham = IdSanPham;
    }

    public LoaiSP getIdLoaiSP() {
        return IdLoaiSP;
    }

    public void setIdLoaiSP(LoaiSP IdLoaiSP) {
        this.IdLoaiSP = IdLoaiSP;
    }

    public DongGo getIdDongGo() {
        return IdDongGo;
    }

    public void setIdDongGo(DongGo IdDongGo) {
        this.IdDongGo = IdDongGo;
    }

    public NhaCungCap getIdNhaCungCap() {
        return IdNhaCungCap;
    }

    public void setIdNhaCungCap(NhaCungCap IdNhaCungCap) {
        this.IdNhaCungCap = IdNhaCungCap;
    }

    public NguonGoc getIdNguocGoc() {
        return IdNguonGoc;
    }

    public void setIdNguocGoc(NguonGoc IdNguocGoc) {
        this.IdNguonGoc = IdNguocGoc;
    }

    public DonViTinh getIdDonViTinh() {
        return IdDonViTinh;
    }

    public void setIdDonViTinh(DonViTinh IdDonViTinh) {
        this.IdDonViTinh = IdDonViTinh;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(BigDecimal GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public BigDecimal getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(BigDecimal GiaBan) {
        this.GiaBan = GiaBan;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "ChiTietDoGo{" + "Id=" + Id + ", IdSanPham=" + IdSanPham + ", IdLoaiSP=" + IdLoaiSP + ", IdDongGo=" + IdDongGo + ", IdNhaCungCap=" + IdNhaCungCap + ", IdNguocGoc=" + IdNguonGoc + ", IdDonViTinh=" + IdDonViTinh + ", TenSP=" + TenSP + ", SoLuong=" + SoLuong + ", GiaNhap=" + GiaNhap + ", GiaBan=" + GiaBan + ", MoTa=" + MoTa + ", TrangThai=" + TrangThai + '}';
    }

}
