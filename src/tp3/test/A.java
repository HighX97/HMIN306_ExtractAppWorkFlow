package tp3.test;

public class A {

	A a = new A();
	public static String mA1(int i, String s, boolean b){
		return i+s+b;
	};
	public static String mA2(int i, String s, boolean b){
		return i+s+b+ mA1(i, s,b);
	};
	public static String mA3(int i, String s, boolean b){
	return i+s+b+ mA2(i, s,b)+i+s+b+ mA1(i, s,b);
};
	public static String mA4(int i, String s, boolean b){
	return i+s+b+ mA3(i, s,b)+i+s+b+ mA2(i, s,b)+i+s+b+ mA1(i, s,b);
};
	public static String mA5(int i, String s, boolean b){
	return i+s+b+ mA4(i, s,b)+i+s+b+ mA3(i, s,b)+i+s+b+ mA2(i, s,b)+i+s+b+ mA1(i, s,b);
};

public String mAA1(int i, String s, boolean b){
	return i+s+b;
};
public String mAA2(int i, String s, boolean b){
	return i+s+b+ mAA1(i, s,b);
};
public String mAA3(int i, String s, boolean b){
return i+s+b+ mAA2(i, s,b)+i+s+b+ mAA1(i, s,b);
};
public String mAA4(int i, String s, boolean b){
return i+s+b+ mAA3(i, s,b)+i+s+b+ mAA2(i, s,b)+i+s+b+ mAA1(i, s,b);
};
public String mAA5(int i, String s, boolean b){
return i+s+b+ mAA4(i, s,b)+i+s+b+ mAA3(i, s,b)+i+s+b+ mAA2(i, s,b)+i+s+b+ mAA1(i, s,b);
};

public String mAAA1(int i, String s, boolean b){
	return i+s+b;
};
public String mAAA2(int i, String s, boolean b){
	return i+s+b+a.mAAA1(i, s,b);
};
public String mAAA3(int i, String s, boolean b){
return i+s+b+a.mAAA2(i, s,b)+i+s+b+a.mAAA1(i, s,b);
};
public String mAAA4(int i, String s, boolean b){
return i+s+b+a.mAAA3(i, s,b)+i+s+b+a.mAAA2(i, s,b)+i+s+b+a.mAAA1(i, s,b);
};
public String mAAA5(int i, String s, boolean b){
return i+s+b+a.mAAA4(i, s,b)+i+s+b+a.mAAA3(i, s,b)+i+s+b+a.mAAA2(i, s,b)+i+s+b+a.mAAA1(i, s,b);
};

}
