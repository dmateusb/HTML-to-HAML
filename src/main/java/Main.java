import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

        try{
            HTMLLexer lexer;
            if (args.length>0)
                lexer = new HTMLLexer(CharStreams.fromFileName(args[0]));
            else
                lexer = new HTMLLexer(CharStreams.fromStream(System.in));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HTMLParser parser = new HTMLParser(tokens);

            ParseTree tree = parser.htmlDocument();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new Traductor(), tree);
            System.out.println(); // print a \n after translation


        } catch (Exception e){
            System.err.println("Error (Main)" + e);
        }
    }
}
