package iBowling;
import java.util.Scanner;

public class BowlingScoreExercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Let's Bowl.");
		//This array keeps score
		int[] score = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		//This array keeps track of which frames were a strike
		boolean[] fStrike = {false, false, false, false, false, false, false, false, false, false};
		//If the previous frame has a spare, this variable would be true
		boolean fSpare = false;
		//f = The frames
		for(int f = 0; f < score.length; f++) {
			//The file's ball variable.
			int ball = 0;
			//Used for the ball roll function
			int firstBall = 0;
			//The calls out the function that prints out the current game's score
			System.out.println(getCurrentScore(score));
			//p = number of pins knocked down
			int p = 0;
			// b = ball used for the for loop
			for (int b = 1; b <= 2; b++) {	
			//The ball it thrown
			p += rollBall(b, firstBall);
			if (b==1) {
				firstBall = p;
			}
			//If the previous frame is a spare
			if (fSpare) {
				score[f-1] += 10 + p;
				fSpare = false;
			}
			//if the previous frame was a strike
			if(f > 0 && fStrike[f-1]) {
				if(b == 2) {
					score[f-1] += (10+p);
				}
				//if the previous 2 frames were strikes
				if(b==1 && f >= 2 && fStrike[f-2])
					score[f-2] += (20+p);
			}
			//If all the pins were knocked down in a ball (strike)
			if (p == 10 && b == 1) {
				fStrike[f] = true;
				ball = 1;
				break;
			}
			//If all the pins were knocked down in 2 ball (spare)
			else if (p == 10 && b == 2) {
				fSpare = true;
			}
			//the ball variable taken from the for loop
			ball = b;
			}
			//If, in the final frame, all the pins were knocked down and an extra ball was needed.
			if(f==9 && p == 10) {
				//In a strike
				if(fStrike[f]) {
					System.out.println("Bouns!");
				//If the second ball was a strike
				boolean secondStrike = false;
				//If the previous frame was a strike
				if(fStrike[f-1]) {
					score[f-1] =+ p;
					}
				
				firstBall = 0;
				//for the bonus balls
				for (int bb = 1; bb <= 2; bb++) {
					p += rollBall(bb,firstBall);
					//if the previous frame was a strike
					if(bb==1 && fStrike[f-1]) {
						score[f-1] += p;
						}
					//If the previous frame is a spare
					if (fSpare) {
						score[f-1] += p;
					}
					//If all the pins are knocked down in one ball or all the pins are knocked down twice in a row
					if ((p == 20 && bb == 1)||(p >= 20 && bb == 2 && secondStrike)) {
						System.out.println("Strike!");
					}
					//If all the pins are knocked down in two balls
					else if (p == 20 && bb == 2 && !secondStrike) {
						System.out.println("Spare.");
						p=20;
					}}
				//The final frame would be recorded here in the strike
				score[f] = p;
				}
			//In a spare
			else if (fSpare) {
				System.out.println("Spare.");
				System.out.println("Bonus!");
				//The number of pins knocked down in the extra ball
				int bb = rollBall(1, firstBall);
				//If all the pins are knocked down by the extra ball
				if (bb == 10) {
					System.out.println("Strike!");
				}
				//The pins knocked down by the extra ball are added here
				p += bb;
				//The final frame would be recorded here in the spare
				score[f] = p;
			}
				break;
			}
			//This would record the frames by default
			else;
			score[f] = RecordFrame(ball, p);
		}
		//This prints the final score
		System.out.println(getCurrentScore(score));
	}
	
	//This function rolls the ball
	@SuppressWarnings({ "resource" })
	private static int rollBall(int b, int firstBall) {
		//The variable of how many pins fell
		int fallPin = 0;
		//This variable is false unless a right number is inputed
		boolean rightNumber = false;
		while(!rightNumber) {
		//Introduces the scanner for input
		Scanner myBall = new Scanner(System.in);
		try {
		//Input
		System.out.println("How many pins fall?");
		fallPin = myBall.nextInt();
		//System.out.println(fallPin);
		}
		//If the input is not the number
		catch(Exception e) {
			System.out.println("That is not a number");
		}
		//If the input is more than the pins currently standing
		if (fallPin > 10 || (b==2 && fallPin > 10-firstBall)) {
			System.out.println("Too high!");
		}
		//If the input is negative
		else if (fallPin < 0) {
			System.out.println("Too low...");
		}
		//If a correct number is inputed
		else rightNumber = true;
		}
		return fallPin;
	}
	
	//This gets the current score
	private static int getCurrentScore(int[] score) {
		//The current score
		int currentScore = 0;
		//Adds to the current score frame by frame
		for (int fs = 0; fs < score.length; fs ++) {
			currentScore += score[fs];
		}
		return currentScore;
	}
	//This function records each frame's score with a couple of exceptions
	private static int RecordFrame(int b, int p) {
			//If all the pins were knocked down in one ball
			if (p >= 10 && b == 1) {
				System.out.println("Strike!");
				p = 0;
			}
			//If all the pins were knocked down in two pins
			else if (p >= 10 && b == 2) {
				System.out.println("Spare.");
				p = 0;
			}
		return p;
	}
}