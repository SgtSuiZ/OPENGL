package com.awesomeholden.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by holden on 10/26/15.
 */
public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public static Context context;

    public MyGLSurfaceView(Context context){
        super(context);

        this.context = context;

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(1);

        mRenderer = new MyGLRenderer(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }

    public int prevX = 0;
    public int prevY = 0;

    public MotionEvent.PointerCoords prevPointerCoords0 = new MotionEvent.PointerCoords();
    public MotionEvent.PointerCoords prevPointerCoords1 = new MotionEvent.PointerCoords();

    public static boolean down = false;

    public float difDivision = 2;

    public float prevDifY1 = 0;

    public boolean firstItteration = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                prevX = x;
                prevY = y;
                down = true;

                /*event.getPointerCoords(0, prevPointerCoords0);

                if(event.getPointerCount()==2) {
                    event.getPointerCoords(0, prevPointerCoords0);
                    event.getPointerCoords(1,prevPointerCoords1);
                    firstItteration = true;
                }*/
                break;
            case MotionEvent.ACTION_UP:
                down = false;
                break;
            default:
                break;
        }

        if(down) {
            float difX = (prevX-x)/difDivision;
            float difY = (prevY-y)/difDivision;

            if(event.getPointerCount()==2){
                MotionEvent.PointerCoords coords0 = new MotionEvent.PointerCoords(); event.getPointerCoords(0,coords0);
                MotionEvent.PointerCoords coords1 = new MotionEvent.PointerCoords(); event.getPointerCoords(1,coords1);

                if(coords0.y>coords1.y){
                        /*MotionEvent.PointerCoords tmp = coords0;
                        coords0 = coords1;
                        coords1 = tmp;*/
                }else{
                    MotionEvent.PointerCoords tmp = coords1;
                    coords1 = coords0;
                    coords0 = tmp;
                }

                float difY1 = (((prevPointerCoords0.y - coords0.y) - (prevPointerCoords1.y - coords1.y)) / difDivision);
                if(!firstItteration) {

                    //MyGLRenderer.degreesY-=difY1;

                    MyGLRenderer.zTranslation -= (difY1 / 10.0f);

                }

                prevPointerCoords0 = coords0;
                prevPointerCoords1 = coords1;
                prevDifY1 = difY1;

                firstItteration = false;
            }else {
                firstItteration = true;
                MyGLRenderer.degreesX -= difX;

                if (MyGLRenderer.degreesX > 360)
                    MyGLRenderer.degreesX = 0;
            }

        }

        prevX = x;
        prevY = y;
        return true;


    }

}
