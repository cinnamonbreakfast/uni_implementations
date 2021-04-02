#include <iostream>
#include <fstream>
#include <bitset>

#include "domain.hpp"
#include "YUV.hpp"
#include "lab3.hpp"

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

    entrs Y_entrs = applyEntropy(Y_dec, lab1.width, lab1.height);
    entrs U_entrs = applyEntropy(U_dec, lab1.width, lab1.height);
    entrs V_entrs = applyEntropy(V_dec, lab1.width, lab1.height);

    channel Y_pe(lab1.height/8, vector<blockN>(lab1.width/8, blockN{8})),
            U_pe(lab1.height/8, vector<blockN>(lab1.width/8, blockN{8})),
            V_pe(lab1.height/8, vector<blockN>(lab1.width/8, blockN{8}));
    reverseEntropy(Y_pe, Y_entrs, lab1.width, lab1.height);
    reverseEntropy(U_pe, U_entrs, lab1.width, lab1.height);
    reverseEntropy(V_pe, V_entrs, lab1.width, lab1.height);

    Y_dec = quantization(Y_pe, lab1.width, lab1.height, quant, true);
    U_dec = quantization(U_pe, lab1.width, lab1.height, quant, true);
    V_dec = quantization(V_pe, lab1.width, lab1.height, quant, true);

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


// int main(int argc, char* argv[])
// {
//     char test_parze[] = "stuff/example.in";
//     blockN quant = readQuantMatrix(test_parze);
    
//     vector<triplet> res = getEntropyEncode(quant);

//     for(int i = 0; i < res.size(); i++) {
//         cout<<res[i].size<<' '<<res[i].runlength<<'/'<<res[i].amplitude<<'\n';
//     }

//     blockN test = rebuildBlock(res);    

//     test.print();

// //     // cout<<endl<<endl;

// //     // byte a = (byte)(513>>8);
// //     // byte b = (byte)(513);
// //     // int y = (int)(a<<8|b);
// //     // cout<<y<<'\n';

    
// //     // cout<<sizeOf(150)<<'\n';

// //     int rep = -512;

// //     // byte res[4];
// //     // // fillWithBytes(res, 0, 150);
// //     // res[0] = (byte)(rep>>8);
// //     // res[1] = (byte)(rep);
// //     // res[2] = (byte)(rep);
// //     // // res[3] = (byte)(-600);
// //     // // byte a = res[0];
    
// //     // byte test = res[0] | (rep < 0 ? 0b00000100 : 0);
// //     // byte sign = ((test>>0)%2?0b00000000:0b11111100);

// //     // int nr = (int)(
// //     //     test<<8 |
// //     //     res[1] |
// //     //     sign
// //     // );

    
// //     // cout<<bitset<8>(res[0])<<' '<<res[1]%2<<' '<<bitset<8>(test)<<' '<<bitset<8>(0b00000001|0b00000001)<<"\n\n\n";
// //     // cout<<bitset<8>(res[1])<<"\n\n\n";

// //     // cout<<nr<<'\n';

// //     // cout<<int(res[1] | res[0]<<8)<<endl;

// //     byte res[2];
// //     for(int i = -1023; i < 1024; i++) {
// //         fillWithBytes(i, res);
// //         if(fromBytesToInt(res) != i) {
// //             cout<<"err at "<<i<<", resulted "<<fromBytesToInt(res)<<'\n';
// //             break;
// //         }
// //     }

//     return 0;
// }