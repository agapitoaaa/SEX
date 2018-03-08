/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drvid
 */
public class connection {
    public static String url = "jdbc:sqlite:C:\\Users\\drvid\\Desktop\\TeamPapsie.db";
    public static Connection con = null;
    public static Statement st = null;
    
    //Queries declaration here
    public static String sqls = "CREATE TABLE if not exists STUDENTS( "
            + "uid integer PRIMARY KEY autoincrement, \n"
            + "name varchar(10)"
            + ")";
    
    public static String sql1 = "CREATE TABLE if not exists user (\n" +
            "    user_id integer  PRIMARY KEY autoincrement,\n" +
            "    username varchar(50) UNIQUE NOT NULL,\n" +
            "    password varchar(50) NOT NULL,\n" +
            "    first_name varchar(50) NOT NULL,\n" +
            "    last_name varchar(50) NOT NULL,\n" +
            "    id_number varchar(100) NOT NULL,\n"+
            "    email varchar(100) NOT NULL,\n" +
            "    account_type integer,\n" +
            "    is_activated boolean DEFAULT TRUE,\n" +
            "    sections varchar(20),\n" +
            "    register_date datetime DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime'))\n" +
            ")";
    public static String sql2 = "CREATE TABLE if not exists inventory (\n" +
            "    equip_id integer PRIMARY KEY autoincrement ,\n" +
            "    item_name varchar(200),\n" +
            "    serial_no varchar(100),\n" +
            "    item_user integer,\n" +
            "    property_no integer NOT NULL,\n" +
            "    is_archived boolean DEFAULT false,\n" +
            "    is_damaged boolean DEFAULT false,\n" +
            "    is_available boolean DEFAULT true,\n"+
            "    borrow_id integer"+
            ")";
    public static String sql3 = "CREATE TABLE if not exists borrow (\n" +
            "    borrow_id integer PRIMARY KEY autoincrement ,\n" +
            "    equip varchar(200) NOT NULL,\n" +
            "    borrowed_by varchar(100) NOT NULL,\n" +
            "    id_number varchar(100) NOT NULL,\n"+
            "    sections varchar(20),\n"+
            "    authorized_by varchar(100) NOT NULL DEFAULT '',\n" +
            "    received_by varchar(100),\n" +
            "    date_borrowed datetime DEFAULT current_date,"+
            "    borrow_time datetime DEFAULT (time(CURRENT_TIME, 'localtime')),\n" +
            "    return_time datetime DEFAULT 'Not Returned',\n" +
            "    room varchar(20),\n" +
            "    professor varchar(100),\n"+
            "    borrower_type integer NOT NULL\n"+
            ")";
    //connection to database
    public static Connection dbcon() {
        try {
            con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //creation of tables
    public static Connection ct() {
        try {
            con = DriverManager.getConnection(url);
            st = con.createStatement();
            st.execute(sqls);
//            st.execute(sql2);
//            st.execute(sql3);
            System.out.println("Created");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
