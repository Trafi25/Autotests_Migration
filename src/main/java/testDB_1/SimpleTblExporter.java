package testDB_1;

import java.io.*;
import java.sql.*;
 
public class SimpleTblExporter {

    public static void ExportwhereOrderBY() {
        String csvFilePath = "Export.csv";
        String con = "jdbc:sqlserver://DESKTOP-E42U79M;database=Time;";

        try (Connection connection = DriverManager.getConnection(con,"admin","admin")) {
            String sql = "select * from cards where cost >1000 ORDER BY card_id ";
            try{
             
            Statement statement = connection.createStatement();
             
            ResultSet result = statement.executeQuery(sql);
             
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             

            fileWriter.write("card_id,action,cost");

            while(true) {
                if (!result.next()) {
                    statement.close();
                    fileWriter.close();
                    break;
                }

                String card_id = result.getString("card_id");
                String action = result.getString("action");
                int cost = result.getInt("cost");
                String line = String.format("%s,%s,%d",card_id, action, cost);
                fileWriter.newLine();
                fileWriter.write(line);
            }
            }catch (Throwable var11) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var10) {
                        var11.addSuppressed(var10);
                    }
                }

                throw var11;
            }
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }       
    }

    public static void ExportGroupby() {
        String csvFilePath = "Export.csv";
        String con = "jdbc:sqlserver://DESKTOP-E42U79M;database=Time;";

        try (Connection connection = DriverManager.getConnection(con,"admin","admin")) {
            try {
                String sql = "SELECT id, sum(collectibleCount) as sumcollectibleCount FROM sets group by [id]";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
                fileWriter.write("id,sumcollectibleCount");

                while(true) {
                    if (!result.next()) {
                        statement.close();
                        fileWriter.close();
                        break;
                    }

                    int ID = result.getInt("id");
                    int sumcollectibleCount = result.getInt("sumcollectibleCount");
                    String line = String.format("%d,%d", ID, sumcollectibleCount);
                    fileWriter.newLine();
                    fileWriter.write(line);
                }
            } catch (Throwable var11) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var10) {
                        var11.addSuppressed(var10);
                    }
                }

                throw var11;
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var12) {
            System.out.println("Datababse error:");
            var12.printStackTrace();
        } catch (IOException var13) {
            System.out.println("File IO error:");
            var13.printStackTrace();
        }

    }
}

    


