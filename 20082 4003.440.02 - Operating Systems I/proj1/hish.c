/* hish.c
 *
 * This program sets up a simple shell to read in commands from a user, and
 * forks() a new process to the OS to take care of the command. A history
 * command was added to keep track of all the past commands entered. 
 *  
 */

 
/* Gabriel Smith
 * Date Completed: 01-01-09
 *
 * Description of what you did:
 *
 *	- forked a child process using fork()
 *  - the child process invokes execvp(args[0], args)..
 *  - the parent waits for the child to complete, but does not
 *     exit - this will allow setup() to be invoked again.
 *
 */

#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <errno.h>

#define MAX_LINE 80 /* 80 chars per line, per command, should be enough. */
#define MAX_CMDS 999 /* 999 commands remembered in the history */

char history[MAX_CMDS][MAX_LINE];
int count = 0;

/**
 * setup() reads in the next command line, separating it into distinct tokens
 * using whitespace as delimiters. setup() sets the args parameter as a 
 * null-terminated string.
 */

void setup(char inputBuffer[], char *args[])
{
    int length, /* # of characters in the command line */
        i,      /* loop index for accessing inputBuffer array */
        start,  /* index where beginning of next command parameter is */
        ct;     /* index of where to place the next parameter into args[] */

    
    ct = 0;
    
    /* Read what the user enters on the command line */
    length = read(STDIN_FILENO, inputBuffer, MAX_LINE);  

    start = -1;

    if (length == 0)
        exit(0);         /* CTRL-D was entered, end of user command stream */


    /* If the process is in the read() system call and is interrupted, then
       read() will return -1, even though it is not a problem. In this case
       errno will be set to EINTR.  We can check this value to ignore the 
       error condition. */
 
    if ( (length < 0) && (errno != EINTR) ) {
        perror("error reading the command");
	exit(-1);           /* terminate with error code of -1 */
    }

    /* Examine every character in the inputBuffer */
    for (i=0; i<length; i++) { 
        switch (inputBuffer[i]){
	    case ' ':
	    case '\t' :               /* argument separators */
		if(start != -1){
                    args[ct] = &inputBuffer[start];    /* set up pointer */
		    ct++;
		}
                inputBuffer[i] = '\0'; /* add a null char; make a C string */
		start = -1;
		break;

            case '\n':                 /* should be the final char examined */
		if (start != -1){
                    args[ct] = &inputBuffer[start];     
		    ct++;
		}
                inputBuffer[i] = '\0';
                start = -1;
                args[ct] = NULL; /* no more arguments to this command */
		break;

	    default :             /* some other character */
		if (start == -1)
		    start = i;
	} 
     }  
       
    args[ct] = NULL; /* just in case the input line was > 80 */
    
    if (args[0] != NULL) {
      int c = 0;
      for (c=0; c<(MAX_LINE/2); c++) {
        if (args[c] != NULL) {
          if (c!=0)
            strcat(history[count], " ");
          strcat(history[count], args[c]); 
        } else {
          c = MAX_LINE;
        }
      }
      
      count++;
    }


} /* End of setup() routine */


/* the signal handler function */
void handle_SIGINT() {
  printf("\n\n");
  int i;
  if (count<11) {
    for (i=0; i<count; i++) {
      printf( "command %d is %s\n", (i+1), history[i]);
    }
  } else {
    for (i=count-10; i<count; i++) {
      printf( "command %d is %s\n", (i+1), history[i]);
    }
  }
  printf("\n");
}


int main(void) {

    char inputBuffer[MAX_LINE]; /* buffer to hold the command entered */
    char *args[MAX_LINE/2];     /* max of 40 arguments */
    int child,                  /* process id of the child process */
        status;                 /* result from execvp() call */
    
    /* set up the signal handler */
    struct sigaction handler;
    handler.sa_handler = handle_SIGINT; 
    handler.sa_flags = 0;
    sigemptyset(&handler.sa_mask);
    sigaction(SIGINT, &handler, NULL);
    
    while (1) {            /* Program terminates normally inside setup */

        fputs(">> ", stdout);
        setup(inputBuffer,args);       /* get next command */   
        child = fork();
        if (child == -1) {
            printf("fork error; program terminated\n");
            return 0;
        }
        
        if ( child != 0 ) {             
            /* Parent code */
            waitpid(-1, NULL, 0);          // wait for the child to exit
        } else {
            /* Child code */            
            status = execvp(args[0], args); // execute command
            if (status != 0) {
              perror("execvp error; program terminated\n");
              exit(-2);
            } 
        }
    }
}
