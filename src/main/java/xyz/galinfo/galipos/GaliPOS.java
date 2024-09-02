/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package xyz.galinfo.galipos;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import xyz.galinfo.galipos.models.ItemTransaksi;
import xyz.galinfo.galipos.models.Log;
import xyz.galinfo.galipos.models.Produk;
import xyz.galinfo.galipos.models.Transaksi;
import xyz.galinfo.galipos.models.User;

/**
 *
 * @author janud
 */
public class GaliPOS {

  public static User sessionUser;

  public static void main(String[] args) {
    try {
      //    try {
//      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//      Logger.getLogger(GaliPOS.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//      if ("Nimbus".equals(info.getName())) {
//        try {
//          UIManager.setLookAndFeel(info.getClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//          Logger.getLogger(GaliPOS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        break;
//      }
//    }
      UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(GaliPOS.class.getName()).log(Level.SEVERE, null, ex);
    }
//    initData();
    FMasuk fMasuk = new FMasuk();
    fMasuk.setVisible(true);
//    sessionUser = User.getByUsername("admin", 0);
//    FMain fMain = new FMain();
//    fMain.setVisible(true);
  }

  public static String getTimeStamp(int mode, String timeString) {
    String result = null;
    switch (mode) {
      case 1:
        result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        break;
      case 2:
        result = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        break;
      default:
        throw new AssertionError();
    }
    return result;
  }

  private static void initData() {
    User.initTable();
    Log.initTable();
    Produk.initTable();
    Transaksi.initTable();
    ItemTransaksi.initTable();
    System.out.println("Init done.");
  }
}
