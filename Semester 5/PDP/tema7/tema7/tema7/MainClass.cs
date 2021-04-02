using MPI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace tema7
{
    class MainClass
    {
        public static Polynomial ComputeFinalResult(Polynomial[] results)
        {
            Polynomial result = new Polynomial(results[0].Degree);

            for (int i = 0; i < result.Size; i++)
                for (int j = 0; j < results.Length; j++)
                    result.Coefficients[i] += results[j].Coefficients[i];

            return result;
        }

        public static void MPIMultiplication(Polynomial polynomial1, Polynomial polynomial2)
        {
            DateTime start = DateTime.Now;

            int n = Communicator.world.Size;
            int begin = 0;
            int end = 0;
            int length = polynomial1.Size / (n - 1);

            for (int i = 1; i < n; i++)
            {
                begin = end;
                end = end + length;
                if (i == n - 1)
                    end = polynomial1.Size;

                Communicator.world.Send(polynomial1, i, 0);
                Communicator.world.Send(polynomial2, i, 0);
                Communicator.world.Send(begin, i, 0);
                Communicator.world.Send(end, i, 0);
            }

            Polynomial[] results = new Polynomial[n - 1];

            for (int i = 1; i < n; i++)
                results[i - 1] = Communicator.world.Receive<Polynomial>(i, 0);

            Polynomial result = ComputeFinalResult(results);

            double time = (DateTime.Now - start).Milliseconds;
            Console.WriteLine("Basic Multiplication:");
            Console.WriteLine("\tResult: " + result.ToString());
            Console.WriteLine("\tTime: " + time.ToString() + "ms");
        }

        public static void MPIMultiplicationWorker()
        {
            //Console.WriteLine("Child");
            Polynomial polynomial1 = Communicator.world.Receive<Polynomial>(0, 0);
            Polynomial polynomial2 = Communicator.world.Receive<Polynomial>(0, 0);

            int begin = Communicator.world.Receive<int>(0, 0);
            int end = Communicator.world.Receive<int>(0, 0);

            Polynomial result = Utils.MPIMultiply(polynomial1, polynomial2, begin, end);

            Communicator.world.Send(result, 0, 0);
        }
        public static void MPIKaratsuba(Polynomial polynomial1, Polynomial polynomial2)
        {
            DateTime start = DateTime.Now;

            Polynomial result = new Polynomial(polynomial1.Degree * 2);
            if (Communicator.world.Size == 1)
            {
                result = Utils.AsynchronousKaratsubaMultiply(polynomial1, polynomial2);
            }
            else
            {
                Communicator.world.Send<int>(0, 1, 0);
                Communicator.world.Send<int[]>(polynomial1.Coefficients, 1, 0);
                Communicator.world.Send<int[]>(polynomial2.Coefficients, 1, 0);
                if (Communicator.world.Size == 2)
                    Communicator.world.Send<int[]>(new int[0], 1, 0);
                else
                    Communicator.world.Send<int[]>(Enumerable.Range(2, Communicator.world.Size - 2).ToArray(), 1, 0);

                int[] coefs = Communicator.world.Receive<int[]>(1, 0);
                result.Coefficients = coefs;
            }

            double time = (DateTime.Now - start).Milliseconds;
            Console.WriteLine("Karatsuba: ");
            Console.WriteLine("\tResult: " + result.ToString());
            Console.WriteLine("\tTime: " + time.ToString());
        }

        public static void MPIKaratsubaWorker()
        {
            Utils.MPIKaratsubaMultiply();
        }

        static void Main(string[] args)
        {
            using (new MPI.Environment(ref args))
            {
                if (Communicator.world.Rank == 0)
                {
                    int totalProcessors = Communicator.world.Size - 1;

                    // Polynomials size
                    int Poly1Size = 7;
                    int Poly2Size= 7;
                    Polynomial Poly1 = new Polynomial(Poly1Size);
                    Poly1.GenerateRandom();

                    Thread.Sleep(500);

                    Polynomial Poly2 = new Polynomial(Poly2Size);
                    Poly2.GenerateRandom();

                    // make them equal in size
                    if (Poly1Size > Poly2Size)
                        Poly2 = Poly2.AddZerosLeft(Poly1Size - Poly2Size);
                    
                    if (Poly1Size < Poly2Size)
                        Poly1 = Poly1.AddZerosLeft(Poly2Size - Poly1Size);

                    Console.WriteLine("Poly1 of size " + Poly1.Size + " and degree " + Poly1.Degree);
                    Console.WriteLine(Poly1.ToString());

                    Console.WriteLine();

                    Console.WriteLine("Poly2 of size " + Poly2.Size + " and degree " + Poly2.Degree);
                    Console.WriteLine(Poly2.ToString());

                    Console.WriteLine();

                    // Start basic mul
                    MPIMultiplication(Poly1, Poly2);

                    Console.WriteLine();

                    // Start Karatsuba mul
                    MPIKaratsuba(Poly1, Poly2);
                }
                else
                {
                    MPIMultiplicationWorker();
                    MPIKaratsubaWorker();
                }
            }
        }
    }
}