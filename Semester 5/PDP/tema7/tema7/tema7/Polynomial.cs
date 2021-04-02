using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace tema7
{
    [Serializable]
    public class Polynomial
    {
        public int Degree { get; set; }
        public int[] Coefficients { get; set; }
        public int Size = 0;

        public Polynomial(int s)
        {
            Degree = s;
            this.Size = s + 1;
            Coefficients = new int[this.Size];
        }

        public void GenerateRandom()
        {
            Random rnd = new Random();

            for (int i = 0; i < this.Size; i++)
            {
                Coefficients[i] = rnd.Next(-10, 10);
                if (i == this.Size - 1)
                {
                    while (Coefficients[i] == 0)
                    {
                        Coefficients[i] = rnd.Next(-10, 10);
                    }
                }
            }
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();

            for (int i = this.Size - 1; i >= 0; i--)
            {
                if (Coefficients[i] != 0)
                {
                    if (Coefficients[i] < 0)
                    {
                        sb.Append(Coefficients[i]);
                    }
                    else if (Coefficients[i] > 0)
                    {
                        if (i < this.Size - 1)
                        {
                            sb.Append("+");
                        }
                        sb.Append(Coefficients[i]);
                    }


                    if (i == 1)
                    {
                        sb.Append("*");
                        sb.Append("X");
                    }
                    else if (i != 0)
                    {
                        sb.Append("*");
                        sb.Append("X^");
                        sb.Append(i);
                    }

                }
            }

            return sb.ToString();
        }

        internal Polynomial GetLast(int m)
        {
            Polynomial result = new Polynomial(m - 1);

            for (int i = 0; i < m; i++)
            {
                result.Coefficients[i] = Coefficients[i];
            }

            return result;

        }

        internal Polynomial GetFirst(int m)
        {
            Polynomial result = new Polynomial(m - 1);

            int k = 0;

            for (int i = this.Size - m; i < this.Size; i++)
            {
                result.Coefficients[k] = Coefficients[i];
                k++;
            }

            return result;
        }

        internal Polynomial Sum(Polynomial b)
        {
            int size1 = this.Size;
            int size2 = b.Size;

            int sizeMax = (size1 > size2) ? size1 : size2;

            Polynomial result = new Polynomial(sizeMax - 1);

            for (int i = 0; i < sizeMax; i++)
            {
                int res = 0;
                if (i < size1)
                {
                    res = res + Coefficients[i];
                }
                if (i < size2)
                {
                    res = res + b.Coefficients[i];
                }
                result.Coefficients[i] = res;
            }

            return result;
        }

        internal Polynomial AddZerosLeft(int v)
        {
            int[] newCoef = new int[this.Size + v];

            for (int i = 0; i < Size; i++)
            {
                newCoef[i] = Coefficients[i];
            }
            for (int i = this.Size; i < this.Size + v; i++)
            {
                newCoef[i] = 0;
            }

            Coefficients = newCoef;
            this.Size = Coefficients.Length;

            return this;
        }

        internal Polynomial AddZerosRight(int v)
        {
            Polynomial result = new Polynomial(Size + v - 1);

            for (int i = v; i < Size + v; i++)
            {
                result.Coefficients[i] = Coefficients[i - v];
            }

            return result;
        }

        internal Polynomial Difference(Polynomial b)
        {
            int size1 = this.Size;
            int size2 = b.Size;

            int sizeMax = (size1 > size2) ? size1 : size2;

            Polynomial result = new Polynomial(sizeMax - 1);

            for (int i = 0; i < sizeMax; i++)
            {
                int res = 0;
                if (i < size1)
                {
                    res = Coefficients[i];
                }
                if (i < size2)
                {
                    res = res - b.Coefficients[i];
                }
                result.Coefficients[i] = res;
            }

            return result;
        }
    }
}