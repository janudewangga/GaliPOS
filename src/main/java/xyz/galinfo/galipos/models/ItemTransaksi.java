/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.galinfo.galipos.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.galinfo.galipos.Database;
import xyz.galinfo.galipos.GaliPOS;

/**
 *
 * @author janud
 */
public class ItemTransaksi {

  private long id;
  private long idTransaksi;
  private String kode;
  private String kodeProduk;
  private String namaProduk;
  private int idProduk;
  private double jumlah;
  private double harga;
  private double total;
  private double diskonItem;
  private double grandTotal;
  private String createdAt;
  private String updatedAt;

  public ItemTransaksi(int idProduk, String kodeProduk, String namaProduk, double jumlah, double harga, double total, double diskonItem, double grandTotal) {
    this.idProduk = idProduk;
    this.kodeProduk = kodeProduk;
    this.namaProduk = namaProduk;
    this.jumlah = jumlah;
    this.harga = harga;
    this.total = total;
    this.diskonItem = diskonItem;
    this.grandTotal = grandTotal;
  }

  public ItemTransaksi(long idTransaksi, String kode, int idProduk, double jumlah, double harga, double total, double diskonItem, double grandTotal) {
    this.idTransaksi = idTransaksi;
    this.kode = kode;
    this.idProduk = idProduk;
    this.jumlah = jumlah;
    this.harga = harga;
    this.total = total;
    this.diskonItem = diskonItem;
    this.grandTotal = grandTotal;
  }

  public ItemTransaksi(long id, long idTransaksi, String kode, int idProduk, double jumlah, double harga, double total, double diskonItem, double grandTotal, String createdAt, String updatedAt) {
    this.id = id;
    this.idTransaksi = idTransaksi;
    this.kode = kode;
    this.idProduk = idProduk;
    this.jumlah = jumlah;
    this.harga = harga;
    this.total = total;
    this.diskonItem = diskonItem;
    this.grandTotal = grandTotal;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getNamaProduk() {
    return namaProduk;
  }

  public void setNamaProduk(String namaProduk) {
    this.namaProduk = namaProduk;
  }

  public String getKodeProduk() {
    return kodeProduk;
  }

  public void setKodeProduk(String kodeProduk) {
    this.kodeProduk = kodeProduk;
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

  public int getIdProduk() {
    return idProduk;
  }

  public void setIdProduk(int idProduk) {
    this.idProduk = idProduk;
  }

  public double getJumlah() {
    return jumlah;
  }

  public void setJumlah(double jumlah) {
    this.jumlah = jumlah;
  }

  public double getHarga() {
    return harga;
  }

  public void setHarga(double harga) {
    this.harga = harga;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public double getDiskonItem() {
    return diskonItem;
  }

  public void setDiskonItem(double diskonItem) {
    this.diskonItem = diskonItem;
  }

  public double getGrandTotal() {
    return grandTotal;
  }

  public void setGrandTotal(double grandTotal) {
    this.grandTotal = grandTotal;
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

  public long getIdTransaksi() {
    return idTransaksi;
  }

  public void setIdTransaksi(long idTransaksi) {
    this.idTransaksi = idTransaksi;
  }

  @Override
  public String toString() {
    return "ItemTransaksi{" + "id=" + id + ", idTransaksi=" + idTransaksi + ", kode=" + kode + ", kodeProduk=" + kodeProduk + ", namaProduk=" + namaProduk + ", idProduk=" + idProduk + ", jumlah=" + jumlah + ", harga=" + harga + ", total=" + total + ", diskonItem=" + diskonItem + ", grandTotal=" + grandTotal + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
  }

  public static void initTable() {
    Database db = new Database();
    String sql = "create table if not exists item_transaksi("
            + "id integer not null primary key autoincrement,"
            + "id_transaksi integer not null,"
            + "kode text null default null,"
            + "id_produk integer not null,"
            + "jumlah real not null default 0,"
            + "harga real not null default 0,"
            + "total real not null default 0,"
            + "diskon_item real not null default 0,"
            + "grand_total real not null default 0,"
            + "created_at text not null,"
            + "updated_at text not null,"
            + "foreign key(id_transaksi) references transaksi(id) on delete restrict on update cascade,"
            + "foreign key(id_produk) references produk(id) on delete restrict on update cascade"
            + ")";
    try {
      db.connection.createStatement().execute(sql);
      System.out.println("Init table item_transaksi done.");
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ItemTransaksi rsProcessor(ResultSet rs) {
    try {
      return new ItemTransaksi(rs.getLong("id"), rs.getLong("id_transaksi"), rs.getString("kode"), rs.getInt("id_produk"), rs.getDouble("jumlah"), rs.getDouble("harga"), rs.getDouble("total"), rs.getDouble("diskon_item"), rs.getDouble("grand_total"), rs.getString("created_at"), rs.getString("updated_at"));
    } catch (SQLException ex) {
      Logger.getLogger(ItemTransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public ItemTransaksi save(Connection dbConn) {
    ItemTransaksi itemTransaksi = null;
    if (dbConn == null) {
      Database db = new Database();
      dbConn = db.connection;
    }
    String sql = "insert into item_transaksi(id_transaksi,kode,id_produk,jumlah,harga,total,diskon_item,grand_total,created_at,updated_at) "
            + "values(?,?,?,?,?,?,?,?,?,?)";
    String timestamp = GaliPOS.getTimeStamp(1, null);
    try {
      PreparedStatement pstmt = dbConn.prepareStatement(sql);
      pstmt.setLong(1, this.idTransaksi);
      pstmt.setString(2, this.kode);
      pstmt.setInt(3, this.idProduk);
      pstmt.setDouble(4, this.jumlah);
      pstmt.setDouble(5, this.harga);
      pstmt.setDouble(6, this.total);
      pstmt.setDouble(7, this.diskonItem);
      pstmt.setDouble(8, this.grandTotal);
      pstmt.setString(9, timestamp);
      pstmt.setString(10, timestamp);
      if (pstmt.executeUpdate() > 0) {
        ResultSet rs = dbConn.createStatement().executeQuery("select it.* from item_transaksi it where it.kode='" + this.kode + "'");
        if (rs.next()) {
          itemTransaksi = rsProcessor(rs);
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(ItemTransaksi.class.getName()).log(Level.SEVERE, null, ex);
    }
    return itemTransaksi;
  }
}
