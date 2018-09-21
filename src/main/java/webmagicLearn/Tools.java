package webmagicLearn;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;

/**
 * @author zhiwj
 * @date 2018/9/21
 */
public class Tools {

    public static DruidDataSource druidDataSource;

    static {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://47.100.119.115:3306/zwj";
        String username = "root";
        String pd = "Notify(1)";
        druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(pd);
    }

    public static void save(String code, String name, String phone, String address) {
        try (
                DruidPooledConnection connection = druidDataSource.getConnection();
        ) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO bank (CODE,NAME,PHONE,ADDRESS) VALUES (?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
