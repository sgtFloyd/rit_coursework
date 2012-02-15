/* 
** File:	clipPolygon.?
** Author:	?
** Template by:	Warren R. Carithers
*/

#include <GL/glut.h>

#include <clipPolygon.h>

/*
** clipPolygon
**
** Clip a polygon using the Sutherland-Hodgman Polygon Clipping algorithm
** discussed in class and in the text. The polygon is specified with vertex
** count in and vertices inv; the clipping region is rectangular, and has
** lower-left corner ll and upper-right corner ur. The resulting vertices
** are placed in outv, with the vertex count placed in out.
*/

void clipPolygon( GLint in, Vertex inv[],
		  GLint *out, Vertex outv[],
		  Vertex ll, Vertex ur ) {

	/* your implementation here */

}

