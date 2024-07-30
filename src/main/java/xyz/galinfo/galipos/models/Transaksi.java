/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.galinfo.galipos.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.galinfo.galipos.Database;

/**
 *
 * @author janud
 */
public class Transaksi {

  private long id;
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

  public Transaksi(String waktu, int idOperator, int idBuyer, int idSupplier, double total, double diskon, double grandTotal, double terbayar, String keterangan, String status) {
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

  public Transaksi(String waktu, int idOperator, int idBuyer, int idSupplier, double total, double diskon, double grandTotal, double terbayar, ArrayList<ItemTransaksi> items, String keterangan, String status) {
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

  @Override
  public String toString() {
    return "Transaksi{" + "id=" + id + ", kode=" + kode + ", waktu=" + waktu + ", idOperator=" + idOperator + ", idBuyer=" + idBuyer + ", idSupplier=" + idSupplier + ", amount=" + total + ", discount=" + diskon + ", total=" + grandTotal + ", paid=" + terbayar + ", keterangan=" + keterangan + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
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

  public Transaksi save() {
    Transaksi transaksi = null;
    return transaksi;
  }
}
