package com.awesomeholden.opengl;

/**
 * Created by holden on 10/26/15.
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;
/*
 * A square drawn in 2 triangles (using TRIANGLE_STRIP).
 */
public class SquareCache {
    private FloatBuffer vertexBuffer;  // Buffer for vertex-array

    public static Vector<Float> vertices = new Vector<Float>();/*{  // Vertices for the square
            -1.0f, -1.0f,  0.0f,  // 0. left-bottom
            1.0f, -1.0f,  0.0f,  // 1. right-bottom
            -1.0f,  1.0f,  0.0f,  // 2. left-top
            1.0f,  1.0f,  0.0f   // 3. right-top
    };*/

    public static void addVert(float x,float y,float z){
        vertices.add(x);
        vertices.add(y);
        vertices.add(z);
    }

    // Constructor - Setup the vertex buffer
    public SquareCache() {
        // Setup vertex array buffer. Vertices in float. A float has 4 bytes
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.size() * 4);
        vbb.order(ByteOrder.nativeOrder()); // Use native byte order
        vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
        vertexBuffer.put(Extra.toPrimitiveArray((Float[]) vertices.toArray()));         // Copy data into buffer
        vertexBuffer.position(0);           // Rewind
    }

    // Render the shape
    public void draw(GL10 gl) {
        // Enable vertex-array and define its buffer
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        // Draw the primitives from the vertex-array directly
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.size() / 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}