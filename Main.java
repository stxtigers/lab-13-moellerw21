import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Main
{
  public static void main(String[] args) throws IOException
  {
   /* 
    Scanner reader = new Scanner(System.in);

    int[] testers = new int[10];
    for(int i = 0; i < testers.length; i++)
    {
      System.out.println("Enter an int: ");
      testers[i] = reader.nextInt();
    }
  */
  
    //WORKS
    //Tests insert
    //System.out.println("The array with hello entered is: ");
    //printArray(insert("hello", 5, testers, 5));

    //WORKS
    //tests delete
    //System.out.println("the array with the 10th int removed is: ");
    //printIntArray(delete(9, testers, 10));
    
    
    //WORKS
    //tests stats
    //stats("sample.txt");
  }

  public static String[] insert(String s, int i, String[] a, int logicalSize)
  {
    if(logicalSize == a.length)
    {
      return null;
    }

    for(int j = a.length - 1; j > i; j--)
    {
      a[j] = a[j-1];
    }

    a[i] = s;
    return a;
  }

  public static int[] delete(int target, int[] a, int logicalSize)
  {
    a[target] = 0;

    for(int i = target; i < a.length - 1; i++)
    {
      a[i] = a[i+1];
    }

    return a;

  }

  public static void stats(String filename) throws IOException
  {
    Scanner fileReader = new Scanner(new File(filename));
    double[] buffer = new double[5000];
    int count = 0;


    while(fileReader.hasNext())
    {
      buffer[count] = fileReader.nextDouble();
      count++;
    }

    double[] numbers = Arrays.copyOf(buffer, count);
    buffer = null; //allow the buffer to be garbage collector
    
    
    

    //NEED TO WRITE REST
    
    PrintWriter writer = new PrintWriter(new File("stats.txt"));
    writer.println("Mean: \t" + average(numbers));
    writer.println("Median: \t" + findMedian(numbers));
    writer.println("Mode: \t" + findMode(numbers));
    
    writer.close();
    
  }






  //Helper methods:
  private static void printArray(String[] a)
  {
    for(int i = 0; i < a.length; i++)
    {
      System.out.println(a[i]);
    }
  }

  private static void printIntArray(int[] a)
  {
    for(int i = 0; i < a.length; i++)
    {
      System.out.println(a[i]);
    }
  }
  
  private static double average(double[] numbers)
  {
    double average = 0;
    for(int i = 0; i < numbers.length; i++)
    {
      average += numbers[i];
    }
    average = average/numbers.length;
    return average;
  }
  
  public static double stdev(double[] a)
  {
    double answer = 0;
    double firstMean = average(a);

    double[] originalMinusMean = new double[a.length];
    for(int i = 0; i < a.length; i++)
    {
      originalMinusMean[i] = a[i] - firstMean;
    }

    double[] origMinusMeanSquared = new double[a.length];
    for(int i = 0; i < a.length; i++)
    {
      origMinusMeanSquared[i] = originalMinusMean[i] * originalMinusMean[i];
    }

    double secondMean = average(origMinusMeanSquared);
    answer = Math.sqrt(secondMean);
    return answer;
  }
  
   private static double findMedian(double[] numbers)
  {
    double[] sortedList = sort(numbers);
    //if odd number of terms, take number of terms/2
    if(sortedList.length % 2 ==1)
    {
      return sortedList[sortedList.length/2];
    }
    else
    {
      return (double)(sortedList[(sortedList.length - 1)/2] + sortedList[sortedList.length/2]) / 2;
    }
  }
  
  private static double[] sort(double[] numbers)
  {
    double[] sortedArray = copyArray(numbers);
    
    //BUBBLE SORT
    for(int j = sortedArray.length -1; j >= 2; j--)
    {
      for(int i = 0; i < j; i++)
      {
        if(sortedArray[i] > sortedArray[i+1]) //swap
        {
          double temp = sortedArray[i];
          sortedArray[i] = sortedArray[i+1];
          sortedArray[i+1] = temp;
        }
      }
    }
    
    return sortedArray;
  }

  private static double[] copyArray(double[] numbers)
  {
    double[] copy = new double[numbers.length];
    
    for(int i=0; i < numbers.length; i++)
    {
      copy[i] = numbers[i];
    }
    
    return copy;
  }
  
  private static double findMode(double[] numbers)
  {
    //Hold the index of the current most frequent number & number of times it appears
    double mostFrequentNumber = numbers[0];
    int numberOfTimes = 1;
    int[] frequency = new int[numbers.length];

    //Check to see how many times each of the remaing numbers appears
    for(int i = 0; i < numbers.length; i++)
    {
      int count = 0;
      for(int j = 0; j < numbers.length; j++)
      {
        if(numbers[i] == numbers[j])
        {
          count++;
        }
      }

      frequency[i] = count;
      //check if numbers[i] appears more often then the current mostFrequentNumber
      if (count > numberOfTimes)
      {
        numberOfTimes = count;
        mostFrequentNumber = numbers[i];
      }
    }


    return mostFrequentNumber;

  }

} 