package com.example;

import java.sql.*;
import de.svenjacobs.loremipsum.LoremIpsum;


public class DatabaseApp {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/notesdb";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASS = "postgres";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(JDBC_URL, DATABASE_USER, DATABASE_PASS);
        Statement statement = connection.createStatement();

        //ResultSet resultSet = statement.executeQuery("SELECT * FROM notes;");
        //int resultCount = statement.executeUpdate("CREATE TABLE test (test_id integer, test_name_character text);");
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notes (note_id, note_title, note_message, user_id) VALUES (?,?,?,?);");
//        preparedStatement.setInt(1, 123);
//        preparedStatement.setString(2,"Przykladowy tytul");
//        preparedStatement.setNull(3,Types.VARCHAR);
//        preparedStatement.setInt(4, 2);
//
//        preparedStatement.executeUpdate();

        /*

        while (resultSet.next()){
            int noteId = resultSet.getInt(1);
            String noteTitle = resultSet.getString("note_title");
            String noteMsg = resultSet.getString(3);

            System.out.println("noteTitle + noteId = " + noteTitle + noteId + " msg: " + noteMsg);

        }
*/
        ResultSet id = statement.executeQuery("SELECT max(note_id) as maximum\n" +
                "\tFROM public.notes\n" +
                "\t;");

        int newInt = 0;
        while (id.next()){
            newInt = id.getInt(1);
        }
        LoremIpsum loremIpsum = new LoremIpsum();


        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notes (note_id, note_title, note_message, user_id) VALUES (?,?,?,?);");

        for (int i = 1; i <= 10 ; i++) {
            preparedStatement.setInt(1, newInt + i);
            preparedStatement.setString(2,loremIpsum.getWords(3));
            preparedStatement.setString(3,loremIpsum.getWords(10));
            preparedStatement.setInt(4, 2);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
        connection.close();

        statement.close();
        connection.close();

    }
}
