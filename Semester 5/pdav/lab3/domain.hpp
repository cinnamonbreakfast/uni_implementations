#ifndef LAB2_DOMAIN_H
#define LAB2_DOMAIN_H

#include <iostream>
#include <vector>
#include <stdlib.h>

using namespace std;

typedef vector<std::vector<float>> block;

struct blockN {
    block b;
    int _x, _y, _n;
    int x, y;
    float _v;

    blockN():_n{8} {
        b = block(_n, vector<float>(_n, 0));
        _x = _y = 0;
    }

    blockN(int N):_n{N} {
        b = block(_n, vector<float>(_n, 0));
        _x = _y = 0;
    }

    void put(int px, int py, int v) {
        b[px][py] = v;
    }

    void push(int v)
    {
        if(_y == _n)
        {
            _y = 0;
            _x += 1;
        }

        b[_x][_y] = v;

        _y += 1;
    }

    blockN operator - (const blockN &p)  const
    {
        blockN res;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                res.b[i][j] = b[i][j] - p.b[i][j];
            }
        }
        return res;
    }

    blockN operator + (const blockN &p)  const
    {
        blockN res;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                res.b[i][j] = b[i][j] + p.b[i][j];
            }
        }
        return res;
    }

    blockN operator / (const blockN &p)  const
    {
        blockN res;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                res.b[i][j] = b[i][j] / p.b[i][j];
            }
        }
        return res;
    }

    blockN operator ^ (const blockN &p)  const
    {
        blockN res;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                res.b[i][j] = b[i][j] * p.b[i][j];
            }
        }
        return res;
    }

    void print() {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                std::cout<<b[i][j]<<' ';
            }
            cout<<'\n';
        }
    }
};

// Channel is the matrix of 8x8 blocks for YUV or RGB whatever
typedef vector<vector<blockN>> channel;
struct YCbCr {
    vector<vector<blockN>> Y, U, V;
    int width, height;
};

vector<int> YUV_RGB(int Y, int U, int V)
{
    vector<int> rgb(3);

    rgb[0] = Y + 1.402 * (V - 128);
    rgb[1] = Y - 0.344 * (U - 128) - 0.714 * (V - 128);
    rgb[2] = Y + 1.772 * (U - 128);

    return rgb;
}

block create8x8()
{
    return block(8, vector<float>(8, 0));
}



#endif