#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include "Domain.h"
#include "List.h"
//#include "UI.h"
//#include "Controller.h"

#include <crtdbg.h> // mem leaks

/*
Layers:
	- domain
	- controller
	- UI
	- Repository

Product:
	name
	cat
	quan
	exp

	- identified by name+cat

a.
	add			[ add to existing -> update ]
	remove
	update

b.
	display:
		- substring
		- empty string: all
	__________________________ sorted by quantity

c.
	filter display
		- category			[ empty -> all ]
		- expiration day (in less than X days)

d. Undo/Redp

BONUS:
b. different type of sorting
c. Undo/Redo:
	- list of operations
	- list of lists
*/



int main() {

	// Init repo
	list* Ls = list_init(1);

	//Repo* R = repo_init();//del_product);

	// Add some models
	/*cnt_add_product(L, "Lapte", "Lactate", 2, 25);
	cnt_add_product(L, "Lapte de capra", "Lactate", 1, 30);
	cnt_add_product(L, "Lapte acru", "Lactate", 0, 14);
	cnt_add_product(L, "Oua", "Lactate", 10, 20);
	cnt_add_product(L, "Cotlet", "Carne", 150, 10);
	cnt_add_product(L, "Rosii", "Legume", 500, 20);
	cnt_add_product(L, "Castravete", "Legume", 200, 20);
	cnt_add_product(L, "Pepene", "Fructe", 500, 18);
	cnt_add_product(L, "Tort", "Desert", 200, 20);
	cnt_add_product(L, "Placinta de mere", "Desert", 124, 12);
	cnt_add_product(L, "Mere", "Categ", 124, 12);

	cnt_print(L, "");*/

	// Open menu
	//main_menu(L);

	// Destroy repo
	//repo_del(L);

	// Testers
	//controller_test();
	//new_prod_test();

	// Check for leakings
	_CrtDumpMemoryLeaks();

	return 0;
}