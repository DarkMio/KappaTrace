import Raytracing.Scenes.SceneOBJ;

import javax.swing.*;

public class Main {

    private static long timeTaken(long initTime, String sceneName) {
        long newTime = System.currentTimeMillis();
        System.out.println("Time taken for " + sceneName + ": " + (newTime - initTime) + "ms");
        return newTime;
    }

    public static void main(String[] args) {
        bootstrap();
        long time = System.currentTimeMillis();
       /* SceneMultithread.SceneFactory();
        time = timeTaken(time, "SceneMultithread");
        Scene4.SceneFactory();
        time = timeTaken(time, "Scene4");
        */
      /*  Scene4.SceneFactory();
        time = timeTaken(time, "Scene5"); */
        SceneOBJ.SceneFactory();
        time = timeTaken(time, "OBJ");
        // Scene4.SceneFactory();
/*        Scene4.SceneFactory();
        time = timeTaken(time, "Scene4");
        Scene5.SceneFactory();
        time = timeTaken(time, "Scene5")
        SceneMultithread.SceneFactory();
        time = timeTaken(time, "SceneMultithread");
 */
    }

    private static void bootstrap() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
