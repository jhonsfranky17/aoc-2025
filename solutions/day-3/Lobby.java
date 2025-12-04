import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Lobby {
    public static void main(String[] args) {
        String inputFile = "./input.txt";
        String outputFile = "./output.txt";
        long part1 = 0;
        long part2 = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                part1 += maximumJoltage(line, 2);
                part2 += maximumJoltage(line, 12);
            }
            System.out.println("Maximum Joltage:" + part1);
            System.out.println("Maximum Joltage:" + part2);
            bw.write("Part 1: " + part1 + "\n");
            bw.write("Part 2: " + part2 + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long maximumJoltage(String bank, int k) {
        int remove = bank.length() - k;
        Stack<Character> stack = new Stack<>();

        for (char c : bank.toCharArray()) {
            while (!stack.isEmpty() && remove > 0 && stack.peek() < c) {
                stack.pop();
                remove--;
            }
            stack.push(c);
        }

        while (remove > 0) {
            stack.pop();
            remove--;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack)
            sb.append(c);

        return Long.parseLong(sb.substring(0, k));
    }
}
