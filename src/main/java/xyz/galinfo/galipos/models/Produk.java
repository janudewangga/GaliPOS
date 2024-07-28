/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.galinfo.galipos.models;

import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.galinfo.galipos.Database;
import xyz.galinfo.galipos.GaliPOS;

/**
 *
 * @author janud
 */
public class Produk {

  private int id;
  private String kode;
  private String nama;
  private String satuan;
  private double harga;
  private double stok;
  private double stokKritis;
  private String status;
  private String createdAt;
  private String updatedAt;

  public Produk() {
  }

  public Produk(String kode, String nama, String satuan, double harga, double stok, double stokKritis, String status) {
    this.kode = kode;
    this.nama = nama;
    this.satuan = satuan;
    this.harga = harga;
    this.stok = stok;
    this.stokKritis = stokKritis;
    this.status = status;
  }

  public Produk(int id, String kode, String nama, String satuan, double harga, double stok, double stokKritis, String status, String createdAt, String updatedAt) {
    this.id = id;
    this.kode = kode;
    this.nama = nama;
    this.satuan = satuan;
    this.harga = harga;
    this.stok = stok;
    this.stokKritis = stokKritis;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getKode() {
    return kode;
  }

  public void setKode(String kode) {
    this.kode = kode;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getSatuan() {
    return satuan;
  }

  public void setSatuan(String satuan) {
    this.satuan = satuan;
  }

  public double getHarga() {
    return harga;
  }

  public void setHarga(double harga) {
    this.harga = harga;
  }

  public double getStok() {
    return stok;
  }

  public void setStok(double stok) {
    this.stok = stok;
  }

  public double getStokKritis() {
    return stokKritis;
  }

  public void setStokKritis(double stokKritis) {
    this.stokKritis = stokKritis;
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
    return "Produk{" + "id=" + id + ", kode=" + kode + ", nama=" + nama + ", satuan=" + satuan + ", harga=" + harga + ", stok=" + stok + ", stokKritis=" + stokKritis + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
  }

  public Produk delete() {
    Produk produk = null;
    String sql = "delete from produk where id=?";
    Database db = new Database();
    try {
      PreparedStatement pstmt = db.connection.prepareStatement(sql);
      pstmt.setInt(1, this.getId());
      if (pstmt.executeUpdate() > 0) {
        produk = this;
        Log log = new Log(GaliPOS.sessionUser.getId(), "delete", null, "user", this.getId() + "", new Gson().toJson(this), "sukses");
        log.save(db.connection);
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
    }
    return produk;
  }

  public static ArrayList<Produk> getAll(String columnName, String qWord) {
    ArrayList<Produk> produks = new ArrayList<>();
    Database db = new Database();
    String sql = "select p.* from produk p order by p.nama asc";
    if (columnName != null && qWord != null && columnName.length() > 0 && qWord.length() > 0) {
      if (columnName.equals("combined")) {
        sql = "select p.* from produk p where p.kode like '%" + qWord + "%' or p.nama like '%" + qWord + "%' order by p.nama asc";
      } else {
        sql = "select p.* from produk p where p." + columnName + " like '%" + qWord + "%' order by p.nama asc";
      }
    }
    try {
      ResultSet rs = db.connection.createStatement().executeQuery(sql);
      while (rs.next()) {
        produks.add(rsProcessor(rs));
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
    }
    return produks;
  }

  public static Produk getByKode(String kode, int idProduk) {
    Database db = new Database();
    Produk produk = null;
    String sql = "select p.* from produk p where kode='" + kode + "'";
    if (idProduk > 0) {
      sql = "select p.* from produk p where kode='" + kode + "' and id!=" + idProduk;
    }
    try {
      ResultSet rs = db.connection.createStatement().executeQuery(sql);
      if (rs.next()) {
        produk = rsProcessor(rs);
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
    }
    return produk;
  }

  public static Produk getByNama(String nama, int idProduk) {
    Database db = new Database();
    Produk produk = null;
    String sql = "select p.* from produk p where nama='" + nama + "'";
    if (idProduk > 0) {
      sql = "select p.* from produk p where nama='" + nama + "' and id!=" + idProduk;
    }
    try {
      ResultSet rs = db.connection.createStatement().executeQuery(sql);
      if (rs.next()) {
        produk = rsProcessor(rs);
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
    }
    return produk;
  }

  public static void initTable() {
    String sql = "create table if not exists produk("
            + "id integer primary key autoincrement,"
            + "kode text not null unique,"
            + "nama text not null unique,"
            + "satuan text not null,"
            + "harga real not null default 0,"
            + "stok real not null default 0,"
            + "stok_kritis real not null default 0,"
            + "status real not null default 'aktif',"
            + "created_at text not null,"
            + "updated_at text not null"
            + ")";
    Database db = new Database();
    try {
      db.connection.createStatement().execute(sql);
      System.out.println("Init table produk done.");
    } catch (SQLException ex) {
      Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Produk rsProcessor(ResultSet rs) {
    Produk produk = null;
    try {
      produk = new Produk(rs.getInt("id"), rs.getString("kode"), rs.getString("nama"), rs.getString("satuan"), rs.getDouble("harga"), rs.getDouble("stok"), rs.getDouble("stok_kritis"), rs.getString("status"), rs.getString("created_at"), rs.getString("updated_at"));
    } catch (SQLException ex) {
      Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
    }
    return produk;
  }

  public Produk save() {
    Produk produk = null;
    Database db = new Database();
    if (this.getId() > 0) {
      String sql = "update produk set kode=?,nama=?,satuan=?,harga=?,stok=?,stok_kritis=?,status=?,updated_at=? where id=?";
      try {
        String timestamp = GaliPOS.getTimeStamp(1, null);
        PreparedStatement pstmt = db.connection.prepareStatement(sql);
        pstmt.setString(1, this.kode);
        pstmt.setString(2, this.nama);
        pstmt.setString(3, this.satuan);
        pstmt.setDouble(4, this.harga);
        pstmt.setDouble(5, this.stok);
        pstmt.setDouble(6, this.stokKritis);
        pstmt.setString(7, this.status);
        pstmt.setString(8, timestamp);
        pstmt.setInt(9, this.getId());
        if (pstmt.executeUpdate() > 0) {
          produk = this;
          Log log = new Log(GaliPOS.sessionUser.getId(), "update", null, "produk", produk.getId() + "", new Gson().toJson(produk), "sukses");
          log.save(db.connection);
        }
        db.connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      String sql = "insert into produk(kode,nama,satuan,harga,stok,stok_kritis,status,created_at,updated_at) "
              + "values(?,?,?,?,?,?,?,?,?)";
      try {
        String timestamp = GaliPOS.getTimeStamp(1, null);
        PreparedStatement pstmt = db.connection.prepareStatement(sql);
        pstmt.setString(1, this.kode);
        pstmt.setString(2, this.nama);
        pstmt.setString(3, this.satuan);
        pstmt.setDouble(4, this.harga);
        pstmt.setDouble(5, this.stok);
        pstmt.setDouble(6, this.stokKritis);
        pstmt.setString(7, this.status);
        pstmt.setString(8, timestamp);
        pstmt.setString(9, timestamp);
        if (pstmt.executeUpdate() > 0) {
          ResultSet rs = db.connection.createStatement().executeQuery("select p.* from produk p order by id desc limit 1");
          if (rs.next()) {
            produk = rsProcessor(rs);
            Log log = new Log(GaliPOS.sessionUser.getId(), "create", null, "produk", produk.getId() + "", new Gson().toJson(produk), "sukses");
            log.save(db.connection);
          }
        }
        db.connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return produk;
  }
}
