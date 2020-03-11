#pragma once

#include "List.h"

typedef struct {
	char* name;
	char* cat;
	int q;
	int x;
} product;

product* new_product(char*, char*, int, int);
void del_product(product*);
char* prod_name(product*);
void new_prod_test();