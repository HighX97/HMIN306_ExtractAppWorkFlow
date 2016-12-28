package tp3.main;

import spoon.reflect.code.CtStatement;
import spoon.reflect.visitor.Filter;


public class SuperStatement implements Filter<CtStatement>{
	

	@Override
	public boolean matches(CtStatement s) {
		if(s.toString().contains("super.")){
			return true;
		}
		return false;
	}

}
