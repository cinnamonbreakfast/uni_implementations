#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "models.c"

void buildAccounts(struct Account accounts[]) {
    for(int i = 0; i < 10; i++) {
        strcpy(accounts[i].owner, "Johnuletz");
    }
}