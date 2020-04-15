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
        String[] dilemeter = {".*([,\n;*])*."};
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
                result += parseInt(num[i]);
            }
        }
        else if (text.contains("-")||text.startsWith("-"))
        {
            try
            {
                String[] num = text.split(",");
                for (int i = 0; i < num.length; i++)
                {
                    result += parseInt(num[i]);
                }
            }
            catch (NumberFormatException e)
            {
                log.error(e);
            }
        }
        else if (text.startsWith("//"))
        {
            String[] splits = text.split(";|\n",2);
            Matcher matcher = Pattern.compile("//(.)\n(.);?1000").matcher(text);
            matcher.matches();

            text = splits[1];
//            delimeter = splits[0].substring(2);
//            text = splits[1];
        }
        try
        {
            String[] input = String.valueOf(dilemeter).split(text);
            for (String i : input)
            {
                String[] SplitIndex = i.split(",");
                for (String num2 : SplitIndex)
                {
                    if (parseInt(num2) >= 1000)
                    {
                        continue;
                    }
                    result += parseInt(num2);
                }
            }
        }
        catch (Exception e)
        {
            log.error(e);
        }
        try (Stream<String> numbers = Arrays.stream(text.split(delimeter)))
        {
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
        add("1,2");//good
        add("2\n4,1");//good
        add("//;\n1;2");
        add("//(-_-')1\n(-_-')2");
        add("//[(-_-')][%]\n1(-_-')2%3");
        add("//[***][%%%]\n1***2%%%3");
        add("//[(-_-')][%]\n1(-_-')2%3");
        add("//[abc][777][:(]\n1abc27773:(1");
        add("-1\n1,2");
    }
}

//import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Calculator {
//    public static int add(String input) throws Exception{
//        String msg = "ERROR: negatives not allowed ";
//        String[] sequence;
//        int total = 0;
//        if(input.isEmpty()) {
//            return total;
//        }
//        ArrayList<Integer> negatives = new ArrayList<>();
//        ArrayList<Integer> negatives2 = new ArrayList<>();
//        if (input.startsWith("-")) {
//            String[] nums = input.split(",");
//            for (String str : nums){
//                negatives.add(Integer.parseInt(str));
//                if (Integer.parseInt(str)<0){
//                    negatives2.add(Integer.parseInt(str));
//                    System.out.println(msg);
//
//                }
//            }
//            for(int i : negatives2){
//                msg += i + " ";
//            }
//            System.out.println(msg);
//            throw new Exception(msg);
//        }
//        String[] DLmit = {".*([,\n;*])*."};
//        if(input.startsWith("//")){
//            String[] inputIndexSplit1 = input.split("\n", 2);
//
//            Matcher matcher = Pattern.compile("//(\\d)\n(\\d);?\\d").matcher(input);
//            matcher.matches();
//            String newDLimit = matcher.group(1);
//            String newInput = matcher.group(1);
//            DLmit = newInput.split(newDLimit);
//            input = inputIndexSplit1[1];
//        }
//        String[] inputIndexSplit2 = input.split(String.valueOf(DLmit));
//        for (String i : inputIndexSplit2) {
//            String[] inputIndexSplit3 = i.split(",");
//            for (String num2 : inputIndexSplit3) {
//                if (Integer.parseInt(num2)>=1000){
//                    continue;
//                }
//                total += Integer.parseInt(num2);
//            }
//        }
//        System.out.println(total);
//        return total;
//    }
//    public static void main(String[] args) {
//        try {
//            add("//;\n1000;1;2;");
//            add("1\n112");
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
//}
