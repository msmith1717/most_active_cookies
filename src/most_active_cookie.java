import java.io.*;
import java.util.*;

public class most_active_cookie {
    /**
     * Main method initializes Log object with command line input
     * and prints most active cookies on a given day.
     * @param args array of strings containing filename and day
     * @return void
     * @exception IllegalArgumentException if args has the wrong number of arguments
     * @exception FileNotFoundException if filename cannot be read
     */
    public static void main(String[] args) throws Exception {
        Map<String, List<String>> cookie_times = new HashMap<>();
        Map<String, Integer> cookie_freq = new HashMap<>();
        if (args.length != 3) throw new IllegalArgumentException();
        String filename = args[0];
        String day = args[2];
        Log cookie_log = new Log(filename, day);
        List<String> active_cookies = cookie_log.most_active();
        for (String cookie : active_cookies) {
            System.out.println(cookie);
        }
    }
}

class Log {
    //map cookie to list of its timestamps
    private Map<String, List<String>> cookie_times = new HashMap<>();
    //map cookie to the number of occurrences on given day
    private Map<String, Integer> cookie_freq = new HashMap<>();
    //the maximum frequency of any cookies on given day
    private int max_freq = Integer.MIN_VALUE;

    /**
     * Log constructor initializes cookie_times, cookie_freq,
     * and max_freq by reading all lines in the given file.
     * @param filename String of the filename to be read
     * @param day String of the day to base frequencies on
     * @exception FileNotFoundException if filename cannot be read
     */
    public Log (String filename, String day) throws Exception {
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        scan.nextLine(); //first line contains column names
        while (scan.hasNext()) {
            String[] cookie_time = scan.nextLine().split(",");
            String cookie = cookie_time[0];
            String time = cookie_time[1];
            List<String> times = cookie_times.getOrDefault(cookie, new ArrayList<>());
            times.add(time);
            if (time.contains(day)) {
                int freq = cookie_freq.getOrDefault(cookie, 0) + 1;
                cookie_freq.put(cookie, freq);
                if (freq > max_freq) {
                    max_freq = freq;
                }
            }
        }
    }

    /**
     * most_active method iterates through all cookies
     * and returns a list of cookies whose frequency
     * is equal to max_freq in no particular order.
     * @return a list of the most active cookies.
     */
    public List<String> most_active() {
        List<String> active_cookies = new ArrayList<>();
        for (String cookie : cookie_freq.keySet()) {
            if (cookie_freq.get(cookie) >= max_freq) {
                active_cookies.add(cookie);
            }
        }
        return active_cookies;
    }
}
