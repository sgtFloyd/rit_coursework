/*  syncupdate.c
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include "sharevar.h"

int main(int argc, char *argv[]) {

  int initial, count, final,
      child0, child1, status0, status1,
      shmid;
  struct shmid_ds buf;

  /* Check for command line arguments */
  if (argc != 2) {
    printf("Usage: syncupdate count\n");
    return;
  }
  count = atoi(argv[1]);

  /* create shared memory */
  shmid = shmget(SHMKEY, sizeof(syncvars), PERMS | IPC_CREAT);
  
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
        status1 = execlp("sync1", "sync1", argv[1], (char *) NULL);
        if (status1 != 0) {
          perror("child1: execvp error; program terminated\n");
          exit(-2);
        } 
        
        
      }
    waitpid(-1, NULL, 0);          // wait for the child to exit
  } else {
    /* child0 code */
    status0 = execlp("sync0", "sync0", argv[1], (char *) NULL);
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
  
  /* remove shared memory */
  shmctl(shmid, IPC_RMID, &buf);
  
  return 0;
}
