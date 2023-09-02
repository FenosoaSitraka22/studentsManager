package com.fns.studentsmanager;

import com.fns.studentsmanager.data.DbConnection;
import com.fns.studentsmanager.data.EtudiantRepository;
import com.fns.studentsmanager.entities.Etudiant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("student"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        //Etudiant etudiant = new Etudiant();
        //System.out.println("--------------------");
        /*Scanner cs = new Scanner(System.in);
        System.out.println("saisir le nom");
        etudiant.setFirstName( cs.nextLine());
        System.out.println("saisir le prénom");
        etudiant.setLastName(cs.nextLine());/*
        System.out.println("date de naissance");
        etudiant.setDateOfBirth(cs.nextLine());
        EtudiantRepository er = new EtudiantRepository();
        er.saveEtudiant(etudiant);*/
        EtudiantRepository etudiantRepository = new EtudiantRepository();
        for ( Etudiant etudiant:etudiantRepository.getStudents()
             ) {
            System.out.println(etudiant.getId() +" " + etudiant.getLastName() + " "+etudiant.getFirstName()+" "+ etudiant.getDateOfBirth());
        }
        launch();

    }

}