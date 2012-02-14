/*  nosyncupdate.c
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char *argv[]) {

  int initial, count, final,
      child0, child1, status0, status1;

  /* Check for command line arguments */
  if (argc != 2) {
    printf("Usage: nosyncupdate count\n");
    return;
  }

  count = atoi(argv[1]);

  /* read the initial value in datafile */
  FILE *fp1 = fopen("datafile", "r");
  fscanf(fp1, "%d", &initial);
  fclose(fp1);
  
  child0 = fork();
  if (child0 == -1) {
    printf("child0: fork error; program terminated\n");
    return 0;
  }
  if ( child0 != 0 ) {             
    /* Parent code */
      child1 = fork();
      if (child1 == -1) {
        printf("child1: fork error; program terminated\n");
        return 0;
      }
      if ( child1 != 0 ) {             
        /* Parent code */
        waitpid(-1, NULL, 0);          // wait for the child to exit
      } else {
        /* child1 code */
        status1 = execlp("nosync1", "nosync1", argv[1], (char *) NULL);
        if (status1 != 0) {
          perror("child1: execvp error; program terminated\n");
          exit(-2);
        } 
      }
    waitpid(-1, NULL, 0);          // wait for the child to exit
  } else {
    /* child0 code */
    status0 = execlp("nosync0", "nosync0", argv[1], (char *) NULL);
    if (status0 != 0) {
      perror("child0: execvp error; program terminated\n");
      exit(-2);
    } 
  }
  
  /* read the final value in datafile */
  FILE *fp2 = fopen("datafile", "r");
  fscanf(fp2, "%d", &final);
  fclose(fp2);

  /* print the initial and final values */
  printf( "Initial Value: %d\n", initial);
  printf( "Final Value:   %d\n", final);
  return 0;
}
