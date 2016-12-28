//@Author Ismail Alaoui
package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sommet {
	//Atributs
	private int id;
	private int numSommet;
	private String nomSommet;
	private List<Arete> aretesEntrantes;
	private List<Arete> aretesSortantes;
	
	private static int count=0;

	//Constructeur
	public Sommet()
	{
		this.setId(++count);
		this.aretesEntrantes = new ArrayList<Arete>();
		this.aretesSortantes = new ArrayList<Arete>();
//		System.out.println("Appel Constructeur Sommet()");
	}
	
	public Sommet(String nomSommet)
	{
		this();
		this.nomSommet = nomSommet;
	}

	public Sommet(int numSommet, String nomSommet, List<Arete> aretesEntrantes, List<Arete> aretesSortantes)
	{
		this();
		this.numSommet = numSommet;
		this.nomSommet = nomSommet;
		this.aretesEntrantes = aretesEntrantes;
		this.aretesSortantes = aretesSortantes;
//		System.out.println("Appel Constructeur (int numSommet, String nomSommet, List<Arete> aretesEntrantes, List<Arete> aretesSortantes)");
	}
	//Accesseur  & Muttaeurs
	public String getNomSommet() {
		return nomSommet;
	}
	public void setNomSommet(String nomSommet) {
		this.nomSommet = nomSommet;
	}
	public int getNumSommet() {
		return numSommet;
	}
	public void setNumSommet(int numSommet) {
		this.numSommet = numSommet;
	}
	public List<Arete> getAretesEntrantes() {
		return aretesEntrantes;
	}
	public void setAretesEntrantes(List<Arete> aretesEntrantes) {
		this.aretesEntrantes = aretesEntrantes;
	}
	public List<Arete> getAretesSortantes() {
		return aretesSortantes;
	}
	public void setAretesSortantes(List<Arete> aretesSortantes) {
		this.aretesSortantes = aretesSortantes;
	}
	
	//Methodes

		public String toString()
		{
			String string="(nom Sommet:" + this.getNomSommet()
					+ ", num Sommet:" + this.getNumSommet()
					+ ", degré entrant:" + this.getAretesEntrantes().size()
					+ ", degré sortant:" + this.getAretesSortantes().size()
					+")";
			return string;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		


}

