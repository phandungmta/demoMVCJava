/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL E7450
 */
public class BookDAO extends DBConnection.ConnectionDB{
    public BookDAO() {
        super();       
}
     public List<Book> listAllBooks() throws SQLException {
         Book book=null;
        List<Book> listBook = new ArrayList<>();         
        String sql = "SELECT * FROM book";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery( );
         
        while (rs.next()) {
            int id = rs.getInt("book_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            float price = rs.getFloat("price");             
              book = new Book(id, title, author, price);
            listBook.add(book);
        }
        return listBook;
    }
     public int deleteBook(Book book)  {
        int  rowDeleted=0;
        String sql = "DELETE FROM book where book_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, book.getId());
            rowDeleted = ps.executeUpdate();
        if (rowDeleted>0)
            System.out.print ("Deleted");
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowDeleted;     
    }
      public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        
        ps = conn.prepareStatement(sql);
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setFloat(3, book.getPrice());
         
        boolean rowInserted = ps.executeUpdate() > 0;       
        return rowInserted;
    }
       public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE book_id = ?";
         
         
        ps= conn.prepareStatement(sql);
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setFloat(3, book.getPrice());
        ps.setInt(4, book.getId());
         
        boolean rowUpdated = ps.executeUpdate() > 0;
        
        return rowUpdated;     
    }
       public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";
         
         ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
         
        rs = ps.executeQuery();
         
        if (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            float price = rs.getFloat("price");
             
            book = new Book(id, title, author, price);
        }
         
       
         
        return book;
    }
}
