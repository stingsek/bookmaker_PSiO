package pl.bookmaker_project.oddStrategy;

public class BritishOdd implements Odd
{
    @Override
    public double convertOdd(double odd)
    {
       return odd - 1;
    }
}
