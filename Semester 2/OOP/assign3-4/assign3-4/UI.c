#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "List.h"
#include "Controller.h"

// convent: 1 br - simple print, 2 br - notice

#define IN_LEN 32

void main_menu_print() {
	printf("1 : Add a product\n");
	printf("2 : Remove a product\n");
	printf("3 : Update a product\n");
	printf("4 : Print everything\n\n");
	printf("5 : Print by substring\n");
	printf("\n0 : Exit\n");
}

void UI_add_product(list* L) {
	char* name = (char*)malloc(IN_LEN *sizeof(char));
	char* cat = (char*)malloc(IN_LEN *sizeof(char));
	int quan = 0, exp = 0;

	printf("\nProduct name: ");
	_fgetchar();
	fgets(name, IN_LEN, stdin);
	name[strlen(name) - 1] = '\0';

	printf("Product category: ");
	scanf("%s", cat);

	printf("Quantity: ");
	scanf("%d", &quan);

	printf("Expiration (in days): ");
	scanf("%d", &exp);

	if (cnt_find(L, name) >= 0) {
		cnt_update(L, name, cat, quan, exp);
		printf("\nQuantity for %s was updated.\n", name);
	}
	else {
		cnt_add_product(L, name, cat, quan, exp);
		printf("\n%s added.\n", name);
	}
	
	
	free(name);
	free(cat);
}

void UI_remove_product(list* L) {
	char* name = (char*)malloc(IN_LEN * sizeof(char));
	int result = 0;

	printf("\nProduct name: ");
	scanf("%s", name);

	result = cnt_remove(L, name);

	if (result == 1) {
		printf("\n%s was deteled.\n", name);
	}
	else {
		printf("\nCannot find %s in the fridge.\n", name);
	}

	free(name);
}

void UI_print_update() {
	printf("\n1 : Update category");
	printf("\n2 : Update quantity");
	printf("\n3 : Update expiration date");
	printf("\n\n0 : Back");
}

void UI_update_product(list* L) {
	char* name = (char*)malloc(IN_LEN * sizeof(char));
	char* edit = (char*)malloc(IN_LEN * sizeof(char));
	int quan = 0, exp = 0, op = -1;

	printf("\n\nProduct name: ");
	_fgetchar();
	fgets(name, IN_LEN, stdin);
	name[strlen(name) - 1] = '\0';

	while (1) {
		UI_print_update();
		printf("\n>> ");
		scanf("%d", &op);

		if (op == 1) {
			printf("\nNew category name: ");
			scanf("%s", edit);

			cnt_update(L, name, edit, 0, op);

			break;
		}
		else if (op == 2) {
			printf("\nNew quantity: ");
			scanf("%d", &quan);

			cnt_update(L, name, "", quan, op);

			break;
		}
		else if (op == 3) {
			printf("\nNew exp days: ");
			scanf("%d", &quan);

			cnt_update(L, name, "", quan, op);

			break;
		}
		else if (op == 0) {
			break;
		}
		else {
			printf("\nInvalid option\n");
		}

		op = -1;
	}


	free(name);
	free(edit);
}

void UI_substring(list* L) {
	char* name = (char*)malloc(IN_LEN * sizeof(char));
	int i = 0;

	printf("\nProduct name: ");
	
	//scanf("%s", name);

	_fgetchar();
	fgets(name, IN_LEN, stdin);
	name[strlen(name) - 1] = '\0';

	cnt_print(L, name);

	free(name);
}

void main_menu(list* L) {
	int cmd = 0;

	while (1) {
		main_menu_print();

		printf("> ");
		scanf("%d", &cmd);

		if (cmd == 1) {
			UI_add_product(L);
		}
		else if (cmd == 2) {
			UI_remove_product(L);
		}
		else if (cmd == 3) {
			UI_update_product(L);
		}
		else if (cmd == 4) {
			cnt_print(L, "");
		}
		else if (cmd == 5) {
			UI_substring(L);
		}
		else if (cmd == 6) {
			lab_activ(L);
		}
		else if (cmd == 0) {
			break;
		}

		cmd = -1;
	}
}