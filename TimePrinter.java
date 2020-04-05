package callbacks;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;



public class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event)
    {
//        System.out.println("Event source: "+ event);
        System.out.println("Event source: "+ event.getSource());
        System.out.println("Event source: "+ event.getWhen());
    }

/***1:同一个listener可监听Handel多个event，不一定是timer
    2:一般会对某一类event单独创建一个listener
  ***/

    public static void main(String[] args) throws InterruptedException {
        TimePrinter listener = new TimePrinter();//creat listener for Timer
        Timer t = new Timer(100, listener);//pass the event to listener
        Timer t2 = new Timer(1000, listener);//pass the event to listener
//        或者：Timer t = new Timer(100, null);
//              t.addActionListener(listener);//Or pass the event to listener in this way
        t.start();
        t2.start();
        while(true);


    }
}
