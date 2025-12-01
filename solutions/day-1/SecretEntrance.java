import java.io.*;

public class SecretEntrance {

    public static void main(String[] args) {
        String filePath = "./input.txt";
        int dialPos = 50;
        int count1 = 0, count2 = 0;

        State state = new State(dialPos, count1, count2);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
                BufferedWriter bw = new BufferedWriter(new FileWriter("./output.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                state = findPassword(line, state);
            }
            System.out.println("Total valid passwords at the end: " + state.count1);
            System.out.println("Total valid passwords including every click: " + state.count2);
            bw.write("Total valid passwords at the end: " + state.count1 + "\n");
            bw.write("Total valid passwords including every click: " + state.count2 + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static State findPassword(String input, State state) {
        int dialPos = state.dial;
        int count1 = state.count1;
        int count2 = state.count2;
        int move = Integer.parseInt(input.substring(1));

        for (int i = 0; i < move; i++) {
            if (input.startsWith("L")) {
                dialPos = (dialPos - 1 + 100) % 100;
            } else {
                dialPos = (dialPos + 1) % 100;
            }
            if (dialPos == 0) {
                count2++;
            }
        }
        if (dialPos == 0) {
            count1++;
        }
        return new State(dialPos, count1, count2);
    }
}

class State {
    int dial;
    int count1, count2;

    State(int dial, int count1, int count2) {
        this.dial = dial;
        this.count1 = count1;
        this.count2 = count2;
    }
}
