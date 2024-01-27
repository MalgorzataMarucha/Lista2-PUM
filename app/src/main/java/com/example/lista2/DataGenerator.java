package com.example.lista2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataGenerator {

    private static final List<String> subjects = Arrays.asList("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy");

    private static final Map<String, Integer> subjectCounters = new HashMap<>();

    public static List<Data.ExerciseList> generateData(int numberOfLists, int maxTasksPerList) {
        List<Data.ExerciseList> exerciseLists = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfLists; i++) {
            int tasksCount = random.nextInt(maxTasksPerList) + 1;
            List<Data.Exercise> exercises = generateExercises(tasksCount);

            String randomSubject = subjects.get(random.nextInt(subjects.size()));
            float randomGrade = generateRandomGrade();

            Data.Subject subject = new Data.Subject(randomSubject);

            int listNumber = subjectCounters.getOrDefault(randomSubject, 0) + 1;
            subjectCounters.put(randomSubject, listNumber);

            Data.ExerciseList exerciseList = new Data.ExerciseList(exercises, subject, randomGrade);
            exerciseList.listNumber = listNumber;

            exerciseLists.add(exerciseList);
        }

        return exerciseLists;
    }

    private static List<Data.Exercise> generateExercises(int numberOfTasks) {
        List<Data.Exercise> exercises = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfTasks; i++) {
            String content = generateLoremIpsum();
            int points = random.nextInt(10) + 1;

            Data.Exercise exercise = new Data.Exercise(content, points);
            exercises.add(exercise);
        }

        return exercises;
    }

    private static String generateLoremIpsum() {
        Random random = new Random();
        int sentenceCount = random.nextInt(5) + 1;

        StringBuilder loremIpsum = new StringBuilder();

        for (int i = 0; i < sentenceCount; i++) {
            int wordCount = random.nextInt(10) + 3;
            loremIpsum.append(generateRandomSentence(wordCount)).append(" ");
        }

        return loremIpsum.toString().trim();
    }

    private static String generateRandomSentence(int wordCount) {
        List<String> words = Arrays.asList(
                "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna", "aliqua"
        );

        StringBuilder sentence = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < wordCount; i++) {
            sentence.append(words.get(random.nextInt(words.size()))).append(" ");
        }

        return sentence.toString().trim();
    }

    private static float generateRandomGrade() {
        Random random = new Random();
        int steps = (int) ((5.0 - 3.0) / 0.5) + 1;
        float randomGrade = (random.nextInt(steps) * 0.5f) + 3.0f;
        return randomGrade;
    }

    public static void main(String[] args) {
        List<Data.ExerciseList> generatedData = generateData(20, 10);

        for (Data.ExerciseList exerciseList : generatedData) {
            System.out.println("Przedmiot: " + exerciseList.subject.name +
                    ", Ocena: " + exerciseList.grade +
                    ", Liczba zadań: " + exerciseList.exercises.size());
        }
    }
    public static List<Data.SubjectAverage> generateSubjectAverages(List<Data.ExerciseList> exerciseLists) {
        Map<String, List<Float>> gradesBySubject = new HashMap<>();

        for (Data.ExerciseList exerciseList : exerciseLists) {
            String subjectName = exerciseList.subject.name;
            float grade = exerciseList.grade;

            if (!gradesBySubject.containsKey(subjectName)) {
                gradesBySubject.put(subjectName, new ArrayList<>());
            }

            gradesBySubject.get(subjectName).add(grade);
        }

        List<Data.SubjectAverage> subjectAverages = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : gradesBySubject.entrySet()) {
            String subjectName = entry.getKey();
            List<Float> grades = entry.getValue();

            float averageGrade = calculateAverage(grades);
            int listNumber = grades.size();

            Data.SubjectAverage subjectAverage = new Data.SubjectAverage(subjectName, averageGrade);
            subjectAverage.setListNumber(listNumber);

            subjectAverages.add(subjectAverage);
        }

        return subjectAverages;
    }

    private static float calculateAverage(List<Float> grades) {
        float sum = 0;
        for (float grade : grades) {
            sum += grade;
        }

        return sum / grades.size();
    }

}