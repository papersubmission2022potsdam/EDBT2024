<h1 align="center">Efficent Window Aggregation in Decentralized Networks </h1>

# Dema

Dema is a decentralized window aggregation approach that mainly focus on time-based windows with decomposable function.
    
# Installation

- **Requirements**: Java 8+
- **Install**
     1. Download DESengine
     2. Compile Project
     3. Set `WINDOWS = true` in `Configuration.java`
     4. Run OverallMainDriverTest.java

# Input

Dema
  - Node Id: 
    - The id of the node that is deployed with Dema.
  - Query Number: 
    - How many queries are processed simultaneously.
  - Query Modes: 
    - The query mode is to choose the query pattern that is set into query generation file.
  - Generator Thread Number: 
    - How many generator threads are initialized. One thread can produce at least 10 million tuples/s.

