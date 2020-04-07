package innerClass;
/***
 *   learn inner class
 *

 * 如果不写inner class，此处需import callbacks 包中的TimePrinter, 但是这个
 * 包中的TimePrinter class无法access TalkingClock class中的变量，如interval, beep，如果需要使用，则
 * 要单独再给他传参。
 * import callbacks.TimePrinter;
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;



public class TalkingClock {
    private  int interval;
    private  boolean beep;
    public static int foo = 5;


    public TalkingClock(int interval, boolean beep){
        this.interval = interval;
        this.beep = beep;
    }
    public void start(){
        TimePrinter listener = new TimePrinter();
        Timer timer = new Timer(interval,listener);
        timer.start();
    }





    public class TimePrinter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
            System.out.println("Interval is "+ interval);
            if(beep){
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
    public static void main(String[] args) {
        TalkingClock tc = new TalkingClock(1000, true);
        tc.start();//只是启动了timer这个event,程序会正常向下执行
        JOptionPane.showMessageDialog(null, "  Quit Program");//显示对话框,如果press ok,这行程序结束，进入下一行
        System.exit(0);

        TalkingClock.TimePrinter ti = tc.new TimePrinter();//一般不这么用


    }
}
