#ifndef LAB3_METHODS_H
#define LAB3_METHODS_H
// #define _USE_MATH_DEFINES

#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <vector>
#include <math.h>

#define PI atan(1)*4

#include "domain.hpp"

using namespace std;

blockN average_2d(blockN B, int n) {
    blockN res = (n == 2 ? blockN(2) : blockN(n/2));
    int avg, i, j;

    for(int i = 0; i < B._n; i+=2)
    {
        for(int j = 0; j < B._n; j+=2)
        {
            avg = (B.b[i][j] + B.b[i][j+1] + B.b[i+1][j] + B.b[i+1][j+1]) / 4;
            res.b[i/2][j/2] = avg;
        }
    }

    return res;
}

blockN up_more(blockN b)
{
    blockN res = blockN(b._n*2);
    int i, j;

    for(i = 0; i < b._n*2; i += 2)
    {
        for(j = 0; j < b._n*2; j += 2)
        {
            res.b[i][j] = b.b[i/2][j/2];
            res.b[i+1][j] = b.b[i/2][j/2];
            res.b[i][j+1] = b.b[i/2][j/2];
            res.b[i+1][j+1] = b.b[i/2][j/2];
        }
    }

    return res;
}

void printBlock(blockN b)
{
    int i, j;

    for(i = 0; i < b._n; ++i)
    {
        for(j = 0; j < b._n; ++j)
        {
            cout<<b.b[i][j]<<' ';
        }
        cout<<'\n';
    }
}

YCbCr RGBtoYUV(char* reading, char* writing) {
    // "nt-P3.ppm"
    fstream input(reading, ios_base::in);
    // fstream output(writing, ios_base::out);

    char format[20];

    input>>format;

    cout<<"File format "<<format<<endl;
    int width, height, max_r;

    input>>width>>height;

    cout<<"Size: "<<width<<" "<<height<<endl;

    vector<int> Yvector;
    vector<int> Uvector;
    vector<int> Vvector;

    int R, G, B;
    int Y, U, V;

    input>>max_r;

    // output<<"P3\n";
    // output<<width<<" "<<height<<'\n';
    // output<<max_r<<'\n';

    vector<vector<blockN>> yMatrices (height/8, vector<blockN>(width/8, blockN(8)));
    vector<vector<blockN>> uMatrices (height/8, vector<blockN>(width/8, blockN{8}));
    vector<vector<blockN>> vMatrices (height/8, vector<blockN>(width/8, blockN{8}));

    int i = 0, j = 0;

    while(input>>R>>G>>B)
    {
        Y = 0.229 * R + 0.587 * G + 0.114 * B;
        U = 128 - 0.1687 * R - 0.3312 * G + 0.5 * B;
        V = 128 + 0.5 * R - 0.4186 * G - 0.0813 * B;

        yMatrices[i/8][j/8].push(Y);
        uMatrices[i/8][j/8].push(U);
        vMatrices[i/8][j/8].push(V);

        j++;
        if(j == width) { i++; j = 0; } // next line
    }

    vector<vector<blockN>> u2Matrices (height/8, vector<blockN>(width/8, blockN{4}));
    vector<vector<blockN>> v2Matrices (height/8, vector<blockN>(width/8, blockN{4}));

    for(i = 0; i < height/8; i++)
    {
        for(j = 0; j < width/8; j++)
        {
            u2Matrices[i][j] = average_2d(uMatrices[i][j], 8);
            v2Matrices[i][j] = average_2d(vMatrices[i][j], 8);
        }
    }

    // up until here is done. Now decoding

    vector<vector<blockN>> uBackMatrices (height/8, vector<blockN>(width/8, blockN{8}));
    vector<vector<blockN>> vBackMatrices (height/8, vector<blockN>(width/8, blockN{8}));

    for(i = 0; i < height/8; i++)
    {
        for(j = 0; j < width/8; j++)
        {
            uBackMatrices[i][j] = up_more(uMatrices[i][j]);
            vBackMatrices[i][j] = up_more(vMatrices[i][j]);
        }
    }

    // vector<int> rgb(3);

    // for(i = 0; i < height; ++i)
    // {
    //     for(j = 0; j < width; j++)
    //     {
    //         // yMatrices, uBackMatrices, vBackMatrices
    //         rgb = YUV_RGB(yMatrices[i/8][j/8].b[i%8][j%8], uBackMatrices[i/8][j/8].b[i%8][j%8], vBackMatrices[i/8][j/8].b[i%8][j%8]);
    //         output<<rgb[0]<<'\n'<<rgb[1]<<'\n'<<rgb[2]<<'\n';
    //     }
    // }

    YCbCr result;
    result.Y = yMatrices;
    result.U = uBackMatrices;
    result.V = vBackMatrices;
    result.width = width;
    result.height = height;

    return result;
}

channel sub128(channel input, int width, int height, blockN sub) {
    channel result = input;

    for(int i = 0; i < height/8; ++i)
    {
        for(int j = 0; j < width/8; j++)
        {
            result[i][j] = input[i][j] = result[i][j] - sub;
        }
    }

    return result;
}

channel add128(channel &input, int width, int height, blockN add) {
    channel result = input;

    for(int i = 0; i < height/8; ++i)
    {
        for(int j = 0; j < width/8; j++)
        {
            result[i][j] = input[i][j] = result[i][j] + add;
        }
    }

    return result;
}

blockN applySigma1(blockN in)
{
    blockN res, inblock = in;

    for(int u = 0; u < 8; u++)
    {
        for(int v = 0; v < 8; v++)
        {
            float val = 0.25 * (u == 0 ? 1/sqrt(2) : 1) * (v == 0 ? 1/sqrt(2) : 1);
            int sum = 0;

            for(int x = 0; x < 8; ++x)
            {
                for(int y = 0; y < 8; ++y)
                {
                    sum += inblock.b[x][y] 
                        * cos(((2*x+1)*(u)*PI)/16) 
                        * cos(((2*y+1)*(v)*PI)/16);
                }
            }
            // cout<<">"<<sum<<"<";
            res.b[u][v] = val*sum;
        }
    }

    return res;
}

channel forwardDCT(channel input, int width, int height) {
    channel result = input;

    for(int i = 0; i < height/8; ++i)
    {
        for(int j = 0; j < width/8; j++)
        {
            result[i][j] = applySigma1(result[i][j]);
        }
    }

    return result;
}

blockN readQuantMatrix(char* filename)
{
    blockN result;
    fstream input(filename, ios_base::in);

    int val;

    while(input>>val)
    {
       result.push(val);
    }

    return result;
}

channel quantization(channel input, int width, int height, blockN quantum, bool reverse = false)
{
    channel res = input;

    if(reverse) {
        cout<<"^"<<endl;
        for(int i = 0; i < height/8; ++i)
        {
            for(int j = 0; j < width/8; j++)
            {
                res[i][j] = input[i][j] ^ quantum;
            }
        }
    } else {
        cout<<"/"<<endl;
        for(int i = 0; i < height/8; ++i)
        {
            for(int j = 0; j < width/8; j++)
            {
                res[i][j] = input[i][j] / quantum;
            }
        }
    }

    return res;
}

blockN applyInvSigma(blockN input)
{
    blockN res = input;

    for(int x = 0; x < 8; x++)
    {
        for(int y = 0; y < 8; y++)
        {
            float val = 0.25;
            int sum = 0;

            for(int u = 0; u < 8; ++u)
            {
                for(int v = 0; v < 8; ++v)
                {
                    sum +=  (u == 0 ? 1/sqrt(2) : 1)
                            * (v == 0 ? 1/sqrt(2) : 1)
                            * input.b[u][v]
                            * cos(((2*(x)+1)*(u)*PI)/16)
                            * cos(((2*(y)+1)*(v)*PI)/16);
                }
            }
            val *= sum;
            
            res.b[x][y] = val;
        }
    }

    return res;
}

channel inverseDCT(channel input, int width, int height) {
    // channel result = input;
    channel result = input;

    for(int i = 0; i < height/8; i++)
    {
        for(int j = 0; j < width/8; j++)
        {
            result[i][j] = applyInvSigma(input[i][j]);
        }
    }

    return result;
}

#endif