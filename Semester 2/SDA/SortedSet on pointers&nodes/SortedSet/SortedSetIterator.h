#pragma once

#include "SortedSet.h"

class SortedSet;

//unidirectional iterator for a container

class SortedSetIterator {

private:

	friend class SortedSet;

	//contains a reference of the container it iterates over

	SortedSetIterator(const SortedSet& c);

	const SortedSet& c;

	int actual = 0;

	/* representation specific for the iterator*/


public:

	//Constructor receives a reference of the container.

	//after creation the iterator will refer to the first element of the container, or it will be invalid if the container is empty

	

	//sets the iterator to the first element of the container

	void first();



	//moves the iterator to the next element

	//throws exception if the iterator is not valid

	void next();

	int act() const;


	//checks if the iterator is valid

	bool valid() const;



	//returns the value of the current element from the iterator

	// throws exception if the iterator is not valid

	TElem getCurrent() const;



};


