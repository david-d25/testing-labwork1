package space.davids_digital

import main.kotlin.space.davids_digital.UndirectedGraph
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class UndirectedGraphTest {

    @Test
    fun `negative size graph creation throws exception`() {
        assertThrows<IllegalArgumentException> { UndirectedGraph(-1) }
    }

    @Test
    fun `empty graph search throws exception`() {
        assertThrows<IllegalArgumentException> { UndirectedGraph(0).breadthFirstSearch(0) }
    }

    @Test
    fun `search by negative vertex index throws exception`() {
        assertThrows<IllegalArgumentException> { UndirectedGraph(1).breadthFirstSearch(-1) }
    }

    @Test
    fun `search by out-of-bounds vertex index throws exception`() {
        assertThrows<IllegalArgumentException> { UndirectedGraph(1).breadthFirstSearch(1) }
    }

    @Test
    fun `single vertex graph search returns -1`() {
        assertEquals(listOf(-1), UndirectedGraph(1).breadthFirstSearch(0))
    }

    @Test
    fun `disconnected graph search returns -1's`() {
        assertEquals(listOf(-1, -1, -1, -1), UndirectedGraph(4).breadthFirstSearch(0))
    }

    @Test
    fun `two-vertex connected graph search returns correct result`() {
        val graph = UndirectedGraph(2)
        graph.addEdge(0, 1)
        assertEquals(listOf(-1, 0), graph.breadthFirstSearch(0))
        assertEquals(listOf(1, -1), graph.breadthFirstSearch(1))
    }

    @Test
    fun `connected graph search returns correct result`() {
        val graph = UndirectedGraph(4)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 3)
        assertEquals(listOf(-1, 0, 0, 0), graph.breadthFirstSearch(0))
        assertEquals(listOf(1, -1, 1, 1), graph.breadthFirstSearch(1))
        assertEquals(listOf(2, 2, -1, 2), graph.breadthFirstSearch(2))
        assertEquals(listOf(3, 3, 3, -1), graph.breadthFirstSearch(3))
    }

    @Test
    fun `ring-topology graph search does correct-order vertex traversal`() {
        val graph = UndirectedGraph(6)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 4)
        graph.addEdge(4, 5)
        graph.addEdge(5, 0)
        assertEquals(listOf(-1, 0, 1, 2, 5, 0), graph.breadthFirstSearch(0))
    }
}