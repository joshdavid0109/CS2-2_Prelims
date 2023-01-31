import java.io.*;

public class Main {

    private static int x = 1;
    public static void main(String[] args) {
        String line = "";
        String delimiter = ";";
        int i = -1;
        int temp = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\2222551\\cs2\\stormwater-pipes_3.csv"));

            while (((line = br.readLine()) != null)  && i <= 1000) {
                String [] rowData = line.split(delimiter);
                String columnHeader = "createdate";

                for (int j = 0; j < rowData.length;j++) {
                    if (rowData[j].equals(columnHeader)) {
                        temp = j;
                    }
                }

                getColumnData(rowData, temp);

                i++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void getColumnData(String[] rowData, int columnHeader) {

            System.out.printf("Date for row [%d] : " + rowData[columnHeader], x);
            System.out.println();
            x++;

    }
}