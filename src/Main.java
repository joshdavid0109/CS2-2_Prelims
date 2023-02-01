import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<rowData> rowDataList = new ArrayList<>();

    public static void main(String[] args) {

        String line = "";
        int temp = -1;
        int i = 1;


        try {
            BufferedReader br = new BufferedReader(new FileReader("stormwater-pipes_3.csv"));
            // Reading the file line by line and stopping when it reaches the 1000th line.

            System.out.printf("%-10s%-10s%-15s%-50s%-10s%-10s%-10s%-10s%-15s%-10s%-10s%-30s%-15s%-15s%-10s%-15s%-20s%-20s%-20s%n",
                    "Object ID", "Facility ID", "Legacy ID", "Location", "Depth US", "Depth DS", "Height", "Invert US", "Invert DS",
                    "Diameter", "Width", "Modified Date", "Material", "Form", "pSlope", "Admin Area", "Shape Length",
                    "Operational Area", "Owner");
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

            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        List<rowData> result = rowDataList.stream().filter(s -> s.getOperationalArea().equals("Third Fork Creek")).toList();


     /*   // Sort in alphabetical order
        List <rowData> sortedNames = rowDataList.stream()
                        .sorted(Comparator.comparing(s -> s.getLocation()))
                                .collect(Collectors.toList());

        sortedNames.forEach(s -> System.out.println(s.getLocation())); // print alphabetical order*/

        System.out.println(rowDataList.toString());
        System.out.println(result.toString());

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