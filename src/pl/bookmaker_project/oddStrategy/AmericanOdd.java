package pl.bookmaker_project.oddStrategy;

public class AmericanOdd implements Odd
{
    @Override
    public double convertOdd(double odd)
    {
        if(odd >= 2)
        {
            return (odd-1)*100;
        }
        else
        {
            return (-100)/(odd-1);
        }

    }
}
