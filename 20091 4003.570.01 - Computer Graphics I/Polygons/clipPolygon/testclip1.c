/*
**  testclip1
**
**  Designed to test the implementation of clipPolygon() with polygons
**  of varying sizes and shapes.
*/

#include <support.h>		/* also includes GL/glut.h, et. al. */

#include <clipPolygon.h>

/*
** Vertex arrays
**
** First, some quads to be drawn
*/

Vertex quad1[4] = { { 20, 20 }, { 20, 40 }, { 40, 40 }, { 40, 20 } };
Vertex quad2[4] = { { 40, 100 }, { 40, 140 }, { 20, 140 }, { 20, 100 } };
Vertex quad3[4] = { { 20, 60 }, { 50, 60 }, { 50, 50 }, { 20, 50 } };
Vertex quad4[4] = { { 44, 22 }, { 60, 22 }, { 60, 46 }, { 44, 46 } };

/*
** A triangle
*/

Vertex tri1[3] = { { 40, 70 }, { 60, 70 }, { 50, 90 } };

/*
** A pentagon
*/

Vertex pent1[5] = {
	{ 80, 20 }, { 90, 10 }, { 110, 20 }, { 100, 50 }, { 80, 40 }
};

/*
** Clipping regions
*/

Vertex clip1[4] = { { 10, 10 }, { 50,  10 }, { 50,   50 }, { 10, 50 } };
Vertex clip2[4] = { { 30, 10 }, { 70,  10 }, { 70,   80 }, { 30, 80 } };

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
	print_poly( 4, clip1 );
	print_poly( 4, clip2 );

	/*
	** For each polygon, print the polygon before and after
	** clipping in a polygon-specific color; print the original
	** using a dotted line of width 2, and the clipped using a
	** solid line of width 3
	*/

	/* 
	** first polygon:  entirely within region
	*/

	wl = 0;
	glColor3f( 1.0, 0.0, 0.0 );		/* red */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 4, quad1 );
	clipPolygon( 4, quad1, &wl, w, clip1[0], clip1[2] );
	glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
	print_poly( wl, w );

	/*
	** second polygon:  entirely outside region
	*/

	wl = 0;
	glColor3f( 0.0, 1.0, 0.0 );		/* green */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 4, quad2 );
	clipPolygon( 4, quad2, &wl, w, clip1[0], clip1[2] );
	/* shouldn't draw anything! */
	if( wl > 0 ) {
		glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
		print_poly( wl, w );
	}

	/*
	** third polygon: triangle, one point outside region
	*/

	wl = 0;
	glColor3f( 0.0, 1.0, 1.0 );             /* cyan */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 3, tri1 );
	clipPolygon( 3, tri1, &wl, w, clip2[0], clip2[2] );
	glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
	print_poly( wl, w );

	/*
	** fourth polygon:  part outside on right
	*/

	wl = 0;
	glColor3f( 1.0, 0.0, 1.0 );		/* magenta */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 4, quad4 );
	clipPolygon( 4, quad4, &wl, w, clip1[0], clip1[2] );
	glLineStipple( 1, 0xffff ); glLineWidth( 3.0 );
	print_poly( wl, w );

	/*
	** fifth polygon:  halfway outside on left
	*/

	wl = 0;
	glColor3f( 0.0, 0.0, 1.0 );		/* blue */
	glLineStipple( 1, 0x0f0f ); glLineWidth( 2.0 );
	print_poly( 4, quad3 );
	clipPolygon( 4, quad3, &wl, w, clip2[0], clip2[2] );
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

	init( argc, argv, "PolyClip Test 1" );

	/*
	** Enter the event loop
	*/

	glutMainLoop( );

	/* this statement is never reached */
	return( 0 );

}
