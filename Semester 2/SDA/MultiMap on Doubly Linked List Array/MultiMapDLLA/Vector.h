#pragma once

template <typename T>
class DynamicVector
{
private:
	int vsize;
	int capacity;
	T* elems;

public:
	DynamicVector(int cap = 10);
	DynamicVector(const DynamicVector& v);
	DynamicVector& operator=(const DynamicVector& v);
	void operator+(const T x);
	//DynamicVector operator+(const T x, const DynamicVector& y);
	T& operator[](int pos) const;

	void append(const T x);
	void push_back(const T x);
	void push_to(const int i, const T x);
	void resize();

	T& get(const int);
	T& at(const int);

	int size();

	void remove(const int&);

	~DynamicVector();
};

//typedef DynamicVector Repository;

template <typename T>
DynamicVector<T>& operator+(T e, DynamicVector<T>& y) {
	// Vector + element
	y.append(e);
	return y;
}

template <typename T>
DynamicVector<T>::DynamicVector(int cap)
{
	// Init
	this->vsize = 0;
	this->capacity = cap;
	this->elems = new T[this->capacity];
}

template <typename T>
DynamicVector<T>::DynamicVector(const DynamicVector & v)
{
	// Vector{ vector }
	this->capacity = v.capacity;
	this->vsize = v.vsize;

	this->elems = new T[this->capacity];
	for (int i = 0; i < this->vsize; i++)
		this->elems[i] = v.elems[i];
}

template <typename T>
void DynamicVector<T>::operator+(const T x) {
	// vector + elem
	this->append(x);
}

template <typename T>
DynamicVector<T>& DynamicVector<T>::operator=(const DynamicVector& v)
{
	// vector = vector
	if (this == &v)
		return *this;

	this->capacity = v.capacity;
	this->vsize = v.vsize;

	delete[] this->elems;
	this->elems = new T[this->capacity];
	for (int i = 0; i < this->vsize; i++)
		this->elems[i] = v.elems[i];
	return *this;
}

template <typename T>
T& DynamicVector<T>::operator[](int pos) const
{
	// vector[ int ] -- getter
	return this->elems[pos];
}

template <typename T>
void DynamicVector<T>::resize() {
	// Resize
	T* new_list = new T[2 * this->capacity];

	this->capacity *= 2;

	for (int i = 0; i < this->vsize; ++i) {
		new_list[i] = this->elems[i];
	}

	delete[] this->elems;
	this->elems = new_list;
}

template <typename T>
void DynamicVector<T>::remove(const int& P) {
	// Remove vector [ i ]
	int i;

	for (i = P; i < this->vsize - 1; ++i) {
		this->elems[i] = this->elems[i + 1];
	}

	this->vsize--;
}

template <typename T>
void DynamicVector<T>::append(const T x) {
	// append
	if (this->vsize >= this->capacity) {
		this->resize();
	}

	this->elems[this->vsize++] = x;
}

template <typename T>
void DynamicVector<T>::push_back(const T x) {
	// append
	if (this->vsize >= this->capacity) {
		this->resize();
	}

	this->elems[this->vsize++] = x;
}

template <typename T>
void DynamicVector<T>::push_to(const int i, const T x) {
	// append
	if (this->vsize >= this->capacity) {
		this->resize();
	}

	this->elems[i] = x;
	this->vsize++;
}

template <typename T>
T& DynamicVector<T>::get(const int i) {
	return this->elems[i];
}

template <typename T>
T& DynamicVector<T>::at(const int i) {
	return this->elems[i];
}

template <typename T>
int DynamicVector<T>::size() {
	return this->vsize;
}

template <typename T>
DynamicVector<T>::~DynamicVector()
{
	delete[] this->elems;
}