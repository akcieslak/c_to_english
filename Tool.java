
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


public class Tool {
	public static void main(String[] args) {
		String text = args[0];
		translate(text);
	}

	public static String translate(String cdeclText) {
		CDeclLexer lexer = new CDeclLexer(new ANTLRInputStream(cdeclText));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CDeclParser parser = new CDeclParser(tokens);
		ParseTree tree = parser.declaration();
		EnglishGenerator gen = new EnglishGenerator();
		return gen.visit(tree);
	}
}
