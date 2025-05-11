package org.example;

import java.io.File;

public class IssueCounter {

    public static void main(String[] args) {
        File mainDirectory = new File("C:\\Users\\USER\\IdeaProjects\\STIWK3014");

        if (!mainDirectory.exists() || !mainDirectory.isDirectory()) {
            System.out.println("Directory does not exist or is not a folder.");
            return;
        }

        int javaFileCount = countJavaFiles(mainDirectory);
        int issueCount = countIssues(mainDirectory);

        System.out.println("Numbers of Java Files = " + javaFileCount);
        System.out.println("Number of Issues = " + issueCount);
    }

    private static int countJavaFiles(File dir) {
        int count = 0;
        File[] files = dir.listFiles();

        if (files == null) return 0;

        for (File file : files) {
            if (file.isDirectory()) {
                count += countJavaFiles(file);
            } else if (file.getName().endsWith(".java")) {
                count++;
            }
        }
        return count;
    }

    private static int countIssues(File dir) {
        int issueCount = 0;
        File[] files = dir.listFiles();

        if(files == null) return 0;

        for (File file : files) {
            if (file.isDirectory()) {
                if (countJavaFiles(file) > 0) {
                    issueCount++;
                }
            }
        }
        return issueCount;
    }
}
