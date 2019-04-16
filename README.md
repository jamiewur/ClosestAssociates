# Closest Associates Report

___by Jing Li (s3676458) and Dili Wu (s3649332)___



## Data Generation

A dedicated data generator class (`GraphGenerator.java`) was created to facilitate graph generation and scenario evaluation. With the number of vertices `N` supplied, the generator creates a list of integers with 1 to N as the elements, and then shuffles the elements. This is the list of all vertices the graph has.

To grow the graph to the desired density, the generator will first add all the elements from the vertex list to the graph (Adjacency List or Incidence Matrix), and then add edges to the graph until the intended density is reached.

#### Graph Density

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



 



