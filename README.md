# textbased-2048
Name: LeeAnn Chu
Contact: lchu7@u.rochester.edu
Date: 9.20.20
------------------------------------------------------------------------------------------------
  My lab partner is Tasheem Brown and we worked together on the concepts of this lab
  namely my swipe() method and my move() method. We also worked on the opening screen together
------------------------------------------------------------------------------------------------

	"Swipe" the board by using the normal wasd keys. 
	r to restart
	q to quit.

I'm really proud of how those two methods turned out, I hadn't considered going into this 
how many different scenarios there would be in this game and how important the order of operations 
would be. These are more detailed notes on what the algorithms are doing behind the scenes 

https://imgur.com/a/sTrI2fi

Utilities designs the methods I call upon in the text.2048 file. 
It's all pretty straight forward, and mostly commented throughout.

As for text2048 it handles what's printed to the console, and a lot of the extra requirements
thrown on top of everything else. I disagree with a lot of the things the instructor has us print out, 
like having to print the board even if the move made was invalid, or if you're trying to quit or restart
the game. 

Lastly, in the requirements he specifies that printing if a move is valid after every keypress, so 
even when you're entering confirmation for quitting you still have to see the "INVALID MOVE" text. 
Which seems really redundant, but that's what's written in the project requirements. 

		"INVALID" moves are considered moves that do not change any of the positions of the
		numbers on the board. (this includes quitting, resetting, and invalid inputs)
