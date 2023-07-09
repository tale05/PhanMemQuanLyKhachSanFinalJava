package DAO;

public class MainDAO {
    public static int excuteSQL(String sql) {
        int i=0;
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        i = cn.executeUpdate(sql);
        cn.close();
        return i;
    }
}
