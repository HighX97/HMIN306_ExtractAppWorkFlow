package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Chemin 
{
	//Champs
	public int id;
	public Set<Struct_Sommet_Arete> value;
	public int coutChemin=Integer.MAX_VALUE;
	
	public static int count;
	
	//Constructeur;
	Chemin()
	{
		this.id = ++count;
		this.value = new HashSet<Struct_Sommet_Arete>();
//		System.out.println(this);
//		System.out.println("this.coutChemin : "+this.coutChemin);
	}
	
	Chemin(Set<Struct_Sommet_Arete> value)
	{
		this.id = ++count;
		this.value = value;
//		System.out.println(this);
//		System.out.println("this.coutChemin : "+this.coutChemin);
	}
	
	Chemin(Struct_Sommet_Arete smt_art)
	{
		this();
		this.value.add(smt_art);
//		System.out.println(this);
//		System.out.println("this.coutChemin : "+this.coutChemin);
	}
	
	public Chemin(Chemin chemin) {
		this();
		this.value  = chemin.value;
		this.coutChemin = chemin.coutChemin;
	}
	

	
	//Methodes
	


	public void updateCoutChemin()
	{
		//etat initial
		int rslt=0;
		
		//etat intermediaire
		for(Struct_Sommet_Arete sm : this.value) //Pour chaque couple sommet arret du chemin
		{
			//On sum les poids des aretes traversé afin d'obtenir le poids total du chemin
			rslt+=sm.arete.getPoidsArret();
			System.out.println("arete.getLabelArret() : "+sm.arete.getLabelArret());
			System.out.println("arete.getPoidsArret() : "+sm.arete.getPoidsArret());
		}
		
		//etat final
		this.coutChemin= rslt;
		System.out.println("this.coutChemin : "+this.coutChemin);
	}
	
	public String toString()
	{
		String string="(id_chemin:" + this.id
				+ ", value: "+this.value
				+ ", coutChemin: "+this.coutChemin;
		return string;
	}

	public Chemin add(Struct_Sommet_Arete struct_Sommet_Arete) 
	{
		value.add(struct_Sommet_Arete);
		this.updateCoutChemin();
		return this;
	}



}
