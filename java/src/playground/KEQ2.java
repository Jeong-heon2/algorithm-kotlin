package playground;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.EnumSet;
import java.util.HashMap;

public class KEQ2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<String, String> map = new HashMap<>();
        map.put("qw", "1");
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        int i = 0;
        while (++i < n) {
            String code = String.valueOf(input.charAt(i - 1)) + String.valueOf(input.charAt(i));
            String num;
            if (code.equals("qw")) num = "1";
            else if(code.equals("as")) num = "2";
            else if(code.equals("zx")) num = "3";
            else if(code.equals("we")) num = "4";
            else if(code.equals("sd")) num = "5";
            else if(code.equals("xc")) num = "6";
            else if(code.equals("er")) num = "7";
            else if(code.equals("df")) num = "8";
            else if(code.equals("cv")) num = "9";
            else if(code.equals("ze")) num = "0";
            else num = "";
            if ((i != 1 || !num.equals("0"))) bw.write(num);
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
