package com.example.demoinclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Formatter;
import java.util.Properties;


public class DBUtility {
    private static String user;
    private static String password;
    //My remote MySQL
    //private static String user= DBCredentials.user;
    //private static String password=DBCredentials.password;

    //my local MySQL
    //private static String password="s2023";
    //private static String user="admin";
    private static String connectURL ="jdbc:mysql://sql9.freesqldatabase.com:3306/sql9622349";

    /*
    * Added configured Credentials
    * */
    public DBUtility()
    {
        try {
            String configFilePath = "src/main/resources/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);

            user=prop.getProperty("DB_USER");
            password=prop.getProperty("6HX1t2hX7Y");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creatFoodMenus() {
        //Random number generator
        SecureRandom secureRandom=new SecureRandom();

        try(
          Formatter formatter=new Formatter("createFoodMenuItems.sql");
        )
        {

            for (int i = 0; i < 50; i++) {
                formatter.format(String.format("INSERT INTO `sql9622349`.`foodmenu`\n" +
                        "(`name`,`price`,`spicylevel`,`calorie`)\n" +
                        "VALUES('Food%s',%.2f,%d,%d);\n",i,
                        (secureRandom.nextDouble()*40)+5,secureRandom.nextInt(0,4),secureRandom.nextInt(100,9000)));
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }



    public static  int insertFoodMenu(FoodMenu foodMenu) throws SQLException {
        ResultSet resultSet =null;
        int id=-1;
        String sql ="INSERT INTO `foodmenu2023`.`foodmenu`\n" +
                "(`name`,\n" +
                "`price`,\n" +
                "`spicylevel`)\n" +
                "VALUES\n" +
                "(?,?,?);\n";
        try(
        Connection connection = DriverManager.getConnection(connectURL,user,password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql,new String[]{"idfoodmenu"})
        )
        {
            preparedStatement.setString(1, foodMenu.getFoodName());
            preparedStatement.setDouble(2, foodMenu.getPrice());
            preparedStatement.setInt(3, foodMenu.getSpicyLevel());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next())
                id=resultSet.getInt(1);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            if (resultSet!=null)
                resultSet.close();
        }


        return id;
     }
}
