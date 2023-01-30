import java.io.*;

public class Main {
    public static void main(String[] args) {

        String line = "";
        String delimiter = ";";
        int temp = -1;
        int i = 1;


        try {
            BufferedReader br = new BufferedReader(new FileReader("stormwater-pipes_3.csv"));
            // Reading the file line by line and stopping when it reaches the 1000th line.
            while (((line = br.readLine()) != null) && i <= 1000) {
                String [] rowData = line.split(delimiter);
                String columnHeader = "createdate";

                if (temp == -1) {
                    temp = findColumn(rowData, columnHeader);;
                    continue;
                }

                if (rowData[temp].equals("")) {
                    System.out.printf("Date for row [%d] " , i);
                    System.out.println();
                    i++;
                    continue;
                }

                System.out.printf("Date for row [%d] " + rowData[temp].substring(0, rowData[temp].indexOf(' ')), i);
                System.out.println();

                i++;

            }
        } catch (IOException e) {
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