package com.nanlagger.main.controllers;

import com.nanlagger.main.entities.MyNumber;

/**
 * Created by NaNLagger on 02.12.14.
 *
 * @author Stepan Lyashenko
 */
public class TProc<T extends MyNumber> {
    public static final int TPROC_NONE = 0;
    public static final int TPROC_ADD = 12;
    public static final int TPROC_SUB = 13;
    public static final int TPROC_Mul = 14;
    public static final int TPROC_Dvd = 15;
    public static final int TPROC_REV = 16;
    public static final int TPROC_SQR = 17;
    protected T lop_res;
    protected T rop;
    protected int operation;

    public TProc() {
        operation = TPROC_NONE;
    }

    public void resetProc() {
        operation = TPROC_NONE;
    }

    public void resetOperation() {
        operation = TPROC_NONE;
    }

    public void runOperation() {
        switch (operation) {
            case TPROC_ADD:
                lop_res.sum(rop);
                break;
            case TPROC_SUB:
                lop_res.sub(rop);
                break;
            case TPROC_Mul:
                lop_res.mul(rop);
                break;
            case TPROC_Dvd:
                lop_res.div(rop);
                break;
            default:
                lop_res = (T) rop.copy();
                break;
        }
    }

    public void runFunction(int function) {
        switch (function) {
            case TPROC_REV:
                rop.reverse();
                break;
            case TPROC_SQR:
                rop.sqr();
                break;
            default:
                break;
        }
    }

    public T getLop_res() {
        return (T) lop_res.copy();
    }

    public void setLop_res(T lop_res) {
        this.lop_res = (T) lop_res.copy();
    }

    public T getRop() {
        return (T) rop.copy();
    }

    public void setRop(T rop) {
        this.rop = (T) rop.copy();
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
}
