package tp3.test;

public class B extends A{

	public String mB1(int i, String s, boolean b){
		return i+s+b;
	};
	public String mB2(int i, String s, boolean b){
		return i+s+b+mA1(i, s,b);
	};
	public String mB3(int i, String s, boolean b){
	return i+s+b+mA2(i, s,b)+i+s+b+mA1(i, s,b);
};
	public String mB4(int i, String s, boolean b){
	return i+s+b+mA3(i, s,b)+i+s+b+mA2(i, s,b)+i+s+b+mA1(i, s,b);
};
	public String mB5(int i, String s, boolean b){
	return i+s+b+mA4(i, s,b)+i+s+b+mA3(i, s,b)+i+s+b+mA2(i, s,b)+i+s+b+mA1(i, s,b);
};

}
