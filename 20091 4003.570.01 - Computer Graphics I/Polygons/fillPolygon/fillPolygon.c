/* 
** File:	fillPolygon.c
** Author:	Gabriel Smith (ges7506)
** Template by:	Warren R. Carithers
*/

#include <GL/glut.h>

#include <fillPolygon.h>

//*TEST*
#include <stdio.h>



/*
** fillPolygon
**
** Draw a filled polygon using the scan-line-based polygon fill algorithm
** discussed in class. The polygon has n distinct vertices.
*
* Yes, I am aware that this doens't function properly.
* It builds the edge table, and iterates through it with the AEL properly,
* but fails miserably when it tries to draw the lines.
*/

// Edge: [ymax, x, dx, dy, sum, *nextEdge]
typedef struct _Edge {
  int ymax;
  int x;
  int dx;
  int dy;
  int sum;
  struct _Edge *next;
} Edge;
Edge *ET[300];    //Edge Table
Edge *AEL = NULL; //Active Edge List

/* Adds edge with specified values to ET[ymin]
 *  If an Edge already exists there, we traverse the list until we
 *  find the proper spot to insert this edge.
 */ 
void addEdge(int ymin, int ymax, int x, int dx, int dy){
  Edge *newEdge = (Edge *)malloc(sizeof(Edge));
  newEdge->ymax = ymax;
  newEdge->x = x;
  newEdge->dx = dx;
  newEdge->dy = dy;
  newEdge->sum = 0;
  newEdge->next = NULL;
  // If no edges added at specified index
  if (ET[ymin]==NULL) {
    ET[ymin] = newEdge;
  } else {
    //Add to Beginning of List
    if (x < ET[ymin]->x) {
      Edge *nextEdge = ET[ymin];
      newEdge->next = nextEdge;
      ET[ymin] = newEdge; 
    } else {
      // traverse list of edges until proper spot is found
      Edge *currEdge = ET[ymin];
      int mCondition = 0;
      while ((currEdge->next != NULL)
              && (currEdge->next->x <= x)
              && (mCondition==0)){
        if (currEdge->next->x == x) {
          double m = dx/dy;
          double nextM = currEdge->next->dx / currEdge->next->dy;
          if (nextM <= m) {
            currEdge = currEdge->next;
          } else { mCondition = 1; }
        } else {
          currEdge = currEdge->next;
        }
      }
      //Add to End of List
      if (currEdge->next == NULL) {
        currEdge->next = newEdge;        
      //Add to Middle of List
      } else {
        Edge *nextEdge = currEdge->next;
        newEdge->next = nextEdge;
        currEdge->next = newEdge;
      }
    }
  }
}

//search ET and AEL for any remaining edges, return 1 if at least one is found
int stillEdges() {
  int emptyET = 1;
  int i=0;
  for (i=0; i<300; i++){
    if (ET[i] != NULL){
      emptyET = 0;
      i=300; //break
    }
  }
  if (emptyET && (AEL==NULL)){
    return 0; 
  } else {
    return 1;
  }
}

//TEST FUNCTION:
// prints out Edge Table to console
// only prints rows with edges in them
void printET() {
  int i=0;
  for (i=0; i<300; i++) {
    if (ET[i] != NULL){
      printf("[%d]:", i);
      Edge *e = ET[i];
      do {
        if (e->next != NULL) {
          printf("->[%d, %d, %d, %d]", e->ymax, e->x, e->dx, e->dy);
          e = e->next;
        }  
      } while (e->next != NULL);
      printf("->[%d, %d, %d, %d]\n", e->ymax, e->x, e->dx, e->dy);
    }
  }
}

//TEST FUNCTION:
// prints out Active Edge List to console
void printAEL() {
  Edge *e = AEL;
  printf("AEL:");
  while (e != NULL) {
    printf("[%d, %d, %d, %d]->", e->ymax, e->x, e->dx, e->dy);
    e = e->next;
  }
  printf("NULL\n");
}

//swaps 2 variables
void swap (GLint *a, GLint *b) {
  GLint c = *a;
  *a = *b;
  *b = c;    
}

/*
 * Implements Bresenham's line algorithm to draw a line from
 * coordinates (x0,y0) to (x1, y1).
 * 
 * Modified, since only horizontal lines will be drawn    
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
  if (deltaX == 0){
    deltaX = 0.0001;
  }
  int deltaY = abs(y1-y0);
  int y=y0;
  int x=0;
  
  // begin algorithm
  for (x=x0; x<x1; x++){
    if (slope) {
      setPixel(x,y);  
    } else {
      setPixel(y,x);
    }
  }
}

void fillPolygon( GLint n, Vertex v[] ) {
  // Build Edge Table
  int i=0; //Clear out any previous ET
  for (i=0; i<300; i++){ET[i] = NULL;}
  for (i=0; i<n; i++) {
    // parse vertices to x and y coordinates
    int x0 = v[i].x;
    int y0 = v[i].y;
    int x1, y1=0;
    if (i==n-1) {
      x1 = v[0].x;
      y1 = v[0].y;
    } else {
      x1 = v[i+1].x;
      y1 = v[i+1].y;
    }
    
    //Processing edge from x0,y0 -> x1,y1
    int ymin,ymax,x,dx,dy=0;
    dx = x0-x1;
    dy = y0-y1;
    if (y0<y1){
      x = x0;
      ymin = y0;
      ymax = y1;
    } else {
      x = x1;
      ymin = y1;
      ymax = y0;
    }
    //Add edge to ET
    if (dy != 0) { //ignore horizontal edges
      addEdge(ymin, ymax, x, dx, dy);
    }
  }
  
  // Set y = index of the first non-empty Edge Table index
  int y = 0;
  for (int i=0; i<300; i++) {
    if (ET[i] != NULL) {
      y = i;
      i = 300; //break
    }
  }
  /*** (step4) Run algorithm:
          xWhile there are still edges,
          x  Discard Active Edge List entries where y = ymax
          x  Move from Edge Table[y] to Active Edge List when ymin = y
            Sort Active Edge List on x; within groups of same x, sort on 1/m
            Fill pixels on scan line y using pairs of x coordinates from AEL
            Increment y
            For each non-vertical edge in Active Edge List
             Increment sum
             If sum >= dy, adjust x and sum ***/           
                        
  while (stillEdges()){
    // Discard Active Edge List entries where y = ymax
    Edge *currEdge = AEL;
    Edge *prevEdge = NULL;
    while (currEdge != NULL) {
      if (currEdge->ymax == y) {
        if (prevEdge == NULL) {
          //beginning of list, simply remove first element
          AEL = AEL->next;
          currEdge = AEL;
        } else {
          prevEdge->next = currEdge->next;
          currEdge = prevEdge->next;
        }
      } else { 
        // ymax != y, continue down list
        prevEdge = currEdge;
        currEdge = currEdge->next;
      }
    }
    // Move from Edge Table[y] to Active Edge List when ymin = y
    if (ET[y] != NULL) { // only when ymin=y
      if (AEL == NULL) {
        AEL = ET[y]; // AEL empty
      } else {
        Edge* currEdge = AEL;
        //get end of list
        while (currEdge->next != NULL) {
          currEdge = currEdge->next;
        }
        //attach edges in ET to end of AEL
        currEdge->next = ET[y];
      }
      //remove from ET
      ET[y] = NULL;
    }
    // Fill pixels on scan line y using pairs of x coordinates from AEL
    if (AEL != NULL){
      int moreEdges = 1;
      Edge *edge1 = AEL;
      Edge *edge2 = edge1->next;
      drawLine(edge1->x, y, edge2->x, y);
      int loop = 1;
      while (moreEdges){
        if (edge2->next == NULL){
          moreEdges=0;
        } else {
          edge1 = edge2->next;
          edge2 = edge1->next;
          drawLine(edge1->x, y, edge2->x, y);
        }  
      } 
    }
    // Increment y
    y++;
    //For each non-vertical edge in Active Edge List
    //  - Increment sum
    //  - If sum >= dy, adjust x and sum
    
    currEdge = AEL;
    while (currEdge != NULL) {
      if (currEdge->dx != 0) {
        currEdge->sum = currEdge->sum + currEdge->dx;
        while (currEdge->sum >= currEdge->dy){
          currEdge->sum = currEdge->sum - currEdge->dy;
          currEdge->x = currEdge->x + 1;
        }
      }
      currEdge = currEdge->next;
    }
      
  }
}
