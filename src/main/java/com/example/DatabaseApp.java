package com.example;

import java.sql.*;


public class DatabaseApp {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/notesdb";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASS = "postgres";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(JDBC_URL, DATABASE_USER, DATABASE_PASS);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM notes;");
        //int resultCount = statement.executeUpdate("CREATE TABLE test (test_id integer, test_name_character text);");


        while (resultSet.next()){
            int noteId = resultSet.getInt(1);
            String noteTitle = resultSet.getString("note_title");
            String noteMsg = resultSet.getString(3);

            System.out.println("noteTitle + noteId = " + noteTitle + noteId + " msg: " + noteMsg);

        }


        statement.close();
        connection.close();

    }
}
