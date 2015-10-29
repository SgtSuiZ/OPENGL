package com.awesomeholden.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.util.Vector;

/**
 *  OpenGL Custom renderer used with GLSurfaceView
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {
    Context context;   // Application's context

    TriangleCache triangle;     // ( NEW )
    Cube quad;           // ( NEW )

    public Vector<Cube> quads = new Vector<Cube>();



    // Constructor with global application context
    public MyGLRenderer(Context context) {
        this.context = context;

        triangle = new TriangleCache();   // ( NEW )
        quad = new Cube(0,0,0);         // ( NEW )

        for(int i=0;i<5000;i++) {
            quads.add(new Cube((Extra.rand.nextFloat() * 4.0f) - 2.0f, (Extra.rand.nextFloat() * 4.0f) - 2.0f, (Extra.rand.nextFloat() * 4.0f) - 2.0f));
            quads.get(i).registerVerts();
        }

        /*quad.addVert(-1.0f, -1.0f,  0.0f);
        quad.addVert(1.0f, -1.0f,  0.0f);
        quad.addVert(-1.0f,  1.0f,  0.0f);
        quad.addVert(1.0f,  1.0f,  0.0f);
        quad.addVert(1,1,2);
        quad.addVert(1,-1,2);
        //quad.addVert(-1,-1,2);*/

        quad.registerVerts();

    }

    // Call back when the surface is first created or re-created
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

        // You OpenGL|ES initialization code here
        // ......
    }

    // Call back after onSurfaceCreated() or whenever the window's size changes
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float)width / height;

        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 60, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset

        // You OpenGL|ES display re-sizing code here
        // ......
    }

    public float z = 0;
    public int degrees = 0;
    // Call back to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers using clear-value set earlier
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();                 // Reset model-view matrix ( NEW )
        gl.glTranslatef(0, 0.0f, z); // Translate left and into the screen ( NEW )
        gl.glRotatef(degrees, 0, 1, 1);
        quad.draw(gl);                   // Draw triangle ( NEW )

        for(int i=0;i<5000;i++)
            quads.get(i).draw(gl);

        // Translate right, relative to the previous translation ( NEW )
        //gl.glTranslatef(3.0f, 0.0f, 0.0f);
        //quad.draw(gl);

        z-=0.01f;

        degrees++;
        if(degrees>360)
            degrees = 0;
    }
}