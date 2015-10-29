package com.awesomeholden.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by holden on 10/27/15.
 */
public class Squares {

    public Vector<Float> verts = new Vector<Float>();

    public Vector<Float> colors = new Vector<Float>();
    private FloatBuffer vertexBuffer;

    private FloatBuffer colorBuffer;

    public void registerVerts(){
        ByteBuffer vbb = ByteBuffer.allocateDirect(verts.size() * 4);
        vbb.order(ByteOrder.nativeOrder()); // Use native byte order
        vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
        vertexBuffer.put(Extra.toPrimitiveArray(verts.toArray()));         // Copy data into buffer
        vertexBuffer.position(0);

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(colors.size() * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        colorBuffer = byteBuf.asFloatBuffer();
        colorBuffer.put(Extra.toPrimitiveArray(colors.toArray()));
        colorBuffer.position(0);
    }

    public void draw(GL10 gl) {
        // Enable vertex-array and define its buffer
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        // Draw the primitives from the vertex-array directly
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verts.size() / 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }

    public void addVert(float x,float y,float z){
        verts.add(x);
        verts.add(y);
        verts.add(z);
    }

    public void addColor(float r,float g,float b){
        colors.add(r);
        colors.add(g);
        colors.add(b);
    }

    public void addColor(float r,float g,float b,float a){
        addColor(r,g,b);
        colors.add(a);
    }



}
