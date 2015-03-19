package com.nanlagger.test;

import com.nanlagger.main.LauncherTest;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LauncherTest$Test {

    @Test
    public void testExec() throws Exception {
        Assert.assertEquals(LauncherTest.exec("Hello"), "olleH");
    }
}