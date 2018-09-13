package sudokuSolution;

import java.util.Scanner;
import org.junit.Test;

public class SudokuSolver 
{
	int[][] sudoku = new int[9][9];
	int[][] solution = new int[9][9];
	
	public int[][] input()
	{
		Scanner scan = new Scanner(System.in);
		
		for (int row = 1; row < 10; row++)
		{
			System.out.println("Input the integers for row " + row + ": ");
			
			for (int col = 1; col < 10; col++)
			{
				solution[row-1][col-1] = sudoku[row-1][col-1] = scan.nextInt();
			}
		}
		
		return sudoku;
	}
	
	public void output(int[][] sudoku)
	{
		String line = new String("---------------------------");
		
		for (int row = 0; row < 9; row++)
		{
			if (row % 3 == 0)
				System.out.println(line);
			
			for (int col = 0; col < 9; col++)
			{
				if (col % 3 == 0 && col != 0)
					System.out.print("  |  ");
				
				System.out.print(sudoku[row][col] + " ");
			}//close inner loop
			
			System.out.println();
		}//close outer loop
		
		System.out.println(line);
	}
	
	public int[][] solve(int[][] solution)
	{	
		int count = 0;
		
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				if (solution[row][col] == 0)
				{
					count = 0;
					
					for (int i = 1; i < 10; i++)
					{
						solution[row][col] = i;
						
						if (isValid(solution, row, col))
						{	
							if (solution == solve(solution))
							{	
								int zero = 0;
								
								for (int r = 0; r < 9; r++)
								{
									for (int c = 0; c < 9; c++)
									{
										if (solution[r][c] == 0)
											zero++;
									}
								}
								
								if (zero == 0)
								{
									return solution;
								}
								
								count++;
							}
							else
							{
								solution = solve(solution);
								break;
							}
						}
						else
							count++;
					}
					
					if (count == 9)
					{
						solution[row][col] = 0;
						return solution;
					}
				}
			}//close col loop
		}//close row loop

		return solution;
	}//close solve
	
	public boolean isValid(int[][] sudoku, int r, int c)
	{	
		for (int col = 0; col < 9; col++)
		{
			if (sudoku[r][c] == sudoku[r][col] && c != col)
				return false;
		}
				
		for (int row = 0; row < 9; row++)
		{
			if (sudoku[r][c] == sudoku[row][c] && r != row)
				return false;
		}

		for (int row = r / 3 + (r / 3) * 2; row < r / 3 + (r / 3) * 2 + 3; row++)
		{
			for (int col = c / 3 + (c / 3) * 2; col < c / 3 + (c / 3) * 2 + 3; col++)
			{
				if (sudoku[r][c] == sudoku[row][col] && r != row && c != col)
					return false;
			}
		}
		
		return true;
	}
	
	@Test
	public void testing()
	{
		
		SudokuSolver sudoku = new SudokuSolver();
		int[][] s = null;

		s = sudoku.input();
		sudoku.output(s);
		
		sudoku.output(sudoku.solve(s));
		
	}

}
