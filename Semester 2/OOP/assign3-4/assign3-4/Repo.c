//#include <stdio.h>
//#include <stdlib.h>
//
//#include "Repo.h"
//
//
//
//Repo* repo_init() //void(*destroy)(ListElem)) //, int(*equals)(ListElem, ListElem))
//{
//	Repo* R = (Repo*)malloc(sizeof(Repo));
//	R->destroy = destroy;
//	R->equals = equals;
//	R->list = list_init(2);
//	return R;
//}
//
//
//void repo_add(Repo* R, ListElem p) {
//	ListElem* pr = list_search(R->list, p, R->equals, R->copy);
//	if (pr == NULL) {
//		list_append(R->list, p);
//	}
//	else {
//		*pr = p;
//	}
//}
//
//int repo_size(Repo* R) {
//	return list_size(R->list);
//}
//
//void repo_del(Repo* R) {
//	list_del(R->list, R->destroy);
//	free(R);
//}
//
