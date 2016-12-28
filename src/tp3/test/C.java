package tp3.test;

public class C extends B {

	public String mC1(int i, String s, boolean b){
		return i+s+b;
	};
	public String mC2(int i, String s, boolean b){
		return i+s+b+mB1(i, s,b);
	};
	public String mC3(int i, String s, boolean b){
	return i+s+b+mB2(i, s,b)+i+s+b+mB1(i, s,b);
};
	public String mC4(int i, String s, boolean b){
	return i+s+b+mB3(i, s,b)+i+s+b+mB2(i, s,b)+i+s+b+mB1(i, s,b);
};
	public String mC5(int i, String s, boolean b){
	return i+s+b+mB4(i, s,b)+i+s+b+mB3(i, s,b)+i+s+b+mB2(i, s,b)+i+s+b+mB1(i, s,b);
};

}
