#include <iostream>
#include <vector>
#include <thread>
#include <stdlib.h>
#include <unistd.h>
#include <chrono>
#include <ctime>

#include "regular.hpp"
#include "karatsuba.hpp"
#include "utils.hpp"

using namespace std;

int main(int argc, char** argv)
{
    srand(time(nullptr));

    vector<int> a = generateVector(123, 1, 5);
    vector<int> b = generateVector(45, 1, 5);

    auto start = std::chrono::system_clock::now();

    // vector<int> res = multiply(a, b);
    // vector<int> res = multiplyPara(a, b);
    // vector<int> res = multiKara(a, b);
    vector<int> res = multiKaraPara(a, b);

    auto end = std::chrono::system_clock::now();

    printVector(res);

    std::chrono::duration<double> elapsed_seconds = end - start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "\nfinished computation at " << std::ctime(&end_time)
              << "elapsed time: " << elapsed_seconds.count() << "s\n";

    return 0;
}