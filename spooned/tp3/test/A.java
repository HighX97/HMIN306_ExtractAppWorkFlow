

package tp3.test;


public class A {
    public java.lang.String mA1(int i, java.lang.String s, boolean b) {
        return (i + s) + b;
        System.out.println("tp3.test.A : mA1");
    }

    public java.lang.String mA2(int i, java.lang.String s, boolean b) {
        return ((i + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.A : mA2");
    }

    public java.lang.String mA3(int i, java.lang.String s, boolean b) {
        return ((((((i + s) + b) + (mA2(i, s, b))) + i) + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.A : mA3");
    }

    public java.lang.String mA4(int i, java.lang.String s, boolean b) {
        return ((((((((((i + s) + b) + (mA3(i, s, b))) + i) + s) + b) + (mA2(i, s, b))) + i) + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.A : mA4");
    }

    public java.lang.String mA5(int i, java.lang.String s, boolean b) {
        return ((((((((((((((i + s) + b) + (mA4(i, s, b))) + i) + s) + b) + (mA3(i, s, b))) + i) + s) + b) + (mA2(i, s, b))) + i) + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.A : mA5");
    }
}

