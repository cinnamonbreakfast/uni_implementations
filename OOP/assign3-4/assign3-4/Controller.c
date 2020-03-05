#include "Controller.h"
#include "List.h"
#include "Domain.h"

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>

void cnt_add_product(list* L, char* name, char* cat, int q, int exp) {
	// Add a product to repo
	// Input: list*, name, cat, quantity, expiration date

	product* p = new_product(name, cat, q, exp);

	list_add(L, *p);

	free(p);
}

int cnt_remove(list* L, char* name) {
	// Remove an element by name
	// Input: list*, char* name
	// Output: TRUE/FALSE
	for (int i = 0; i < L->size; ++i) {
		if (strcmp(L->p[i].name, name) == 0) {
			return list_remove(L, i);
		}
	}

	return 0;
}

void cnt_update(list* L, char* name, char* edit, int val, int op) {
	// Update a product by name
	// Input: list*, char*, char*, int value, int option

	for (int i = 0; i < L->size; ++i) {
		if (strcmp(L->p[i].name, name) == 0) {
			if (op == 1) {
				strcpy(L->p[i].cat, edit);
			} 
			else if (op == 2) {
				L->p[i].q = val;
			}
			else if (op == 3) {
				L->p[i].x = val;
			}
		}
	}
}

int cnt_find(list* L, char* name) {
	for (int i = 0; i < L->size; ++i) {
		if (strcmp(L->p[i].name, name) == 0) {
			return i;
		}
	}

	return -1;
}

void cnt_print(list* L, char* name) {
	// De pus in UI
	// Print products by substring, ascending
	// Input: list*, char*

	int i = 0, j = 0;
	product p;

	if (strlen(name) <= 1) {
		for (i = 0; i < L->size - 1; ++i) {
			for (j = i; j < L->size; ++j)
				if (L->p[i].q > L->p[j].q) {
					p = (L->p[i]);
					L->p[i] = L->p[j];
					L->p[j] = p;
				}
		}

		for (i = 0; i < L->size; ++i) {
			printf("%s %s %d(G/L) Exp: %d\n", L->p[i].name, L->p[i].cat, L->p[i].q, L->p[i].x);
		}
	}
	else {
		for (i = 0; i < L->size-1; ++i) {
			if (strstr(L->p[i].name, name)) {
				for (j = i; j < L->size; ++j)
					if (strstr(L->p[j].name, name))
						if (L->p[i].q > L->p[j].q) {
							p = (L->p[i]);
							L->p[i] = L->p[j];
							L->p[j] = p;
						}
				
				}
			}


		for (i = 0; i < L->size; ++i) {
			if (strstr(L->p[i].name, name))
				printf("%s %s %d(G/L) Exp: %d\n", L->p[i].name, L->p[i].cat, L->p[i].q, L->p[i].x);
		}
	}
	
}

void lab_activ(list* L) {
	// De pus in UI
	// LAB WORK
	int i, j;
	product p;

	for (i = 0; i < L->size - 1; ++i) {
		for (j = i; j < L->size; ++j)
			if (strcmp(L->p[i].name, L->p[j].name) < 0) {
				p = (L->p[i]);
				L->p[i] = L->p[j];
				L->p[j] = p;
			}
	}


	for (i = 0; i < L->size; ++i) {
		printf("%s %s %d(G/L) Exp: %d\n", L->p[i].name, L->p[i].cat, L->p[i].q, L->p[i].x);
	}
}

void controller_test() {
	/*product* p = NULL;
	list* L = list_init(15);

	cnt_add_product(L, "N", "C", 1, 2);

	assert(strcmp(L->p[0].name, "N") == 0);

	assert(cnt_find(L, "N") == 0);
	assert(cnt_remove(L, "N") == 1);

	cnt_print(L, "");
	
	list_del(L);*/

	int i = 3;
}