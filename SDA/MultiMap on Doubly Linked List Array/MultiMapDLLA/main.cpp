#include "MultiMapIterator.h"
#include "MultiMap.h"
#include "ShortTest.h"
#include "ExtendedTest.h"
#include <assert.h>
#include <iostream>

using namespace std;

/*
function filter(cond)
	i <- MM.head

	while i =/= -1, do
		tmp <- MM.next[i]

		if(cond(MM, MM[i]) == true), then
			remove(MM, MM[i].first, MM[i].second)
			i <- tmp
		else
			i <- MM.next[i]
		end_if
	end_while
end_function
*/

bool condition(TKey k)
{
	// TKey must be even number
	return k % 2 == 0;
}

void extraTest()
{
	cout << "Extra Test begin\n";

	MultiMap m;
	m.add(1, 100);
	m.add(2, 200);
	m.add(3, 300);
	m.add(1, 500);
	m.add(2, 600);
	m.add(4, 800);

	assert(m.size() == 6);

	// Keep only those whose TKey val is even number
	m.filter(condition);

	assert(m.size() == 3);

	cout << "Extra Test ended OK\n";
}


int main() {
	extraTest();
	testAll();
	testAllExtended();

	system("pause");
	return 0;
}