#include <vector>
#include <stdlib.h>

int randint(int min, int max)
{
    return min+ int((2* max+ 1)* 1.* rand()/ (RAND_MAX+ 1.));
}

std::vector<int> generate_vector(int size)
{
    std::vector<int> gen_vector;

    for(int i = 0; i < size; ++i)
    {
        gen_vector.push_back(randint(-50, 50));
    }

    return gen_vector;
}