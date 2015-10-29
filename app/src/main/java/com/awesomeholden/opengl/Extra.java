package com.awesomeholden.opengl;

import java.util.Random;

/**
 * Created by holden on 10/27/15.
 */
public class Extra {

    public static float[] toPrimitiveArray(Object[] a){
        float[] ret = new float[a.length];

        for(int i=0;i<a.length;i++)
            ret[i] = (float)a[i];

        return ret;
    }

    public static Random rand = new Random();
}
