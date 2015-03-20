package com.nanlagger.main.entities;

/**
 * Created by NaNLagger on 01.12.14.
 *
 * @author Stepan Lyashenko
 */
public class TMemory<T extends MyNumber> {
    protected T fNumber;
    protected boolean fState;

    public TMemory(T obj) {
        fNumber = obj;
        fState = false;
    }

    public void setMemory(T number) {
        this.fNumber = (T) number.copy();
        this.fState = true;
    }

    public T getMemory() {
        return (T) fNumber.copy();
    }

    public void add(T number) {
        fNumber.sum(number);
    }

    public void clear() {
        fNumber = null;
    }

    public String getState() {
        return fState ? "On" : "Off";
    }
}