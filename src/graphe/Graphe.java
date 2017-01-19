package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graphe {
	//Atributs
	private String nomGraphe;
	private Map<String ,Sommet> sommets;
	private List<Arete> aretes;


	//Constructeur
	public Graphe()
	{
		this.sommets = new HashMap<String ,Sommet>();
		this.aretes = new ArrayList<Arete>();
//		System.out.println("Appel Constructeur Graphe()");
//		System.out.println(this);
	}
	Graphe(String nomGraphe)
	{
		this.nomGraphe = nomGraphe;
		this.sommets = new HashMap<String ,Sommet>();
		this.aretes = new ArrayList<Arete>();
//		System.out.println("Appel Constructeur Graphe(String nomGraphe)");
//		System.out.println(this);
	}
	Graphe(String nomGraphe, HashMap<String ,Sommet> sommets, List<Arete> aretes)
	{
		this.nomGraphe = nomGraphe;
		this.sommets = sommets;
		this.aretes = aretes;
//		System.out.println("Appel Constructeur Graphe(String nomGraphe, HashMap<String ,Sommet> sommets, List<Arete> aretes)");
//		System.out.println(this);
	}

	//Accesseur  & Muttaeurs
	public Map<String ,Sommet> getSommets() {
		return sommets;
	}

	public void setSommets(HashMap<String ,Sommet> sommets) {
		this.sommets = sommets;
	}
	public void addSommet(Sommet sommet) {
		this.sommets.put(sommet.getNomSommet(),sommet);
		//System.out.println(this.sommets);
	}

	public List<Arete> getAretes() {
		return aretes;
	}

	public void setAretes(List<Arete> aretes) {
		this.aretes = aretes;
	}

	public void addArete(Arete aretes) {
		this.aretes.add(aretes);
		//System.out.println(this.aretes);
	}

	public String getNomGraphe() {
		return nomGraphe;
	}

	public void setNomGraphe(String nomGraphe) {
		this.nomGraphe = nomGraphe;
	}

	//Methodes

	public String toString()
	{
		String string="\tnomGraphe :" + this.getNomGraphe()
				+ "\n\tnombre de sommets:" + this.getSommets().size()
				+ "\n\tnombre d'aretes:" + this.getAretes().size();
		return string;
	}
	public Map<String ,Sommet> getTaches() 
	{
		Map<String ,Sommet> out =  new HashMap<String ,Sommet>();
		for (Map.Entry<String, Sommet> entry : this.sommets.entrySet())
		{
			int i=0;
		    int n =this.aretes.size();
		    for (Arete a : this.aretes)
		    {
		    	i++;
		    	if (a.getSommetBegin().getNomSommet().equalsIgnoreCase(entry.getKey()))
		    	{
		    		//System.out.println("NO "+entry.getKey());
		    		break;
		    	}
		    }
		    if (i==n)
		    {
	    		//System.out.println("YES "+entry.getKey());

		    	out.put(entry.getKey(),entry.getValue());
		    }
			
		}
		return out;
	}
	public Map<String ,Sommet> getTachesComposites() 
	{
		Map<String ,Sommet> out =  new HashMap<String ,Sommet>();
		for (Map.Entry<String, Sommet> entry : this.sommets.entrySet())
		{
			
		    for (Arete a : this.aretes)
		    {
		    	
		    	if (a.getSommetEnd().getNomSommet().equalsIgnoreCase(entry.getKey()))
		    	{
		    		for (Arete aa : this.aretes)
				    {
		    			
		    			if (aa.getSommetBegin().equals(a.getSommetEnd()))
				    	{
		    				out.put(entry.getKey(),entry.getValue());
				    	}
				    }
		    		
		    		
		    	}
		    }
		    
			
		}
		return out;
	}
	
	public Map<String ,Sommet> getPointEntree() 
	{
		Map<String ,Sommet> out =  new HashMap<String ,Sommet>();
		for (Map.Entry<String, Sommet> entry : this.sommets.entrySet())
		{
			int i=0;
			int j=0;
		    int n =this.aretes.size();
		    for (Arete a : this.aretes)
		    {
		    	i++;
		    	if (a.getSommetEnd().getNomSommet().equalsIgnoreCase(entry.getKey()))
		    	{
		    		i--;
		    		//System.out.println("NO "+entry.getKey());
		    		break;
		    	}
		    	else if(a.getSommetBegin().getNomSommet().equalsIgnoreCase(entry.getKey()))
		    	{
		    		j++;
		    	}
		    }
		    if (i==n && j>0)
		    {
	    		//System.out.println("YES "+entry.getKey());
		    	out.put(entry.getKey(),entry.getValue());
		    }
		}
		return out;
	}
	
	
	public List<Sommet> getNodeChild(Sommet s)
	{
		List<Sommet> out =  new ArrayList<Sommet>();
		 for (Arete a : this.aretes)
		    {
		    	if(a.getSommetBegin().getNomSommet().equalsIgnoreCase(s.getNomSommet()))
		    	{
		    		out.add(a.getSommetEnd());
		    	}
		    }
		 return out;
	}
	
	public List<Sommet> getNodeParent(Sommet s)
	{
		List<Sommet> out =  new ArrayList<Sommet>();
		 for (Arete a : this.aretes)
		    {
		    	if(a.getSommetEnd().getNomSommet().equalsIgnoreCase(s.getNomSommet()))
		    	{
		    		out.add(a.getSommetBegin());
		    	}
		    }
		 return out;
	}
	
	
	


}
