/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philipp
 */
public class DB_Access {

    private DB_Database database = DB_Database.getInstance();

    //Implement DB_Access as Singleton
    private static DB_Access theInstance = null;

    public static DB_Access getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }
        return theInstance;
    }

    private DB_Access() {
    }

    public Vector<String> getAllPublishers() throws SQLException {
        Vector<String> vec = new Vector();

        Statement stat = database.getStatement();
        String sqlStr = "SELECT * FROM publisher";
        ResultSet rs = stat.executeQuery(sqlStr);
        database.releaseStatement(stat);

        while (rs.next()) {
            String bookname = rs.getString("name");
            vec.add(bookname);

        }
        return vec;
    }
    
 

    
    
    public static void main(String[] args) {
        DB_Access dba = DB_Access.getInstance();
        try {
            for (String pub : dba.getAllPublishers()) {
                System.out.println(pub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
