#include "BinaryHeap.h"
#include <algorithm>
#include <iostream>

using namespace std;

void swap(int &x, int &y) {
	int temp = x;
	x = y;
	y = temp;
}

BinaryHeap::BinaryHeap(const int maxi):
	cap { maxi },
	sum { 0 },
	size{ 0 }
{
	elems = new int[cap];
}

void BinaryHeap::add(int e)
{
	// Add an element. If the capacity it's reached, replace
	// the smallest element with the new one ONLY IF the new
	// element to be added is greater than the smallest in
	// the heap.

	// Complexity: O(logn), n = heap capacity

	int i = size - 1;

	if (size == cap) {
		if (e > getRoot())
		{
			sum -= elems[0];
			elems[0] = e;
			sum += e;
		}
	}
	else {
		size++;
		i = size - 1;
		elems[i] = e;
		sum += e;
	}

	//bubble_min_recursive(0); // work aswell
	bubble_down(0);
}

int BinaryHeap::get_sum()
{
	// Return the sum of the k elements
	// Complexity: Theta(1)

	return this->sum;
}

void BinaryHeap::show()
{
	// Display the vector

	// Complexity: Theta(n)

	for (int i = 0; i < this->size; i++)
	{
		cout << this->elems[i] << ' ';
	}
	cout << '\n';
}

void BinaryHeap::bubble_min_recursive(int p)
{
	// Turn the heap into a MinHeap by comparing the
	// leaves with the parent (both leaves)
	// After that, the algorithm continues with the
	// smallest leaf => log2(n) time complexity

	// Complexity: O(logn) , n = heap size

	int l = left(p);
	int r = right(p);
	int smallest = p;

	if (l < size && elems[l] < elems[p])
	{
		smallest = l;
	}

	if (r < size && elems[r] < elems[smallest])
	{
		smallest = r;
	}

	if (smallest != p)
	{
		swap(elems[p], elems[smallest]);
		bubble_min_recursive(smallest);
	}
}

void BinaryHeap::bubble_down(int p)
{
	// Makes a MinHeap/restore MinHeap property by
	// comparing each father with his leaves, swap
	// and continue with the smallest one => log2(n)

	// Complexity: log2(n) , n = heap size


	int pos = p;
	//int elem = elems[p];
	int min_child = p;

	while (pos < this->size)
	{
		min_child = pos;

		if (left(pos) < size  && elems[left(pos)] < elems[pos])
		{
			min_child = left(pos);
		}

		if (right(pos) < size && elems[right(pos)] < elems[min_child])
		{
			min_child = right(pos);
		}

		if (min_child != pos)
		{
			swap(elems[pos], elems[min_child]);
			pos = min_child;
		}
		else
		{
			//pos = this->size + 1;
			break;
		}
	}
}

BinaryHeap::~BinaryHeap()
{
	delete elems;
}