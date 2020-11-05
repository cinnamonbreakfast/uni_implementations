#include <iostream>
#include <vector>
#include <chrono>
#include <thread>
#include <mutex>
#include <ctime>
#include <string>
#include <string.h>
#include <algorithm>
#include <bits/stdc++.h> 

#include "Account.h"
#include "Log.h"

long ACCOUNTS = 5;
long THREADS = 4;
long TRANSACTIONS = 1000;

using namespace std;

mutex transaction_mutex;
long transactions = 0;

vector<Account> accounts;

int randExclusion(int ex) {
    int r = rand() % ACCOUNTS;
    while(r == ex) r = rand() % ACCOUNTS;

    return r;
}

void make_transaction() 
{

    for(int i = 0; i < TRANSACTIONS; ++i) {
        transaction_mutex.lock();

        int target = rand() % ACCOUNTS;
        int source = randExclusion(target);
        float amount = rand() % 100;

        transactions += 1;

        Log target_log = Log(transactions, target, source, amount, "Received", Transaction::receive);
        target_log.setBalance(accounts[target].getBalance() + amount);
        
        Log source_log = Log(transactions, source, target, amount, "Sent to", Transaction::send);
        source_log.setBalance(accounts[source].getBalance() - amount);

        accounts[source].transaction(source_log);
        accounts[target].transaction(target_log);
        
        transaction_mutex.unlock();
    }

}

void verify() {
    int checks = 0;

    while(checks < 40000) {
        if(checks % 26 == 0) {
            transaction_mutex.lock();

            float summ = 0;

            for(int i = 0; i < ACCOUNTS; ++i) {
                summ += accounts.at(i).getBalance();
            }

            if(summ != ACCOUNTS*500) cout<<"Evaziune fiscala\n";

            transaction_mutex.unlock();
        }
        checks++;
    }
}

void createAccounts() {
    for(int i = 0; i < ACCOUNTS; ++i) {
        Account acc = Account("John " + to_string(i), 500, i);
        accounts.push_back(acc);
    }
}

int main(int argc, char** argv) {
    cout<<"Entering..."<<endl;
    srand ( time(NULL) );

    if((argc - 1)%2 == 0) {
        for(int x = 1; x < argc - 1; x+=2) {

            if(strcmp(argv[x], "-w") == 0) {
                THREADS = atol(argv[x+1]);
                continue;
            }
            if(strcmp(argv[x], "-t") == 0) {
                TRANSACTIONS = atol(argv[x+1]);
                continue;
            }
            if(strcmp(argv[x], "-a") == 0) {
                ACCOUNTS = atol(argv[x+1]);
                continue;
            }
        }
    }

    createAccounts();

    cout<<"Starting for " + to_string(ACCOUNTS) + " accounts and make " + to_string(THREADS) + " workers of " + to_string(TRANSACTIONS) + " transactions.\n";

    // cout<<accounts.size()<<" accounts created\n";

    cout<<endl<<"Before transactions: \n\n";
    for(int i = 0; i < ACCOUNTS; ++i) {
        cout<<accounts.at(i).toString()<<endl;
    }

    int threads = 0;

    auto start = std::chrono::system_clock::now();

    thread vf (verify);
    // // vf.detach();

    vector<thread> workers;

    while(threads < THREADS) {
        workers.push_back(thread(make_transaction));

        threads += 1;
    }

    threads = 0;

    while(threads < THREADS) {
        workers[threads].join();
        threads += 1;
    }
    vf.join();

    auto end = std::chrono::system_clock::now();

    cout<<endl<<"After transactions: \n\n";
    for(int i = 0; i < ACCOUNTS; ++i) {
        cout<<accounts[i].toString()<<endl;
    }

    cout<<'\n';

    std::chrono::duration<double> elapsed_seconds = end-start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "\n\n\nfinished computation at " << std::ctime(&end_time)
              << "elapsed time: " << elapsed_seconds.count() << "s\n";

    return 0;
}