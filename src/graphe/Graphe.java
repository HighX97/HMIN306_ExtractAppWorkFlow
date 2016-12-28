package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graphe {
	//Atributs
	private String nomGraphe;
	private List<Sommet> sommets;
	private List<Arete> aretes;


	//Constructeur
	Graphe()
	{
		this.sommets = new ArrayList<Sommet>();
		this.aretes = new ArrayList<Arete>();
//		System.out.println("Appel Constructeur Graphe()");
//		System.out.println(this);
	}
	Graphe(String nomGraphe)
	{
		this.nomGraphe = nomGraphe;
		this.sommets = new ArrayList<Sommet>();
		this.aretes = new ArrayList<Arete>();
//		System.out.println("Appel Constructeur Graphe(String nomGraphe)");
//		System.out.println(this);
	}
	Graphe(String nomGraphe, List<Sommet> sommets, List<Arete> aretes)
	{
		this.nomGraphe = nomGraphe;
		this.sommets = sommets;
		this.aretes = aretes;
//		System.out.println("Appel Constructeur Graphe(String nomGraphe, List<Sommet> sommets, List<Arete> aretes)");
//		System.out.println(this);
	}

	//Accesseur  & Muttaeurs
	public List<Sommet> getSommets() {
		return sommets;
	}

	public void setSommets(List<Sommet> sommets) {
		this.sommets = sommets;
	}

	public List<Arete> getAretes() {
		return aretes;
	}

	public void setAretes(List<Arete> aretes) {
		this.aretes = aretes;
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
