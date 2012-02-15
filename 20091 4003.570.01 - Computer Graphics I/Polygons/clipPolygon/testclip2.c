/*
**  testclip2
**
**  Designed to test the implementation of clipPolygon() with polygons
**  of varying sizes and shapes.
*/

#include <support.h>		/* also includes GL/glut.h, et. al. */

#include <clipPolygon.h>

/*
** Vertex arrays
*/

/*
** Clipping regions
*/

Vertex clip4[4] = { { 90, 34 }, { 120, 34 }, { 120,  60 }, { 90, 60 } };
Vertex clip5[4] = { { 90, 80 }, { 130, 80 }, { 130, 110 }, { 90, 110 } };
Vertex clip6[4] = { { 30, 20 }, { 40, 20 }, { 40, 30 }, { 30, 30 } };
Vertex clip7[4] = { { 30, 90 }, { 62, 90 }, { 62, 120 }, { 30, 120 } };

/*
** Two pentagons
*/

Vertex pent1[5] = {
	{ 20, 20 }, { 30, 10 }, { 50, 20 }, { 40, 50 }, { 20, 40 }
};

Vertex pent2[5] = {
	{ 80, 20 }, { 90, 10 }, { 110, 20 }, { 100, 50 }, { 80, 40 }
};

/*
** Two heptagons
*/

Vertex hept1[7] = {
	{ 120, 70 }, { 140, 70 }, { 160, 80 }, { 160, 100 },
	{ 130, 130 }, { 110, 120 }, { 100, 90 }
};

Vertex hept2[7] = {
	{ 30, 70 }, { 50, 70 }, { 70, 80 }, { 70, 100 },
	{ 40, 130 }, { 20, 120 }, { 10, 90 }
};

/*
** Variables for use when calling clipPolygon()
*/

Vertex w[50];
GLint wl;

/*
** Display routine - does all the real work
*/

#ifdef __cplusplus
extern "C" {
#endif

void display( void ) {

	glClear( GL_COLOR_BUFFER_BIT );

	/*
	** Begin by drawing all the clip regions in black
	** using a solid line of width 1
	*/

	glColor3f( 0.0, 0.0, 0.0 );
	glLineStipple( 1, 0xffff );
	glLineWidth( 1.0 );
	print_poly( 4, clip4 );
	print_poly( 4, clip5 );
	print_poly( 4, clip6 );
	print_poly( 4, clip7 );

	/*
	** For each polygon, print the polygon before and after
	** clipping in a polygon-specific color; print the original
	** using a dotted line of width 2, and the clipped using a
	** solid line of width 3
	*/

	/*
	** first polygon:  encloses clipping region
	*/

	wl = 0;
	glColor3f( 1.0, 0.0, 0.0 );		/* red */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 5, pent1 );
	clipPolygon( 5, pent1, &wl, w, clip6[0], clip6[2] );
	glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
	print_poly( wl, w );

	/*
	** second polygon:  outside on left and bottom
	*/

	wl = 0;
	glColor3f( 0.0, 1.0, 0.0 );		/* green */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 5, pent2 );
	clipPolygon( 5, pent2, &wl, w, clip4[0], clip4[2] );
	glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
	print_poly( wl, w );

	/*
	** third polygon:  outside on top, right, and bottom
	*/

	wl = 0;
	glColor3f( 0.0, 0.0, 1.0 );		/* blue */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 7, hept1 );
	clipPolygon( 7, hept1, &wl, w, clip5[0], clip5[2] );
	glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
	print_poly( wl, w );

	/*
	** fourth polygon:  all vertices outside, edge crosses region
	*/

	wl = 0;
	glColor3f( 0.0, 1.0, 1.0 );		/* cyan */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 7, hept2 );
	clipPolygon( 7, hept2, &wl, w, clip7[0], clip7[2] );
	glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
	print_poly( wl, w );

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

	init( argc, argv, "PolyClip Test 2" );

	/*
	** Enter the event loop
	*/

	glutMainLoop( );

	/* this statement is never reached */
	return( 0 );

}
