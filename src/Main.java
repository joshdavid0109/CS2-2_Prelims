import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    private static Scanner kbd = new Scanner(System.in);
    private static List<rowData> rowDataList = new ArrayList<>();

    public static void main(String[] args) {

        int choice = 0;


        readDataSet();

        System.out.println("Pili ka muna 1, 2, 3");

        choice = kbd.nextInt()  ;

        switch (choice) {
            // Filter data
            case 1:
                filterDataAccordingtoColumns();
                break;
            // sort
            case 2:
                sortDataAccordingToColumns();
                break;
        }





//        System.out.println(rowDataList.toString());


    }


    // Sort in alphabetical order
    private static void sortDataAccordingToColumns() {
        System.out.println("SORTING OF DATA");
        int choice = 0, c = 0;
        System.out.println("[1] Sort according to date");
        System.out.println("[2] Sort String Data");
        System.out.println("[2] Sort Integers/Double Data");


        choice = kbd.nextInt();

        if (choice == 1) {
            System.out.println("[1] Location");
            System.out.println("[2] Operational Area");
            c = kbd.nextInt();
            List<rowData> sortedNames;
            switch(c) {
                case 1:
                     sortedNames = rowDataList.stream()
                            .sorted(Comparator.comparing(rowData::getLocation))
                            .collect(Collectors.toList());
                    System.out.println(sortedNames);
                    break;
                case 2:
                    sortedNames = rowDataList.stream()
                            .sorted(Comparator.comparing(rowData::getOperationalArea))
                            .collect(Collectors.toList());
                    System.out.println(sortedNames);
                    break;
            }
        }
        else if (choice == 2) {
            System.out.println();
        }



    }

    private static void filterDataAccordingtoColumns() {
        System.out.println("FILTERING DATA");
        int c = 0;
        List<rowData> distinctV;
        List<rowData> result;
        showFilterColumnChoices();
        c = kbd.nextInt();
        int choice = 0;
        switch (c) {
            case 1:
                distinctV = rowDataList.stream().filter(distinctByKey(rowData::getMaterial)).collect(Collectors.toList());
                do {
                    showMaterialChoices(distinctV);
                    switch (choice) {
                        case 1 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(0).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 2 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(1).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 3 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(2).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 4 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(3).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 5 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(4).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 6 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(5).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 7 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(6).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 8 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(7).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 9 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(8).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 10 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(9).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 11 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(10).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 12 -> {
                            result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(11).getMaterial())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        default -> {
                        }
                    }

                    System.out.print("\nchoice"); // pang stop ng loop
                    choice = kbd.nextInt();
                } while (choice != distinctV.size() +1);

                break;
            case 2:
                distinctV = rowDataList.stream().filter(distinctByKey(rowData::getOperationalArea)).collect(Collectors.toList());

                do {
                    showOAChoices(distinctV);
                    switch (choice) {
                        case 1 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(0).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 2 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(1).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 3 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(2).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 4 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(3).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 5 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(4).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 6 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(5).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 7 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(6).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 8 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(7).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 9 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(8).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 10 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(9).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 11 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(10).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 12 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(11).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 13 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(12).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        case 14 -> {
                            result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(13).getOperationalArea())).toList();
                            printHeaders();
                            System.out.println(result);
                        }
                        default -> {
                        }
                    }

                    System.out.print("\nchoice"); // pang stop ng loop
                    choice = kbd.nextInt();
                } while (choice != distinctV.size() +1);
                break;

            case 3:
                distinctV = rowDataList.stream().filter(distinctByKey(rowData::getOwner)).collect(Collectors.toList());
               do {
                    showOwnershipChoices(distinctV);
                   switch (choice) {
                       case 1 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(0).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 2 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(1).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 3 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(2).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 4 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(3).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 5 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(4).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 6 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(5).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 7 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(6).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 8 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(7).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 9 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(8).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 10 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(9).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 11 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(10).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       case 12 -> {
                           result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(11).getOwner())).toList();
                           printHeaders();
                           System.out.println(result);
                       }
                       default -> {
                       }
                   }

                   System.out.print("\nchoice"); // pang stop ng loop
                    choice = kbd.nextInt();
                } while (choice != distinctV.size() +1);
                break;

            default:
                break;
        }
    }

    private static void showOwnershipChoices(List<rowData> list) {
        System.out.println("\t List of Ownerships");
        for (int i = 1; i <= list.size() + 1; i++) {
            if (i == list.size() + 1) {
                System.out.printf("[%d] " + "Go Back\n", i);
                continue;
            }
            System.out.printf("[%d] " + list.get(i-1).getOwner(), i);
            System.out.println();
        }
    }

    private static void showMaterialChoices(List<rowData> list) {
        System.out.println("\t List of Materials");
        for (int i = 1; i <= list.size() + 1; i++) {
            if (i == list.size() + 1) {
                System.out.printf("[%d] " + "Go Back\n", i);
                continue;
            }
            System.out.printf("[%d] " + list.get(i-1).getMaterial(), i);
            System.out.println();
        }
    }

    private static void printHeaders() {
        System.out.printf("%n%-15s%-15s%-15s%-50s%-10s%-10s%-10s%-10s%-15s%-10s%-10s%-30s%-20s%-15s%-10s%-15s%-20s%-20s%-20s%n",
                "Object ID", "Facility ID", "Legacy ID", "Location", "Depth US", "Depth DS", "Height", "Invert US", "Invert DS",
                "Diameter", "Width", "Modified Date", "Material", "Form", "pSlope", "Admin Area", "Shape Length",
                "Operational Area", "Owner");
    }

    private static void showFilterColumnChoices() {
        System.out.println("[1] Material");
        System.out.println("[2] Operational Area");
        System.out.println("[3] Ownership");
        System.out.println("[4] Go Back");
    }

    private static void showOAChoices(List<rowData> list) {
        System.out.println("\t List of Operational Areas");
        for (int i = 1; i <= list.size() + 1; i++) {
            if (i == list.size() + 1) {
                System.out.printf("[%d] " + "Go Back\n", i);
                continue;
            }
            System.out.printf("[%d] " + list.get(i-1).getOperationalArea(), i);
            System.out.println();
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


    private static void readDataSet() {
        String line = "";
        int temp = -1;
        int i = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("stormwater-pipes_3.csv"));
            // Reading the file line by line and stopping when it reaches the 1000th line.


            while (((line = br.readLine()) != null) && i <= 1000) {
                String [] rowData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String columnHeader = "location";

                if (temp == -1) {
                    temp = findColumn(rowData, columnHeader);
                    continue;
                }

                rowDataList.add(new rowData(rowData[0], rowData[1], rowData[2], rowData[3],
                        rowData[4], rowData[5], rowData[6], rowData[7], rowData[8], rowData[9],
                        rowData[10], rowData[11], rowData[12], rowData[13], rowData[14], rowData[15],
                        rowData[16], rowData[17], rowData[18]));


            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int findColumn(String [] rowData, String cHeader) {
        int x = 0;
        for (int i = 1; i <= rowData.length; i++) {
            if (rowData[i].equals(cHeader)){
                x = i;
                return x;
            }
        }
        return x;
    }
}