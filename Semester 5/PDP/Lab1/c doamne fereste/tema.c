#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

#include "utils.c"
#include "models.c"

#define NUM_THREADS 5
#define ACCOUNT_NUMBS 10

float AMOUNT;
pthread_mutex_t lock;

struct Account accounts[ACCOUNT_NUMBS];

int getRandomExcluded(int i) {
	int rnd = rand() % ACCOUNT_NUMBS;
	while(rnd == i) {
		rnd = rand() % ACCOUNT_NUMBS;
	}

	return rnd;
}

void *worker_action(void *threadid)
{
	pthread_mutex_lock(&lock);

	long tid;
	tid = (long)threadid;

	int acc1 = rand() % ACCOUNT_NUMBS;
	int acc2 = getRandomExcluded(acc1);



	printf("The random is %d", (rand() % 5));

	pthread_mutex_unlock(&lock);
	return NULL;
}

int main()
{
	buildAccounts(accounts);

	for(int i = 0; i < ACCOUNT_NUMBS; i++) {
		printf("Name %s\n", accounts[i].owner);
	}

	return 0;
	// pthread_t threads[NUM_THREADS];
	// int rc;
	// long t;
	// for(t = 0; t < NUM_THREADS; t++) {
	// 	printf("In main: creating thread %ld\n", t);
	// 	rc = pthread_create(&threads[t], NULL, &worker_action, (void*)t);

	// 	if(rc) {
	// 		printf("Error, return code %d\n", rc);
	// 		exit(-1);
	// 	}
	// }

	// printf("Amount %f\n", AMOUNT);

	// pthread_exit(NULL);

	//printf("Amount %f\n", AMOUNT);
}
