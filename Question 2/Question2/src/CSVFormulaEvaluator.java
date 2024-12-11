import java.io.*;
import java.util.*;
import java.util.regex.*;

public class CSVFormulaEvaluator {
    private static final Pattern FORMULA_PATTERN = Pattern.compile("=(\\d+|[A-Z]\\d+)([+\\-*/])(\\d+|[A-Z]\\d+)");
    
    public static void main(String[] args) throws IOException {
        String inputFile = "input.csv";
        String outputFile = "output.csv";

        List<List<String>> data = readCSVFile(inputFile);
        Map<String, Integer> addressValuesMap = new HashMap<>();
        
        for (int row = 0; row < data.size(); row++) {
            for (int col = 0; col < data.get(row).size(); col++) {
                String cellName = getCellAddress(row, col);
                String cellContent = data.get(row).get(col);
                addressValuesMap.put(cellName, evaluateCell(cellContent, addressValuesMap));
            }
        }

        writeCSVFile(outputFile, data, addressValuesMap);
        System.out.println("Results written to " + outputFile);
    }

    private static List<List<String>> readCSVFile(String fileName) throws IOException {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(Arrays.asList(line.split(",")));
            }
        }
        return data;
    }

    private static void writeCSVFile(String fileName, List<List<String>> data, Map<String, Integer> cellValues) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int row = 0; row < data.size(); row++) {
                List<String> line = new ArrayList<>();
                for (int col = 0; col < data.get(row).size(); col++) {
                    String cellName = getCellAddress(row, col);
                    line.add(String.valueOf(cellValues.get(cellName)));
                }
                bw.write(String.join(",", line));
                bw.newLine();
            }
        }
    }

    private static String getCellAddress(int row, int col) {
        char column = (char) ('A' + col);
        return column + String.valueOf(row + 1);
    }

    private static int evaluateCell(String cellContent, Map<String, Integer> cellValues) {
        if (cellContent.matches("\\d+")) {
            return Integer.parseInt(cellContent);
        }
        Matcher matcher = FORMULA_PATTERN.matcher(cellContent);
        if (matcher.matches()) {
            int operand1 = getValue(matcher.group(1), cellValues);
            int operand2 = getValue(matcher.group(3), cellValues);
            String operator = matcher.group(2);
            return calculate(operand1, operator, operand2);
        }
        throw new IllegalArgumentException("Invalid cell content: " + cellContent);
    }

    private static int getValue(String operand, Map<String, Integer> cellValues) {
        if (operand.matches("\\d+")) {
            return Integer.parseInt(operand);
        }
        if (!cellValues.containsKey(operand)) {
            throw new IllegalArgumentException("Reference to undefined cell: " + operand);
        }
        return cellValues.get(operand);
    }

    private static int calculate(int operand1, String operator, int operand2) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }
}
