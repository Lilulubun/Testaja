package com.example.latihan1.Controller;

import com.example.latihan1.Model.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class StudentController {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<StudentModel> table;
    @FXML
    private TableColumn<StudentModel, String> nimColumn;
    @FXML
    private TableColumn<StudentModel, String> namaColumn;
    @FXML
    private TableColumn<StudentModel, Integer> semesterColumn;
    @FXML
    private final ObservableList<StudentModel> studentList = FXCollections.observableArrayList();

    public StudentController() {
    }

    @FXML
    private void initialize() {
        // Initialize the student table with the columns.
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));

        // Load data from CSV file.
        loadStudentDataFromCSV("/com/example/latihan1/students.csv");
        table.setItems(studentList);
    }

    private void loadStudentDataFromCSV(String resourcePath) {
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.out.println("Resource not found: " + resourcePath);
                return;
            }
            try (InputStreamReader isr = new InputStreamReader(is)) {
                List<StudentModel> students = StudentModel.CSVUtil.readStudentsFromCSV(isr);
                studentList.addAll(students);
                table.setItems(studentList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAdd() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Student");
        dialog.setHeaderText("Enter new student details");
        dialog.setContentText("NIM:");
        Optional<String> nim = dialog.showAndWait();
        if (nim.isPresent()) {
            dialog.setContentText("Nama:");
            Optional<String> nama = dialog.showAndWait();
            if (nama.isPresent()) {
                dialog.setContentText("Semester:");
                Optional<String> semesterStr = dialog.showAndWait();
                if (semesterStr.isPresent()) {
                    int semester;
                    try {
                        semester = Integer.parseInt((String)semesterStr.get());
                    } catch (NumberFormatException var7) {
                        this.showAlert("Invalid Input", "Semester must be a number.");
                        return;
                    }

                    this.studentList.add(new StudentModel((String)nim.get(), (String)nama.get(), semester));
                }
            }
        }
    }

    @FXML
    public void handleEdit() {
        StudentModel selectedStudent = (StudentModel)this.table.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            this.showAlert("No Selection", "Please select a student to edit.");
        } else {
            TextInputDialog dialog = new TextInputDialog(selectedStudent.getNim());
            dialog.setTitle("Edit Student");
            dialog.setHeaderText("Edit student details");
            dialog.setContentText("NIM:");
            Optional<String> nim = dialog.showAndWait();
            if (nim.isPresent()) {
                selectedStudent.setNim((String)nim.get());
                dialog.setContentText("Nama:");
                Optional<String> nama = dialog.showAndWait();
                if (nama.isPresent()) {
                    selectedStudent.setNama((String)nama.get());
                    dialog.setContentText("Semester:");
                    Optional<String> semesterStr = dialog.showAndWait();
                    if (semesterStr.isPresent()) {
                        int semester;
                        try {
                            semester = Integer.parseInt((String)semesterStr.get());
                        } catch (NumberFormatException var8) {
                            this.showAlert("Invalid Input", "Semester must be a number.");
                            return;
                        }

                        selectedStudent.setSemester(semester);
                        this.table.refresh();
                    }
                }
            }
        }
    }

    @FXML
    public void handleDelete() {
        StudentModel selectedStudent = (StudentModel)this.table.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            this.showAlert("No Selection", "Please select a student to delete.");
        } else {
            this.studentList.remove(selectedStudent);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText((String)null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}