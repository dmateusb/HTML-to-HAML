import com.sun.xml.internal.bind.v2.model.core.ID;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Traductor extends HTMLParserBaseListener {
    int indent = -1;

    private String indentation(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            s.append("\t");
        }
        return s.toString();
    }


    @Override
    public void enterHtmlDocument(HTMLParser.HtmlDocumentContext ctx) {
        super.enterHtmlDocument(ctx);
    }

    @Override
    public void enterHtmlElement(HTMLParser.HtmlElementContext ctx) {
        super.enterHtmlElement(ctx);
        indent++;
        String indentation = indentation();
        String tag = ctx.TAG_NAME().get(0).toString();

        System.out.print( indentation + tag + " ");

    }

    @Override
    public void enterHtmlMisc(HTMLParser.HtmlMiscContext ctx) {
        super.enterHtmlMisc(ctx);
        System.out.println("");
    }

    @Override
    public void enterHtmlComment(HTMLParser.HtmlCommentContext ctx) {
        super.enterHtmlComment(ctx);
        System.out.println(ctx.HTML_COMMENT().toString().replace("<!--", "/! ").replace("-->"," "));
    }

    @Override
    public void exitHtmlElement(HTMLParser.HtmlElementContext ctx) {
        super.exitHtmlElement(ctx);
        if(ctx.TAG_SLASH_CLOSE() != null) {
            System.out.print("/" + " ");
        }
        indent--;
    }

    @Override
    public void enterHtmlChardata(HTMLParser.HtmlChardataContext ctx) {
        super.enterHtmlChardata(ctx);

        if(ctx.HTML_TEXT() != null) {
            System.out.print(ctx.HTML_TEXT());
        }
        System.out.println("");
    }

    @Override
    public void enterHtmlAttribute(HTMLParser.HtmlAttributeContext ctx) {
        super.enterHtmlAttribute(ctx);
        if(ctx.TAG_NAME().toString().equals("id")) {
            System.out.print("#" + ctx.ATTVALUE_VALUE().toString().replaceAll("\"", " ").replaceAll("\\s+","") + " ");
        }else if(ctx.TAG_NAME().toString().equals("class")){
            System.out.print("." + ctx.ATTVALUE_VALUE().toString().replaceAll("\"", " ").replaceAll("\\s+","") + " ");
        } else if(ctx.TAG_NAME().toString().equals("title")){
            System.out.print(ctx.TAG_NAME().toString() + ctx.TAG_EQUALS().toString() + "(" + ctx.ATTVALUE_VALUE().toString() + ")"+ " ");
        }else{
            System.out.print(ctx.TAG_NAME().toString() + ctx.TAG_EQUALS().toString() + ctx.ATTVALUE_VALUE().toString() + " ");
        }
    }

    @Override
    public void exitHtmlDocument(HTMLParser.HtmlDocumentContext ctx) {
        super.exitHtmlDocument(ctx);
    }
}
