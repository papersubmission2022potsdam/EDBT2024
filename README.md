<h1 align="center">Dema: Efficient Decentralized Aggregation for Non-Decomposable Quantile Functions </h1>

# Dema

Dema is a decentralized window aggregation approach that mainly focuses on time-based windows with decomposable functions, e.g., median and quantile.
    
# Installation

- **Requirements**: Java 8+
- **Install**
     1. Download EDBT2024
     2. Compile Project
     3. Set `WINDOWS = true` in `Configuration.java`
     4. Run OverallMainDriverTest.java

# Input

Dema
  - Node Id: 
    - The id of the node that is deployed with Dema.
  - Query Modes: 
    - The query mode is to choose the query pattern that is set into query generation file.
  - Generator Thread Number: 
    - How many generator threads are initialized. One thread can produce at least 10 million tuples/s.

