#pragma once
#include <string>
#include <vector>

#include "Log.h"

class Account {
    private:
        int account_id;
        float balance;
        std::string owner;
        std::vector<Log> logs;

    public:
        Account();
        Account(std::string, float, int);
        int getId();

        void setName(std::string);
        std::string getName();

        float transaction(Log);

        std::vector<Log> getTransactions();

        float getBalance();

        std::string toString();
};