package com.seu_pacote;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/literatura";
    private static final String USER = "alura";
    private static final String PASSWORD = "alura";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, authors, download_url) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, String.join(", ", book.getAuthors().stream().map(Book.Author::getName).toArray(String[]::new)));
            statement.setString(3, book.getDownloadUrl());
            statement.executeUpdate();
        }
    }
}
