/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositories.TimKiemCTSPRepository;
import services.impl.IManageTimKiemCTSPSService;
import viewModel.ChiTietDoGoViewModel;

/**
 *
 * @author ktkha
 */
public class TimKiemCTSPService implements IManageTimKiemCTSPSService {

    TimKiemCTSPRepository tkRp = new TimKiemCTSPRepository();

    @Override
    public List<ChiTietDoGoViewModel> timKiemPhanTrang(int i, int b, String ten) {
        List<Object[]> list = tkRp.timKiemPhanTrang(i, b, ten);

        List<ChiTietDoGoViewModel> ct = new ArrayList<>();
        for (Object[] a : list) {
            ChiTietDoGoViewModel x = new ChiTietDoGoViewModel();

            x.setId(a[0].toString());
            //
            x.setTensp(a[1].toString());

            x.setSp(a[2].toString());

            x.setLoad(a[3].toString());

            x.setDonggo(a[4].toString());

            x.setNcc(a[5].toString());

            x.setNguongoc(a[6].toString());

            x.setDonvi(a[7].toString());

            x.setMota(a[8].toString());

            x.setSoluong(Integer.parseInt(a[9].toString()));

            Double giaNhap = Double.parseDouble(a[10].toString());

            x.setGiaNhap(BigDecimal.valueOf(giaNhap));

            Double giaBan = Double.parseDouble(a[11].toString());

            x.setGiaBan(BigDecimal.valueOf(giaBan));

            ct.add(x);
        }
        return ct;
    }

    @Override
    public int row(String ten) {
        try {
            return tkRp.getRowTimKiem(ten);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        TimKiemCTSPService a = new TimKiemCTSPService();
        
    }

}
