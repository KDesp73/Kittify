/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.musicplayer.gui.MainFrame;

/**
 *
 * @author konstantinos
 */
public class Queries {

    public static List<String> getDirectories() {
        DatabaseConnection db = Database.connection();
        QueryBuilder builder = new QueryBuilder();

        ResultSet rs = db.executeQuery(builder.select("directory").from("Directories").build());
        List<String> paths = new ArrayList<>();

        try {
            while (rs.next()) {
                paths.add(rs.getString("directory"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paths;
    }

    public static List<String> getFiles() {
        DatabaseConnection db = Database.connection();
        QueryBuilder builder = new QueryBuilder();

        ResultSet rs = db.executeQuery(builder.select("filepath").from("Files").build());
        List<String> paths = new ArrayList<>();

        try {
            while (rs.next()) {
                paths.add(rs.getString("filepath"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paths;
    }

    public static void clearDatabase() {
        DatabaseConnection db = Database.connection();
        QueryBuilder builder = new QueryBuilder();

        db.executeUpdate(builder.deleteFrom("Directories").build());
        db.executeUpdate(builder.deleteFrom("Files").build());
        db.executeUpdate(builder.deleteFrom("Songs").build());

        db.close();
    }

    public static String getSortBy() {
        DatabaseConnection db = Database.connection();
        ResultSet rs = db.executeQuery(new QueryBuilder().select("sort_by").from("Settings").build());
        String sort_by = "";
        try {
            rs.next();
            sort_by = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.close();

        return sort_by;
    }

    public static void updateSortBy(String sort_by) {
        DatabaseConnection db = Database.connection();
        
        db.executeUpdate(new QueryBuilder().update("Settings").set("sort_by", sort_by).where("key = 1").build());

        db.close();
    }

	public static int getLastPlayed() {
        DatabaseConnection db = Database.connection();
        ResultSet rs = db.executeQuery(new QueryBuilder().select("last_played").from("Settings").build());
        int sort_by = 0;
        try {
            rs.next();
            sort_by = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.close();

        return sort_by;
    }
	
	public static void updateLastPlayed(int index){
        DatabaseConnection db = Database.connection();
        db.executeUpdate(new QueryBuilder().update("Settings").set("last_played", index).where("key = 1").build());
        db.close();
		
	}
	
}
