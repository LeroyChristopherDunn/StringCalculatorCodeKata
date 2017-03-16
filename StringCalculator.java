import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    static int add(String numbers) throws Exception {
        if (numbers.isEmpty())
            return 0;

        String[] splitNumbers = getSplitNumbers(numbers);
        return sum(splitNumbers);
    }

    private static String[] getSplitNumbers(String numbers) {

        String delimiter = ",|\n";

        Pattern pattern;
        Matcher matcher;

        if (numbers.startsWith("//[")) {

            pattern = Pattern.compile("//\\[(.*)\\]\n(.*)");

            matcher = pattern.matcher(numbers);
            matcher.find();
            String delimiters = matcher.group(1);
            String[] splitDelimiters = delimiters.split("\\]\\[");
            String concatenatedDelimiter = Pattern.quote(splitDelimiters[0]);
            for (String splitDelimiter : splitDelimiters) {
                concatenatedDelimiter += "|" + Pattern.quote(splitDelimiter);
            }
            delimiter = concatenatedDelimiter;
            numbers = matcher.group(2);

        } else if (numbers.startsWith("//")) {

            pattern = Pattern.compile("//(.*)\n(.*)");

            matcher = pattern.matcher(numbers);
            matcher.find();
            delimiter = Pattern.quote(matcher.group(1));
            numbers = matcher.group(2);

        }

        return numbers.split(delimiter);
    }

    private static int sum(String[] splitNumbers) throws Exception {
        int sum = 0;
        List<String> negativeNumbers = new LinkedList<>();
        for (String splitNumber : splitNumbers) {
            int iNumber = Integer.parseInt(splitNumber);
            if (iNumber < 0)
                negativeNumbers.add(splitNumber);
            if (iNumber < 1001)
                sum += iNumber;
        }

        if (negativeNumbers.size() > 0) {
            String errorMessage = "";
            for (String negativeNumber : negativeNumbers) {
                errorMessage += negativeNumber + ",";
            }
            throw new Exception(errorMessage);
        }
        return sum;
    }
}
