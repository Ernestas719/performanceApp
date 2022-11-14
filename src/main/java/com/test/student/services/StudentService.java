package com.test.student.services;

import com.test.student.models.SortingResponse;
import com.test.student.models.Student;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class StudentService {

    private static List<Student> students = new ArrayList<>();

    @PostConstruct
    private void init() {
        readStudentFromFile();
    }

    public static List<Student> getAllStudents() {
        return students;
    }

    private void readStudentFromFile() {

        String line = "";

        try {

            Scanner readFile = new Scanner(new File("C:\\Users\\User\\Desktop\\grades.txt"));
            while (readFile.hasNext()) {
                try {
                    line = readFile.nextLine();
                    String[] splittedLine = line.split(", ");
                    students.add(new Student(splittedLine[0], Double.parseDouble(splittedLine[1])));
                } catch (InputMismatchException e) {
                    readFile.next();
                }

            }
            System.out.println("Sorted Array order: " + students);
        } catch (FileNotFoundException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }

    public SortingResponse getBubbleSort() {
        double[] arr = students.stream().mapToDouble(Student::getGrade).toArray();
        int n = arr.length;
        double temp = 0;
        long startTime = System.nanoTime();
        long endTime = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        endTime = System.nanoTime();
        return mapToServiceResponse(arr, (endTime - startTime));
    }

    public SortingResponse mapToServiceResponse(double[] arr, long time) {
        return new SortingResponse(arr, time);
    }

    public List<Student> addStudent(Student student) {
        students.add(student);
        return students;
    }
}
