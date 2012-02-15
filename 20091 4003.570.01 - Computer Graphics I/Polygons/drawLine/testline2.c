/*
**  testline
**
**  This program is designed to test "drawLine".
*/

#include <support.h>		/* also includes GL/glut.h et. al. */

#include <drawLine.h>

#ifdef __cplusplus
extern "C" {
#endif

void display( void ) {

	glClear( GL_COLOR_BUFFER_BIT );	/* clear the display window */

	/*
	** Draw various line segments
	**
	** All segments radiate from a common vertex at (100,100)
	*/

	/*
	** First, draw left-to-right, bottom-to-top segments in black
	*/

	glColor3f( 0.0, 0.0, 0.0 );

	drawLine( 100, 100, 100, 150 );  /* Vertical */
	drawLine( 100, 100, 150, 100 );  /* Horizontal */
	drawLine( 100, 100, 150, 150 );  /* + diagonal */
	drawLine( 100, 100, 150, 50 );   /* - diagonal */
	drawLine( 100, 100, 150, 125 );  /* shallow + slope */
	drawLine( 100, 100, 150, 75 );   /* shallow - slope */
	drawLine( 100, 100, 125, 150 );  /* steep + slope */
	drawLine( 100, 100, 125, 50 );   /* steep - slope */

	/*
	** Now, draw right-to-left, top-to-bottom versions in red
	*/

	glColor3f( 1.0, 0.0, 0.0 );

	drawLine( 100, 100, 100, 50 );   /* Vertical */
	drawLine( 100, 100, 50, 100 );   /* Horizontal */
	drawLine( 100, 100, 50, 50 );    /* + diagonal */
	drawLine( 100, 100, 50, 150 );   /* - diagonal */
	drawLine( 100, 100, 50, 75 );    /* shallow + slope */
	drawLine( 100, 100, 50, 125 );   /* shallow - slope */
	drawLine( 100, 100, 75, 50 );    /* steep + slope */
	drawLine( 100, 100, 75, 150 );   /* steep - slope */

	/*
	** Finally, draw some polygons in blue
	*/

	glColor3f( 0.0, 0.0, 1.0 );

	/* triangle */
	drawLine(  50, 200,  80, 200 );
	drawLine(  80, 200,  70, 230 );
	drawLine(  70, 230,  50, 200 );

	/* pentagon */
	drawLine(  90, 200, 120, 200 );
	drawLine( 120, 200, 140, 220 );
	drawLine( 140, 220, 120, 240 );
	drawLine( 120, 240,  90, 240 );
	drawLine(  90, 240,  90, 200 );

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

	init( argc, argv, "Line Test 2" );

	/*
	** Enter the event loop
	*/

	glutMainLoop( );

	/* this statement is never reached */
	return( 0 );

}
