package space.harbour.hw6;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Before;
import org.junit.Test;


public class WebCrawlerTest {
    static volatile LinkedBlockingQueue<URL> toVisit;
    static volatile LinkedBlockingQueue<URL> alreadyVisited;

    @Before
    public void setUp() {
        WebCrawler.toVisit = new LinkedBlockingQueue<>();
        alreadyVisited = new LinkedBlockingQueue<>();
    }

    @Test
    public void size() throws MalformedURLException, ExecutionException, InterruptedException {
        System.out.println(WebCrawler.toVisit.isEmpty());
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        final int N = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        LinkedBlockingQueue<URL> alreadyVisitedUrls =  new LinkedBlockingQueue<>();

        int counter = 0;
        while (!WebCrawler.toVisit.isEmpty()) {
            counter++;
            Future future = executorService.submit(new WebCrawler.UrlVisitor());
            alreadyVisitedUrls = (LinkedBlockingQueue<URL>) future.get();
        }
        System.out.println("length of urls : " + alreadyVisitedUrls.toArray().length);
        alreadyVisitedUrls.stream().forEach(u -> System.out.println(u.toString()));
        System.out.println("Counter is:" + counter);
        assertEquals(15, alreadyVisitedUrls.toArray().length);
        executorService.shutdown();
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
        assertEquals(true, WebCrawler.toVisit.isEmpty());
    }



}