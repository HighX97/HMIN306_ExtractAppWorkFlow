//@Author Ismail Alaoui
package graphe;

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
	private int degreEntrant;
	private int degreSortant;
	private Map<Sommet, Chemin> chemins;
	
	private static int count=0;

	//Constructeur
	Sommet()
	{
		this.setId(++count);
		this.chemins = new HashMap<Sommet, Chemin>();
//		System.out.println("Appel Constructeur Sommet()");
	}

	Sommet(int numSommet, String nomSommet, List<Arete> aretesEntrantes, List<Arete> aretesSortantes)
	{
		this();
		this.numSommet = numSommet;
		this.nomSommet = nomSommet;
		this.aretesEntrantes = aretesEntrantes;
		this.aretesSortantes = aretesSortantes;
		this.degreEntrant = aretesEntrantes.size();
		this.degreSortant = aretesSortantes.size();
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
	public int getDegreEntrant() {
		return degreEntrant;
	}
	public void setDegreEntrant(int degreEntrant) {
		this.degreEntrant = degreEntrant;
	}
	public int getDegreSortant() {
		return degreSortant;
	}
	public void setDegreSortant(int degreSortant) {
		this.degreSortant = degreSortant;
	}

	//Methodes

		public String toString()
		{
			String string="(nom Sommet:" + this.getNomSommet()
					+ ", num Sommet:" + this.getNumSommet()
					+ ", degré entrant:" + this.getDegreEntrant()
					+ ", degré sortant:" + this.getDegreSortant()
					+")";
			return string;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Map<Sommet, Chemin> getChemins() {
			return chemins;
		}

		public void setChemins(Map<Sommet, Chemin> chemins) {
			this.chemins = chemins;
		}

		public void setCheminSommet(Sommet s, Chemin chemin) {
			if(this.chemins.get(s) ==null)
			{
				this.chemins.put(s, chemin);
			}
			else
			{
				this.chemins.replace(s, chemin);
			}
			
		}

		public Chemin getCheminsSommet(Sommet s) 
		{
			return this.chemins.get(s);
		}


}

