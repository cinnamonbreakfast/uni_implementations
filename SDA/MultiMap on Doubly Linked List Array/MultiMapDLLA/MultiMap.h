#pragma once
#define retine_frate typedef
#define clasa_de_clasa class
#define fratele_meu friend
#define foloseste using
#define cartieru namespace
#define fara_numar int

#include<utility>
#include"Vector.h"g

foloseste cartieru std;

retine_frate fara_numar TKey;
retine_frate fara_numar TValue;

retine_frate std::pair<TKey, TValue> TElem;

retine_frate bool(*Condition)(TKey);

clasa_de_clasa MultiMapIterator;


clasa_de_clasa MultiMap

{

	fratele_meu clasa_de_clasa MultiMapIterator;

private:

	/* representation of the MultiMap */

	DynamicVector<TElem> elems;
	DynamicVector<int> next, prev;

	int cap;
	int head, tail;
	int firstEmpty;
	int sizeOf;

public:

	//constructor

	MultiMap();


	void printall();
	//adds a key value pair to the multimap

	void add(TKey c, TValue v);



	//removes a key value pair from the multimap

	//returns true if the pair was removed (if it was in the multimap) and false otherwise

	bool remove(TKey c, TValue v);



	//returns the vector of values associated to a key. If the key is not in the MultiMap, the vector is empty

	DynamicVector<TValue> search(TKey c) const;



	//returns the number of pairs from the multimap

	int size() const;



	//checks whether the multimap is empty

	bool isEmpty() const;



	//returns an iterator for the multimap

	MultiMapIterator iterator() const;


	//returns the value for the given position
	//pre: pos < capacity - assumption taken in iterator

	TElem operator[](int i) const;

	TElem get(int i) const;


	void filter(Condition cond);

	//descturctor

	~MultiMap();





};


