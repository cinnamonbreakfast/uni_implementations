#include "SortedSetIterator.h"
#include <exception>
#include <stdio.h>

SortedSetIterator::SortedSetIterator(const SortedSet& c) :c(c){
	this->actual = 0;
}

void SortedSetIterator::first() {
	this->actual = 0;
}

void SortedSetIterator::next() {
	if (this->valid()) {
		this->actual++;
	}
	else throw std::exception("not valid");
}

bool SortedSetIterator::valid() const {
	return this->c.size() > this->actual && this->c.size()!=0;
}

int SortedSetIterator::act() const {
	return this->actual;
}

TElem SortedSetIterator::getCurrent() const {
	if (this->valid()) {
		return this->c[this->actual];
	}
	else throw std::exception("not valid");
}
