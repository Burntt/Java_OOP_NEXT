package space.harbour.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
    static volatile LinkedBlockingQueue<URL> toVisit = new LinkedBlockingQueue<>();
    static volatile LinkedBlockingQueue<URL> alreadyVisited = new LinkedBlockingQueue<>();

    public static class UrlVisitor implements Callable {

        public static String getContentOfWebPage(URL url) {
            final StringBuilder content = new StringBuilder();

            try (InputStream is = url.openConnection().getInputStream();
                    InputStreamReader in = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(in);) {
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    content.append(inputLine);
                }
            } catch (IOException e) {
                System.out.println("Failed to retrieve content of " + url.toString());
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        public LinkedBlockingQueue<URL> call() {
            synchronized (toVisit) {
                synchronized (alreadyVisited) {
                    URL urlUnsafe = toVisit.poll();
                    AtomicReference<URL> url = new AtomicReference<>(urlUnsafe);

                    // mark it as visited by adding to alreadyVisited set
                    alreadyVisited.add(url.get());

                    if (url.get() == null) {
                        Thread.currentThread().interrupt();
                    }

                    // get content of the web page
                    AtomicReference<String> content
                            = new AtomicReference<>(getContentOfWebPage(url.get()));
                    //  System.out.println("CONTENT : " + content);

                    // Retrieve the links from the HTML content
                    String regex =
                            "\\b(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]"
                                    + "*[-a-zA-Z0-9+&@#/%=~_|]";
                    Pattern regexPattern = Pattern.compile(regex);
                    Matcher regexMatcher = regexPattern.matcher(content.get());
                    while (regexMatcher.find()) {
                        AtomicReference<String> link = new AtomicReference<>(regexMatcher.group());
                        try {
                            AtomicReference<URL> newLinkUrl
                                    = new AtomicReference<>(new URL(link.get()));
                            if (!toVisit.contains(newLinkUrl)
                                    && !alreadyVisited.contains(newLinkUrl)) {
                                toVisit.add(newLinkUrl.get());
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return alreadyVisited;
        }
    }

    public static void main(String[] args)
            throws MalformedURLException, InterruptedException, ExecutionException {
        toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        final int N = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        LinkedBlockingQueue<URL> alreadyVisitedUrls =  new LinkedBlockingQueue<>();
        while (!toVisit.isEmpty()) {
            Future future = executorService.submit(new UrlVisitor());
            alreadyVisitedUrls = (LinkedBlockingQueue<URL>) future.get();
        }
        System.out.println("length of urls : " + alreadyVisitedUrls.toArray().length);
        executorService.shutdown();

    }
}
