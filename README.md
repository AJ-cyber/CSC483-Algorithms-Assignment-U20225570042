# CSC483 Algorithms Assignment

**Student:** Agiri Joshua Abraham  
**ID:** U2022/5570042

## Requirements
- Java 11 or higher
- Maven 3.8 or higher

## Project Structure
- `assignment1/search` - search algorithms and benchmark
- `assignment2/sorting` - sorting algorithms and benchmark
- `src/test/java` - JUnit 5 test classes
- `sample-data` - small sample datasets

## Compile
```bash
mvn compile
```

## Run Tests
```bash
mvn test
```

## Run Search Benchmark
```bash
mvn exec:java -Dexec.mainClass="com.csc483.assignment1.search.SearchBenchmark"
```

## Run Sorting Benchmark
```bash
mvn exec:java -Dexec.mainClass="com.csc483.assignment2.sorting.SortBenchmark"
```

## Notes
- Product IDs are generated in the range 1 to 200,000.
- Binary search assumes the product array is sorted by `productId`.
- All sorting algorithms in this project are implemented manually as required by the assignment.
