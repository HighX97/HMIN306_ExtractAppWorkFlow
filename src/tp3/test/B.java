package tp3.test;

public class B extends A
{

	public A a =  new A();

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

/*
public String mBB1(int i, String s, boolean b){
	return i+s+b;
};
public String mBB2(int i, String s, boolean b){
	return i+s+b+a.mAA1(i, s,b);
};
public String mBB3(int i, String s, boolean b){
return i+s+b+a.mAA2(i, s,b)+i+s+b+a.mAA1(i, s,b);
};
public String mBB4(int i, String s, boolean b){
return i+s+b+a.mAA3(i, s,b)+i+s+b+a.mAA2(i, s,b)+i+s+b+a.mAA1(i, s,b);
};
public String mBB5(int i, String s, boolean b){
return i+s+b+a.mAA4(i, s,b)+i+s+b+a.mAA3(i, s,b)+i+s+b+a.mAA2(i, s,b)+i+s+b+a.mAA1(i, s,b);
};
*/

}
