/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.galinfo.galipos.models;

import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.galinfo.galipos.Database;
import xyz.galinfo.galipos.GaliPOS;

/**
 *
 * @author janud
 */
public class Transaksi {

  private long id;
  private String tipe;
  private String kode;
  private String waktu;
  private int idOperator;
  private int idBuyer;
  private int idSupplier;
  private double total;
  private double diskon;
  private double grandTotal;
  private double terbayar;
  private ArrayList<ItemTransaksi> items;
  private String keterangan;
  private String status;
  private String createdAt;
  private String updatedAt;

  public Transaksi(String tipe, String waktu, int idOperator, int idBuyer, int idSupplier, double total, double diskon, double grandTotal, double terbayar, String keterangan, String status) {
    this.tipe = tipe;
    this.waktu = waktu;
    this.idOperator = idOperator;
    this.idBuyer = idBuyer;
    this.idSupplier = idSupplier;
    this.total = total;
    this.diskon = diskon;
    this.grandTotal = grandTotal;
    this.terbayar = terbayar;
    this.keterangan = keterangan;
    this.status = status;
  }

  public Transaksi(String tipe, String waktu, int idOperator, int idBuyer, int idSupplier, double total, double diskon, double grandTotal, double terbayar, ArrayList<ItemTransaksi> items, String keterangan, String status) {
    this.tipe = tipe;
    this.waktu = waktu;
    this.idOperator = idOperator;
    this.idBuyer = idBuyer;
    this.idSupplier = idSupplier;
    this.total = total;
    this.diskon = diskon;
    this.grandTotal = grandTotal;
    this.terbayar = terbayar;
    this.items = items;
    this.keterangan = keterangan;
    this.status = status;
  }

  public Transaksi(long id, String kode, String waktu, int idOperator, int idBuyer, int idSupplier, double total, double diskon, double grandTotal, double terbayar, String keterangan, String status, String createdAt, String updatedAt) {
    this.id = id;
    this.kode = kode;
    this.waktu = waktu;
    this.idOperator = idOperator;
    this.idBuyer = idBuyer;
    this.idSupplier = idSupplier;
    this.total = total;
    this.diskon = diskon;
    this.grandTotal = grandTotal;
    this.terbayar = terbayar;
    this.keterangan = keterangan;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public ArrayList<ItemTransaksi> getItems() {
    return items;
  }

  public void setItems(ArrayList<ItemTransaksi> items) {
    this.items = items;
  }

  public int getIdSupplier() {
    return idSupplier;
  }

  public void setIdSupplier(int idSupplier) {
    this.idSupplier = idSupplier;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getKode() {
    return kode;
  }

  public void setKode(String kode) {
    this.kode = kode;
  }

  public String getWaktu() {
    return waktu;
  }

  public void setWaktu(String waktu) {
    this.waktu = waktu;
  }

  public int getIdOperator() {
    return idOperator;
  }

  public void setIdOperator(int idOperator) {
    this.idOperator = idOperator;
  }

  public int getIdBuyer() {
    return idBuyer;
  }

  public void setIdBuyer(int idBuyer) {
    this.idBuyer = idBuyer;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public double getDiskon() {
    return diskon;
  }

  public void setDiskon(double diskon) {
    this.diskon = diskon;
  }

  public double getGrandTotal() {
    return grandTotal;
  }

  public void setGrandTotal(double grandTotal) {
    this.grandTotal = grandTotal;
  }

  public double getTerbayar() {
    return terbayar;
  }

  public void setTerbayar(double terbayar) {
    this.terbayar = terbayar;
  }

  public String getKeterangan() {
    return keterangan;
  }

  public void setKeterangan(String keterangan) {
    this.keterangan = keterangan;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getTipe() {
    return tipe;
  }

  public void setTipe(String tipe) {
    this.tipe = tipe;
  }

  @Override
  public String toString() {
    return "Transaksi{" + "id=" + id + ", kode=" + kode + ", waktu=" + waktu + ", idOperator=" + idOperator + ", idBuyer=" + idBuyer + ", idSupplier=" + idSupplier + ", amount=" + total + ", discount=" + diskon + ", total=" + grandTotal + ", paid=" + terbayar + ", keterangan=" + keterangan + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
  }

  public static ArrayList<Transaksi> getByTanggal(String tanggal1, String tanggal2) {
    ArrayList<Transaksi> transaksis = new ArrayList<>();
    Database db = new Database();
    String sql = "select tr.* from transaksi tr where tr.waktu>='" + tanggal1 + " 00:00:00' and tr.waktu<='" + tanggal2 + " 23:59:59' order by tr.waktu asc";
    try {
      ResultSet rs = db.connection.createStatement().executeQuery(sql);
      while (rs.next()) {
        transaksis.add(rsProcessor(rs));
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
    return transaksis;
  }

  public static void initTable() {
    Database db = new Database();
    String sql = "create table if not exists transaksi("
            + "id integer not null primary key autoincrement,"
            + "tipe text not null default 'jual',"
            + "kode text null default null,"
            + "waktu text not null,"
            + "id_operator integer not null,"
            + "id_buyer integer null default null,"
            + "id_supplier integer null default null,"
            + "total real not null default 0,"
            + "diskon real not null default 0,"
            + "grand_total real not null default 0,"
            + "terbayar real not null default 0,"
            + "keterangan text null default null,"
            + "status text not null default 'unpaid',"
            + "created_at text not null,"
            + "updated_at text not null,"
            + "foreign key(id_operator) references user(id) on delete restrict on update cascade,"
            + "foreign key(id_buyer) references user(id) on delete restrict on update cascade,"
            + "foreign key(id_supplier) references user(id) on delete restrict on update cascade"
            + ")";
    try {
      db.connection.createStatement().execute(sql);
      System.out.println("Init table transaksi done.");
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Transaksi rsProcessor(ResultSet rs) {
    Transaksi transaksi = null;
    try {
      transaksi = new Transaksi(rs.getLong("id"), rs.getString("kode"), rs.getString("waktu"), rs.getInt("id_operator"), rs.getInt("id_buyer"), rs.getInt("id_supplier"), rs.getDouble("total"), rs.getDouble("diskon"), rs.getDouble("grand_total"), rs.getDouble("terbayar"), rs.getString("keterangan"), rs.getString("status"), rs.getString("created_at"), rs.getString("updated_at"));
    } catch (SQLException ex) {
      Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
    }
    return transaksi;
  }

  public Transaksi save() {
    Transaksi transaksi = null;
    String timestamp = GaliPOS.getTimeStamp(1, null);
    Database db = new Database();
    String sql = "insert into transaksi(tipe,waktu,id_operator,id_buyer,id_supplier,total,diskon,grand_total,terbayar,status,created_at,updated_at) "
            + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
      PreparedStatement pstmt = db.connection.prepareStatement(sql);
      pstmt.setString(1, this.getTipe());
      pstmt.setString(2, this.getWaktu());
      pstmt.setInt(3, this.getIdOperator());
      if (this.getIdBuyer() > 0) {
        pstmt.setInt(4, this.getIdBuyer());
      } else {
        pstmt.setNull(4, Types.INTEGER);
      }
      if (this.getIdSupplier() > 0) {
        pstmt.setInt(5, this.getIdSupplier());
      } else {
        pstmt.setNull(5, Types.INTEGER);
      }
      pstmt.setDouble(6, this.getTotal());
      pstmt.setDouble(7, this.getDiskon());
      pstmt.setDouble(8, this.getGrandTotal());
      pstmt.setDouble(9, this.getTerbayar());
      pstmt.setString(10, this.getStatus());
      pstmt.setString(11, timestamp);
      pstmt.setString(12, timestamp);
      if (pstmt.executeUpdate() > 0) {
        ResultSet rs = db.connection.createStatement().executeQuery("select * from transaksi order by id desc limit 1");
        if (rs.next()) {
          transaksi = rsProcessor(rs);
          String newKode = "TRX" + (new Random().nextInt(999 - 111 + 1) + 111) + String.format("%04d", rs.getInt("id"));
          transaksi.setKode(newKode);
          db.connection.createStatement().executeUpdate("update transaksi set kode='" + newKode + "' where id=" + rs.getInt("id"));
          int iterator1 = 1;
          for (ItemTransaksi item : items) {
//            System.out.println(item.toString());
            item.setIdTransaksi(transaksi.getId());
            item.setKode(newKode + String.format("%03d", iterator1));
            item.save(db.connection);
            iterator1++;
          }
          Log log = new Log(GaliPOS.sessionUser.getId(), "create", null, "transaksi", rs.getInt("id") + "", new Gson().toJson(transaksi), "sukses");
          log.save(db.connection);
        }
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
    return transaksi;
  }
}
