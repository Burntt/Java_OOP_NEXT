package space.harbour.hw2;

public class EstimateSize {

    public static void main(String[] args) throws InterruptedException {
        long integerOutput = (long) estimateSizeOfInt();
        long stringOutput = (long) estimateSizeOfString();
        long referenceOutput = (long) estimateSizeOfReference();
        long objectOutput = (long) estimateSizeOfObject();

        System.out.println("memory used by int DS: " + integerOutput + " bytes");
        System.out.println("memory used by String DS: " + stringOutput + " bytes");
        System.out.println("memory used by Reference DS: " + referenceOutput + " bytes");
        System.out.println("memory used by Object DS: " + objectOutput + " bytes");
    }

    public static float estimateSizeOfInt() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        long totalOccBefore = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        //
        int n = 10_000_000;
        int[] a = new int[n];
        //
        long totalOccAfter = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        long totalOccDs = totalOccAfter - totalOccBefore;
        return totalOccDs / (float) n;
    }

    public static float estimateSizeOfString() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        long totalOccBefore = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        //
        int n = 10_000_000;
        int[] a = new int[n];
        String[] b = new String[n];
        for (int i = 0; i < n; i++) {
            b[i] = String.valueOf(i);
        }
        //
        long totalOccAfter = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        long totalOccDs = totalOccAfter - totalOccBefore;
        return totalOccDs / (float) n;
    }

    public static float estimateSizeOfReference() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        long totalOccBefore = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        //
        int n = 10_000_000;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = null;
        }
        //
        long totalOccAfter = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        long totalOccDs = totalOccAfter - totalOccBefore;
        return totalOccDs / (float) n;
    }

    public static float estimateSizeOfObject() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        long totalOccBefore = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        //
        int n = 10_000_000;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        //
        long totalOccAfter = Runtime.getRuntime().totalMemory()
                            - Runtime.getRuntime().freeMemory();
        long totalOccDs = totalOccAfter - totalOccBefore;
        return totalOccDs / (float) n;
    }
}
