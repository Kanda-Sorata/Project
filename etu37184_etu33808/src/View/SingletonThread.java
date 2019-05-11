package View;

public class SingletonThread {
    private static FrameThread counterFrame;

    public static FrameThread getSingletonThread(Frame frame) {
        if (counterFrame == null) {
            counterFrame = new FrameThread(frame);
        }
        return counterFrame;
    }

    public static void close() {
        counterFrame = null;
    }
}
