#include "Prob10.h"
#include "BinaryHeap.h"
#include <iostream>
#include <algorithm>

using namespace std;

long computeSum(vector<TElem> vector, int k)
{
	if (k <= 0)
		throw std::exception();

	BinaryHeap heap(k);
	for (unsigned i = 0; i < vector.size(); ++i) {
		heap.add(vector[i]);
	}

	return heap.get_sum();
}