package tp3.test;

public class B extends A{

	public static String mB1(int i, String s, boolean b){
		return i+s+b;
	};
	public static String mB2(int i, String s, boolean b){
		return i+s+b+A.mA1(i, s,b);
	};
	public static String mB3(int i, String s, boolean b){
	return i+s+b+A.mA2(i, s,b)+i+s+b+A.mA1(i, s,b);
};
	public static String mB4(int i, String s, boolean b){
	return i+s+b+A.mA3(i, s,b)+i+s+b+A.mA2(i, s,b)+i+s+b+A.mA1(i, s,b);
};
	public static String mB5(int i, String s, boolean b){
	return i+s+b+A.mA4(i, s,b)+i+s+b+A.mA3(i, s,b)+i+s+b+A.mA2(i, s,b)+i+s+b+A.mA1(i, s,b);
};

}
