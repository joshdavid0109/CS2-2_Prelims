/**
 * AUTHORS: Abella, Jahn Crystan - 2224514
 * David, Joshua Daniel - 2222551
 * Decena, Alexcious Norlan - 2221089
 * Molintas, Jonison Martel - 2211261
 * Tarlit, Ariel Jr. - 2221621
 * SUBMITTED TO: Mrs. Ma. Concepcion Clemente
 * DATE: 2/2/2023
 **/

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import java.util.stream.Collectors;


public class Main {

    private static Scanner kbd = new Scanner(System.in);
    private static List<rowData> rowDataList = new ArrayList<>();

    /**
     * The function runs a loop that prints the menu, takes the user's choice, and then runs the corresponding function
     */
    public static void run() {
        readDataSet();
        int choiceMenu;

        do {
            printMenu();
            choiceMenu = kbd.nextInt();
            switch (choiceMenu) {
                case 1 -> printData();
                case 2 -> sortDataAccordingToColumns();
                case 3 -> filterDataAccordingtoColumns();
                case 4 -> aggregateFunctions();
                case 5 -> {
                    System.out.println("Thank you for using our program!!");
                }
            }
        } while (choiceMenu != 5);
    }

    public static void main(String[] args) {
        printIntroduction();

        run();

    }

    public static void printIntroduction() {
        System.out.println("Activity 0 - Information Management");
        System.out.println("\033[3mA Java Program for Reading Data\033[0m\n"); // change niyo nalang hehe
        System.out.println("Press enter to continue... ");
        kbd.nextLine();
    }

    public static void printMenu() {
        System.out.println("---- MAIN MENU ----");
        System.out.println("[1] Print the data");
        System.out.println("[2] Sort Data");
        System.out.println("[3] Filter Data");
        System.out.println("[4] Aggregate Functions");
        System.out.println("[5] Exit the program\n");

        System.out.print("\nInput your choice: ");
    }

    // Case [1]
    /**
     * This function prints the data in the rowDataList ArrayList, and then waits for the user to press enter twice
     */
    private static void printData() {
        System.out.println(rowDataList.toString().replace(",", ""));
        System.out.println("Press enter to continue...");
        kbd.nextLine();
        kbd.nextLine();

    }

    /**
     * This function is used to display the submenu for the aggregate functions and then calls the appropriate function to
     * perform the desired operation
     *
     * 1. AVERAGES
     * 2. MINIMUMS
     * 3. MAXIMUMS
     * 4. SUMS
     * 5. FREQUENCIES
     */
    private static void aggregateFunctions() {
        int choice = 0;

        do {
            functionsSubMenu();
            System.out.print("\nInput your choice:  ");
            choice = kbd.nextInt();
            switch (choice) {
                // Averages
                case 1:
                    System.out.println("\t\t\t\t\t\t\t\t--------------------AVERAGES-------------------");
                    System.out.printf("%-17s%-17s%-17s%-17s%-17s%-17s%-17s%-17s%n", "Depth US", "Depth DS", "Height",
                            "Invert US", "Invert DS", "Diameter", "PSlope", "Shape Length");
                    List<Double> Averages = solveAverage();
                    for (double d : Averages) {
                        System.out.printf("%-17.2f", d);
                    }

                    pressEnter();

                    break;

                // Min
                case 2:

                    System.out.println("\n\t\t\t\t\t\t\t\t--------------------MINIMUMS-------------------");
                    System.out.printf("%-17s%-17s%-17s%-17s%-17s%-17s%-17s%-17s%n", "Depth US", "Depth DS", "Height",
                            "Invert US", "Invert DS", "Diameter", "PSlope", "Shape Length");
                    List<Double> Mins = getMin();
                    for (double d : Mins) {
                        System.out.printf("%-17.2f", d);
                    }
                    pressEnter();
                    break;

                // Max
                case 3:
                    System.out.println("\n\t\t\t\t\t\t\t\t--------------------MAXIMUMS-------------------");
                    System.out.printf("%-17s%-17s%-17s%-17s%-17s%-17s%-17s%-17s%n", "Depth US", "Depth DS", "Height",
                            "Invert US", "Invert DS", "Diameter", "PSlope", "Shape Length");
                    List<Double> Maxs = getMax();
                    for (double d : Maxs)
                        System.out.printf("%-17.2f", d);
                    pressEnter();
                    break;

                // Sums
                case 4:
                    System.out.println("\n\t\t\t\t\t\t\t\t--------------------SUMS-------------------");
                    System.out.printf("%-17s%-17s%-17s%-17s%-17s%-17s%-17s%-17s%n", "Depth US", "Depth DS", "Height",
                            "Invert US", "Invert DS", "Diameter", "PSlope", "Shape Length");
                    List<Double> sums = getSum();
                    for (double d : sums) {
                        System.out.printf("%-17.2f", d);
                    }
                    System.out.println();
                    pressEnter();
                    break;

                // Frequencies
                case 5:
                    int c = 0;
                    do {
                        System.out.println("[1] Frequency of Materials Used");
                        System.out.println("[2] Frequency of pipes existing in each Operational Area");
                        System.out.println("[3] Go Back");
                        c = kbd.nextInt();
                        switch (c) {
                            case 1:
                                HashMap<String, Integer> matFreq = getMaterialFrequency();
                                for (Map.Entry<String, Integer> entry: matFreq.entrySet())
                                    System.out.println((entry.getKey().equals("")? "No Material" + " = " + entry.getValue(): entry.getKey()) + " = " + entry.getValue());
                                pressEnter();
                                break;
                            case 2:
                                HashMap<String, Integer> pipeFreq = getPipeFrequency();
                                for (Map.Entry<String, Integer> entry: pipeFreq.entrySet())
                                    System.out.println(entry.getKey().equals("")? "No Location" + " = " + entry.getValue(): entry.getKey() + " = " + entry.getValue());
                                pressEnter();
                                break;
                            default:
                                break;
                        }
                    } while (c != 3);
                    break;
                default:
                    break;
            }
        } while (choice != 6    );
    }

    /**
     * It takes a list of objects, filters out the distinct values of a specific property, then counts the number of times
     * each distinct value appears in the list
     *
     * @return A HashMap with the key being the operational area and the value being the frequency of that operational
     * area.
     */
    private static HashMap<String, Integer> getPipeFrequency() {
        List<rowData> distinctV = rowDataList.stream().filter(distinctByKey(rowData::getOperationalArea)).toList();
        HashMap<String, Integer> hash = new HashMap<>();

        for (int i = 0; i < distinctV.size(); i++) {
            int finalI = i;
            List<rowData> result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(finalI).getOperationalArea())).toList();
            hash.put(distinctV.get(finalI).getOperationalArea(), result.size() );
        }

        return hash;
    }

    private static void functionsSubMenu() {
        System.out.println("---- AGGREGATION OPTIONS ----");
        System.out.println("[1] Averages");
        System.out.println("[2] Minimums");
        System.out.println("[3] Maximums");
        System.out.println("[4] Sums");
        System.out.println("[5] Frequencies");
        System.out.println("[6] Go Back");
    }

    /**
     * It takes a list of objects, filters out the distinct values of a specific property of the object, then counts the
     * number of times each distinct value appears in the list
     *
     * @return A HashMap of the material and the frequency of that material.
     */
    private static HashMap<String, Integer> getMaterialFrequency() {
        List<rowData> distinctV = rowDataList.stream().filter(distinctByKey(rowData::getMaterial)).toList();
        HashMap<String, Integer> hash = new HashMap<>();

        for (int i = 0; i < distinctV.size(); i++) {
            int finalI = i;
            List<rowData> result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(finalI).getMaterial())).toList();
            hash.put(distinctV.get(finalI).getMaterial(), result.size() );
        }

        return hash;
    }


    /**
     * This function is used to calculate the sum of each column in the table
     *
     * @return A list of doubles.
     */
    private static List<Double> getSum() {
        double sumDUS = 0;
        double sumDDS = 0;
        double sumHeight = 0;
        double sumInvertUS = 0;
        double sumInvertDS = 0;
        double sumDiameter = 0;
        double sumPSlope = 0;
        double sumShapeLength = 0;
        List<Double> sumList = new ArrayList<>();

        for (rowData rd : rowDataList) {
            sumDUS += rd.getDepthUS().equals("")? 0: Double.parseDouble(rd.getDepthUS());
            sumDDS += rd.getDepthDS().equals("")?  0: Double.parseDouble(rd.getDepthDS());
            sumHeight += rd.getHeight().equals("")? 0 : Double.parseDouble(rd.getHeight());
            sumInvertUS += rd.getInvertUS().equals("")? 0:Double.parseDouble(rd.getInvertUS());
            sumInvertDS += rd.getInvertDS().equals("")? 0: Double.parseDouble(rd.getInvertDS());
            sumDiameter += rd.getDiameter().equals("")? 0:Double.parseDouble(rd.getDiameter());
            sumPSlope += rd.getpSlope().equals("")? 0 :Double.parseDouble(rd.getpSlope());
            sumShapeLength += rd.getShapeLength().equals("")? 0:Double.parseDouble(rd.getShapeLength());
        }

        sumList.add(sumDUS);
        sumList.add(sumDDS);
        sumList.add(sumHeight);
        sumList.add(sumInvertUS);
        sumList.add(sumInvertDS);
        sumList.add(sumDiameter);
        sumList.add(sumPSlope);
        sumList.add(sumShapeLength);
        return sumList;
    }


    /**
     * It returns a list of the minimum values of each attribute in the rowDataList
     *
     * @return A list of the minimum values for each attribute.
     */
    private static List<Double> getMin() {
        List<Double> Mins = new ArrayList<>();
        rowData minDUS = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getDepthUS().equals("")? 0: Double.parseDouble(s.getDepthUS()))).orElseThrow(NoSuchElementException::new);
        double dus = (minDUS.getDepthUS().equals("") ? 0 : Double.parseDouble(minDUS.getDepthUS()));
        Mins.add(dus);

        rowData minDDS = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getDepthDS().equals("")? 0: Double.parseDouble(s.getDepthDS()))).orElseThrow(NoSuchElementException::new);
        double dds = (minDDS.getDepthDS().equals("") ? 0 : Double.parseDouble(minDDS.getDepthDS()));
        Mins.add(dds);

        rowData height = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getHeight().equals("")? 0: Double.parseDouble(s.getHeight()))).orElseThrow(NoSuchElementException::new);
        double h = (height.getHeight().equals("") ? 0 : Double.parseDouble(height.getHeight()));
        Mins.add(h);

        rowData iUS = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getInvertUS().equals("")? 0: Double.parseDouble(s.getInvertUS()))).orElseThrow(NoSuchElementException::new);
        double invUS = (iUS.getInvertUS().equals("") ? 0 : Double.parseDouble(iUS.getInvertUS()));
        Mins.add(invUS);

        rowData iDS = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getInvertDS().equals("")? 0: Double.parseDouble(s.getInvertDS()))).orElseThrow(NoSuchElementException::new);
        double invDS = (iDS.getInvertDS().equals("") ? 0 : Double.parseDouble(iDS.getInvertDS()));
        Mins.add(invDS);

        rowData dia = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getDiameter().equals("")? 0: Double.parseDouble(s.getDiameter()))).orElseThrow(NoSuchElementException::new);
        double d = (dia.getDiameter().equals("") ? 0 : Double.parseDouble(dia.getDiameter()));
        Mins.add(d);

        rowData psl = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getpSlope().equals("")? 0: Double.parseDouble(s.getpSlope()))).orElseThrow(NoSuchElementException::new);
        double slope = (psl.getpSlope().equals("") ? 0 : Double.parseDouble(psl.getpSlope()));
        Mins.add(slope);

        rowData shapel = rowDataList.stream().min(Comparator.comparingDouble(s -> s.getShapeLength().equals("")? 0: Double.parseDouble(s.getShapeLength()))).orElseThrow(NoSuchElementException::new);
        double sl = (shapel.getShapeLength().equals("") ? 0 : Double.parseDouble(shapel.getShapeLength()));
        Mins.add(sl);

        return Mins;
    }

    /**
     * It returns a list of doubles that are the maximum values of the following: DepthUS, DepthDS, Height, InvertUS,
     * InvertDS, Diameter, pSlope, ShapeLength
     *
     * @return A list of doubles.
     */
    private static List<Double> getMax() {
        List<Double> Maxs = new ArrayList<>();
        rowData maxDUS = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getDepthUS()).equals("")? 0: Double.parseDouble(s.getDepthUS()))).orElseThrow(NoSuchElementException::new);
        double dus = (maxDUS.getDepthUS().equals("") ? 0 : Double.parseDouble(maxDUS.getDepthUS()));
        Maxs.add(dus);

        rowData maxDDS = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getDepthDS().equals("")? 0: Double.parseDouble(s.getDepthDS())))).orElseThrow(NoSuchElementException::new);
        double dds = (maxDDS.getDepthDS().equals("") ? 0 : Double.parseDouble(maxDDS.getDepthDS()));
        Maxs.add(dds);

        rowData height = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getHeight().equals(""))? 0 :Double.parseDouble(s.getHeight()))).orElseThrow(NoSuchElementException::new);
        double h = (height.getHeight().equals("") ? 0 : Double.parseDouble(height.getHeight()));
        Maxs.add(h);

        rowData iUS = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getInvertUS().equals(""))? 0 :Double.parseDouble(s.getInvertUS()))).orElseThrow(NoSuchElementException::new);
        double invUS = (iUS.getInvertUS().equals("") ? 0 : Double.parseDouble(iUS.getInvertUS()));
        Maxs.add(invUS);

        rowData iDS = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getInvertDS().equals(""))? 0 :Double.parseDouble(s.getInvertDS()))).orElseThrow(NoSuchElementException::new);
        double invDS = (iDS.getInvertDS().equals("") ? 0 : Double.parseDouble(iDS.getInvertDS()));
        Maxs.add(invDS);

        rowData dia = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getDiameter().equals(""))? 0 :Double.parseDouble(s.getDiameter()))).orElseThrow(NoSuchElementException::new);
        double d = (dia.getDiameter().equals("") ? 0 : Double.parseDouble(dia.getDiameter()));
        Maxs.add(d);

        rowData psl = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getpSlope().equals(""))? 0 :Double.parseDouble(s.getpSlope()))).orElseThrow(NoSuchElementException::new);
        double slope = (psl.getpSlope().equals("") ? 0 : Double.parseDouble(psl.getpSlope()));
        Maxs.add(slope);

        rowData shapel = rowDataList.stream().max(Comparator.comparingDouble(s -> (s.getShapeLength().equals(""))? 0 :Double.parseDouble(s.getShapeLength()))).orElseThrow(NoSuchElementException::new);
        double sl = (shapel.getShapeLength().equals("") ? 0 : Double.parseDouble(shapel.getShapeLength()));
        Maxs.add(sl);

        return Maxs;
    }

    /**
     * This function takes the data from the rowDataList and sums up the values for each column, then divides by the number
     * of rows to get the average
     *
     * @return A list of doubles.
     */
    private static List<Double> solveAverage() {
        double sumDUS = 0;
        double sumDDS = 0;
        double sumHeight = 0;
        double sumInvertUS = 0;
        double sumInvertDS = 0;
        double sumDiameter = 0;
        double sumPSlope = 0;
        double sumShapeLength = 0;
        List<Double> averages = new ArrayList<>();

        for (rowData rd : rowDataList) {
            sumDUS += rd.getDepthUS().equals("")? 0: Double.parseDouble(rd.getDepthUS());
            sumDDS += rd.getDepthDS().equals("")?  0: Double.parseDouble(rd.getDepthDS());
            sumHeight += rd.getHeight().equals("")? 0 : Double.parseDouble(rd.getHeight());
            sumInvertUS += rd.getInvertUS().equals("")? 0:Double.parseDouble(rd.getInvertUS());
            sumInvertDS += rd.getInvertDS().equals("")? 0: Double.parseDouble(rd.getInvertDS());
            sumDiameter += rd.getDiameter().equals("")? 0:Double.parseDouble(rd.getDiameter());
            sumPSlope += rd.getpSlope().equals("")? 0 :Double.parseDouble(rd.getpSlope());
            sumShapeLength += rd.getShapeLength().equals("")? 0:Double.parseDouble(rd.getShapeLength());
        }
        averages.add(sumDUS / rowDataList.size());
        averages.add(sumDDS / rowDataList.size());
        averages.add(sumHeight / rowDataList.size());
        averages.add(sumInvertUS / rowDataList.size());
        averages.add(sumInvertDS / rowDataList.size());
        averages.add(sumDiameter / rowDataList.size());
        averages.add(sumPSlope / rowDataList.size());
        averages.add(sumShapeLength / rowDataList.size());

        return averages;
    }


    // Sort in alphabetical order
    /**
     * It sorts the data according to the user's choice
     */
    private static void sortDataAccordingToColumns() {
        System.out.println("\n---- SORTING OF DATA ----");
        int choice = 0, c = 0, x = 0;
        
        do {
            System.out.println("---- SEQUENCE OF SORTING ----");
            System.out.println("[1] Ascending ");
            System.out.println("[2] Descending");
            System.out.println("[3] Go Back");
            System.out.print("\n\nInput your choice: ");
            x = kbd.nextInt();
            switch (x) {
                case 1:
                    ascendingOrder(choice,c);
                    break;
                case 2:
                    descendingOrder(choice, c);
                    break;
                default:
                    break;
            }

        } while (x !=3 );

    }

    private static void metricSubMenu() {
        System.out.println("---- SORTING OF INTEGER/DECIMAL DATA ----");
        System.out.println("[1] Depth (Up Stream)");
        System.out.println("[2] Depth (Down Stream)");
        System.out.println("[3] Height");
        System.out.println("[4] Invert (Up Stream)");
        System.out.println("[5] Invert (Down Stream)");
        System.out.println("[6] Diameter");
        System.out.println("[7] Pipe Slope");
        System.out.println("[8] Shape Length");
        System.out.println("[9] Go Back");
    }

    private static void sortSubMenu() {
        System.out.println("---- SORTING SUB MENU ----");
        System.out.println("[1] Sort according to date");
        System.out.println("[2] Sort String Data");
        System.out.println("[3] Sort Integers/Double Data");
        System.out.println("[4] Go Back");
    }

    /**
     * This function is a sub-menu that allows the user to sort the data in descending order by date, location, operational
     * area, or any of the 8 metrics
     *
     * @param choice the user's choice from the main menu
     * @param c is the choice for the sub menu
     */
    private static void descendingOrder(int choice, int c) {
        do {
            sortSubMenu();
            System.out.print("\nInput your choice:  ");
            choice = kbd.nextInt();
            switch (choice){
                case 1:
                    List<rowData> sortedDates;
                    printHeaders();
                    sortedDates = rowDataList.stream()
                            .sorted(Comparator.comparing(rowData::getDate).reversed()).collect(Collectors.toList());
                    System.out.println(sortedDates.toString().replace(",", ""));
                    pressEnter();
                    break;
                case 2:
                    System.out.println("Columns that contain string data");
                    System.out.println("[1] Location");
                    System.out.println("[2] Operational Area");
                    System.out.println("[3] Go Back");
                    System.out.print("\n\nInput your choice:  ");
                    c = kbd.nextInt();
                    List<rowData> sortedNames;
                    switch (c) {
                        case 1:
                            sortedNames = rowDataList.stream()
                                    .sorted(Comparator.comparing(rowData::getLocation).reversed())
                                    .collect(Collectors.toList());
                            System.out.println(sortedNames.toString().replace(",", ""));
                            pressEnter();
                            break;
                        case 2:
                            sortedNames = rowDataList.stream()
                                    .sorted(Comparator.comparing(rowData::getOperationalArea).reversed())
                                    .collect(Collectors.toList());
                            System.out.println(sortedNames.toString().replace(",", ""));
                            pressEnter();
                            break;
                        default:
                            continue;
                    }
                    break;
                case 3:
                    List<rowData> temp;
                    do {
                        metricSubMenu();
                        System.out.print(" \nInput your choice:  ");
                        pressEnter();
                        c = kbd.nextInt();
                        switch (c) {
                            case 1 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getDepthUS)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 2 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getDepthDS)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 3 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getHeight)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 4 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getInvertUS)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 5 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getInvertDS)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 6 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getDiameter)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 7 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getpSlope)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 8 -> {
                                printHeaders();
                                temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getShapeLength)).reversed()).collect(Collectors.toList());
                                System.out.println(temp.toString().replace(",", ""));
                                pressEnter();
                            }
                            default -> {
                            }
                        }

                    } while (c != 9);
                    break;
            }
        } while (choice !=4);
    }

    /**
     * This function is used to sort the data in ascending order
     *
     * @param choice the user's choice for the sorting sub-menu
     * @param c choice
     */
    private static void ascendingOrder(int choice, int c) {
        do {
            sortSubMenu();
            System.out.print("\nInput your choice: ");
            choice = kbd.nextInt();
            if (choice == 1) {
                List<rowData> sortedDates;

                printHeaders();
                sortedDates = rowDataList.stream()
                        .sorted(Comparator.comparing(rowData::getDate)).collect(Collectors.toList());
                System.out.println(sortedDates.toString().replace(",", ""));
            } else if (choice == 2) {
                System.out.println("---- SORTING OF STRING DATA OPTIONS ----");
                System.out.println("[1] Location");
                System.out.println("[2] Operational Area");
                System.out.println("[3] Go Back");
                System.out.print("\nInput your choice:  ");
                c = kbd.nextInt();
                List<rowData> sortedNames;
                switch (c) {
                    case 1:
                        sortedNames = rowDataList.stream()
                                .sorted(Comparator.comparing(rowData::getLocation))
                                .collect(Collectors.toList());
                        System.out.println(sortedNames.toString().replace(",", ""));
                        break;
                    case 2:
                        sortedNames = rowDataList.stream()
                                .sorted(Comparator.comparing(rowData::getOperationalArea))
                                .collect(Collectors.toList());
                        System.out.println(sortedNames.toString().replace(",", ""));
                        break;
                    default:
                        continue;
                }

            } else if (choice == 3) {
                List<rowData> temp;
                do {
                    metricSubMenu();
                    System.out.print("\nInput your choice:  ");
                    c = kbd.nextInt();
                    switch (c) {
                        case 1 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getDepthUS))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        case 2 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getDepthDS))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        case 3 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getHeight))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        case 4 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getInvertUS))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        case 5 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getInvertDS))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        case 6 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getDiameter))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        case 7 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getpSlope))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        case 8 -> {
                            printHeaders();
                            temp = rowDataList.stream().sorted(Comparator.comparing((rowData::getShapeLength))).collect(Collectors.toList());
                            System.out.println(temp.toString().replace(",", ""));
                        }
                        default -> {
                        }
                    }

                } while (c != 9);
            }
        } while (choice != 4);
    }

    /**
     * This function filters the data according to the column headers
     */
    private static void filterDataAccordingtoColumns() {
        System.out.println("FILTERING OF DATA BASED ON COLUMN HEADERS");
        int c = 0;
        int choice = 0;
        do {
        List<rowData> distinctV;
        List<rowData> result;
        showFilterColumnChoices();
        System.out.print("\nInput your choice:  ");
        c = kbd.nextInt();
            switch (c) {
                case 1:
                    distinctV = rowDataList.stream().filter(distinctByKey(rowData::getMaterial)).collect(Collectors.toList());
                    do {
                        showMaterialChoices(distinctV);
                        System.out.print("\nInput your choice:  "); // pang stop ng loop
                        choice = kbd.nextInt();
                        switch (choice) {
                            case 1 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(0).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 2 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(1).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 3 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(2).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 4 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(3).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 5 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(4).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 6 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(5).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 7 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(6).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 8 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(7).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 9 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(8).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 10 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(9).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 11 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(10).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 12 -> {
                                result = rowDataList.stream().filter(s -> s.getMaterial().equals(distinctV.get(11).getMaterial())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            default -> {

                            }
                        }


                    } while (choice != distinctV.size() + 1);

                    break;
                case 2:
                    distinctV = rowDataList.stream().filter(distinctByKey(rowData::getOperationalArea)).collect(Collectors.toList());

                    do {
                        showOAChoices(distinctV);
                        System.out.print("Input your choice:"); // pang stop ng loop
                        choice = kbd.nextInt();
                        switch (choice) {
                            case 1 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(0).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 2 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(1).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 3 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(2).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 4 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(3).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 5 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(4).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 6 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(5).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 7 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(6).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 8 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(7).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 9 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(8).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 10 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(9).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 11 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(10).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 12 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(11).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 13 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(12).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 14 -> {
                                result = rowDataList.stream().filter(s -> s.getOperationalArea().equals(distinctV.get(13).getOperationalArea())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            default -> {
                            }
                        }
                    } while (choice != distinctV.size() + 1);
                    break;

                case 3:
                    distinctV = rowDataList.stream().filter(distinctByKey(rowData::getOwner)).collect(Collectors.toList());
                    do {
                        showOwnershipChoices(distinctV);
                        System.out.println("Input your choice: ");
                        choice = kbd.nextInt();
                        switch (choice) {
                            case 1 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(0).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 2 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(1).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 3 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(2).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 4 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(3).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 5 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(4).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 6 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(5).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 7 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(6).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 8 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(7).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 9 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(8).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 10 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(9).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 11 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(10).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            case 12 -> {
                                result = rowDataList.stream().filter(s -> s.getOwner().equals(distinctV.get(11).getOwner())).toList();
                                printHeaders();
                                System.out.println(result.toString().replace(",", ""));
                                pressEnter();
                            }
                            default -> {
                            }
                        }
                    } while (choice != distinctV.size() + 1);
                    break;

                default:
                    break;
            }
        } while (c != 4);
    }

    /**
     * It prints out a list of ownerships that the user can choose from
     *
     * @param list The list of rowData objects that are being displayed.
     */
    private static void showOwnershipChoices(List<rowData> list) {
        System.out.println("\t List of Ownerships");
        for (int i = 1; i <= list.size() + 1; i++) {
            if (i == list.size()) {
                System.out.printf("[%d] " + "No Ownership", i);
                System.out.println();
                continue;
            }
            if (i == list.size()+1) {
                System.out.printf("[%d] " + "Go Back\n", i);
                continue;
            }
            System.out.printf("[%d] " + list.get(i-1).getOwner(), i);
            System.out.println();
        }
    }

    /**
     * It prints out a list of materials that the user can choose from
     *
     * @param list The list of materials that the user can choose from.
     */
    private static void showMaterialChoices(List<rowData> list) {
        System.out.println("\t List of Materials");
        for (int i = 1; i <= list.size() + 1; i++) {
            if (i == list.size()) {
                System.out.printf("[%d] " + "No Material", i);
                System.out.println();
                continue;
            }
            if (i == list.size()+1) {
                System.out.printf("[%d] " + "Go Back\n", i);
                continue;
            }
            System.out.printf("[%d] " + list.get(i-1).getMaterial(), i);
            System.out.println();
        }
    }

    private static void printHeaders() {
        System.out.printf("%n%-15s%-15s%-15s%-50s%-10s%-10s%-10s%-15s%-15s%-10s%-10s%-30s%-20s%-15s%-10s%-15s%-20s%-20s%-20s%n",
                "Object ID", "Facility ID", "Legacy ID", "Location", "Depth US", "Depth DS", "Height", "Invert US", "Invert DS",
                "Diameter", "Width", "Modified Date", "Material", "Form", "pSlope", "Admin Area", "Shape Length",
                "Operational Area", "Owner");
    }

    private static void showFilterColumnChoices() {
        System.out.println("---- FILTERING OF DATA OPTIONS ----");
        System.out.println("[1] Material");
        System.out.println("[2] Operational Area");
        System.out.println("[3] Ownership");
        System.out.println("[4] Go Back");
    }

    /**
     * This function prints out the list of operational areas that the user can choose from
     *
     * @param list The list of rowData objects that contain the operational areas.
     */
    private static void showOAChoices(List<rowData> list) {
        System.out.println("\t List of Operational Areas");
        for (int i = 1; i <= list.size() + 1; i++) {
            if (i == list.size()) {
                System.out.printf("[%d] " + "No Operational Area", i);
                System.out.println();
                continue;
            }
            if (i == list.size()+1) {
                System.out.printf("[%d] " + "Go Back\n", i);
                continue;
            }
            System.out.printf("[%d] " + list.get(i-1).getOperationalArea(), i);
            System.out.println();
        }
    }

    /**
     * Given a function that extracts a key from a value, return a predicate that returns true if the key hasn't been seen
     * before.
     *
     * @param keyExtractor A function that extracts a key from a given element.
     * @return A Predicate that will return true if the keyExtractor.apply(t) has not been seen before.
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static void pressEnter() {
        System.out.println("\nPress Enter to Continue");
        kbd.nextLine();
        kbd.nextLine();
    }


    /**
     * It reads the CSV file line by line and adds each line to a list of rowData objects
     */
    private static void readDataSet() {
        String line = "";
        int temp = -1;
        int i = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("stormwater-pipes_3.csv"));

            // Reading the file line by line and stopping when it reaches the 1000th line.
            while (((line = br.readLine()) != null)) {
                String [] rowData = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String columnHeader = "location";

                if (temp == -1) {
                    findColumn(rowData, columnHeader);
                    temp++;
                } else {
                    // Adding a new rowData object to the rowDataList.
                    rowDataList.add(new rowData(rowData[0], rowData[1], rowData[2], rowData[3],
                            rowData[4], rowData[5], rowData[6], rowData[7], rowData[8], rowData[9],
                            rowData[10], rowData[11], rowData[12], rowData[13], rowData[14], rowData[15],
                            rowData[16], rowData[17], rowData[18]));
                }

            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * It takes a row of data and a column header, and returns the column number of the column header
     *
     * @param rowData The row of data that you want to search through.
     * @param cHeader The column header you want to find
     */
    public static void findColumn(String[] rowData, String cHeader) {
        int x = 0;
        for (int i = 1; i <= rowData.length; i++) {
            if (rowData[i].equals(cHeader)) {
                x = i;
                return;
            }
        }
    }
}