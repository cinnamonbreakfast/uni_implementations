/*
    Phase 1 :   divide the image in 8x8 blocks
    Phase 2 :   composing the image from a set of x8x pixel blocks

    Phase 1 :
        - read each pixel and convert from RGB to YUV
        - form 3 matrixes: one for Y, for U and V
        - divide the Y matrix into 8x8 blocks; for each block store: 4x4=16 values/bytes from the block, the type of
            block (Y) and the position of the block in the image
        - divide the U and V matrixes into 8x8 blocks; each block stores 44=16 values/bytes from the block, type of
            the block abd position in the image
        - store the list of 8x8 Y blocks and 4x4 U and V blocks and print this list on the screen
*/

#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <iostream>
#include <vector>

using namespace std;

typedef vector<vector<int>> block;

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
    return block(8, vector<int>(8, 0));
}

struct blockN {
    block b;
    int _x, _y, _n;
    int x, y;

    blockN():_n{8} {
        b = block(_n, vector<int>(_n, 0));
        _x = _y = 0;
    }

    blockN(int N):_n{N} {
        b = block(_n, vector<int>(_n, 0));
        _x = _y = 0;
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
};

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

    // if(n == 4) return res;
    // return average_2d(res, n/2);

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

int main(int argc, char* argv[]) {
    fstream input("nt-P3.ppm", ios_base::in);
    fstream output("nt-P3-YUV.ppm", ios_base::out);

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

    output<<"P3\n";
    output<<width<<" "<<height<<'\n';
    output<<max_r<<'\n';

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

    vector<int> rgb(3);

    for(i = 0; i < height; ++i)
    {
        for(j = 0; j < width; j++)
        {
            // yMatrices, uBackMatrices, vBackMatrices
            rgb = YUV_RGB(yMatrices[i/8][j/8].b[i%8][j%8], uBackMatrices[i/8][j/8].b[i%8][j%8], vBackMatrices[i/8][j/8].b[i%8][j%8]);
            output<<rgb[0]<<'\n'<<rgb[1]<<'\n'<<rgb[2]<<'\n';
        }
    }

    return 0;
}