package com.example.lista2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Data {

    public static class Exercise implements Parcelable {
        public String content;
        public int points;

        public Exercise(String content, int points) {
            this.content = content;
            this.points = points;
        }

        protected Exercise(Parcel in) {
            content = in.readString();
            points = in.readInt();
        }

        public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
            @Override
            public Exercise createFromParcel(Parcel in) {
                return new Exercise(in);
            }

            @Override
            public Exercise[] newArray(int size) {
                return new Exercise[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(content);
            dest.writeInt(points);
        }
    }
    public static class SubjectAverage {
        private String name;
        private float averageGrade;
        private int listNumber;

        public SubjectAverage(String name, float averageGrade) {
            this.name = name;
            this.averageGrade = averageGrade;
        }

        public String getName() {
            return name;
        }
        public int getListNumber() {
            return listNumber;
        }
        public void setListNumber(int listNumber) {
            this.listNumber = listNumber;
        }

        public float getAverageGrade() {
            return averageGrade;
        }
    }

    public static class Subject {
        public String name;

        public Subject(String name) {
            this.name = name;
        }
    }

    public static class ExerciseList {
        public List<Exercise> exercises;
        public Subject subject;
        public float grade;
        public int listNumber;

        public ExerciseList(List<Exercise> exercises, Subject subject, float grade) {
            this.exercises = exercises;
            this.subject = subject;
            this.grade = grade;
        }
        public int getListNumber() {
            return listNumber;
        }
    }


    private static final String[] SUBJECTS = {"Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy"};

    public static List<ExerciseList> generateTaskLists(int numberOfLists) {
        List<ExerciseList> taskLists = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfLists; i++) {
            int numberOfExercises = random.nextInt(10) + 1;

            Subject subject = new Subject(SUBJECTS[random.nextInt(SUBJECTS.length)]);

            List<Exercise> exercises = new ArrayList<>();
            for (int j = 0; j < numberOfExercises; j++) {
                String loremIpsum = generateLoremIpsum();
                int points = random.nextInt(10) + 1;
                exercises.add(new Exercise(loremIpsum, points));
            }

            float grade = (random.nextInt(5) + 6) / 2.0f;

            taskLists.add(new ExerciseList(exercises, subject, grade));
        }

        return taskLists;
    }

    private static String generateLoremIpsum() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit...";
    }

    public static void main(String[] args) {
        List<ExerciseList> generatedLists = generateTaskLists(20);

        for (ExerciseList taskList : generatedLists) {
            System.out.println("Subject: " + taskList.subject.name);
            System.out.println("Grade: " + taskList.grade);
            System.out.println("Exercises:");

            for (Exercise exercise : taskList.exercises) {
                System.out.println("  - Content: " + exercise.content);
                System.out.println("    Points: " + exercise.points);
            }
        }
    }
}