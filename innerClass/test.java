package innerClass;

public class test {
    public static void main(String[] args) {

        TalkingClock tc = new TalkingClock(1000, true);
        TalkingClock.TimePrinter t = tc.new TimePrinter();


    }
}
