#include "SortedMultiMap.h"
#include "SMMIterator.h"
#include <iostream>

using namespace std;
//implementation of the operations from Container.h

int hashFunc(TKey k, int m)
{
	return k%m;
}

int hashMul(TKey k, int m)
{
	double n = (double)k * 0.6180339887;
	double f = n - floor(n);
	return floor(m * f);
}

SortedMultiMap::SortedMultiMap(Relation F) :
	m{ 10 },
	firstFree{ 0 },
	c{ F },
	a{ 0 },
	h{ hashMul }
{
	T = new TElem[m];
	next = new int[m];

	for (int i = 0; i < m; ++i)
	{
		T[i] = pair<TKey, TValue>(INT_MIN, INT_MIN);
		next[i] = -1; // let's be safe tho
	}
}

SMMIterator SortedMultiMap::iterator() const {
	return SMMIterator(*this);
}

void SortedMultiMap::updateFirstFree()
{
	/*this->firstFree += 1;

	while (this->firstFree < this->m and this->T[firstFree].first != INT_MIN)
	{
		this->firstFree += 1;
	}*/

	this->firstFree = 0;

	while (this->T[firstFree].first != INT_MIN)
	{
		this->firstFree++;
	}
}

void SortedMultiMap::rehashize()
{
	TElem *nT = new TElem[this->m * 2];
	int *nN = new int[this->m * 2];
	int fF = 0;
	int i = 0;


	for (i = 0; i < this->m*2; ++i)
	{
		nT[i] = pair<TKey, TValue>(INT_MIN, INT_MIN);
		nN[i] = -1; // let's be safe tho
	}

	TKey k = 0;
	TValue v = 0;

	for (int i = 0; i < this->m; ++i)
	{
		if (this->T[i].first == INT_MIN) continue;

		k = this->T[i].first;
		v = this->T[i].second;

		int pos = this->h(k, this->m*2);

		if (nT[pos].first == INT_MIN)
		{
			nT[pos] = pair<TKey, TValue>(k, v);
			nN[pos] = -1;
		}
		else {
			fF = 0;
			while (nT[fF].first != INT_MIN)
			{
				fF++;
			}

			int cc = pos, pr = pos;

			while (cc != -1)
			{
				pr = cc;
				cc = nN[cc];
			}
			
			nT[fF] = pair<TKey, TValue>(k, v);
			nN[fF] = -1;
			nN[pr] = fF;

			/*int cur = pos, prev = pos;
			while (cur != -1) { prev = cur; cur = nN[cur]; }
			nT[cur] = pair<TKey, TValue>(k, v);
			nN[cur] = -1;
			nN[prev] = cur;*/
		}
	}
	delete[] this->T;
	delete[] this->next;

	this->T = nT;
	this->next = nN;
	this->m *= 2;
	this->firstFree = fF;
}

void SortedMultiMap::add(TKey k, TValue v)
{
	int pos = this->h(k, this->m);

	if (this->T[pos].first == INT_MIN)
	{
		this->T[pos] = pair<TKey, TValue>(k, v);
		this->next[pos] = -1;
	}
	else {
		if (this->size() == m - 1)
		{
			// resize
			// rehash

			//cout << "rehash";

			this->rehashize();

			// firstFree & m are covered by rehashize
			// function, while pos refresh is user's
			// responsability (as done below)

			pos = this->h(k, this->m);

		}

		this->updateFirstFree();
		int cur = pos, prev = pos;
		while (cur != -1)
		{
			prev = cur;
			cur = this->next[cur];
		}

		/*this->T[cur] = pair<TKey, TValue>(k, v);
		this->next[cur] = -1;
		this->next[prev] = cur;*/
		this->T[firstFree] = pair<TKey, TValue>(k, v);
		this->next[firstFree] = -1;
		this->next[prev] = firstFree;
		//this->updateFirstFree();
	}

	/*cout << "\n\n\n\n\n";
	for (int i = 0; i < this->m; ++i)
	{
		cout << T[i].first << " " << T[i].second << " " << next[i] << "\n";
	}
	cout << "\n\n\n\n\n";*/

	this->a++;
}


//void SortedMultiMap::add(TKey k, TValue v)
//{
//	int pos = this->h(k, this->m);
//	
//	if (this->T[pos].first == INT_MIN)
//	{
//		this->T[pos] = pair<TKey, TValue>(k, v);
//		this->next[pos] = -1;
//	}
//	else {
//		//this->updateFirstFree();
//		if (this->firstFree == this->m - 1)
//		{
//			// resize
//			// rehash
//
//			cout << "rehash";
//
//			this->rehashize();
//
//			// firstFree & m are covered by rehashize
//			// function, while pos refresh is user's
//			// responsability (as done below)
//
//			pos = this->h(k, this->m);
//			
//		}
//
//		int cur = pos;
//		while (this->next[cur] != -1)
//		{
//			//cout << "loop1";
//			cur = this->next[cur];
//		}
//
//		this->T[firstFree] = pair<TKey, TValue>(k, v);
//		this->next[firstFree] = -1;
//		this->next[cur] = firstFree;
//		this->updateFirstFree();
//	}
//
//	cout << "\n\n\n\n\n";
//	for (int i = 0; i < this->m; ++i)
//	{
//		cout << T[i].first << " " << T[i].second << " " << next[i] << "\n";
//	}
//	cout << "\n\n\n\n\n";
//
//	this->a++;
//}

bool SortedMultiMap::remove(TKey k, TValue v)
{
	// if this points to another element we will
	// bring the pointed one here and free the
	// old position of the pointed one

	int pos = this->h(k, this->m);

	int cur = pos;
	//int cur = 0;
	while (cur != -1)
	{
		if (this->T[cur].first == k and this->T[cur].second == v)
		{

			if (this->next[cur] != -1)
			{
				// bring
				int n = this->next[cur];
				this->T[cur] = this->T[n];
				this->next[cur] = this->next[n];

				// free
				this->T[n] = pair<TKey, TValue>(INT_MIN, INT_MIN);
				this->next[n] = -1;
			}
			else {
				this->T[cur] = pair<TKey, TValue>(INT_MIN, INT_MIN);
			}
			this->a--;
			return true;
		}
		cur = this->next[cur];
	}

	//int prev = -1;

	//int cur = 0;

	//cout << m;

	//while (cur < this->m)
	//{
	//	if (this->T[cur].first == k && this->T[cur].second == v)
	//	{
	//		if (this->next[cur] != -1)
	//		{
	//			this->T[cur] = this->T[this->next[cur]];
	//			this->next[cur] = this->next[this->next[cur]];
	//			this->T[this->next[cur]] = pair<TKey, TValue>(INT_MIN, INT_MIN);
	//			this->next[this->next[cur]] = -1;
	//		}
	//		else {
	//			this->T[cur] = pair<TKey, TValue>(INT_MIN, INT_MIN);
	//			this->next[cur] = -1;
	//		}

	//		this->a--;

	//		return true;
	//	}
	//	cur++;
	//}

	return false;
}

vector<TValue> SortedMultiMap::search(TKey k)
{
	// assuming that there isn't any overlaping
	// we may start on the given pos by the
	// hash function and iterate through the
	// positions given by next till we reach -1

	//int i = 0;

	/*for (; i < this->m; i++)
	{
		cout << T[i].first << " " << T[i].second << " " << next[i] << '\n';
	}*/

	int pos = this->h(k, this->m);
	vector<TValue> temp;

	/*if (this->size() == 0)
		return temp;*/

	//int cur = pos;
	//while (cur != -1)
	//{
	//	//cout << cur << '\n';
	//	if (this->T[cur].first == k)
	//		temp.push_back(this->T[cur].second);

	//	cur = this->next[cur];
	//}

	int cur = 0;
	while (cur < m)
	{
		//cout << cur << '\n';
		if (this->T[cur].first == k)
			temp.push_back(this->T[cur].second);

		cur ++;
	}
	
	return temp;
}

void SortedMultiMap::print()
{
	cout << "\n\n\n\n\n";
	for (int i = 0; i < this->m; ++i)
	{
		cout << i << " " << T[i].first << " " << T[i].second << " " << next[i] << "\n";
	}
	cout << "\n\n\n\n\n";
}

void SortedMultiMap::filter(Condition c)
{
	int i, j;
	for (i = 0; i < this->m; i++)
	{
		if (!c(this->T[i].first))
		{
			this->T[i] = pair<TKey, TValue>(INT_MIN, INT_MIN);
			
			for (j = 0; j < this->m; j++)
			{
				if (this->next[j] == i)
				{
					this->next[j] = this->next[i];
				}
			}

			this->next[i] = -1;
			this->a--;
		}
	}
}

SortedMultiMap::~SortedMultiMap() {
	delete[] this->T;
	delete[] this->next;
}


//the rest of the operations