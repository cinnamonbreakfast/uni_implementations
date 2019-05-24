#include "ShortTest.h"
#include "ExtendedTest.h"
#include <iostream>

#include "SortedSet.h"
#include "SortedSetIterator.h"
#include <assert.h>

bool ascending(TComp e1, TComp e2) {
	if (e1 <= e2) {
		return true;
	}
	else {
		return false;
	}
}

void testUnion(Relation r) {
	SortedSet s(r);

	assert(s.size() == 0);
	assert(s.isEmpty() == true);

	s.add(1);
	s.add(5);
	s.add(3);
	s.add(7);
	s.add(-2);

	assert(s.size() == 5);


	SortedSet s2(r);

	s2.add(1);
	s2.add(4);
	s2.add(13);

	assert(s2.size() == 3);
	assert(s2.isEmpty() == false);

	s.sunion(s2);

	assert(s.size() == 7);

	// Harder tests

	SortedSet s3(r);

	for (int i = -200; i < 200; i++) {
		s3.add(i);
	}

	assert(s3.size() == 400);

	SortedSet s4(r);

	for (int i = -200; i < 200; i++) {
		s4.add(i);
	}

	assert(s4.size() == 400);

	for (int i = 100; i < 300; i++) {
		s4.add(i);
	}

	assert(s4.size() == 500);
}

int main() {
	//testAll();
	testAllExtended();

	testUnion(ascending);

	return 0;
}