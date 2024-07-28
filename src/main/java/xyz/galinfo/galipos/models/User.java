/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.galinfo.galipos.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
public class User {

  private int id;
  private String foto;
  private String nama;
  private String jenisKelamin;
  private String tanggalLahir;
  private String alamat;
  private String telepon;
  private String email;
  private String username;
  private String password;
  private String akses;
  private String status;
  private String createdAt;
  private String updatedAt;

  public User() {
  }

  public User(String foto, String nama, String jenisKelamin, String tanggalLahir, String alamat, String telepon, String email, String username, String password, String akses, String status) {
    this.foto = foto;
    this.nama = nama;
    this.jenisKelamin = jenisKelamin;
    this.tanggalLahir = tanggalLahir;
    this.alamat = alamat;
    this.telepon = telepon;
    this.email = email;
    this.username = username;
    this.password = password;
    this.akses = akses;
    this.status = status;
  }

  public User(int id, String foto, String nama, String jenisKelamin, String tanggalLahir, String alamat, String telepon, String email, String username, String password, String akses, String status, String createdAt, String updatedAt) {
    this.id = id;
    this.foto = foto;
    this.nama = nama;
    this.jenisKelamin = jenisKelamin;
    this.tanggalLahir = tanggalLahir;
    this.alamat = alamat;
    this.telepon = telepon;
    this.email = email;
    this.username = username;
    this.password = password;
    this.akses = akses;
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

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getJenisKelamin() {
    return jenisKelamin;
  }

  public void setJenisKelamin(String jenisKelamin) {
    this.jenisKelamin = jenisKelamin;
  }

  public String getTanggalLahir() {
    return tanggalLahir;
  }

  public void setTanggalLahir(String tanggalLahir) {
    this.tanggalLahir = tanggalLahir;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public String getTelepon() {
    return telepon;
  }

  public void setTelepon(String telepon) {
    this.telepon = telepon;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAkses() {
    return akses;
  }

  public void setAkses(String akses) {
    this.akses = akses;
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
    return "User{" + "id=" + id + ", foto=" + foto + ", nama=" + nama + ", jenisKelamin=" + jenisKelamin + ", tanggalLahir=" + tanggalLahir + ", alamat=" + alamat + ", telepon=" + telepon + ", email=" + email + ", username=" + username + ", password=" + password + ", akses=" + akses + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
  }

  public User delete() {
    Database db = new Database();
    String sql = "delete from user where id=?";
    PreparedStatement pstmt;
    try {
      pstmt = db.connection.prepareStatement(sql);
      pstmt.setInt(1, this.getId());
      if (pstmt.executeUpdate() > 0) {
        this.setPassword(null);
        Log log = new Log(GaliPOS.sessionUser.getId(), "delete", null, "user", this.getId() + "", new Gson().toJson(this), "sukses");
        log.save(db.connection);
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return this;
  }

  public static ArrayList<User> getActiveBuyers() {
    ArrayList<User> users = new ArrayList<>();
    Database db = new Database();
    String sql = "select u.* from user u where u.id not in(1,2,3) and akses='buyer' and status='aktif' order by u.nama asc";
    try {
      ResultSet rs = db.connection.createStatement().executeQuery(sql);
      while (rs.next()) {
        users.add(rsProcessor(rs));
      }
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return users;
  }

  public static ArrayList<User> getAll(String columnName, String qWord) {
    ArrayList<User> users = new ArrayList<>();
    Database db = new Database();
    String sql = "select u.* from user u where u.id not in(1,2,3) order by u.nama asc";
    if (columnName != null && qWord != null && columnName.length() > 0 && qWord.length() > 0) {
      sql = "select u.* from user u where u.id not in(1,2,3) and " + columnName + " like '%" + qWord + "%' order by u.nama asc";
    }
    try {
      ResultSet rs = db.connection.createStatement().executeQuery(sql);
      while (rs.next()) {
        users.add(rsProcessor(rs));
      }
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return users;
  }

  public static String[] getAllowedAkses() {
    String[] allowedAkses = new String[]{};
    switch (GaliPOS.sessionUser.getAkses()) {
      case "administrator":
        allowedAkses = new String[]{"operator", "kasir", "supplier", "buyer"};
        break;
      case "operator":
        allowedAkses = new String[]{"kasir", "buyer"};
        break;
      default:
        throw new AssertionError();
    }
    return allowedAkses;
  }

  public static User getByUsername(String username, int idUser) {
    User user = null;
    Database db = new Database();
    String sql = "select u.* from user u where username=?";
    if (idUser > 0) {
      sql = "select u.* from user u where username=? and id!=" + idUser;
    }
    try {
      PreparedStatement pstmt = db.connection.prepareStatement(sql);
      pstmt.setString(1, username);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        user = new User(rs.getInt("id"), rs.getString("foto"), rs.getString("nama"), rs.getString("username"), rs.getString("tanggal_lahir"), rs.getString("alamat"), rs.getString("telepon"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("akses"), rs.getString("status"), rs.getString("created_at"), rs.getString("updated_at"));
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
  }

  public static User getById(int id) {
    User user = null;
    Database db = new Database();
    String sql = "select u.* from user u where id=?";
    try {
      PreparedStatement pstmt = db.connection.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        user = rsProcessor(rs);
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
  }

  public static void initTable() {
    Database db = new Database();
    String sql = "create table if not exists user("
            + "id integer not null primary key autoincrement,"
            + "foto text null default null,"
            + "nama text not null,"
            + "jenis_kelamin text null default null,"
            + "tanggal_lahir text null default null,"
            + "alamat text null default null,"
            + "telepon text null default null,"
            + "email text null default null,"
            + "username text null default null,"
            + "password text null default null,"
            + "akses text not null default 'buyer',"
            + "status text not null default 'nonaktif',"
            + "created_at text not null,"
            + "updated_at text not null"
            + ")";
    try {
      db.connection.createStatement().execute(sql);
      System.out.println("Init table user done.");
      ResultSet rs = db.connection.createStatement().executeQuery("select count(id) as jumlahUser from user order by id asc limit 3");
      if (rs.next()) {
        if (rs.getInt("jumlahUser") == 0) {
          String timestamp = GaliPOS.getTimeStamp(1, null);
          db.connection.createStatement().execute("insert into user(nama,username,password,akses,status,created_at,updated_at) values('Umum','umum','_pass','buyer','aktif','" + timestamp + "','" + timestamp + "')");
          db.connection.createStatement().execute("insert into user(nama,username,password,akses,status,created_at,updated_at) values('Sistem','sistem','" + BCrypt.withDefaults().hashToString(12, "admin".toCharArray()) + "','administrator','aktif','" + timestamp + "','" + timestamp + "')");
          db.connection.createStatement().execute("insert into user(nama,username,password,akses,status,created_at,updated_at) values('Administrator','admin','" + BCrypt.withDefaults().hashToString(12, "admin".toCharArray()) + "','administrator','aktif','" + timestamp + "','" + timestamp + "')");
          System.out.println("Init user data done.");
        }
      }
      db.connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static User rsProcessor(ResultSet rs) {
    User user = null;
    try {
      user = new User(rs.getInt("id"), rs.getString("foto"), rs.getString("nama"), rs.getString("username"), rs.getString("tanggal_lahir"), rs.getString("alamat"), rs.getString("telepon"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("akses"), rs.getString("status"), rs.getString("created_at"), rs.getString("updated_at"));
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
  }

  public User save() {
    User user = null;
    Database db = new Database();
    String timestamp = GaliPOS.getTimeStamp(1, null);
    String sql = "insert into user(foto,nama,jenis_kelamin,tanggal_lahir,alamat,telepon,email,username,password,akses,status,created_at,updated_at) "
            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    if (this.getId() > 0) {
      sql = "update user set foto=?,nama=?,jenis_kelamin=?,tanggal_lahir=?,alamat=?,telepon=?,email=?,username=?,password=?,akses=?,status=?,updated_at=? where id=?";
      PreparedStatement pstmt;
      try {
        pstmt = db.connection.prepareStatement(sql);
        pstmt.setString(1, this.foto);
        pstmt.setString(2, this.nama);
        pstmt.setString(3, this.jenisKelamin);
        pstmt.setString(4, this.tanggalLahir);
        pstmt.setString(5, this.alamat);
        pstmt.setString(6, this.telepon);
        pstmt.setString(7, this.email);
        pstmt.setString(8, this.username);
        pstmt.setString(9, this.password);
        pstmt.setString(10, this.akses);
        pstmt.setString(11, this.status);
        pstmt.setString(12, timestamp);
        pstmt.setInt(13, this.getId());
        if (pstmt.executeUpdate() > 0) {
          user = this;
          user.setPassword(null);
          Log log = new Log(GaliPOS.sessionUser.getId(), "update", null, "user", user.getId() + "", new Gson().toJson(user), "sukses");
          log.save(db.connection);
        }
        db.connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      try {
        PreparedStatement pstmt = db.connection.prepareStatement(sql);
        pstmt.setString(1, this.foto);
        pstmt.setString(2, this.nama);
        pstmt.setString(3, this.jenisKelamin);
        pstmt.setString(4, this.tanggalLahir);
        pstmt.setString(5, this.alamat);
        pstmt.setString(6, this.telepon);
        pstmt.setString(7, this.email);
        pstmt.setString(8, this.username);
        pstmt.setString(9, this.password);
        pstmt.setString(10, this.akses);
        pstmt.setString(11, this.status);
        pstmt.setString(12, timestamp);
        pstmt.setString(13, timestamp);
        if (pstmt.executeUpdate() > 0) {
          ResultSet rs = db.connection.createStatement().executeQuery("select u.* from user u order by id desc limit 1");
          if (rs.next()) {
            user = rsProcessor(rs);
            user.setPassword(null);
            Log log = new Log(GaliPOS.sessionUser.getId(), "create", null, "user", user.getId() + "", new Gson().toJson(user), "sukses");
            log.save(db.connection);
          }
        }
        db.connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return user;
  }
}
