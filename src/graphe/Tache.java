package graphe;

import java.util.List;

public class Tache extends Sommet
{
	private List<Sommet> parents;

	public List<Sommet> getParents() 
	{
		setParents();
		return parents;
	}

	private void setParents() 
	{
		parents = graphe.getNodeParent(this);
	}
}
