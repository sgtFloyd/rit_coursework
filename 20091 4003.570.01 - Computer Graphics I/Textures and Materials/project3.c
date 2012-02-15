/************************************************
/
/	Author:	Gabriel Smith
/			ges7506@rit.edu
/
/	Class:	DCN 20091
/
/	Assignment: Programming Project #3
/
/***********************************************/

#if defined(__APPLE__) && defined(__MACH__)  // for mac
#   include <GLUT/glut.h>
#else // for others
#	include <GL/glut.h>
#endif
#include <math.h>
#include <stdlib.h>

// constants
#define WINDOW_HEIGHT 500
#define WINDOW_WIDTH 500
#define WINDOW_X_POS 100
#define WINDOW_Y_POS 100

/* state variables */
int animationRunning = 0;       // 1 if animation is running
int light_0 = 0;                // 1 if light0 is on
int light_1 = 0;                // 1 if light1 is on
int disco = 0;					// 1 is disco lights are activated

/* camera variables */
int aC = 0;                     // "activeCamera" (current camera in use)
int cm[][9] = {//camera 0       // gluLookAt values for multiple cameras
				0.0, 2.0, -3.0,    // camera eye
				0.0, 2.0, 1.0,     // camera lookat
				0.0, 1.0, 0.0,     // camera up
			   // camera 1
				4.0, 2.0, 1.0,
				0.0, 2.0, 3.0,
				0.0, 1.0, 0.0 };

/* animation variables */
double rotation = 0;	// angle of rotation for cube (also used for torus)
double bounce = -1;		// height to set bouncing sphere
double scale = 1;		// scale to resize torus to
double sinCount = 0;	// angle to calc sin of (for ball height)
double sinCount2 = 0;	// angle to calc sin of (for torus size)
double light1 = 2;		// setting for current color disco light
int discoCount=0;		// 'timer' to delay disco light changing

/*
 * set current material using value passed in
 */
void setMaterial(int x) {
	GLfloat m1[3] = {0,0,0};
	GLfloat m2[3] = {0,0,0};
	GLfloat m3[3] = {0,0,0};
	double shine;
	if (x==0) {
		// grey
		m1[0]=0.2; m1[1]=0.2; m1[2]=0.2;
		m2[0]=0.8; m2[1]=0.8; m2[2]=0.8;
		m3[0]=0.0; m3[1]=0.0; m3[2]=0.0;
		shine = 0.5;
	} else if (x==1) {
		// dark red
		m1[0]=0.17; m1[1]=0.01; m1[2]=0.01;
		m2[0]=0.61; m2[1]=0.04; m2[2]=0.04;
		m3[0]=0.73; m3[1]=0.63; m3[2]=0.63;
		shine = 0.9;
	} else if (x==2) {
		// teal
		m1[0]=0.17; m1[1]=0.01; m1[2]=0.01;
		m2[0]=0.11; m2[1]=0.54; m2[2]=0.54;
		m3[0]=0.63; m3[1]=0.73; m3[2]=0.53;
		shine = 0.9;
	} else if (x==3) {
		// pink
		m1[0]=0.80; m1[1]=0.00; m1[2]=0.80;
		m2[0]=0.80; m2[1]=0.00; m2[2]=0.80;
		m3[0]=0.80; m3[1]=0.00; m3[2]=0.80;
		shine = 0.9;
	}
	glMaterialfv(GL_FRONT, GL_AMBIENT, m1);
	glMaterialfv(GL_FRONT, GL_DIFFUSE, m2);
	glMaterialfv(GL_FRONT, GL_SPECULAR, m3);
	glMaterialf(GL_FRONT, GL_SHININESS, shine * 128.0);
}

/*
 * set colored disco light using value stored in light1
 */
void setLight() {
	glDisable(GL_LIGHT2);
	glDisable(GL_LIGHT3);
	glDisable(GL_LIGHT4);
	glDisable(GL_LIGHT5);
	glDisable(GL_LIGHT6);
	glDisable(GL_LIGHT7);
	if (disco) {
		if (light1==2)
			glEnable(GL_LIGHT2);
		else if (light1==3)
			glEnable(GL_LIGHT3);
		else if (light1==4)
			glEnable(GL_LIGHT4);
		else if (light1==5)
			glEnable(GL_LIGHT5);
		else if (light1==6)
			glEnable(GL_LIGHT6);
		else if (light1==7)
			glEnable(GL_LIGHT7);
	}
}

/*
 * Display callback function
 */
void display() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glLoadIdentity ();

	// set camera position based on active camera stored in aC
	gluLookAt(	cm[aC][0], cm[aC][1], cm[aC][2],
				cm[aC][3], cm[aC][4], cm[aC][5],
				cm[aC][6], cm[aC][7], cm[aC][8] );

	// update disco lights if animation is going
	if (animationRunning)
		setLight();

	// draw floor
	setMaterial(0);
	int right= -7;
	int front= 4;
	int left= 7;
	int back= 14;
	int floor= -2;
	glBegin( GL_QUADS );
		glNormal3f(0,1,0);
		glVertex3f( right, floor, front );
		glVertex3f( left, floor, front );
		glVertex3f( left, floor, back );
		glVertex3f( right, floor, back );
	glEnd( );
	
	// draw rotating cube
	glPushMatrix();
		glTranslatef(0,0,5);
		glRotatef(rotation, 0.0, 1.0, 0.0);
		setMaterial(1);
		glutSolidCube(2.0);
	glPopMatrix();

	// draw teapot
	glPushMatrix();
		setMaterial(3);
		glTranslatef(0,1.7,5);
		glRotatef(rotation, 0.0, 1.0, 0.0);
		glutSolidTeapot(1);
	glPopMatrix();

	// draw bouncing sphere
	glPushMatrix();
		glTranslatef(-3,bounce,5);
		setMaterial(2);
		glutSolidSphere(0.5, 16, 16);
	glPopMatrix();
	
	// draw checkerboard morphing torus
	glEnable(GL_TEXTURE_2D);
	glPushMatrix();
		glTranslatef(3, 1, 5);
		glScalef(scale,scale,scale);
		glRotatef((rotation/0.25), 0.5, 0.5, 0.5);
		setMaterial(0);
		glutSolidTorus(0.375, 0.85, 6, 15);
	glPopMatrix();
	glDisable(GL_TEXTURE_2D);

	glutSwapBuffers( );
}

/*
 * Reshape callback function
 */
void reshape(int w, int h) {
	glViewport(0, 0, (GLsizei) w, (GLsizei) h); 
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(90, w/h, 1, 20);
	glMatrixMode(GL_MODELVIEW);
}

/*
 * Idle callback function, updates all animation variables
 */
void animate() {
	if (animationRunning) {
		// animation for rotating cube
		rotation += 1;
		if (rotation > 360)
			rotation -= 360;
		// animation for bouncing ball
		sinCount += 0.15;
		if (sinCount > 180)
			sinCount -= 180;
		bounce = 2.5*sin(sinCount) + 1.5;
		// animation for morphing torus
		sinCount2 += 0.20;
		if (sinCount2 > 180)
			sinCount2 -= 180;
		scale = (sin(sinCount2)+2)/2;
		// animation for light1 'disco' colors
		if (disco) {
			if (discoCount>4) {
				int x = light1;
				while (x==light1)
					x = (random()%6)+2; //randnum from [2,7]
				light1 = x;
				discoCount-=4;
			}
			discoCount++;
		}
		glutPostRedisplay();
	}
}

/*
 * Turn animation on or off, called when 'a' key pressed
 */
void toggleAnimation() {
	animationRunning = !animationRunning;
}

/*
 * Turn disco lights on or off, called when 'd' key pressed
 */
void toggleDisco() {
	disco = !disco;
}

/*
 * Set active camera (aC) when '1' or '2' key pressed
 */
void switchCamera(int newCamera) {
	if (aC != newCamera) {
		aC = newCamera;
		glutPostRedisplay();
	}
}

/*
 * Turn on or off GL_LIGHT0 or GL_LIGHT1 when '5' or '6' key pressed
 */
void toggleLight(int light){
	if (light==0) {
		if (light_0)
			glDisable(GL_LIGHT0);
		else
			glEnable(GL_LIGHT0);
		light_0 = !light_0;
	} else if (light==1) {
		if (light_1)
			glDisable(GL_LIGHT1);
		else
			glEnable(GL_LIGHT1);
		light_1 = !light_1;
	}
	glutPostRedisplay();
}


/*
 * Prepare textures and lighting for use in display callback function
 */
void init(void) {
	glClearColor (0.0, 0.0, 0.0, 0.0); // background color

	/* prepare lighting */
	glEnable( GL_DEPTH_TEST );
	glEnable( GL_LIGHTING );
	//GL_LIGHT0
	GLfloat position[] = { 4, 2, 1.0, 0.0 };
	glLightfv( GL_LIGHT0, GL_POSITION, position );

	//GL_LIGHT1
	position[0] = 0.5; position[1] = 2.0;
	position[2] = 10.0; position[3] = 0.0;
	glLightfv(GL_LIGHT1, GL_POSITION, position);
	GLfloat direction[] = {0, 5, 10};
	glLightfv(GL_LIGHT1, GL_SPOT_DIRECTION, direction);
	GLfloat color[] = { 1, 1, 1, 1 };
	glLightfv(GL_LIGHT1, GL_DIFFUSE, color);
	glLightfv(GL_LIGHT1, GL_SPECULAR, color);

	//remaining lights used for 'disco' effect
	glLightfv(GL_LIGHT2, GL_POSITION, position);
	glLightfv(GL_LIGHT2, GL_SPOT_DIRECTION, direction);
	color[0]=1;	color[1]=1;	color[2]=0;
	glLightfv(GL_LIGHT2, GL_DIFFUSE, color);
	glLightfv(GL_LIGHT2, GL_SPECULAR, color);

	glLightfv(GL_LIGHT3, GL_POSITION, position);
	glLightfv(GL_LIGHT3, GL_SPOT_DIRECTION, direction);
	color[0]=1;	color[1]=0;	color[2]=1;
	glLightfv(GL_LIGHT3, GL_DIFFUSE, color);
	glLightfv(GL_LIGHT3, GL_SPECULAR, color);

	glLightfv(GL_LIGHT4, GL_POSITION, position);
	glLightfv(GL_LIGHT4, GL_SPOT_DIRECTION, direction);
	color[0]=0;	color[1]=1;	color[2]=1;
	glLightfv(GL_LIGHT4, GL_DIFFUSE, color);
	glLightfv(GL_LIGHT4, GL_SPECULAR, color);

	glLightfv(GL_LIGHT5, GL_POSITION, position);
	glLightfv(GL_LIGHT5, GL_SPOT_DIRECTION, direction);
	color[0]=1;	color[1]=0;	color[2]=0;
	glLightfv(GL_LIGHT5, GL_DIFFUSE, color);
	glLightfv(GL_LIGHT5, GL_SPECULAR, color);

	glLightfv(GL_LIGHT6, GL_POSITION, position);
	glLightfv(GL_LIGHT6, GL_SPOT_DIRECTION, direction);
	color[0]=0;	color[1]=1;	color[2]=0;
	glLightfv(GL_LIGHT6, GL_DIFFUSE, color);
	glLightfv(GL_LIGHT6, GL_SPECULAR, color);

	glLightfv(GL_LIGHT7, GL_POSITION, position);
	glLightfv(GL_LIGHT7, GL_SPOT_DIRECTION, direction);
	color[0]=0;	color[1]=0;	color[2]=1;
	glLightfv(GL_LIGHT7, GL_DIFFUSE, color);
	glLightfv(GL_LIGHT7, GL_SPECULAR, color);

	/* prepare checkerboard texture */
	GLfloat planes[ ] = { -1.0, 0.0, 1.0, 0.0 };
	GLfloat planet[ ] = { 0.0, -1.0,  0.0, 1.0 };
	GLubyte image[ 64 ][ 64 ][ 3 ];
	int i, j, c;
	for ( i = 0; i < 64; i++ ) {
		for ( j = 0 ; j < 64; j++ ) {
			c = ( ( ( ( i & 0x8 ) == 0 ) ^ ( ( j & 0x8 ) ) == 0 ) ) * 255;
 			image[ i ][ j ][ 0 ] = ( GLubyte ) c;
			image[ i ][ j ][ 1 ] = ( GLubyte ) c;
			image[ i ][ j ][ 2 ] = ( GLubyte ) c;
		}
	}
	glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
	glTexGenfv(GL_S, GL_OBJECT_PLANE, planes);
	glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT );
	glEnable(GL_TEXTURE_GEN_S);
	glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
	glTexGenfv(GL_T, GL_OBJECT_PLANE, planet);
	glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT );
	glEnable(GL_TEXTURE_GEN_T);
	glTexImage2D( GL_TEXTURE_2D, 0, 3, 64, 64, 0, GL_RGB, 
					GL_UNSIGNED_BYTE, image );
	glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
	glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST ); 
}

/*
 * Keyboard callback function
 *	Keys and their effects:
 *		a/A:	toggle animation
 *		1:	switch to camera position 1
 *		2:	switch to camera position 2
 *		5:	toggle light source 1
 *		6:	toggle light source 2
 *		7/d/D:	toggle disco lights
 *		q/Q:	quit program
 */
void keyboard(unsigned char key, int x, int y) {
    switch (key) {
		case 'A':
        case 'a':
			toggleAnimation();
            break;
		case 'D':
        case 'd':
			toggleDisco();
            break;
        case '1':
			switchCamera(0);
            break;
        case '2':
			switchCamera(1);
            break;
        case '5':
			toggleLight(0);
            break;
        case '6':
			toggleLight(1);
            break;
		case 'Q':
        case 'q':
            exit(0);
            break;
        default:
            break;
    }
}

/*
 * Main program initializes window, callback functions and begins display loop
 */
int main(int argc, char* argv[]) {

	glutInit(&argc, argv);
  	glutInitDisplayMode( GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH );
	glutInitWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	glutInitWindowPosition (WINDOW_X_POS, WINDOW_Y_POS);
  	glutCreateWindow(argv[0]);

	glutIdleFunc(animate);
  	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
  	glutKeyboardFunc(keyboard);

	init();

  	glutMainLoop();

	return 0;
}
