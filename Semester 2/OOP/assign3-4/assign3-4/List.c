#include "List.h"
#include "Domain.h"

#include <stdlib.h>
#include <stdio.h>

list* list_init(int capacity)
{
	// Init a list and return it
	// Input: int<capacity>
	// Output: *list
	list* L = (list*)malloc(sizeof(list));
	
	L->p = (product*)malloc(capacity *sizeof(product));
	L->size = 0;
	L->cap = capacity;

	return L;
}

void list_resize(list* L) {
	// Resize the dynamic list
	// Input: list*

	product* new_list = (product*)malloc(2 * L->cap * sizeof(product));

	L->cap *= 2;

	for (int i = 0; i < L->size; ++i) {
		new_list[i] = L->p[i];
	}

	free(L->p);
	L->p = new_list;
}

void list_empty(list* L) {
	// Destroy each element
	// Input: *list

	for (int i = 0; i < L->size; ++i) {
		del_product(&L->p[i]);
	}

}

void list_del(list* L) {
	// Delete the list
	// Input: list*

	if (L != NULL) {
		list_empty(L);
		free(L->p);
		free(L);
	}
}

int list_remove(list* L, int index) {
	// Remove a product on a certain index
	// Input: list*, index
	// Output: 0 - invalid index, 1 - valid


	if (index < L->size) {
		// Free memory
		int i;

		//if (L->p[ != NULL)
		del_product(&(L->p[index]));

		for (i = index; i < L->size - 1; ++i) {
			L->p[i] = L->p[i + 1];
		}

		L->size--;

		printf("%d", L->size);

		return 1;
	}
	else {
		return 0;
	}

}

int list_remove_obj(list* L, int o) {
	// Remove a product on a certain index
	// Input: list*, index
	// Output: 0 - invalid index, 1 - valid
	return 0;

}

void list_append(list* L, product pr) {
	// Append a product
	// Input: list*, product

	if (L->size >= L->cap) {
		list_resize(L);
	}

	L->p[L->size++] = pr;
}

int list_size(list* L) {
	return L->size;
}

//ListElem* list_search(list* L, ListElem p, int(*equals)(ListElem, ListElem), ListElem(*copy)(ListElem)) {
//	int i = 0;
//	for (i; i < L->size; i++) {
//		if (equals(p, L->p[i]) == 0) {
//			return &(L->p[i]);
//		}
//	}
//	return NULL;
//}