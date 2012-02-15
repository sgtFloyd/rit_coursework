/* 
** File:	drawLine.c
** Author:	Gabriel Smith (ges7506)
** Template by:	Warren R. Carithers
*/

#include <GL/glut.h>

#include <drawLine.h>

/*
** drawLine
**
**  Draw a line from vertex (x0,y0) to vertex (x1,y1) using the
**  midpoint line algorithm (as discussed in class). Referenced
**  class notes and wikipedia to assist in implementation.
**
*/

/*
 * Simple swap function to swap two variables
 */ 
void swap (GLint *a, GLint *b) {
  GLint c = *a;
  *a = *b;
  *b = c;    
}

/*
 * Implements Bresenham's line algorithm to draw a line from
 * coordinates (x0,y0) to (x1, y1). 
 */  
void drawLine( GLint x0, GLint y0, GLint x1, GLint y1 ) {

  // normalize x and y values to ensure:
  //    - points are drawn left to right
  //    - points are gives as a shallow slope
  int slope = (abs(y1-y0) > abs(x1-x0));
  if (slope) {
    swap(&x0, &y0);
    swap(&x1, &y1);
  }
  if (x0 > x1) {
    swap (&x0, &x1);
    swap (&y0, &y1);
  }
  int yDir=0;
  if (y0<y1) {
    yDir = 1;  // positive slope
  } else {
    yDir = -1; // negative slope
  }
  
  // calculate initial values
  int deltaX = x1-x0;
  int deltaY = abs(y1-y0);
  double deltaError = deltaY/deltaX;
  double error = 0.0;
  int y=y0;
  int x=0;
  
  // begin algorithm
  for (x=x0; x<x1; x++){
    if (slope) {
      setPixel(x,y);  
    } else {
      setPixel(y,x);
    }
    error = error + deltaError;
    if (error >= 0.5) {
      y = y+yDir;
      error = error - yDir;
    }
  }
}
