package com.nanlagger.main.entities;

/**
 * Created by NaNLagger on 01.12.14.
 *
 * @author Stepan Lyashenko
 */
public interface MyNumber<T> {

    public void sum(T right);

    public void sub(T right);

    public void mul(T right);

    public void div(T right);

    public void reverse();

    public void sqr();

    public T copy();
}
