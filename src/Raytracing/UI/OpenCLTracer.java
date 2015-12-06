package Raytracing.UI;

import static java.lang.System.*;

import com.jogamp.opencl.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.Random;
import java.util.logging.Logger;

/**
 *  Lets write an example application first - then we tackle this shit it following order:
 *  Push data on the kernel.
 *  Do the kernel code.
 *  Suck less.
 */
public class OpenCLTracer {

    // VM supplied argument: -Djava.util.logging.SimpleFormatter.format="%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %2$s > %5$s%6$s%n"
    // (makes pretty ouput, m8)
    protected final Logger logger = Logger.getLogger(getClass().getName());

    public static void main(String[] args) throws IOException {
        OpenCLTracer oclt = new OpenCLTracer();
        oclt.invoke();
    }

    public void invoke() throws IOException {
        CLContext context = CLContext.create();
        logger.info("created context: " + context);
        try {
            // Get the fastest bitch unda da hood
            CLDevice device = context.getMaxFlopsDevice();
            logger.info("using device: " + device);

            // Get a command queue
            CLCommandQueue queue = device.createCommandQueue();
            logger.info("created queue: " + queue);

            int elementCount = 10;
            int localWorkSize = Math.min(device.getMaxWorkGroupSize(), 10);
            int globalWorkSize = roundUp(localWorkSize, elementCount);

            // load onto the bitch and command it
            InputStream is = new FileInputStream(new File("src/Kernels/VectorAdd.cl"));
            CLProgram program = context.createProgram(is).build();
            logger.info("built program: " + program);


            // A, B are input buffers, C is for the result
            CLBuffer<FloatBuffer> clBufferA = context.createFloatBuffer(globalWorkSize, CLMemory.Mem.READ_ONLY);
            CLBuffer<FloatBuffer> clBufferB = context.createFloatBuffer(globalWorkSize, CLMemory.Mem.READ_ONLY);
            CLBuffer<FloatBuffer> clBufferC = context.createFloatBuffer(globalWorkSize, CLMemory.Mem.WRITE_ONLY);
            logger.info("using memory: " + (clBufferA.getCLSize() + clBufferB.getCLSize() + clBufferC.getCLSize())/(1024.0*1024) + "MB");

            // generates predictable output
            // float[] stuff = {1.0f, 1.5f, 2.5f, 3.0f, 4.5f, 6f, 7f, 8f, 9f, 10f};
            // clBufferA.getBuffer().put(stuff);
            // clBufferB.getBuffer().put(stuff);
            fillBuffer(clBufferA.getBuffer(), 12345);
            fillBuffer(clBufferB.getBuffer(), 67890);

            // get a reference to the kernel function with the name 'VectorAdd'
            // and map the buffers to its input parameters.
            CLKernel kernel = program.createCLKernel("VectorAdd");
            kernel.putArgs(clBufferA, clBufferB, clBufferC).putArg(elementCount);

            // asynchronous write of data to GPU device,
            // followed by blocking read to get the computed results back.
            long time = nanoTime();
            queue.putWriteBuffer(clBufferA, false)
                 .putWriteBuffer(clBufferB, false)
                 .put1DRangeKernel(kernel, 0, globalWorkSize, localWorkSize)
                 .putReadBuffer(clBufferC, true);
            time = nanoTime() - time;

            // print first few elements of the resulting buffer to the console.
            logger.info("a + b = c snapshot: ");
            for(int i= 0; i < 10; i++) logger.info("\t" + clBufferC.getBuffer().get());
            logger.info("and " + clBufferC.getBuffer().remaining() + " more");
            logger.info("computation took: "+(time/1000000)+"ms");
        } finally {
            context.release();
        }
    }

    protected static void fillBuffer(FloatBuffer buffer, int seed) {
        float f = 0.5f;
        while(buffer.remaining() != 0) buffer.put(f++);
        buffer.rewind();
    }

    protected static int roundUp(int groupSize, int globalSize) {
        int r = globalSize % groupSize;
        if (r == 0) {
            return globalSize;
        } else {
            return globalSize + groupSize - r;
        }
    }
}
