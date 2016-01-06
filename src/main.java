import Raytracing.Scenes.*;

public class main {

    private static long timeTaken(long initTime, String sceneName) {
        long newTime = System.currentTimeMillis();
        System.out.println("Time taken for " + sceneName + ": " + (newTime - initTime) + "ms");
        return newTime;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
       /* SceneMultithread.Scene5Factory();
        time = timeTaken(time, "SceneMultithread");
        Scene4.Scene4Factory();
        time = timeTaken(time, "Scene4");
        Scene5.Scene5Factory();
        time = timeTaken(time, "Scene5");*/
        SceneOBJ.SceneOBJFactory();
/*        Scene4.Scene4Factory();
        time = timeTaken(time, "Scene4");
        Scene5.Scene5Factory();
        time = timeTaken(time, "Scene5")
        SceneMultithread.Scene5Factory();
        time = timeTaken(time, "SceneMultithread");
 */
    }
}
