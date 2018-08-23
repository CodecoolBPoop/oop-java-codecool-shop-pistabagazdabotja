package com.codecool.shop.db;

import java.sql.*;
import java.util.*;

public class db_connection {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/pistaba";

    static final String USER = "";
    static final String PASS = "";

    private static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void executeQuery(String query){
        Connection conn = getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void executeQuery(String query, List params){
        Connection conn = getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            int n = 1;
            Iterator iter = params.listIterator();
            while (iter.hasNext()){
                Object next = iter.next();
                if (next instanceof String)
                    ps.setString(n, next.toString());
                else if (next instanceof Integer)
                    ps.setInt(n, ((Integer) next).intValue());
                n++;
            }
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if(ps!=null)
                    ps.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static List<HashMap> executeQueryWithResult(String query){
        Connection conn = getConn();
        Statement statement = null;
        ResultSet rs;
        ResultSetMetaData rsmd;
        List<HashMap> result = new LinkedList<>();
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            rsmd = rs.getMetaData();
            Integer columnCount = rsmd.getColumnCount();
            HashMap<String, String> row;
            while (rs.next()){
                row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++){
                    row.put(rsmd.getColumnName(i), rs.getString(i));
                }
                result.add(row);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
