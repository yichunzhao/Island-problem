package solver;


import lombok.extern.slf4j.Slf4j;
import model.Grid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
public class IslandSolver<E> extends AbstractSolver<E> {

    protected Queue<Grid.Coordinate> queue;

    public IslandSolver(Grid<E> grid) {
        super(grid);

        this.queue = new LinkedList<>();
    }

    public static class Island<E> {
        List<E> lands = new ArrayList<>();
    }

    @Override
    public Result solve(Grid.Coordinate source) {
        Result result = Result.create();
        List<Island> islands = new ArrayList<>();

        //put source in the Q, and keep it until all its neighbors found.
        queue.offer(source);

        //a cell enqueued is a visited cell.
        markAsVisited(source);

        while (!queue.isEmpty()) {
            //deQueue v from the queue
            Grid.Coordinate v = queue.poll();

            //Finding out all its surrounding cells
            //If it is not-visited yet, enQueue it.
            grid.adjacentTo(v).stream()
                    .filter(n -> !isVisited(n))
                    .forEach(nv -> {
                        queue.offer(nv);
                        markAsVisited(nv);
                    });

            log.info(this.queue.toString());

            //mark current cell as visited.

        }



        result.addResult(Result.Key.Visited, visited);

        return result;
    }

}
