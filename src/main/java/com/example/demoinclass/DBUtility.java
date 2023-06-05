package com.example.demoinclass;

import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Properties;
import java.util.ResourceBundle;


public class DBUtility  {

    //My remote MySQL
    private static String user;
    private static String password;
//    //My remote MySQL
//    private static String user= DBCredentials.user;
//    private static String password=DBCredentials.password;

    //my local MySQL
    //private static String password="s2023";
    //private static String user="admin";
    private static String connectURL ="jdbc:mysql://sql9.freesqldatabase.com:3306/sql9622349";



    /*
     *Get the foodmenu list for the tableview
     *  */
    public static ArrayList<FoodMenu> getFoodMenus()
    {
        ArrayList<FoodMenu> foodMenus = new ArrayList<>();
        //query from the database
        String sql= "SELECT * FROM sql9622349.foodmenu;";
        try (
                Connection connection =DriverManager.getConnection(connectURL,user,password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ){
            while(resultSet.next())
            {
                String name= resultSet.getString("name");
                Double price= resultSet.getDouble("price");
                Integer spicy= resultSet.getInt("spicylevel");
                Integer calorie= resultSet.getInt("calorie");
                FoodMenu foodMenu = new FoodMenu(name,price,spicy,calorie);
                foodMenus.add(foodMenu);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return  foodMenus;
    }

    /*
    * Create the sql string to create demo data
    * */
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

    /*
     * Added configured Credentials
     * */

    public static void getCredentials() {
       // https://niruhan.medium.com/how-to-add-a-config-file-to-a-java-project-99fd9b6cebca
       try {

            String configFilePath = "src/main/resources/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);

            user=prop.getProperty("DB_USER");
            password=prop.getProperty("DB_PASSWORD");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
