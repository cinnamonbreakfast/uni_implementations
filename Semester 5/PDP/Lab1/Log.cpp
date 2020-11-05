#include "Log.h"
#include <string>

using namespace std;

Log::Log(): 
    id{ 0 },
    descript{ "Description" },
    target{ 0 },
    source{ 1 },
    amount{ 0 },
    transtype{ send }
{}

Log::Log(long b_id, int b_target, int b_source, float b_amount, string b_descr, Transaction typ):
    id{ b_id },
    target{ b_target },
    source{ b_source },
    amount{ b_amount },
    descript{ b_descr },
    transtype{ typ }
{}

string Log::getDescript() {
    return descript;
}

int Log::getTarget() {
    return target;
}

int Log::getSource() {
    return source;
}

float Log::getAmount() {
    return amount;
}

Transaction Log::getTransType() {
    return transtype;
}

string Log::toString() {
    string ret = "(";
    ret += "id: " + to_string(id) + ", ";
    ret += "source: " + to_string(source) + ", ";
    ret += "amount: " + to_string(amount) + ", ";
    ret += string("type: " + (getTransType() == Transaction::send ? string("send") : string("receive")) + ", ");
    ret += "balance: " + to_string(target_balance) + ", ";
    ret += ")";
    
    return ret;
}

float Log::setBalance(float bal) {
    target_balance = bal;

    return bal;
}