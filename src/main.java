import Raytracing.Scenes.Scene3;
import Raytracing.Scenes.Scene4;
import Raytracing.Scenes.Scene5;
import Raytracing.Scenes.SceneMultithread;

public class main {

    private static long timeTaken(long initTime, String sceneName) {
        long newTime = System.currentTimeMillis();
        System.out.println("Time taken for " + sceneName + ": " + (newTime - initTime) + "ms");
        return newTime;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        SceneMultithread.Scene5Factory();
        time = timeTaken(time, "SceneMultithread");
        Scene4.Scene4Factory();
        time = timeTaken(time, "Scene4");
        Scene5.Scene5Factory();
        time = timeTaken(time, "Scene4");
/*        Scene4.Scene4Factory();
        time = timeTaken(time, "Scene4");
        Scene5.Scene5Factory();
        time = timeTaken(time, "Scene5")
        SceneMultithread.Scene5Factory();
        time = timeTaken(time, "SceneMultithread");
 */
    }
}
