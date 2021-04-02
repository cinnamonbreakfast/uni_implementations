#ifndef LAB5_UTILS_HPP
#define LAB5_UTILS_HPP

#include <iostream>
#include <vector>
#include <stdlib.h>
#include <time.h>

void printVector(std::vector<int>& a)
{
    std::cout<<a.size() - 1<<" grade polynomial."<<'\n';

    for(int i = 0; i < a.size(); ++i) {
        if(a[i] == 0) continue;
        if(a[i] > 1 || i > a.size() - 2) std::cout<<a[i];
        if(i <= a.size() - 2) std::cout<<"x^"<<a.size() - i - 1;
        if(i != a.size() - 1) std::cout<<" + ";
    }

    std::cout<<'\n';
}

std::vector<int> generateVector(int minSize, int maxSize, int domainMax)
{
    int size = rand() % maxSize + minSize;

    std::vector<int> res(size);

    for(int i = 0; i < size; i++)
    {
        res[i] = rand() % domainMax + 0;
    }

    return res;
}

#endif