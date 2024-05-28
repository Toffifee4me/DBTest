/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Philipp
 */
public class DB_CachedConnection
{

    private final Queue<Statement> statQueue = new LinkedList<>();
    private final Connection conn;

    public DB_CachedConnection(Connection conn)
    {
        this.conn = conn;
    }

    public Statement getStatement() throws SQLException
    {
        if (conn == null)
        {
            throw new RuntimeException("Not connected to database");
        }
        if (statQueue.isEmpty())
        {
            return conn.createStatement();
        } else
        {
            return statQueue.poll();
        }

    }

    public void releaseStatement(Statement stat)
    {
        if (conn == null)
        {
            throw new RuntimeException("Not connected to database");
        }
        statQueue.offer(stat);
    }
}
