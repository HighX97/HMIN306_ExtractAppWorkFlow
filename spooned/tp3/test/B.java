

package tp3.test;


public class B extends tp3.test.A {
    public java.lang.String mB1(int i, java.lang.String s, boolean b) {
        return (i + s) + b;
        System.out.println("tp3.test.B : mB1");
    }

    public java.lang.String mB2(int i, java.lang.String s, boolean b) {
        return ((i + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.B : mB2");
    }

    public java.lang.String mB3(int i, java.lang.String s, boolean b) {
        return ((((((i + s) + b) + (mA2(i, s, b))) + i) + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.B : mB3");
    }

    public java.lang.String mB4(int i, java.lang.String s, boolean b) {
        return ((((((((((i + s) + b) + (mA3(i, s, b))) + i) + s) + b) + (mA2(i, s, b))) + i) + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.B : mB4");
    }

    public java.lang.String mB5(int i, java.lang.String s, boolean b) {
        return ((((((((((((((i + s) + b) + (mA4(i, s, b))) + i) + s) + b) + (mA3(i, s, b))) + i) + s) + b) + (mA2(i, s, b))) + i) + s) + b) + (mA1(i, s, b));
        System.out.println("tp3.test.B : mB5");
    }
}

