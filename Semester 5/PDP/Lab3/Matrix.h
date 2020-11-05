#ifndef LAB3_MATRIX_H
#define LAB3_MATRIX_H

#include <vector>

typedef std::vector<std::vector<int>> matrixRaw;

struct Matrix {
    matrixRaw m;
    int s;

    Matrix() {
        m = matrixRaw(1, std::vector<int>(1, 0));
        random();
    }

    Matrix(int size):s{size} {
        m = matrixRaw(size, std::vector<int>(size, 0));
        random();
    }

    void random() {
        for(int i = 0; i < s; i ++) 
        {
            for(int j = 0; j < s; j ++)
            {
                m[i][j] = rand() % 100;
            }
        }
    }

    void put(int x, int y, int value) {
        m[x][y] = value;
    }

    int get(int x, int y) {
        return m[x][y];
    }

    int size() 
    {
        return s;
    }

    void print()
    {
        for(int i = 0; i < s; i ++) 
        {
            for(int j = 0; j < s; j ++)
            {
                std::cout<<m[i][j]<<' ';
            }
            std::cout<<std::endl;
        }
    }
};

#endif