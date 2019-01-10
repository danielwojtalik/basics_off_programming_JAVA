package other_operations;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ZapisDoPliku {


    public static void zapisdoPlikuListOfIntegeres(String nazwaPliku, List<Integer> row) {
        try {
            List<String> numbersStr = new ArrayList<>();
            for (Integer intvalue : row) {
                numbersStr.add(String.valueOf(intvalue));
            }

            FileWriter writer = new FileWriter(nazwaPliku, true);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.print(numbersStr + "\n");
            printWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
