#pragma once

#include "List.h"

typedef struct {
	list* L;
	void *destroy;
	int(*equals)(ListElem, ListElem);
} Repo;

Repo* repo_init();//void(*destroy)(ListElem));//, int(*equals)(ListElem, ListElem));

void repo_add(Repo*, ListElem);
int repo_size(Repo*);
void repo_del(Repo*);