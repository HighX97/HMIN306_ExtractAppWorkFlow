package tp3.test;

public class C extends B {

	public static String mC1(int i, String s, boolean b){
		return i+s+b;
	};
	public static String mC2(int i, String s, boolean b){
		return i+s+b+B.mB1(i, s,b);
	};
	public static String mC3(int i, String s, boolean b){
	return i+s+b+B.mB2(i, s,b)+i+s+b+B.mB1(i, s,b);
};
	public static String mC4(int i, String s, boolean b){
	return i+s+b+B.mB3(i, s,b)+i+s+b+B.mB2(i, s,b)+i+s+b+B.mB1(i, s,b);
};
	public static String mC5(int i, String s, boolean b){
	return i+s+b+B.mB4(i, s,b)+i+s+b+B.mB3(i, s,b)+i+s+b+B.mB2(i, s,b)+i+s+b+B.mB1(i, s,b);
};

}
