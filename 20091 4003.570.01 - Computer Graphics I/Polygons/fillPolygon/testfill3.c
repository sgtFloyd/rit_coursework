/*
** testfill3
**
** This program is designed to test filling concave figures
** using the supplied fillPolygon() routine
*/

#include <support.h>		/* also includes GL/glut.h et. al. */

#include <fillPolygon.h>

#ifdef __cplusplus
extern "C" {
#endif

void display( void ) {
	Vertex v[10];

	glClear( GL_COLOR_BUFFER_BIT );

	/* 
	** First, draw a basic pentagon, vertices specified in 
	** counter-clockwise (CCW) sequence, in red
	*/
	v[0].x = 100; v[0].y = 10;
	v[1].x = 120; v[1].y = 10;
	v[2].x = 130; v[2].y = 30;
	v[3].x = 110; v[3].y = 50;
	v[4].x =  90; v[4].y = 30;

	glColor3f( 1.0, 0.0, 0.0 );
	fillPolygon( 5, v );

	/*
	** Pentagon, concave on right, LL, CCW, green
	*/
	v[0].x =  80; v[0].y =  70;
	v[1].x = 140; v[1].y =  70;
	v[2].x = 100; v[2].y =  90;
	v[3].x = 110; v[3].y = 130;
	v[4].x =  60; v[4].y = 110;

	glColor3f( 0.0, 1.0, 0.0 );
	fillPolygon( 5, v );

	/*
	** Pentagon, concave on top, LL, CCW, black
	*/
	v[0].x =  80; v[0].y = 150;
	v[1].x = 140; v[1].y = 150;
	v[2].x = 160; v[2].y = 190;
	v[3].x = 100; v[3].y = 170;
	v[4].x =  60; v[4].y = 190;

	glColor3f( 0.0, 0.0, 0.0 );
	fillPolygon( 5, v );

	/*
	** Pentagon, concave on left, LL, CCW, blue
	*/
	v[0].x = 180; v[0].y =  70;
	v[1].x = 240; v[1].y =  70;
	v[2].x = 260; v[2].y = 110;
	v[3].x = 210; v[3].y = 130;
	v[4].x = 220; v[4].y =  90;

	glColor3f( 0.0, 0.0, 1.0 );
	fillPolygon( 5, v );

	/*
	** Pentagon, concave on bottom, LL, CCW, cyan
	*/
	v[0].x = 160; v[0].y = 150;
	v[1].x = 200; v[1].y = 170;
	v[2].x = 260; v[2].y = 150;
	v[3].x = 240; v[3].y = 190;
	v[4].x = 180; v[4].y = 190;

	glColor3f( 0.0, 1.0, 1.0 );
	fillPolygon( 5, v );

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

	init( argc, argv, "PolyFill Test 3" );

	/*
	** Enter the event loop
	*/

	glutMainLoop( );

	/* this statement is never reached */
	return( 0 );

}
