# Multi-Threaded File Processor

This project processes text files in a multithreaded environment, extracting unique words and calculating aggregate statistics such as the total number of unique words, the average word length, and the longest and shortest words. The project is implemented in Java using concurrent data structures and a thread pool for efficient file processing.

## Features

- **Multithreaded processing**: Files are processed concurrently using a thread pool.
- **Concurrent data structure**: Uses `ConcurrentSkipListSet` to store unique words in a thread-safe manner.
- **Statistics aggregation**: Calculates:
    - Total number of unique words.
    - Average word length.
    - Longest word.
    - Shortest word.
- **Scalable design**: Handles multiple files efficiently with dynamic scaling of threads.

## Prerequisites

- **Java 8** or higher.
- A directory named `assets/` containing text files to be processed.

## How It Works

1. **File discovery**: The `assets/` directory is scanned for text files.
2. **Multithreaded processing**: Each file is processed in a separate thread to extract unique words.
3. **Word aggregation**: The unique words are stored in a thread-safe `ConcurrentSkipListSet`.
4. **Statistics calculation**: The program computes and displays:
    - Total unique words.
    - Average word length.
    - Longest and shortest words.
