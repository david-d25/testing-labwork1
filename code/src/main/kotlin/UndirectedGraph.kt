package main.kotlin

import java.lang.IllegalArgumentException
import java.util.*

class UndirectedGraph(private val verticesNum: Int) {

    init {
        if (verticesNum < 0)
            throw IllegalArgumentException("This graph can't have negative number of vertices")
    }

    private val vertices: List<Vertex> = (0 until verticesNum).map { Vertex(it) }

    public fun addEdge(idx1: Int, idx2: Int) {
        if (idx1 < 0 || idx2 < 0 || idx1 >= vertices.size || idx2 >= vertices.size)
            throw IllegalArgumentException("Invalid indices: given values $idx1 and $idx2, the valid range is [0, ${vertices.size - 1}]")

        vertices[idx1].neighbors.add(vertices[idx2])
        vertices[idx2].neighbors.add(vertices[idx1])
    }

    public fun breadthFirstSearch(startVertexIndex: Int): List<Int> {
        if (startVertexIndex < 0 || startVertexIndex >= vertices.size)
            throw IllegalArgumentException("Too big index: given index $startVertexIndex, but graph has ${vertices.size} vertices")

        val queue: Queue<Vertex> = LinkedList()
        val parents: MutableList<Int> = (0 until verticesNum).map { -1 }.toMutableList()
        queue.add(vertices[startVertexIndex])
        vertices[startVertexIndex].visited = true
        while (queue.isNotEmpty()) {
            val thisVertex = queue.remove()
            thisVertex.neighbors
                .filter { !it.visited }
                .forEach {
                    queue.add(it)
                    it.visited = true
                    parents[it.index] = thisVertex.index
                }
        }
        resetVisited()
        return parents
    }

    private fun resetVisited() {
        vertices.forEach { it.visited = false }
    }

    data class Vertex (
        val index: Int,
        var visited: Boolean = false,
        val neighbors: SortedSet<Vertex> = sortedSetOf()
    ): Comparable<Vertex> {
        override fun compareTo(other: Vertex) = index - other.index
    }
}