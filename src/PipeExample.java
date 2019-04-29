import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeExample {
    public static void main(String[] args)throws IOException{
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input = new PipedInputStream(output);

        // Java匿名类
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output.write("Hello word, pipe!".getBytes());
                } catch (IOException e){

                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = input.read();
                    while (data != -1) {
                        System.out.print((char) data);
                        data = input.read();
                    }
                } catch (IOException e){

                }
            }
        });

        thread1.start();
        thread2.start();
    }
}