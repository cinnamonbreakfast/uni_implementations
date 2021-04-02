#ifndef LAB5_KARATSUBA_HPP
#define LAB5_KARATSUBA_HPP

#include <iostream>
#include <vector>
#include <algorithm>
#include "regular.hpp"

typedef std::vector<int> poly;

std::vector<int> makeEqualLength(std::vector<int> target, int size)
{
    if(size - target.size() == 0) return target;
    int i = 0;
    while(i <= size - target.size())
    {
        target.insert(target.begin(), 0);
        i++;
    }

    return target;
}


std::vector<int> rise(std::vector<int> target, int size)
{
    int i = 0;
    while(i < size)
    {
        target.push_back(0);
        i++;
    }

    return target;
}

poly shift(poly target, int size)
{
    int i = 0;
    while(i < size)
    {
        target.insert(target.begin(), 0);
        i++;
    }

    return target;
}

int mulTwo(int a, int b)
{
    return a*b;
}

std::vector<int> add(std::vector<int> a, std::vector<int> b)
{
    std::vector<int> res(std::max(a.size(), b.size()));

    for(int i = 0; i < a.size(); i++) {
        res[i] = a[i];
    }
    for(int i = 0; i < b.size(); i++) {
        res[i] += b[i];
    }

    return res;
}

std::vector<int> substr(std::vector<int> a, std::vector<int> b)
{
    std::vector<int> res(std::max(a.size(), b.size()));

    for(int i = 0; i < a.size(); i++) {
        res[i] += a[i];
    }
    for(int i = 0; i < b.size(); i++) {
        res[i] -= b[i];
    }

    return res;
}

poly multiKara(poly a, poly b)
{
    poly atmp, btmp;
    int n = std::max(a.size(), b.size());
    std::cout<<"Size: "<<n<<"\n";
    atmp = add(poly(n), a);
    btmp = add(poly(n), b);

    if(a.size() < 2 || b.size() < 2) return multiply(atmp, btmp);

    int fh = n/2;               // First half

    // First and second half of the first poly
    poly Xl(atmp.begin(), atmp.begin() + fh);
    poly Xr(atmp.begin() + fh, atmp.end());

    // First and second half of the second poly
    poly Yl(btmp.begin(), btmp.begin() + fh);
    poly Yr(btmp.begin() + fh, btmp.end());
    
    poly P1 = multiKara(Xl, Yl);
    poly P2 = multiKara(Xr, Yr);
    poly P3 = multiKara(add(Xl, Xr), add(Yl, Yr));

    poly P4 = substr(P3, add(P1, P2));

    poly PE(P1.size() + n);
    poly P1E(P1.size() + n);
    P1E = add(P1, P1E);

    poly P2E(P1.size() + n);
    for(int i = P1E.size() - P1.size(), p = 0; i < P1E.size(); i++, p++)
    {
        P2E[i] = P2[p];
    }

    poly P4E(P4.size() + fh);
    P4E = add(P4, P4E);
    for(int i = PE.size() - P4E.size(), p = 0; i < PE.size(); i++, p++)
    {
        PE[i] = P4E[p];
    }

    poly res = add(add(P1E, P2E), PE);

    return res;
}

poly multiKaraPara(poly a, poly b)
{
    poly atmp, btmp;
    int n = std::max(a.size(), b.size());

    atmp = add(poly(n), a);
    btmp = add(poly(n), b);

    if(a.size() < 2 || b.size() < 2) return multiply(atmp, btmp);

    int fh = n/2;               // First half

    // First and second half of the first poly
    poly Xl(atmp.begin(), atmp.begin() + fh);
    poly Xr(atmp.begin() + fh, atmp.end());

    // First and second half of the second poly
    poly Yl(btmp.begin(), btmp.begin() + fh);
    poly Yr(btmp.begin() + fh, btmp.end());

    maginatics::ThreadPool threadpool(NUM_THR);
    
    poly P1;
    poly P2;
    poly P3;

    threadpool.execute([=, &P1]() {
        P1 = multiKaraPara(Xl, Yl);
    });

    threadpool.execute([=, &P2]() {
        P2 = multiKaraPara(Xr, Yr);
    });

    threadpool.execute([=, &P3]() {
        P3 = multiKaraPara(add(Xl, Xr), add(Yl, Yr));
    });

    threadpool.drain();

    poly P4 = substr(P3, add(P1, P2));

    poly PE(P1.size() + n);
    poly P1E(P1.size() + n);
    P1E = add(P1, P1E);

    poly P2E(P1.size() + n);
    for(int i = P1E.size() - P1.size(), p = 0; i < P1E.size(); i++, p++)
    {
        P2E[i] = P2[p];
    }

    poly P4E(P4.size() + fh);
    P4E = add(P4, P4E);
    for(int i = PE.size() - P4E.size(), p = 0; i < PE.size(); i++, p++)
    {
        PE[i] = P4E[p];
    }

    poly res = add(add(P1E, P2E), PE);

    return res;
}

#endif