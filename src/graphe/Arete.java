//@Author Ismail Alaoui

package graphe;

import java.util.List;

public class Arete {
	//Atributs
	private int id;
	private int poidsArret;
	private String labelArret;
	private Sommet sommetBegin;
	private Sommet sommetEnd;
	
	private static int count=0;

	//Constructeur
	public Arete()
	{
		this.id=++count;
//		System.out.println("Appel Constructeur Arete()");
//		System.out.println(this);
	}
	public Arete(int poidsArret, String labelArret)
	{
		this();
		this.poidsArret = poidsArret;
		this.labelArret = labelArret;
//		System.out.println("Appel Constructeur Arete(int poidsArret, String labelArret)");
	}


	public Arete(Sommet sommetBegin, int poidsArret, Sommet sommetEnd) 
	{
		this();
		this.labelArret = sommetBegin.getNomSommet()+"-"+sommetEnd.getNomSommet();
		this.poidsArret = poidsArret;
		this.sommetBegin = sommetBegin;
		this.sommetEnd = sommetEnd;
	}
	
	public Arete(Sommet sommetBegin, Sommet sommetEnd) 
	{
		this();
		this.labelArret = sommetBegin.getNomSommet()+"-->"+sommetEnd.getNomSommet();
		this.sommetBegin = sommetBegin;
		this.sommetEnd = sommetEnd;
	}
	
	public Arete(Sommet p) 
	{
		this(p,0,p);
	}
	//Accesseur  & Muttaeurs
	public int getPoidsArret() {
		return poidsArret;
	}
	public void setPoidsArret(int poidsArret) {
		this.poidsArret = poidsArret;
	}
	public String getLabelArret() {
		return labelArret;
	}
	public void setLabelArret(String labelArret) {
		this.labelArret = labelArret;
	}
	public Sommet getSommetEnd() {
		return sommetEnd;
	}
	public void setSommetEnd(Sommet sommetEnd) {
		this.sommetEnd = sommetEnd;
	}
	public Sommet getSommetBegin() {
		return sommetBegin;
	}
	public void setSommetBegin(Sommet sommetBegin) {
		this.sommetBegin = sommetBegin;
	}

	//Methodes

	public String toString()
	{
		String string=this.getSommetBegin().getNomSommet()
				+ "--->" + this.getSommetEnd().getNomSommet()
				;
		return string;
	}

}

