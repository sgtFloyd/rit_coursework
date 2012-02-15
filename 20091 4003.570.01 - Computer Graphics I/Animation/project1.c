#if defined(__APPLE__) && defined(__MACH__)  // for mac
#   include <GLUT/glut.h>
#else // for others
#   include <GL/glut.h>
#endif
#include <math.h>

/*
 * project1.c
 *  Author: Gabriel Smith (ges7506@g.rit.edu)
 *
 *  This programs uses OpenGL to open a window and display an animation.
 *  The animation shows a stick figure doing the popular '80s dance,
 *  "The Running Man"
 *  
 *  At this point, entering 'Q' on the keyboard will not exit the program, it
 *  must be force quit from the command line. The animation loop must be put
 *  into its own thread before key presses will be registered.
 *     
 */

#define WINDOW_HEIGHT 475
#define WINDOW_WIDTH 350

// draws a circle of specified radius around cetner x,y
void drawCircle(GLint x, GLint y, int radius) {
  glBegin(GL_LINE_LOOP);
  int angle;
  for (angle=0; angle<360; angle=angle+1){
    glVertex2f(x + sin(angle)*radius, y + cos(angle)*radius);
  }
  glEnd();
}

//draws a 12px by 20px Rectangle around center x,y
void drawRectangle(GLint x, GLint y) {
  glBegin(GL_QUADS);
    glVertex2i(x-6, y-10);
    glVertex2i(x+6, y-10);
    glVertex2i(x+6, y+10);
    glVertex2i(x-6, y+10);
  glEnd();
}

//draws triangle around starting point x,y
void drawTriangle(GLint x, GLint y) {
  glBegin(GL_TRIANGLES);
    glVertex2i(x-8, y-2);
    glVertex2i(x+8, y-2);
    glVertex2i(x, y+18);
  glEnd();
}

void display() {
  // each frame of the animation is represented by 12 points
  // each point represents the location of a major joint of the running man
  GLint frame[22][12][2] = 
  {
    { // frame 0
      {181, 71}, {206,211}, // head [0] and waist [1]
      {132,277}, {183,334}, // knees [2] and [3]
      {219,287}, {215,418}, // feet [4] and [5]
      {160, 90}, {201,105}, // shoulders [6] and [7]
      {106,106}, {123,128}, // elbows [8] and [9]
      { 37,114}, { 45,129}  // hands [10] [11]
    },
    { // frame 1
      {156, 64}, {203,208},
      {130,280}, {205,320},
      {209,334}, {228,408},
      {157, 92}, {185, 93},
      {130,131}, {138,139},
      { 75,139}, { 71,159}
    },
    { // frame 2
      {146, 61}, {211,209},
      {130,292}, {197,327},
      {181,366}, {250,397},
      {151, 92}, {186, 97},
      {163,138}, {180,150},
      { 88,145}, {104,168}
    },
    { // frame 3
      {140, 74}, {207,208},
      {149,307}, {229,342},
      {167,395}, {279,395},
      {136,105}, {177,101},
      {158,150}, {198,149},
      {106,190}, {110,157}
    },
    { // frame 4
      {130, 83}, {205,231},
      {145,308}, {248,339},
      {168,407}, {295,412},
      {138,122}, {178,119},
      {209,150}, {239,155},
      {133,161}, {159,163}
    },
    { // frame 5
      {135, 85}, {206,237},
      {151,324}, {247,343},
      {170,403}, {296,412},
      {125,124}, {174,115},
      {205,156}, {241,163},
      {133,163}, {150,174}
    },
    { // frame 6
      {138, 86}, {212,231},
      {166,316}, {223,339},
      {170,402}, {281,402},
      {137,115}, {181,113},
      {176,155}, {211,169},
      {120,150}, {136,160}
    },
    { // frame 7
      {142, 75}, {220,214},
      {184,330}, {209,331},
      {180,394}, {275,377},
      {144,109}, {182,104},
      {163,148}, {181,161},
      {119,148}, {119,163}
    },
    { // frame 8
      {152, 77}, {226,216},
      {166,301}, {188,309},
      {258,348}, {207,396},
      {151,117}, {185,113},
      {125,150}, {128,162},
      { 60,140}, { 80,156}
    },
    { // frame 9
      {164, 75}, {227,214},
      {148,295}, {194,321},
      {246,319}, {222,404},
      {165,112}, {197,113},
      {129,137}, {131,157},
      { 62,137}, { 70,153}
    },
    { // frame 10
      {160, 82}, {221,216},
      {139,287}, {190,321},
      {234,301}, {224,402},
      {156,112}, {199,126},
      {121,137}, {119,155},
      { 59,141}, { 67,153}
    },
    { // frame 11
      {161, 77}, {234,225},
      {153,283}, {204,321},
      {225,311}, {224,402},
      {158,112}, {197,115},
      {124,141}, {137,146},
      { 65,146}, { 86,159}
    },
    { // frame 12
      {147, 75}, {235,214},
      {154,284}, {224,306},
      {232,295}, {217,342},
      {150,105}, {196,104},
      {157,139}, {169,153},
      { 90,146}, {112,160}
    },
    { // frame 13
      {138, 69}, {226,223},
      {153,284}, {222,302},
      {199,377}, {258,393},
      {152,105}, {190,100},
      {174,131}, {205,142},
      {123,153}, {127,169}
    },
    { // frame 14
      {146, 82}, {221,226},
      {163,309}, {244,332},
      {190,401}, {282,388},
      {149,120}, {184,104},
      {212,145}, {248,138},
      {164,171}, {185,180}
    },
    { // frame 15
      {143,100}, {215,224},
      {169,328}, {260,337},
      {200,406}, {298,393},
      {149,124}, {186,111},
      {220,143}, {255,145},
      {168,168}, {198,178}
    },
    { // frame 16
      {148, 99}, {220,241},
      {160,338}, {260,333},
      {194,406}, {296,394},
      {153,132}, {196,111},
      {214,152}, {263,152},
      {166,166}, {192,159}
    },
    { // frame 17
      {144, 95}, {219,230},
      {173,337}, {257,344},
      {194,405}, {287,384},
      {151,132}, {191,117},
      {204,148}, {245,148},
      {136,161}, {164,161}
    },
    { // frame 18
      {147, 95}, {233,223},
      {191,315}, {219,318},
      {195,410}, {278,365},
      {139,125}, {190,112},
      {190,165}, {210,156},
      {124,168}, {137,155}
    },
    { // frame 19
      {156, 81}, {231,213},
      {185,300}, {200,309},
      {209,400}, {254,337},
      {155,119}, {197,115},
      {188,163}, {212,156},
      {121,168}, {139,151}
    },
    { // frame 20
      {164, 88}, {222,213},
      {166,282}, {194,323},
      {214,310}, {240,393},
      {163,123}, {209,118},
      {129,145}, {141,155},
      { 62,142}, { 64,162}
    },
    { // frame 21
      {158, 89}, {227,219},
      {153,277}, {209,326},
      {234,292}, {251,400},
      {159,116}, {198,116},
      {124,137}, {126,149},
      { 63,145}, { 59,162}
    }
  };
    

	int currFrame = 0;
  glClearColor( 0.0, 0.0, 0.0, 0.0 ); // black background  
  while(1) {
    glClear( GL_COLOR_BUFFER_BIT );
    glColor3f(1.0, 1.0, 1.0);
    // spine
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][0]);
  			glVertex2iv(frame[currFrame][1]);
  	glEnd();
  	// shoulders
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][6]);
  			glVertex2iv(frame[currFrame][7]);
  	glEnd();
  	// upper arms
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][6]);
  			glVertex2iv(frame[currFrame][8]);
  	glEnd();
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][7]);
  			glVertex2iv(frame[currFrame][9]);
  	glEnd();
  	//lower arms
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][8]);
  			glVertex2iv(frame[currFrame][10]);
  	glEnd();
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][9]);
  			glVertex2iv(frame[currFrame][11]);
  	glEnd();
  	// upper legs
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][1]);
  			glVertex2iv(frame[currFrame][2]);
  	glEnd();
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][1]);
  			glVertex2iv(frame[currFrame][3]);
  	glEnd();
  	// lower legs
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][2]);
  			glVertex2iv(frame[currFrame][4]);
  	glEnd();
  	glBegin(GL_LINES);
  			glVertex2iv(frame[currFrame][3]);
  			glVertex2iv(frame[currFrame][5]);
  	glEnd();

  	glColor3d(0.0, 0.45, 0.0);
  	// knees
  	drawRectangle(frame[currFrame][2][0], frame[currFrame][2][1]);
  	drawRectangle(frame[currFrame][3][0], frame[currFrame][3][1]);
  	// shoulders
  	drawTriangle(frame[currFrame][6][0], frame[currFrame][6][1]);
  	drawTriangle(frame[currFrame][7][0], frame[currFrame][7][1]);
  	// elbows
  	drawRectangle(frame[currFrame][8][0], frame[currFrame][8][1]);
  	drawRectangle(frame[currFrame][9][0], frame[currFrame][9][1]);
    // eyes
    glColor3d(0.0, 1.0, 0.0);
    glPointSize(5.0f);
    glBegin(GL_POINTS);
  			glVertex2i(frame[currFrame][0][0]-10, frame[currFrame][0][1]-7);
  			glVertex2i(frame[currFrame][0][0]+10, frame[currFrame][0][1]-7);
  	glEnd();
  	//mouth
  	int x = frame[currFrame][0][0]; // x pos of head center
    int y = frame[currFrame][0][1]; // y pos of head center
  	glBegin(GL_POLYGON);
  			glVertex2i(x-10, y+10);
  			glVertex2i(x-10, y+15);
  			glVertex2i(x+10, y+15);
  			glVertex2i(x+13, y+7);
  			glVertex2i(x+7, y+12);
  	glEnd();
 
    glColor3d(0.1, 0.0, 0.75); 	
    // head
  	drawCircle(frame[currFrame][0][0], frame[currFrame][0][1], 30);
  	// waist
  	drawCircle(frame[currFrame][1][0], frame[currFrame][1][1], 15);
    // text
    char* string = "Running Man";
    glRasterPos2f(100,450);
    int len, i;
    len = (int) strlen(string);
    for (i = 0; i < len; i++) {
      glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, string[i]);
    }


    usleep(75000);
  	glutSwapBuffers();
  	currFrame = (currFrame+1) % 22;
	}
}

void keyboard(unsigned char key, int x, int y) {
    switch (key) {
        case 'q':
            exit(0);
            break;
        default:
            break;
    }
}

void init() {
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0);
}

int main(int argc, char* argv[]) {
	glutInit(&argc, argv);
  	glutInitDisplayMode( GLUT_RGB | GLUT_DOUBLE );	 // double buffer
	glutInitWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT);
  	glutCreateWindow( argv[0] );

	init();

  	glutDisplayFunc(display);
  	glutKeyboardFunc(keyboard);
  	glutMainLoop();

	return 0;
}
