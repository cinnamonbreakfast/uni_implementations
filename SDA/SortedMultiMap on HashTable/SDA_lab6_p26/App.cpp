#include <iostream>
#include "SortedMultiMap.h"
#include "SMMIterator.h"
#include "ShortTest.h"
#include "ExtendedTest.h"
#include <exception>
#include <assert.h>

using namespace std;

bool re1(TKey cheie1, TKey cheie2) {
	if (cheie1 <= cheie2) {
		return true;
	}
	else {
		return false;
	}
}

bool condition(TKey x)
{
	return (x % 2 == 0);
}

int main() {

	testAll();
	cout << "Short end\n";
	testAllExtended();

	SortedMultiMap smm = SortedMultiMap(re1);
	for (int i = 1; i <= 15; i++) {
		smm.add(i, i + 1);
		smm.add(i, i + 2);
	}

	assert(smm.size() == 30);

	smm.filter(condition);

	assert(smm.size() == 14);



	//SortedMultiMap smm = SortedMultiMap(condition);
	//smm.add(0, 1);
	//smm.add(0, 1);
	//smm.add(0, 2);
	/*smm.add(1, 2);
	smm.add(1, 3);
	smm.add(2, 3);
	smm.add(2, 4);
	smm.add(3, 4);
	smm.add(3, 5);
	smm.add(4, 5);
	smm.add(4, 6);
	smm.add(5, 6);
	smm.add(5, 7);
	smm.add(6, 7);
	smm.add(6, 8);
	smm.add(7, 8);
	smm.add(7, 9);
	smm.add(8, 9);
	smm.add(8, 10);
	smm.add(9, 10);
	smm.add(9, 11);
	smm.add(10, 11);
	smm.add(10, 12);
*/
	/*int kMin = 0;
	int kMax = 10;
	for (int i = kMin; i <= kMax; i++) {
		cout << "da";
		smm.add(i, i + 1);
		smm.add(i, i + 2);
	}
	int intervalDim = 10;
	for (int i = kMin; i <= kMax; i++) {
		vector<TValue> v = smm.search(i);
		assert(v.size() == 2);
	}*/

	system("pause");
}
