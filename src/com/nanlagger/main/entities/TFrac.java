package com.nanlagger.main.entities;

/**
 * Created by NaNLagger on 19.03.15.
 *
 * @author Stepan Lyashenko
 */
public class TFrac implements MyNumber<TFrac> {

    protected Integer a = 0;
    protected Integer b = 1;

    public TFrac() {
        this.a = 0;
        this.b = 1;
    }

    public TFrac(Integer a, Integer b) {
        if(b == 0) {
            throw new IllegalArgumentException("Denominator can't be zero");
        }
        int del = euclid(a, b);
        this.a = a/del;
        this.b = b/del;
        znak();
    }

    public TFrac(String str) {
        this();
        Integer a_t = 0,b_t = 0;
        String[] obj = str.split("/");
        try {
            a_t = Integer.parseInt(obj[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage() + " format must be [+ or -] number[/number] example: 1/2 or -1/2 or 1");
        }
        try {
            b_t = Integer.parseInt(obj[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            b_t = 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage() + " format must be [+ or -] number[/number] example: 1/2 or -1/2 or 1");
        }

        if(b_t == 0) {
            throw new IllegalArgumentException("Denominator can't be zero");
        }

        int del = euclid(a_t, b_t);
        this.a = a_t/del;
        this.b = b_t/del;
        znak();
    }

    protected int euclid(int a, int b) {
        int U[], V[], T[], temp[];
        U = new int[3];
        V = new int[3];
        T = new int[3];
        U[0] = a; U[1] = 1; U[2] = 0;
        V[0] = b; V[1] = 0; V[2] = 1;
        while(V[0] != 0) {
            int q = U[0]/V[0];
            T[0] = U[0]%V[0];
            T[1] = U[1] - q*V[1];
            T[2] = U[2] - q*V[2];
            temp = U; U = V; V = T; T = temp;
        }
        return U[0];
    }

    protected void znak() {
        if(this.b < 0) {
            this.a = -this.a;
            this.b = -this.b;
        }
    }

    public Integer getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    public String getAString() {
        return a.toString();
    }

    public String getBString() {
        return b.toString();
    }

    @Override
    public String toString() {
        if (b == 1) {
            return String.valueOf(a);
        }
        return (a + "/" + b);
    }

    @Override
    public void sum(TFrac right) {
        TFrac temp = new TFrac(this.a*right.b + right.a*this.b, this.b*right.b);
        this.a = temp.a;
        this.b = temp.b;
    }

    @Override
    public void sub(TFrac right) {
        TFrac temp = new TFrac(this.a*right.b - right.a*this.b, this.b*right.b);
        this.a = temp.a;
        this.b = temp.b;
    }

    @Override
    public void mul(TFrac right) {
        TFrac temp = new TFrac(this.a*right.a, this.b*right.b);
        this.a = temp.a;
        this.b = temp.b;
    }

    @Override
    public void div(TFrac right) {
        TFrac temp = new TFrac(this.a*right.b, this.b*right.a);
        this.a = temp.a;
        this.b = temp.b;
    }

    @Override
    public void reverse() {
        TFrac temp = new TFrac(this.b, this.a);
        this.a = temp.a;
        this.b = temp.b;
    }

    @Override
    public void sqr() {
        TFrac temp = new TFrac(this.a*this.a, this.b*this.b);
        this.a = temp.a;
        this.b = temp.b;
    }

    @Override
    public TFrac copy() {
        return new TFrac(this.a, this.b);
    }
}
