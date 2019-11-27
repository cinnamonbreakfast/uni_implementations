#include <stdio.h>

int asmRot(int x);

int main()
{
	int nr = 0, rt = 7;

	printf("Numar: ");
	citireNR(nr);

	printf("%x\n", nr);


	while (rt > 0) {
		nr = asmRot(nr);

		printf("%x", nr);
	}

	return 0;
}

void citireNR(int nr)
{
	scanf("%d", nr);
}