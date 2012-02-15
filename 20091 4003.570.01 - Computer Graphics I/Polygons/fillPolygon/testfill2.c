/*
** testfill2
**
** This program is designed to test filling triangles
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
	** Triangle, isoceles. aligned lower edge, magenta
	*/
	v[0].x = 100; v[0].y = 10;
	v[1].x = 150; v[1].y = 10;
	v[2].x = 125; v[2].y = 20;

	glColor3f( 1.0, 0.0, 1.0 );
	fillPolygon( 3, v );

	/*
	** Triangle, aligned top, tail on left, yellow
	*/
	v[0].x = 100; v[0].y = 30;
	v[1].x = 140; v[1].y = 50;
	v[2].x = 175; v[2].y = 50;

	glColor3f( 1.0, 1.0, 0.0 );
	fillPolygon( 3, v );

	/*
	** Triangle, aligned top, tail on right, black
	*/
	v[0].x = 120; v[0].y = 40;
	v[1].x = 80;  v[1].y = 60;
	v[2].x = 45;  v[2].y = 60;

	glColor3f( 0.0, 0.0, 0.0 );
	fillPolygon( 3, v );

	/*
	** Triangle, right
	*/
	v[0].x = 10; v[0].y = 100;
	v[1].x = 10; v[1].y = 120;
	v[2].x = 25; v[2].y = 100;

	glColor3f( 1.0, 1.0, 0.5 );
	fillPolygon( 3, v );

	/*
	** Triangle, right
	*/
	v[0].x = 10; v[0].y = 130;
	v[1].x = 20; v[1].y = 130;
	v[2].x = 20; v[2].y = 140;

	glColor3f( 1.0, 0.5, 1.0 );
	fillPolygon( 3, v );

	/*
	** Triangle, right
	*/
	v[0].x = 10; v[0].y = 170;
	v[1].x = 20; v[1].y = 170;
	v[2].x = 10; v[2].y = 150;

	glColor3f( 0.5, 1.0, 1.0 );
	fillPolygon( 3, v );

	/*
	** Triangle, aligned bottom, tail upper left
	*/
	v[0].x = 100; v[0].y = 70;
	v[1].x = 150; v[1].y = 70;
	v[2].x = 75;  v[2].y = 90;

	glColor3f( 1.0, 0.5, 0.5 );
	fillPolygon( 3, v );

	/*
	** Triangle, aligned bottom, tail upper right
	*/
	v[0].x = 100; v[0].y = 100;
	v[1].x = 150; v[1].y = 100;
	v[2].x = 195; v[2].y = 120;

	glColor3f( 0.5, 0.5, 1.0 );
	fillPolygon( 3, v );

	/*
	** Triangle, scalene
	*/
	v[0].x = 100; v[0].y = 170;
	v[1].x = 150; v[1].y = 150;
	v[2].x = 175; v[2].y = 130;

	glColor3f( 0.5, 0.5, 0.5 );
	fillPolygon( 3, v );


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

	init( argc, argv, "PolyFill Test 2 - Triangles" );

	/*
	** Enter the event loop
	*/

	glutMainLoop( );

	/* this statement is never reached */
	return( 0 );

}
