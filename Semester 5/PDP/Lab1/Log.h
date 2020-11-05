#pragma once
#include <string>

enum Transaction {
    send = 0,
    receive
};

class Log {
    private:
        long id;
        std::string descript;
        int target;
        int source;
        float amount;
        float target_balance;
        Transaction transtype;
    public:
        Log();
        Log(long, int, int, float, std::string, Transaction);

        std::string getDescript();
        int getTarget();
        int getSource();
        float getAmount();
        float setBalance(float);
        Transaction getTransType();

        std::string toString();
};