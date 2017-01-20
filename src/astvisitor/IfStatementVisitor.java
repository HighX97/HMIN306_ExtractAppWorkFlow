package astvisitor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;

public class IfStatementVisitor extends ASTVisitor {

	public List<IfStatement> getifStatements() {
		// TODO Auto-generated method stub
		return new ArrayList<IfStatement>();
	}

}
