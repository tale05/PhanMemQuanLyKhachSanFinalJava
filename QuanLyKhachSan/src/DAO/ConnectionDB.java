package DAO;

import java.sql.*;

public class ConnectionDB {
    private Connection cn;
    Statement state;
    ResultSet rs;

    public Connection getCn() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLKS";
            String user = "sa";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            
            cn = DriverManager.getConnection(url, user, pass);

            
            System.out.println("Ket noi CSDL thanh cong");
        } catch (Exception e) {
            System.out.println("Ket noi CSDL khong thanh cong");
        }
        return cn;
    }
    
    public void close(){
        try {
            this.cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String sql){
        ResultSet rs = null;
        try {
            Statement stm = cn.createStatement();
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public int executeUpdate(String sql){
        int i = -1;
        try {
            Statement stm = cn.createStatement();
            i = stm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
