package edu.tamu.compassreport;

import javax.sql.DataSource;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by datamaskinaggie on 1/12/17.
 */
public class ReadBlob {

    Connection conn = null;
    private String url;
    private String dbName;
    private String driver;
    private String userName;
    private String passWord;
    private String mimetype;
    ResultSet rs = null;

    private byte[] bytes = null;

    public byte[] getBytes() {
        return bytes;
    }

    private void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public DataSource dataSource = null;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public ReadBlob() {
    }

    /*public static void main(String args[]) {
    }*/

    public byte[] readBlob(BigDecimal seq, DataSource dataSource) {
        int length = 0;
        int total = 0;
        try {

            DataSource ds = dataSource;

            Connection conn = ds.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select gw_rpts_blob, gw_rpts_mime FROM gw_rpts WHERE gw_rpts_sequence = '" + seq + "'");
            Blob lob = null;
            String mimetype = new String();
            while (rs.next()) {
                lob = rs.getBlob("GW_RPTS_BLOB");
                mimetype = rs.getString("GW_RPTS_MIME");
            }
            this.setMimetype(mimetype);

            InputStream in = lob.getBinaryStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int buffersize = 1024;
            length = (int) lob.length();

            byte[] buffer = new byte[buffersize];

            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            this.setBytes(buffer);

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("WriteBlog").log(Level.ALL, "Reason for exception: " + e.getMessage());
        }
        this.setBytes(getBytes());
        this.setMimetype(getMimetype());
        return this.getBytes();
    }
}