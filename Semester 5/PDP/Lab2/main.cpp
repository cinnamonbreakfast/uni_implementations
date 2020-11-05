#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <queue>
#include <chrono>

#include "stuff.hpp"

using namespace std;

int LEN = 5;

mutex m;
condition_variable read;
condition_variable write;

vector<int> a, b;
queue<int> prod;
int sum;

bool dataReady = false;
bool haveData = true;

void consumer()
{
    while(haveData)
    {
        cout<<"waiting"<<endl;
        unique_lock<mutex> lock(m);
        
        while(!dataReady)
        {
            read.wait(lock);
        }

        sum += prod.front();
        prod.pop();
        dataReady = false;
    }
}

void producer()
{
    for(int i = 0; i < LEN; ++i)
    {
        this_thread::sleep_for(chrono::milliseconds(500)); 
        lock_guard<mutex> lock(m);

        prod.push(a[i]*b[i]);

        cout<<"Prod aXb="<<a[i]<<" x "<<b[i]<<"="<<a[i]*b[i]<<endl;

        dataReady = true;
        read.notify_one();
    }

    lock_guard<mutex> lock(m);
    dataReady = true;
    haveData = false;
    read.notify_one();
}

int main()
{
    srand ( time(NULL) ); // get real random values

    a = generate_vector(LEN);
    b = generate_vector(LEN);

    cout<<"Vector a is: ";
    for(int i = 0; i < LEN; ++i) { cout<<a[i]<<' '; } cout<<endl;
    cout<<"Vector b is: ";
    for(int i = 0; i < LEN; ++i) { cout<<b[i]<<' '; } cout<<endl;

    thread c(consumer);
    thread p(producer);

    p.join();
    c.join();

    cout<<"Scalar product is "<<sum<<endl;

    return 0;
}