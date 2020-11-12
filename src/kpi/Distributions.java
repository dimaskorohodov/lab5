package ua.kpi;

import java.util.Random;

public class Distributions {
    public static double exp(double timeMean)
    {
        Random random = new Random();
        double a = 0;
        while (a == 0)
        {
            a = Math.random();
        }
        a = -timeMean * Math.log(a);

        return a;
    }

    public static double uniform(double timeMin, double timeMax)
    {
        Random random = new Random();
        double a = 0;
        while (a == 0)
        {
            a = Math.random();
        }
        a = timeMin + a * (timeMax - timeMin);

        return a;
    }

    public static double norm(double timeMean, double timeDeviation)
    {
        double a;
        Random random = new Random();
        double u1 = 1.0 - Math.random();
        double u2 = 1.0 - Math.random();
        double randStdNormal = Math.sqrt(-2.0 * Math.log(u1)) * Math.sin(2.0 * Math.PI * u2);
        a = timeMean + timeDeviation * randStdNormal;

        return a;
    }

    public static double erlang(double timeMean, double timeDeviation)
    {
        double a = -1 / timeDeviation;
        double[] R = new double[] { 0.43, 0.80, 0.29, 0.67, 0.19, 0.96, 0.02, 0.73, 0.50, 0.33, 0.14, 0.71 };
        double r = 1;
        for (int i = 0; i < (int)timeMean; i++)
        {
            r *= R[i];
        }
        a *= Math.log(r);
        return a;
    }
}

