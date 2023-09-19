package library;


public class Video extends Publication {
    private int runtime; // Add an instance variable to store the runtime

    /**
     * Constructs a new Video object with the specified title, author, copyright year, and runtime.
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

        this.runtime = runtime; // Assign the runtime value
    }

    /**
     * Gets the runtime of the video in minutes.
     *
     * @return The runtime of the video.
     */
    public int getRuntime() {
        return runtime; // Return the actual runtime value
    }

    // Rest of the Video class...

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
            super(title + " has invalid runtime " + runtime);
        }
    }

    /**
     * Generates a string representation of the video.
     *
     * @return The string representation of the video.
     */
    @Override
    public String toString() {
        // Custom toString implementation for Video
        return super.toString() + "\nRuntime: " + getRuntime() + " minutes";
    }
}
