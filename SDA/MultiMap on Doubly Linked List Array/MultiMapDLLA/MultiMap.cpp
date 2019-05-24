#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <iostream>

MultiMap::MultiMap() :
	cap{ 10 },
	head{ -1 }, tail{ -1 },
	firstEmpty{ 0 },
	elems{ 10 },
	next{ 10 },
	prev{ 10 },
	sizeOf{ 0 }
{
	int i = 0;
	for (; i < this->cap-1; ++i) {
		/*this->next[i] = i + 1;
		this->prev[i] = i - 1;*/
		this->next.push_to(i, i + 1);
		this->prev.push_to(i, i - 1);
	}
	this->prev[0] = -1;
	this->prev[this->cap-1] = this->cap-2;
	this->next[this->cap-1] = -1;
}

void MultiMap::printall() {
	int i;
	cout << '\n';
	for (i = 0; i < this->size(); i++) {
		printf("%d (%d %d) %d %d\n", i, this->elems[i].first, this->elems[i].second, next[i], prev[i]);
		
		////cout << next[i] << " " << prev[i]<<'\n';
	}
	cout << '\n';
}

void MultiMap::add(TKey c, TValue v) {
	if (this->head == -1)
	{
		// no elements yet
		//if(this->isEmpty()==false)
			this->head = this->tail = firstEmpty; // goes to first empty pos
		TElem new_add{ c, v };
		//this->elems[firstEmpty] = new_add; // old
		this->elems.push_to(firstEmpty, new_add);
		this->firstEmpty = this->next[firstEmpty];
	}
	else
	{
		if (firstEmpty == -1)
		{
			int i = 0;

			elems.resize();
			next.resize();
			prev.resize();

			for (i = this->cap-1; i < this->cap * 2; ++i) {
				//printall();

				/*next[i] = i + 1;
				prev[i] = i - 1;*/
				next.push_to(i, i + 1);
				prev.push_to(i, i - 1);
			}
			//printall();

			next[this->cap * 2 - 1] = -1;
			prev[this->cap * 2 - 1] = this->cap*2-2;
			prev[this->cap] = tail;
			firstEmpty = this->cap;
			this->cap *= 2;
			//printall();

		}
		TElem new_add{ c, v };

		int current = firstEmpty;

		/*this->elems[firstEmpty] = new_add;*/
		this->elems.push_to(firstEmpty, new_add);
		this->next[tail] = firstEmpty;
		this->prev[firstEmpty] = tail;
		tail = firstEmpty;
		firstEmpty = this->next[firstEmpty];
		this->next[current] = -1;
		//printall();
	}

	sizeOf++;
}

bool MultiMap::remove(TKey c, TValue v)
{
	int i = head;

	if (elems[i].first == c && elems[i].second == v && i == head) {
		if (this->size() == 1) {
			int i = 0;
			for (; i < this->cap - 1; ++i) {
				/*this->next[i] = i + 1;
				this->prev[i] = i - 1;*/
				this->next.push_to(i, i + 1);
				this->prev.push_to(i, i - 1);
			}
			this->prev[0] = -1;
			this->prev[this->cap - 1] = this->cap - 2;
			this->next[this->cap - 1] = -1;

			head = tail = -1;
			firstEmpty = 0;

			this->sizeOf -= 1;

			return true;
		}
		head = next[i];
		
		prev[next[i]] = -1;
		next[i] = firstEmpty;
		prev[firstEmpty] = i;
		prev[i] = tail;

		firstEmpty = i;

		this->sizeOf -= 1;

		return true;
	}
	
	for (; i != tail; i = next[i])
	{
		if (elems[i].first == c && elems[i].second == v) {
			
			this->next[prev[i]] = this->next[i];
			this->prev[next[i]] = this->prev[i];
			this->next[i] = firstEmpty;
			this->prev[i] = -1;
			this->prev[firstEmpty] = i;

			if (i == tail) {
				tail = prev[i];
				if (tail == -1) head = -1;
			}

			firstEmpty = i;

			this->sizeOf -= 1;

			return true;
		}
	}

	return false;
}

DynamicVector<TValue> MultiMap::search(TKey c) const
{
	DynamicVector<TValue> tmp;

	int i = head;
	for (; i != -1; i = next[i])
	{
		//printf("(%d %d) %d %d\n", this->elems[i].first, this->elems[i].second, next[i], prev[i]);

		if (this->elems[i].first == c)
		{
			tmp.append(elems[i].second);
		}
	}

	return tmp;
}

bool MultiMap::isEmpty() const
{
	return this->size() == 0;
}

int MultiMap::size() const
{
	return this->sizeOf;
}

TElem MultiMap::operator[](int i) const
{
	return this->elems[i];
}

TElem MultiMap::get(int i) const
{
	return this->elems[i];
}

MultiMapIterator MultiMap::iterator() const
{
	return MultiMapIterator{ *this };
}

void MultiMap::filter(Condition cond)
{
	// Keep only those pairs whose TKey respects the Condition 'cond'
	// Complexity: Theta(n)

	int i = head, tmp;
	while (i != -1)
	{
		tmp = next[i];
		if (cond(this->get(i).first) == false)
		{
			this->remove(this->get(i).first, this->get(i).second);
			i = tmp;
		}
		else {
			i = next[i];
		}
		
	}
}

MultiMap::~MultiMap()
{
	
}