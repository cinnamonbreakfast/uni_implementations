#ifndef LAB3_METHODS_H
#define LAB3_METHODS_H

#include "Matrix.h"

int compute_cell(int i, int j, Matrix m1, Matrix m2)
{
    int s = 0;

    for(int k = 0; k < m1.size(); k++)
    {
        s += m1.get(i, k) * m2.get(k, j);
    }

    return s;
}

void split1(int nr, int start_i, int start_j, Matrix m1, Matrix m2, Matrix& result) {
    int n = m1.size();

    for (int i = start_i; i < n; i++) {
        for (int j = start_j; j < n; j++) {

            auto calc = compute_cell(i, j, m1, m2);
            result.put(i, j, calc);

            nr--;

            if (nr == 0) {
                return;
            }

        }
        start_j = 0;
    }
}

void split2(int nr, int start_i, int start_j, Matrix m1, Matrix m2, Matrix& result) {
    int n = m1.size();

    for (int j = start_j; j < n; j++) {
        for (int i = start_i; i < n; i++) {

            auto calc = compute_cell(i, j, m1, m2);
            result.put(i, j, calc);

            nr--;

            if (nr == 0) {
                return;
            }

        }
        start_i = 0;
    }
}

void split3(int index, int k, Matrix m1, Matrix m2, Matrix& result) {

    int i = 0 + index / m1.size();
    int j = 0 + index % m1.size();

    while (i < m1.size()) {
        while (j < m1.size()) {

            auto calc = compute_cell(i, j, m1, m2);
            result.put(i, j, calc);
            j += k;
            
        }

        i += j / m1.size();
        j %= m1.size();
    }
}

#endif