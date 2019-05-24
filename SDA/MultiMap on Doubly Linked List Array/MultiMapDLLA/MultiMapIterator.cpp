#include "MultiMapIterator.h"

MultiMapIterator::MultiMapIterator(const MultiMap& c) :
	c{ c }
{
	current = -1;
	if (c.isEmpty() == false)
		first();
}

void MultiMapIterator::first()
{
	current = c.head;
}

void MultiMapIterator::next()
{
	if (valid() == true)
		current = c.next[current];
	else
		throw;
}

bool MultiMapIterator::valid() const
{
	return (this->current != -1);
}

TElem MultiMapIterator::getCurrent() const
{
	return this->c[this->current];
}

int MultiMapIterator::getCurr() const
{
	return this->current;
}