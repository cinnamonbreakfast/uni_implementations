#include <iostream>
#include "SortedSet.h"
#include "SortedSetIterator.h"

SortedSet::SortedSet(Relation r):R(r){
	this->elems = new TElem[this->cap];
}

bool SortedSet::add(TComp e) {
	int i;

	if (this->search(e) == true)
		return false;

	if (this->asize + 1 > this->cap) {
		TElem* new_list = new TElem[2 * this->cap];

		this->cap *= 2;

		for (i = 0; i < this->asize; ++i) {
			new_list[i] = this->elems[i];
		}

		delete[] this->elems;
		this->elems = new_list;
	}

	this->elems[this->asize++] = e;

	int j = 0;

	TComp x;
	
	// DUMB THING BEGINS

	// THIS. IS. THE DUMBEST. THING. EVER
	// Just iterate till relation evaluates
	// as FALSE. That means you found the
	// pos to insert node.

	for (i = 0; i < this->asize - 1; i++) {
		for (j = i; j < this->asize; j++)
			if (this->R(this->elems[j], this->elems[i])) {
				x = this->elems[i];
				this->elems[i] = this->elems[j];
				this->elems[j] = x;
			}
	}

	// DUMB THING END

	return true;
}

bool SortedSet::remove(TComp e) {
	if (this->search(e) == false) {
		return false;
	}

	int i = 0, j;
	for (; i < this->asize; i++) {
		if (this->elems[i] == e)
		{
			for (j = i; j < this->asize - 1; ++j) {
				this->elems[j] = this->elems[j + 1];
			}
			this->asize--;
			return true;
		}
	}

	return false;
}

bool SortedSet::search(TElem elem) const
{
	int i = 0;

	for (; i < this->asize; i++) {
		if (this->elems[i] == elem)
			return true;
	}

	return false;
}

int SortedSet::size() const {
	return this->asize;
}

bool SortedSet::isEmpty() const {
	return this->asize == 0;
}

SortedSetIterator SortedSet::iterator() const {
	SortedSetIterator x{ *this };
	return x;
}

TElem SortedSet::operator[](int i) const {
	return this->elems[i];
}

SortedSet::~SortedSet() {
	delete[] this->elems;
}

void SortedSet::sunion(const SortedSet& s) {
	// Overall complexity: O(n)

	if (s.isEmpty() == false) {		//theta(1)
		SortedSetIterator I = s.iterator();

		I.first();

		while (I.valid()) {
			// theta(1) for get current
			// O(n) for add, because first it looks for
			// the value and if it doesn't exists in the
			// sett, it will be added. The position or
			// existence is not sure, so worst case
			// complexity is O(n)

			this->add(I.getCurrent()); 
			
			I.next();
		}
	}
}