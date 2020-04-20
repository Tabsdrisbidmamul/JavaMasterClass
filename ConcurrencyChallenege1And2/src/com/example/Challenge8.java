package com.example;

public class Challenge8 {
    public static void main(String[] args) {
        Tutor tutor = new Tutor();
        Student student = new Student(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Tutor gets the lock on Tutor-object
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Student gets the lock on the Student-object
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

class Tutor {
    private Student student;

    public synchronized void setStudent(Student student) {
        this.student = student;
    }

    public synchronized void studyTime() {
        System.out.println("Tutor has arrived");
        try {
            // wait for student to arrive and hand in assignment
            wait(300);
        } catch (InterruptedException e) {

        }
        // Tries to obtain the Student lock, without the wait() the Tutor object is not released and the Student
        // cannot access the Tutor Object likewise the same goes for Student as well, the Tutor cannot access the
        // Student Object
        student.startStudy();
        System.out.println("Tutor is studying with student");
    }

    public synchronized void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class Student {

    private Tutor tutor;

    Student(Tutor tutor) {
        this.tutor = tutor;
    }

    public synchronized void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public synchronized void handInAssignment() {
        notifyAll();
        // without the notifyALL() the Student object is attempting to access the Tutor object - but it doesn't have
        // the lock, the notifyAll() will release the lock
        tutor.getProgressReport();
        System.out.println("Student handed in assignment");
    }
}

