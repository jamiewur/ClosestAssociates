# Closest Associates Report

___by Jing Li (s3676458) and Dili Wu (s3649332)___



## Data Generation

A dedicated data generator class (`GraphGenerator.java`) was created to facilitate graph generation and scenario evaluation. With the number of vertices `N` supplied, the generator creates a list of integers with 1 to N as the elements, and then shuffles the elements. This is the list of all vertices the graph has.

To grow the graph to the desired density, the generator will first add all the elements from the vertex list to the graph (Adjacency List or Incidence Matrix), and then add edges to the graph until the intended density is reached.

#### Graph Density:

Graph density can be defined as the number of edges over the number of vertices squared: 
$$
\begin{align*}
\frac{M}{N^2}
\end{align*}
$$
where `M` is the number of edges and `N` is the number of vertices. Also, for directed graphs, the **maximum number of edges** can be expressed as $N(N-1)$, as each vertex can form an edge with all other $(N-1)​$ vertices. Since this project is to research social networks where each edge is a relationship between two people. Self-loop doesn't make much sense and therefore ignored from calculating total number of edges. The maximum density for a graph is hence:
$$
\begin{align*}
\frac{N(N-1)}{N^2}
\end{align*}
$$
We then know that the range of the density is `[0, 1]`. Density is `0` when there is no edge at all, and density is `1` when the number of vertices approaches infinity and all the vertices form edges with all other vertices.

Then for the graph generator, it will calculates the maximum number of edges `Mm` given the number of vertices, and then multiply it with the desired density (a decimal number between 0 and 1) to get the number of edges the graph should have. For example, a graph with 10 vertices can have at most 90 edges ($10\times(10-1)$), and to form a graph with a density of 0.5, there should be 45 edges ($90\times0.5$).

The generator loops through the list of vertices and for each vertex, it will form edges with all other vertices in the same vertex list. The weight for each edge is a randomly generated integer between 1 and 100. Every time a new edge is added, a counter will increase by 1. The loops end when the counter reaches the desired number of edges. This will grow a graph to the desired density. The following pseudo code is a simple illustration of the generation process:

```java
numOfAddedEdges -> 0;
numOfEdges -> MaxNumEdges * Density;
outer loop:
    for i = 0; i < vertexList.length; i++ {
        srcVert -> vertexList[i];
        inner:
            for j -> 0; j < vertexList.length; j++) {
                if (numOfAddedEdges == numOfEdges) break outer;
                if (j == i) continue inner;
                tarVert -> vertexList[j];
                weight -> Random(1, 100);
                graph.addEdge(srcVert, tarVert, weight);
                numOfAddedEdges++;
            }
    }
```



## Experiments

For this project, we want to test the performance of the two graph data structures, **adjacency list** and **incidence matrix**, under three scenarios with varied densities. To create the graphs to test, we decided to have 1,000 vertices and then vary the density by adding different numbers of edges.

To vary the density, we defined three types of densities: **High** (0.3), **Medium** (0.1), and **Low** (0.05). Although the theoretical range of the density is [0, 1], due to the limitations of our computing power, too high a density would cause the experiments to run a long time or even face `Out of Memory` exception, since 1,000 vertices can have a maximum of whopping 999,000 edges. Therefore, we arrived at those three numbers as they are not extremely high to cause memory or time issues, and they are spread out and large enough to create significant experimental results.

### Scenarios:

The three scenarios are **Shrinking Graph**, **Nearest Neighbors**, and **Changing Associations**. For each of the scenarios, we tested the two graph structures with the three different densities for 5 iterations and took the average running time of those 5 iterations to do the analysis. Below are the descriptions of the three scenarios:

#### Shrinking Graph

For each of the two data structures with a given density, we run the following operations and measure the running time in milliseconds:

- Remove all the vertices
- Remove all the edges

*Note: because both of the above operations destroy the graph structure, we create a new same graph before each of the operations.*

#### Nearest Neighbors

For each of the two data structures with a given density, we run the following operations and measure the running time in milliseconds:

- Get all in-neighbors for all the vertices
- Get 5 nearest in-neighbors for all the vertices
- Get 999 nearest in-neighbors for all the vertices
- Get all out-neighbors for all the vertices
- Get 5 nearest out-neighbors for all the vertices
- Get 999 nearest out-neighbors for all the vertices

#### Changing Associations

For each of the two data structures with a given density, we run the following operations and measure the running time in milliseconds:

- Update all the edges with new randomly generated weights

*Note: the new weights are all randomly generated integers between 1 and 100, when the data is large enough like in our case, we can observe both increases and decreases to the original weights.*

### Running Experiments:

For each iteration of the above scenarios, each data structure generates 9 results per density. We have run the scenarios 5 times and calculated the average of those 5 iterations to get a more accurate overall picture of the performances.







 



