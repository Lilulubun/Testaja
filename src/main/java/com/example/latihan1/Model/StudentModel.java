package com.example.latihan1.Model;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    private String nim;
    private String nama;
    private int semester;

    public StudentModel() {
    }

    public StudentModel(String nim, String nama, int semester) {
        this.nim = nim;
        this.semester = semester;
        this.nama = nama;
    }

    public String getNim() {
        return this.nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String toString() {
        return "StudentModel{nim='" + this.nim + "', nama='" + this.nama + "', semester=" + this.semester + "}";
    }
    public static class CSVUtil {
//        public static List<StudentModel> readStudentsFromCSV(InputStreamReader reader) {
//            List<StudentModel> students = new ArrayList<>();
//            try (CSVReader csvReader = new CSVReader(reader)) {
//                String[] line;
//                int lineCount = 0;
//                while ((line = csvReader.readNext()) != null) {
//                    lineCount++;
//                    System.out.println("Reading line " + lineCount + ": " + String.join(", ", line));
//                    if (lineCount == 1) {
//                        // Skip header row
//                        continue;
//                    }
//                    if (line.length < 3) {
//                        System.out.println("Skipping incomplete line: " + String.join(", ", line));
//                        continue;
//                    }
//                    String nim = line[0];
//                    String nama = line[1];
//                    int semester;
//                    try {
//                        semester = Integer.parseInt(line[2]);
//                    } catch (NumberFormatException e) {
//                        System.out.println("Skipping invalid semester value: " + line[2]);
//                        continue;
//                    }
//                    students.add(new StudentModel(nim, nama, semester));
//                }
//                System.out.println("Total lines read (including header): " + lineCount);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return students;
//        }
        public static List<StudentModel> readStudentsFromCSV(InputStreamReader reader) {
            List<StudentModel> students = new ArrayList<>();
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    String nim = line[0];
                    String nama = line[1];
                    int semester;
                    try {
                        semester = Integer.parseInt(line[2]);
                    } catch (NumberFormatException e) {
//                        System.out.println("Skipping invalid semester value: " + line[2]);
                        continue;
                    }
                    students.add(new StudentModel(nim, nama, semester));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return students;
        }
    }
}
