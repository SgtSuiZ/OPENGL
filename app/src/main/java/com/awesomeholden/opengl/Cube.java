package com.awesomeholden.opengl;

/**
 * Created by holden on 10/27/15.
 */
public class Cube extends Squares {

    public static float size = 1;

    public Cube(float x,float y,float z){
        addVert(x,y,z);
        addVert(x+1,y,z);
        addVert(x,y-1,z);
        addVert(x+1,y-1,z);

        for(int i=0;i<4;i++)
            addColor(1,0,0);

        addVert(x,y-1,z+1);
        addVert(x+1,y-1,z+1);

        addColor(0,1,0);
        addColor(0,1,0);

        addVert(x,y,z+1);
        addVert(x+1,y,z+1);

        addColor(0,0,1);
        addColor(0,0,1);

        addVert(x,y,z);
        addVert(x+1,y,z);

        addColor(0.5f,0.5f,0);
        addColor(0.5f,0.5f,0);
    }

}
