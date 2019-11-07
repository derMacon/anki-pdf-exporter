import java.io.IOException;

/**
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
//        try {
            System.out.println("Anki-Pdf-Parser v1.0");
//            ExporterFactory.create(args).export();
//        } catch (IncompleteExportInfo invalidArgs) {
//            System.err.println(invalidArgs.getMessage());
//        }



        Game game = new GameFactory().createGame(App.class.getResourceAsStream("/example.field"));

        game.play();

    }
}
