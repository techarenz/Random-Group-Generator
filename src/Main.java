import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        driver("Year 11", 5);

    }

    private static ArrayList<String> readFromFile(String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

        ArrayList<String> students = new ArrayList<>();

        while (scanner.hasNextLine()) {

            students.add(scanner.nextLine());

        }

        return students;
    }

    private static ArrayList<String[]> determineGroups(ArrayList<String> students, int groupSize) {

        if (groupSize < 0 || groupSize > students.size()) throw new IllegalArgumentException("The size of the group cannot be less than 0 or exceed the total number of students! You have requested that the number of students in each group be " + groupSize + " when there are " + students.size() + " students in your class!");

        ArrayList<String[]> groups = new ArrayList<>();

        String[] group;

        int randomNumber;
        int groupSizeCounter = groupSize;

        while (!students.isEmpty()) {

            group = new String[groupSize];

            while (groupSizeCounter > 0 && !students.isEmpty()) {

                randomNumber = (int) (Math.random() * students.size());

                group[groupSizeCounter-1] = students.remove(randomNumber);

                groupSizeCounter--;

            }

            groups.add(group);

            groupSizeCounter = groupSize;

        }

        return groups;

    }

    private static void printGroups(ArrayList<String[]> groups) {

        for (int i = 0; i < groups.size(); i++) {

            System.out.println("Group " + (i+1));
            printGroup(groups.get(i));
            System.out.println();

        }

    }

    private static void printGroup(String[] group) {

        for (String member: group) {

            if (member != null) System.out.print(member + " ");

        }

        System.out.println();

    }

    private static void driver(String filename, int groupSize) throws FileNotFoundException {

        ArrayList<String> students = readFromFile(filename);
        ArrayList<String[]> groups = determineGroups(students, groupSize);
        printGroups(groups);

    }

}