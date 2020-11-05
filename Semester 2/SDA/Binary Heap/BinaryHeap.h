#pragma once
#include <iostream>

typedef int TElem;

class BinaryHeap
{
private:
	int *elems;
	int size;
	int cap;
	int sum;

public:
	BinaryHeap(const int);
	void add(int);
	int get_sum();
	void show();

	int parent(int i) { return (i - 1) / 2; }
	int left(int i) { return 2 * i + 1; }
	int right(int i) { return 2 * i + 2; }

	int getRoot() { return elems[0]; }

	void bubble_min_recursive(int);
	void bubble_down(int);

	~BinaryHeap();
};

