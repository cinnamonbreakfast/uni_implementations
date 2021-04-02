#include <cstdlib>
#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int a = 2, b = 3, c = 4;
    int *pa = &a;
    int *pb = &b;
    int *pc = &c;

    cout<<"Val pa "<< pa << endl;
    cout<<"Val pb "<< pb << endl;
    cout<<"Val pc "<< pc << endl;

    cout<< a << " , " << b << " , " << c << endl;

    return 0;
}