/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fns.studentsmanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.fns.studentsmanager.data.EtudiantRepository;
import com.fns.studentsmanager.entities.Etudiant;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Nir'info
 */
public class StudentController implements Initializable {
    @FXML
    public TextField txFirstName;
    @FXML
    public  TextField txLastName;
    @FXML
    public  TextField txDateOfBirth;

    @FXML
    public Button btnSave;

    @FXML
    public  Button btnUpdate;

    @FXML
    public Button btnDelete;
    @FXML
    public Button btnNew;
    @FXML
    public TableView<Etudiant> tvListeStudents;

    @FXML
    public TableColumn<Etudiant,Integer> colId;
    @FXML
    public TableColumn<Etudiant,String> colFirstName;
    @FXML
    public TableColumn<Etudiant,String> colLastName;
    @FXML
    public  TableColumn<Etudiant,String> colDateOfBirth;
    private EtudiantRepository etudiantRepository = new EtudiantRepository();
    @FXML
    private Etudiant etudiant;

    private ObservableList<Etudiant> etudiants ;
    public StudentController() {
    }
    public StudentController(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.etudiants = this.etudiantRepository.getStudents();
        showStudents();
    }
    @FXML
    public  void  addStudent(){
        Etudiant student = new Etudiant(txFirstName.getText(),txLastName.getText(),txDateOfBirth.getText());
        etudiants.add(this.etudiantRepository.saveEtudiant(student));
        this.txFirstName.clear();
        this.txLastName.clear();
        this.txDateOfBirth.clear();
        this.showStudents();
    }

    @FXML
    public void deleteEtudiant(MouseEvent mouseEvent){
        if(this.etudiant!=null){
            this.etudiantRepository.deleteEtudiant(this.etudiant.getId());
            this.etudiants.forEach(etudiant1 -> {
                System.out.println("---------"+etudiant1.getFirstName()+"+++++"+ this.etudiants.indexOf(this.etudiant));
            });
            this.etudiants = this.etudiantRepository.getStudents();

            this.txFirstName.clear();
            this.txLastName.clear();
            this.txDateOfBirth.clear();
            this.btnDelete.setDisable(true);
            this.showStudents();
        }
    }

    @FXML
    public void showStudents(){
        this.colId.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("id"));
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("firstName"));
        this.colLastName.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("lastName"));
        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("dateOfBirth"));
        this.tvListeStudents.setItems(this.etudiants);
        this.tvListeStudents.refresh();
    }
    @FXML
    private void mouseClicked(MouseEvent mouseEvent){
        try {
            Etudiant etudiant = this.tvListeStudents.getSelectionModel().getSelectedItem();
            this.etudiant = new Etudiant(etudiant.getId(),etudiant.getFirstName(),etudiant.getLastName(),etudiant.getDateOfBirth());
            this.txFirstName.setText(this.etudiant.getFirstName());
            this.txLastName.setText(this.etudiant.getLastName());
            this.txDateOfBirth.setText(this.etudiant.getDateOfBirth());
            this.btnDelete.setDisable(false);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private  void updateStudent(){

        this.etudiantRepository.updateEtudiant(new Etudiant(this.etudiant.getId(),this.txFirstName.getText(),this.txLastName.getText(),this.txDateOfBirth.getText()));
       /* this.etudiants.set(this.etudiants.indexOf(this.etudiant),
                new Etudiant(this.etudiant.getId(),this.txFirstName.getText(),this.txLastName.getText(),this.txDateOfBirth.getText()));
       */
        this.etudiants = etudiantRepository.getStudents();
        showStudents();
    }

}
