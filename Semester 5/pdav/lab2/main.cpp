#include <iostream>
#include <fstream>

#include "domain.hpp"
#include "YUV.hpp"

using namespace std;

int main(int argc, char* argv[])
{
    fstream output("photo_out.ppm", ios_base::out);
    // 1. Read the file and get the YUV (+ reverse subsampling, so we have 8x8 blocks anywhere)
    char input_ppm[] = "nt-P3.ppm";
    char output_ppm[] = "out.ppm";
    YCbCr lab1 = RGBtoYUV(input_ppm, output_ppm);

    char quant_file[] = "quantization_matrix.in";
    char substr_file[] = "1.in";

    blockN quant = readQuantMatrix(quant_file);
    blockN subst = readQuantMatrix(substr_file);

    channel Y = sub128(lab1.Y, lab1.width, lab1.height, subst);
    channel U = sub128(lab1.U, lab1.width, lab1.height, subst);
    channel V = sub128(lab1.V, lab1.width, lab1.height, subst);

    Y = forwardDCT(Y, lab1.width, lab1.height);
    U = forwardDCT(U, lab1.width, lab1.height);
    V = forwardDCT(V, lab1.width, lab1.height);

    // // DECODING
    channel Y_dec;
    channel U_dec;
    channel V_dec;

    Y_dec = quantization(Y, lab1.width, lab1.height, quant);
    U_dec = quantization(U, lab1.width, lab1.height, quant);
    V_dec = quantization(V, lab1.width, lab1.height, quant);

    Y_dec = quantization(Y_dec, lab1.width, lab1.height, quant, true);
    U_dec = quantization(U_dec, lab1.width, lab1.height, quant, true);
    V_dec = quantization(V_dec, lab1.width, lab1.height, quant, true);

    Y_dec = inverseDCT(Y_dec, lab1.width, lab1.height);
    U_dec = inverseDCT(U_dec, lab1.width, lab1.height);
    V_dec = inverseDCT(V_dec, lab1.width, lab1.height);


    add128(Y_dec, lab1.width, lab1.height, subst);
    add128(U_dec, lab1.width, lab1.height, subst);
    add128(V_dec, lab1.width, lab1.height, subst);

    vector<int> rgb(3);

    output<<"P3\n";
    output<<lab1.width<<" "<<lab1.height<<'\n';
    output<<255<<'\n';
    for(int i = 0; i < lab1.height; ++i)
    {
        for(int j = 0; j < lab1.width; j++)
        {
            // yMatrices, uBackMatrices, vBackMatrices
            rgb = YUV_RGB(
                Y_dec[i/8][j/8].b[i%8][j%8],
                U_dec[i/8][j/8].b[i%8][j%8],
                V_dec[i/8][j/8].b[i%8][j%8]
            );
            output<<rgb[0]<<'\n'<<rgb[1]<<'\n'<<rgb[2]<<'\n';
        }
    }
    return 0;
}

int main3() 
{
    fstream input("q1.in", ios_base::in);

    int Y, U, V;

    blockN y, u, v;

    while(input>>Y>>U>>V)
    {
        y.push(Y);
        u.push(U);
        v.push(V);
    }

    int i, j;

    for(i = 0; i < 8; i++)
    {
        for(j = 0; j < 8; j++)
        {
            cout<<y.b[i][j]<<' ';
        }
        cout<<endl;
    }

    cout<<endl<<endl;

    // for(i = 0; i < 8; i++)
    // {
    //     for(j = 0; j < 8; j++)
    //     {
    //         cout<<u.b[i][j]<<' ';
    //     }
    //     cout<<endl;
    // }

    // cout<<endl<<endl;

    // for(i = 0; i < 8; i++)
    // {
    //     for(j = 0; j < 8; j++)
    //     {
    //         cout<<v.b[i][j]<<' ';
    //     }
    //     cout<<endl;
    // }

    // cout<<endl<<endl;

    char qmx[] = "quantization_matrix.in";
    char m128[] = "128.in";

    blockN quant = readQuantMatrix(qmx);
    blockN mat128 = readQuantMatrix(m128);

    y = y - mat128;
    // u = u - mat128;
    // u = u - mat128;

    for(i = 0; i < 8; i++)
    {
        for(j = 0; j < 8; j++)
        {
            cout<<y.b[i][j]<<' ';
        }
        cout<<endl;
    }

    cout<<endl<<endl;

    y = applySigma1(y);

    for(i = 0; i < 8; i++)
    {
        for(j = 0; j < 8; j++)
        {
            cout<<y.b[i][j]<<' ';
        }
        cout<<endl;
    }

    cout<<endl<<endl;

    y = y / quant;

    for(i = 0; i < 8; i++)
    {
        for(j = 0; j < 8; j++)
        {
            cout<<y.b[i][j]<<' ';
        }
        cout<<endl;
    }

    cout<<endl<<endl;

    y = y ^ quant;

    for(i = 0; i < 8; i++)
    {
        for(j = 0; j < 8; j++)
        {
            cout<<y.b[i][j]<<' ';
        }
        cout<<endl;
    }

    cout<<endl<<endl;

    y = applyInvSigma(y);

    for(i = 0; i < 8; i++)
    {
        for(j = 0; j < 8; j++)
        {
            cout<<y.b[i][j]<<' ';
        }
        cout<<endl;
    }

    cout<<endl<<endl;

    y = y + mat128;

    for(i = 0; i < 8; i++)
    {
        for(j = 0; j < 8; j++)
        {
            cout<<y.b[i][j]<<' ';
        }
        cout<<endl;
    }

    cout<<endl<<endl;

    // for(i = 0; i < 8; i++)
    // {
    //     for(j = 0; j < 8; j++)
    //     {
    //         cout<<u.b[i][j]<<' ';
    //     }
    //     cout<<endl;
    // }

    // cout<<endl<<endl;

    // for(i = 0; i < 8; i++)
    // {
    //     for(j = 0; j < 8; j++)
    //     {
    //         cout<<v.b[i][j]<<' ';
    //     }
    //     cout<<endl;
    // }

    // cout<<endl<<endl;

}