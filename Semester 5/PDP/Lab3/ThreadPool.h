#include <mutex>
#include <queue>
#include <functional>
#include <condition_variable>
#include <thread>

#ifndef LAB3_THREADPOOL_H
#define LAB3_THREADPOOL_H

class ThreadPool {
public:
    explicit ThreadPool(size_t n): finished{false} {
        threads.reserve(n);
        for (size_t i = 0; i < n; i++) {
            threads.emplace_back([&]() { this->run(); });
        }
    }

    ~ThreadPool() {
        close();
        for (auto& t : threads) {
            t.join();
        }
    }

    void close() {
        std::unique_lock<std::mutex> lk(mtx);
        finished = true;
        cv.notify_all();
    }

    void enqueue(std::function<void()> fun) {
        std::unique_lock<std::mutex> lk(mtx);
        queue.push(std::move(fun));
        cv.notify_one();
    }

private:
    std::vector<std::thread> threads;
    std::mutex mtx;
    std::condition_variable cv;
    std::queue<std::function<void()>> queue;
    bool finished;

    void run() {
        while (true) {
            std::function<void()> func;
            {
                std::unique_lock<std::mutex> lk(mtx);
                while (queue.empty() && !finished) {
                    cv.wait(lk);
                }
                if (queue.empty()) {
                    return;
                }
                func = std::move(queue.front());
                queue.pop();
            }
            func();
        }
    }
};

#endif //LAB3_THREADPOOL_H