#include "Account.h"
#include "Log.h"
#include <iostream>

using namespace std;

Account::Account():
    account_id{ 0 },
    balance { 0 },
    owner { "John Doe" }
{

}

Account::Account(string build_owner, float build_balance, int build_id):
    owner{ build_owner },
    balance{ build_balance },
    account_id{ build_id }
{

}

void Account::setName(string name) {
    owner = name;
}

string Account::getName() {
    return owner;
}

float Account::transaction(Log trans) {
    // Log log = new Log(account_id, source, sum, reason);
    logs.push_back(trans);

    if(trans.getTransType() == Transaction::send) {
        balance -= trans.getAmount();
    } else {
        balance += trans.getAmount();
    }

    return balance;
}

float Account::getBalance() {
    return balance;
}

int Account::getId() {
    return account_id;
}

vector<Log> Account::getTransactions() {
    return logs;
}


string Account::toString() {
    // string ret = "(\n";
    // ret += "\tid: " + to_string(account_id) + ",\n";
    // ret += "\tname: " + owner + ", ";
    // ret += "\tbalance: " + to_string(balance) + ",\n";
    // ret += "\tlogs: [\n";
    // for(int i = 0; i < logs.size(); ++i) {
    //     ret += "\t\t" + logs[i].toString() + ",\n";
    // }
    // ret += "\t]\n)";

    string ret = "( ";
    ret += "id: " + to_string(account_id) + ", ";
    ret += "name: " + owner + ", ";
    ret += "balance: " + to_string(balance) + ", ";
    ret += ")";

    return ret;
}