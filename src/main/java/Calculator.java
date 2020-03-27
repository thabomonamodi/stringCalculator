import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Calculator
{
    private static final Logger log = LogManager.getLogger(Calculator.class.getName());

    public static int add(String text)throws NumberFormatException
    {
        String delimeter = "//";
        int result=0;
        if (text.isEmpty())
        {
            result = 0;
        }
        else if (text.contains(","))
        {
            String[] num = text.split(",|\n");
            for (int i = 0; i < num.length; i++)
            {
                result += Integer.parseInt(num[i]);
            }
        }
        else if (text.contains("-"))
        {
            try {
                String[] num = text.split("//-");
                for (int i = 0; i < num.length; i++)
                {
                    result += Integer.parseInt(num[i]);
                }
            }
            catch (NumberFormatException e)
            {
                log.error(e);
            }
        }
        else if (text.startsWith("//"))
        {
            String[] splits = text.split("\n",2);
            delimeter = splits[0].substring(2);
            text = splits[1];
        }
        try (Stream<String> numbers = Arrays.stream(text.split(delimeter))) {
            return numbers.mapToInt(Integer::parseInt).sum();
        }
        catch (NumberFormatException e)
        {
            log.error(e);
        }
        System.out.println(result);
        return result;
    }
    public static void main(String[] args)
    {
        add("1,2");
        add("2\n4,1");
        add("//;\n1;2");
        add("//(-_-')1\n(-_-')2");
        add("-1\n1,2");
    }
}
