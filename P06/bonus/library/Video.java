package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.Duration;

/**
 * The Video class represents a video publication that can be checked out from the library.
 */
public class Video extends Publication {
    private Duration runtime;

    /**
     * Constructs a new Video object with the specified title, author, copyright year, and runtime in minutes.
     *
     * @param title     The title of the video.
     * @param author    The author or director of the video.
     * @param copyright The year of copyright for the video.
     * @param runtime   The runtime of the video in minutes.
     * @throws InvalidRuntimeException If the runtime is less than or equal to zero.
     */
    public Video(String title, String author, int copyright, int runtime) {
        super(title, author, copyright);

        if (runtime <= 0) {
            throw new InvalidRuntimeException(title, runtime);
        }

        this.runtime = Duration.ofMinutes(runtime); // Convert minutes to Duration
    }

    /**
     * Saves the Video object's data to the provided BufferedWriter stream.
     *
     * @param bw The BufferedWriter to write to.
     * @throws IOException if an I/O error occurs while writing.
     */
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);

        bw.write(runtime.toString() + '\n');
    }

    /**
     * Constructs a new Video object from the data read from a BufferedReader stream.
     *
     * @param br The BufferedReader to read from.
     * @throws IOException if an I/O error occurs while reading.
     */
    public Video(BufferedReader br) throws IOException {
        super(br);

        String runtimeString = br.readLine();
        this.runtime = Duration.parse(runtimeString);
    }

    /**
     * Custom exception class for handling invalid runtime values.
     */
    public static class InvalidRuntimeException extends ArithmeticException {
        /**
         * Constructs a new InvalidRuntimeException with the specified title and runtime.
         *
         * @param title   The title of the video.
         * @param runtime The invalid runtime value.
         */
        public InvalidRuntimeException(String title, int runtime) {
            super(title + " has an invalid runtime of " + runtime + " minutes");
        }
    }

    /**
     * Generates a string representation of the video.
     *
     * @return The string representation of the video.
     */
    @Override
    public String toString() {
        return super.toString() + "\nRuntime: " + runtime.toMinutes() + " minutes";
    }

    public static Video load(BufferedReader reader) {
        return null;
    }
}


