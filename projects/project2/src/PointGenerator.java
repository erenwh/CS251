import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by HanWang on 2/20/17.
 */
public class PointGenerator {
    public static void main(String[] args) throws IOException {

        int n;
        System.out.println("How many points you want?");
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        File f = new File("test" + n +".txt");
        FileWriter fw = new FileWriter(f);
        fw.write(n + "\n");
        for (int i = 0; i < n; i++) {
            int x = (int)StdRandom.uniform(0,32767);
            int y = (int)StdRandom.uniform(0,32767);
            fw.write(x + " ");
            fw.write(y + "\n");
        }
        s.close();
        fw.close();
    }
}
