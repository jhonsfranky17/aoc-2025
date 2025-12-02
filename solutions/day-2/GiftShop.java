import java.io.*;

public class GiftShop {
    public static void main(String[] args) {
        String filePath = "./input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
                BufferedWriter bw = new BufferedWriter(new FileWriter("./output.txt"))) {
            String line;
            long invalidIdsSum = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    String range[] = part.trim().split("-");
                    long start = Long.parseLong(range[0]);
                    long end = Long.parseLong(range[1]);
                    invalidIdsSum += invalidId(start, end);
                }

            }
            System.out.println("Sum of invalid gift IDs: " + invalidIdsSum);
            bw.write("Sum of invalid gift IDs: " + invalidIdsSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long invalidId(long start, long end) {
        long invalidIdsSum = 0;
        for (long i = start; i <= end; i++) {
            if (isInvalid(i))
                invalidIdsSum += i;
        }
        return invalidIdsSum;
    }

    public static boolean isInvalid(long id) {
        String idStr = Long.toString(id);
        int n = idStr.length();

        for (int len = 1; len <= n / 2; len++) {
            if (n % len == 0) {
                String sub = idStr.substring(0, len);
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < n / len; i++) {
                    sb.append(sub);
                }

                if (sb.toString().equals(idStr)) {
                    return true;
                }
            }
        }
        return false;
    }
}
