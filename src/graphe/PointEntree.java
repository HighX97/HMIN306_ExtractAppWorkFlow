package graphe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointEntree extends Sommet
{
	private List<Sommet> childs;

	public List<Sommet> getChilds() 
	{
		setChilds();
		return childs;
	}

	private void setChilds() 
	{
		childs = graphe.getNodeChild(this);
	}
}
