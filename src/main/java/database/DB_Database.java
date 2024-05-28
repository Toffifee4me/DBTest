/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Philipp
 */
public class DB_Database implements DB_Config
{
    // implement this class as singleton
    private static DB_Database theInstance = null;

    public static DB_Database getInstance()
    {
        if (theInstance == null)
        {
            theInstance = new DB_Database();
        }
        return theInstance;
    }

    private DB_Database()
    {
        try
        {
            Class.forName(DB_DRIVER);
            connect();
        } catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Connection conn;
    private DB_CachedConnection cc;

    public void connect() throws SQLException
    {
        conn = DriverManager.getConnection(DB_URL+DB_DATABASENAME, DB_USER, DB_PASSWD);
        cc = new DB_CachedConnection(conn);
    }
    
    public void disconnect() throws SQLException
    {
        conn.close();
    }
    
    public Statement getStatement() throws SQLException
    {
        return cc.getStatement();
    }
    
    public void releaseStatement(Statement stat)
    {
        cc.releaseStatement(stat);
    }
}
