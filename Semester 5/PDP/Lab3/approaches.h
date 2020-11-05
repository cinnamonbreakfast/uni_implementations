#ifndef LAB3_APPROACHES_H
#define LAB3_APPROACHES_H

#include "Matrix.h"
#include "methods.h"
#include <vector>
#include <thread>
#include "thread_pool_library/maginatics/threadpool.h"

void classic_parallelism(int tasks, int method, Matrix m1, Matrix m2, Matrix& res) {
    std::vector<std::thread> threads;

    if(method == 1)
    {
        int count = m1.size() * m1.size();
        
        int distrib = count / tasks;
        
        int start, end, nr, start_i = 0, start_j = 0;

        for (int i = 0; i < tasks; i++) {
            start = i * distrib;
            end = i == tasks - 1 ?
                count : start + distrib;
            nr = end - start;

            threads.push_back(std::thread([=, &res] { split1(nr, start_i, start_j, m1, m2, res); }));

            start_i = end / m1.size();
            start_j = end % m1.size();
        }

        for(int i = 0; i < tasks; i++)
        {
            threads[i].join();
        }
    }
    else if(method == 2)
    {
        int count = m1.size() * m1.size();
        
        int distrib = count / tasks;
        
        int start, end, nr, start_i = 0, start_j = 0;

        for (int i = 0; i < tasks; i++) {
            start = i * distrib;
            end = i == tasks - 1 ?
                count : start + distrib;
            nr = end - start;

            threads.push_back(std::thread([=, &res] { split2(nr, start_i, start_j, m1, m2, res); }));

            start_i = end % m1.size();
            start_j = end / m1.size();
        }

        for(int i = 0; i < tasks; i++)
        {
            threads[i].join();
        }
    }
    else if(method == 3)
    {
        for (int i = 0; i < tasks; i++) {
            threads.push_back(std::thread([=, &res] { split3(i, tasks, m1, m2, res); }));
        }

        for(int i = 0; i < tasks; i++)
        {
            threads[i].join();
        }
    }
}

void threadpool_method(int threads, int method, Matrix m1, Matrix m2, Matrix& res) {
    maginatics::ThreadPool threadpool(threads);

    if(method == 1)
    {
        int count = m1.size() * m1.size();

        int distrib = count / threads;

        int start, end, nr, start_i = 0, start_j = 0;
        for (int i = 0; i < threads; i++) {
            start = i * distrib;
            end = i == threads - 1 ?
                count : start + distrib;
            nr = end - start;

            threadpool.execute([=, &res]() { split1(nr, start_i, start_j, m1, m2, res); });

            start_i = end / m1.size();
            start_j = end % m1.size();
        }
    }
    else if(method == 2)
    {
        int count = m1.size() * m1.size();

        int distrib = count / threads;

        int start, end, nr, start_i = 0, start_j = 0;
        for (int i = 0; i < threads; i++) {
            start = i * distrib;
            end = i == threads - 1 ?
                count : start + distrib;
            nr = end - start;

            threadpool.execute([=, &res]() { split2(nr, start_i, start_j, m1, m2, res); });

            start_i = end % m1.size();
            start_j = end / m1.size();
        }
    }
    else if(method == 3)
    {
        for (int i = 0; i < threads; i++) {
            threadpool.execute([=, &res]() { split3(i, threads, m1, m2, res); });
        }
    }

    threadpool.drain();
}

#endif