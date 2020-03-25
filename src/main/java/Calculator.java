import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Stream;

public class Calculator
{
    private static final Logger log = LogManager.getLogger(Calculator.class.getName());

    public static int add(String text)
    {
        int result=0;
        if (text.isEmpty())
        {
            result = 0;
        }
        else if (text.contains(",") && text.contains("\n"))
        {
            String[] num = text.split(",|\n");
            for (int i = 0; i < num.length; i++)
            {
                result += Integer.parseInt(num[i]);
            }
        }
        /*else if (text.contains("//\\n,!.?:;@#$%^&*()_+=?'<>+]"))
        {
            String[] num = text.split("[//\n,!.?:;@#$%^&*()_+=?'<>+]");
            for (int i = 0; i < num.length; i++)
            {
                result += Integer.parseInt(num[i]);
            }
        }*/

        System.out.println(result);
        return result;
    }
    public static void main(String[] args)
    {
        add("2\n4,1");
        add("//;\n1;2");

    }
}
