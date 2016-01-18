import Raytracing.Scenes.Scene4;
import Raytracing.Scenes.Scene5;
import Raytracing.Scenes.SceneOBJ;
import Raytracing.Scenes.SceneTexture;

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
       /* SceneMultithread.SceneFactory();
        time = timeTaken(time, "SceneMultithread");
        Scene4.SceneFactory();
        time = timeTaken(time, "Scene4");
        */
      /*  Scene4.SceneFactory();
        time = timeTaken(time, "Scene5"); */
        //SceneTexture.SceneFactory();
        //time = timeTaken(time, "OBJ");
       // SceneOBJ.SceneFactory();
        // SceneTexture.SceneFactory();
        Scene4.SceneFactory();
       /* time = timeTaken(time, "Scene4");
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
