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
	/*
	 * Algorithme Dijkstra (données : graphe G=(X, E, c) orienté avec c(e)≥0 ∀e∈E ; sommet
	 * de départ s ; résultat : ensemble des potentiels π)
	 * π(s)=0 ; EnsS=(s) ; p=s ;
	 * Pour tout x≠s faire π(x)=+∞ fin pour tout
	 * Tant que X≠S faire
	 * Pour tout x∉S tel que e=(p,x)∈E faire
	 * Si π(x) >π(p)+c(e) alors π(x)= π(p)+c(e) fin si
	 * fin pour tout
	 * Choisir x tel que π(x)=min(π(y)) y∈X-S
	 * S=S∪{x} ; p=x ;
	 * fin tant que
	 * fin algorithme
	 */
	public void algo_Dijkstra(Sommet s)
	{
		//Sommets sources
		Map<Sommet, Chemin> π = new HashMap<Sommet, Chemin>();
		for (Sommet x:this.getSommets())
		{
			System.out.println("------------------"+x.getNomSommet()+"------------------");
			for (Sommet sx:this.getSommets())
			{
				x.getChemins().put(sx,new Chemin(new Struct_Sommet_Arete(sx)));
				if (x.getId() == sx.getId())
				{
					x.getChemins().get(x).updateCoutChemin(); // π(s)=0
				}
				else
				{
					//π(x)=+∞
				}
				
			}
			System.out.println(x.getChemins());
			System.out.println("------------------/"+x.getNomSommet()+"/------------------");
		}

		List<Sommet> EnsS = new ArrayList<Sommet>();
		//EnsS.add(s); // EnsS=(s)
		Sommet p = s;
		int k=0;
			while (EnsS.size() < this.getSommets().size()) // Tant que X≠S faire
			{
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("x.getNomSommet() :"+p.getNomSommet());
					System.out.println("EnsS.contains(x):"+EnsS.contains(p));
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					try {
					    Thread.sleep(5000);                 //5000 milliseconds is five second.
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					if (! EnsS.contains(p)) //
					{
						if (p.getAretesSortantes().size() > 0)
						{
							System.out.println("p.getAretesSortantes().size() :"+p.getAretesSortantes().size());
						for (Arete e:p.getAretesSortantes())// Pour tout x x∉S tel que e=(p,x)∈E
						{
//							System.out.println("------------------"+e.getLabelArret()+"------------------");
//							System.out.println("------------------"+p.getNomSommet()+"------------------");
//							System.out.println("------------------"+s.getNomSommet()+"------------------");
//							System.out.println(s.getChemins().get(e.getSommetEnd()).coutChemin);
//							System.out.println(s.getChemins().get(p).coutChemin);
//							System.out.println(e.getPoidsArret());
							System.out.println("p.getId() :"+p.getId());
							System.out.println(e);
							System.out.println("e.getSommetBegin().getId():"+e.getSommetBegin().getId());
							if (p.getId() == e.getSommetBegin().getId()) //tel que e=(p,x)∈E
							{
//								System.out.println("p.getChemins().get(e.getSommetEnd()).value.size() : "+p.getChemins().get(e.getSommetEnd()).value.size());
//								System.out.println("s.getChemins().get(e.getSommetEnd()).coutChemin :"+s.getChemins().get(e.getSommetEnd()).coutChemin);
//								System.out.println("s.getChemins().get(e.getSommetEnd()).coutChemin  : "+s.getChemins().get(e.getSommetEnd()).coutChemin );
//								System.out.println("e.getPoidsArret()"+e.getPoidsArret());
//								if (p.getChemins().get(e.getSommetEnd()).value.size() < 2)
//								{
//									p.getChemins().get(e.getSommetEnd()).add(new Struct_Sommet_Arete(p, new Arete(p)));		
//									System.out.println("p.getChemins().get(e.getSommetEnd()).value.size() <2 :"+p.getChemins().get(e.getSommetEnd()).value);
//								}
								
								if (s.getChemins().get(e.getSommetEnd()).coutChemin >= s.getChemins().get(p).coutChemin + e.getPoidsArret())
								{
//									s.setCheminSommet(e.getSommetEnd(), s.getChemins().get(e.getSommetEnd()).add(p.getChemins().get(e.getSommetEnd()).value.get(e)) );
//									Struct_Sommet_Arete a = s.getChemins().get(p).value.
									Chemin c = new Chemin();
									int i=0;
									for(Struct_Sommet_Arete a : s.getChemins().get(p).value)
									{
//										System.out.println("a from dd:"+a);
										c.add(a);
										i++;
									}
//									System.out.println("c.value \t\t :"+c);
//									System.out.println("s.getChemins().get(p) \t\t :"+s.getChemins().get(p));
//									System.out.println("e.getSommetEnd()\t :"+e.getSommetEnd());
//									System.out.println("s.getChemins().get(p)\t :"+s.getChemins().get(p));
//									System.out.println("c.value \t\t :"+c);
//									System.out.println("c.value \t\t :"+c.value.size());
									c.add(new Struct_Sommet_Arete(e.getSommetEnd(), e,i));
									for(Struct_Sommet_Arete a : c.value)
									{
										i++;
									}
//									System.out.println("c.value :"+c.value);
//									System.out.println("c.value \t\t :"+c.value.size());
//									System.out.println("c.coutChemin :"+c.coutChemin);
									s.setCheminSommet(e.getSommetEnd(),c);
//									System.out.println("\n");
//									System.out.println(s.getCheminsSommet(e.getSommetEnd()));
//									System.out.println("\n");
								}
//								if (π.get(x).coutChemin >= (π.get(p).coutChemin+ e.getPoidsArret()))//π(x) >π(p)+c(e))
//								{
//									π.get(x).coutChemin = π.get(p).coutChemin + e.getPoidsArret(); //π(x)= π(p)+c(e)
//								}
//							}
						}
//							System.out.println("s.getChemins().get(e.getSommetEnd()).coutChemin  : "+s.getChemins().get(e.getSommetEnd()).coutChemin );
//							System.out.println(s.getChemins().get(p));
//							System.out.println("p :"+p);
//
//							 System.out.println(s.getChemins().get(e.getSommetEnd()).coutChemin);
//								System.out.println(s.getChemins().get(p).coutChemin);
//								System.out.println(e.getPoidsArret());
//							 System.out.println("------------------/"+e.getLabelArret()+"/------------------");
						}
						}

				} //fin pour tout
					 try {
						    Thread.sleep(1000);                 //5000 milliseconds is five second.
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
				Sommet xF = null;
				System.out.println("P : "+p+ " : "+s.getChemins().get(p).coutChemin); //Choisir x tel que π(x)=min(π(y)) y∈X-S
				for(Entry<Sommet, Chemin> 
				entry_s_c : p.getChemins().entrySet())
				{
					if (xF == null)
						{
						System.out.print(xF);
						if (s.getChemins().get(p).coutChemin < s.getChemins().get(entry_s_c.getKey()).coutChemin)
						{
							{
								System.out.print("--------\n");
								xF=entry_s_c.getKey();
								System.out.print(xF);
								System.out.print("--------\n");
							}
						}
					}	
					else
					{	
						System.out.print("---Coucou----\n");
						System.out.print(xF);
						System.out.print("--------\n");
						if ((s.getChemins().get(p).coutChemin < s.getChemins().get(entry_s_c.getKey()).coutChemin)  
								&& (s.getChemins().get(entry_s_c.getKey()).coutChemin < s.getChemins().get(xF).coutChemin))
						{
							if(!EnsS.contains(xF))
							{
							System.out.println("MIN: "+entry_s_c.getKey() + " : "+s.getChemins().get(entry_s_c.getKey()).coutChemin);
							xF=entry_s_c.getKey();
							}
						}
//						System.out.println("System.out.println(entry_s_c.getKey()); : "+entry_s_c.getKey()+ " : "+s.getChemins().get(entry_s_c.getKey()).coutChemin); //Choisir x tel que π(x)=min(π(y)) y∈X-S
//						System.out.println((s.getChemins().get(p).coutChemin));
//						System.out.println(s.getChemins().get(entry_s_c.getKey()).coutChemin);
//						System.out.println(s.getChemins().get(xF).coutChemin);
//						System.out.println((s.getChemins().get(p).coutChemin < s.getChemins().get(entry_s_c.getKey()).coutChemin));
//						System.out.println(s.getChemins().get(entry_s_c.getKey()).coutChemin < s.getChemins().get(xF).coutChemin);
//						System.out.println((s.getChemins().get(p).coutChemin < s.getChemins().get(entry_s_c.getKey()).coutChemin)  && (s.getChemins().get(entry_s_c.getKey()).coutChemin <= s.getChemins().get(xF).coutChemin));
						System.out.println("MIN: "+xF + " : "+s.getChemins().get(xF).coutChemin);
					}
				}
				EnsS.add(p);//S=S∪{x}
				System.out.println(EnsS);				
				System.out.println(xF);
				System.out.println(++k);
				if (xF ==null)
				{
					for(Sommet smt : this.getSommets())
					{
						if (!EnsS.contains(smt))
						{
							xF=smt;
						}
					}
				}
				p=xF;//p=x
			}//fin tant que
		//}//fin algorithme
	}
	
	public void algo_Flyod_Wharshall()
	{
		
	}
	
	public void algo_Bellman_Ford()
	{
		
	}
	
	
	
}
