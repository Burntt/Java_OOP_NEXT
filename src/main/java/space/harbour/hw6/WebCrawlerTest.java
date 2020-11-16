package space.harbour.hw6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;

public class WebCrawlerTest {

    static volatile LinkedBlockingQueue<URL> toVisit = new LinkedBlockingQueue<>();
    static volatile LinkedBlockingQueue<URL> alreadyVisited = new LinkedBlockingQueue<>();


    @Test
    public void size() throws MalformedURLException, ExecutionException, InterruptedException {
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        final int N = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        LinkedBlockingQueue<URL> alreadyVisitedUrls =  new LinkedBlockingQueue<>();
        while (!WebCrawler.toVisit.isEmpty()) {
            Future future = executorService.submit(new WebCrawler.UrlVisitor());
            alreadyVisitedUrls = (LinkedBlockingQueue<URL>) future.get();
        }
        executorService.shutdown();

        // Length from Vasilii's github website should be five
        assertEquals(5, alreadyVisitedUrls.toArray().length);
    }

    @Test
    public void testFirstUrlIsMain()
            throws MalformedURLException, ExecutionException, InterruptedException {
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        final int N = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        LinkedBlockingQueue<URL> alreadyVisitedUrls =  new LinkedBlockingQueue<>();
        while (!WebCrawler.toVisit.isEmpty()) {
            Future future = executorService.submit(new WebCrawler.UrlVisitor());
            alreadyVisitedUrls = (LinkedBlockingQueue<URL>) future.get();
        }
        executorService.shutdown();
        assertEquals(new URL("https://vasart.github.io/supreme-potato/"), alreadyVisitedUrls.toArray()[0]);
    }

    @Test
    public void testIfVisitIsEmpty()
            throws MalformedURLException, ExecutionException, InterruptedException {
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        final int N = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        LinkedBlockingQueue<URL> alreadyVisitedUrls =  new LinkedBlockingQueue<>();
        while (!WebCrawler.toVisit.isEmpty()) {
            Future future = executorService.submit(new WebCrawler.UrlVisitor());
            alreadyVisitedUrls = (LinkedBlockingQueue<URL>) future.get();
        }
        executorService.shutdown();
        assertTrue(WebCrawler.toVisit.isEmpty());
    }



}