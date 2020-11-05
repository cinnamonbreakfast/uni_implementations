#ifndef MODELS_H  // header guard
#define MODELS_H

#include <stdio.h>
#include <stdlib.h>

struct LOG {
	long target_acc;
	long source_acc;
	float value;
	char description[50];
	struct LOG *next;
};

struct Account {
	long account_id;
	char owner[80];
	float amount;
	struct LOG *logs;
};