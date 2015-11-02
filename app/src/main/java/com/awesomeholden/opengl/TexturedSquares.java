package com.awesomeholden.opengl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by holden on 11/2/15.
 */
public class TexturedSquares extends Squares {

    public Vector<Float> texCoords = new Vector<Float>();

    public FloatBuffer texCoordBuffer;

    public int texture;

    public static GL10 gl;

    public TexturedSquares(Bitmap bitmap){
        super();

        texture = MyGLRenderer.loadGLTexture(gl,MyGLSurfaceView.context,bitmap);
    }

    @Override
    public void registerVerts(){
        super.registerVerts();

        ByteBuffer b = ByteBuffer.allocateDirect(texCoords.size() * 4);
        b.order(ByteOrder.nativeOrder()); // Use native byte order
        texCoordBuffer = b.asFloatBuffer(); // Convert from byte to float
        texCoordBuffer.put(Extra.toPrimitiveArray(texCoords.toArray()));         // Copy data into buffer
        texCoordBuffer.position(0);
    }

    public void addTexCoord(float f0, float f1){
        texCoords.add(f0);
        texCoords.add(f1);
    }

    @Override
    public void draw(GL10 gl){

        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glDisable(gl.GL_LIGHTING);
        gl.glFrontFace(GL10.GL_CW);
        gl.glBindTexture(gl.GL_TEXTURE_2D, MyGLRenderer.textures.get(texture));
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoordBuffer);
        super.draw(gl);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glDisable(gl.GL_TEXTURE_2D);

        //for(int i=0;i<texCoords.size();i++){
            /*texCoords.set(3,texCoords.get(3)+0.001f);
        System.out.println("TEXCOORDS 3: "+texCoords.get(3));*/
        //}
    }
}
