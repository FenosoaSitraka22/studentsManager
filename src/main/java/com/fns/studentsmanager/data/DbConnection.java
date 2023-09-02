/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fns.studentsmanager.data;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nir'info
 */
public class DbConnection {
    private static Connection connexion;
    public void getDBConnection(){
        synchronized(""){
            try{
                if (this.getConnexion()==null || this.connexion.isClosed()){
                   try{
                      String url = "jdbc:mysql://localhost:3306/dbStudent";
                     //  String url = "http://localhost/phpmyadmin/dbSudent";
                       Class.forName("com.mysql.cj.jdbc.Driver");
                       setConnexion(DriverManager.getConnection(url,"root","Septembre*22kali"));
                   } catch(Exception e){
                       e.printStackTrace();
                   }
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }
    }
    
    public Connection getConnexion(){
        return this.connexion;
    }
    
    void setConnexion(Connection connexion){
        this.connexion = connexion;
    }
    void closeConnexion() throws SQLException {
        this.connexion.close();
    }
}
