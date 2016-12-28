

package tp3.test;


public class C extends tp3.test.B {
    public java.lang.String mC1(int i, java.lang.String s, boolean b) {
        return (i + s) + b;
        System.out.println("tp3.test.C : mC1");
    }

    public java.lang.String mC2(int i, java.lang.String s, boolean b) {
        return ((i + s) + b) + (mB1(i, s, b));
        System.out.println("tp3.test.C : mC2");
    }

    public java.lang.String mC3(int i, java.lang.String s, boolean b) {
        return ((((((i + s) + b) + (mB2(i, s, b))) + i) + s) + b) + (mB1(i, s, b));
        System.out.println("tp3.test.C : mC3");
    }

    public java.lang.String mC4(int i, java.lang.String s, boolean b) {
        return ((((((((((i + s) + b) + (mB3(i, s, b))) + i) + s) + b) + (mB2(i, s, b))) + i) + s) + b) + (mB1(i, s, b));
        System.out.println("tp3.test.C : mC4");
    }

    public java.lang.String mC5(int i, java.lang.String s, boolean b) {
        return ((((((((((((((i + s) + b) + (mB4(i, s, b))) + i) + s) + b) + (mB3(i, s, b))) + i) + s) + b) + (mB2(i, s, b))) + i) + s) + b) + (mB1(i, s, b));
        System.out.println("tp3.test.C : mC5");
    }
}

