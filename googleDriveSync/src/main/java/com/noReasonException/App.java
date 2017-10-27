package com.noReasonException;
/**
 * Hello world!
 *
 */
public class App
{
    native double f(int i,String s);

    static {
        Runtime.getRuntime().loadLibrary("Test");

    }
    public static void main( String[] args )
    {

        System.out.print(new App().f(1,"stef"));
    }
}
