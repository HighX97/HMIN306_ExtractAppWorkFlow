package graphe;

public class Struct_Sommet_Arete 
{
	public int position;
	public Sommet sommet;
	public Arete arete;
	
	Struct_Sommet_Arete(Sommet sommet)
	{
		this.position = 0;
		this.sommet=sommet;
		this.arete=new Arete(sommet,0,sommet);
	}
	
	Struct_Sommet_Arete(Sommet sommet, Arete arete, int position)
	{
		this.position = position;
		this.sommet=sommet;
		this.arete=arete;
	}
	
	public String toString()
	{
		String string="(s:" + this.sommet
				+ ", a: "+this.arete
				+ ", pos: "+this.position;
		return string;
	}

}
