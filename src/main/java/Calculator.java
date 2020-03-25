import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculator
{
    private static final Logger log = LogManager.getLogger(Calculator.class.getName());
    //private static CharSequence newLine;
    public static String newLine = "\n";
    /*public static int add(String num)
    {
        if(num.isEmpty())
        {
            return  0;
        }
        else
        {
            return Integer.parseInt(num);
        }
    }*/
    public static int add(String text)
    {
        int result=0;
        if (text.isEmpty())
        {
            result = 0;
        }
        else if (text.contains(",") )
        {
            String[] num = text.split(",|\n");
            for (int i = 0; i < num.length; i++)
            {
                result += Integer.parseInt(num[i]);
            }
        }
        else if (text.contains("\n"))
        {
            String[] num = text.split("\n");
            for (int i = 0; i < num.length; i++)
            {
                result += Integer.parseInt(num[i]);
            }
        }
        System.out.println(result);
        return result;
    }
    public static void main(String[] args)
    {
        add("1\n2\n4,1");
    }
}
