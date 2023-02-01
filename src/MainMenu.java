/**
 * AUTHORS: Abella, Jahn Crystan - 2224514
 *          David, Joshua Daniel - 2222551
 *          Decena, Alexcious Norlan -
 *          Molintas, Jonison Martel -
 *          Tarlit, Ariel Jr. - 2221621
 *
 *
 * PROBLEM:
 *
 * SUBMITTED TO:
 * DATE:
 *
**/


 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends Main {
    private static List<rowData> rowDataList = new ArrayList<>();
    static Scanner kbd = new Scanner(System.in);

    public static byte IntroductionMenu() {
        System.out.println("Welcome to the Data Viewer Program! ");
        System.out.println("Press enter to continue...");
        kbd.nextLine();

        System.out.println("Classify yourself!");
        System.out.println("[1] Load the data");
        System.out.println("[2] Print the data");
        System.out.println("[3] View Filtered Data");
        System.out.println("[4] ");
        System.out.println("[5] Exit the program");
        return kbd.nextByte();
    }

    public static void PrintFilteredDataMenu() {
        System.out.println("test test");
        System.out.println("[1] Alphabetically Arrange Data");
        System.out.println("[2] Sort Decending Order");
        System.out.println("[3] Sort Ascending Order");
        System.out.println("[4] ");
        System.out.println("[5] ");
        System.out.println("[6]");
        System.out.println("[5] Back to Main Menu");
    }

    public static void ViewFilteredData() {

            int chFilter = kbd.nextInt();
            do {
                switch(chFilter) {
                    case 1 :

                        break;
                    case 2 :

                        break;
                    case 3 :

                        break;
                    case 4 :

                        break;
                    case 5 :
                        System.exit(0);
                        break;
                }
            } while(chFilter != 6);
        }

    public static void printData() {
        String line = "";
        int temp = -1;
        int i = 1;

        try {
            // Reading the file line by line and stopping when it reaches the 1000th line.
            BufferedReader br = new BufferedReader(new FileReader("stormwater-pipes_3.csv"));
            System.out.printf("%-10s%-10s%-15s%-50s%-10s%-10s%-10s%-10s%-15s%-10s%-10s%-30s%-15s%-15s%-10s%-15s%-20s%-20s%-20s%n",
                    "Object ID", "Facility ID", "Legacy ID", "Location", "Depth US", "Depth DS", "Height", "Invert US", "Invert DS",
                    "Diameter", "Width", "Modified Date", "Material", "Form", "pSlope", "Admin Area", "Shape Length",
                    "Operational Area", "Owner");

            while (((line = br.readLine()) != null) && i <= 1000) {
                String[] rowData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
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

                /*  TEST LANG ITOWS
                if (rowData[temp].equals("")) {
                    System.out.printf("Date for row [%d] " , i);
                    System.out.println();
                    i++;
                    continue;
                }


                System.out.printf("Date for row [%d] " + rowData[temp], i);
                System.out.println();

                i++;*/

//        List<rowData> result = rowDataList.stream().filter
//                (s -> s.getOperationalArea().equals ("Third Fork Creek")).toList();


       /* lagay ko lang, alphabetical sorting tapos print
        List <rowData> sortedAlphabetical = rowDataList.stream()
                        .sorted(Comparator.comparing(s -> s.getLocation()))
                                .collect(Collectors.toList());

        sortedAlphabetical.forEach(s -> System.out.println(s.getLocation())); // print alphabetical order
       */
//
        System.out.println(rowDataList.toString());
//        System.out.println(result.toString());
//        result.forEach(s -> System.out.println(s.getOperationalArea()));

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

    public static void run() {
        byte choiceMenu;

        do {
            choiceMenu = IntroductionMenu();
            switch (choiceMenu) {
                case 1 -> printData();
                case 2 -> printData();
                case 3->  printData();
            }
        } while(choiceMenu != 5);
    }
    public static void main(String[] args) {
        run();
    }
}
