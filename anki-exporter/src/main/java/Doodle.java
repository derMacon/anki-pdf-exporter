import com.dermacon.export.Exporter;
import com.dermacon.export.MockExporter;
import com.dermacon.model.generate.Parser;
import com.dermacon.model.generate.TXTParser;

public class Doodle {

    public static void main(String[] args) {
        Parser par = new TXTParser("media/", "deck");
        String input = "front\tback\n";
        StringBuilder out = new StringBuilder();
        new MockExporter(par, input, out).export();

        System.out.println(out);
    }

}
