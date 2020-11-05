#pragma once;
#include <vector>
#include <limits.h>
#define and &&

typedef int TKey;
typedef int TValue;

typedef std::pair<TKey, TValue> TElem;

typedef TKey TComp;

typedef bool(*Relation)(TComp, TComp);
typedef int(*HFunction)(TKey, int);
typedef bool(*Condition)(TKey);

class SMMIterator;

class SortedMultiMap {

	friend class SMMIterator;

private:
	TElem *T;
	int *next;
	int m;
	int a;
	int firstFree;
	Relation c;
	HFunction h;

	void updateFirstFree();
	void rehashize();

	//container representation
public:
	//implicit constructor
	SortedMultiMap(Relation);

	//specific operations
	void add(TKey, TValue);

	bool remove(TKey, TValue);

	std::vector<TValue> search(TKey);

	void filter(Condition);

	int size() const { return this->a; }
	bool isEmpty() const { return this->size() == 0; }
	void print();

	//returns an iterator for the container
	SMMIterator iterator() const;

	//destructor
	~SortedMultiMap();

};

