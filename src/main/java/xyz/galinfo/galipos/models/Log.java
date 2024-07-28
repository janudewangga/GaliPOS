/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.galinfo.galipos.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.galinfo.galipos.Database;
import xyz.galinfo.galipos.GaliPOS;

/**
 *
 * @author janud
 */
public class Log {

  private long id;
  private int idUser;
  private String aksi;
  private String ipAddress;
  private String namaTabel;
  private String idData;
  private String keterangan;
  private String status;
  private String createdAt;
  private String updatedAt;

  public Log(int idUser, String aksi, String ipAddress, String namaTabel, String idData, String keterangan, String status) {
    this.idUser = idUser;
    this.aksi = aksi;
    this.ipAddress = ipAddress;
    this.namaTabel = namaTabel;
    this.idData = idData;
    this.keterangan = keterangan;
    this.status = status;
  }

  public Log(long id, int idUser, String aksi, String ipAddress, String namaTabel, String idData, String keterangan, String status, String createdAt, String updatedAt) {
    this.id = id;
    this.idUser = idUser;
    this.aksi = aksi;
    this.ipAddress = ipAddress;
    this.namaTabel = namaTabel;
    this.idData = idData;
    this.keterangan = keterangan;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public String getAksi() {
    return aksi;
  }

  public void setAksi(String aksi) {
    this.aksi = aksi;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getNamaTabel() {
    return namaTabel;
  }

  public void setNamaTabel(String namaTabel) {
    this.namaTabel = namaTabel;
  }

  public String getIdData() {
    return idData;
  }

  public void setIdData(String idData) {
    this.idData = idData;
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
    return "Log{" + "id=" + id + ", idUser=" + idUser + ", aksi=" + aksi + ", ipAddress=" + ipAddress + ", namaTabel=" + namaTabel + ", idData=" + idData + ", keterangan=" + keterangan + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
  }

  public static void initTable() {
    Database db = new Database();
    String sql = "create table if not exists log("
            + "id integer not null primary key autoincrement,"
            + "id_user integer not null,"
            + "aksi text not null default 'read',"
            + "ip_address text null default null,"
            + "nama_tabel text null default null,"
            + "id_data text null default null,"
            + "keterangan text null default null,"
            + "status text not null default 'sukses',"
            + "created_at text not null,"
            + "updated_at text not null,"
            + "foreign key(id_user) references user(id) on delete restrict on update cascade"
            + ")";
    try {
      db.connection.createStatement().execute(sql);
      System.out.println("Init table log done.");
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Log save(Connection dbConn) {
    Log log = null;
    if (dbConn == null) {
      Database db = new Database();
      dbConn = db.connection;
    }
    String sql = "insert into log(id_user,aksi,ip_address,nama_tabel,id_data,keterangan,status,created_at,updated_at) "
            + "values(?,?,?,?,?,?,?,?,?)";
    PreparedStatement pstmt;
    try {
      String timestamp = GaliPOS.getTimeStamp(1, null);
      pstmt = dbConn.prepareStatement(sql);
      pstmt.setInt(1, this.idUser);
      pstmt.setString(2, this.aksi);
      pstmt.setString(3, this.ipAddress);
      pstmt.setString(4, this.namaTabel);
      pstmt.setString(5, this.idData);
      pstmt.setString(6, this.keterangan);
      pstmt.setString(7, this.status);
      pstmt.setString(8, timestamp);
      pstmt.setString(9, timestamp);
      if (pstmt.executeUpdate() > 0) {
        ResultSet rs = dbConn.createStatement().executeQuery("select l.* from log l order by id desc limit 1");
        if (rs.next()) {
          log = new Log(rs.getLong("id"), rs.getInt("id_user"), rs.getString("aksi"), rs.getString("ip_address"), rs.getString("nama_tabel"), rs.getString("id_data"), rs.getString("keterangan"), rs.getString("status"), rs.getString("created_at"), rs.getString("updated_at"));
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      dbConn.close();
    } catch (SQLException ex) {
      Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
    }
    return log;
  }
}
