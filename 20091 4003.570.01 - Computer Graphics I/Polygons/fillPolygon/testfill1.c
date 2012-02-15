/*
** testfill1
**
** This program is designed to test filling rectangles
** using the supplied fillPolygon() routine
*/

#include <support.h>		/* also includes GL/glut.h, et. al. */

#include <fillPolygon.h>

#ifdef __cplusplus
extern "C" {
#endif

void display( void ) {
	Vertex v[4];

	glClear( GL_COLOR_BUFFER_BIT );	/* clear the display window */

	/* 
	** Use student's fillPolygon() to draw polygons of
	** various sizes, shapes, and orientations
	*/

	/*
	** First, draw a basic quad from (10,10) to (20,20), vertices
	** specified in counter-clockwise (CCW) sequence from the 
	** lower-left (LL) corner, in red
	*/

	v[0].x = 10; v[0].y = 10;
	v[1].x = 20; v[1].y = 10;
	v[2].x = 20; v[2].y = 20;
	v[3].x = 10; v[3].y = 20;

	glColor3f( 1.0, 0.0, 0.0 );	
	fillPolygon( 4, v );

	/*
	** Quad from (40,30) to (30,50), CCW, LR, green
	*/

	v[0].x = 40; v[0].y = 30;
	v[1].x = 40; v[1].y = 50;
	v[2].x = 30; v[2].y = 50;
	v[3].x = 30; v[3].y = 30;

	glColor3f( 0.0, 1.0, 0.0 );
	fillPolygon( 4, v );

	/*
	** Quad, (10,70) to (40,90), UR, CW, blue
	*/
	v[0].x = 40; v[0].y = 90;
	v[1].x = 40; v[1].y = 70;
	v[2].x = 10; v[2].y = 70;
	v[3].x = 10; v[3].y = 90;

	glColor3f( 0.0, 0.0, 1.0 );
	fillPolygon( 4, v );

	/*
	** Quad, (10,110) to (40,130), UL, CW, cyan
	*/
	v[0].x = 10; v[0].y = 130;
	v[1].x = 40; v[1].y = 130;
	v[2].x = 40; v[2].y = 110;
	v[3].x = 10; v[3].y = 110;

	glColor3f( 0.0, 1.0, 1.0 );
	fillPolygon( 4, v );

	/*
	** Non-aligned quad, CCW, black
	*/
	v[0].x = 30; v[0].y = 150;
	v[1].x = 50; v[1].y = 180;
	v[2].x = 30; v[2].y = 210;
	v[3].x = 10; v[3].y = 180;

	glColor3f( 0.0, 0.0, 0.0 );
	fillPolygon( 4, v );

	/* 
	** Ensure that everything is displayed
	*/
	glFlush();

}

#ifdef __cplusplus
}
#endif


/* 
** Main routine - GLUT setup and initialization 
*/

int main( int argc, char** argv ) {

	/*
	** Call our generic "initialize everything" routine
	*/

	init( argc, argv, "PolyFill Test 1 - Quads" );

	/*
	** Enter the event loop
	*/

	glutMainLoop( );

	/* this statement is never reached */
	return( 0 );

}
