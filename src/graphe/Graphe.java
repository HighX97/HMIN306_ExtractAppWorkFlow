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
		System.out.println(this.sommets);
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


}
