package homework;

public class Rosalind {

    public static long fib(int input) {
        if (input <= 1) return input ;
        return fib(input - 1) + fib(input - 2);
    }


}