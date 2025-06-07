package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Maze {

    private File file;

    public Maze(File file) {
        this.file = file;
    }

    public int getRows() {
        try {
            Scanner scanner = new Scanner(file);
            int row = 0;
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
            }
            scanner.close();
            return row;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    public int getCols() {
        try {
            Scanner scanner = new Scanner(file);
            int cols = 0;
            if (scanner.hasNextInt()) {
                scanner.nextInt(); // skip first int
                if (scanner.hasNextInt()) {
                    cols = scanner.nextInt();
                }
            }
            scanner.close();
            return cols;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    public int getMazes() {
        try {
            Scanner scanner = new Scanner(file);
            int maze = 0;
            if (scanner.hasNextInt()) {
                scanner.nextInt(); // skip first int
                if (scanner.hasNextInt()) {
                    scanner.nextInt(); // skip second int
                    if (scanner.hasNextInt()) {
                        maze = scanner.nextInt();
                    }
                }
            }
            scanner.close();
            return maze;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    public boolean isDollar() {
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // skip header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("$")) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
            return false;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public String[][] toArr() {
        try {
            Maze m = new Maze(file);
            int rows = m.getRows();
            int col = m.getCols();

            String[][] arr = new String[rows][col];
            Scanner scanner = new Scanner(file);

            // skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // read the maze data
            for (int i = 0; i < rows; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < col; j++) {
                    arr[i][j] = line.substring(j, j + 1);
                }
            }

            scanner.close();
            return arr;

        } catch (FileNotFoundException e) {
            return new String[0][0];
        }
    }

    public void findPath() {
        Maze m = new Maze(file);
        String[][] maze = m.toArr();
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int wolvR = -1;
        int wolvC = -1;
        outerLoop: for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j].equals("W")) {
                    wolvR = i;
                    wolvC = j;
                    break outerLoop;
                }
            }
        }

        Stack<Coordinate> stack = new Stack<>();
        stack.push(new Coordinate(wolvR, wolvC));

        while (!stack.isEmpty()) {
            Coordinate curr = stack.pop();
            int row = curr.getRow();
            int col = curr.getCol();

            if (maze[row][col].equals("$")) {
                System.out.println("Shortest path found!");
                return;
            }

            if (!visited[row][col]) {
                visited[row][col] = true;
                System.out.println("Visited cell: (" + row + ", " + col + ")");

                int[] dRow = { -1, 1, 0, 0 };
                int[] dCol = { 0, 0, 1, -1 };
                for (int k = 0; k < 4; k++) {
                    int nextRow = row + dRow[k];
                    int nextCol = col + dCol[k];

                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                            && (maze[nextRow][nextCol].equals(".") || maze[nextRow][nextCol].equals("$"))
                            && !visited[nextRow][nextCol]) {
                        stack.push(new Coordinate(nextRow, nextCol));
                    }
                }
            }
        }

        System.out.println("No path found to the goal.");
    }

    class Coordinate {
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

	public static void main(String[] args) {
    
    		String filename = "easyMap1.txt"; // default
    		if (args.length > 0) {
       			filename = args[0];
   	 	}

    		File file = new File(filename);
    		Maze maze = new Maze(file);

    		System.out.println("Using file: " + filename);
    		System.out.println("Rows: " + maze.getRows());
    		System.out.println("Cols: " + maze.getCols());
    		System.out.println("Mazes: " + maze.getMazes());
    		System.out.println("Dollar present: " + maze.isDollar());

    		maze.findPath();
	}


}
