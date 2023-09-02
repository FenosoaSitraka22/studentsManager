package com.fns.studentsmanager.data;

import com.fns.studentsmanager.entities.Etudiant;
import com.mysql.cj.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EtudiantRepository {
    private com.fns.studentsmanager.data.DbConnection dbConnection = new DbConnection();

    public  Etudiant saveEtudiant(Etudiant etudiant){
        dbConnection.getDBConnection();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnexion().prepareStatement("insert into student(first_name,last_name,date_of_birth) value(?,?,?)");
            preparedStatement.setString(1, etudiant.getFirstName());
            preparedStatement.setString(2,etudiant.getLastName());
            preparedStatement.setString(3, etudiant.getDateOfBirth());
            preparedStatement.execute();
            preparedStatement = dbConnection.getConnexion().prepareStatement("Select last_insert_id() from student");
            ResultSet  resultSet = preparedStatement.executeQuery();
            resultSet.next();
            etudiant =new Etudiant(Integer.parseInt(resultSet.getString(1)),
                    etudiant.getFirstName(),
                    etudiant.getLastName(),
                    etudiant.getDateOfBirth());
            resultSet.close();
            preparedStatement.close();
            System.out.println("--------- id = "+ etudiant.getId());
            dbConnection.closeConnexion();
            return etudiant;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEtudiant(Etudiant etudiant){
        dbConnection.getDBConnection();
        try{
            PreparedStatement preparedStatement =dbConnection.getConnexion()
                    .prepareStatement("UPDATE student SET first_name = ? , last_name = ? , date_of_birth = ? WHERE id = ?");
            preparedStatement.setString(1, etudiant.getFirstName());
            preparedStatement.setString(2, etudiant.getLastName());
            preparedStatement.setString(3, etudiant.getDateOfBirth());
            preparedStatement.setString(4,String.format("%d",etudiant.getId()));
            preparedStatement.execute();
            dbConnection.closeConnexion();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteEtudiant(int id){
        try{
            dbConnection.getDBConnection();
            PreparedStatement preparedStatement =dbConnection.getConnexion()
                    .prepareStatement("DELETE FROM student  WHERE id = ?");
           preparedStatement.setString(1,String.format("%d",id));
            preparedStatement.execute();
            dbConnection.closeConnexion();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<Etudiant> getStudents(){
        ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
        dbConnection.getDBConnection();
        try{
            Statement statement = dbConnection.getConnexion().createStatement();
            ResultSet resultSet = statement.executeQuery("Select*From student order by first_name desc");
            while (resultSet.next()){
                etudiants.add(new Etudiant(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"),resultSet.getString("date_of_birth") ));
            }
            resultSet.close();
            statement.close();
            dbConnection.closeConnexion();
        }catch (Exception e){
            e.printStackTrace();
        }
        return etudiants;
    }
}
