package tp3.test;

public class A {

	public static String mA1(int i, String s, boolean b){
		return i+s+b;
	};
	public static String mA2(int i, String s, boolean b){
		return i+s+b+mA1(i, s,b);
	};
	public static String mA3(int i, String s, boolean b){
	return i+s+b+mA2(i, s,b)+i+s+b+mA1(i, s,b);
};
	public static String mA4(int i, String s, boolean b){
	return i+s+b+mA3(i, s,b)+i+s+b+mA2(i, s,b)+i+s+b+mA1(i, s,b);
};
	public static String mA5(int i, String s, boolean b){
	return i+s+b+mA4(i, s,b)+i+s+b+mA3(i, s,b)+i+s+b+mA2(i, s,b)+i+s+b+mA1(i, s,b);
};

}
