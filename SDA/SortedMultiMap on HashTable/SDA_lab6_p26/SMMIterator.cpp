#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <algorithm>
#include <exception>
#include <stdio.h>

using namespace std;

SMMIterator::SMMIterator(const SortedMultiMap& c): SMM(c) {
	//initialize current and other specific attributes
	int i = 0;

	if (!SMM.isEmpty())
	{
		this->first();
	}
}

TElem SMMIterator::getCurrent() {
	//TBA
	return elems[this->cur];
}

bool SMMIterator::valid() {
	return this->cur < SMM.size() && SMM.size() != 0;
}

void SMMIterator::next() {
	if (this->valid())
		this->cur++;
	else throw std::exception("not valid");
}

void SMMIterator::first() {
	int i = 0, j = 0;
	if (!SMM.isEmpty()) {
		for (; i < SMM.m; ++i)
		{
			if (SMM.T[i] != TElem(INT_MIN, INT_MIN)) {
				elems.push_back(SMM.T[i]);
			}
		}

		TElem aux;

		for (i = 0; i < elems.size() - 1; i++)
		{
			for (j = i + 1; j < elems.size(); j++)
			{
				if (SMM.c(elems[j].first, elems[i].first))
				{
					aux = elems[i];
					elems[i] = elems[j];
					elems[j] = aux;
				}
			}
		}
	}

	this->cur = 0;
}

