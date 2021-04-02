#ifndef LAB5_REG_SEQ_HPP
#define LAB5_REG_SEQ_HPP

#include <iostream>
#include <vector>
#include <thread>
#include "thread_pool_library/maginatics/threadpool.h"

#define NUM_THR 4

std::vector<int> multiply(std::vector<int>& a, std::vector<int>& b)
{
    int i, j;

    std::vector<int> res((a.size() - 1) + (b.size() - 1) + 1);

    for(i = 0; i < a.size(); ++i)
    {
        for(j = 0; j < b.size(); j++)
        {
            res[i+j] += a[i]*b[j];
        }
    }

    return res;
}

std::vector<int> multiplyPara(std::vector<int>& a, std::vector<int>& b)
{
    int i, j;
    maginatics::ThreadPool threadpool(NUM_THR);

    std::vector<int> res((a.size() - 1) + (b.size() - 1) + 1);

    for(i = 0; i < a.size(); ++i)
    {
        for(j = 0; j < b.size(); j++)
        {
            threadpool.execute([=, &res]() {
                res[i+j] += a[i]*b[j];
            });
        }
    }

    return res;
}


#endif