#include <Windows.h>
#include "Prob10.h"
#include "TestP10.h"
#include "BinaryHeap.h"
#include <exception>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main()
{
	testP10();

	std::cout << "Tests OK!\n";
	system("pause");
	return 0;

}

//int main() {
//	BinaryHeap b(15);
//	//cout << b.get_sum() << '\n';
//	// 12 5 787 1 23
//	b.add(30);
//	b.add(15);
//	b.add(10);
//	b.add(50);
//	b.add(40);
//	b.add(100);
//	b.add(25);
//	b.add(5);
//
//	cout << "\n\n";
//
//	// 5 10 15 25 30 40 50 100
//
//	for (int i = -200; i < 201; i++)
//		b.add(i);
//
//	int s = 0;
//
//	for (int j = 200; j > 200 - 10; j--)
//		s += j;
//
//	//b.show();
//
//	//b.heapSort(4);
//
//	cout << b.get_sum() << " " << s << '\n';
//
//	system("pause");
//	return 0;
//}