package graphe;

import java.util.ArrayList;
import java.util.List;

public class Test_Graphe {

	public static void main(String [] args)
	{
		//Aretes
		//Begin Montpellier
		Arete a1 = new Arete(7,"Montpellier-Hambourg");
		Arete a2 = new Arete(4,"Montpellier-Londres");
		Arete a3 = new Arete(3,"Montpellier-Amsterdam");
		//Begin Hambourg
		Arete a4 = new Arete(1,"Hambourg-Stockholm");
		Arete a5 = new Arete(1,"Hambourg-Berlin");
		//Begin Amsterdam
		Arete a6 = new Arete(1,"Amsterdam-Londres");
		Arete a7 = new Arete(2,"Amsterdam-Hambourg");
		Arete a8 = new Arete(8,"Amsterdam-Oslo");
		//Begin Londres
		Arete a9 = new Arete(2,"Londres-Edimbourg");
		//Begin Edimbourg
		Arete a10= new Arete(6,"Edimbourg-Rana");
		Arete a11= new Arete(7,"Edimbourg-Oslo");
		Arete a12= new Arete(3,"Edimbourg-Amsterdam");
		//Begin Oslo
		Arete a13 = new Arete(2,"Oslo-Rana");
		//Begin Rana
		Arete a14 = new Arete(5,"Rana-Stockholm");
		//Begin Stockholm
		Arete a15 = new Arete(2,"Stockholm-Berlin");
		Arete a16 = new Arete(2,"Stockholm-Oslo");
		//Begin Berlin
		Arete a17 =  new Arete(3,"Berlin-Oslo");
		Arete a18 =  new Arete(2,"Berlin-Amsterdam");
		

		//Sommet
		//Montpellier
		List<Arete> aretes_Entrantes_Montpellier = new ArrayList<Arete>();
		List<Arete> aretes_Sortantes_Montpellier = new ArrayList<Arete>();
		aretes_Sortantes_Montpellier.add(a1);
		aretes_Sortantes_Montpellier.add(a2);
		aretes_Sortantes_Montpellier.add(a3);
		Sommet s1 = new Sommet(1, "Montpellier", aretes_Entrantes_Montpellier, aretes_Sortantes_Montpellier);
		a1.setSommetBegin(s1);
		a2.setSommetBegin(s1);
		a3.setSommetBegin(s1);
		//Hambourg
		List<Arete> aretes_Entrantes_Hambourg = new ArrayList<Arete>();
		aretes_Entrantes_Hambourg.add(a1);
		aretes_Entrantes_Hambourg.add(a7);
		List<Arete> aretes_Sortantes_Hambourg = new ArrayList<Arete>();
		aretes_Sortantes_Hambourg.add(a4);
		aretes_Sortantes_Hambourg.add(a5);
		Sommet s2 = new Sommet(2, "Hambourg", aretes_Entrantes_Hambourg, aretes_Sortantes_Hambourg);
		a1.setSommetEnd(s2);
		a7.setSommetEnd(s2);
		a4.setSommetBegin(s2);
		a5.setSommetBegin(s2);
		//Amsterdam
		List<Arete> aretes_Entrantes_Amsterdam= new ArrayList<Arete>();
		aretes_Entrantes_Amsterdam.add(a2);
		aretes_Entrantes_Amsterdam.add(a12);
		aretes_Entrantes_Amsterdam.add(a18);
		List<Arete> aretes_Sortantes_Amsterdam = new ArrayList<Arete>();
		aretes_Sortantes_Amsterdam.add(a6);
		aretes_Sortantes_Amsterdam.add(a7);
		aretes_Sortantes_Amsterdam.add(a8);
		Sommet s3 = new Sommet(3, "Amsterdam", aretes_Entrantes_Amsterdam, aretes_Sortantes_Amsterdam);
		a3.setSommetEnd(s3);
		a12.setSommetEnd(s3);
		a18.setSommetEnd(s3);
		a6.setSommetBegin(s3);
		a7.setSommetBegin(s3);
		a8.setSommetBegin(s3);
		//Londres
		List<Arete> aretes_Entrantes_Londres= new ArrayList<Arete>();
		aretes_Entrantes_Londres.add(a3);
		aretes_Entrantes_Londres.add(a6);
		List<Arete> aretes_Sortantes_Londres = new ArrayList<Arete>();
		aretes_Sortantes_Londres.add(a9);
		Sommet s4 = new Sommet(4, "Londres", aretes_Entrantes_Londres, aretes_Sortantes_Londres);
		a2.setSommetEnd(s4);
		a6.setSommetEnd(s4);
		a9.setSommetBegin(s4);
		//Edimbourg
		List<Arete> aretes_Entrantes_Edimbourg= new ArrayList<Arete>();
		aretes_Entrantes_Edimbourg.add(a9);
		List<Arete> aretes_Sortantes_Edimbourg = new ArrayList<Arete>();
		aretes_Sortantes_Edimbourg.add(a10);
		aretes_Sortantes_Edimbourg.add(a12);
		aretes_Sortantes_Edimbourg.add(a17);
		Sommet s5 = new Sommet(5, "Edimbourg", aretes_Entrantes_Edimbourg, aretes_Sortantes_Edimbourg);
		a9.setSommetEnd(s5);
		a10.setSommetEnd(s5);
		a11.setSommetBegin(s5);
		a12.setSommetBegin(s5);
		//Oslo
		List<Arete> aretes_Entrantes_Oslo= new ArrayList<Arete>();
		aretes_Entrantes_Oslo.add(a8);
		aretes_Entrantes_Oslo.add(a11);
		aretes_Entrantes_Oslo.add(a16);
		aretes_Entrantes_Oslo.add(a17);
		List<Arete> aretes_Sortantes_Oslo = new ArrayList<Arete>();
		aretes_Sortantes_Oslo.add(a13);
		Sommet s6 = new Sommet(6, "Oslo", aretes_Entrantes_Oslo, aretes_Sortantes_Oslo);
		a8.setSommetEnd(s6);
		a11.setSommetEnd(s6);
		a16.setSommetEnd(s6);
		a17.setSommetEnd(s6);
		a13.setSommetBegin(s6);
		//Rana
		List<Arete> aretes_Entrantes_Rana= new ArrayList<Arete>();
		aretes_Entrantes_Rana.add(a10);
		aretes_Entrantes_Rana.add(a13);
		aretes_Entrantes_Rana.add(a14);
		List<Arete> aretes_Sortantes_Rana = new ArrayList<Arete>();
		Sommet s7 = new Sommet(7, "Rana", aretes_Entrantes_Rana, aretes_Sortantes_Rana);
		a10.setSommetEnd(s7);
		a13.setSommetEnd(s7);
		a14.setSommetBegin(s7);
		//Stockholm
		List<Arete> aretes_Entrantes_Stockholm= new ArrayList<Arete>();
		aretes_Entrantes_Stockholm.add(a4);
		aretes_Entrantes_Stockholm.add(a15);
		List<Arete> aretes_Sortantes_Stockholm = new ArrayList<Arete>();
		aretes_Sortantes_Stockholm.add(a14);
		aretes_Sortantes_Stockholm.add(a16);
		Sommet s8 = new Sommet(8, "Stockholm", aretes_Entrantes_Stockholm, aretes_Sortantes_Stockholm);
		a4.setSommetEnd(s8);
		a14.setSommetEnd(s8);
		a15.setSommetEnd(s8);
		a16.setSommetBegin(s8);
		//Berlin
		List<Arete> aretes_Entrantes_Berlin= new ArrayList<Arete>();
		aretes_Entrantes_Berlin.add(a5);
		aretes_Entrantes_Berlin.add(a15);
		List<Arete> aretes_Sortantes_Berlin = new ArrayList<Arete>();
		aretes_Sortantes_Berlin.add(a17);
		aretes_Sortantes_Berlin.add(a18);
		Sommet s9 = new Sommet(9, "Berlin", aretes_Entrantes_Berlin, aretes_Sortantes_Berlin);
		a5.setSommetEnd(s9);
		a15.setSommetEnd(s9);
		a17.setSommetBegin(s9);
		a18.setSommetBegin(s9);
		//"Graphe Vol Europe"
		List<Arete> aretes = new ArrayList<Arete>();
		aretes.add(a1);
		aretes.add(a2);
		aretes.add(a3);
		aretes.add(a4);
		aretes.add(a5);
		aretes.add(a6);
		aretes.add(a7);
		aretes.add(a8);
		aretes.add(a9);
		aretes.add(a10);
		aretes.add(a11);
		aretes.add(a12);
		aretes.add(a13);
		aretes.add(a14);
		aretes.add(a15);
		aretes.add(a16);
		aretes.add(a17);
		aretes.add(a18);
		List<Sommet> sommets = new ArrayList<Sommet>();
		sommets.add(s1);
		sommets.add(s2);
		sommets.add(s3);
		sommets.add(s4);
		sommets.add(s5);
		sommets.add(s6);
		sommets.add(s7);
		sommets.add(s8);
		sommets.add(s9);
		
		
		Graphe g2 = new Graphe("Graphe Vol Europe",sommets,aretes);
		g2.algo_Dijkstra(s1);
	}

}

