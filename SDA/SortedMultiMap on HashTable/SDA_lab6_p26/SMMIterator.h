#pragma once
#include "SortedMultiMap.h"

class SortedMultiMap;

class SMMIterator
{
	friend class SortedMultiMap;

private:
	//the iterator stores a reference to the container 
	const SortedMultiMap& SMM;
	//other specific attributes: current, etc
	std::vector<TElem> elems;

	int cur;

	SMMIterator(const SortedMultiMap&);

public:
	TElem getCurrent();
	bool valid();
	void next();
	void first();
};

