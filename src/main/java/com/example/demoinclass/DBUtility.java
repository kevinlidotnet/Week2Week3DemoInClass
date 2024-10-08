package com.example.demoinclass;

import javafx.scene.chart.XYChart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.*;

public class DBUtility {
    private static String user="student";
    private static String password="student";
    //private static String connectURL ="jdbc:mysql://sql9.freesqldatabase.com:3306/sql9622349";
    private static String connectURL ="jdbc:mysql://localhost:3306/foodmenu2023";

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
     *
     * */
     public static void configCridentials()
     {
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

     /*
     * Generate 50 rows in SQL query
     * */
    public static void creatFoodMenus(){
        SecureRandom secureRandom = new SecureRandom();
        try(Formatter formatter = new Formatter("createSomeItems.sql");)
        {
            for (int i = 0; i < 50; i++) {
              formatter.format(" INSERT INTO `sql9622349`.`foodmenu`\n" +
                      "(`name`,`price`,`spicylevel`,`calorie`)" +
                      "VALUES ('Food%d',%.2f, %d,%d);\n" ,
                      i,
                      (secureRandom.nextDouble()*40)+5,
                      secureRandom.nextInt(0,4),
                      secureRandom.nextInt(100,9000)  ) ;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    /*
    * Get all data
    * */
    public static ArrayList<FoodMenu> getFoodMenus(String... sqls) throws SQLException {
        ArrayList<FoodMenu> foodMenus = new ArrayList<>();
        String sql="SELECT * FROM foodmenu;";
        if (sqls.length>0) sql=sqls[0];
        try(
                Connection connection =DriverManager.getConnection(connectURL,user,password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
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
        catch(SQLException e)
        {
            e.printStackTrace();
        }


        return  foodMenus;
    }


    /*
    * Get top list for the chart
    * */
    public static XYChart.Series<String, Double> getTopFoodMenus()  {
        XYChart.Series<String,Double> topFoodSeries = new XYChart.Series<>();
        try {
            ArrayList<FoodMenu> topFoodMenus = getFoodMenus("SELECT *, price/calorie as cost  FROM foodmenu \n" +
                    "order by price/calorie\n" +
                    "limit 10;");
            for (FoodMenu foodMenu: topFoodMenus
                 ) {
                topFoodSeries.getData().add(new XYChart.Data<>(foodMenu.getFoodName(),foodMenu.getPrice()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(topFoodSeries.getData().stream().count());
        return topFoodSeries;
    }

    public static XYChart.Series<String, Double> getTopFoodMenusWithSpicyLevel()  {
        XYChart.Series<String,Double> topFoodSeries = new XYChart.Series<>();
        try {
            ArrayList<FoodMenu> topFoodMenus = getFoodMenus("SELECT *, price/calorie as cost  FROM foodmenu \n" +
                    "order by price/calorie\n" +
                    "limit 10;");
            for (FoodMenu foodMenu: topFoodMenus
            ) {
                topFoodSeries.getData().add(new XYChart.Data<>(foodMenu.getFoodName(),Double.valueOf(foodMenu.getSpicyLevel())));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(topFoodSeries.getData().stream().count());
        return topFoodSeries;
    }

    //Get cases by age


    /*
     * Get cases by age for the chart
     * */
    private static XYChart.Series<String, Integer> getCases(String sql,String group)  {
        XYChart.Series<String,Integer> ageCases = new XYChart.Series<>();
        try(
                Connection connection =DriverManager.getConnection(connectURL,user,password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while(resultSet.next())
            {
                String age_group= resultSet.getString(group);
                Integer cases= resultSet.getInt("cases");
                ageCases.getData().add(new XYChart.Data<>(age_group,cases));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ageCases;
    }


    public static XYChart.Series<String, Integer> getCasesByAge() {
        String sql = "SELECT sum(count) as cases, age_group FROM foodmenu2023.covidcase \n" +
                "group by age_group\n" +
                "order by 2 ";
        String group = "age_group";
        return  getCases(sql,group);
    }
    public static XYChart.Series<String, Integer> getCasesByGender() {
        String sql = "SELECT sum(count) as cases,gender FROM foodmenu2023.covidcase \n" +
                "group by gender\n" +
                "order by 2 ";
        String group = "gender";
        return  getCases(sql,group);
    }
    public static XYChart.Series<String, Integer> getCasesByMonth() {
        String sql = "SELECT sum(count) as cases,  \n" +
                "  EXTRACT(YEAR_MONTH  FROM date) AS month  FROM foodmenu2023.covidcase \n" +
                "group by month\n" +
                "order by 2 ";
        String group = "month";
        return  getCases(sql,group);
    }



}
