import Raytracing.Scenes.*;

import javax.swing.*;

public class main {

    private static long timeTaken(long initTime, String sceneName) {
        long newTime = System.currentTimeMillis();
        System.out.println("Time taken for " + sceneName + ": " + (newTime - initTime) + "ms");
        return newTime;
    }

    public static void main(String[] args) {
        bootstrap();
        long time = System.currentTimeMillis();
       /* SceneMultithread.Scene5Factory();
        time = timeTaken(time, "SceneMultithread");
        Scene4.Scene4Factory();
        time = timeTaken(time, "Scene4");
        */
      /*  Scene4.Scene4Factory();
        time = timeTaken(time, "Scene5"); */
        SceneOBJ.SceneOBJFactory();
        time = timeTaken(time, "OBJ");
        // Scene4.Scene4Factory();
/*        Scene4.Scene4Factory();
        time = timeTaken(time, "Scene4");
        Scene5.Scene5Factory();
        time = timeTaken(time, "Scene5")
        SceneMultithread.Scene5Factory();
        time = timeTaken(time, "SceneMultithread");
 */
    }

    private static void bootstrap() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
