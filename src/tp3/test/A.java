package tp3.test;

public class A {

	public static String mA1(int i, String s, boolean b){
		return i+s+b;
	};
	public static String mA2(int i, String s, boolean b){
		return i+s+b+A.mA1(i, s,b);
	};
	public static String mA3(int i, String s, boolean b){
	return i+s+b+A.mA2(i, s,b)+i+s+b+A.mA1(i, s,b);
};
	public static String mA4(int i, String s, boolean b){
	return i+s+b+A.mA3(i, s,b)+i+s+b+A.mA2(i, s,b)+i+s+b+A.mA1(i, s,b);
};
	public static String mA5(int i, String s, boolean b){
	return i+s+b+A.mA4(i, s,b)+i+s+b+A.mA3(i, s,b)+i+s+b+A.mA2(i, s,b)+i+s+b+A.mA1(i, s,b);
};

public String mAA1(int i, String s, boolean b){
	return i+s+b;
};
public String mAA2(int i, String s, boolean b){
	return i+s+b+this.mAA1(i, s,b);
};
public String mAA3(int i, String s, boolean b){
return i+s+b+this.mAA2(i, s,b)+i+s+b+this.mAA1(i, s,b);
};
public String mAA4(int i, String s, boolean b){
return i+s+b+this.mAA3(i, s,b)+i+s+b+this.mAA2(i, s,b)+i+s+b+this.mAA1(i, s,b);
};
public String mAA5(int i, String s, boolean b){
return i+s+b+this.mAA4(i, s,b)+i+s+b+this.mAA3(i, s,b)+i+s+b+this.mAA2(i, s,b)+i+s+b+this.mAA1(i, s,b);
};

}
