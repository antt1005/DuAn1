/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
public class CreateQR {

    public static void main(String[] args) throws WriterException, IOException {
        String data = "654D333F-5868-4B10-ADD3-0DE5673F107B";
        String path = "C:\\Users\\Admin\\OneDrive\\Máy tính\\Java Thu Muc\\QR.jpg";

        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, 500, 500);

        MatrixToImageWriter.writeToPath(matrix,
                "jpg", Paths.get(path));
    }

}
