
public class EnglishGenerator extends CDeclBaseVisitor<String> {

    /** typename declarator ';' */
    @Override
    public String visitDeclaration(CDeclParser.DeclarationContext ctx){
        String left = visit(ctx.declarator());
        String right = visit(ctx.typename());
        return left + right;
    }

    /** :   'void'
     |   'float'
     |   'int'
     |	ID
     ;
     */
    @Override
     public String visitTypename(CDeclParser.TypenameContext ctx){
        String id = ctx.getText();
        if (id.equals("void")) return "nothing";
        return id;
    }


    /** :   declarator '[' ']'		# Array		// right operators have highest precedence
     |   declarator '(' ')'		# Func
     |	'*' declarator			# Pointer
     |   '(' declarator ')'		# Grouping
     |	ID						# Var
     ;
     */
    @Override
    public String visitArray(CDeclParser.ArrayContext ctx){
        return visit(ctx.declarator() )+ "array of ";
    }

    @Override
    public String visitFunc(CDeclParser.FuncContext ctx){
        return visit(ctx.declarator()) + "function returning ";
    }

    @Override
    public String visitPointer(CDeclParser.PointerContext ctx){
        return visit(ctx.declarator()) + "pointer to ";
    }

    @Override
    public String visitGrouping(CDeclParser.GroupingContext ctx){
        return visit(ctx.declarator());
    }

    @Override
    public String visitVar(CDeclParser.VarContext ctx){
        return ctx.getText() + " is a ";
    }


}
