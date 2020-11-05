#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <unistd.h>
#include <vector>
#include "approaches.h"
#include "Matrix.h"
#include "methods.h"
#include <chrono>
#include <ctime>

using namespace std;


int main(int argc, char** argv) {
    int T = 4, M = 1, A = 1;
    if(argc > 1)
    {
        T = atol(argv[1]);
        M = atol(argv[2]);
        A = atol(argv[3]);
    }

    cout<<"Starting with "<<T<<" and the method"<<M<<endl;

    srand(time(nullptr));
    Matrix m = Matrix(250);

    Matrix m2 = Matrix(250);

    Matrix m3 = Matrix(250);

    // m.print();
    // cout<<'\n';
    // m2.print();

    auto start = std::chrono::system_clock::now();

    A == 1 ? classic_parallelism(T, M, m, m2, m3) : threadpool_method(T, M, m, m2, m3);

    auto end = std::chrono::system_clock::now();

    // m3.print();

    std::chrono::duration<double> elapsed_seconds = end - start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "\nfinished computation at " << std::ctime(&end_time)
              << "elapsed time: " << elapsed_seconds.count() << "s\n";

    return 0;
}
