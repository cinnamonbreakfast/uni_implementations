#include <stdlib.h>
#include <stdio.h>

#include "Domain.h"
#include "List.h"

#include <string.h>
#include <assert.h>

product* new_product(char* name, char* cat, int qn, int exp) {
	// Create a new product
	// Input: name, category, quantity, expiration in days
	// Output: product*

	product* p = malloc(sizeof(product)); //cast

	p->name = (char*)malloc((strlen(name)+1) * sizeof(char));
	strcpy(p->name, name);

	p->cat = (char*)malloc((strlen(cat) + 1) * sizeof(char));
	strcpy(p->cat, cat);

	p->q = qn;
	p->x = exp;
	return p;
}

void del_product(product* p) {
	// Deletes a product (memory free)
	// Input: product

	if (p != NULL) {
		free(p->name);
		free(p->cat);
	}
}

char* prod_name(product* p) {
	return p->name;
}



/* T E S T S */

void new_prod_test() {
	product* prod_test = new_product("Lapte", "lactate", 20, 15);

	char name_test[6] = "Lapte";
	char cat_test[8] = "lactate";

	assert(strcmp(prod_test->name, name_test)==0);
	assert(strcmp(prod_test->cat, cat_test)==0);
	assert(prod_test->q == 20);
	assert(prod_test->x == 15);

	// F A T A L I T Y
	free(prod_test->name);
	free(prod_test->cat);
	free(prod_test);
}