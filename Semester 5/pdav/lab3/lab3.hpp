#ifndef LAB3_H
#define LAB3_H

#include <vector>
#include <array>
#include <math.h>
#define vec std::vector<int>
#define entrs std::vector<vector<triplet>>
#define MAX_ENTR 64*3-1
#define byte char
#include "domain.hpp"
#include <string>

struct triplet {
    int size, runlength;
    int x, y;
    float amplitude;

    triplet(int s, float a, int r, int xx, int yy) {
        size = s;
        amplitude = a;
        runlength = r;
        x=xx;
        y=yy;
    }
};

int sizeOf(int amp) {
    return 1 + log2(abs(amp));
}

int byteOf(int number) {
    return sizeOf(number)/8 + (sizeOf(number) < 8);
}

void fillWithBytes(int number, byte arr[]) {
    arr[0] = (byte)(number>>8);
    arr[1] = (byte)(number);
}

int fromBytesToInt(byte* buffer) {
    return int(buffer[1] | buffer[0]<<8);
}

std::vector<triplet> getEntropyEncode(blockN tile) {
    int m = tile._n;
    std::vector<triplet> result;
    int z = 0;
    int value = 0;

    for (int i = 0; i < m + m - 1; i++) {
        if (i % 2 == 1) {
            // down left
            int x = i < m ? 0 : i - m + 1;
            int y = i < m ? i : m - 1;
            while (x < m && y >= 0) {
                value = tile.b[x++][y--];
                if(value == 0) {
                    z++;
                    continue;
                }
                result.push_back(triplet(sizeOf(value), value, z, x-1, y+1));
                z = 0;
            }
        } else {
            // up right
            int x = i < m ? i : m - 1;
            int y = i < m ? 0 : i - m + 1;
            while (x >= 0 && y < m) {
                value = tile.b[x--][y++];
                if(value == 0) {
                    z++;
                    continue;
                }
                result.push_back(triplet(sizeOf(value), value, z, x+1, y-1));
                z = 0;
            }
        }
    }

    // if(result.size() < m+m) {
    //     result.push_back(triplet(sizeOf(0), 0, 0, 0, 0));
    // }

    return result;
}

blockN rebuildBlock(vector<triplet> entropy) {
    blockN result = blockN();

    for(triplet tr:entropy) {
        result.put(tr.x, tr.y, tr.amplitude);
    }

    return result;
}

entrs applyEntropy(channel input, int width, int height) {
    entrs entr_res;

    for(int i = 0; i < height/8; ++i)
    {
        for(int j = 0; j < width/8; j++)
        {
            entr_res.push_back(getEntropyEncode(input[i][j]));
        }
    }

    return entr_res;
}

void reverseEntropy(channel &input, entrs enInt, int width, int height) {
    int p = 0;
    for(int i = 0; i < height/8; ++i)
    {
        for(int j = 0; j < width/8; j++)
        {
            input[i][j] = rebuildBlock(enInt[p++]);
        }
    }
}

#endif