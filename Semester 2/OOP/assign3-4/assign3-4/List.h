#pragma once

#include "Domain.h"

//typedef product ListElem;

typedef struct {
	product* p;
	int size, cap;
} list;

list* list_init(int);
void list_append(list*, product);
void list_del(list*);
void list_resize(list*);
int list_remove(list*, int);
int list_size(list*);