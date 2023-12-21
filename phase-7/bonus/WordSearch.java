import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WordSearch {
    private static final String usage = "usage: java WordSearch [-h] [-v] [#threads] [#puzzles] [puzzleFile]...";

    public WordSearch(List<String> args) {

        
        if (args.size() > 0 && args.get(0).equals("-h")) {
            System.out.println(usage);
            System.exit(0);
        }
        if (args.size() > 0 && args.get(0).equals("-v")) {
            verbose = true;
            args.remove(0);
        } else {
            verbose = false;
        }
        int threads = 0;
        try {
            threads = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch (Exception e) {
            System.err.println("Error: Invalid number of threads\n" + usage);
            System.exit(-1);
        }
        NUM_THREADS = threads;
        int numPuzzles = 0;
        try {
            numPuzzles = Integer.parseInt(args.get(0));
            
        } catch (Exception e) {
            System.err.println("Error: Invalid number of puzzles\n" + usage);
            System.exit(-1);
        }

        
        for (String puzzleName : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(puzzleName))) {
                puzzles.add(new Puzzle(puzzleName, br));
            } catch (IOException e) {
                System.err.println("Unable to load puzzle " + puzzleName);
            }
        }

        
        if (puzzles.size() != args.size())
            System.exit(-3);
        solutions = new TreeSet<>();
        
        if (numPuzzles < puzzles.size()) puzzles.subList(numPuzzles, puzzles.size()).clear();
        else if (numPuzzles > puzzles.size()) {
            for (int i = puzzles.size(); i < numPuzzles; ++i)
                puzzles.add(puzzles.get(i % puzzles.size()));
        }
        NUM_PUZZLES = puzzles.size();
    }

    public void solve(int threadID) {
        while (true) {
            int puzzleIndex;
            synchronized (puzzleCounterLock) {
                if (puzzleCounter >= NUM_PUZZLES) {
                    
                    return;
                }
                puzzleIndex = puzzleCounter;
                puzzleCounter++;
            }

            Puzzle p = puzzles.get(puzzleIndex);
            Solver solver = new Solver(p);

            for (String word : p.getWords()) {
                try {
                    Solution s = solver.solve(word);
                    if (s == null) {
                        System.err.println("#### Failed to solve " + p.name() + " for '" + word + "'");
                    } else {
                        synchronized (solutions) {
                            solutions.add(s);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("#### Exception solving " + p.name()
                            + " for " + word + ": " + e.getMessage());
                }
            }
        }
    }

    public void printSolutions() {
        for (Solution s : solutions)
            System.out.println(s);
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch(new LinkedList<>(Arrays.asList(args)));

        if (ws.NUM_THREADS <= 0) {
            System.err.println("Error: Invalid number of threads");
            return;
        }

        if (ws.NUM_THREADS == 1) {
            ws.solve(0);
        } else {
            ExecutorService threadPool = Executors.newFixedThreadPool(ws.NUM_THREADS);

            for (int threadID = 0; threadID < ws.NUM_THREADS; threadID++) {
                final int finalThreadID = threadID;
                threadPool.submit(() -> ws.solve(finalThreadID));
            }

            
            threadPool.shutdown();
            try {
                threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (ws.verbose) {
            ws.printSolutions();
        }
    }

    public final int NUM_THREADS;
    public final int NUM_PUZZLES;
    public final boolean verbose;

    private List<Puzzle> puzzles = new ArrayList<>();
    private SortedSet<Solution> solutions = new TreeSet<>();
    private int puzzleCounter = 0; 
    private final Object puzzleCounterLock = new Object(); 
}


